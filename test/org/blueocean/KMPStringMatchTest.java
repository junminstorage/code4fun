package org.blueocean;

import java.util.Arrays;

import junit.framework.TestCase;

public class KMPStringMatchTest extends TestCase {

	public void testKMP(){
		System.out.println(KMPStringMatch.kmp("abcabdabcabdabcabdabdabc".toCharArray(), "abcabdabc".toCharArray()));
		
		KMPStringMatch.searchSubString("abcabdabcabdabcabdabdabc".toCharArray(), "abcabdabc".toCharArray());
	}
	public void testBuildTable(){
	//System.out.println(Arrays.toString(KMPStringMatch.buildTable(new char[]{'1', '2', '3', '1', '3', '4'})));
	System.out.println(Arrays.toString(KMPStringMatch.buildTable("AABAACAABAA".toCharArray())));
	
	}
}
