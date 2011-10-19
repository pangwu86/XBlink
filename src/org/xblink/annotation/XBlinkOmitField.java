package org.xblink.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该字段被忽略，不进行序列化与反序列。
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface XBlinkOmitField {
}
