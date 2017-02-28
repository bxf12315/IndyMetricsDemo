package org.commonjava.indy.metrics.jaxrs.interceptor;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import org.commonjava.indy.measure.annotation.IndyTimerAnnotation;
import org.commonjava.indy.measure.IndyMetricsUtil;
import org.commonjava.indy.measure.annotation.IndyTimers;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.codahale.metrics.Timer;


/**
 * Created by xiabai on 2/24/17.
 */
@Interceptor
@IndyTimerAnnotation

public class TimerInterceptor {
    @Inject
    IndyMetricsUtil util;

    private static final Logger logger = LoggerFactory.getLogger(TimerInterceptor.class);

    @AroundInvoke
    public Object operation(InvocationContext context) throws Exception {
        logger.info("call in TimerInterceptor.operation");
                Timer.Context contextTime = util.getTimer(context.getMethod().getAnnotation(IndyTimers.class)).time();
                Object obj = context.proceed();
                contextTime.stop();
                return obj;
    }

}
