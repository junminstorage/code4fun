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
	
	
	public static LinkedListNode findIntersection(LinkedListNode l1, LinkedListNode l2){
		if(l1==null || l2 == null)
			return null;
		
		int len1 = getLength(l1);
		int len2 = getLength(l2);
		
		LinkedListNode b;
		LinkedListNode s;
		if(len1 >= len2){
			b = l1;
			s = l2;
		}else{
			b = l2;
			s = l1;
		}
		
		int step = 0;
		while(step<Math.abs(len1-len2)){
			step++;
			b = b.next;
		}
		
		while(b!=null && s!=null && b!=s){
			b = b.next;
			s = s.next;
		}
		
		return b;
	}
	
	public static int getLength(LinkedListNode l){
		int len = 0;
		while(l!=null){
			l = l.next;
			len++;
		}
		return len;
	}
	
	public static void reverseR(LinkedListNode head){			
		if(head==null)
			return;		
		reverseR(head, head.next);		
		head.next = null;		
	}
	
	public static void segPosNeg(LinkedListNode head){
		LinkedListNode pos = head;
		LinkedListNode neg = head;
		
		while(neg!=null){
			while(neg!=null && (Integer)neg.data>=0){
				neg = neg.next;
			}

			pos = neg;

			while(pos!=null && (Integer)pos.data<=0){
				pos = pos.next;
			}

			if(pos!=null && neg!=null){
				Object t = pos.data;
				pos.data = neg.data;
				neg.data = t;
			}
			
		}
		
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
