package org.blueocean.leetcode;

public class SortQ {
	
	public static int maxiDiff(int[] nums){
		int min = nums[0], max = nums[0], len = nums.length;
		for(int i = 0; i<len; i++){
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		//calculate min and max
		int interval = (max-min)/(len+1);
		int counter = (max-min)/interval+1;
		Integer[] mins = new Integer[counter];
		Integer[] maxs = new Integer[counter];
		
		for(int i = 0; i<len; i++){
			int index = (nums[i]-min)/interval;
			mins[index] = mins[index] == null ? nums[i] : (Math.min(nums[i], mins[index]));
			maxs[index] = maxs[index] == null ? nums[i] : (Math.max(nums[i], maxs[index]));
		}
		
		int maxDiff = Integer.MIN_VALUE;
		
		int pre = maxs[0];
		for(int i=1; i<counter; i++){
			if(mins[i]!=null){
				maxDiff = Math.max(maxDiff, mins[i] - pre);
				pre = maxs[i];
			}
		}
		return maxDiff;
	}

}
