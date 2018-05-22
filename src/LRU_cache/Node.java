package LRU_cache;

//	Double Linked List
public class Node {
	int key;			//	key of int
	Object value;		//	value of Object class
	Node pre;
	Node next;

	public Node(int key, Object value){
		this.key = key;
		this.value = value;
	}

}
