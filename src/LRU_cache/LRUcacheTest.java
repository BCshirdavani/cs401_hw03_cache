package LRU_cache;

import junit.framework.TestCase;

public class LRUcacheTest extends TestCase {
	TextFileStrategy dataFile = new TextFileStrategy("./src/LRU_cache/FileTest_01.txt");


	// tests if an element is NOT contained
	public void testNotContains(){
		LRUcache cache = new LRUcache(5, dataFile);
		boolean Found;
		Found = cache.contains(7);
		assertEquals(false, Found);
	}

	// tests if an element IS contained
	public void testContains(){
		LRUcache cache = new LRUcache(5, dataFile);
		boolean Found;
		cache.put(4,"Germany");
		Found = cache.contains(4);
		assertEquals(true, Found);
	}

	// tests if map size is 0 after clearing the cache
	public void testClear(){
		LRUcache cache = new LRUcache(5, dataFile);
		cache.get(1);
		cache.get(2);
		cache.get(3);
		cache.get(4);
		cache.clear();
		int mapSize = cache.map.size();
		assertEquals(0,mapSize);
	}

	// test hit ratio
	public void testHit(){
		LRUcache cache = new LRUcache(5, dataFile);
		cache.put(1,"Spain");
		cache.put(2,"Russia");
		cache.get(2);				// hit = 1/1
		cache.get(3);				// hit = 1/2
		Float hitRatio = cache.getHitRatio();
		assertEquals(((1.0)/(2.0)),hitRatio, 0.001);
	}

	// test miss ratio
	public void testMiss(){
		LRUcache cache = new LRUcache(5, dataFile);
		cache.put(1,"Spain");
		cache.put(2,"Russia");
		cache.get(2);				// miss = 0/1
		cache.get(3);				// miss = 1/2
		cache.get(4);				// miss = 2/3
		Float missRatio = cache.getMissRatio();
		assertEquals(((2.0)/(3.0)), missRatio, 0.001);
	}

	//	test if recent item is at FRONT of cache linked list for most recently used
	public void testMostRecent(){
		LRUcache cache = new LRUcache(5, dataFile);
		cache.put(1,"Spain");
		cache.put(2,"Russia");
		cache.put(3,"USA");
		cache.get(2);				// Russia is now the most recent thing
		assertEquals("Russia", cache.head.value.toString());
	}

	// test least recent shoudl be at END of cache linked list
	public void testLeastRecent(){
		LRUcache cache = new LRUcache(5, dataFile);
		cache.put(1,"Spain");
		cache.put(2,"Russia");
		cache.put(3,"USA");
		cache.get(2);				// Russia is now the most recent thing
		assertEquals("Spain", cache.end.value.toString());
	}


}