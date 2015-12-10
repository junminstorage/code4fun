package org.blueocean.leetcode;

public class LinkedListQ {
	
	public static class Node{
		int data;
		Node next;
	}
	
	public static Node reverse(Node head){
		Node p = head;
		Node pNext = p.next;
		Node pNNext = null;
		while(pNext!=null){
			pNNext = pNext.next;
			//reverse the pointer
			pNext.next = p;
			//move p and pNext
			p = pNext;
			pNext = pNNext;
		}
		//don't forget the head.next
		head.next = null;
		return p;
	}

}
