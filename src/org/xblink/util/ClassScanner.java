package org.xblink.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.xblink.XBlink;
import org.xblink.types.XArray;


/**
 * 包扫描工具.
 * 
 * 
 * @author E-Hunter(xjf1986518@gmail.com)
 * 
 */
public class ClassScanner {
	private List<Class<?>> fileList = new ArrayList<Class<?>>();
	private String packageName = null;
	/**
	 * 扫描并得到指定包下的所有类（不进行递归）
	 * @param clazzs 参考类
	 * @return 
	 */
	public List<Class<?>> scan(Class<?>... clazzs) {
		StringBuffer path = new StringBuffer();
		for (Class<?> clazz : clazzs) {
			path.delete(0, path.length());
			packageName = clazz.getPackage().getName();//获得包名
			path.append(clazz.getResource("").getPath());//获得参考类所在的根目录
			path.deleteCharAt(0);
			addToClassList(path.toString(), this.fileList);
		}

		return this.fileList;
	}

	/**
	 * 加载指定包下面的所有类
	 * @param path 指定位置
	 * @param filelist 用于存储类的列表
	 */
	private void addToClassList(String path, List<Class<?>> filelist) {
		//此设计最初为支持递归，如果需要递归则不需进行太多改动
		File dir = new File(path);
		File[] files = dir.listFiles();
		if (files == null)
			return;
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().endsWith(".class")&&!files[i].isDirectory()) {
				// 把路径转换成类名
				if (files[i].getName().endsWith(".class")) {
					String strFileName = files[i].getAbsolutePath()
							.replace(path.replace('/', '\\'), "").replace(".class", "");
					strFileName = packageName +"."+ strFileName;
					Class<?> cls;
					try {		
						cls = Class.forName(strFileName);
						filelist.add(cls);
					} catch (ClassNotFoundException e) {
						System.out.println("没有找到此类：" + strFileName);
					}

				}
			}
		}
	}
}
