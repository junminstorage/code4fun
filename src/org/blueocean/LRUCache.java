package org.blueocean;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LRUCache {
	private final static int MAX = 100;
	
	/////////////////////////////////////
	//solution 1
	private Map cache = new LinkedHashMap(MAX, .75F, true){
		@Override
		protected boolean removeEldestEntry(Map.Entry oldest){
			return size() > MAX;
		}
		
	};
	
	public Map m = Collections.synchronizedMap(cache);
	
	/////////////////////////////////////
	//soultion 2
	Entry head;
	Entry tail;
	
	HashMap<Object, Entry> store = new HashMap<Object, Entry>();
	public class Entry{
		Object key;
		Object value;
		Entry pre;
		Entry after;
	}
	//always put new one at the end
	public void put(Object key, Object value){
		Entry entry = new Entry();
		entry.key = key;
		entry.value = value;
		
		store.put(key, entry);
		if(head==null){
			head = entry;
			tail = entry;
		}else{
			tail.after = entry;
			entry.pre = tail;
			tail = entry;
			if(store.size() > MAX){			
				head = head.after;
			}
		}		
	}
	
	//if found, then we need to move it to the tail as well
	public Object get(Object key){
		Entry e = store.get(key);
		if(e==null)
			return null;
		
		//delete e node
		Entry pre = e.pre;
		Entry after = e.after;
		if(after==null)//is tail
			return e.value;
		
		if(pre==null){//is head
			head = after;
		}else{
			pre.after = after;
		}		
		//move e node to tail
		tail.after = e;
		e.pre = tail;
		tail = e;
		
		return e.value;
	}
	
	///////////////////////////
	//solution 3
	private ConcurrentHashMap cmap = new ConcurrentHashMap();
	private ConcurrentLinkedQueue cq = new ConcurrentLinkedQueue();
	
	public Object cget(Object key){
		Object o = cmap.get(key);
		if(key!=null && cq.contains(key)){
			cq.remove(key);
			cq.add(key);
		}
		return o;
	}
	
	
	public void cput(Object key, Object value){
		if(cmap.contains(key))
			cq.remove(key);
		if(cq.size()>=MAX){
			cq.remove();
		}
		cmap.put(key, value);
		cq.add(key);
	}
	
	

}
