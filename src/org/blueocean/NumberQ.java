package org.blueocean;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class NumberQ {
	
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
