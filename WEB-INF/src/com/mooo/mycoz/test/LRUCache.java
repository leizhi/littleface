package com.mooo.mycoz.test;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Collections;

/**
 * This class uses the LinkedHashMap to build LRU cache. User can define the
 * cache size.
 */
public class LRUCache {
	int cacheSize = 0;
	float loadFactor = 0.75f; // default
	Map<Object, Object> cache;

	public LRUCache(int cacheSize) {
		this.cacheSize = cacheSize;
		cache = new LinkedHashMap<Object, Object>(cacheSize, loadFactor, true) {
			protected boolean removeEldestEntry(Map.Entry eldest) {
				return size() > LRUCache.this.cacheSize;
				// return false;
			}
		};
		cache = (Map<Object, Object>) Collections.synchronizedMap(cache);
	}

	public synchronized void clear() {
		cache.clear();
	}

	public synchronized Object get(Object key) {
		return cache.get(key);
	}

	public synchronized void put(Object key, Object value) {
		cache.put(key, value);
	}

	public synchronized Object remove(Object key) {
		return cache.remove(key);
	}

	public synchronized int size() {
		return cache.size();
	}

	public synchronized Collection<Object> values() {
		return cache.values();
	}

	public static void main(String[] args) {
		// testing
		int size = 3;
		LRUCache cache = new LRUCache(size);
		cache.put(new Integer("1"), "1");
		cache.put(new Integer("2"), "2");
		cache.put(new Integer("3"), "3");

		String value = (String) cache.get(new Integer(1));
		System.out.println(value);
		System.out.println("Testing ...");

		Object[] values = cache.values().toArray();
		for (int i = 0; i < values.length; i++) {
			value = (String) values[i];
			System.out.println(value);
		}
	}
}
