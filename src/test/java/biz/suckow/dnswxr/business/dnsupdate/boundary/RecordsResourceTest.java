package biz.suckow.dnswxr.business.dnsupdate.boundary;

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


import biz.suckow.dnswxr.business.dnsupdate.control.UpdateService;
import org.easymock.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author Tobias Suckow <tobias@suckow.biz>
 */
public class RecordsResourceTest extends EasyMockSupport {

    @Mock(type = MockType.NICE)
    Logger logger;

    @Mock
    UpdateService service;

    @TestSubject
    RecordsResource cut = new RecordsResource();

    @BeforeClass
    private void setup() {
        EasyMockSupport.injectMocks(this);
    }

    @Test
    public void verifyServiceInvocationAsExpected() {
        String username = "duke";
        String password = "duke-123";
        String ip = "127.0.0.1";
        Set<String> ids = new HashSet<>();
        String ttl = "42";
        String expectedResult = "OK";

        EasyMock.expect(this.service.updateARecord(username, password, ip, ids, ttl)).andStubReturn(expectedResult);
        replayAll();

        Response actualResult = cut.update(username, password, ip, ids, ttl);
        Assert.assertEquals(actualResult.getStatus(), HttpServletResponse.SC_OK);
        Assert.assertEquals(actualResult.getEntity(), expectedResult);
        verifyAll();
    }
}