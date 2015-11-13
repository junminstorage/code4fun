package org.blueocean.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrayQ {
	
	/**
	 * 3 1 2 2 1
	 * 5/3 = 2
	 * re1 3 1
	 * re2 1 1
	 * 
	 * 
	 * @param nums
	 * @return
	 */
	public static List<Integer> majority2(int[] nums){
		int c1 = 0, r1 = nums[0], c2 = 0, r2=nums[0];
		for(int n : nums){
			if(r1 == n)
				c1++;
			else if(r2 == n)
				c2++;
			else
				c1--;c2--;
			
			if(c1==0){
				r1=n;c1=1;
			}
			else if(c2==0){
				r2=n;c2=1;
			}
		}
		c1=c2=0;
		for(int n:nums){
			if(n==r1)
				c1++;
			if(n==r2)
				c2++;
		}
		
		List<Integer> result = new ArrayList<Integer>();
		if(c1>nums.length/3)
			result.add(r1);
		if(c2>nums.length/3)
			result.add(r2);
		return result;
	}
	
	

	/*
	 * 1 3 4 5 1 1 1
	 */
	public static int majority(int[] nums){
		int counter = 0, result = nums[0];
		for(int n : nums){
			if(n == result){
				counter++;
			}else
				counter--;
			
			if(counter==0){
				result = n;
				counter = 1;
			}
		}
		
		return result;
	}
	
	
	/*
	 * Given an array S of n integers, are there elements a, b, c, and d in S 
	 * such that a + b + c + d = target? 
	 * Find all unique quadruplets in the array which gives the sum of target.
	 */
	
	public static void fourSum(int[] nums, int target){
		Arrays.sort(nums);
		Set<List<Integer>> result = new HashSet<List<Integer>>();
		int len = nums.length;
		for(int i=0; i<len; i++){
			for(int j=i+1; j<len; j++){
				int k = j+1, l=len-1;
				while(k<l){
					int sum = nums[i] + nums[j] + nums[k] + nums[l];
					if(sum < target)
						k++;
					else if(sum > target)
						l--;
					else{
						result.add(Arrays.asList(new Integer[]{nums[i], nums[j], nums[k], nums[l]}));
						k++; l--;
					}
				}				
			}
		}
		
		System.out.println(result);
	}
	
	
	/*
	 * Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. 
Please note that your returned answers (both index1 and index2) are not zero-based.
	 */
	public static class Item implements Comparable<Item> {
		int data;
		int index;
		public Item(int i, int i2) {
			this.data = i; this.index = i2;
		}
		@Override
		public int compareTo(Item o) {
			return Integer.compare(this.data, o.data);
		}
		
	}
	/*
	 * this method first sort the nums, then using two pointers left and right
	 * left starts with 0 and goes right, right starts with len-1 and goes left
	 * compare the sum with target, if
	 * sum>target, move left pointer to right
	 * sum<target, move right pointer to left
	 * sum==target, return the indices
	 */
	public static int[] twoSum(int[] nums, int target){
		int len = nums.length;
		Item[] items = new Item[len];
		for(int i = 0; i<len; i++){
			items[i] = new Item(nums[i], i);
		}
		Arrays.sort(items);
		
		int i=0,j=len-1;
		while(i<j){
			int sum = nums[i] + nums[j];
			if(sum<target)
				i++;
			else if(sum>target)
				j--;
			else
				return new int[]{i+1,j+1};
		}
				
		return null;
	}

	public static int[] twoSumWithHash(int[] nums, int target){
		/*
		 * store number and its index in the array
		 */
		Map<Integer, Integer> store = new HashMap<Integer, Integer>();
		for(int i=0; i<nums.length; i++){
			if(store.containsKey(target - nums[i])){
				return new int[]{store.get(target - nums[i])+1, i+1};
			}else{
				/*
				 * this may overwrite duplicate's index, but it is ok
				 */
				store.put(nums[i], i);
			}
		}
		
		return null;
		
	}
	
}
