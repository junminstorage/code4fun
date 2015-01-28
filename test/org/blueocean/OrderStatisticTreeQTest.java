package org.blueocean;

import junit.framework.TestCase;
import org.blueocean.OrderStatisticTreeQ.Node;

public class OrderStatisticTreeQTest extends TestCase {
	
	public void testfindKthSmallest(){
		OrderStatisticTreeQ q = new OrderStatisticTreeQ();
		
		Node n4 = q.new Node(4, 3);
		Node n2 = q.new Node(2, 1);
		Node n5 = q.new Node(5, 2);
		Node n6 = q.new Node(6, 1);
		n4.left = n2; n4.right = n5;
		n5.right = n6;
		int index = q.findIndexFor(n4, 2); 
		int smallest = q.findKthSmallest(n4, 2); 
		
		System.out.println(index);
		System.out.println(smallest);
	}

}
