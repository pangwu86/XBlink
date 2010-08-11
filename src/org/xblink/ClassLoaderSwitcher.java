package org.xblink;

/**
 * 类加载器切换器.<br>
 * 为了OSGI环境设置.
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class ClassLoaderSwitcher {

	/** 本地类加载器 */
	private static ClassLoader localClassLoader;

	/** 用户类加载器 */
	private ClassLoader userClassLoader;

	/** 当前类加载器 */
	private ClassLoader currentClassLoader;

	static {
		localClassLoader = Thread.currentThread().getContextClassLoader();
	}

	public ClassLoaderSwitcher() {
		currentClassLoader = localClassLoader;
	}

	/**
	 * 设置用户类加载器.
	 * 
	 * @param userClassLoader
	 *            用户类加载器
	 */
	public void setUserClassLoader(ClassLoader userClassLoader) {
		this.userClassLoader = userClassLoader;
	}

	/**
	 * 使用特定的类加载器进行Class.forName()的封装.
	 * 
	 * @param className
	 *            类名
	 * @return 类
	 * @throws ClassNotFoundException
	 */
	public Class<?> forName(String className) throws ClassNotFoundException {
		if (userClassLoader != null) {
			currentClassLoader = userClassLoader;
		}
		Class<?> clz = currentClassLoader.loadClass(className);
		currentClassLoader = localClassLoader;
		return clz;
	}
}