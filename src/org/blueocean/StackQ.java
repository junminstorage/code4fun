package org.blueocean;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

public class StackQ {
	
	/*
	 * 3. Make a stack using 2 given queue.
	 */
	public static class StackWithQ{
		private Deque pushQ;
		private Deque pepQ;
		
		public StackWithQ(){
			pushQ = new ArrayDeque();
			pepQ = new ArrayDeque();
		}
		
		public void push(Object item){
			pepQ.addLast(item);
			
			if(!pushQ.isEmpty()){
				pepQ.addLast(pushQ.removeFirst());
			}
			
			Deque temp = pushQ;
			pushQ = pepQ;
			pepQ = temp;
			
		}
		
		public Object pop(){
			if(pushQ.isEmpty())
				return null;
			return pushQ.removeFirst();
		}
	}
	
	/*
	 * http://www.geeksforgeeks.org/design-a-stack-with-find-middle-operation/
	 */
	public class LinkedListNode{
		Object data;
		LinkedListNode pre;
		LinkedListNode next;
		public LinkedListNode(Object d){this.data = d;}
	}
	
	LinkedListNode head;
	LinkedListNode middle;
	LinkedListNode tail;
	int sizeS = 0;
	int middlePos = -1;
	
	public void pushS(Object d){
		sizeS++;

		LinkedListNode newNode = new LinkedListNode(d);
		if(tail == null){
			head = newNode;
			tail = newNode;
			middle = newNode;
		}else{			
			newNode.next = head;
			head.pre = newNode;
			head = newNode;
		}	
		
		if((sizeS-1)/2 - middlePos==1){
			middle = middle.next;
			middlePos++;
		}
		
	}
	
	public LinkedListNode popS(){
		if(tail == null){
			return null;
		}else{
			sizeS--;
			if((sizeS-1)/2 - middlePos==-1){
				middle = middle.pre;
				middlePos--;
			}
			
			LinkedListNode re = head;
			
			if(head==tail){//just one node
				head = null;
				tail = null;
			}else{
				head.next.pre = null;
				head = head.next;
			}			
			return re;
		}
	}
	
	public LinkedListNode findMiddle(){
		return middle;
	}
	
	public void deleteMiddle(){
		size--;
		middlePos--;
		if(size%2==0)
			middle = middle.pre;
		else
			middle = middle.next;
	}
	
	
	/**
	 *  Given a tree populate the sibiling of the tree node with the 
	 *  next node in same level.space complexity-O(1)
	 *  
	 * @param input
	 * @return
	 */
	/*
	 * Given a stack output a sorted stack.
	 */
	public static Stack<Integer> toSortedStack(Stack<Integer> input){
		Stack<Integer> sorted = new Stack<Integer>();
		while(!input.isEmpty()){
			int v = input.pop();
			while(!sorted.isEmpty() && sorted.peek()>v){
				input.push(sorted.pop());
			}
			sorted.push(v);
		}
		return sorted;
	}
	
	/*
	 * implement k stacks with one array
	 */
	private int[] store;
	private int[] tops;
	private int free;
	private int[] next; //stacks and free stacks' next pointers
	private int size;
	private int capacity;
	
	public StackQ(int k, int capacity){
		this.capacity = capacity;
		store = new int[capacity];
		tops = new int[k];
		for(int i: tops)
			i = -1;
		size = 0;
		next = new int[capacity];
		for(int i=0;i<capacity-1;i++)
			next[i] = i+1;
		next[capacity-1] = -1;
		free = 0; // first free index is 0
	}
	
	public boolean isEmpty(int index){
		return tops[index]==-1;
	}
	
	public boolean isFull(){
		return size == capacity;
	}
	
	public void push(int index, int v){
		if(isFull())
			return;
		int available = free;
		int nextFree = next[available];
		
		store[available] = v;
		next[available] = tops[index];
		tops[index] = available;
		
		free = nextFree;
		size++;
	}
	
	public int pop(int index){
		if(isEmpty(index))
			return -1;
		int freeCandidate = tops[index];
		int returnV = store[freeCandidate];
		
		int nextFree = free;
		free = freeCandidate;
		next[free] = nextFree;
		
		tops[index] = next[freeCandidate];
		
		return returnV;
	}
	
	/*
	 * implement a queue using two stacks
	 */
	Stack left = new Stack();
	Stack right = new Stack();
	
	public Object pop(){
		if(!right.isEmpty())
			return right.pop();
		else{
			while(!left.isEmpty()){
				right.push(left.pop());
			}
			
			if(!right.isEmpty())
				return right.pop();
			else
				return null;			
		}		
	}
	
	public void push(Object v){
		left.push(v);
	}
	
	/*
	 * concurrent stack implementation
	 */
	class Node {
		int value;
		Node next;
	}
	
	class ConcurrentStack{
		final AtomicReference<StackQ.Node> head = new AtomicReference<StackQ.Node>();
		
		void push(Node n){			
			Node current = head.get();	
			n.next = current;
			while(!head.compareAndSet(current, n)){
				current = head.get();
				n.next = current;
			}			
		}
		
		Node pop(){						
			Node current;
			Node n;
			do {
				current = head.get();
				if(current==null)
					return null;
				n = current.next;
			}while(!head.compareAndSet(current, n));			
			return current;
		}	
	}

}
