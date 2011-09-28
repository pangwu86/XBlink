package org.xblink.core.path;

import java.util.LinkedList;

/**
 * 记录文件层次路径，返回当前访问节点的路径。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class PathTracker {

	private static String PATH_SEPARATER = "/";

	private int pointer;
	private LinkedList<String> pathStack;

	public PathTracker() {
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

	/**
	 * 返回当前节点的绝对路径。
	 * 
	 * @return 相对于根节点的路径
	 */
	public String getPath() {
		StringBuilder currentPath = new StringBuilder();
		for (int i = pointer - 1; i > -1; i--) {
			currentPath.append(PATH_SEPARATER).append(pathStack.get(i));
		}
		return currentPath.toString();
	}

}
