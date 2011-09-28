package org.xblink.core.convert;

import org.xblink.core.TransferInfo;

/**
 * 转换器，提供对象<--->文字相互转换的功能。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public interface Converter {

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
	 * @param transferInfo
	 *            传递信息
	 * @throws Exception
	 *             异常
	 */
	public void obj2Text(Object obj, TransferInfo transferInfo) throws Exception;

	// public Object text2Obj(TransferInfo transferInfo);

}
