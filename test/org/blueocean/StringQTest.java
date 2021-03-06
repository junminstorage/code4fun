package org.blueocean;

import java.nio.CharBuffer;
import java.util.Arrays;

import org.blueocean.Trie.TrieNode;

import junit.framework.Assert;
import junit.framework.TestCase;

public class StringQTest extends TestCase {
	
	public void testlongestPalindromeStartingLeft(){
		System.out.println(StringQ.longestPalindromeStartingLeft("aaabaaaaa"));
		System.out.println(StringQ.longestPalindromeStartingLeft("abaeab"));
		System.out.println(StringQ.longestPalindromeStartingLeft("abcbaef"));
		System.out.println(StringQ.longestPalindromeStartingLeft("abcaaba"));
	}
	
	
	public void testlongestPalindromeFromLeft(){
		System.out.println(StringQ.longestPalindromeFromLeft("aaabaaaaa"));
		System.out.println(StringQ.longestPalindromeFromLeft("abaeeaba"));
		System.out.println(StringQ.longestPalindromeFromLeft("abaeab"));
		System.out.println(StringQ.longestPalindromeFromLeft("abcbaef"));
		System.out.println(StringQ.longestPalindromeFromLeft("abcaaba"));
	}
	public void testreversePrint(){
		//StringQ.reversePrint2("afdsf");
		
		char[] str = "abacba".toCharArray();
		Arrays.sort(str);
		System.out.println(str);
		int pos = str.length-1;
		int dup_num = 0;    //to record how many duplication are there in str.
        for(int i=1;i<=pos;i++){
        	System.out.println(i + "-" + dup_num);
            if(str[i]==str[i-1-dup_num]){
                dup_num++;
            }
            else{
                str[i-dup_num] = str[i];
            }
        }
        str[str.length-dup_num] = '\0';
        System.out.println(dup_num);
        
        System.out.println(String.valueOf(str));
	}
	
	public void testBiggerGreater(){
		//StringQ.biggerGreater("gojh");
		
		int t = StringQ.lastIndexOf("12345".toCharArray(), 1, 4, "23".toCharArray(), 0, 2, 3);
		System.out.println(t);
	}
	
	public void testSubDiff(){
		StringQ.subDiff("tabriz", "torino", 2);
		StringQ.subDiff("helloworld", "yellomarin", 3);
	}
	public void testminDistance2(){
		System.out.println(StringQ.minDistance2(new int[]{1, 10},  new int[]{3,8}));
	}
	
	public void testcountDuplicatesFB(){
		System.out.println(StringQ.countDuplicatesFB("coffee tuffee"));
	}
	
	public void testIsMatch(){
		
		System.out.println(StringQ.isMatch("faadcebook", "fa*cebook"));
	}
	
	public void testcanComposed(){
		System.out.println(StringQ.canComposed(new String[]{"cat", "world"}, "catworldd"));
	}
	
	public void testaddBinaryNumbers(){
		System.out.println(StringQ.addBinaryNumbers("110", "01101"));
	}
	
	public void testprintPath(){
		System.out.println(org.blueocean.learning.StringQ.printPath("/usr/bin/mail", "../../../etc/xyz/../abc"));
	}
	
	public void testprintNonComments(){
		StringQ.printNonComments();
	}
	
	public void testfindCommonChars(){
		StringQ.findCommonChars(new String[]{"abcddf", "fadbgth", "aaf"});
	}
	
	public void testfindAllPalindrome(){
		//StringQ.findAllPalindrome("a??a");
		StringQ.findAllPalindrome("a?b");
	}
	
	public void testgroupAnagrams(){
		System.out.println(StringQ.groupAnagrams(new String[]{"star", "astr", "car", "rac", "st"}));
	}
	
	public void testSplitBySpaces(){
		System.out.println(Arrays.asList(StringQ.splitBySpaces(" sdfs sdsd  ssd ")));
	}
	
	public void testHasAnagram(){
		System.out.println(StringQ.hasAnagrams(new String[]{"abc", "adc"}));
		System.out.println(StringQ.hasAnagrams(new String[]{"abc", "adc", "cab"}));
	}
	
	public void testOneEditApart(){
		System.out.println(StringQ.OneEditApart("abc", "cab"));
		System.out.println(StringQ.OneEditApart("abc", "ab"));
		System.out.println(StringQ.OneEditApart("abc", "adc"));
		System.out.println(StringQ.OneEditApart("abc", "abcd"));
		System.out.println(StringQ.OneEditApart("abc", "dabc"));
		System.out.println(StringQ.OneEditApart("abc", "abccc"));
		System.out.println(StringQ.OneEditApart("at", "cat"));
		
		System.out.println(StringQ.OneEditApart("ad", "abc"));
	}

	public void testreverseStringFacebook(){
		System.out.println(StringQ.reverseStringFacebook("the boy ran"));
		System.out.println(StringQ.reverseStringFacebook(" the boy rannn"));
		System.out.println(StringQ.reverseStringFacebook("theboyrannn"));
		
	}
	public void testQSort(){
		char[] t = "ddloyc".toCharArray();
		StringQ.quickSort(t, 0, t.length-1, "odl");
		
		System.out.println(t);
	}
	
	public void testvalidateParenthesis(){
		Assert.assertTrue(StringQ.validateParenthesis("((alf)ls) "));
		Assert.assertFalse(StringQ.validateParenthesis(")(dkk)()  "));
	}
	public void testrotateString(){
		StringQ.rotateString("abcdefg", 2);		
		StringQ.rotateString2("abcdefg".toCharArray(), 2);	
		StringQ.rotateString3("abcdefg".toCharArray(), 2);
		
	}
	
	public void testfindNonRepeastSubString(){
		System.out.println(StringQ.findNonRepeastSubString("abcabcbb"));
		System.out.println(StringQ.findNonRepeastSubString("abcadbeabbefghi"));
		System.out.println(StringQ.findNonRepeastSubString("aa"));
		System.out.println(StringQ.findNonRepeastSubString("bbefghi"));
	}
	
	public void teststringReplace(){
		StringQ.stringReplace("aabcabcb", "abc");
	}
	
	public void testprintCombinationsOf(){
		System.out.println(StringQ.printCombinationsOf("ab"));
		System.out.println(StringQ.printCombinationsOf("abc"));
	}
	
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
