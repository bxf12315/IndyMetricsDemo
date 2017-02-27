package org.commonjava.indy.metrics.jaxrs;

import org.commonjava.indy.measure.IndyTimerAnnotation;
import org.commonjava.indy.measure.IndyTimers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Random;

/**
 * Created by xiabai on 2/24/17.
 */
@Path("/demo/")
@Produces("application/json")
@Consumes("application/json")
public class IndyResource {
    private static final Logger logger = LoggerFactory.getLogger(IndyResource.class);
    
    @GET
    @Path("/timer")
    @IndyTimerAnnotation
    @IndyTimers(c=IndyResource.class,name="testRequest")
    public Response getTimer() throws Exception{
        logger.info("call in method : getTimer");
        Random random = new Random();
        Thread.sleep(random.nextInt(100));
        return Response.ok("well done", MediaType.APPLICATION_JSON).build();
    }
}
