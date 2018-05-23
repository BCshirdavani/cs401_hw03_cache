package LRU_cache;

public class CacheClient {

	public void main(String[] args){
		LRUcache cache = new LRUcache(10);
		TextFileStrategy dataFile = new TextFileStrategy("./src/LRU_cache/FileTest_01.txt");

		int myKey = 4;
		System.out.println("searching for key: " + myKey);
		Object myValue = dataFile.getData(myKey);
		System.out.println(myValue);
		cache.set(myKey, myValue);

		System.out.println("printing from cache...");
		System.out.println(cache.get(myKey));

	}

}
