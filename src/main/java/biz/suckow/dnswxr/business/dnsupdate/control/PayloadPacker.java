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

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.StringWriter;
import java.util.Set;

/**
 * @author Tobias Suckow <tobias@suckow.biz>
 */
@Singleton
public class PayloadPacker {
    private final Template template;

    @Inject
    public PayloadPacker(final Template template) {
        this.template = template;
    }

    public String pack(final String username, final String password,
                       final String ip, final Set<String> ids, final String ttl) {
        final Context context = new VelocityContext();
        context.put("username", username);
        context.put("password", password);
        context.put("ip", ip);
        context.put("ids", ids);
        context.put("ttl", ttl);

        final StringWriter writer = new StringWriter();
        this.template.merge(context, writer);
        final String result = writer.toString();
        return result;
    }
}
