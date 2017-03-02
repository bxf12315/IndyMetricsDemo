package org.commonjava.indy.metrics.jaxrs.interceptor;

import org.commonjava.indy.measure.annotation.IndyMetrics;
import org.commonjava.indy.measure.IndyMetricsUtil;

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
@IndyMetrics(type = IndyMetrics.MetricsType.TIMER)
public class TimerInterceptor {
    @Inject
    IndyMetricsUtil util;

    private static final Logger logger = LoggerFactory.getLogger(TimerInterceptor.class);

    @AroundInvoke
    public Object operation(InvocationContext context) throws Exception {
        logger.info("call in TimerInterceptor.operation");
                Timer.Context contextTime = util.getTimer(context.getMethod().getAnnotation(IndyMetrics.class)).time();
                Object obj = context.proceed();
                contextTime.stop();
                return obj;
    }

}
