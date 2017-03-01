package org.commonjava.indy.metrics.jaxrs.interceptor;

import com.codahale.metrics.Meter;
import org.commonjava.indy.measure.IndyMetricsUtil;
import org.commonjava.indy.measure.annotation.IndyMeterAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.commonjava.indy.measure.annotation.IndyMeter;
/**
 * Created by xiabai on 2/27/17.
 */
@Interceptor
@IndyMeterAnnotation
public class MeterInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MeterInterceptor.class);

    @Inject
    IndyMetricsUtil util;

    @AroundInvoke
    public Object operation(InvocationContext context) throws Exception {
        logger.info("call in MeterHandler.operation");
            Meter requests = util.getMeter(context.getMethod().getAnnotation(IndyMeter.class));
            Object o = context.proceed();
            requests.mark();
            return o;
    }
}
