package org.blueocean;

public class Array2Stack {
	private int[] store;
	private int capacity;
	private int size;
	private int leftEnd;
	private int rightEnd;
	
	public Array2Stack(int c){
		this.capacity = c;
		this.store = new int[this.capacity];
		this.size = 0;
		this.leftEnd = -1;
		this.rightEnd = this.capacity;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public boolean isFull(){
		return size>=this.capacity;
	}
	
	public void push(int index, int v){
		if(isFull())
			return;
		if(index==0){
			store[++leftEnd] = v;
		}else{
			store[--rightEnd] = v;
		}
		size++;
	}
	
	public int pop(int index){
		if(isEmpty())
			return Integer.MIN_VALUE;
		size--;
		if(index==0){
			return store[leftEnd--];
		}else{
			return store[rightEnd++];
		}	
	}

}
