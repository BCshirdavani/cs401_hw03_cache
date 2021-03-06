package LRU_cache;

import java.util.HashMap;

public class LRUcache {
	int capacity;
	HashMap<Integer, Node> map = new HashMap<Integer, Node>();
	Node head=null;
	Node end=null;
	int countTotalGetCalls;
	int countMiss;
	int countHit;
	GetDataStrategy dataSource;

	//	CLEAR
	public void clear(){
		map.clear();
		head.next = null;
		head = null;
	}

	// CONSTRUCTOR
	public LRUcache(int capacity, GetDataStrategy source) {
		this.capacity = capacity;
		this.countHit = 0;
		this.countMiss = 0;
		this.countTotalGetCalls = 0;
		this.dataSource = source;	//	custom data source, filePathName ?????
	}

	//	constructor with default capacity of 10, and .txt source
	public LRUcache() {
		this.capacity = 10;
		this.countHit = 0;
		this.countMiss = 0;
		this.countTotalGetCalls = 0;
		this.dataSource = new TextFileStrategy("./src/LRU_cache/FileTest_01.txt");
	}

	//	CONTAINS
	public boolean contains(int key){
		return map.containsKey(key);
	}


	//	GET the OBJECT value
	public Object get(int key) {
		countTotalGetCalls++;		//	increment countTotalGetCalls
		if(map.containsKey(key)){	//	if found, return object value in node
			Node n = map.get(key);
			remove(n);
			setHead(n);
			countHit++;				//	increment countHit
			return n.value;
		}
		else{
			countMiss++;			//	increment countMiss
			Object valFromSource = dataSource.getData(key);
			if(valFromSource != null){
				put(key, valFromSource);
				return valFromSource;
			}
			else{
				System.out.println("cache miss from cache.get(" + key + "), returning -1.");
				return -1;				//	if not found, return -1
			}
		}
	}

	//	DELETE a node
	private void remove(Node n) {	//	if node is NOT at front of list
		if(n.pre!=null){
			n.pre.next = n.next;	//	easy bypass
		}else{
			head = n.next;		//	assign new front
		}
		if(n.next!=null){		//	if node is NOT at end of list
			n.next.pre = n.pre;	//	easy bypass
		}else{
			end = n.pre;		//	assign new end
		}
	}

	//	PUT new HEAD
	private void setHead(Node n) {
		n.next = head;		//	old head becomes #2 spot
		n.pre = null;		//	nothing before new head
		if(head!=null)		//	if list is NOT empty
			head.pre = n;	//	before the old head, is the new head
		head = n;			//	make new head official with pointer
		if(end == null)		//	if list has not end
			end = head;		//	head is also end, because we have only 1 element
	}


	//	PUT : put() : INSERT NEW element
	public void put(int key, Object value) {
		if(map.containsKey(key)){			//	if key already in cache
			Node old = map.get(key);
			old.value = value;				//	overwrite value of existing node for same key
			remove(old);					//	remove old node from Linked List
			setHead(old);					//	put inset this node to the head of the list
		}else{										//	else if key was not in cache map
			Node created = new Node(key, value);	//	create a new node
			if(map.size() >= capacity){					//	and if map is FULL
				map.remove(end.key);					//	remove last node from map
				remove(end);							//	remove node from Linked List
				setHead(created);						//	put new node at head
			}else{										//	else if map is NOT full
				setHead(created);						//	put new node at head
			}
			map.put(key, created);						//	add this new key,val to the map
		}
	}


	public Float getHitRatio(){
		return ((float)countHit / (float)countTotalGetCalls);
	}

	public Float getMissRatio(){
		return ((float)countMiss / (float)countTotalGetCalls);
	}


	public void printMap(){
		System.out.println("***MAP***");
		System.out.println("KEY\t\tVALUE");
		for (int key:map.keySet()) {
			System.out.println(key + "\t\t" + map.get(key).value);
		}
	}

	public void printList(){
		System.out.println("***LIST***");
		Node here = head;
		for(int i = 0; i < map.size(); i++){
			System.out.print(here.key + ":" + here.value + "  -->  ");
			here = here.next;
		}
		System.out.println();
	}




}
