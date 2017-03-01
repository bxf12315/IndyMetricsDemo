package org.commonjava.indy.metrics.proxy;


import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by xiabai on 2/28/17.
 */
public class IndyMetricsFactory {
//    private static final Logger logger = LoggerFactory.getLogger(IndyMetricsFactory.class);
//
//    private static MetricRegistry instance;
//
//    private IndyMetricsFactory(){}
//
//    public static MetricRegistry getInstance(){
//        ScheduledReporter reporter = null;
//        if(instance==null){
//        synchronized(IndyMetricsFactory.class){
//                if(instance==null){
//                    instance = new MetricRegistry();
//                    synchronized(IndyMetricsFactory.class) {
//                        if (reporter == null) {
//                            reporter = ConsoleReporter.forRegistry(instance).build();
//                            logger.info("call in IndyMetricsUtil.initReporter and reporter has been start");
//                            reporter.start(30, TimeUnit.SECONDS);
//                        }
//                    }
//                }
//            }
//        }
//        return instance;
//    }
}
