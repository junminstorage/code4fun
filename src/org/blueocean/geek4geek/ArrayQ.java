package org.blueocean.geek4geek;

public class ArrayQ {
	
	public static int maxTrapWater2(int[] levels){
		int len = levels.length;
		
		int[] leftMax = new int[len];
		leftMax[0] = levels[0];
		
		int[] rightMax = new int[len];
		rightMax[len-1] = levels[len-1];
		
		for(int i=1; i<len; i++)
			leftMax[i] = Math.max(levels[i], leftMax[i-1]);
		
		for(int i = len-2; i>=0; i--)
			rightMax[i] = Math.max(levels[i], rightMax[i+1]);
		
		int sum = 0;
		for(int i=0; i<len; i++){
			sum += Math.min(leftMax[i], rightMax[i]) - levels[i];
		}
		
		return sum;
	}
	/*
	 * http://www.geeksforgeeks.org/trapping-rain-water/
	 * 
	 * the key here is to find the dividers which can trap water between them
	 * the characteristic of the dividers are they are taller than the levels between them
	 * 
	 * and note the first and last levels are always the dividers
	 */
	public static int maxTrapWater(int[] levels){
		int len = levels.length;
		/*
		 * an array for each index i, it contains the index of element 
		 * which is larger than current levels at index i
		 */
		int[] nextLarger = new int[len];
		/*
		 * there is no element on the right, so set it to len
		 */		
		nextLarger[len-1] = len;
		/*
		 * go from right to left, for each element in the array
		 * find the closest element on the right which is bigger
		 * if there is none, then set its value to len
		 */
		for(int i=len-2; i>=0; i--){
			int p = i+1;
			while(p<len && levels[i]>=levels[p])
				p = nextLarger[p];
			nextLarger[i] = p;
		}
		
		boolean[] isDivider = new boolean[len];
		isDivider[0] = true;
		isDivider[len-1] = true;
		int p = 0;
		while(p<len){
			int next = nextLarger[p];
			if(next == len){//levels[p] is the tallest compared to any level on the right
				isDivider[p] = true;
				p++;
			}else{
				if(isDivider[p])//levels[p] is divider, level[next] taller than that, then it is divider too
					isDivider[next] = true;
				p = next;
			}
		}
		
		/*
		 * go through dividers to calculate the water volume between them
		 */
		int pre = levels[0];	
		int preIndex = 0;
		int sum = 0;
		for(int i=1; i<len; i++){
			if(isDivider[i]){
				sum += Math.min(levels[i], pre) * (i-preIndex-1);
				preIndex = i;
				pre = levels[i];
			}else
				sum -= levels[i];
		}
		
		return sum;

	}
}
