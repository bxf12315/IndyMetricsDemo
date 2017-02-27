package org.commonjava.indy.metrics.jaxrs.interceptor;

import com.codahale.metrics.MetricRegistry;
import org.commonjava.indy.measure.IndyTimerAnnotation;
import org.commonjava.indy.measure.IndyMetricsUtil;
import org.commonjava.indy.measure.IndyTimers;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.codahale.metrics.Timer;
//import com.codahale.metrics.annotation.Metric;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * Created by xiabai on 2/24/17.
 */
@Interceptor
@IndyTimerAnnotation
public class TimerInterceptor {
//    private static final MetricRegistry metrics = new MetricRegistry();
//    private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();

//    @Produces
//    @Metric(name = "customTimer")
//    Timer timer = new Timer(new SlidingTimeWindowReservoir(1L, TimeUnit.MINUTES));
//    private static final Timer requests = metrics.timer(name(TimerInterceptor.class, "request"));
    @Inject
    MetricRegistry metrics;

    private static final Logger logger = LoggerFactory.getLogger(TimerInterceptor.class);

    @AroundInvoke
    public Object operation(InvocationContext context) throws Exception {
        logger.info("call in TimerInterceptor.operation");
        Timer.Context contextTime = IndyMetricsUtil.getTimer(context.getMethod().getAnnotation(IndyTimers.class),metrics).time();
        IndyMetricsUtil.getReporter(metrics);
        try {
            Object obj = context.proceed();
            return obj;
        }finally {
            contextTime.stop();
        }

    }

}
