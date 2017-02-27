package org.commonjava.indy.measure.annotation;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by xiabai on 2/27/17.
 */
@InterceptorBinding
@Target({METHOD, ElementType.ANNOTATION_TYPE,ElementType.TYPE})
@Retention(RUNTIME)
public @interface IndyMeterAnnotation {
}
