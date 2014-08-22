package org.blueocean.mergeKSortedArrays;

public class Driver {
	
	public static void mergeKSortedArrays(int[][] nums){
		int[] result = new int[nums.length*nums[0].length];
		
		MinHeap mh = new MinHeap(nums);
		
		for(int i = 0; i< result.length; i++){
			Node root = mh.getMin();
			result[i] = root.value;
			
			root.posInArray++;
			if(root.posInArray>=nums[0].length)
				root.value = Integer.MAX_VALUE;
			else
				root.value = nums[root.arrayIndex][root.posInArray];
			mh.replaceRoot(root);			
		}		
	}

}
