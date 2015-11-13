package org.blueocean.leetcode;

import static org.junit.Assert.*;

import org.junit.Test;

public class RecursionQTest {
	
	@Test
	public void findAllPali(){
		
		System.out.println(RecursionQ.matchReg("abc", "a*"));
		
		assertTrue(RecursionQ.hasPaliPartitions("aab",0));
		assertTrue(RecursionQ.hasPaliPartitions("ab",0));
		RecursionQ.findAllPali("aab");
		
		System.out.println(RecursionQ.palindromePartitioning("aab"));
	}

}
