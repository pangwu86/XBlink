package org.xblink.core.cache;

import java.util.HashMap;
import java.util.Map;

import org.xblink.core.AnalysisObject;

/**
 * 分析结果缓存。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class AnalysisCache {

	private AnalysisCache() {
	}

	private static boolean useAnalysisCache = true;

	// TODO 没有同步，或许有线程问题
	private static Map<Class<?>, AnalysisObject> anaylsisMap = new HashMap<Class<?>, AnalysisObject>();

	public static void setUseAnalysisCache(boolean use) {
		useAnalysisCache = use;
	}

	/**
	 * 获得分析后的结果对象。
	 * 
	 * @param clz
	 * @return
	 */
	public static AnalysisObject getAnalysisObject(Class<?> clz) {
		AnalysisObject analysisObject = null;
		if (useAnalysisCache) {
			analysisObject = anaylsisMap.get(clz);
			if (null == analysisObject) {
				analysisObject = new AnalysisObject(clz);
				anaylsisMap.put(clz, analysisObject);
			}
		} else {
			analysisObject = new AnalysisObject(clz);
		}
		return analysisObject;
	}
}
