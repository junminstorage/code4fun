package org.blueocean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.TestCase;

public class ArrayQTest extends TestCase {
	List<Integer> test;
	/*public void setUp(){
		Integer[] input = {3, 5, 8, 1, 9};
		test = Arrays.asList(input);
	}*/
	
	
	public void testTest2(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		Object[] arr = (Object[])list.toArray();
		System.out.println(Arrays.toString(arr));
		
		Integer[] arr2 = (Integer[])list.toArray(new Integer[0]);
		System.out.println(Arrays.toString(arr2));
		
		Integer[] array = new Integer[2];
		array[0] = 1;
		array[1] = 2;
		
		//list2 doesn't support add or remove element
		List<Integer> list2 = Arrays.asList(array);
		System.out.println(list2);
		
		List<Integer> list3 = new ArrayList(Arrays.asList(array));
		System.out.println(list3);
		
		
	}
	
	public void testsdsd(){
		float j = 1.0f;
		for(int k = 1; k<10; k++){
			j *= (float)(10000 - k)/10000;
		}
		System.out.println(j);
		
		System.out.println(Math.pow(2, 16));
	}
	
	public void testsf2(){
		Set<Integer> s = new TreeSet<Integer>();
		Integer s1 = 1111;
		Integer s2 = 1111;
		s.add(s1);
		s.add(s2);
		System.out.println(s1==s2);
		System.out.println(s1.hashCode() == s2.hashCode());
		System.out.println(s1.equals(s2));
		System.out.println("dfsf" +s.size());
	}
	
	public void testsf(){
		Set<String> s = new TreeSet<String>();
		String s1 = new String("abc");
		String s2 = new String("abc");
		s.add(s1);
		s.add(s2);
		System.out.println(s1==s2);
		System.out.println(s1.hashCode() == s2.hashCode());
		System.out.println(s1.equals(s2));
		System.out.println("dfsf" +s.size());
	}
	
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
