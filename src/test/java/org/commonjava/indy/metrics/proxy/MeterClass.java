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

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import org.commonjava.indy.measure.annotation.IndyException;
import org.commonjava.indy.measure.annotation.IndyMetrics;
import org.commonjava.indy.metrics.jaxrs.IndyResource;
import org.commonjava.indy.metrics.proxy.invocationhandler.IndyMeticsInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * Created by xiabai on 3/1/17.
 */
public interface MeterClass {

    @IndyMetrics(type = IndyMetrics.MetricsType.METER,c=IndyResource.class,name="testMeterRequest")
    @IndyException(type = IndyException.IndyExceptionType.METERHANDLER,c=IndyResource.class,name="testMeterRequest has exception")
    public void getMeter(boolean isException) throws Exception;

    public static MeterClass getTimerClass(MetricRegistry metricRegistry, ScheduledReporter reporter){
        MeterClass impl = new MeterClassImpl();
        IndyMeticsInvocationHandler<MeterClass> handler = new IndyMeticsInvocationHandler<MeterClass>(impl,metricRegistry,reporter);

        return (MeterClass) Proxy.newProxyInstance(MeterClass.class.getClassLoader(), new Class[]{MeterClass.class},handler);
    }
}
