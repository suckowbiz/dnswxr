package biz.suckow.dnswxr.business;

/*
 * #%L
 * dnswxr
 * %%
 * Copyright (C) 2013 - 2016 suckow.biz
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author Tobias Suckow <tobias@suckow.biz>
 */
@ApplicationScoped
public class EndpointFactory {
    public static final String API_URL = "https://api.domrobot.com/xmlrpc/";
    private final Provider<TrustManager> managerProvider;
    private final Provider<HostnameVerifier> verifierProvider;

    @Inject
    public EndpointFactory (final Provider<TrustManager> managerProvider, final Provider<HostnameVerifier> verifierProducer) {
        this.managerProvider = managerProvider;
        this.verifierProvider = verifierProducer;
    }

    @Produces
    WebTarget configure() {
        try {
            final SecureRandom random = new SecureRandom();
            final HostnameVerifier verifier = this.verifierProvider.get();
            final TrustManager[] managers = this.getManagers();
            SSLContext context = SSLContext.getInstance("SSL");
            context.init(null, managers, random);
            return ClientBuilder.newBuilder().hostnameVerifier(verifier).sslContext(context).build().target(API_URL);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new IllegalStateException("Failure to build always trusting SSL client.");
        }
    }

    private TrustManager[] getManagers() {
        return  new TrustManager[] {this.managerProvider.get()};
    }
}
