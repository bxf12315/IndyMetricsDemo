package org.commonjava.indy.measure;

import com.codahale.metrics.*;
import org.commonjava.indy.measure.annotation.IndyExceptionMeter;
import org.commonjava.indy.measure.annotation.IndyTimers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import org.commonjava.indy.measure.annotation.IndyMeter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * Created by xiabai on 2/27/17.
 */
@ApplicationScoped
public class IndyMetricsUtil {
    private static final Logger logger = LoggerFactory.getLogger(IndyMetricsUtil.class);

    @Inject
    MetricRegistry metrics;

    private ScheduledReporter reporter;


    @PostConstruct
    public void initReporter(){
        reporter = ConsoleReporter.forRegistry(metrics).build();
        logger.info("call in IndyMetricsUtil.initReporter and reporter has been start");
        reporter.start(30, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void destroyResource(){
        reporter.close();
    }

    public Timer getTimer(IndyTimers indyTimers){
        logger.info("call in IndyMetricsUtil.getTimer" );
        return metrics.timer(name(indyTimers.c(), indyTimers.name()));
    }

    public Meter getMeter(IndyMeter indyMeter){
        logger.info("call in IndyMetricsUtil.getMeter" );
        return metrics.meter(name(indyMeter.c(), indyMeter.name()));
    }


    public Meter getTimer(IndyTimers indyTimers,boolean isException ){
        logger.info("call in IndyMetricsUtil.getTimer has exception" );
        return metrics.meter(name(indyTimers.c(), indyTimers.name()+".hasException"));
    }

    public Meter getMeter(IndyMeter indyMeter,boolean isException){
        logger.info("call in IndyMetricsUtil.getMeter has exception" );
        return metrics.meter(name(indyMeter.c(), indyMeter.name()+".hasException"));
    }

    public Meter getExceptionMeter(IndyExceptionMeter indyExceptionMeter){
        logger.info("call in IndyMetricsUtil.getExceptionMeter has exception" );
        return metrics.meter(name(indyExceptionMeter.c(), indyExceptionMeter.name()));
    }
}
