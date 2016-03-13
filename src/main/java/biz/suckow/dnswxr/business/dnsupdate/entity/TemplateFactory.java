package biz.suckow.dnswxr.business.dnsupdate.entity;

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
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import javax.enterprise.inject.Produces;
import java.util.Properties;

/**
 * @author Tobias Suckow <tobias@suckow.biz>
 */
public class TemplateFactory {
    private static final String TEMPLATE_NAME = "template-dns-update.vm";

    /**
     * The INWX RPC XML is a pain so instead of JAXB use a template to ease development.
     *
     * @return the template
     */
    @Produces
    public Template compile() {
        final Properties properties = new Properties();
        properties.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        properties.setProperty("input.encoding", "UTF-8");
        properties.setProperty("classpath.resource.loader.class",
                ClasspathResourceLoader.class.getName());

        Velocity.init(properties);
        final Template result = Velocity
                .getTemplate(TemplateFactory.TEMPLATE_NAME);
        return result;
    }
}
