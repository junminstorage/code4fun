package org.blueocean;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CuckooHash {
	//the key index array
	protected int[] keys;
	//the buckets
	protected Set[] cache;
	
	//the size of the buckets
	int capacity;
	//the number of filled buckets
	private int size;
	//the maximum ratio of size over capacity until resizing	
	private final float loadFactor;
	
	final transient IntHashFuncI[] hashFunctions;
	static final int PRIME_NUMBER2 =0xb4b82e39;
	static final int PRIME_NUMBER3 =0xced1c241;
	private static final IntHashFuncI[] HASH_FUNCS = new IntHashFuncI[]{new BitOpHash(PRIME_NUMBER2) , new BitOpHash(PRIME_NUMBER3)};

	private final boolean ready;
	
	public CuckooHash(int iniCapacity, float loadfactor, String file, IntHashFuncI[] hashFuncs){
		//File f = new File(file);
		this(iniCapacity, loadfactor, hashFuncs);
		//parse file	
		//ready = true;
	}
	
	public CuckooHash(int iniCapacity, float loadfactor, String file){
		this(iniCapacity, loadfactor, file, HASH_FUNCS);
	}
	
	public CuckooHash(String file){
		this(16, 0.75f, file, HASH_FUNCS);
	}
	
	CuckooHash() {
		this(16, 0.75f, new IntHashFuncI[]{new HashFunc(1) , new HashFunc(2)});
	}
	
	CuckooHash(int iniCapacity, float loadfactor, IntHashFuncI[] hashFuncs){
		keys = new int[iniCapacity];
		for(int i=0; i< iniCapacity; i++)
			keys[i] = -1;
		cache = new Set[iniCapacity];
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
		if(!ready)
			throw new IllegalStateException("cache is not ready yet");
		for(int i=0; i<2; i++){
			int index = keys[id];					
					//hashFunctions[i].hash(id, capacity);
			if(index!=-1 && cache[index]!=null)
				return cache[index];
		}
		return Collections.emptySet();
	}
	
	boolean insert(int id, Set<String> tags, boolean flag){
		//tags = Collections.unmodifiableSet(tags);
		for(int i=0; i<2; i++){
			int index = hashFunctions[i].hash(id, capacity);
			if(cache[index]==null){
				cache[index] = tags;
				keys[id] = index;
				if(flag)
					this.size++;
				return true;
			}
		}
		return false;
	}
	
	private void ensureCapacity (int id) {
		if(this.size>=this.loadFactor*this.capacity){
			System.out.format("ensureCapacity %d, %d %d %f", id, this.size, this.capacity,  this.loadFactor);
			rehash(this.capacity<<1);
		}
	}
	
	void put(int id, Set<String> tags){
		put(id, tags, true);
	}
	 
	void put(int id, Set<String> tags, boolean flag){
		//tags = Collections.unmodifiableSet(tags);
		ensureCapacity(id);
		
		if(this.insert(id, tags, flag))
			return;
		//start the cuckoo bullying process
		Set insert = tags;
		Set current = insert;
		int counter = 0, currentId = id;
		int index = hashFunctions[0].hash(id, capacity);
		while(counter++<this.capacity ){
			if(cache[index]==null){
				cache[index] = current;
				keys[currentId] = index;
				if(flag)
					size++;
				return;
			}
			Set temp = cache[index];
			cache[index] = current;
			current = temp;
			keys[currentId] = index;
			currentId = index;
			
			if(index == hashFunctions[0].hash(currentId, capacity))
				index = hashFunctions[1].hash(currentId, capacity);
			else
				index = hashFunctions[0].hash(currentId, capacity);
		}
		
		rehash(this.capacity<<1);
		put(id, tags, flag);
	}
	
	//double hash table size
	private void rehash(int newSize) {
		System.out.println("rehash to " + newSize);
		int temp = this.size;
		this.capacity = newSize;
		hashFunctions[0].reset(capacity);
		hashFunctions[1].reset(capacity);
		Set[] oldCache = cache;
		int[] oldKeys = keys;
		cache = new Set[newSize];
		keys = new int[newSize];
		for(int i=0; i< newSize; i++)
			keys[i] = -1;
		for(int i=0; i<temp; i++){
			if(oldKeys[i]!=-1 && oldCache[oldKeys[i]]!=null)
				this.put(i, oldCache[oldKeys[i]], false);
		}
		this.size = temp;
		System.out.println("rehash and size is " + this.size);
	}

	//int primitive HashMap entry, immutable class
	final class Entry {
		private final int id;
		private final Set<String> tags;
		public Entry(final int id, final Set<String> tags) {
			super();
			this.id = id;
			this.tags = Collections.unmodifiableSet(tags);
		}
		public int getId(){
			return id;
		}
		
		public Set<String> getTags() {
			return tags;
		}		
	}
		
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
