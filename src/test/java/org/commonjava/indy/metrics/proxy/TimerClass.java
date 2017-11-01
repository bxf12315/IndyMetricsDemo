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
package org.commonjava.indy.metrics.proxy;

/**
 * Created by xiabai on 3/1/17.
 */

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import org.commonjava.indy.measure.annotation.IndyException;
import org.commonjava.indy.measure.annotation.IndyMetrics;
import org.commonjava.indy.metrics.jaxrs.IndyResource;
import org.commonjava.indy.metrics.proxy.invocationhandler.IndyMeticsInvocationHandler;

import java.lang.reflect.Proxy;

public interface TimerClass {

    @IndyMetrics(type = IndyMetrics.MetricsType.TIMER,c=IndyResource.class,name="testTimerRequest")
    @IndyException(type = IndyException.IndyExceptionType.METERHANDLER,c=IndyResource.class,name="testTimerRequest has exception")
    public void getTimer(boolean isException) throws Exception;

    public static TimerClass getTimerClass(MetricRegistry metricRegistry, ScheduledReporter reporter){
        TimerClass impl = new TimerClassImpl();
        IndyMeticsInvocationHandler<TimerClass> handler = new IndyMeticsInvocationHandler<TimerClass>(impl,metricRegistry,reporter);

        return (TimerClass) Proxy.newProxyInstance(TimerClass.class.getClassLoader(), new Class[]{TimerClass.class},handler);
    }
}
