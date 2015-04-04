package org.blueocean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class XEntityTagCache {
	
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private static final int DEFAULT_INIT_CAPACITY = 16;
	private volatile static boolean ready;
	
	/*
	 * public method to create cache singleton 
	 */
	public static XEntityTagCache getInstance(String file) throws FileNotFoundException, IOException{
		INSTANCE.loadFile(new File(file));
		ready = true;
		return INSTANCE;
	}
	
	//the key index array
	private int[] keys;
	//the buckets
	private AtomicReferenceArray<Entry> cache;
	//the size of the buckets
	private int capacity;
	//the number of filled buckets
	private int size;
	//the maximum ratio of size over capacity until resizing	
	private final float loadFactor;
	
	private final transient IntHashFuncI[] hashFunctions;
	static final int PRIME_NUMBER2 =0xb4b82e39;
	static final int PRIME_NUMBER3 =0xced1c241;
	static final IntHashFuncI[] HASH_FUNCS = new IntHashFuncI[]{new BitOpHash(PRIME_NUMBER2) , new BitOpHash(PRIME_NUMBER3)};

	private static final XEntityTagCache INSTANCE = new XEntityTagCache();
	
	private XEntityTagCache(int iniCapacity, float loadfactor, String file, IntHashFuncI[] hashFuncs) throws FileNotFoundException, IOException {
		keys = new int[iniCapacity];
		cache = new AtomicReferenceArray<Entry>(iniCapacity);
		loadFactor = loadfactor;
		capacity = iniCapacity;
		hashFunctions = hashFuncs;	
		hashFunctions[0].reset(capacity);
		hashFunctions[1].reset(capacity);
		//parse file	
		this.loadFile(new File(file));	
		ready = true;
	}
	
	private XEntityTagCache(int iniCapacity, float loadfactor, String file) throws FileNotFoundException, IOException {
		this(iniCapacity, loadfactor, file, HASH_FUNCS);
	}
	
	private XEntityTagCache(String file) throws FileNotFoundException, IOException{
		this(DEFAULT_INIT_CAPACITY, DEFAULT_LOAD_FACTOR, file, HASH_FUNCS);
	}
	
	//these two package scope constructors is mainly for unit tests and cache tuning purpose
    XEntityTagCache() {
		this(DEFAULT_INIT_CAPACITY, DEFAULT_LOAD_FACTOR, HASH_FUNCS);
	}
	
	XEntityTagCache(int iniCapacity, float loadfactor, IntHashFuncI[] hashFuncs){
		keys = new int[iniCapacity];
		cache = new AtomicReferenceArray<Entry>(iniCapacity);
		loadFactor = loadfactor;
		capacity = iniCapacity;
		hashFunctions = hashFuncs;	
		hashFunctions[0].reset(capacity);
		hashFunctions[1].reset(capacity);		
		ready = true;
	}
		
	public Set<String> getTags(int id){
		return this.get(id);
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
	
	public Set<String> get(int id){
		if(!ready)
			throw new IllegalStateException("cache is not ready yet");
		int index = keys[id];

		if(cache.get(index)!=null && cache.get(index).id == id)
			return cache.get(index).tags;
		return Collections.emptySet();
	}
	
	/*
	 * read file line by line and put tags into TAG_POOL by string interning
	 */
	private void loadFile(File file) throws FileNotFoundException, IOException{
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       String[] tokens = line.split(",");
		       if(tokens.length>1){
		    	   Set<String> tags = new HashSet<String>();
		    	   for(int i=1; i<tokens.length; i++){
		    		   tags.add(intern(tokens[i]));
		    	   }
		    	   this.put(Integer.valueOf(tokens[0]), tags);
		       }
		    }
		}
		
	}
	
	boolean insert(int id, Set<String> tags){
		return insert(id, tags, true);
	}
	boolean insert(int id, Set<String> tags, boolean flag){
		for(int i=0; i<2; i++){
			int index = hashFunctions[i].hash(id, capacity);
			if(cache.get(index)==null){
				cache.set(index, new Entry(id, tags));
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
			//System.out.format("ensureCapacity %d, %d %d %f", id, this.size, this.capacity,  this.loadFactor);
			rehash(this.capacity<<1);
		}
	}
	 
	void put(int id, Set<String> tags){
		put(id, tags, true);
	}
	void put(int id, Set<String> tags, boolean flag){
		ensureCapacity(id);
		//try to find empty slot and insert
		if(this.insert(id, tags, flag))
			return;
		//start the cuckoo bullying process
		Entry insert = new Entry(id, tags);
		Entry current = insert;
		int counter = 0;
		int index = hashFunctions[0].hash(id, capacity);
		//cycle detection condition
		while(counter++<this.capacity || current!=insert ){
			if(cache.get(index)==null){
				cache.set(index,  current);
				keys[current.id] = index;
				if(flag)
					size++;
				return;
			}
			Entry temp = cache.get(index);
			cache.set(index, current);
			keys[current.id] = index;
			current = temp;
			
			if(index == hashFunctions[0].hash(current.id, capacity))
				index = hashFunctions[1].hash(current.id, capacity);
			else
				index = hashFunctions[0].hash(current.id, capacity);
		}
		//this is rare case when there is cycle, hash table needs to be resized 
		rehash(this.capacity<<1);
		//then try insert again
		put(id, tags, flag);
	}
	
	//double hash table size to the next power of 2
	private void rehash(int newSize) {
		//System.out.println("rehash to " + newSize);
		int temp = this.size;
		this.capacity = newSize;
		hashFunctions[0].reset(capacity);
		hashFunctions[1].reset(capacity);
		AtomicReferenceArray<Entry> oldCache = cache;
		cache = new AtomicReferenceArray<Entry>(newSize);
		keys = new int[newSize];
		for(int i=0; i<oldCache.length(); i++){
			Entry e = oldCache.get(i);
			if(e!=null)
				this.put(e.id, e.tags, false);
		}
		this.size = temp;
		//System.out.println("rehash and size is " + this.size);
	}

	//id to tags HashMap entry, immutable class
	final class Entry {
		private final int id;
		private final Set<String> tags;
		public Entry(final int id, final Set<String> tags) {
			super();
			this.id = id;
			this.tags = Collections.unmodifiableSet(tags);
		}
		int getId(){
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
	
	public int size(){
		return this.size;
	}
	
	public int capacity(){
		return this.capacity;
	}
}
