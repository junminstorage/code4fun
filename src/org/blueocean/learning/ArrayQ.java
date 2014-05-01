package org.blueocean.learning;

public class ArrayQ {
	
	public static int findKthMinin2D(int[][] nums, int k){
		MinHeap heap = new MinHeap(nums[0]);
		
		int i = 0;
		MinHeap.Node min = null;
		while(i<k){
			min = heap.getRoot();
			MinHeap.Node n; 
			if(min.row+1<nums.length){
				//System.out.println(min.row);
				//System.out.println(min.col);
				n =  heap.new Node(nums[min.row+1][min.col], min.row+1, min.col);
			}
			else{
				n = heap.new Node(Integer.MAX_VALUE, min.row+1, min.col);
			}
			heap.replaceRoot(n);			
			i++;
		}		
		return min.value;
	}

}
