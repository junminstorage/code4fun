package org.blueocean.learning;

import junit.framework.TestCase;

public class StringQTest extends TestCase {
	
	public void testKeypad(){
		StringQ.keypad(new String[]{"Amazon", "microsoft", "facebook", "Aa", "Bb"});
		
	}
	
	public void testLongestPalindrome(){
		System.out.println(StringQ.longestPalindrome("abgeekskeeghgh"));
	}

}
