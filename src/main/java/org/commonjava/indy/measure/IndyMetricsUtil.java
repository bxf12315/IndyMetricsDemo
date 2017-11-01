/**
 * Copyright (C) 2011-2017 Red Hat, Inc. (https://github.com/Commonjava/indy)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.commonjava.indy.measure;

import com.codahale.metrics.*;
import org.commonjava.indy.measure.annotation.IndyException;
import org.commonjava.indy.measure.annotation.IndyMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

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

    public Timer getTimer(IndyMetrics indyMetrics){
        logger.info("call in IndyMetricsUtil.getTimer" );
        return metrics.timer(name(indyMetrics.c(), indyMetrics.name()));
    }

    public Meter getMeter(IndyMetrics indyMetrics){
        logger.info("call in IndyMetricsUtil.getMeter" );
        return metrics.meter(name(indyMetrics.c(), indyMetrics.name()));
    }

    public Meter getExceptionMeter(IndyException indyException){
        logger.info("call in IndyMetricsUtil.getExceptionMeter has exception" );
        return metrics.meter(name(indyException.c(), indyException.name()));
    }
}
