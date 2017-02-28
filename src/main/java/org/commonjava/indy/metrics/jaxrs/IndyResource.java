package org.commonjava.indy.metrics.jaxrs;

import org.commonjava.indy.measure.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
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
    @Path("/timer/{isException :[a-zA-Z]+}")
    @IndyTimerAnnotation
    @IndyTimers(c=IndyResource.class,name="testTimerRequest")
    @IndyExceptionMeterAnnotation
    @IndyExceptionMeter(c=IndyResource.class,name="testTimerRequest.hasException")
    public Response getTimer(@PathParam("isException") String isException) throws Exception{
        if(isException.equals("true")){
            throw new Exception("getTimer has a exception");
        }
        logger.info("call in method : getTimer");
        Random random = new Random();
        Thread.sleep(random.nextInt(100));
        return Response.ok("Timer: well done", MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/meter/{isException :[a-zA-Z]+}")
    @IndyMeter(c=IndyResource.class,name="testMeterRequest")
    @IndyMeterAnnotation
    @IndyExceptionMeterAnnotation
    @IndyExceptionMeter(c=IndyResource.class,name="testMeterRequest.hasException")
    public  Response getMeter(@PathParam("isException") String isException) throws Exception{
        logger.info("call in method : getMeter");
        if(isException.equals("true")){
            throw new Exception("getMeter has a exception");
        }
        Thread.sleep(100);
        return Response.ok("Meter: well done", MediaType.APPLICATION_JSON).build();
    }

}
