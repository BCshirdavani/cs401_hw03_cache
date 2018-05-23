package LRU_cache;

public class CacheClient {

	public static void main(String[] args){

		TextFileStrategy dataFile = new TextFileStrategy("./src/LRU_cache/FileTest_01.txt");
		LRUcache cache = new LRUcache(10, dataFile);

		int myKey = 4;
		System.out.println("searching for key: " + myKey);
		Object myValue = dataFile.getData(myKey);
		System.out.println(myValue);

		System.out.println(cache.get(myKey));
		System.out.println("get miss ratio: " + cache.getMissRatio());
		cache.get(myKey);
		System.out.println("get miss ratio: " + cache.getMissRatio());
		cache.set(myKey, myValue);

		System.out.println("printing from cache...");
		System.out.println("get hit ratio: " + cache.getHitRatio());
		System.out.println(cache.get(myKey));
		System.out.println("get hit ratio: " + cache.getHitRatio());

	}

}
