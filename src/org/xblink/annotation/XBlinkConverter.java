package org.xblink.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.xblink.core.convert.Converter;

/**
 * 为某个类型指定一个转换类。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface XBlinkConverter {
	Class<? extends Converter> value();
}
