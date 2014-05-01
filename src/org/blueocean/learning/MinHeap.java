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
	/*
	 * populate MinHeap with the first row
	 */
	public MinHeap(int[] sorted){
		list = new Node[sorted.length];
		
		for(int i=0; i<sorted.length; i++){
			list[i] = new Node(sorted[i], 0, i);
		}				
	}
	
	public int[] getChilden(int index){
		return new int[]{2*index+1, 2*index+2};
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
		if(children[0]>=list.length)
			return;
		if(children[1]>=list.length){
			if(list[children[0]].value < list[index].value)
				swap(children[0], index);				
			return;			
		}
		
		if(Math.min(list[children[0]].value, list[children[1]].value) < list[index].value){
			int index2 = list[children[0]].value < list[children[1]].value ? children[0] : children[1];
			swap(index2, index);
			bubbleDown(index2);
		}
		
		return;
	}
}
