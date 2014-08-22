package org.blueocean;

import java.util.concurrent.atomic.AtomicReference;

public class StackQ {
	
	class Node {
		int value;
		Node next;
	}
	
	class ConcurrentStack{
		final AtomicReference<StackQ.Node> head = new AtomicReference<StackQ.Node>();
		
		void push(Node n){			
			Node current = head.get();		
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
