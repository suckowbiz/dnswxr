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

import biz.suckow.dnswxr.business.dnsupdate.entity.TemplateFactory;
import org.apache.velocity.Template;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Tobias Suckow <tobias@suckow.biz>
 */
public class PayloadPackerIT {

    @Test
    public void verifyPackingContainsExpectedContent() throws IOException, URISyntaxException {
        URI uri = this.getClass().getClassLoader().getResource("payload-packer-it-result.txt").toURI();
        Path path = Paths.get(uri);
        byte[] bytes = Files.readAllBytes(path);
        String expectedResult = new String(bytes);
        Template template = new TemplateFactory().compile();

        String username = "duke";
        String password = "duke123";
        String ip = "127.0.0.1";
        Set<String> ids = new HashSet<>();
        String ttl = "42";
        String actualResult = new PayloadPacker(template).pack(username, password, ip, ids, ttl);
        Assert.assertEquals(actualResult, expectedResult);
    }
}