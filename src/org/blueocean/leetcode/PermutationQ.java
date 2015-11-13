package org.blueocean.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationQ {

	/*
	 * assume k is valid between 1 and n!
	 * 
	 * the idea behind it is based on swapping algorithm
	 * e.g. 1, 2, 3
	 * first swap: 1**  2**, 3**
	 * 
	 * if k=1 or 2, then the result is coming from 1**, 
	 * k=3,4, then result comes from 2**
	 * k=5,6, then result comes from 3**
	 * 
	 * so this is based on k/(n-1)!
	 * 
	 */
	public static List<Integer> permutationByRank(int n, int k){
		List<Integer> list = new ArrayList<Integer>();
		for(int i=1; i<=n; i++)
			list.add(i);
		int size = factor(n-1);
		return permuByRankUtil(list, k-1, size);
	}
	
	/*
	 * here k starts at 0 to n!-1 for easy computing purpose
	 */
	public static List<Integer> permuByRankUtil(List<Integer> nums, int k, int f){
		int size = nums.size();
		if(size==1)
			return nums;
		else{
			int index = k/f;
			int first = nums.remove(index);
			List<Integer> re = permuByRankUtil(nums, k%f, f/(size-1));
			re.add(0, first);
			return re;
		}
	}
	
	public static int factor(int n){
		int k = 1;
		for(int i=1; i<=n; i++){
			k = k*i;
		}
		return k;
	}
	
	public static void permutation(){
		int[] nums = new int[]{1, 2, 3, 4, 5};	
		List<int[]> current = new ArrayList<int[]>();
		current.add(nums);
		for(int i=0; i<nums.length-1; i++){
			List<int[]> temp = new ArrayList<int[]>(current);
			for(int j=i+1; j<nums.length; j++){
				for(int[] each : current){
					//swap i and j
					int[] t = swap(each, i, j);
					if(t[2]!=4)
						temp.add(t);
				}	
			}
			current = temp;
		}
	}

	private static int[] swap(int[] each, int i, int j) {
		int[] copy = Arrays.copyOf(each, each.length);
		int t = copy[i];
		copy[i] = copy[j];
		copy[j] = t;
		return copy;
	}

}
