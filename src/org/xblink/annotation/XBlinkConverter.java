package org.xblink.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.xblink.core.convert.SingleValueTypeConverter;

/**
 * 为某个类型指定一个转换类。<br>
 * 
 * 暂时仅支持单值类型的转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface XBlinkConverter {
	Class<? extends SingleValueTypeConverter> value();
}
