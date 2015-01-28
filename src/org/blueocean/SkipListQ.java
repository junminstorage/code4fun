package org.blueocean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SkipListQ {
	//maximum height of skip list, usually around 16 - 32
	private int max;
	private int level;
	//head is a dummy node will point to the first node in the list if it is not empty
	Node head;
	
	public SkipListQ(int max){
		head = new Node(max);
		this.max = max;
		level = 1;
	}
	
	class Node {
		int data;
		Node[] next;
		public Node(int l){
			this.next = new Node[l];
		}
	}
		
	//a flipping coin algorithm to generate the number between 1 and max
	public int randomLevel(int max){
		int level = 1;
		while(Math.random()<0.5 && level < max)
			level++;
		return level;
	}
	
	//random height generator between 1 and max-1
		//max height is 31
	public int randHeight(int max){
		int random = new Random().nextInt();
		boolean found = false;
		int h = 1;
		while(!found && random > 0){
			if( (random & 1) > 0)
				found = true;
			else{
				h++;
				random = random >>1;
			}
		}
		return Math.max(h, max-1);		
	}
	
	//return the value itself if found otherwise -1
	public int find(int v){
		Node curr = head;
		for(int l = level-1; l>=0; l--){
			for(; curr.next[l]!=null && curr.next[l].data<v; curr = curr.next[l]){}
		}		
		return curr.next[0]!=null && curr.next[0].data == v? curr.next[0].data : -1;
	}
	
	public void insert(int v){
		List<Node> path = new ArrayList<Node>();
		Node curr = head;
		path.add(head);
		for(int l = level-1; l>=0; l--){
			while(curr.next[l]!=null && curr.next[l].data<v){
				curr = curr.next[l];
				path.add(curr);
			}
		}
		
		if(curr.next[0]!=null && curr.next[0].data==v){
				 return;
		}else{
			//typically the node's height is random decided
			//this implementation assume the height is current height
			Node newNode = new Node(this.max);
			//insert v between curr.next[0] and path.get(path.size()-1)
			int index = 1;
			for(int l = this.level-1; l>=0; l--){
				newNode.next[l] = path.get(index).next[l];
				path.get(index).next[l] = newNode;
				index++;
			}
		}
	}
	
	///////////////////////////////////////////////
	LinkedNode h;
	class LinkedNode {
		int data;
		LinkedNode next;
		LinkedNode down;
	}
	
	public SkipListQ(){
		h = new LinkedNode();
	}
	public int find2(int v){
		LinkedNode curr = h;
		while(curr.down!=null){
			while(curr.next!=null && curr.next.data < v)
				curr = curr.next;
			curr = curr.down;
		}
		
		return curr.next!=null && curr.next.data == v? curr.next.data : -1;
	}
}
