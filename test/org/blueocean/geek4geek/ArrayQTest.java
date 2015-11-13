package org.blueocean.geek4geek;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayQTest {
	
	@Test
	public void maxTrapWater2(){
		assertEquals(2, ArrayQ.maxTrapWater2(new int[]{2,0,2}));
		assertEquals(2, ArrayQ.maxTrapWater(new int[]{2,0,2}));
		
		assertEquals(5, ArrayQ.maxTrapWater2(new int[]{2,0,1,0,3}));
		assertEquals(5, ArrayQ.maxTrapWater(new int[]{2,0,1,0,3}));
	}

}
