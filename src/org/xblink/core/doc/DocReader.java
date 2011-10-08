package org.xblink.core.doc;

import java.io.Reader;
import java.util.Iterator;

/**
 * 定义了读取文本文件的几个动作。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public interface DocReader {

	/**
	 * 是否还存在子节点。
	 * 
	 * @return
	 */
	public boolean hasMoreChildren();

	/**
	 * 进去下一个节点。
	 */
	public void moveDown();

	/**
	 * 退到上一个节点。
	 */
	public void moveUp();

	/**
	 * 获得当前节点的名称。
	 * 
	 * @return
	 */
	public String getNodeName();

	/**
	 * 获得当前节点文字值。
	 * 
	 * @return
	 */
	public String getTextValue();

	/**
	 * 获得当期节点的属性数量。
	 * 
	 * @return
	 */
	public int getAttributeCount();

	/**
	 * 获得当前节点所有属性名称的迭代器。
	 * 
	 * @return
	 */
	public Iterator<String> getAttributeNames();

	/**
	 * 根据指定名称获得当前节点的属性值。
	 * 
	 * @param name
	 * @return
	 */
	public String getAttribute(String name);

	/**
	 * 根据指定名称获得当前节点的属性值(通过索引值)。
	 * 
	 * @param index
	 * @return
	 */
	public String getAttribute(int index);

	/**
	 * 关闭输入流。
	 */
	public void close();

	/**
	 * 获得内容的Reader。
	 * 
	 * @return
	 */
	public Reader getReader();

}
