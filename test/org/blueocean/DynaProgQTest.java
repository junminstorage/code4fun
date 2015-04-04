package org.blueocean;

import org.blueocean.DynaProgQ.Job;

import junit.framework.TestCase;

public class DynaProgQTest extends TestCase {
	
	public void testfindLongestSubsequence(){
		int l = 9;
		double n = l/2;
		System.out.println(n);
		
		System.out.println(DynaProgQ.findLongestSubsequence("abcde", "bdace"));
	}
	
	public void testmaxProfit(){
		DynaProgQ q = new DynaProgQ();
		
		Job j1 = q.new Job(1, 2, 50);
		Job j2 = q.new Job(3, 5, 20);
		Job j3 = q.new Job(6, 19, 100);
		Job j4 = q.new Job(2, 100, 200);
		Job j5 = q.new Job(19, 20, 60);
		Job j6 = q.new Job(20, 21, 60);
		Job[] jobs = {j1, j2, j3, j4, j5, j6};
		int profit = q.maxProfit(jobs);
		System.out.println(profit);
	}
	
	public void testmaxRevenue(){
		System.out.println(DynaProgQ.maxRevenue(new int[]{2, 5}, 4));
	}
	
	public void testmatrixChainOrder(){
		System.out.println(DynaProgQ.matrixChainOrder(new int[]{2, 3, 4}));
		System.out.println(DynaProgQ.matrixChainOrder(new int[]{10, 20, 30, 40, 30}));
		System.out.println(DynaProgQ.matrixChainOrder(new int[]{40, 20, 30, 10, 30}));
	}
	
}
