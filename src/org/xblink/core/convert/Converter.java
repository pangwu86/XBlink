package org.xblink.core.convert;

/**
 * 转换器，提供对象<--->文字相互转换的功能。
 * 
 * 注意，这里只负责两者的转换，不要加入输出等操作。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public interface Converter {

	/**
	 * 返回当前转换器可以处理的对象类型。
	 * 
	 * @return class对象
	 */
	public Class<?>[] getTypes();

	/**
	 * 是否可以转换这种类型。
	 * 
	 * @param type
	 *            类类型
	 * @return 能否成功
	 */
	public boolean canConvert(Class<?> type);

	/**
	 * 将对象转换为文字格式的值。
	 * 
	 * @param obj
	 *            转换对象
	 * @throws Exception
	 *             异常
	 * @return 字符结果
	 */
	public String obj2Text(Object obj) throws Exception;

	/**
	 * 将文字格式的值转换为对象。
	 * 
	 * @param text
	 *            文字格式的值
	 * @throws Exception
	 *             异常
	 * @return 对象
	 */
	public Object text2Obj(String text) throws Exception;

}
