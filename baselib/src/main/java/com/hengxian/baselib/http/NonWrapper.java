package com.hengxian.baselib.http;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 注明接口的 response 没有包装
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RUNTIME)
public @interface NonWrapper {
}
