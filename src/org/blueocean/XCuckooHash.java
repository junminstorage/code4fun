package org.blueocean;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class XCuckooHash extends CuckooHash {
	private static final int N_LOCKS = 16;
	
	private final ReadWriteLock[] locks = new ReentrantReadWriteLock[N_LOCKS];
	
	private final Lock[] rs = new Lock[N_LOCKS];
	private final Lock[] ws = new Lock[N_LOCKS];
	   
    public XCuckooHash(String file){
		super(16, 0.75f, file, new IntHashFuncI[]{new HashFunc(1) , new HashFunc(2)});
		for(int i=0; i<N_LOCKS; i++){
			locks[i] = new ReentrantReadWriteLock();
			rs[i] = locks[i].readLock();
			ws[i] = locks[i].writeLock();
		}			
	}
    
    XCuckooHash(int iniCapacity, float loadfactor, IntHashFuncI[] hashFuncs){
    	super(iniCapacity, loadfactor,  hashFuncs);
    	for(int i=0; i<N_LOCKS; i++){
    		locks[i] = new ReentrantReadWriteLock();
			rs[i] = locks[i].readLock();
			ws[i] = locks[i].writeLock();
		}	
    }
    
    @Override
    public Set<String> get(int id){   	
    	for(int i=0; i<2; i++){
			int index = hashFunctions[i].hash(id, capacity);
			if(cache[index]!=null && cache[index] == id){
				rs[index%N_LOCKS].lock();
				try{
					return cache[index].getTags();
				}finally{
					rs[index%N_LOCKS].unlock();
				}
			}
		}
		return Collections.emptySet();
	}
    
    public boolean updateTags(int id, Set<String> tags){
    	boolean flag = false;
    	for(int i=0; i<2; i++){
    		int index = hashFunctions[i].hash(id, capacity);
    		if(cache[index]!=null && cache[index].getId() == id){
    			ws[index%N_LOCKS].lock();
    			try{
    				cache[index] = new Entry(id, tags);
    				flag= true;
    			}finally{
    				ws[index%N_LOCKS].unlock();
    			}
    		}
    	}
    	return flag;
    }

}
