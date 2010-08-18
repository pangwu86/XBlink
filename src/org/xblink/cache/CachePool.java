package org.xblink.cache;

import java.util.HashMap;
import java.util.Map;

import org.xblink.cache.impl.PackageScannerCache;

public class CachePool {

	private static Map<String, Cache> cacheMap = new HashMap<String, Cache>();
	
	public static Cache getCache(String cacheName){
		return cacheMap.get(cacheName);
	}
	public static void setCache(String cacheName, Cache cache){
		cacheMap.put(cacheName, cache);
	}
}
