package org.xblink.core.doc;

/**
 * 定义了书写文本文件的几个动作。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public interface DocWriter {

	/**
	 * 编写文档开头。
	 * 
	 * @throws Exception
	 *             异常
	 */
	public void writeStartDocument() throws Exception;

	/**
	 * 编写文档结尾。(一定要先调用writeStartDocument)
	 * 
	 * @throws Exception
	 *             异常
	 */
	public void writeEndDocument() throws Exception;

	/**
	 * 编写开始标签。
	 * 
	 * @param tagName
	 *            标签名称
	 * @throws Exception
	 *             异常
	 */
	public void writeStartTag(String tagName) throws Exception;

	/**
	 * 编写结束标签。(一定要先调用writeStartTag)
	 * 
	 * @param tagName
	 *            标签名称
	 * @throws Exception
	 *             异常
	 */
	public void writeEndTag(String tagName) throws Exception;

	/**
	 * 编写属性。(一定要先调用writeStartTag)
	 * 
	 * @param name
	 *            属性名称
	 * @param value
	 *            属性值
	 * @throws Exception
	 *             异常
	 */
	public void writeAttribute(String name, String value) throws Exception;

	/**
	 * 编写内容。
	 * 
	 * @param text
	 *            内容
	 * @throws Exception
	 *             异常
	 */
	public void writeText(String text) throws Exception;

	/**
	 * 编写元素。
	 * 
	 * @param tagName
	 *            元素名称
	 * @param text
	 *            内容
	 * @throws Exception
	 *             异常
	 */
	public void writeElementText(String tagName, String text) throws Exception;

	/**
	 * 编写引用节点。(一定要先调用writeStartTag)
	 * 
	 * @param tagName
	 *            元素名称
	 * @param refName
	 *            引用属性名称
	 * @param text
	 *            引用路径
	 * @throws Exception
	 *             异常
	 */
	public void writeReference(String tagName, String refName, String text) throws Exception;

	/**
	 * 关闭当前Writer。
	 * 
	 * @throws Exception
	 *             异常
	 */
	public void close() throws Exception;

	/**
	 * 输出当前内容。
	 * 
	 * @throws Exception
	 *             异常
	 */
	public void flush() throws Exception;

	/**
	 * 返回生成的文本。
	 * 
	 * @return 文本内容
	 */
	public String getString();

}
