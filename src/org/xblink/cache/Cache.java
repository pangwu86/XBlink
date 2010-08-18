package org.xblink.cache;

import java.util.List;

public interface Cache<T> {
	void put(Object key,Object object);
	T get(T t,Object key);
	List toList();
}
