package org.blueocean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class NumberQ {
	
	public static List<Integer> findKNearest(int[] nums, int found, int k){
		List<Integer> result = new ArrayList<Integer>();
		
		int index = NumberQ.findNearestIndex(nums, found);
		
		int i = index;
		int j = index + 1;
		while(result.size()<k){
			
			if(i<0 && j>nums.length-1)
				break;
			else if(j>nums.length-1){
				result.add(nums[i]);
				i--;
			}else if(i<0){
				result.add(nums[j]);
				j++;
			}else if( found - nums[i] > nums[j] - found ){
				result.add(nums[j]);
				j++;
			}else{
				result.add(nums[i]);
				i--;
			}	
			
		}
		
		return result;
		
	}
	
	public static int findNearestIndex(int[] nums, int found){
		if(nums[0]>=found)
			return 0;		
		for(int i=0; i<nums.length-1; i++){
			if(nums[i]<=found && nums[i+1]>found){
				return i;
			}				
		}		
		return nums.length-1;
	}
	
	
	public static String findCol(int n){
		StringBuffer sb = new StringBuffer();
		
		int d = 0;
		int a = 'A';
		
		while(n>0){
			d = (n-1)%26;
			sb.append((char)(d+a));
			n = (n - d)/26;
		}
		
		return sb.reverse().toString();
	}
	
	public static List<Integer> findMaxArithmeticsSeq(int[] num){
		int maxL = 0;
		List<Integer> result = new ArrayList<Integer>();
		//the difference between two numbers in the list defines the maxL of the sequence
		//store the mapping will help to reduce the number of the iterations		
		Map<Integer, Integer> diff2Length = new HashMap<Integer, Integer>();
		
		for(int i=0; i<num.length-1; i++){			
			for(int j=i+1; j<num.length; j++){
				int diff = num[j]-num[i];
				//we don't need to continue since max Length for this difference is already found
				if(diff2Length.containsKey(diff))
					continue;
				List<Integer> found = new ArrayList<Integer>();
				found.add(num[i]);
				int pre = num[i];
				for(int k=j; k<num.length; k++){
					if(num[k] - pre == diff){
						found.add(num[k]);
						pre = num[k];
					}
				}				
				diff2Length.put(diff, found.size());
				if(found.size() > maxL){
					maxL = found.size();
					result = found;
				}
			}						
		}		
		return result;
	}
	
	public static void printPermutation(int[] nums, int start){
		if(start == nums.length-1){
			System.out.println(Arrays.toString(nums));
			return;
		}
		
		for(int k=start; k<nums.length; k++){				
			swap(nums, start, k);								
			printPermutation(nums, start+1);
			swap(nums, start, k);
		}													
	}
	
	public static ArrayList<ArrayList<Integer>> printPermutation2(int[] nums){
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<Integer>());		
		for(int i=0; i<nums.length; i++){
			ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
			for(ArrayList<Integer> each : result){				
				for(int index = 0; index<=each.size(); index++){
					ArrayList<Integer> dest = new ArrayList<>(each);
					dest.add(index, nums[i]);
					temp.add(dest);
				}				
			}			
			result = temp;			
		}		
		return result;
	}
	
	
	public static List<List<Integer>> printPermutation3(List<Integer> nums){
		List<List<Integer>> result = new ArrayList<>();
		if(nums.size()==0){
			result.add(new ArrayList<Integer>());
			return result;
		}
		Integer n = nums.remove(0);
		List<List<Integer>> result2 = printPermutation3(nums);
		for(List<Integer> each : result2){				
			for(int index = 0; index<=each.size(); index++){
				ArrayList<Integer> dest = new ArrayList<>(each);
				dest.add(index, n);
				result.add(dest);
			}				
		}			
		return result;
	}
	
	
	private static void swap(int[] nums, int i, int j){
		int t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;
	}
	
	public static void printNumbersInOrder(int[] nums){
		Set<Integer> s = new TreeSet<Integer>();
		
		for(int i=0; i< nums.length; i++)
			s.add(nums[i]);
		
		//for(Integer i : s)
			//System.out.println(i+"");
		
		Integer[] result = s.toArray(new Integer[s.size()]);
		for(int k=0; k<result.length; k++)
			System.out.println(result[k]);
		
	}
	
	public static boolean ifZeroSumExit(int[] nums){
		Set<Integer> s = new TreeSet<Integer>();		
		for(int i=0; i< nums.length; i++)
			s.add(nums[i]);	
		
		Integer[] result = s.toArray(new Integer[1]);
		
		for(int k=0; k<result.length; k++){
			int l = k+1;
			int m = result.length-1;
			
			while(l<m){
				int sum = result[k] + result[l] + result[m] ;
				if(sum == 0)
					return true;
				
				if(sum > 0) 
					m--; 
				else	
					l++;
			}			
		}				
		return false;
	}
	
	
	/*
	 * printAllZeroSum2(sorted, new Stack[sorted.length], 0, 0, sum)		
	 */
	public static void printAllZeroSum2(int[] sorted, int[] stack, int stackLen, int startIndex, int target){
		if(target==0){
			if(startIndex!=0){	
				System.out.println(Arrays.toString(Arrays.copyOf(stack, stackLen)));
				return;
			}
		}
		
		if(startIndex>sorted.length-1 || sorted[startIndex]>target)
			return;
		
		while(startIndex<sorted.length && sorted[startIndex]<=target){
			stack[stackLen] = sorted[startIndex];
			printAllZeroSum2(sorted, stack, stackLen+1, startIndex+1, target-sorted[startIndex]);
			startIndex++;
		}				
	}
	
	public static void AllSubSetSum(int[] nums, int target){
		Arrays.sort(nums);		
		printAllZeroSum2(nums, new int[nums.length], 0, 0, target);
	}
	
	public void populateSubset(final int[] data, int fromIndex, 
			final int[] stack, final int stacklen,
			final int target) {
		System.out.println(" - " + fromIndex + " - " + target + " - "+ stacklen + " - " + Arrays.toString(stack));
		if (target == 0) {
			// exact match of our target. Success!
			if(fromIndex!=0){
				print(Arrays.copyOf(stack, stacklen));
				return;
			}
		}

		while (fromIndex < data.length && data[fromIndex] > target) {
			// take advantage of sorted data.
			// we can skip all values that are too large.
			fromIndex++;
			//return;
		}

		while (fromIndex < data.length && data[fromIndex] <= target) {
			// stop looping when we run out of data, or when we overflow our target.
			stack[stacklen] = data[fromIndex];
			populateSubset(data, fromIndex + 1, stack, stacklen + 1, target - data[fromIndex]);
			fromIndex++;
		}
	}

	private void print(int[] js) {    
        System.out.println(Arrays.toString(js));
    }
	
	
	public void test(int[] data, int target){		
		Arrays.sort(data); 
		System.out.println(Arrays.toString(data));
		populateSubset(data, 0, new int[data.length], 0, target);
	}
	public static int reverseNum(int num){
		assert(num<=0);		
		int result = 0;
		while(num!=0){
			result = num%10 + result*10;
			num = num/10;
		}		
		return result;
	}
	/**
	 * method use no extra space
	 * @param num
	 * @return
	 */
	public static boolean isPanlindrome(int num){
		assert(num<=0);
		
		if(num<10)
			return true;
		
		while(num>=10){
			int first =num;
			int counter=0;
			while(first>=10){
				first = first/10;
				counter++;
			}			
			int last = num%10;			
			
			if(first!=last)
				return false;
			else{
				num = (num - first*10*counter)/10;				
			}				
		}	
		
		return true;
	}
	
	
	public static int getFirstDigit(int num){
		int result=0;
		while(num!=0){
			result = num%10;
			num = num/10;
		}
		return result;
	}

	public static int reverseBits(int num, int i, int j){
		int biti = (num>>i)&1;
		int bitj = (num>>j)&1;
		
		if((biti^bitj)==1){
			num = (1<<j)^num;
			num = (1<<i)^num;
		}
		return num; 		
	}
	
	public static int reverseBits(int num){
		int size =  8 * 4 ;
		for(int i=0; i<size/2; i++){
			num = reverseBits(num, i, size-i-1);
		}		
		return num;
	}
	
	public static int reverseBits2(int num){
		int b=0;	
		int counter = 31;
		while(counter!=0){
			b|=(num&1);		
			num >>=1;
			b = b<<1;
			counter--;
		}
		return b;
		
	}
	
	/**
	 *  Given an array that has positive numbers and negative numbers and zero in it. 
	 *  You need to seperate the negative numbers and positive numbers in such a way that 
	 *  negative numbers lies to left of zero 
	 *  and positive numbers to the right and the original order of elements should be maintained
	 * @param num
	 */
	public static void orderByZero(int[] num){
		
		for(int i = 1 ; i< num.length; i++){
			int j = i ;			
			while(j>0){
				if((num[j]<0 && num[j-1]>=0) || (num[j]==0 && num[j-1]>0)){
					int t = num[j];
					num[j] = num[j-1];
					num[j-1] = t;
					j--;
				}else{				
					break;
				}
			}			
		}
	}
	
public static void orderByZero2(int[] num){
	System.out.println(Arrays.toString(num));
	
	int neg = -1;
	int pos = num.length;
	
	int i = 0;
			//assume there is one zero
			while(neg<pos-1 && i < num.length){
				
				if((num[i]<=0)){
					neg++;
					int t = num[neg];
					num[neg] = num[i];
					num[i] = t;
					
				}
				
				//if((num[i]>0)){	
				//	pos--;
				//	int t = num[pos];
				//	num[pos] = num[i];
				//	num[i] = t;
					
				//}
				
				i = neg + 1;
				//System.out.println(Arrays.toString(num));				
			}					
	}
		
	public static boolean isPrimary(int num){
		if(num == 0 || num ==1 )
			return false;
		if(num == 2 || num == 3)
			return true;		
		
		for(int i = 2; i< (int)Math.sqrt(num)+1; i++){
			if(num%i==0)
				return false;
		}
		return true;		
	}
	
	/**
	 * num (num-1)!
	 * @param k
	 * @param num
	 * @return
	 */
	public static boolean isDivisible(int k, int num){
		if(num ==1 )
			return true;
		if(k>=num)
			return true;		
		
		if((num==2 || num==3 || num==4))
			if(k<num)
				return false;
		
		for(int i = 2; i< (int)Math.sqrt(num)+1; i++){
			if(num%i==0)
				return true;
		}
		return false;		
	}
	
	public static int findMaxOnes(int[][] nums){
		int max = nums[0].length-1;
		int index = 0;
		
		for(int i = 0; i<nums.length; i++){
			for(int j = max; j>=0; j--){
				if(nums[i][j]==0)
					break;
				max = j;
				index = i;
			}			
		}
		
		return index;
	}

}
