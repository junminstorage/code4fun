package org.blueocean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

public class NumberQTest extends TestCase {
	
	public void testTest(){
		// int[] data = { 1, 3, 4, 5, 6, 2, 7, 8, 9, 10, 11, 13, 14, 15 };

		//new NumberQ().test(data, 15);
		
		int[] nums = {-3, -1, -2,  -4, 5, 8};
		
		new NumberQ().test(nums, 0);
	}
	
	public void testPrintAllZeroSum(){
		int[] nums = {-1, -2, -3, -4, 5, 8};
		
		NumberQ.AllSubSetSum(nums, 0);
	
	}
	public void testFindMaxOnes(){
		int[][] nums = {{0,0,1,1}, {0,1,1,1},{0,0,0,1}, {1,1,1,1}};
		
		System.out.println(NumberQ.findMaxOnes(nums));
	}
	
	public void testOrderByZero(){
		int[] nums = {1, 2,  3, 4, 5, 8};
		NumberQ.orderByZero(nums);
		System.out.println(Arrays.toString(nums));
		
		int[] nums2 = {1, 2, 0, 3, 4, 5, 8};
		NumberQ.orderByZero(nums2);
		System.out.println(Arrays.toString(nums2));
		
		int[] nums3 = {1, 2, 0, -1, 3, 4, 5, 8};
		NumberQ.orderByZero(nums3);
		System.out.println(Arrays.toString(nums3));
		
	}
	
	public void testOrderByZero2(){
		int[] nums = {1, 2,  3, 4, 5, 8};
		NumberQ.orderByZero2(nums);
		System.out.println(Arrays.toString(nums));
		
		int[] nums2 = {1, 2, 0, 3, 4, 5, 8};
		NumberQ.orderByZero2(nums2);
		System.out.println(Arrays.toString(nums2));
		
		int[] nums3 = {1, 2, 0, -1, 3, 4, 5, 8};
		NumberQ.orderByZero2(nums3);
		System.out.println(Arrays.toString(nums3));
		
		int[] nums4 = {1, 2, 0, -1, -2, 3, 4, -4, 5, 8};
		NumberQ.orderByZero2(nums4);
		System.out.println(Arrays.toString(nums4));
		
	}
	
	public void testIsPrimary(){
		assertTrue(NumberQ.isPrimary(3));
		assertTrue(NumberQ.isPrimary(2));
		assertTrue(NumberQ.isPrimary(5));
		
		assertFalse(NumberQ.isPrimary(4));
		assertFalse(NumberQ.isPrimary(6));
		assertTrue(NumberQ.isPrimary(23));
		assertFalse(NumberQ.isPrimary(24));
		assertFalse(NumberQ.isPrimary(25));
		
	}
	
	public void testFindKNearest(){
		int[] nums = {1, 2,  3, 4, 5, 8};
		
		System.out.println(NumberQ.findKNearest(nums, 6, 4));
		System.out.println(NumberQ.findKNearest(nums, 1, 4));
		System.out.println(NumberQ.findKNearest(nums, 100, 4));
		System.out.println(NumberQ.findKNearest(nums, 1, 40));
		System.out.println(NumberQ.findKNearest(nums, 6, 1));
			
	}
	
	public void testfindCol(){
		System.out.println(NumberQ.findCol(1));
		System.out.println(NumberQ.findCol(26));
		
		System.out.println(NumberQ.findCol(52));
		
		System.out.println(NumberQ.findCol(26*27));
	}
	
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
