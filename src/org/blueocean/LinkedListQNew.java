package org.blueocean;

import java.util.HashSet;
import java.util.Set;

public class LinkedListQNew {

	public class Node{
		Object data;
		Node next;
	}
	
	
	public Node findCyclePoint(Node head){
		if(head==null)
			return null;
		Node slow = head, fast = head.next;
		while(slow!=fast && fast!=null && fast.next!=null){
			slow = slow.next; fast = fast.next.next;
		}
		
		fast = head;
		while(slow!=fast && slow!=null){
			slow = slow.next; fast = fast.next;
		}		
		return fast;
	}
	
	
	
	public Node reverseSLLEveryK(Node head, int k){
	    if(head == null)
	        return null;
	        
	    Node current = head;
	    Node tail = null;
	    Node newHead = null;
	    while(current!=null){
	    Node preTail = current;

	    //reverse k nodes
	    Node next = current.next;
	    int counter = 0;
	    while(next!=null && counter++<k-1){
	        tail = next;
	        Node nextN = next.next;
	     
	        next.next = current;
	        current = next;       
	        next = nextN;          
	    }
	    
	    newHead = current;
	    preTail.next = next; //make connection between segments
	    current = next;
	    }
	    
	    return newHead;//this is the new head which was tail
	}

	public Node reverseSLL(Node head){
	    if(head == null)
	        return null;
	        
	    Node current = head;
	    Node next = current.next;
	    while(next!=null){
	        Node nextN = next.next;
	     
	        next.next = current;
	        current = next;
	        next = nextN;          
	    }
	    
	    head.next = null;
	    return current;//this is the new head which was tail
	}

	public Node getKthToLast(Node head, int k){
	    if(k<0 || head == null)
	        return null;
	        
	    Node p = head;
	    for(int i = 0; i<k && p!=null; i++)
	        p = p.next;
	        
	    if(p==null)
	        return null;
	        
	    Node p1 = head;
	    while(p.next!=null){ //not tail
	        p1 = p1.next; 
	        p = p.next;
	    }        
	    
	    return p1;
	}

	public void removeDup(Node head){
	    Set table = new HashSet();
	    Node pre = null;
	    for(Node p = head; p!=null; p=p.next){
	        if(table.contains(p.data)){
	            pre.next = p.next;
	        }else{
	            pre = p;
	            table.add(p.data);
	        }
	    }
	}

	public void removeDup2(Node head){
	    for(Node p = head; p!=null; p=p.next){
	        Node pre = p;
	        for(Node runner = p.next; runner!=null; runner=runner.next){
	            if(runner.data == p.data)
	                pre.next = runner.next;
	            else{
	                pre = runner;
	            }    
	        }       
	    }
	}
}
