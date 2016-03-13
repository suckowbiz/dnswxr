package biz.suckow.dnswxr.business.dnsupdate.control;

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

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import java.util.Set;

/**
 * @author Tobias Suckow <tobias@suckow.biz>
 */
public class UpdateService {
    private final PayloadPacker packer;

    /**
     * A provider enables to just get an instance on demand to have independent instances of http clients.
     */
    private final Provider<WebTarget> targetProvider;

    @Inject
    public UpdateService(final PayloadPacker packer, final Provider<WebTarget> targetProvider) {
        this.packer = packer;
        this.targetProvider = targetProvider;
    }

    public String updateARecord(final String username, final String password,
                                final String ip, final Set<String> ids, final String ttl) {
        final String payload = this.packer.pack(username, password, ip, ids, ttl);
        final Entity<String> entity = Entity.text(payload);
        final String result = this.targetProvider.get().request().post(entity, String.class);
        return result;
    }
}
