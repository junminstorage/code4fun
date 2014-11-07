package org.blueocean.learning;

public class MinHeap {
	class Node {
		int value;
		int row;
		int col;
		
		Node(int v, int r, int c){
			this.value = v;
			this.row =r;
			this.col = c;
		}
	}
	
	Node[] list;
	int size;
	/*
	 * populate MinHeap with the first row
	 */
	public MinHeap(int[] sorted){
		list = new Node[sorted.length];
		size = sorted.length;
		
		for(int i=0; i<sorted.length; i++){
			list[i] = new Node(sorted[i], 0, i);
		}				
	}
	
	public int[] getChilden(int index){
		return new int[]{2*index+1, 2*index+2};
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public void add(Node n){
		if(size==list.length)
			return;
		
		list[size] = n;
		size++;
		bubbleUp(size);	
	}
	
	public void bubbleUp(int index){
		
	}
	
	public Node popMin(){
		Node min = list[0];
		if(size>1){
			list[0] = list[size-1];
			list[size-1] = null;
		}
		size--;
		bubbleDown(0);
		return min;
	}
	
	public Node getRoot(){
		return list[0];
	}
	
	public void replaceRoot(Node n){
		 list[0] = n;
		 bubbleDown(0);
	}
	
	public void swap(int i, int j){
		Node t = list[i];
		list[i] = list[j];
		list[j] = t;
	}
	
	public void bubbleDown(int index){
		int[] children = this.getChilden(index);
		int replaceIndex = index;
		
		if(children[0]<list.length && list[children[0]].value<list[index].value)
			replaceIndex = children[0];
		
		if(children[1]<list.length && list[children[1]].value<list[index].value)
			replaceIndex = children[1];		
				
		if(replaceIndex!=index){
			swap(replaceIndex, index);
			bubbleDown(replaceIndex);
		}
		
	}
}
