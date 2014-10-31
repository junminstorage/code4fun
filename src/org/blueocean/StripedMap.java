package org.blueocean;

public class StripedMap {
	private final int NUM_LOCKS = 16;
	private final Node[] buckets;
	private final Object[] locks;
	
	private static class Node {
		Object key;
		Object value;
		Node next;
	}
	
	public StripedMap(int numBuckets) {
		buckets = new Node[numBuckets];
		locks = new Object[NUM_LOCKS];
		for(int i = 0; i<NUM_LOCKS; i++)
			locks[i] = new Object();
	}
	
	public Object get(Object key){
		int index = key.hashCode() % buckets.length;
		Object ret = null;
		
		synchronized(locks[index%NUM_LOCKS]){
			Node head = buckets[index];
			while(head!=null){
				if(head.key.equals(key)){
					ret = head.value;
					break;
				}
				head = head.next;
			}
		}
		return ret;
	}
	
	private void resize(){
		int newSize = buckets.length*2;
		Node[] newBuckets = new Node[buckets.length*2];
		for(Node b : buckets){	
			while(b!=null){
				Node head = newBuckets[b.key.hashCode()%newSize];
				
				if(head!=null){
					Node nt = head.next;
					b.next = nt;
				}
				
				head = b;
				b = b.next;
			}
		}		
	}
}
