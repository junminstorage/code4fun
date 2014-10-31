package org.blueocean;

import java.util.ArrayList;
import java.util.List;

public class ArrayStack<T> {
	private final int capacity;
	private Node<T>[] store;
	private int[] tops;
	private List<Integer> freeList;
	private int size;
	private int lastIndex;
	
	public ArrayStack(int cap){
		size = 0;
		lastIndex=-1;
		tops = new int[]{-1, -1, -1};
		this.capacity = cap;
		store =  new Node[cap];
		List<Integer> freeList = new ArrayList<Integer>();	
	}
	
	class Node<T> {
		T data;
		int next;
		
		public Node(T d){
			this.data = d;
			next = -1;
		}
		
		public Node(T d, int i){
			this.data = d;
			this.next = i;
		}
	}
	
	public boolean isEmpty(int number){
		return tops[number]==-1;
	}
	
	public boolean isFull(){
		return size==this.capacity;
	}
	
	public void push(int index, T d){
		if(isFull())
			return;	
		Node<T> n = new Node<T>(d);
		if(tops[index]>=0)
			n.next = tops[index];	
		if(lastIndex<this.capacity-1){
			tops[index] = ++lastIndex;
			store[tops[index]] = n;
		}else{
			int available = freeList.remove(0);
			store[available] = n;
			tops[index] = available;
		}
		size++;
	}
	
	public T pop(int number){
		if(isEmpty(number))
			return null;
		Node<T> n = store[tops[number]];
		freeList.add(tops[number]);
		tops[number] = n.next;	
		return n.data;
	}
}
