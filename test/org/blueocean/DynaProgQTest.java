package org.blueocean;

import junit.framework.TestCase;

public class DynaProgQTest extends TestCase {
	
	public void testmaxRevenue(){
		System.out.println(DynaProgQ.maxRevenue(new int[]{2, 5}, 4));
	}
	
	public void testmatrixChainOrder(){
		System.out.println(DynaProgQ.matrixChainOrder(new int[]{2, 3, 4}));
		System.out.println(DynaProgQ.matrixChainOrder(new int[]{10, 20, 30, 40, 30}));
		System.out.println(DynaProgQ.matrixChainOrder(new int[]{40, 20, 30, 10, 30}));
	}
	
}
