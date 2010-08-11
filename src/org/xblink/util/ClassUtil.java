package org.xblink.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;

import org.xblink.ClassLoaderSwitcher;
import org.xblink.Constants;
import org.xblink.ImplClasses;
import org.xblink.annotations.XBlinkAlias;

/**
 * 类相关操作工具类.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class ClassUtil implements Constants {

	/** 八种基本类型 */
	static private HashMap<Class<?>, Integer> classMap = new HashMap<Class<?>, Integer>();

	static {
		classMap.put(byteClass, 1);
		classMap.put(shortClass, 2);
		classMap.put(intClass, 3);
		classMap.put(floatClass, 4);
		classMap.put(doubleClass, 5);
		classMap.put(longClass, 6);
		classMap.put(charClass, 7);
		classMap.put(booleanClass, 8);
		classMap.put(ByteClass, 9);
		classMap.put(ShortClass, 9);
		classMap.put(IntClass, 9);
		classMap.put(FloatClass, 9);
		classMap.put(DoubleClass, 9);
		classMap.put(LongClass, 9);
		classMap.put(CharClass, 9);
		classMap.put(BooleanClass, 9);
	}

	/**
	 * 获得类名称(别名).
	 * 
	 * @param obj
	 * @return 类名称(别名)
	 */
	public static StringBuffer getClassName(Object obj) {
		XBlinkAlias classNameAlias = (XBlinkAlias) obj.getClass().getAnnotation(XBlinkAlias.class);
		return getClassName(classNameAlias, obj.getClass());
	}

	/**
	 * 获得类名称(别名).
	 * 
	 * @param clz
	 * @return 类名称(别名)
	 */
	public static StringBuffer getClassName(Class<?> clz) {
		XBlinkAlias classNameAlias = (XBlinkAlias) clz.getAnnotation(XBlinkAlias.class);
		return getClassName(classNameAlias, clz);
	}

	/**
	 * 获得类名称(别名).
	 * 
	 * @param classNameAlias
	 * @param clz
	 * @return 类名称(别名)
	 */
	private static StringBuffer getClassName(XBlinkAlias classNameAlias, Class<?> clz) {
		StringBuffer className = new StringBuffer();
		if (null != classNameAlias) {
			className.append(classNameAlias.value());
		} else {
			className.append(clz.getSimpleName().toLowerCase());
		}
		return className;
	}

	/**
	 * 获得字段名称(别名).
	 * 
	 * @param field
	 * @return 字段名称(别名)
	 */
	public static StringBuffer getFieldName(Field field) {
		XBlinkAlias xAlias = field.getAnnotation(XBlinkAlias.class);
		StringBuffer fieldName = new StringBuffer();
		// 是否需要起别名
		if (null != xAlias) {
			fieldName.append(xAlias.value());
		} else {
			fieldName.append(field.getName());
		}
		return fieldName;
	}

	/**
	 * 向对象字段中塞入合法类型的值.
	 * 
	 * @param field
	 * @param obj
	 * @param value
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void fieldSet(Field field, Object obj, String value,
			ClassLoaderSwitcher classLoaderSwitcher) throws Exception {
		Integer num = classMap.get(field.getGenericType());
		// Class类型 其中包括Class和Interface
		if (field.getType().getSimpleName().equals("Class")) {
			if (value.startsWith(CLASS_PREFIX)) {
				try {
					field.set(obj, classLoaderSwitcher.forName(new String(value.replaceAll(
							CLASS_PREFIX, EMPTY_STRING))));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			if (value.startsWith(INTERFACE_PREFIX)) {
				try {
					field.set(obj, classLoaderSwitcher.forName(new String(value.replaceAll(
							INTERFACE_PREFIX, EMPTY_STRING))));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			return;
		}
		// 八种基本类型跟Class类型以外的其他所有类型
		if (null == num) {
			// TODO 这里以后对各种对象进行解析，然后实例化
			Constructor<?> constructor = field.getType().getDeclaredConstructor(String.class);
			if(constructor != null){
				field.set(obj, constructor.newInstance(value));
				return;
			}
			throw new Exception("没有找到 " + field.getType().getName() + " 的合适的构造函数，无法进行賦值.");
		}
		//  八种基本类型
		switch (num) {
		case 1:// byteClass
			field.setByte(obj, Byte.parseByte(value));
			break;
		case 2:// shortClass
			field.setShort(obj, Short.parseShort(value));
			break;
		case 3:// intClass
			field.setInt(obj, Integer.decode(value).intValue());
			break;
		case 4:// floatClass
			field.setFloat(obj, Float.parseFloat(value));
			break;
		case 5:// doubleClass
			field.setDouble(obj, Double.parseDouble(value));
			break;
		case 6:// longClass
			field.setLong(obj, Long.parseLong(value));
			break;
		case 7:// charClass
			field.setChar(obj, value.charAt(0));
			break;
		case 8:// booleanClass
			field.setBoolean(obj, Boolean.parseBoolean(value));
			break;
		case 9:// 八种基本类型的包装类
			if (field.getType().equals(CharClass)) {
				field.set(
						obj,
						field.getType().getDeclaredConstructor(char.class)
								.newInstance(value.charAt(0)));
			} else {
				field.set(obj,
						field.getType().getDeclaredConstructor(String.class).newInstance(value));
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 根据Class获得对象实例.
	 * 
	 * @param clz
	 * @return 对象
	 * @throws Exception
	 */
	public static Object getInstance(Class<?> clz, ImplClasses xmlImplClasses) throws Exception {
		if (!clz.isInterface()) {
			return getSimpleInstance(clz);
		}
		Class<?> clzImpl = xmlImplClasses.getImplClassesMap().get(clz);
		if (null == clzImpl) {
			for (int i = 0; i < xmlImplClasses.getImplClassesLength(); i++) {
				Class<?>[] implClasses = xmlImplClasses.getImplClasses();
				if (clz.isAssignableFrom(implClasses[i])) {
					xmlImplClasses.getImplClassesMap().put(clz, implClasses[i]);
					clzImpl = implClasses[i];
					break;
				}
			}
		}
		if (null == clzImpl) {
			throw new Exception("没有找到 " + clz.getName() + " 的实现类，无法进行实例化.");
		}
		return getSimpleInstance(clzImpl);
	}

	/**
	 * 根据Class获得对象实例.
	 * 
	 * @param clz
	 * @return 对象
	 * @throws Exception
	 */
	public static Object getSimpleInstance(Class<?> clz) throws Exception {
		try {
			if (clz.equals(IntClass)) {
				return new Integer(0);
			} else if (clz.equals(FloatClass)) {
				return new Float(0);
			} else if (clz.equals(DoubleClass)) {
				return new Double(0);
			} else if (clz.equals(ByteClass)) {
				return new Byte((byte) 0);
			} else if (clz.equals(BooleanClass)) {
				return new Boolean(true);
			} else if (clz.equals(ShortClass)) {
				return new Short((short) 0);
			} else if (clz.equals(LongClass)) {
				return new Long(0);
			} else if (clz.equals(CharClass)) {
				return new Character((char) 0);
			} else if (clz.equals(Class.class)) {
				return clz;
			}
			return clz.newInstance();
		} catch (Exception e) {
			throw new Exception(clz.getName() + " 没有不带参数的构造函数，无法进行实例化.");
		}
	}
}
