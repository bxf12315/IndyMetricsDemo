package org.commonjava.indy.measure.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by xiabai on 2/27/17.
 */
@Target({METHOD, ElementType.ANNOTATION_TYPE,ElementType.TYPE})
@Retention(RUNTIME)
public @interface IndyMeter {
    Class c() default Object.class;
    String name() default "indy";
}
