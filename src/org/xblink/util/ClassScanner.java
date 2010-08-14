package org.xblink.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 包扫描.
 * 
 * 
 * @author E-Hunter(xjf1986518@gmail.com)
 * 
 */
public class ClassScanner {
	private ArrayList<Class<?>> fileList = new ArrayList<Class<?>>();
	private String packageName = null;

	public List<Class<?>> scan(String... packageNames) {
		WatchTimer watchTimer = new WatchTimer();
		String basePath = Package.class.getResource("/").getPath().substring(1);// 去除开头的‘\’
		StringBuffer path = new StringBuffer(basePath);
		System.out.println("获得参考路径：" + watchTimer.getTimer());
		watchTimer = new WatchTimer();
		for (String _packageName : packageNames) {
			path.delete(basePath.length(), path.length());
			this.packageName = _packageName + ".";
			// System.out.println(this.packageName+"--"+_packageName);
			path.append(_packageName.replace(".", "/"));// 获得包的绝对路径
			addToClassList(path.toString(), this.fileList);

		}
		System.out.println("扫描并生成CalssList：" + watchTimer.getTimer());

		return this.fileList;
	}

	private void addToClassList(String path, List<Class<?>> filelist) {
		File dir = new File(path);
		File[] files = dir.listFiles();

		if (files == null)
			return;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				addToClassList(files[i].getAbsolutePath(), fileList);
			} else {
				// 把路径转换成类名
				if (files[i].getName().endsWith(".class")) {
					String strFileName = files[i].getAbsolutePath()
							.replace(path.replace('/', '\\'), "").replace(".class", "");
					strFileName = packageName + strFileName.substring(1);
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

	public static void main(String[] args) {
		ClassScanner classScanner = new ClassScanner();
		WatchTimer watchTimer = new WatchTimer();
		classScanner.scan("org.xblink.types");
		System.out.println("总耗时：" + watchTimer.getTimer());

	}
}
