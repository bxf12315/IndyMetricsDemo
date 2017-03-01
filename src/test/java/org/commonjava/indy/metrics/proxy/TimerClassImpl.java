package org.commonjava.indy.metrics.proxy;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import org.commonjava.indy.measure.annotation.IndyTimers;
import org.commonjava.indy.metrics.proxy.invocationhandler.IndyMeticsInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * Created by xiabai on 3/1/17.
 */
public class TimerClassImpl implements TimerClass{

    public void getTimer(boolean isException) throws Exception{
        Thread.sleep(300);
        if(isException){
            throw new Exception("has a exception");
        }
    }

}
