package org.blueocean;

import java.util.LinkedList;

public class LinkedListQ {
	
	class LinkedListNode{
		Object data;
		LinkedListNode next;		
	}
	
	public static LinkedListNode reverseR(LinkedListNode head){		
		if(head==null)
			return null;
					
		if(head.next!=null){
			LinkedListNode h = reverseR(head.next); //recursive all way until the tail
			h.next = head;
			head.next = null;
			return h;
		}
		else
			return head;//stack pops from tail
		
	}
	
	public static void reverseI(LinkedListNode data){
		if(data==null)
			return;
	
		LinkedListNode currentN = data;
		LinkedListNode nextN = data.next;
	
		while(nextN!=null){
			
			LinkedListNode temp = nextN.next;
			
			nextN.next = currentN;//reverse									
			
			currentN = nextN;
			nextN = temp.next;
		}
		
		data.next = null;//head becomes tail
				
	}

}
