package org.blueocean;

import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class XXCuckooHash {
	
	//the buckets
	protected AtomicReferenceArray<Entry> cache;
	//the size of the buckets
	int capacity;
	//the number of filled buckets
	private int size;
	//the maximum ratio of size over capacity until resizing	
	private final float loadFactor;
	
	final transient IntHashFuncI[] hashFunctions;
	
	private final boolean ready;
	
	public XXCuckooHash(int iniCapacity, float loadfactor, String file, IntHashFuncI[] hashFuncs){
		//File f = new File(file);
		this(iniCapacity, loadfactor, hashFuncs);
		//parse file	
		//ready = true;
	}
	
	public XXCuckooHash(int iniCapacity, float loadfactor, String file){
		this(iniCapacity, loadfactor, file, new IntHashFuncI[]{new HashFunc(1) , new HashFunc(2)});
	}
	
	public XXCuckooHash(String file){
		this(16, 0.75f, file, new IntHashFuncI[]{new HashFunc(1) , new HashFunc(2)});
	}
	
	XXCuckooHash() {
		this(16, 0.75f, new IntHashFuncI[]{new HashFunc(1) , new HashFunc(2)});
	}
	
	XXCuckooHash(int iniCapacity, float loadfactor, IntHashFuncI[] hashFuncs){
		cache = new AtomicReferenceArray<Entry>(iniCapacity);
		loadFactor = loadfactor;
		capacity = iniCapacity;
		hashFunctions = hashFuncs;	
		hashFunctions[0].reset(capacity);
		hashFunctions[1].reset(capacity);		
		ready = true;
	}
	
	/*
	 * a special implementation of string intern which is faster than JDK version
	 */
	private static final ConcurrentMap<String, String> TAG_POOL = new ConcurrentHashMap<String, String>();
	public static String intern(String s) {
		String result = TAG_POOL.get(s);
		if (result == null) {
			result = TAG_POOL.putIfAbsent(s, s);
			if (result == null)
				result = s;
		}
		return result;
	}
	
	public Set<String> get(int id){
		for(int i=0; i<2; i++){
			int index = hashFunctions[i].hash(id, capacity);
			if(cache.get(index)!=null && cache.get(index).id == id)
				return cache.get(index).tags;
				//return new HashSet<String>(cache[index].tags);
				//return Collections.unmodifiableSet(cache[index].tags);
		}
		return Collections.emptySet();
	}
	
	public boolean updateTags(int id, Set<String> tags){
		boolean flag = false;
		for(int i=0; i<2; i++){
			int index = hashFunctions[i].hash(id, capacity);
			if(cache.get(index)!=null && cache.get(index).getId() == id){
				cache.set(index, new Entry(id, tags));
				flag= true;	    			
			}
		}
		return flag;
	}

	boolean insert(int id, Set<String> tags){
		tags = Collections.unmodifiableSet(tags);
		for(int i=0; i<2; i++){
			int index = hashFunctions[i].hash(id, capacity);
			if(cache.get(index)==null){
				cache.set(index,  new Entry(id, tags));
				this.size++;
				return true;
			}
		}
		return false;
	}
	
	private void ensureCapacity (int id) {
		if(this.size>=this.loadFactor*this.capacity){
			//System.out.format("ensureCapacity %d, %d %d %f", id, this.size, this.capacity,  this.loadFactor);
			rehash(this.capacity<<1);
		}
	}
	 
	void put(int id, Set<String> tags){
		//tags = Collections.unmodifiableSet(tags);
		ensureCapacity(id);
		
		if(this.insert(id, tags))
			return;
		//start the cuckoo bullying process
		Entry insert = new Entry(id, tags);
		Entry current = insert;
		int counter = 0;
		int index = hashFunctions[0].hash(id, capacity);
		while(counter++<this.capacity || current!=insert ){
			if(cache.get(index)==null){
				cache.set(index,  current);
				size++;
				return;
			}
			Entry temp = cache.get(index);
			cache.set(index,  current);
			current = temp;
			
			if(index == hashFunctions[0].hash(current.id, capacity))
				index = hashFunctions[1].hash(current.id, capacity);
			else
				index = hashFunctions[0].hash(current.id, capacity);
		}
		
		rehash(this.capacity<<1);
		put(id, tags);
	}
	
	//double hash table size
	private void rehash(int newSize) {
		System.out.println("rehash to " + newSize);
		int temp = this.size;
		this.capacity = newSize;
		hashFunctions[0].reset(capacity);
		hashFunctions[1].reset(capacity);
		AtomicReferenceArray<Entry> oldCache = cache;
		cache = new AtomicReferenceArray<Entry>(newSize);
		for(int i=0; i<temp; i++){
			Entry e = oldCache.get(i);
			if(e!=null)
				this.put(e.id, e.tags);
		}
		this.size = temp;
		System.out.println("rehash and size is " + this.size);
	}

	//int primitive HashMap entry
	final class Entry {
		private final int id;
		private final Set<String> tags;
		public Entry(int id, Set<String> tags) {
			super();
			this.id = id;
			this.tags = tags;
		}
		int getId(){
			return id;
		}
		
		public Set<String> getTags() {
			return tags;
		}		
	}
	
	static final int PRIME_NUMBER2 =0xb4b82e39;
	static final int PRIME_NUMBER3 =0xced1c241;
	
	//implementation of hash function using bit operation
	static class BitOpHash implements IntHashFuncI {
		private final int prime;
		private int shift;
		
		BitOpHash(int prime){
			this.prime = prime;
		}
		@Override
		public int hash(int key, int range){
			key *= prime;
		    return (key ^ (key >>> shift)) & (range - 1);
		}

		@Override
		public void reset(int range) {
			shift = 31 - Integer.numberOfTrailingZeros(range);
		}
	}
	
	//implementation of hash function using random generator
	static class HashFunc implements IntHashFuncI {
		private static final Random GENERATOR = new Random();
		private int round;
		HashFunc(int loop){
			round = loop;
		}
		@Override
		public int hash(int key, int range){
			GENERATOR.setSeed(key);
			int hash = GENERATOR.nextInt(range);
			for(int i=0; i<this.round; i++)
				hash = GENERATOR.nextInt(range);
			return hash;
		}	
		@Override
		public void reset(int range){}
	}
	
	static interface IntHashFuncI {
		public int hash(int key, int range);
		public void reset(int range);
	}

}
