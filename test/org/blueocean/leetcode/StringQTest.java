package org.blueocean.leetcode;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class StringQTest {
	
	@Test
	public void simplifyPath(){
		assertEquals(StringQ.simplifyPath("/home/"), "/home");
		assertEquals(StringQ.simplifyPath("/home//foo/"), "/home/foo");
		assertEquals(StringQ.simplifyPath("/../"), "/");
		assertEquals(StringQ.simplifyPath("/a/./b/../../c/"), "/c");
	}
	
	@Test
	public void isMatch(){
		assertTrue(StringQ.isMatch("aa", "aa"));
		assertTrue(StringQ.isMatch("aa", "a*"));
		assertFalse(StringQ.isMatch("aaa", "aa"));
		assertTrue(StringQ.isMatch("aa", ".*"));
		assertTrue(StringQ.isMatch("ab", ".*"));
		assertTrue(StringQ.isMatch("aab", "c*a*b"));
	}

	@Test
	public void subStringWithAllWords(){
		Set<String> test = new HashSet<>(Arrays.asList("foo","bar"));
		
		System.out.println(StringQ.subStringWithAllWords("barfoothefoobarman", test));
	}
	
	@Test
	public void miniSubstr(){
		System.out.println(StringQ.miniSubstr("adobecodebanc", "abc"));
	}
}
