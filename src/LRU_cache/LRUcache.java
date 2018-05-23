package LRU_cache;

import java.util.HashMap;

public class LRUcache {
	int capacity;
	HashMap<Integer, Node> map = new HashMap<Integer, Node>();
	Node head=null;
	Node end=null;
	// TODO: add hit ratio for cache
	// TODO: add miss ratio for cache
	// TODO: make clear method to clear the cache

	//	constructor with custom capacity
	public LRUcache(int capacity) {
		this.capacity = capacity;
	}

	//	constructor with capacity of 10
	public LRUcache() {
		this.capacity = 10;
	}

	// TODO: make "contains" method for cache, which is like "get", but a boolean
	//	RETURN the OBJECT value
	public Object get(int key) {
		if(map.containsKey(key)){	//	if found, return object value in node
			Node n = map.get(key);
			remove(n);
			setHead(n);
			return n.value;
		}
		return -1;					//	if not found, return -1
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

	// TODO: remane to "put" method for cache
	//	set new HEAD
	private void setHead(Node n) {
		n.next = head;		//	old head becomes #2 spot
		n.pre = null;		//	nothing before new head
		if(head!=null)		//	if list is NOT empty
			head.pre = n;	//	before the old head, is the new head
		head = n;			//	make new head official with pointer
		if(end == null)		//	if list has not end
			end = head;		//	head is also end, because we have only 1 element
	}


	//	INSERT NEW element
	public void set(int key, Object value) {
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




}
