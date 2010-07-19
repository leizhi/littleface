package com.manihot.xpc.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Map;

/**
 * \u7c7b\u8bf4\u660e\uff1a\u5229\u7528LinkedHashMap\u5b9e\u73b0\u7b80\u5355\u7684\u7f13\u5b58\uff0c \u5fc5\u987b\u5b9e\u73b0removeEldestEntry\u65b9\u6cd5\uff0c\u5177\u4f53\u53c2\u89c1JDK\u6587\u6863
 * 
 * @author dennis
 * 
 * @param <K>
 * @param <V>
 */
public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
	private final int maxCapacity;

	private static final float DEFAULT_LOAD_FACTOR = 0.75f;

	private final Lock lock = new ReentrantLock();

	public LRULinkedHashMap(int maxCapacity) {
		super(maxCapacity, DEFAULT_LOAD_FACTOR, true);
		this.maxCapacity = maxCapacity;
	}

	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		return size() > maxCapacity;
	}

	@Override
	public boolean containsKey(Object key) {
		try {
			lock.lock();
			return super.containsKey(key);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public V get(Object key) {
		try {
			lock.lock();
			return super.get(key);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public V put(K key, V value) {
		try {
			lock.lock();
			return super.put(key, value);
		} finally {
			lock.unlock();
		}
	}

	public int size() {
		try {
			lock.lock();
			return super.size();
		} finally {
			lock.unlock();
		}
	}

	public void clear() {
		try {
			lock.lock();
			super.clear();
		} finally {
			lock.unlock();
		}
	}

	public Collection<Map.Entry<K, V>> getAll() {
		try {
			lock.lock();
			return new ArrayList<Map.Entry<K, V>>(super.entrySet());
		} finally {
			lock.unlock();
		}
	}
}
