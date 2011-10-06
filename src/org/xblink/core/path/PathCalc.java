package org.xblink.core.path;

import org.xblink.core.Constant;
import org.xblink.util.StringUtil;

/**
 * 计算出两个节点间相对路径。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class PathCalc {

	private PathCalc() {
	}

	/**
	 * 根据当前节点路径与目标节点绝对路径，计算目标节点相对路径。
	 * 
	 * @param currentPath
	 * @param targetPath
	 * @return
	 */
	protected static String calcRelativePath(String[] currentPath, String[] targetPath) {
		int cDepth = currentPath.length;
		int tDepth = targetPath.length;
		String[] paths = new String[cDepth + tDepth];
		if (cDepth > tDepth) {
			// 计算差额，全部是向上层走的
			int dd1 = cDepth - tDepth;
			int dd2 = 0;
			int dif = 0;
			// 从头开始比较
			for (int i = 0; i < tDepth; i++) {
				if (currentPath[i] != targetPath[i]) {
					// 分支路口
					dd2 = tDepth - i;
					dif = i;
					break;
				}
			}
			// 编辑路程
			int dd3 = dd1 + dd2;
			for (int i = 0; i < dd3; i++) {
				paths[i] = Constant.PATH_PARENT;
			}
			if (0 != dif) {
				for (int i = dif; i < tDepth; i++) {
					paths[dd3++] = targetPath[i];
				}
			}
		} else if (cDepth <= tDepth) {
			int dif = 0;
			// 从头开始比较
			for (int i = 0; i < cDepth; i++) {
				if (currentPath[i] != targetPath[i]) {
					// 分支路口
					dif = i;
					break;
				}
			}
			if (0 == dif) {
				// 这个比较特别 两者路径相同，即同一个对象下存放了两个相同的东东
				// 除非两个名字还一样，否则不会出现这样的问题
				// FIXME
				return "../" + targetPath[tDepth - 1];
			}
			// 编辑路程
			int dd = cDepth - dif;
			for (int i = 0; i < dd; i++) {
				paths[i] = Constant.PATH_PARENT;
			}
			for (int i = dif; i < tDepth; i++) {
				paths[dd++] = targetPath[i];
			}
		}
		return getPathFromRoot(paths).substring(1);
	}

	/**
	 * 根据当前节点路径与目标节点的相对路径，计算目标节点绝对路径。
	 * 
	 * @param currentPath
	 * @param relativePath
	 * @return
	 */
	protected static String calcAbsolutePath(String[] currentPath, String[] relativePath) {
		// TODO
		return null;
	}

	/**
	 * 将字符数组路径变成字符串路径，返回从根目录开始的路径
	 * 
	 * @param path
	 *            字符数组路径
	 * @return 字符串路经
	 */
	protected static String getPathFromRoot(String[] path) {
		StringBuilder sb = new StringBuilder();
		for (String s : path) {
			if (StringUtil.isBlankStr(s)) {
				continue;
			}
			sb.append(Constant.PATH_SEPARATER).append(s);
		}
		return sb.toString();
	}

	/**
	 * 分割字符串。
	 * 
	 * @param str
	 * @return
	 */
	protected static String[] getSts(String str) {
		return str.split(Constant.PATH_SEPARATER);
	}

}
