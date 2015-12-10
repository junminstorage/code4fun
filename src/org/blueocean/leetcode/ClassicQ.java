package org.blueocean.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class ClassicQ {
	
	
	static class QNode {
		Integer data;
		QNode previous;
		QNode(Integer n, QNode p){
			data = n; previous = p;
		}
	}
	
	public static class StackUsingQNode {
		Deque<QNode> queue = new ArrayDeque<QNode>();
		QNode last;
		int capacity;
		StackUsingQNode(int cap){
			capacity = cap;
		}
		public void push(Integer num){
			ensureCapcity();
			QNode node = new QNode(num, last);
			queue.add(node);
			last = node;
		}
		
		private void ensureCapcity(){
			if(queue.size() > capacity){
				Deque<QNode> temp = new ArrayDeque<QNode>();
				while(!queue.isEmpty()){
					QNode n = queue.poll();
					if(n.data!=null)
						temp.offer(n);
				}
			queue = temp;
			}
		}
		
		public Integer top(){
			if(last==null)
				return null;
			return last.data;
		}
		
		public Integer pop(){
			Integer ret = top();
			
			QNode temp = last;
			last = last.previous;
			temp.previous = null;
			temp.data = null;
			
			return ret;
		}
		
		public boolean isEmpty(){
			return last == null;
		}
		
	}
	
	
	
	/*
	 * implement stack using queues
	 * with penalty on pop
	 */
	public static class Stack {
		Deque<Integer> queue = new ArrayDeque<Integer>();
		Deque<Integer> queue2 = new ArrayDeque<Integer>();
		Integer last;
		public void push(Integer num){
			queue.add(num);
			last = num;
		}
		
		public Integer top(){
			return last;
		}
		
		public Integer pop(){
			if(queue.isEmpty())
				return null;
			int size=queue.size();
			if(size==1)
				return queue.remove();
			Integer last = null;
			while(size-->1){
				queue2.addLast(queue.remove());
			}		
			last = queue.remove();
			queue = queue2;
			queue2 = new ArrayDeque<Integer>();			
			return last;
		}
		
		public boolean isEmpty(){
			return queue.isEmpty();
		}
	}
	
	/*
	 * implement stack using queues
	 * with penalty on push
	 */
	public static class Stack2 {
		Deque<Integer> queue = new ArrayDeque<>();
		Deque<Integer> queue2 = new ArrayDeque<>();
		Integer last;
		public void push(Integer num){
			/*
			 * queue2 is always empty
			 * we add last push to it
			 */
			queue2.add(num);
			while(!queue.isEmpty())
				queue2.offer(queue.poll());
			queue = queue2;
			queue2 = new ArrayDeque<>();
		}
		
		public Integer top(){
			return queue.peek();
		}
		
		public Integer pop(){
			return queue.poll();
		}
		
		public boolean isEmpty(){
			return queue.isEmpty();
		}
		
		
	}
	
	

}
