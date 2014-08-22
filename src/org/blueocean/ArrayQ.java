package org.blueocean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class ArrayQ {
	
	public static int findInRotatedSorted(int[] num, int k, int start, int end){
		if(start<0 || end >=num.length)
			return -1;
		if(start>end)
			return -1;
		
		int mid = (start + end)/2;
		if(num[mid] == k)
			return mid;
		
		if(num[mid] > num[start]){
			if(num[mid] > k && num[start] <= k){
				return binarySearch(num, k, start, mid);
			}else if(num[mid] < k){
				return findInRotatedSorted(num, k, mid, end);
			}else{
				return findInRotatedSorted(num, k, mid, end);
			}				
		}
		
		if(num[mid] < num[end]){
			if(num[mid] < k && num[end]>=k){
				return binarySearch(num, k, mid, end);
			}else
				return findInRotatedSorted(num, k, start, mid);
		}	
		
		return -1;
	}
	
	public static int binarySearch(int[] num, int k, int start, int end){
		if(start<0 || end >=num.length)
			return -1;
		if(start>end)
			return -1;
		
		int mid = (start + end)/2;
		if(num[mid] == k)
			return mid;
		else if(num[mid] < k){
			return binarySearch(num, k, mid+1, end);
		}else{
			return binarySearch(num, k, start, mid-1);
		}
		
	}
	
	public static int findMaxCostPath(int[][] nums){
		int[][] path = new int[nums.length][nums[0].length];
		System.out.println(nums.length + " - " + nums[0].length);
		for(int i=0; i<nums.length; i++){			
			for(int j=0; j<nums[0].length; j++){
				int max = Math.max((i-1>=0)?path[i-1][j]:Integer.MIN_VALUE , (j-1>=0)?path[i][j-1]:Integer.MIN_VALUE );
				path[i][j] = (max==Integer.MIN_VALUE?0:max) + nums[i][j];	
			}			
		}	
		StringBuilder sb = new StringBuilder();
		int i = nums.length-1; int j = nums[0].length-1;
		while(i>0 || j >0){
				if(i==0){
					sb.append("R");
					j--;
				}else if(j==0){
					sb.append("D");
					i--;
				}
				else if(path[i-1][j] > path[i][j-1]){
					sb.append("D");
					i--;					
				}
				else{
					sb.append("R");
					j--;
				}
		}
	
		System.out.println(sb.reverse().toString());
		
		return path[nums.length-1][nums[0].length-1];
	}
	
	/*
	 * can only move right or down
	 */
	public static Set<String> printPathInAGrid(int x, int y){
		Map<String, Integer> result = new HashMap<String, Integer>();
		result.put("", 0);
		
		//RRDDD => 2*3
		for(int i=1; i<=x+y; i++){
			Map<String, Integer> temp = new HashMap<String, Integer>();
			for(String s : result.keySet()){
				//System.out.println(s);
				Integer numberOfRs = result.get(s);
				if(numberOfRs<x)
					temp.put(s + "R", numberOfRs + 1);
				if(s.length()-numberOfRs<y)
					temp.put(s + "D", numberOfRs);
			}
			
			result = temp;
		}					
		return result.keySet();
	}
	
	/*
	 * can only move right or down
	 */
	public static int numberOfPathInAGrid(int x, int y){
		int[][] result = new int[x+1][y+1];
		
		for(int i=1; i<=x; i++){
			for(int j = 1; j<=y; j++){
				if(i==1 || j == 1)
					result[i][j] = 1;
				else
					result[i][j] = result[i-1][j] + result[i][j-1];				
			}					
		}					
		return result[x][y];
	}
	
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
