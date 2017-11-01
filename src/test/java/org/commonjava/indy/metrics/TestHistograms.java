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
package org.commonjava.indy.metrics;
import com.codahale.metrics.*;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.codahale.metrics.MetricRegistry.name;
/**
 * Created by xiabai on 2/24/17.
 */
public class TestHistograms {
    /**
     */
    private static final MetricRegistry metrics = new MetricRegistry();

    /**
     */
    private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).convertDurationsTo(TimeUnit.SECONDS)
            .convertRatesTo(TimeUnit.MILLISECONDS).formattedFor(Locale.US).build();

    private static final Timer requests = metrics.timer(name(TestTimers.class, "request"));

    /**
     */
    private static final Histogram randomNums = metrics.histogram(name(TestHistograms.class, "random"));

    public static void handleRequest(double random) {
        randomNums.update((int) (random*100));
    }

    public static void handleRequest(int sleep) {
        Timer.Context context = requests.time();
        try {
            //some operator
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            context.stop();
        }

    }


    public static void main(String[] args) throws InterruptedException {
        reporter.start(3, TimeUnit.SECONDS);
        Random rand = new Random();
        Random random = new Random();
        while(true){
            handleRequest(rand.nextDouble());
            handleRequest(random.nextInt(1000));
//            Thread.sleep(100);
        }
    }
}
