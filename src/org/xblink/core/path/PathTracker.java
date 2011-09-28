package org.xblink.core.path;

import java.util.LinkedList;

import org.xblink.core.Constant;

/**
 * 记录文件层次路径，返回当前访问节点的路径。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class PathTracker {

	private boolean useRelativePath;
	private int pointer;
	private LinkedList<String> pathStack;

	public PathTracker(boolean useRelativePath) {
		this.useRelativePath = useRelativePath;
		this.pointer = 0;
		this.pathStack = new LinkedList<String>();
	}

	public void push(String nodeName) {
		pathStack.addFirst(nodeName);
		pointer++;
	}

	public void pop() {
		pathStack.removeFirst();
		pointer--;
	}

	public void reset() {
		pathStack.clear();
		pointer = 0;
	}

	// ********************* 获得路径的各种方法 ************************

	/**
	 * 以字符数组返回当前节点的绝对路径。
	 * 
	 * @return 相对于根节点的路径
	 */
	public String[] getCurrentPath() {
		String[] currentPath = new String[pointer];
		for (int i = 0, j = pointer - 1; i < pointer; i++, j--) {
			// TODO 这里的get是否会很慢?
			currentPath[i] = pathStack.get(j);
		}
		return currentPath;
	}

	/**
	 * 以字符串形式返回当前节点的绝对路径。
	 * 
	 * @return 相对于根节点的路径
	 */
	public String getCurrentPathAsString() {
		return getPath(getCurrentPath());
	}

	/**
	 * 根据传入的引用对象路径与当前路径进行计算，得出相对路径。
	 * 
	 * @param refPath
	 *            引用对象路径
	 * @return 相对路径字符串
	 */
	public String getRelativePathAsString(String[] refPath) {
		String[] currentPath = getCurrentPath();
		int currentDepth = currentPath.length;
		int refDepth = refPath.length;
		// TODO 需要一个快捷的算法，计算出相对路径
		return null;
	}

	/**
	 * 以字符串形式获得引用路径。
	 * 
	 * @param path
	 *            引用对象路径
	 * @return 字符串路径
	 */
	public String getReferencePathAsString(String[] refPath) {
		if (useRelativePath) {
			return getRelativePathAsString(refPath);
		}
		return getPath(refPath);
	}

	/**
	 * 将字符数组路径变成字符串路径。
	 * 
	 * @param path
	 *            字符数组路径
	 * @return 字符串路经
	 */
	private String getPath(String[] path) {
		StringBuilder sb = new StringBuilder();
		for (String s : path) {
			sb.append(Constant.PATH_SEPARATER).append(s);
		}
		return sb.toString();
	}
}
