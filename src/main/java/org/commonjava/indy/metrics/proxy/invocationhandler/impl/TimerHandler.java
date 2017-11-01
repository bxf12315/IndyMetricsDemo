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
package org.commonjava.indy.metrics.proxy.invocationhandler.impl;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.commonjava.indy.measure.annotation.IndyMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * Created by xiabai on 3/1/17.
 */
public class TimerHandler<T> {
    private static final Logger logger = LoggerFactory.getLogger(TimerHandler.class);

    public Object operation(MetricRegistry metricRegistry, T proxyInstance, Method method, Object[] parameters,IndyMetrics indyMetrics) throws Throwable{
        logger.info("call in TimerHandler.operation");
        Timer.Context contextTime  = metricRegistry.timer(name(indyMetrics.c(),indyMetrics.name())).time();
        Object object =  method.invoke(proxyInstance,parameters);
        contextTime.stop();
        return object;
    }
}
