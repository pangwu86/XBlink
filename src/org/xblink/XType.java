package org.xblink;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.xblink.writer.XMLWriterUtil;

/**
 * 
 * 属性类型抽象类.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public abstract class XType implements Constants {

	/** 类加载器切换器 */
	protected ClassLoaderSwitcher classLoaderSwitcher;

	/** 与传入的参数保存同步更新 */
	protected ImplClasses xmlImplClasses;

	/** 字段集合 */
	protected List<Field> fieldTypes = new ArrayList<Field>();

	/**
	 * 获得该变量的注解类型.
	 * 
	 * @param field
	 * @return 是否成功
	 */
	public abstract boolean getAnnotation(Field field);

	/**
	 * 写项目.
	 * 
	 * @param writer
	 * @param obj
	 * @throws Exception
	 */
	public abstract void writeItem(Object obj, XMLWriterUtil writer) throws Exception;

	/**
	 * 读项目.
	 * 
	 * @param obj
	 * @param baseNode
	 * @throws Exception
	 */
	public abstract void readItem(Object obj, Node baseNode) throws Exception;

	/**
	 * 判断字段集合是否为空.
	 * 
	 * @return 是否是空
	 */
	public boolean isFieldsEmpty() {
		if (fieldTypes.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取类加载器切换器.
	 * 
	 * @return 类加载器切换器
	 */
	public ClassLoaderSwitcher getClassLoaderSwitcher() {
		return classLoaderSwitcher;
	}

	/**
	 * 设置类加载器切换器.
	 * 
	 * @param classLoaderSwitcher
	 */
	public void setClassLoaderSwitcher(ClassLoaderSwitcher classLoaderSwitcher) {
		this.classLoaderSwitcher = classLoaderSwitcher;
	}

	/**
	 * 获得实现类参数状态.
	 * 
	 * @param xmlImplClasses
	 */
	public void setImplClass(ImplClasses xmlImplClasses) {
		this.xmlImplClasses = xmlImplClasses;
	}

	/**
	 * 返回实现类集合.
	 * 
	 * @return 实现类集合
	 */
	public ImplClasses getImplClass() {
		return xmlImplClasses;
	}

	/**
	 * 内部类.<BR/>
	 * Field的内部类型.
	 * 
	 * 
	 * @author pangwu86(pangwu86@gmail.com)
	 * 
	 */
	public class ClassType {
		Type fieldInnerClassType;
		Class<?> fieldInnerClass;
		Class<?> fieldClass;

		public Type getFieldInnerClassType() {
			return fieldInnerClassType;
		}

		public void setFieldInnerClassType(Type fieldInnerClassType) {
			this.fieldInnerClassType = fieldInnerClassType;
		}

		public Class<?> getFieldInnerClass() {
			return fieldInnerClass;
		}

		public void setFieldInnerClass(Class<?> fieldInnerClass) {
			this.fieldInnerClass = fieldInnerClass;
		}

		public Class<?> getFieldClass() {
			return fieldClass;
		}

		public void setFieldClass(Class<?> fieldClass) {
			this.fieldClass = fieldClass;
		}
	}

	/**
	 * 获得类类型.
	 * 
	 * @param field
	 * @return 类类型
	 */
	public ClassType getClassType(Field field) {
		Type type = null;
		Class<?> fieldInnerClass = null;
		Class<?> fieldClass = field.getType();
		Type gtype = field.getGenericType();
		if (gtype instanceof ParameterizedType) {
			type = ((ParameterizedType) gtype).getActualTypeArguments()[0];
		}
		if (type != null) {
			if (GENERICS.equals(type.toString())) {
				// TODO 为了最外层的实现类 root层使用
				fieldInnerClass = getImplClass().getNewInstanceClass();
			} else {
				if (type instanceof ParameterizedType) {
					Type innerType = null;
					innerType = ((ParameterizedType) type).getActualTypeArguments()[0];
					if (innerType != null && GENERICS.equals(innerType.toString())) {
						if (type.toString().startsWith(
								Class.class.toString().replaceAll(CLASS_PREFIX, EMPTY_STRING))) {
							fieldInnerClass = Class.class;
						} else {
							// FIXME
							fieldInnerClass = getImplClass().getNewInstanceClass();
						}
					}
				} else {
					fieldInnerClass = (Class<?>) type;
				}
			}
		}
		ClassType classType = new ClassType();
		classType.setFieldInnerClassType(type);
		classType.setFieldClass(fieldClass);
		classType.setFieldInnerClass(fieldInnerClass);
		return classType;
	}
}
