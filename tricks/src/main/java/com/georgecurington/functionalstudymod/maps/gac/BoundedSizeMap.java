package com.georgecurington.functionalstudymod.maps.gac;

import java.util.LinkedHashMap;
import java.util.Map;

public class BoundedSizeMap<K,V> extends LinkedHashMap<K,V> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1485004912593792902L;
	private int maxEntries;

	public BoundedSizeMap(int maxEntries) {
		super(16, 0.75f, true);
		this.maxEntries = maxEntries;
	}

	protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
		return size() > maxEntries;
	}
}