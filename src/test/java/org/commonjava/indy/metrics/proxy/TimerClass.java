package org.commonjava.indy.metrics.proxy;

/**
 * Created by xiabai on 3/1/17.
 */
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import org.commonjava.indy.measure.annotation.IndyTimers;
import org.commonjava.indy.measure.annotation.IndyExceptionMeter;
import org.commonjava.indy.metrics.proxy.invocationhandler.IndyMeticsInvocationHandler;

import java.lang.reflect.Proxy;

public interface TimerClass {

    @IndyTimers(c= TimerClass.class,name="test timer for handler")
    @IndyExceptionMeter(c= TimerClassImpl.class,name="test timer for handler.has a exception" )
    public void getTimer(boolean isException) throws Exception;

    public static TimerClass getTimerClass(MetricRegistry metricRegistry, ScheduledReporter reporter){
        TimerClass impl = new TimerClassImpl();
        IndyMeticsInvocationHandler<TimerClass> handler = new IndyMeticsInvocationHandler<TimerClass>(impl,metricRegistry,reporter);

        return (TimerClass) Proxy.newProxyInstance(TimerClass.class.getClassLoader(), new Class[]{TimerClass.class},handler);
    }
}
