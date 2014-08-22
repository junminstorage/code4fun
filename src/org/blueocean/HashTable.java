package org.blueocean;

import java.io.Serializable;

public class HashTable implements Cloneable {
	
	private int size;
	private LinkedList[] buckets = new LinkedList[size];

	private static class LinkedList {
		final Object key;
		Object data;
		LinkedList next;
		
		LinkedList(Object d, Object k, LinkedList n){
			data = d; next = n; key = k;
		}
		LinkedList deepCopy(){
			return new LinkedList(this.data, this.key, this.next!=null? this.next.deepCopy(): null);
		}
		
		LinkedList deepCopyI(){
			
			LinkedList copy = new LinkedList(this.data, this.key, this.next);
			
			while(copy.next!=null){
				copy.next = new LinkedList(copy.next.data, this.key, copy.next.next);				
				copy = copy.next;
			}
			
			return copy;
		}
	}
	
	@Override
	public HashTable clone(){
			
		try {
			HashTable copy = (HashTable) super.clone();
			copy.size = this.size;
			copy.buckets = new LinkedList[this.size];
			
			for(int i=0; i<copy.size; i++){
				copy.buckets[i] = this.buckets[i].deepCopy();
			}
			return copy;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
		
			
	}

}
