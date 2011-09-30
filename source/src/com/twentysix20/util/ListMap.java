package com.twentysix20.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListMap<K,V> implements Map<K, V> {
	private static final long serialVersionUID = 1L;
	private Map<K,List<V>> map = new HashMap<K,List<V>>();

    @Override
    public V put(K key, V value) {
    	List<V> list = map.get(key);
    	if (list == null) {
    		list = new ArrayList<V>();
    		map.put(key,list);
    	}
    	list.add(value);
    	return null;
    }

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public V get(Object key) {
		throw new UnsupportedOperationException();
	}

	public List<V> getList(Object key) {
		return map.get(key);
	}

	public V getElement(Object key, int index) {
		return map.get(key).get(index);
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		return map.keySet();
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public V remove(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public Collection<V> values() {
		throw new UnsupportedOperationException();
	}
}