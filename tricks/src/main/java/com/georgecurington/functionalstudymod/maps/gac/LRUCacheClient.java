package com.georgecurington.functionalstudymod.maps.gac;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * This example was gotton off the net:
 * http://www.makeinjava.com/least-recently-used-lru-cache-example-linkedhashmap/
 * 
 * Good illustration of using a LinkedHashMap as an LRU cache
 * </pre>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 31, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 * @param <K>
 * @param <V>
 */
class LRUCache<K, V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = 1L;
	private int lruSize;

	public LRUCache(int lruSize) {
		super(16, 0.75f, true);
		this.lruSize = lruSize;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size() > lruSize;
	}	
}

public class LRUCacheClient {
	public static void main(String[] args) {
		int cacheSize = 5;
		Map<Integer, String> mapVehicleNoAndOwner = new LRUCache<>(cacheSize);

		mapVehicleNoAndOwner.put(1000, "Federer");
		mapVehicleNoAndOwner.put(2000, "Bradman");
		mapVehicleNoAndOwner.put(3000, "Jordan");
		mapVehicleNoAndOwner.put(4000, "Woods");
		mapVehicleNoAndOwner.put(5000, "Ali");

		System.out.println("1. Iterating initial cache of size = "+cacheSize);
		demoIterateCache(mapVehicleNoAndOwner);
		
		int key = 1000;
		System.out.printf("2. Accessting value at key: %d is %s\n",key,mapVehicleNoAndOwner.get(key));
		
		key = 3000;
		System.out.printf("3. Accessting value at key: %d is %s\n",key,mapVehicleNoAndOwner.get(key));
		
		System.out.println("4. Iterating cache after accessing its keys: ");
		demoIterateCache(mapVehicleNoAndOwner);
		
		key = 6000;
		String value = "Don";
		System.out.printf("5. Adding new entry to cache, key=%d, value=%s\n",key,value);
		mapVehicleNoAndOwner.put(6000, "Don");
		key = 7000;
		value = "Campbell";
		System.out.printf("6. Adding new entry to cache, key=%d, value=%s\n",key,value);
		mapVehicleNoAndOwner.put(7000, "Campbell");

		System.out.println("7. Iterating cache after adding entries beyond its size: ");
		demoIterateCache(mapVehicleNoAndOwner);
	}

	private static void demoIterateCache(Map<Integer, String> mapVehicleNoAndOwner) {

		mapVehicleNoAndOwner.forEach((key, value) -> {
			System.out.println("Key:" + key + ", Value:" + value);
		});
	}
}
