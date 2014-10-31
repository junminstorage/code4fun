package org.blueocean;

import org.blueocean.LinkedListQ.LinkedListNode;

import junit.framework.TestCase;

public class LinkedListQTest extends TestCase{
	
	public void setUp(){
			
	}
	
	public void testgetKthNodeByPacer(){
		LinkedListQ q = new LinkedListQ();
		LinkedListQ.LinkedListNode n = q.new LinkedListNode();
		
		LinkedListQ.LinkedListNode n2 = q.new LinkedListNode();	
		LinkedListQ.LinkedListNode n3 = q.new LinkedListNode();
		n.next = n2;
		n2.next = n3;
		n3.next = null;	
	
		LinkedListQ.LinkedListNode result = q.getKthNodeByPacer(n, 1);
	}
	
	public void testReverseR(){
		LinkedListQ q = new LinkedListQ();
		LinkedListQ.LinkedListNode n = q.new LinkedListNode();
		
		LinkedListQ.LinkedListNode n2 = q.new LinkedListNode();	
		LinkedListQ.LinkedListNode n3 = q.new LinkedListNode();
		n.next = n2;
		n2.next = n3;
		n3.next = null;	
			q.reverseR(n);		
	}
	
	public void testReverseI(){
		LinkedListQ q = new LinkedListQ();
		LinkedListQ.LinkedListNode n = q.new LinkedListNode();
		
		LinkedListQ.LinkedListNode n2 = q.new LinkedListNode();	
		LinkedListQ.LinkedListNode n3 = q.new LinkedListNode();
		n.next = n2;
		n2.next = n3;
		n3.next = null;	
		q.reverseI(n);		
}

}
