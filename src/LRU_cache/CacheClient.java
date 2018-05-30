package LRU_cache;

public class CacheClient {

	public static void main(String[] args){

		TextFileStrategy dataFile = new TextFileStrategy("./src/LRU_cache/FileTest_01.txt");
		LRUcache cache = new LRUcache(5, dataFile);

		int myKey = 1;
		System.out.println("searching for key: " + myKey);
		Object myValue = dataFile.getData(myKey);
		System.out.println(myValue);

		System.out.println(cache.get(myKey));
		cache.get(myKey);
//		cache.set(myKey, myValue);

		System.out.println("printing from cache...");
		System.out.println("get hit ratio: " + cache.getHitRatio());
		System.out.println(cache.get(myKey));
		System.out.println("get hit ratio: " + cache.getHitRatio());

		cache.printMap();
		cache.printList();
		System.out.println(cache.get(2));
		cache.printMap();
		cache.printList();
		System.out.println(cache.get(3));
		cache.printMap();
		cache.printList();
		System.out.println(cache.get(4));
		cache.printMap();
		cache.printList();
		System.out.println(cache.get(5));
		cache.printMap();
		cache.printList();
		System.out.println(cache.get(3));
		cache.printMap();
		cache.printList();

	}

}
