package org.xblink.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentHashMap;

import org.xblink.Constants;
import org.xblink.EightBasicTypes;
import org.xblink.annotations.XBlinkAlias;
import org.xblink.transfer.ClassLoaderSwitcher;
import org.xblink.transfer.ImplClasses;

/**
 * 类相关操作工具类.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class ClassUtil {

	/** 记录所有解析过的ClassName,FiledName **/
	static private ConcurrentHashMap<String, StringBuffer> names = new ConcurrentHashMap<String, StringBuffer>();

	/** 八种基本类型 */
	static private ConcurrentHashMap<Class<?>, Integer> classMap = new ConcurrentHashMap<Class<?>, Integer>();

	static {
		classMap.put(Constants.byteClass, 1);
		classMap.put(Constants.shortClass, 2);
		classMap.put(Constants.intClass, 3);
		classMap.put(Constants.floatClass, 4);
		classMap.put(Constants.doubleClass, 5);
		classMap.put(Constants.longClass, 6);
		classMap.put(Constants.charClass, 7);
		classMap.put(Constants.booleanClass, 8);
		classMap.put(Constants.ByteClass, 9);
		classMap.put(Constants.ShortClass, 9);
		classMap.put(Constants.IntClass, 9);
		classMap.put(Constants.FloatClass, 9);
		classMap.put(Constants.DoubleClass, 9);
		classMap.put(Constants.LongClass, 9);
		classMap.put(Constants.CharClass, 9);
		classMap.put(Constants.BooleanClass, 9);
	}

	/**
	 * 获得类名称(别名).
	 * 
	 * @param clz
	 * @return 类名称(别名)
	 */
	public static StringBuffer getClassName(Class<?> clz) {
		// 判断是否解析过
		StringBuffer cn = names.get(clz.getName());
		if (null != cn) {
			return cn;
		}
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
		// 记录ClassName
		names.put(clz.getName(), className);
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
			if (value.startsWith(Constants.CLASS_PREFIX)) {
				try {
					field.set(obj, classLoaderSwitcher.forName(new String(value.replaceAll(
							Constants.CLASS_PREFIX, Constants.EMPTY_STRING))));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			if (value.startsWith(Constants.INTERFACE_PREFIX)) {
				try {
					field.set(obj, classLoaderSwitcher.forName(new String(value.replaceAll(
							Constants.INTERFACE_PREFIX, Constants.EMPTY_STRING))));
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
			if (constructor != null) {
				field.set(obj, constructor.newInstance(value));
				return;
			}
			throw new Exception("没有找到 " + field.getType().getName() + " 的合适的构造函数，无法进行賦值.");
		}
		// 八种基本类型
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
			if (field.getType().equals(Constants.CharClass)) {
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
			for (int i = 0; i < xmlImplClasses.getImplClassesNumber(); i++) {
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
			if (Constants.IntClass == clz || Constants.intClass == clz) {
				return new Integer(0);
			} else if (Constants.FloatClass == clz || Constants.floatClass == clz) {
				return new Float(0);
			} else if (Constants.DoubleClass == clz || Constants.doubleClass == clz) {
				return new Double(0);
			} else if (Constants.ByteClass == clz || Constants.byteClass == clz) {
				return new Byte((byte) 0);
			} else if (Constants.BooleanClass == clz || Constants.booleanClass == clz) {
				return new Boolean(true);
			} else if (Constants.ShortClass == clz || Constants.shortClass == clz) {
				return new Short((short) 0);
			} else if (Constants.LongClass == clz || Constants.longClass == clz) {
				return new Long(0);
			} else if (Constants.CharClass == clz || Constants.charClass == clz) {
				return new Character((char) 0);
			} else if (Class.class == clz) {
				return clz;
			}
			return clz.newInstance();
		} catch (Exception e) {
			throw new Exception(clz.getName() + " 没有不带参数的构造函数，无法进行实例化.");
		}
	}

	/**
	 * 获得类类型.
	 * 
	 * @param field
	 * @param implClasses
	 * 
	 * @return 类类型
	 */
	public static ClassType getClassTypes(Field field, ImplClasses implClasses) {
		Type type1 = null;
		Type type2 = null;
		Class<?> fieldInnerClass1 = null;
		Class<?> fieldInnerClass2 = null;
		Class<?> fieldClass = field.getType();
		Type gtype = field.getGenericType();
		if (gtype instanceof ParameterizedType) {
			type1 = ((ParameterizedType) gtype).getActualTypeArguments()[0];
			type2 = ((ParameterizedType) gtype).getActualTypeArguments()[1];
		}
		TypeAndFieldInnerClass tf1 = new TypeAndFieldInnerClass();
		tf1.setType(type1);
		tf1.setFieldInnerClass(fieldInnerClass1);
		TypeAndFieldInnerClass tf2 = new TypeAndFieldInnerClass();
		tf2.setType(type2);
		tf2.setFieldInnerClass(fieldInnerClass2);
		getTypeAndInnerClass(tf1, implClasses);
		getTypeAndInnerClass(tf2, implClasses);
		ClassType classType = new ClassType();
		classType.setFieldClass(fieldClass);
		classType.setFieldInnerClassType1(tf1.getType());
		classType.setFieldInnerClass1(tf1.getFieldInnerClass());
		classType.setFieldInnerClassType2(tf2.getType());
		classType.setFieldInnerClass2(tf2.getFieldInnerClass());
		return classType;
	}

	/**
	 * 获得类类型.
	 * 
	 * @param field
	 * @param implClasses
	 * 
	 * @return 类类型
	 */
	public static ClassType getClassType(Field field, ImplClasses implClasses) {
		Type type = null;
		Class<?> fieldInnerClass = null;
		Class<?> fieldClass = field.getType();
		Type gtype = field.getGenericType();
		if (gtype instanceof ParameterizedType) {
			type = ((ParameterizedType) gtype).getActualTypeArguments()[0];
		}
		TypeAndFieldInnerClass tf = new TypeAndFieldInnerClass();
		tf.setType(type);
		tf.setFieldInnerClass(fieldInnerClass);
		getTypeAndInnerClass(tf, implClasses);
		ClassType classType = new ClassType();
		classType.setFieldClass(fieldClass);
		classType.setFieldInnerClassType1(tf.getType());
		classType.setFieldInnerClass1(tf.getFieldInnerClass());
		return classType;
	}

	private static void getTypeAndInnerClass(TypeAndFieldInnerClass tf, ImplClasses implClasses) {
		Type type = tf.getType();
		Class<?> fieldInnerClass = null;
		if (type != null) {
			if (Constants.GENERICS.equals(type.toString())) {
				// TODO 为了最外层的实现类 root层使用
				fieldInnerClass = implClasses.getRootInstanceClass();
			} else {
				if (type instanceof ParameterizedType) {
					Type innerType = null;
					innerType = ((ParameterizedType) type).getActualTypeArguments()[0];
					if (innerType != null && Constants.GENERICS.equals(innerType.toString())) {
						if (type.toString().startsWith(
								Class.class.toString().replaceAll(Constants.CLASS_PREFIX,
										Constants.EMPTY_STRING))) {
							fieldInnerClass = Class.class;
						} else {
							// TODO 为了最外层的实现类 root层使用
							fieldInnerClass = implClasses.getRootInstanceClass();
						}
					}
				} else {
					fieldInnerClass = (Class<?>) type;
				}
			}
		}
		tf.setFieldInnerClass(fieldInnerClass);
	}

	/**
	 * 
	 * 内部类.
	 *
	 */
	public static class TypeAndFieldInnerClass {

		private Type type;

		private Class<?> fieldInnerClass;

		public Type getType() {
			return type;
		}

		public void setType(Type type) {
			this.type = type;
		}

		public Class<?> getFieldInnerClass() {
			return fieldInnerClass;
		}

		public void setFieldInnerClass(Class<?> fieldInnerClass) {
			this.fieldInnerClass = fieldInnerClass;
		}

	}

	/**
	 * 获得数组中的类型.
	 * 
	 * @param object
	 * @return 类型
	 */
	public static Class<?> getArrayElementType(Object object) {
		Class<?> type = object.getClass();
		if (type.isArray()) {
			Class<?> elementType = type.getComponentType();
			return elementType;
		}
		return null;
	}

	/**
	 * 获得类型
	 * 
	 * @param clz
	 * @return 8中基本类型
	 */
	public static EightBasicTypes getEightType(Class<?> clz) {
		if (Constants.intClass == clz) {
			return EightBasicTypes.Int;
		} else if (Constants.doubleClass == clz) {
			return EightBasicTypes.Double;
		} else if (Constants.floatClass == clz) {
			return EightBasicTypes.Float;
		} else if (Constants.booleanClass == clz) {
			return EightBasicTypes.Boolean;
		} else if (Constants.byteClass == clz) {
			return EightBasicTypes.Byte;
		} else if (Constants.charClass == clz) {
			return EightBasicTypes.Char;
		} else if (Constants.shortClass == clz) {
			return EightBasicTypes.Short;
		} else if (Constants.longClass == clz) {
			return EightBasicTypes.Long;
		} else {
			return EightBasicTypes.Null;
		}
	}
}
