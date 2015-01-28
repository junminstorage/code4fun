package org.blueocean.learning;

import java.util.HashMap;
import java.util.Map;

public class ArrayQ {
	private final Map<String, String> states;
	
	public ArrayQ(){		
		states = new HashMap<String, String>();
	
	}
	
	private void test(){
		
	}
	
	/*
	 * http://www.geeksforgeeks.org/kth-smallest-element-in-a-row-wise-and-column-wise-sorted-2d-array-set-1/
	 * Given an n x n matrix, where every row and column is sorted in non-decreasing order. 
	 * Find the kth smallest element in the given 2D array.
	 */
	public static int findKthMinin2D(int[][] nums, int k){
		
		MinHeap heap = new MinHeap(nums[0]);
		
		int i = 0;
		MinHeap.Node min = null;
		while(i<k){
			min = heap.getRoot();
			MinHeap.Node n; 
			if(min.row+1<nums.length){
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
