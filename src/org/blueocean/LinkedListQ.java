package org.blueocean;

import java.util.LinkedList;

public class LinkedListQ {
	
	class LinkedListNode{
		Object data;
		LinkedListNode next;
		LinkedListNode random;
		
		public LinkedListNode clone(){
			LinkedListNode copy = new LinkedListNode();
			copy.data = this.data;
			copy.next = this.next;
			return copy;
		}
	}
	
	/*
	 * http://www.careercup.com/question?id=5691288624037888
	 * We have one single linked list. How we’ll travers it that we 
	 * reach till second last (n-1) node. If we want to reach till (n\2) node.
	 */
	public LinkedListNode getKthNodeByPacer(LinkedListNode head, int k){
		LinkedListNode p1 = head;
		for(int i=0; i<k; i++)
			p1 = p1.next;
		
		if(p1==null)
			return null;
		
		LinkedListNode p2 = head;
		while(p1.next!=null){
			p1 = p1.next;
			p2 = p2.next;
		}
		return p2;
	}
	
	public LinkedListNode getMiddle(LinkedListNode head){
		LinkedListNode p1 = head;
		LinkedListNode p2 = head;
		
		while(p1!=null && p1.next!=null){
			p1 = p1.next.next;
			p2 = p2.next;
		}
		return p2;
	}
	
	
	
	public LinkedListNode getKthNode(LinkedListNode head, int k){
		return getKthNodeRecursive(head, 1, k);
	}
	
	public LinkedListNode getKthNodeRecursive(LinkedListNode current, int currentPos, int k){
		if(current==null)
			return null;
		if(currentPos==k)
			return current;
		else
			return getKthNodeRecursive(current.next, currentPos++, k);
	}
	
	
	/*
	 * How to detect loop in LL
	 */
	public static boolean hasLoop(LinkedListNode head){
		if(head==null)
			return false;
		
		LinkedListNode p1 = head;
		LinkedListNode p2 = head;
		
		while(p1!=null && p2!=null && p2.next!=null && p1!=p2){
			p1 = p1.next;
			p2 = p2.next.next;
		}
		
		if(p1!=null && p2!=null && p1==p2){
			p1 = head;
			while(p1!=p2){
				p1 = p1.next;
				p2 = p2.next;
			}
			return true;
		}
		
		return false;
		
	}
	
	/*
	 *  How to find if nodes in LL are odd or even
	 */
	public static boolean isEven(LinkedListNode head){
		LinkedListNode current = head;
		while(current!=null && current.next!=null){
			current = current.next.next;
		}
		
		if(current==null)
			return true;
		
		return true;
		
	}
	
	
	/*
	 * http://www.geeksforgeeks.org/a-linked-list-with-next-and-arbit-pointer/
	 */
	public static LinkedListNode cloneLLWithRandomP(LinkedListNode head){
		LinkedListNode current = head;
		LinkedListNode newList = null;
		//first loop: clone the list and insert clone between the current and next node
		while(current !=null){
			LinkedListNode copy = current.clone();
			current.next = copy;
			if(newList==null)
				newList = copy;
			current = copy.next;
		}
		
		//second iteration to populate the random pointer for the clone list
		current = head;
		while(current!=null){
			current.next.random = current.random.next;
			current = current.next.next;
		}
		
		//third iteration to disconnect list and its clone
		current = head;
		while(current!=null){
			LinkedListNode copy = current.next;
			current.next = copy.next;
			current = copy.next;
			if(current!=null)
				copy.next = current.next;
		}
		
		return newList;
		
	}
	
	
	static class SortedListNode{
		int v;
		SortedListNode next;
	}
	
	/*
	 * http://www.geeksforgeeks.org/design-a-stack-with-find-middle-operation/
	 */
	
	/*
	Q4 – Insert an element into a sorted link list which is having loop somewhere and duplicate elements as well.
	*/
	public static void insertNode(SortedListNode head, int value){
		//find the looping Node
		SortedListNode loopingNode; 
		SortedListNode current = head;
		SortedListNode previous = null;
		boolean reached = false;
		while(current!=null && current.v < value){
			if(current.v<previous.v){
				//reach the looping point
				reached = true;
				break;
				
			}
			previous = current;
			current = current.next;
		}
		
		SortedListNode newNode = new SortedListNode();
		newNode.v = value;
		
		
		if(reached){
			previous.next = newNode;
			newNode.next = current;
		}
		else{
			newNode.next = current;
			if(previous!=null)
				previous.next = newNode;
			else
				head = newNode;
		}
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

	public static LinkedListNode reverseEveryK(LinkedListNode head, int k){
		if(head==null)
			return null;	
		return  reverseEveryKHelper(head, k);
		
	}
	
	/*
	 * start with a Node head, reverse k node, and return the head
	 */
	public static LinkedListNode reverseEveryKHelper(LinkedListNode head, int k){
		if(head==null)
			return null;
		
		LinkedListNode current = head;
		LinkedListNode next = null;
		int i = 0;
		
		while(i<k && current!=null){
			i++;
			next = current.next;
			LinkedListNode temp = null;
			if(next!=null){
				temp = next.next;
				next.next = current;
				current = next;
				next = temp;				
			}			
		}
		//at this moment head became tail, so set its next to next head
		head.next = reverseEveryKHelper(next, k);		
		//current is current head now
		return current;
		
	}
	
}
