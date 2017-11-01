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

/**
 * Created by xiabai on 2/24/17.
 */
import com.codahale.metrics.ConsoleReporter;
        import com.codahale.metrics.MetricRegistry;
        import com.codahale.metrics.Timer;

        import java.util.Random;
        import java.util.concurrent.TimeUnit;

        import static com.codahale.metrics.MetricRegistry.name;

/**
 */
public class TestTimers {
    /**
     */
    private static final MetricRegistry metrics = new MetricRegistry();

    /**
     */
    private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();

    /**
     */
    private static final Timer requests = metrics.timer(name(TestTimers.class, "request"));

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
        Random random = new Random();
        int i=0;
        while(true){
            handleRequest(random.nextInt(1000));
            i++;
            System.out.println("result================================="+i);
        }
    }

}
