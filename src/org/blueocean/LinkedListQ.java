package org.blueocean;

import java.util.LinkedList;

public class LinkedListQ {
	
	class LinkedListNode{
		Object data;
		LinkedListNode next;
	}
	
	class SortedListNode{
		int v;
		SortedListNode next;
	}
	
	/**
	 * insert node to sorted cyclic list
	 */
	public static void insertNode(SortedListNode n, SortedListNode insert){
		if(n==null){
			//create a list
		}
		
		SortedListNode p = n;
		
		while(n.next!=p){
		if(n.next.v<n.v){
			if(insert.v > n.v || insert.v < n.next.v){
				break;
			}
		}		
		if(n.next.v > n.v){
			if(insert.v>n.v && insert.v <n.next.v){
				break;
			}
		}		
		n = n.next;		
		}		
		
		//insertAfterNode(SortedListNode n, SortedListNode insert);		
	}
	
	
	public static void reverseR(LinkedListNode head){			
		if(head==null)
			return;		
		reverseR(head, head.next);		
		head.next = null;		
	}
	
	private static LinkedListNode reverseR(LinkedListNode curr, LinkedListNode next){			
		if(next==null)
			return curr;
		
		LinkedListNode nextN = next;		
		LinkedListNode h = reverseR(nextN, nextN.next); //recursive all way until the tail
		h.next = curr;
		return curr;
	
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
			nextN = temp;
		}
		
		data.next = null;//head becomes tail
				
	}

}
