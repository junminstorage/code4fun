package org.blueocean;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentStack <T>{
	AtomicReference<Node<T>> top = new AtomicReference<Node<T>>();
	
	private static class Node <T> {
		final T data;
		Node next;
		public Node(T item){
			this.data = item;
		}
	}
	
	public void push (T item){
		Node newN = new Node(item);

		while(true){			
				Node curTop = top.get();
				newN.next = curTop;
				if(top.compareAndSet(curTop, newN))
					return;
		}
	}
	
	public T pop(){
		while(true){
			Node curTop = top.get();
			if(curTop==null)
				return null;
			Node nexTop = curTop.next;
			if(top.compareAndSet(curTop, nexTop))
				return (T) curTop.data;
		}
	}

}
