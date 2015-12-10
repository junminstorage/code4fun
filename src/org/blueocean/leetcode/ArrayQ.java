package org.blueocean.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrayQ {
	
	public static class ArrayQueue<T> {
		int capacity;
		int head;
		int tail;
		int size;
		T[] data;
		
		public ArrayQueue(int c){
			this.capacity = c;
			data = (T[]) new Object[c];
		}
		
		private boolean isFull(){
			return size==capacity;
		}
		
		public boolean isEmpty(){
			return size==0;
		}
		
		public boolean offer(T num){
			if(isFull())
				return false;
			data[tail] = num;
			tail = (tail+1)%capacity;
			size++;
			return true;
		}
		
		/*
		 * return min_value if empty
		 */
		public T poll(){
			if(isEmpty())
				return null;
			
			T ret = data[head];
			data[head] = null;
			head = (head+1)%capacity;
			size--;
			return ret;
		}
		
	}
	
	public static boolean wordBreak(String[] dict, String str) {
		int len = str.length();
		/*
		 * a boolean array, hasWord[i] indicates if there is a word in the dictionary
		 * starts somewhere in the string and ends at position i
		 * e.g. hasWord[len-1] mean the string is breakable
		 */
        boolean[] hasWord = new boolean[len];
        /*
         * we starts from the beginning of string, check if there is any word which starts 
         * at the index i and ends at i+word_length-1, if yes, mark the i+word_length-1 to true
         */
        for(int i=0; i<len; i++) {
        	//we skip the i if there is no word ends at i-1
            if(i-1>=0 && !hasWord[i-1])
                continue;
            for(String word:dict) {
            	int wLen = word.length();
                if(wLen+i<=len && str.substring(i, i+wLen).equals(word))
                    hasWord[i+wLen-1] = true;
            }
        }
        return hasWord[len-1];
    }

    public static void main(String[] args) {
        String[] dict = {"cat", "dog", "san"};
        String str = "catsanddog";
        System.out.println(wordBreak(dict, str));
    }
	
	/*
	 * http://www.programcreek.com/2014/05/leetcode-minimum-size-subarray-sum-java/
	 *
	Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

	For example, given the array [2,3,1,2,4,9] and s = 7, the subarray [4,3] has the minimal length of 2 under the problem constraint.
	*/
	public static int minimumSizeArray(int[] nums, int s){
		int min = Integer.MAX_VALUE, sum = 0;
		int p1=0,p2=0,len=nums.length;
		while(p2<len && p1<len){
			sum += nums[p2];

			while(p1<len && sum >=s){
				min = Math.min(p2-p1+1, min);
				sum -= nums[p1];
				p1++;
			}
			
			p2++;	
		}
		
		return min == Integer.MAX_VALUE?0 :min;
		
	}
	
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
