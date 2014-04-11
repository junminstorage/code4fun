package org.blueocean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

public class NumberQTest extends TestCase {
	
	
	public void testFindMaxArithmeticsSeq(){
		int[] nums = {1, 2, 5, 3, 4, 8, 5};
		
		System.out.println(NumberQ.findMaxArithmeticsSeq(nums));
		
	}
	
	public void testPrintPermutation(){
		int[] nums = {13, 4, 8};
		
		NumberQ.printPermutation(nums, 0);
	}
	
	public void testPrintPermutation2(){
		int[] nums = {13, 4, 8};
		
		ArrayList<ArrayList<Integer>>  result = NumberQ.printPermutation2(nums);
		
		for(ArrayList<Integer> item: result)
			System.out.println(item);
	}
	
	public void testPrintPermutation3(){
		Integer[] nums = {13, 4, 8, 1};
		List<Integer> numbers = new ArrayList<>(Arrays.asList(nums));
		
		List<List<Integer>> result = NumberQ.printPermutation3(numbers);
		
		for(List<Integer> item: result)
			System.out.println(item);
	}
	
	
	public void testPrintNumbersInOrder(){
		int[] nums = {10, 2, 13, 4, 8};
		
		NumberQ.printNumbersInOrder(nums);
		
		int[] nums3 = {-10, 18, 12, 4, -8};
		
		NumberQ.printNumbersInOrder(nums3);
		
	}
	
	public void testIfZeroSumExit(){
		int[] nums = {10, 2, 13, 4, 8};
		
		Assert.assertFalse(NumberQ.ifZeroSumExit(nums));
		
		int[] nums2 = {-10, -2, 12, 4, 8};
		
		Assert.assertTrue(NumberQ.ifZeroSumExit(nums2));
		
		int[] nums3 = {-10, 18, 12, 4, -8};
		
		Assert.assertTrue(NumberQ.ifZeroSumExit(nums3));
	}
	
	public void testIsPalindrome(){
		Assert.assertFalse(NumberQ.isPanlindrome(10));
		Assert.assertTrue(NumberQ.isPanlindrome(11));
		Assert.assertTrue(NumberQ.isPanlindrome(101));
		
		Assert.assertTrue(NumberQ.isPanlindrome(1));
		
	}
	
	public void testReverseBits(){
		
		System.out.println(NumberQ.reverseBits(-21));
		System.out.println(NumberQ.reverseBits2(-21));
		
		System.out.println(Integer.reverse(-21));
				
	}

}
