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
	
	@Test
	public void isMatch(){
		
		assertTrue(RecursionQ.isMatch("ab", ".c*b"));
		assertTrue(RecursionQ.isMatch("abc", "abc"));
		assertFalse(RecursionQ.isMatch("abc", "ab"));
		
		assertFalse(RecursionQ.isMatch("abc", "abcdfdf"));
		
		assertTrue(RecursionQ.isMatch("abc", ".*fgfdg"));
		
		assertTrue(RecursionQ.isMatch("abc", "a*bc"));
		
		assertTrue(RecursionQ.isMatch("abc", "d*abc"));
		
		assertTrue(RecursionQ.isMatch("abc", "a*abc"));
	}

}
