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

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tobias Suckow <tobias@suckow.biz>
 */
@Path("records")
public class RecordsResource {
    @Inject
    private Logger logger;

    @Inject
    private UpdateService service;

    @GET // GET to support e.g. FritzBox that just provides a trigger to fire a GET on IP update
    @Path("a/update")
    @Produces("application/xml")
    public Response update(@QueryParam("username") final String username,
                           @QueryParam("password") final String password,
                           @QueryParam("ip") final String ip,
                           @QueryParam("id") final Set<String> ids,
                           @QueryParam("ttl") final String ttl) {
        Response response;
        try {
            this.logger.log(Level.INFO, "--> update() Incoming ip={0}, ttl={1}s, username={2}, ids={3}",
                    new Object[]{ip, ttl, username, ids});
            final String result = this.service.updateARecord(username, password, ip, ids, ttl);
            response = Response.ok(result).build();
            this.logger.log(Level.INFO, "<-- update() Returns: {0}", result);
        } catch (final Exception e) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            this.logger.log(Level.SEVERE, "<-- update() failed", e);
        }
        return response;
    }
}
