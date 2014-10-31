package org.blueocean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class ArrayQTest extends TestCase {
	List<Integer> test;
	/*public void setUp(){
		Integer[] input = {3, 5, 8, 1, 9};
		test = Arrays.asList(input);
	}*/
	
	public void testprintMatrixDiagonally(){
		int[][] nums = new int[][]{{1, 4, 5}, {13, 4, 6}, {13, 2, 1}};
		ArrayQ.printMatrixDiagonally(nums);
		
	} 
	
	public void testfindSumSubSquare(){
		int[][] nums = new int[][]{{1, 4, 5}, {13, 4, 6}, {13, 2, 1}};
		ArrayQ.findSumSubSquare(nums, 2);
		ArrayQ.findSumSubSquare2(nums, 2);
	}
	
	public void testprint2DArraySpiral(){
		int[][] nums = new int[][]{{1, 4, 5}, {13, 4, 6}, {13, 2, 1}};
		ArrayQ.print2DArraySpiral(nums);
		
		int[][] nums2 = new int[][]{{1, 4, 5}, {13, 4, 6}};
		ArrayQ.print2DArraySpiral(nums2);
	}
	public void testbinarySearch(){
		int[] nums = new int[]{2, 4, 6, 8, 9, 10, 12};
		System.out.println(ArrayQ.binarySearch(nums, 7, 0, nums.length-1));
		System.out.println(ArrayQ.binarySearch(nums, 8, 0, nums.length-1));
		System.out.println(ArrayQ.binarySearch(nums, 2, 0, nums.length-1));
		
	}
	
	public void testfindInRotatedSorted(){
		int[] nums = new int[]{10, 12, 2, 4, 6, 8, 9};
		System.out.println(ArrayQ.findInRotatedSorted(nums, 7, 0, nums.length-1));
		System.out.println(ArrayQ.findInRotatedSorted(nums, 8, 0, nums.length-1));
		System.out.println(ArrayQ.findInRotatedSorted(nums, 12, 0, nums.length-1));
		System.out.println(ArrayQ.findInRotatedSorted(nums, 9, 0, nums.length-1));
		
	}
	
	public void testfindMaxCostPath(){
		int[][] nums = new int[][]{{1, 4, 5}, {13, 4, 6}, {13, 2, 1}};
		
		System.out.println(ArrayQ.findMaxCostPath(nums));
	}
	
	public void testPrintPathInAGrid(){
		System.out.println(ArrayQ.printPathInAGrid(1, 2));		
		System.out.println(ArrayQ.printPathInAGrid(2, 3));		
	}
	
	public void testnumberOfPathInAGrid(){
		System.out.println(ArrayQ.numberOfPathInAGrid(1, 2));
		
		System.out.println(ArrayQ.numberOfPathInAGrid(3, 4));
		
		System.out.println(ArrayQ.numberOfPathInAGrid(4, 5));
		
	}
	
	public void testFindNLE(){
		Integer[] input = {3, 5, 8, 1, 9};
		test = Arrays.asList(input);
		Map<Integer, Integer> result = ArrayQ.findNLE(test);		
		System.out.println(result.toString());
		
		Integer[] inputinput = {3, 5, 38, 1, 9};
		test = Arrays.asList(inputinput);
		result = ArrayQ.findNLE(test);		
		System.out.println(result.toString());
		
		test = Arrays.asList(new Integer[]{5, 0, 8, 3, 17, 9, 9});
		result = ArrayQ.findNLE(test);		
		System.out.println(result.toString());
	}
}
