package org.blueocean;

import java.nio.CharBuffer;

import org.blueocean.Trie.TrieNode;

import junit.framework.Assert;
import junit.framework.TestCase;

public class StringQTest extends TestCase {
	
	public void testlongestSubstringWithoutRepeatingCharacters(){
		System.out.println(StringQ.longestSubstringWithoutRepeatingCharacters("dsdsdsdsbdah"));
		System.out.println(StringQ.longestSubstringWithoutRepeatingCharacters("GEEKSFORGEEKS"));
		System.out.println(StringQ.longestSubstringWithoutRepeatingCharacters("ABDEFGABEF"));
		
	}
	
	public void testSearchAnagramSubstring(){
		StringQ.searchAnagramSubstring("BACDGABCDA", "ABCD");
		StringQ.searchAnagramSubstring("AAABABAA", "AABA");
		StringQ.searchAnagramSubstring("BACDGAABCDA", "AABA");
	}
	
	public void testNumberToString(){
		System.out.println(StringQ.numberToString("1234"));
	}
	
	public void test(){
		int diff = '1' - 'A';		
		System.out.println(   (char)('1' - diff));
		System.out.println(   (char)('9' - diff));
		
		int i = 'A' + 25;
		System.out.println((char)i);
		
		
		for(int j = 0; j<=10; j++){
		i = 'A' + j - 1;
		System.out.println(j + " = " + (char)i);
		}
	}
	public void testFindFirstNoRepeat(){
		System.out.println(StringQ.findFirstNoRepeat("dfdsfs"));
		System.out.println(StringQ.findFirstNoRepeat("geeksforgeeksandgeeksquizfor"));
	}
	
	public void testFindFirstNoRepeatByQ(){
		System.out.println(StringQ.findFirstNoRepeatByQ("dfdsfs"));
		System.out.println(StringQ.findFirstNoRepeatByQ("geeksforgeeksandgeeksquizfor"));
	}
	
	public void testIsSubDP(){
		System.out.println(StringQ.isSubStrDP("efdffgadffg", "ffg"));
	}
	
	public void testreverseOrder(){
		System.out.println(StringQ.reverseOrder("sfa dsd   defd"));
	}
	
	public void testFindMinWindow(){
		System.out.println(StringQ.findMinWindow("abafgceba", "aab"));
	}
	
	
	public void testFindPali(){
		
		System.out.println(StringQ.longestPalindromicSubStr("fdasffsaf"));
		
	}
	
	public void testfindLongestCommonSubString(){
		System.out.println(StringQ.findLongestRepeatSubString("banaanac"));
		
	}
	public void testFindLongestRepeatSubString(){
		
		Trie t = new Trie();
		String s = "ABCDE";
		for(int i = 0; i<s.length(); i++)
			t.add(s.toLowerCase().substring(i));
		
		String s2 = "XABCZ";
		for(int i = 0; i<s2.length(); i++)
			t.add(s2.toLowerCase().substring(i));
		
		TrieNode result = t.findLongestRepeatSubstring();
		
		t.getSubstring(result);
	}
	
	public void testfindDupSubString(){
		System.out.println(StringQ.findDupSubString("aabbccaaaa"));
		System.out.println(StringQ.findDupSubString("aaabbbbccaaa"));
		System.out.println(StringQ.findDupSubString("aaabbccac"));
	}
	
	public void testCountDuplicates(){
		System.out.println(StringQ.countDuplicates("aaabbcca"));
		System.out.println(StringQ.countDuplicates("aaabbccaaa"));
		System.out.println(StringQ.countDuplicates("aaabbccac"));
		
	}
	
	public void testLCS(){
		
		System.out.println(StringQ.findLongestCommonSubsequence("ABCDGH", "AEDFHR"));
		System.out.println(StringQ.findLongestCommonSubsequence("AGGTAB", "GXTXAYB"));
		
	}
	
	public void testLCS2(){
		
		System.out.println(StringQ.findLongestCommonSubsequence2("ABCDGH", "AEDFHR"));
		System.out.println(StringQ.findLongestCommonSubsequence2("AGGTAB", "GXTXAYB"));
		
	}
	
	public void testToChar(){
		String s = "afs";
		char[] chars= s.toCharArray();
		
		System.out.println(s.length() + " - " + chars.length);
		System.out.println(s.substring(0, s.length()));
		System.out.println(chars[2]);
		
	}
	
	public void testIsSub(){
		String s = "sdfdfsfdsf";
		String m = "fd";
		Assert.assertTrue(StringQ.isSubStr(s, m));
		
	}

}
