package org.blueocean.mergeKSortedArrays;

public class MinHeap {
	Node[] data;
	int size;
	
	public MinHeap(int k){
		size = k;
		data = new Node[k];
	}
	
	public MinHeap(int[][] nums){
		this(nums.length);
		
		for(int i = 0; i< nums.length; i++){			
			data[i] = new Node(i, 0, nums[i][0]);			
		}
		
		for(int i=0; i< nums.length/2; i++)
			this.bubbleDown(i);
	}
	
	private int getLeft(int index){
		return 2*index + 1;
	}
	
	private int getRight(int index){
		return 2*index + 2;
	}
	
	public Node getMin(){
		return data[0];
	}
	
	public void replaceRoot(Node newRoot){
		data[0] = newRoot;
		bubbleDown(0);
	}
	
	public void bubbleDown(int index){
		if(index>=size)
			return;
		int left = this.getLeft(index);
		int right = this.getRight(index);
		
		int smallest = index;
		
		if(left<size && data[smallest].value > data[left].value)
			smallest = left;
		
		if(right<size && data[smallest].value > data[right].value)
			smallest = right;
		
		if(smallest != index){
			Node temp = data[index];
			data[index] = data[smallest];
			data[smallest] = temp;						
			bubbleDown(smallest);
		}					
	}

}
