package org.commonjava.indy.measure;

import com.codahale.metrics.*;
import org.commonjava.indy.measure.annotation.IndyTimers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.commonjava.indy.measure.annotation.IndyMeter;
import static com.codahale.metrics.MetricRegistry.name;

/**
 * Created by xiabai on 2/27/17.
 */
public class IndyMetricsUtil {
    private static final Logger logger = LoggerFactory.getLogger(IndyMetricsUtil.class);
    private final static HashMap<String,Timer> timerInstances = new HashMap<String, Timer>();
    private final static HashMap<String,Meter> meterInstances = new HashMap<String, Meter>();
    private final static ScheduledReporter reporter = null;

    public synchronized static ScheduledReporter getReporter(MetricRegistry metrics){
        logger.info("call in IndyMetricsUtil.getReporter");
        if (reporter!=null){
            return reporter;
        }
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();
        logger.info("call in IndyMetricsUtil.getReporter and reporter has been start");
        reporter.start(30, TimeUnit.SECONDS);
        return  reporter;
    }

    public synchronized static Timer getTimer(IndyTimers indyTimers, MetricRegistry metrics){
        logger.info("call in IndyMetricsUtil.getTimer");
        Timer t = timerInstances.get(indyTimers.c().getName()+indyTimers.name());
        if(t==null){
            t = metrics.timer(name(indyTimers.c(), indyTimers.name()));
            timerInstances.put(indyTimers.c().getName()+indyTimers.name(),t);
            return t;
        }else{
            return t;
        }

    }
    public synchronized static Meter getMeter(IndyMeter indyMeter, MetricRegistry metrics){
        logger.info("call in IndyMetricsUtil.getMeter");
        Meter m  = meterInstances.get(indyMeter.c().getName()+indyMeter.name());
        if (m==null){
            m =  metrics.meter(name(indyMeter.c(),indyMeter.name()));
            meterInstances.put(indyMeter.c().getName()+indyMeter.name(),m);
            return m;
        }else {
            return m;
        }
    }
}
