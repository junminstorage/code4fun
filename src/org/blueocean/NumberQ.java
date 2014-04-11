package org.blueocean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class NumberQ {
	
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
	
}
