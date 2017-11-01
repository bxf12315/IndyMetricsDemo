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

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import org.junit.*;


/**
 * Created by xiabai on 3/1/17.
 */
public class HandlerTests {

    static MetricRegistry metricRegistry;
    static ScheduledReporter reporter;

    @BeforeClass
    public static void begin() {
        metricRegistry = new MetricRegistry();
        reporter = ConsoleReporter.forRegistry(metricRegistry).build();
    }


    @Test
    public void testTimerHandler() throws Exception {
        TimerClass timerClass = TimerClass.getTimerClass(metricRegistry, reporter);
        for (int i = 0; i < 20; i++) {
            timerClass.getTimer(false);
        }
        for (int i = 0; i < 20; i++) {
            try {
                timerClass.getTimer(true);
            } catch (Throwable throwable) {
                //do noting
            }

        }
    }

    @Test
    public void testMeterHandler() throws Exception {
        MeterClass meterClass = MeterClass.getTimerClass(metricRegistry, reporter);

        for (int i = 0; i < 20; i++) {
            meterClass.getMeter(false);
        }
        for (int i = 0; i < 20; i++) {
            try {
                meterClass.getMeter(true);
            } catch (Throwable throwable) {
                //do noting
            }
        }
    }
}
