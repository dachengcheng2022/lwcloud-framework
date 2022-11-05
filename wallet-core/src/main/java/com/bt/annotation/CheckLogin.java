package com.bt.annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Order(1)
public @interface CheckLogin {
    String value() default "";
}
