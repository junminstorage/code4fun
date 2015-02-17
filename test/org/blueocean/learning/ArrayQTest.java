package org.blueocean.learning;

import java.util.Arrays;

import junit.framework.TestCase;

public class ArrayQTest  extends TestCase {
	
	public void testTest(){
		int[] d = new int[]{1, 2};
		d[0] = 3;
		System.out.println(Arrays.toString(d));
		d[0] = 2;
		System.out.println(Arrays.toString(d));
		d = new int[5];
		
		Integer i = 222;
		Integer j = 222;
		
		System.out.println(i == j);
		
		System.out.println(i.hashCode());
		System.out.println(j.hashCode());
	}

	public void testFindKMin(){
		
		
		int[][] mat = { {10, 20, 30, 40},
                {15, 25, 35, 45},
                {25, 29, 37, 48},
                {32, 33, 39, 50},
              };
		
		System.out.println(ArrayQ.findKthMinin2D(mat, 7));
		
		System.out.println(ArrayQ.findKthMinin2D(mat, 6));
		
		System.out.println(ArrayQ.findKthMinin2D(mat, 4));
		
		System.out.println(ArrayQ.findKthMinin2D(mat, 5));
		
		System.out.println(ArrayQ.findKthMinin2D(mat, 3));
		
		System.out.println(ArrayQ.findKthMinin2D(mat, 2));
	}
}
