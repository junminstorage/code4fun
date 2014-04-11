package org.blueocean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ArrayQ {
	
	/*
	 * 3sum = 0
	 * n^2
	 */
	public static int[] find3Sum(int[] nums){
		Arrays.sort(nums); 
		for(int k=0; k<nums.length-1; k++){
			int i=k+1;
			int j=nums.length-1;
			int target = 0 - nums[i];
			while(i<j){
				int sum = nums[i] + nums[j];
				if(sum == target)
					return new int[]{k, i+1, j+1};
				else if(sum>target)
					j--;
				else
					i++;
			}
		}
		
		return new int[]{0, 0, 0};
		
	}
	
	/**
	 * 2sum solution 1
	 * O(nlogn)
	 */
	public static int[] find2Sum(int[] nums, int target){
		Arrays.sort(nums); 
		int i=0;
		int j=nums.length-1;
		
		while(i<j){
			int sum = nums[i] + nums[j];
			if(sum == target)
				return new int[]{i+1, j+1};
			else if(sum>target)
				j--;
			else
				i++;
		}
			
		return new int[]{0,0};
	}
	
	/**
	 * 2sum solution 2
	 * O(n)
	 */
	public static int[] find2Sum2(int[] nums, int target){
		HashMap<Integer, Integer> found = new HashMap<Integer, Integer>();
		found.put(nums[0], 1);		
		for(int i=1; i< nums.length; i++){
			 if(found.containsKey(target - nums[i]))
				 return new int[]{found.get(target - nums[i]), i+1};
			 else
				 found.put(nums[i], i+1);
		}
			
		return new int[]{0,0};
	}
	
	
	/**
	 * find nearest larger element
	 * 
	 * @param input
	 * @return
	 */
	public static Map<Integer, Integer> findNLE(List<Integer> input){		
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		
		if(input==null || input.isEmpty())
			return result;
		
		Stack<Integer> s = new Stack<Integer>();
		s.push(input.get(0));
		for(int sCurr=1; sCurr<input.size(); sCurr++){
			while(!s.isEmpty()){
				if(s.peek()<input.get(sCurr))
					result.put(s.pop(), input.get(sCurr)); 
				else
					break;				
			}			
			s.push(input.get(sCurr));					
		}
		
		while(!s.isEmpty()){
			result.put(s.pop(), Integer.MIN_VALUE);
		}		
		return result;		
	}
}
