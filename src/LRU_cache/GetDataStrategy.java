package LRU_cache;

public interface GetDataStrategy {

	//	this method will be overriden later for different data source types
	public Object getData(int key);

}
