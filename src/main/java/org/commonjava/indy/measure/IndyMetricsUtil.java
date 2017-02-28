package org.commonjava.indy.measure;

import com.codahale.metrics.*;
import org.commonjava.indy.measure.annotation.IndyTimers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.commonjava.indy.measure.annotation.IndyMeter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;

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
        return metrics.timer(name(indyTimers.c(), indyTimers.name()));
    }

    public Meter getMeter(IndyMeter indyMeter){
        return metrics.meter(name(indyMeter.c(), indyMeter.name()));
    }
}
