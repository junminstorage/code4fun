package org.blueocean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class FacebookQ {
	//http://www.careercup.com/question?id=9333968
	
	//find the min index of the item in array which large than k
	public static int binarySearch(int[] nums, int start, int end, int k){
		while(end-start>1){
			int mid = (end+start)>>>1;
			if(nums[mid]>k)
				end = mid;
			else
				start = mid;
		}		
		return nums[start]>k? start : end;
	}
	
	//Dynamic programming
	/*
	 * the idea is that the final sorted array will always end up with one of the original numbers
	 * appear in the array. then all of the larger numbers before this number will be decremented, 
	 * dp(i, j), i is the index of the original array a[], j is the index of the sorted array b[] of unique 
	 * numbers in the array
	 * 
	 * dp(i, j) = min(dp(i-1, 0), dp(i-1, 2), ... dp(i-1, j)) + cost_of_convert(a[i], b[j])
	 * 
	 * final answer: min(dp(a.len-1, 0), dp(a.len-1, 1)..., dp(a.len-1, b.len-1)
	 */
	public static int minSortCostDP(int[] nums){
		Set<Integer> unique = new HashSet<Integer>();
		for(int i:nums)
			unique.add(i);
		Set<Integer> sortedSet = new TreeSet<Integer>(unique);
		Integer[] sorted = (Integer[]) sortedSet.toArray();
		
		int lenA = nums.length,  lenB = sorted.length;
		int[][] table = new int[lenA][lenB];
		
		//first row
		for(int col = 0; col<lenB; col++)
			table[0][col] = Math.max(0, nums[0]-sorted[col]);
		
		for(int row = 1; row<lenA; row++){
			for(int col = 0; col<lenB; col++){
				//either deletion or decremention
				int cost_of_convert = nums[row]>=sorted[col]? nums[row]-sorted[col]:nums[row];
				table[row][col] = Integer.MAX_VALUE;
				for(int k=0; k<=col; k++){
					table[row][col] = Math.min(table[row-1][k]+cost_of_convert, table[row][col]);
				}
			}			
		}
		int minCost = Integer.MAX_VALUE;
		//last row
		for(int col = 0; col<lenB; col++){
			minCost = Math.min(table[lenA-1][col], minCost);
		}
		return minCost;
	}
	
	
	//greedy algorithm
	public static int minSortCost(int[] nums){
		int cost = 0;
		int start = 0;
		int end = 1;
		int len = nums.length;
		
		while(end<len){
			while(end<len && nums[end]>=nums[end-1]){
				end++;
			}
			
			if(end<len){
				int index = binarySearch(nums, start, end-1, nums[end]);
				int cost1 = nums[end];//deletion
				int cost2 = 0;
				for(int j = index; j<=end-1; j++)
					cost2 += nums[j]-nums[end];//decrement
				if(cost1<=cost2){
					//delete item at index of end
					int[] newCopy = new int[len-1];
					System.arraycopy(nums, start, newCopy, 0, end-start);
					if(end+1<len)
						System.arraycopy(nums, end+1, newCopy, end-start-1, len-1-end);
					nums = newCopy;
					len = newCopy.length;
					cost += cost1;
				}else{
					for(int j = index; j<=end-1; j++)
						nums[j] = nums[end];
					cost += cost2;
				}
			}
			
			end++;
		}
		
		return cost;
		
	}

}
