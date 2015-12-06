package org.blueocean.leetcode;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayQTest {
	
	
	@Test
	public void minSizeArray(){
		assertEquals(2, ArrayQ.minimumSizeArray(new int[]{2,3,1,2,4,3}, 7));
		assertEquals(1, ArrayQ.minimumSizeArray(new int[]{2,3,1,2,4,9}, 7));
		assertEquals(0, ArrayQ.minimumSizeArray(new int[]{2,3,1,2,4,3}, 57));
	}
	@Test
	public void fourSum(){
		ArrayQ.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
	}

	@Test
	public void majority2(){
		System.out.println(ArrayQ.majority2(new int[]{1,2,3,2,4,2,1,1}));
	}
	
	
}
