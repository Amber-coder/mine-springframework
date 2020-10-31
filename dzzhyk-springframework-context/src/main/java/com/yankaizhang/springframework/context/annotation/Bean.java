package com.yankaizhang.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * 指明一个Bean对象
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bean {
}
