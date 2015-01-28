package org.blueocean.learning;

import junit.framework.TestCase;

public class ArrayQTest  extends TestCase {

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
