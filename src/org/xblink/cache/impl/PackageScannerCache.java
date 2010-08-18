package org.xblink.cache.impl;

import java.util.ArrayList;
import java.util.List;

import org.xblink.cache.Cache;

public class PackageScannerCache implements Cache<Class<?>> {

	private static List<Class<?>> set = new ArrayList<Class<?>>();
	@Override
	public Class<?> get(Class<?> t, Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(Object key, Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List toList() {
		// TODO Auto-generated method stub
		return null;
	}

}
