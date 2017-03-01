package org.commonjava.indy.metrics.proxy;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import org.commonjava.indy.measure.annotation.IndyExceptionMeter;
import org.commonjava.indy.measure.annotation.IndyMeter;
import org.commonjava.indy.metrics.proxy.invocationhandler.IndyMeticsInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * Created by xiabai on 3/1/17.
 */
public interface MeterClass {

    @IndyMeter(c=MeterClass.class,name="test meter for handler")
    @IndyExceptionMeter(c= TimerClassImpl.class,name="test timer for handler.has a exception" )
    public void getMeter(boolean isException) throws Exception;

    public static MeterClass getTimerClass(MetricRegistry metricRegistry, ScheduledReporter reporter){
        MeterClass impl = new MeterClassImpl();
        IndyMeticsInvocationHandler<MeterClass> handler = new IndyMeticsInvocationHandler<MeterClass>(impl,metricRegistry,reporter);

        return (MeterClass) Proxy.newProxyInstance(MeterClass.class.getClassLoader(), new Class[]{MeterClass.class},handler);
    }
}
