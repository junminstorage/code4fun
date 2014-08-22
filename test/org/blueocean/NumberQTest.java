package org.blueocean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

public class NumberQTest extends TestCase {
	
	public void testcalculateSqrt(){
		System.out.println(NumberQ.calculateSqrt(100));
	}
	
	public void testFindMinimumDisTwoNumbers(){
		System.out.println(NumberQ.FindMinimumDisTwoNumbers(new int[]{3, 4, 5}, 3, 5));
		System.out.println(NumberQ.FindMinimumDisTwoNumbers(new int[]{1, 5, 3, 7, 2, 8, 3, 4, 5, 9, 9, 3, 1, 3, 2, 9}, 3, 9));
		
		System.out.println(NumberQ.FindMinimumDisTwoNumbers(new int[]{2, 5, 3, 5, 4, 4, 2, 3}, 3, 2));
		System.out.println(NumberQ.FindMinimumDisTwoNumbers(new int[]{3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3}, 3, 6));
		
	}
	public void testSwapNoNegative(){
		System.out.println(NumberQ.swapNoNegative(new int[]{1,0, 0, 2, 0, 3}));
		System.out.println(NumberQ.swapNoNegative(new int[]{1, 0, 0, -6, 2, 0}));
		
	}
	
	public void testbase62Encoding(){
		System.out.println(NumberQ.base62Encoding(100));
		System.out.println(NumberQ.encode(100));
		
		System.out.println(NumberQ.base62Encoding(2_000_000_000));
		System.out.println(NumberQ.encode(2000000_000));
	}
	
	public void testbase62decoding(){
		System.out.println(NumberQ.base62decoding("2J"));
		//System.out.println(NumberQ.encode(100));
		
		System.out.println(NumberQ.base62decoding("43JwjC"));
		//System.out.println(NumberQ.encode(2000000_000));
	}
	
	public void testnumberToExcelCell(){
		System.out.println(NumberQ.numberToExcelCell(1));
		System.out.println(NumberQ.numberToExcelCell(26));
		System.out.println(NumberQ.numberToExcelCell(51));
		System.out.println(NumberQ.numberToExcelCell(52));
		System.out.println(NumberQ.numberToExcelCell(80));
		System.out.println(NumberQ.numberToExcelCell(676));
	}
	
	public void testFindMedian(){
		int[] num1 = new int[]{3, 6, 8, 11, 25};		
		int[] num2 = new int[]{4, 7, 8, 10, 15, 18, 40};		
		System.out.println(NumberQ.findMedian(num1, 0, 4, num2, 0, 6));
				
		num1 = new int[]{3};		
		num2 = new int[]{1};		
		System.out.println(NumberQ.findMedian(num1, 0, num1.length-1, num2, 0, num2.length-1));
		
		
	}
	
	public void testNumber2Binary(){
		
		System.out.println(NumberQ.number2Binary(-20));
	}
	
	public void testMinNumToRemoved(){
		//int[] nums = new int[]{4, 5, 100, 9, 10, 11, 12, 15, 200};
		System.out.println(NumberQ.minToRemoved(new int[]{4, 5, 100, 9, 10, 11, 12, 15, 200}));
		System.out.println(NumberQ.minToRemoved(new int[]{100, 8, 9, 10, 11, 12, 15, 200}));
		System.out.println(NumberQ.minToRemoved(new int[]{8, 9, 100, 10, 11, 12, 15, 200}));
		System.out.println(NumberQ.minToRemoved(new int[]{20, 4, 1, 3}));
		System.out.println(NumberQ.minToRemoved(new int[]{20, 7, 5, 6}));
		System.out.println(NumberQ.minToRemoved(new int[]{4, 7, 5, 6}));
		
	}
	public void testDivisionToString(){
		System.out.println(NumberQ.divisionToString(1, 2));
		System.out.println(NumberQ.divisionToString(1, 3));
		
		System.out.println(NumberQ.divisionToString(10, 2));
		
		System.out.println(NumberQ.divisionToString(10, 3));
		
		System.out.println(NumberQ.divisionToString(12, 5));
		
		
	}
	
	public void testAddByList(){
		
		NumberQ.Node n1 = new NumberQ.Node(1);
		NumberQ.Node n2 = new NumberQ.Node(3);
		NumberQ.Node n3 = new NumberQ.Node(9);
		n1.next = n2;
		n2.next = n3;
		
		NumberQ.Node r = NumberQ.addByLinkedList(n1);
		
	}
	
	public void testReverseList(){
		NumberQ.Node n1 = new NumberQ.Node(1);
		NumberQ.Node n2 = new NumberQ.Node(3);
		NumberQ.Node n3 = new NumberQ.Node(9);
		n1.next = n2;
		n2.next = n3;
		
		NumberQ.Node n = NumberQ.reverseList(n1);
		
	}
	
	public void testTest(){
		// int[] data = { 1, 3, 4, 5, 6, 2, 7, 8, 9, 10, 11, 13, 14, 15 };

		//new NumberQ().test(data, 15);
		
		int[] nums = {-3, -1, -2,  -4, 5, 8};
		
		new NumberQ().getSubSetsForSum(nums, 0);
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
