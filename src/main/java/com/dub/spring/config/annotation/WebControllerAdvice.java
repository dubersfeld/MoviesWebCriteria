package com.dub.spring.config.annotation;

import org.springframework.web.bind.annotation.ControllerAdvice;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ControllerAdvice
public @interface WebControllerAdvice
{
    String value() default "";
}
