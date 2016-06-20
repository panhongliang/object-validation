package com.phl.object.validate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by panhongliang  on 2016-06-20.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Validation
public @interface Boolean {
    String value() default "";
}
