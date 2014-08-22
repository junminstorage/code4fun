package org.blueocean.learning;
import java.util.ArrayList;
import java.util.List;


public class ListQ {
	
	class Node{
		Node next;
		int value;
	}
	
	public Node mergeTwoSortedList(Node list1, Node list2){
		if(list1==null && list2==null)
			return null;		
		Node head = new Node();
		Node tail = head;		
		while(list1!=null && list2!=null){			
			if(list1.value < list2.value){
				tail.next = list1;
				list1 = list1.next;
			}else{
				tail.next = list2;
				list2 = list2.next;
			}			
			tail = tail.next;
		}		
		if(list1 != null)
			tail.next = list1;		
		if(list2 != null)
			tail.next = list2;		
		Node result = head.next;
		head = null;
		return result;
		
	}
	
	/**
	 * spare is the 1 or 0 after adding the previous digits
	 * 
	 * @param list1
	 * @param list2
	 * @param spare
	 * @return
	 */
	public Node add2Num(Node list1, Node list2){
		Node head = null;
		Node tail = null;
		int spare = 0;
		while(list1!=null || list2!=null){
			if(list1!=null){
				spare = spare + list1.value;
				list1 = list1.next;
			}
			if(list2!=null){
				spare = spare + list2.value;
				list2=list2.next;
			}
			Node n = new Node();
			n.value = spare%10;	
			
			if(head==null){
				tail = head = n;
			}
			else{
				tail.next = n;
				tail = n;
			}
					
			spare = spare/10;				
		}
		
		if(spare>0){
			Node n = new Node();
			n.value = spare;
			tail.next = n;
		}
			
		return head;		
	}
	
	
	public boolean isCircularList(Node n){
		if(n==null)
			return false;		
		if(n.next == n)
			return true;
		
		Node p1 = n;
		Node p2 = n;
		while(p1!=null && p2!=null && p2.next !=null){
			p1 = p1.next;			
			p2=p2.next.next;					
			if(p1==p2)
				return true;
		}				
		return false;
	}
	
	public static Node getIntersectionPoint(Node list1, Node list2){
		if(list1==null || list1==null)
			return null;
		
		int l1 = getLength(list1);
		int l2 = getLength(list2);		
		Node p;
		Node q;
		
		int diff = Math.abs(l1 - l2);
		
		if(l1>l2){
			p = list1;
			q = list2;
		}else{
			p = list2;
			q = list1;
		}
		
		while(diff!=0){
			p = p.next;
			diff--;
		}
		
		while(p!=null & q!=null & p!=q){
			p = p.next;
			q = q.next;
		}		
		return p;			
	}
	
	public static int getLength(Node list){
		int count = 0;
		while(list!=null){
			list = list.next;
			count++;
		}
		return count;
	}

}
