package org.blueocean;

import java.io.ObjectInput;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Map.Entry;

public class StringQ {
	
	/**
	 * Longest palindrome starting from left side 
	 * @param text
	 * @param pattern
	 * @return
	 */
	public static String longestPalindromeFromLeft(String s){
		char[] pattern = s.toCharArray();
		char[] text = new StringBuilder(s).reverse().toString().toCharArray();
		
		int[] table = buildTable(pattern);
		
		int j = 0, i = 0;
		while(i<text.length){
			while(j>=0  && text[i]!=pattern[j])
				j = table[j];
			i++;
			j++;
		}
		
		return s.substring(0, j);
	}
	
	public static List<Integer> kmp(char[] text, char[] pattern){
		int[] table = buildTable(pattern);
		List<Integer> result = new ArrayList<Integer>();
		int j = 0, i = 0;
		while(i<text.length){
			while(j>=0  && text[i]!=pattern[j])
				j = table[j];
			i++;
			j++;
			if(j == pattern.length){
				result.add(i-j);
				System.out.println("found "+ (i - j));
				j=table[j];
			}
		}
		
		return result;
	}
	
	public static int[] buildTable(char[] digits){
		int[] table = new int[digits.length+1];
		table[0] = -1;
		int i = 1, j = -1;
		while(i<digits.length){
			while(j>=0 && digits[i]!=digits[j])
				j = table[j];
			i++;
			j++;
			table[i] = j;		
		}
		return table;
	}
	
	
	
	/**
	 * Longest palindrome starting from left side
	 * @author junminliu
	 *
	 */
	

	public static int longestPalindromeStartingLeft(String str) {
        int left=0, right=str.length()-1;
        while(right>=0) {
            if(str.charAt(left)==str.charAt(right)) {
                left++;
                right--;
            }
            else if(left==0) {
                right--;
            }
            else {
                left = 0;
            }
        }
        return left;
    }

	    public static void main(String[] args) {
//	        String str = "abbaaba";
//	        String str = "abbaabba";
	        String str = "cabbacabba";
	        System.out.println(longestPalindromeStartingLeft(str));
	        System.out.println(longestPalindromeStartingLeft("abcaaba"));
	    }
	
	
	static class DescendComparator implements Comparator<Entry<Character, Integer>> {
	    public int compare(Entry<Character, Integer> entry1,
	            Entry<Character, Integer> entry2) {
	        int count1 = entry1.getValue();
	        int count2 = entry2.getValue();
	        return count2 - count1;
	    }
	}
	
    public static void descendPrint2(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        // put char count to hashMap
        Map<Character, Integer> hm = new HashMap<Character, Integer>();
        for (int i = 0; i < str.length(); i++) {
            if (!hm.containsKey(str.charAt(i))) {
                hm.put(str.charAt(i), 1);
                continue;
            }
            int count = hm.get(str.charAt(i));
            hm.put(str.charAt(i), ++count);
        }
        // Sort hashMap according to value.
        Set<Entry<Character, Integer>> set = hm.entrySet();
        ArrayList<Entry<Character, Integer>> list = new ArrayList<Entry<Character, Integer>>(
                set);
        Collections.sort(list, new DescendComparator());
        // Print result
        for (Entry<Character, Integer> entry : list) {
            char ch = entry.getKey();
            int count = entry.getValue();
            for (int i = 0; i < count; i++) {
                System.out.print(ch);
            }
        }
    }
	
	
	public static void reversePrint2(String s){
		if(s==null || s.isEmpty())
			return;
		
		int p1=s.length()-1;
		while(p1>=0){
			System.out.print(s.charAt(p1));
			p1--;
		}
	}
	
	public static void reversePrint(String s){
		if(s==null || s.isEmpty())
			return;
		
		char[] chars = s.toCharArray();
		int p0=0;
		int p1=s.length()-1;
		while(p0<p1){
			char temp = chars[p0];
			chars[p0] = chars[p1];
			chars[p1] = temp;
			p0++; p1--;
		}
		System.out.println(String.valueOf(chars));
	}
	
	 public static int lastIndexOf(char[] source, int sourceOffset, int sourceCount,
	            char[] target, int targetOffset, int targetCount,
	            int fromIndex) {
	        /*
	         * Check arguments; return immediately where possible. For
	         * consistency, don't check for null str.
	         */
	        int rightIndex = sourceCount - targetCount;
	        if (fromIndex < 0) {
	            return -1;
	        }
	        if (fromIndex > rightIndex) {
	            fromIndex = rightIndex;
	        }
	        /* Empty string always matches. */
	        if (targetCount == 0) {
	            return fromIndex;
	        }

	        int strLastIndex = targetOffset + targetCount - 1;
	        char strLastChar = target[strLastIndex];
	        int min = sourceOffset + targetCount - 1;
	        int i = min + fromIndex;

	        startSearchForLastChar:
	        while (true) {
	            while (i >= min && source[i] != strLastChar) {
	                i--;
	            }
	            if (i < min) {
	                return -1;
	            }
	            int j = i - 1;
	            int start = j - (targetCount - 1);
	            int k = strLastIndex - 1;

	            while (j > start) {
	                if (source[j--] != target[k--]) {
	                    i--;
	                    continue startSearchForLastChar;
	                }
	            }
	            System.out.println(start + " - " + sourceOffset);
	            return start - sourceOffset + 1;
	        }
	    }
	
	public static boolean equals(String s1, String s2){
		if(s1==null || s2==null)
			throw new IllegalArgumentException();
		
		if(s1.length()!=s2.length())
			return false;
		
		char[] char1 = s1.toCharArray();
		char[] char2 = s2.toCharArray();
		
		for(int p=0; p<char1.length; p++){
			if(char1[p]!=char2[p])
				return false;
		}
		
		return true;
		
	}
	public static int strcmp(String s1, String s2){
	    assert(s1!=null && s2!=null);	    
	    int len1 = s1.length();
		int len2 = s2.length();
		for(int p1=0, p2=0; p1<len1&&p2<len2; p1++,p2++){
	        char c1 = s1.charAt(p1);
			char c2 = s2.charAt(p2);
			if(c1!=c2)
	            return c1 - c2;
	    }
	    return len1 - len2;	     
	}
	
	public static void biggerGreater(String str){
	    char[] chars = str.toCharArray();
	    int len = chars.length;
	    int p = len-1;
	    while(p-1>=0 && chars[p-1]>=chars[p])
	        p--;
	        
	    if(p==0)
	        return;
	    
	    int p1 = p-1;
	    //binary search p1 from p, len-1
	    int left = p;
	    int right = len;
	    
	    while(left<right-1){
	        int mid = (left+right)>>>1;
	        if(chars[mid]>chars[p1])
	            left = mid;
	        else
	            right = mid;
	    }
	    
	    int p2 = left;
	    
	    //swap p1, p2
	    char temp = chars[p1];
	    chars[p1] = chars[p2];
	    chars[p2] = temp;
	    
	    //reverse chars from p1+1, len-1
	    for(int start = p1+1, end = len -1; start<len && end>=0 && start<end; start++, end--){
	        temp = chars[start];
	        chars[start] = chars[end];
	        chars[end] = temp;
	    }
	    
	    System.out.println(String.valueOf(chars));
	}
	public static void subDiff(String st1, String st2, int maxD){
		Deque q = new ArrayDeque();
		
        System.out.println(st1 + st2 + maxD);
        int len = st1.length();
        int maxL = 0;
        int[][] table = new int[len][len];
        int[][] diffs = new int[len][len];
        
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                int diff = 0;               
                int start = 0;
                if(i>0 && j>0){
                	if(st1.charAt(i) == st2.charAt(j)){
                		start = table[i-1][j-1];
                		diff = diffs[i-1][j-1];
                	}else{
                		diff = diffs[i-1][j-1]-1;
                	}
                }
                
                for(int k=start; i+k<len && j+k<len; k++){
                    if(st1.charAt(i+k)!=st2.charAt(j+k))
                        diff++;
                    if(diff<=maxD){
                        maxL = Math.max(maxL, k+1);
                        table[i][j] = k;
                        diffs[i][j] = diff;
                    }
                    else
                        break;
                }
                
                
            }          
        }
        
        System.out.println(maxL);
    }
	
	/*
	 * Question 1: Given two valid dictionary words, find the minimum number of steps required to transform first word to second word. Following are the transformation rules –

1. You can, in a single step, change a single letter in the word.
2. Each transition should result in a valid word. Assume you have been provided a helper function boolean isValid (String word) which tells you if a word is valid or not.
3. This must be done with minimum transitions.

Example: Transform CAT to TOY. One of the several possible transformations is CAT -> CAR -> TAR -> TOR -> TOY
	 */
	public static class Node{
		String w;
		int d;
		public Node(String s, int t){
			w = s;
			d = t;
		}
	}
	
	public static boolean isValid(String w){
		return true;
	}
	public static int shortestDistanceBetweenWords(String w, String t){
		if(w.length()!=t.length())
			return Integer.MAX_VALUE;
		
		Set<String> cache = new HashSet<String>();
		Deque<Node> q = new ArrayDeque<Node>();
		q.add(new Node(w, 0));
		cache.add(w);
		while(!q.isEmpty()){
			Node c = q.pop();
			if(c.w.equals(t))
				return c.d;
			
			char[] chars = c.w.toCharArray();
			for(int i=0; i<chars.length; i++){
				char temp = chars[i];
				for(int j = 'a'; j<='z'; j++){
					chars[i] = (char)j;
					String newW = String.valueOf(chars);
					if(!cache.contains(newW) && isValid(newW)){
						q.add(new Node(newW, c.d+1));
						cache.add(newW);
					}
					chars[i] = temp;
				}
			}		
		}
		
		return Integer.MAX_VALUE;
	}
	
	
	//Given an Amazon reviews paragraph containing several words, find the minimum distance between two given words.
	//Example: Following is a hypothetical paragraph in an amazon review –
	//“Amazon is the best company to work for. The amazon is a beautiful forest.”

	public int minDistance(String s1, String s2, String sentence){
	   if(s1==null || s1.isEmpty() || s2==null || s2.isEmpty() || sentence==null || sentence.isEmpty())
	       return -1;
	       

	    int minD = Integer.MAX_VALUE;
	    int p1 = -1;
	    int p2 = -1;
	    
	    StringTokenizer st = new StringTokenizer(sentence);
	    int p = -1;
	    while(st.hasMoreTokens()){
	        String t = st.nextToken();
	        p++;
	        if(t.equals(s1))
	            p1 = p;
	        if(t.equals(s2))
	            p2 = p;
	        minD = minD > p2-p1? p2-p1:minD;    
	    }
	    
	    return minD;
	}
	/*
	 * Given: You are given the position of each word in the paragraph. Meaning, you know that word ‘Amazon’ occurs at positions 1 and 10, and ‘The’ occurs at 3 and 9. 
	 * You do not have to parse the paragraph to gather this info.
	 */
	public static int minDistance2(int[] p1, int[] p2){
		   int t1 = 0;
		   int t2 = 0;
		   
		   int minD = Math.abs(p1[0]-p2[0]);
		   
		   while(t1<p1.length && t2<p2.length){
		       int d = Math.abs(p1[t1] - p2[t2]);
		       minD = minD > d? d:minD; 
		       if(p1[t1]<p2[t2])
		           t1++;
		       else 
		           t2++;
		   }
		   
		   return minD;
		}
	
	//select customer_id from (select customer_id, sum(expense) as sumE from expense
	//group by customer_id order by sumE desc) where rownum<=5
	
	/*
	 * http://www.careercup.com/question?id=5088478488428544
	 */
	public class WordCounter implements Comparable<WordCounter>{
		int counter;
		String w;
		
		public WordCounter(String w1, int c){
			this.w = w1;
			this.counter = c;
		}
		@Override
		public int compareTo(WordCounter o) {
			if(this.counter == o.counter)
				return this.w.compareTo(o.w);
			else
				return this.counter - o.counter;
		}	
	}
	
	public void processFile(){
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		TreeSet<WordCounter> set = new TreeSet<WordCounter>();
		
		for(String s : map.keySet()){
			set.add(new WordCounter(s, map.get(s)));
		}
		
	}
	
	/*http://www.careercup.com/page?pid=facebook-interview-questions&n=5
	*/

	public static char countDuplicatesFB(String s){
	    int[] counter = new int[256];
	    
	    for(int i=0; i<s.length(); i++){
	        counter[(int)s.charAt(i)]++;
	    }
	    
	    int maxIndex = 0;
	    for(int i=1; i<256; i++){
	        if(counter[i]>counter[maxIndex])
	            maxIndex = i;
	    }
	    
	    return (char)maxIndex;
	}
	
	/*In Java: Write a function in language of your choice that 
	 * takes in two strings, and returns true if they match. 
	 * Constraints are as follows: String 1, the text to match to, will be alphabets and digits. String 2, the pattern, will be alphabets, digits, '.' and '*'. '.' means either alphabet or digit will be considered as a "match". "*" means the previous character is repeat 0 or more # of times. For example: Text: Facebook Pattern: F.cebo*k returns true.
	*/

	public static boolean isLegal(char sC){
		return (sC>='0' && sC <='9') || (sC>='A' && sC<='Z') || (sC>='a' && sC<='z');
	}
	public static boolean isMatch(String s, String p){
	    int p1 = 0;
	    int p2 = 0;
	    
	    char pre = '\0';
	    while(p1<s.length() && p2<p.length()){
	        char sC = s.charAt(p1);
	        char pC = p.charAt(p2);
	        
	      //check illegal chars, alphabets or digits
	        if(!isLegal(sC))
	        	throw new IllegalArgumentException("invalid string");
	        	
	        if(!isLegal(pC) && pC!='.' && pC!='*')
	        	throw new IllegalArgumentException("invalid pattern");
	        
	        if(pC == '.'){
	            p1++; p2++;
	        }else if(pC == '*'){
	            if(pre == '\0')
	                throw new IllegalArgumentException("invalid pattern");
	            else if(pre=='.'){
	                p1++;
	            }        
	            else if(sC == pre){
	                p1++;
	            }else{
	                p2++;
	            }    
	            
	        }else{
	            if(sC == pC){
	                p1++; p2++;
	            }else{
	                return false;
	            }
	        }
	    
	        pre = pC;
	        
	    }
	    
	    return p1==s.length() && (p2 == p.length()||p2==p.length()-1 && pre == '*');


	}
	
	/*You're given a dictionary of strings, and a key. Check if the key is composed of an arbitrary number of concatenations of strings from the dictionary. For example: 

	dictionary: "world", "hello", "super", "hell" 
	key: "helloworld" --> return true 
	key: "superman" --> return false 
	key: "hellohello" --> return true
	*/
	public static boolean canComposed(String[] words, String word){
	    Stack<Integer> s = new Stack<Integer>();   
	    s.push(0);
	    
	    return canComposedRec(words, word, s);

	}

	public static boolean canComposedRec(String[] words, String word, Stack<Integer> s){
	    if(s.isEmpty())
	        return false;
	        
	    int len = word.length();
	    
	    Stack<Integer> s1 = new Stack<Integer>();   
	    while(!s.isEmpty()){
	        int start = s.pop();
	        if(start==len)
	            return true;
	       
	        for(String w : words){
	            int index = word.indexOf(w, start);
	            if(index>=0){
	                    s1.push(index+w.length());
	            }
	         }        
	    }
	    
	    return canComposedRec(words, word, s1);

	}
	
	/*
	 * http://www.careercup.com/question?id=15420859
	 * Write a program to sum two binary numbers represented as strings. 
	 * Input: "110", "01101"  
	 * Output: "10011" 
	*/
	public static String addBinaryNumbers(String num1, String num2){
		assert(num1!=null && num2!=null);
	    int p1 = num1.length()-1;
	    int p2 = num2.length()-1;
	    
	    String result = "";
	    int left = 0;
	    int sum = 0;
	    while(p1>=0 && p2>=0){
	        sum = left;
	        char c1 = num1.charAt(p1);
	        char c2 = num2.charAt(p2);
	        
	        if(c1=='1')
	            sum++;
	        if(c2=='1')
	            sum++;
	            
	        left = sum/2;
	            
	        result = sum % 2 + result;  
	        p1--;
	        p2--;  
	    }

	    int p = p1>=0? p1:p2;
	    String num = p1>=0?num1:num2;
	    while(p>=0){
	       sum = left;
	       char c = num.charAt(p);
	       if(c=='1')
	           sum++;
	       left = sum/2;
	       result = sum % 2 + result;  
	       p--;
	    }
	    if(left==1)
	        result = left + result;
	    return result;
	}
	
	static void printNonComments(){
		
	    String line = null;
	    boolean begin = false;
	    int j = 0;
	    while((line= getNextLine(j)) != null){
	    	j++;
	        if(line.isEmpty())
	            System.out.println(line);
	        else {
	        	int index = -1;
	            for(int i=0; i<line.length(); i++){
	                if(!begin && line.charAt(i)=='/' && i+1<line.length() && line.charAt(i+1)=='*'){
	                    begin = true;
	                    index = i;
	                    break;
	                }else if(begin && line.charAt(i)=='*' && i+1<line.length() && line.charAt(i+1)=='/'){
	                    begin = false;
	                    index = i+2<line.length()?i+2:line.length()-1;
	                    break;
	                }      
	            }
	            
	            if(index != -1){              
                    System.out.println(begin? line.substring(0, index) : line.substring(index));
	            }else if(!begin)
	            	System.out.println(line);
	            
	        }
	    
	    }
	}
	
	
	
	private static String getNextLine(int i) {
		if(i==0)
			return "hello /* this is a ";
		if(i==1)
			return "dsdsad";
		if(i==2)
			return "multi line comment */ all ";
		return null;
	}



	/*
	 * http://www.careercup.com/question?id=6283084039192576
	 * Write a program that gives count of common characters presented in an array of strings..(or array of character arrays) 
	 */
	public static void findCommonChars(String[] strings){
		if(strings==null || strings.length<2)
			return;
		
		int[] t = new int[26];
		
		for(String s: strings){
			int[] seen = new int[26];
			for(int i=0; i<s.length(); i++){
				int c = s.charAt(i)-'a';
				seen[c]++;
				if(seen[c]==1)
					t[c]++;
			}
		}
		
		for(int i=0; i<26; i++){
			if(t[i]==strings.length)
				System.out.println((char)(i+'a'));
		}
	}
	
	/*
	 * http://www.geeksforgeeks.org/amazon-interview-experience-set-140-experienced-sde/
	 * 2.	In a given string some of the characters are replaced by question mark, and 
	 * you can replace question mark with any character. Given such a string find total number of 
	 * palindrome that can created. String contains only [a-z] characters and question 
	 * marks can also be only replaced by [a-z].
Example:
Input String: String str=”a??a”
Output: 26
	 */
	public static void findAllPalindrome(String input){
		if(input==null || input.isEmpty())
			return;
		
		findAllPalindrome(input, 0, input.length()-1);
	}
	
	public static void findAllPalindrome(String input, int i, int j){
		if(i>j)
			System.out.println(input);
		else if(i==j){
			if(input.charAt(i)=='?'){
				for(int k='a'; k<='z'; k++){
					char[] chars = input.toCharArray();
					chars[i] = (char)k;
					System.out.println(String.valueOf(chars));
				}				
			}else
				System.out.println(input);
		}else{
			char ci = input.charAt(i);
			char cj = input.charAt(j);
			if(ci!='?'&&cj!='?'&&ci!=cj){
				System.out.println("can not find any");
			}
			else if(ci=='?' && cj=='?'){
				for(int k='a'; k<='z'; k++){
					char[] chars = input.toCharArray();
					chars[i] = (char)k;
					chars[j] = (char)k;
					findAllPalindrome(String.valueOf(chars), i+1, j-1);
				}	
			}else{
				char replace = ci=='?'?cj:ci;
				char[] chars = input.toCharArray();
				chars[i] = replace;
				chars[j] = replace;
				findAllPalindrome(String.valueOf(chars), i+1, j-1);
			}
		}
	}
	
	
	/*
	 * http://www.javacodegeeks.com/2010/07/java-best-practices-high-performance.html
	 */
	/*
	 * http://www.javacodegeeks.com/2010/11/java-best-practices-char-to-byte-and.html
	 */
	public static byte[] stringToBytesASCII(String input){
		byte[] byteArray = new byte[input.length()];
		for(int i=0; i<input.length(); i++){
			byteArray[i] = (byte)input.charAt(i);
		}
		return byteArray;
	}
	
	
	static ObjectInput objectInput;
	
	
	public static byte[] stringToBytesCustom(String input){
		
		byte[] byteArray = new byte[input.length()<<1];
		for(int i=0; i<input.length(); i++){
			int j = i<<1;
			byteArray[j] = (byte)(input.charAt(i)&0xFF00>>8);
			byteArray[j+1] = (byte)(input.charAt(i)&0x00FF);
		}
		return byteArray;
	}
	
	
	
	/*
	 * Group Anagrams 
input = ["star, astr, car, rac, st"] 
output = [["star, astr"],["car","rac"],["st"]);
hash("abr") = (97 × 1012) + (98 × 1011) + (114 × 1010) = 999,509 
int primes[] = {2, 3, 5, 7, ...} // can be auto generated with a simple code

inline int prime_map(char c) {
    // check c is in legal char set bounds
    return primes[c - first_char];
}

	 */
	
	public static Collection<List<String>> groupAnagrams(String[] strings){
		
		HashMap<String, List<String>> result = new HashMap<String, List<String>>();
		for(String s : strings){
			char[] chars = s.toCharArray();
			Arrays.sort(chars);
			String anagram = String.valueOf(chars);
			if(!result.containsKey(anagram)){
				List<String> list = new ArrayList<String>();
				list.add(s);
				result.put(anagram, list);
			}else{
				result.get(anagram).add(s);
			}
		}
		
		return result.values();
	}
	
	private boolean isAnagram(String string, String s) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * split string into strings based on empty spaces
	 * http://java-performance.info/regexp-related-methods-of-string/#more-60
	 * Always (or nearly always) replace String.matches, split, replaceAll, replaceFirst methods with Matcher and Pattern methods.
	 * In all other simple cases consider handwriting parsing methods for simple situations in the time-critical code. 
	 * You can easily gain 10 times speedup by replacing Pattern methods with handcrafted methods.
	 */
	public static String[] splitBySpaces(String input){
		List<String> result = new ArrayList<String>();
		
		for(int i=0; i<input.length(); i++){
			if(input.charAt(i)<=' '){
				if(i==0)
					result.add("");
			}else{
				int start = i;
				while(start<input.length() && input.charAt(start)>' ')
					start++;
				result.add(String.valueOf(input.substring(i, start)));
				i = start;
			}			
		}
		
		return result.toArray(new String[result.size()]);
	}
	
	/*
	 * http://www.careercup.com/question?id=5654505932718080
	 * Given an array of words, write a method that determines 
	 * whether there are any words in this array that are anagrams of each other. 
	 */
	public static boolean hasAnagrams(String[] input){
		Set<String> seen = new HashSet<String>(); //sorted string
		for(String s : input){
			String a = anagram(s);
			if(seen.contains(a))
				return true;
			else
				seen.add(a);
		}
		
		return false;
	}
	
	public static String anagram(String input){
		char[] chars = input.toCharArray();
		Arrays.sort(chars);
		return String.valueOf(chars);
	}
	
	/*
	 * http://www.careercup.com/question?id=4793416529477632
	 * Implement a function OneEditApart with the following signature: 
	 * bool OneEditApart(string s1, string s2) 
	 */
	public static boolean OneEditApart(String s1, String s2) {
		if(s1==null || s1 ==null)
			return false;
		if(Math.abs(s1.length()-s2.length())>1)
			return false;
		
		int diff = 0;
		
		if(s1.length() == s2.length()){
			for(int i=0;i<s1.length(); i++){
				if(s1.charAt(i) != s2.charAt(i)){
					diff++;
					if(diff==2)
						return false;
				}
			}
		}else{
			if(s1.length()>s2.length()){
				String t = s1;
				s1 = s2;
				s2 = t;
			}
				
			for(int i=0;i<s1.length(); i++){
				if(s1.charAt(i) != s2.charAt(i+diff)){
					diff++;
					i--;
					if(diff==2)
						return false;
				}
			}
		}
		
		return true;
	}
	/*
	 * minimum number of editions between 2 strings
	 */
	public static int distance(String s1, String s2){
		int[][] edits = new int[s1.length()+1][s2.length()+1];
		edits[0][0] = 0;
		for(int i=1; i<=edits.length; i++)
			edits[i][0] = 1;
		for(int j =1; j<=edits[0].length; j++)
			edits[0][j] = 1;
		
		for(int i=1;i<=edits.length;i++){
			for(int j=1;j<=edits[0].length;j++){
				if(s1.charAt(i-1)==s2.charAt(j-1))
					edits[i][j] = edits[i-1][j-1];
				else
					edits[i][j] = Math.min(edits[i][j-1]+1,Math.min(edits[i-1][j-1]  + 1, edits[i-1][j]+1));
			}
		}
		
		return edits[s1.length()][s2.length()];
		
	}
	
	
	
	static int distanceBetween(String s1, String s2){
	    if(s1==null || s2==null)
	        return -1;
	        
	        
	    int[][] table = new int[s1.length()+1][s2.length()+1];
	    table[0][0] = 0;
	    for(int i = 0; i<s1.length()+1; i++)
	        table[i][0] = 1;
	      
	     for(int i = 0; i<s2.length()+1; i++)
	        table[0][i] = 1;    
	    
	    for(int i = 1; i<s1.length()+1; i++){
	        for(int j = 1; j<s2.length()+1; j++){
	            if(s1.charAt(i-1) == s2.charAt(j-1))
	                table[i][j] = table[i-1][j-1];
	            else{
	                int replace =  table[i-1][j-1] + 1;
	                int deletei = table[i-1][j]+1;
	                int deletej = table[i][j-1] + 1;
	                
	                table[i][j] = Math.min(deletei, Math.min(replace, deletej));         
	            }       
	        }   
	    }
	    
	    return table[s1.length()][s2.length()];
	}

	
	/*
	 * Code a function that receives a string composed by words separated by 
	 * spaces and returns a string where words appear in the same order 
	 * but than the original string, but every word is inverted. 
	Example, for this input string
	http://www.careercup.com/question?id=5106757177180160
	 */
	public static String reverseStringFacebook(String str){
		int start = 0;
		char[] chars = str.toCharArray();
		while(start<str.length()){
			if(str.charAt(start)==' '){
				start++;
			}
			else{	
				int end = start;

				while(end<str.length() && str.charAt(end)!=' ')
					end++;
				
				for(int p1=start,p2= end-1; p1<=p2; p1++,p2--){
					char temp = chars[p1];
					chars[p1] = chars[p2];
					chars[p2] = temp;
				}
				start = end+1;
			}
		}
		
		return String.valueOf(chars);
		
	}
	
	/*
	 * Two strings are given. One of them is the initial string 
	 * and other string contains characters as per their priority. 
	 */
	public static void quickSort(char[] target, int start, int end, String p){
		if(start>=end)
			return;

		int pivotal = (start + end)/2;
		char pC = target[pivotal];

		//sort around pivotal
		swap(target, pivotal, end);
		int p1 = start;
		int p2 = start;
		while(p1<end){
			if(compare(target[p1],  pC, p)<0){				
				swap(target, p1, p2);
				p2++;
			}
			p1++;
		}
		swap(target, p2, end);

		quickSort(target, start, p2-1, p);
		quickSort(target, p2+1, end, p);
		
	}
	
	public static int compare(char a, char b, String p){
		if(p.indexOf(a)>-1 && p.indexOf(b)>-1)
			return p.indexOf(a) - p.indexOf(b);
		else if(p.indexOf(a)>-1){
			return -1;
		}else if(p.indexOf(b)>-1){
			return 1;
		}else{
			return a - b;
		}
		
	}
	public static void swap(char[] target, int a, int b){
		char t = target[a];
		target[a] = target[b];
		target[b] = t;
	}
	
	
	/*
	 * http://www.geeksforgeeks.org/amazon-interview-set-51-campus-sdet/
	 * A string consists of parenthesis and letters. 
	 * Write a program to validate all the parenthesis. Ignore the letters.
	 */
	public static boolean validateParenthesis(String s){
		Stack<Character> stack = new Stack<Character>();
		
		for(int i=0; i<s.length(); i++){
			if(s.charAt(i)=='(')
				stack.push('(');
			
			if(s.charAt(i) ==')'){
				if(stack.empty())
					return false;
				else{
					stack.pop();
				}
			}
		}
		
		return stack.empty();
	}
	
	/*
	 * Q1. A string of length n and an integer m was given, 
	 * give an algo. to rotate the string counter clockwise by m. 
	 * I was asked to give all the check conditions for input m.
	 */
	//solution 1: using extra temp array
	public static void rotateString(String s, int m){
		if(m<=0)
			return;
		if(m>=s.length())
			return;
		
		char[] string = s.toCharArray();
		
		String tempS = s.substring(0, m);
		
		int p1=0;
		
		while(p1+m<s.length()){
			char tempC = string[p1];
			string[p1] = string[p1+m];
			string[p1+m] = tempC;
			p1++;
		}
		
		while(p1<s.length()){
			string[p1] = tempS.charAt(p1+m-s.length());
			p1++;
		}
		
		System.out.println(string);
	}
	
	//solution 2: left shift one by one
	public static void rotateString2(char[] string, int m){
		for(int i=0; i<m; i++)
			leftShiftByOnePos(string);
		System.out.println(string);
	}
	
	public static void leftShiftByOnePos(char[] string){
		if(string==null || string.length == 0)
			return;
		char temp = string[0];
		int pos;
		for(pos = 0; pos < string.length-1; pos++){
			string[pos] = string[pos+1];
		}
		string[pos] = temp;
	}
	//solution 3. global replace
	public static void rotateString3(char[] string, int m){
		if(string==null || string.length == 0)
			return;
		
		int original = 0;
		char temp = string[original];
		int target  = original;
		while(target<string.length){
			int replace = target + m;
			if(replace>=string.length)
				replace = replace - string.length;		
			if(replace == original) {
				string[target] = temp;
				break;
			}else{
				string[target] = string[replace];
				
			}
			target = replace;
		}
		
		System.out.println(string);
	}
	
	/*
	 * Q1. Given a string find the length of longest substring which has none of its character repeated?
	 * http://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
	 */
	public static int findNonRepeastSubString(String target){
		Map<Character, Integer> char2pos = new HashMap<Character, Integer>();
		
		int start = 0;
		int end = -1;
		int max = 0;
		for(int i=0; i< target.length(); i++){
			end++;
			if(char2pos.containsKey(target.charAt(i))){			
				//only move start when the repeat is after start, discard any char before start
				if(char2pos.get(target.charAt(i))>=start)	
					start = char2pos.get(target.charAt(i)) + 1;					
			}
			//update the new pos to i					
			char2pos.put(target.charAt(i), i);
			if(end-start>max){
				max = end-start;
			}
		}				
		return max + 1;
	}
	
	/*
	 * Q3 – Write an efficient function that takes two strings 
	 * as arguments and removes the second string from first string (in place). (Shifting not allowed)
	 */
	public static void stringReplace(String s, String t){
		char[] sChar = s.toCharArray();
		char[] tChar = t.toCharArray();
		
		boolean[][] found = new boolean[s.length()][t.length()];
		//find index which have t	
		found[1][2] = true;
		found[4][2] = true;
		for(int i=0; i<found.length; i++){
			if(found[i][t.length()-1]){
				int start = i;
				int end = start+t.length()-1;
				for(int r=start; r<=end; r++){
					sChar[r] = '\0';
				}								
			}
		}		
		for(int i=0; i<s.length(); i++){
			if(sChar[i]=='\0'){
				int replace = i+1;
				while(replace<s.length() && sChar[replace]=='\0')
					replace++;
				if(replace<s.length()){
					sChar[i] = sChar[replace];
					sChar[replace] = '\0';
				}else
					break;
			}
		}
		
		System.out.println(sChar);
	}
	
	/*
	 * Given a string having no spaces, and a dictionary.Problem was to find 
	 * if that string can be splitted in multiple strings such that all the splitted
	 *  strings are in dictionary. I was provided a function search(string str) which will tell if a particular string str is in the dictionary or not.
	 */
	public boolean stringSplittable(String s){
		boolean[][] store = new boolean[s.length()][s.length()];
		
		for(int i=0; i<s.length()-1; i++)
			store[i][i] = search(s.substring(i, i+1));
		
		for(int step =1; step<s.length(); step++){
			for(int start = 0; start<s.length()-step && start+step<s.length(); start++){
				for(int i=0;i<step;i++){
					if(search(s.substring(start, start+i)) && store[start+i][start+step]){
						store[start][start+step] = true;
						break;
					}
				}
			}
		}
		
		return store[0][s.length()-1];
	}
	
	private boolean search(String str){
		return true;
	}
	
	/*
	 * Write a code to print all 
	 * possible combinations(order matters) of characters of string in lexicographical order.
	 */
	
	public static Set<String> printCombinationsOf(String s){
		Set<String> result = new TreeSet<String>();
		result.add(String.valueOf(s.charAt(0)));
		for(int i=1; i<s.length(); i++){
			Set<String> temp = printCombinationsOfHelper(s, result, i);
			result = temp;
		}
		return result;
	}
	
	public static Set<String> printCombinationsOfHelper(String s, Set<String> pre, int index){
		Set<String> result = new TreeSet<String>(pre);
		
		for(String st : pre){
			for(int i=0; i<st.length(); i++){
				result.add(st.substring(0, i) + s.charAt(index) + st.substring(i, st.length()));
			}
			result.add(st + s.charAt(index));
		}
		result.add(String.valueOf(s.charAt(index)));
		return result;
	}
	
	public static int longestSubstringWithoutRepeatingCharacters(String target){
		if(target==null || target.isEmpty())
			return 0;
		
		int start = 0;
		int maxLen = 1;
		int[] found = new int[256];
		found[target.charAt(0)] = 1;
		for(int i=1; i<target.length(); i++){
			if(found[target.charAt(i)]>0 && found[target.charAt(i)]>=start){//only look at index after start
				if(i-start>maxLen)
					maxLen = i - start;				
				start = found[target.charAt(i)];
			}	
			found[target.charAt(i)] = i+1;
		}
		
		if(target.length()-start>maxLen)
			maxLen = target.length() - start;
		
		return maxLen;
	}
	
	/*
	 * given 2 arrays wrds[] , chars[] as an input to a function such that 
wrds[] = [ "abc" , "baa" , "caan" , "an" , "banc" ] 
chars[] = [ "a" , "a" , "n" , "c" , "b"] 
Function should return the longest word from words[] which can be constructed from the chars in chars[] array. 
for above example - "caan" , "banc" should be returned 

	 */
	static volatile boolean first= true;
	public static List<String> canComposteFromChars(String[] words, char[] chars){
		int[] needToFound = new int[256];
		int[] seen = new int[256];
		
		int maxL = 0;
		List<String> result = new ArrayList<String>();
		for(int i=0; i<chars.length; i++)
			needToFound[chars[i]] ++;

		for(int i=0; i<words.length; i++){
			int j =0;
			while(j<words[i].length()){
				char c = words[i].charAt(j);
				if(needToFound[c]>=1 && seen[c]++<needToFound[c]){
					j++;
				}else
					break;
			}		
			if(j==words[i].length()){//found positive words
				if(maxL <=j){
					if(maxL < j){
						result.clear();
					}
					result.add(words[i]);
				}					
			}
		}

		return result;
	}
	
	
	public static void searchAnagramSubstring(String target, String pattern){
		int[] needToFound = new int[256]; // number of times each char appears in pattern
		int[] seen = new int[256]; // number of times each char appears so far
		int counter = 0; //if counter == pattern.length, then FOUND!
		
		for(int i=0; i<pattern.length(); i++){
			needToFound[pattern.charAt(i)] ++;
		}
		
		int currP = 0;
		for(int j=0; j<target.length(); j++){
			
			if(j>=pattern.length()){
				char charAtCurrP = target.charAt(currP);
				if(needToFound[charAtCurrP]>=1){
					seen[charAtCurrP]--;
					if(seen[charAtCurrP]<needToFound[charAtCurrP])
						counter--;
				}
				currP++;
			}
			
			int curr = target.charAt(j);
			
			if(needToFound[curr]>=1){//matched!
				seen[curr] ++; //number of matches				
				if(seen[curr]<=needToFound[curr])
					counter++;				
			}			
			if(counter == pattern.length())
				System.out.println("Found at " + j);
			}
			
		}
		
	
	public static int CountPossibleDecodingsofDigitSequence(int[] numbers){
		int[] table = new int[numbers.length+1];
		table[0] = 1;
		table[1] = 1;
		
		for(int i=2; i< numbers.length+1; i++){
			if(numbers[i]>=1 &&numbers[i]<= 'Z'-'A'+1)
				table[i] = table[i-1];
			if(i+1<numbers.length){
				int n = numbers[i+1] + numbers[i] * 10;
				if(n>=1 && n <='Z' - 'A' + 1)
					table[i] = table[i] + table[i-2];
			}
		}
		
		return table[numbers.length];
	}
	
	public static ArrayList<String> numberToString(String number){
		ArrayList<String> result = new ArrayList<String>();
		if(number.isEmpty()){
			result.add("");
			return result; 
		}
		
		int diff = '1' - 'A';		
		char c =   (char)(number.charAt(0) - diff);		
		ArrayList<String> result1 = numberToString(number.substring(1));		
		for(String s : result1){
			result.add(c + s);
		}
		
		if(number.length()>1){
			c = (char)('A' -1 + Integer.valueOf(number.substring(0, 2)));
				
			if(c>= 'A' && c<='Z'){
			ArrayList<String> result2 = numberToString(number.substring(2));		
			for(String s : result2){
				result.add(c + s);
			}
			}
		}
		return result;
	}
	
	
	static class DLLNode{
		char pos; // position
		DLLNode next;
		DLLNode pre;
		
		public DLLNode(char p, DLLNode n, DLLNode pr){
			pos = p;
			next = n;
			pre = pr;
		}
	}
	
	/*
	 * http://www.geeksforgeeks.org/given-a-string-find-its-first-non-repeating-character/
	 * Given a string, find the first non-repeating character in it. 
	 */
	public static class Record{
		int pos;
		int counter;
		Record(int p, int c){
			pos = p;
			counter = c;
		}
	}
	
	//using hash to store the counter and position of each character
	public static char findFirstNoRepeatUsingHash(String source){
		if(source==null || source.isEmpty())
			return '\0';
		
		Map<Character, Record> store = new HashMap<Character, Record>();
		for(int i=0; i<source.length(); i++){
			if(store.containsKey(source.charAt(i))){
				store.get(source.charAt(i)).counter++;
			}else{
				Record r = new Record(i, 1);
				store.put(source.charAt(i), r);
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(Character c : store.keySet()){
			if(store.get(c).counter==1 && store.get(c).pos<min)
				min = store.get(c).pos;
		}
		return source.charAt(min);
	}
	
	public static char findFirstNoRepeatByQ(String source){

		int[] repeated = new int[256];
		Deque<Character> firstAppear = new ArrayDeque<Character>();
	
		for(int i=0; i<source.length(); i++){			
			char c = source.charAt(i);
			repeated[c]++;		
			if(repeated[c] == 1){
				firstAppear.add(c);
			}		
		}
		
		char result = '\0';
		
		while(!firstAppear.isEmpty()) {
			result = firstAppear.removeFirst();
			if(repeated[result]==1)
				return result;
		}
					
		return '\0';
	}
	
	/*
	 * http://www.geeksforgeeks.org/given-a-string-find-its-first-non-repeating-character/
	 */
	public static char findFirstNoRepeat(String source){

		int[] repeated = new int[256];
		int[] firstAppear = new int[256];//position starts at 1..source.length(), 
		
		DLLNode head = null;
		DLLNode tail = null;
		HashMap<Character, DLLNode> map = new HashMap<Character, DLLNode>();		
		
		for(int i=0; i<source.length(); i++){			
			char c = source.charAt(i);
			repeated[c]++;		
			if(repeated[c] == 1){
				firstAppear[c] = i + 1;
				
				if(head == null){
					head = new DLLNode(c, null, null);
					tail = head;
					map.put(c, head);
				}else{					
					DLLNode newTail = new DLLNode(c, null, tail);
					tail.next = newTail;
					tail = newTail;	
					map.put(c, newTail);
				}
			}else{
				DLLNode deleted = map.get(c);
				DLLNode pre = deleted.pre;
				DLLNode next = deleted.next;
				if(pre == null)
					head = next;
				else if(next == null){
					pre.next = null;
					tail = pre;
				}else{
					pre.next = next;
					next.pre = pre;
				}
				map.remove(c);
			}			
		}
		
		int pos = 0;
		
		for(int i=0; i<repeated.length; i++){
			if(repeated[i] == 1 && firstAppear[i]>0){
				if(pos == 0 || pos > firstAppear[i])
					pos = firstAppear[i];
			}
		}
		
		if(pos>0)
			return source.charAt(pos-1);
		else
			return '\0';
	}
	
	public static String reverseOrder(String st){
		if(st==null || st.isEmpty())
			return st;
		
		char[] list = st.toCharArray();
		
		for(int i=0, j=list.length-1; i<j; i++, j--){			
			char first = list[i];
			list[i] = list[j];
			list[j] = first;					
		}
		
		int nextBlank = 0;
		for(int i=0; i<list.length; i++){
			nextBlank = i;
			while(nextBlank<list.length && list[nextBlank]!=' ')
				nextBlank++;
			
			for(int j=i, k=nextBlank-1; j<k; j++,k--){
				char first = list[j];
				list[j] = list[k];
				list[k] = first;
			}
			
			while(nextBlank<list.length && list[nextBlank]==' ')
				nextBlank++;
			
			i = nextBlank-1 ;			
		}
		
		return String.valueOf(list);
	}
	
	/*
	 * case insensitive search
	 */
	public static String findMinWindow(String str, String tar){
		int[] needFound = new int[256];
		int[] hasFound = new int[256];
		
		for(int i=0; i<tar.length(); i++)
			needFound[tar.charAt(i)-'a']++;
		
		int start = 0;
		int end = 0;		
		int counter = 0;
		
		int window = 0;
		int minStart = 0;
		
		for(int i=0; i<str.length(); i++){
			int k = str.charAt(i)-'a';
			if(needFound[k]==0)
				continue;
			
			if(needFound[k]>=1){
				hasFound[k]++;				
				if(hasFound[k]<=needFound[k])
					counter++;		
			}
			
			if(counter>=tar.length()){
				end = i;
				int m = str.charAt(start)-'a';
				while(needFound[m]==0 || needFound[m]<hasFound[m]){
					if(needFound[m]<hasFound[m])
						hasFound[m]--;
					start++;	
					m = str.charAt(start)-'a';
				}
				
		
				if(window==0){
					window=end-start;
					minStart = start;
				}else if(window>end-start){
					window = end-start;
					minStart = start;
				}
			
			}
			
		}
		
		return str.substring(minStart, window+1);
		
	}
	
	public static String longestPalindromicSubStr(String s){
		if(s==null || s.isEmpty())
			return null;
		
		if(s.length()==1)
			return s;
		
		int n = s.length();
		
		boolean[][] isPali = new boolean[n][n];
		
		for(int i=0; i<n; i++)
			isPali[i][i] = true;
		
		for(int i=0; i<n-1; i++){
			if(s.charAt(i) == s.charAt(i+1))
				isPali[i][i+1] = true;
		}
		
		for(int gap = 2; gap<n-2; gap++){
			for(int i=0; i<n && i + gap<n; i++){
				int j = i + gap;
				isPali[i][j]  = (s.charAt(i) == s.charAt(j) &&  isPali[i+1][j-1]); 
			}
		}
		
		int maxL = 0;
		int maxR = 0;
		for(int i=0; i<n; i++){
			for(int j=i; j<n; j++){
				if(isPali[i][j]){
					if(j-i>maxR-maxL){
						maxR = j;
						maxL = i;
					}
				}
			}
		}
		
		return s.substring(maxL, maxR+1);
	} 
	
	public static String findLongestRepeatSubString(String s){
		if(s==null || s.isEmpty())
			return null;
		 
		int[][] solution = new int[s.length()+1][s.length()+1];
		int max = 1;
		int maxIndex = 0;
		for(int i=0; i<=s.length(); i++){
			solution[i][0] = 0;
			solution[0][i] = 0;
		}
		
		for(int i=1; i<=s.length(); i++){
			for(int j = 1; j<=s.length(); j++){
				if(s.charAt(i-1) == s.charAt(j-1) && Math.abs(i-j)>solution[i-1][j-1])
					solution[i][j] = solution[i-1][j-1] + 1;
				else
					solution[i][j] = solution[i-1][j-1];
				
				if(solution[i][j] > max){
					max = solution[i][j];
					maxIndex = Math.max(i, j);											
			}
			}
		}
		
		System.out.println(max + " - " + maxIndex);
		if(max>0){
			return s.substring(maxIndex-max, maxIndex);
		}
		
		return null;
		
	}
	
	public static String findDupSubString(String s){
		if(s==null || s.isEmpty())
			return null;
		
		char[] charArray = s.toCharArray();
		
		char current = charArray[0];
		int dupNum = 1;
		int max = 1;
		String result = s.substring(0, max);
		for(int i = 1; i< charArray.length; i++){
			if(charArray[i]==current)
				dupNum++;
			else{
				if(dupNum > max){
					max = dupNum;
					result =  s.substring(i-max, i);
				}
				current = charArray[i];
				dupNum = 1;
				
			}
		}
		
		if(dupNum > max){
			max = dupNum;
			result =  s.substring(charArray.length-max, charArray.length);
		}
		return result;
	}
	
	/*
	 * input : aaabbccdeeabb output should be : a3b2c2de2ab2
	 */
	public static String countDuplicates(String s){
		if(s==null || s.isEmpty())
			return null;
		
		char[] charArray = s.toCharArray();
		
		char charZero = '0';
		
		char pre  = charArray[0];
		int pos = 0 ;
		int counter = 1;
		for(int curr = 1; curr < s.length(); curr++){
			if(charArray[curr] == pre)
				counter ++;
			else{
				pos ++ ;
				if(counter > 1){
					charArray[pos] = (char)(counter+charZero);
					pos++;
				}	
				charArray[pos] = charArray[curr]; 
				counter = 1;
				pre = charArray[curr];
			}			
		}
		
		if(counter>1)
			charArray[++pos] = (char)(counter+charZero);
		
		while(pos < s.length() - 1){
			charArray[++pos] = '\0';
		}
		return s + " => " + String.valueOf(charArray);		
	}
	
	/*
	 * do it iteratively
	 */
	public static int findLongestCommonSubsequence2(String s1, String s2){
		char[] cArray1 = s1.toCharArray();
		char[] cArray2 = s2.toCharArray();
		
		int[][] lcs = new int[s1.length()+1][s2.length()+1];
		
		for(int i=0; i<cArray1.length; i++){		
			for(int j=0; j<cArray2.length; j++){				
				if(cArray1[i] == cArray2[j])
					lcs[i+1][j+1] = lcs[i][j] + 1;
				else
					lcs[i+1][j+1] = Math.max(lcs[i+1][j], lcs[i][j+1]);				
			}
		}
		
		return lcs[s1.length()][s2.length()];
		
	}
	
	/**
	 * do it recursively
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int findLongestCommonSubsequence(String s1, String s2){
		if(s1.isEmpty() || s2.isEmpty())
			return 0;
		
		String substring1 = s1.substring(0, s1.length()-1);
		String substring2 = s2.substring(0, s2.length()-1);
		
		if(s1.charAt(s1.length()-1) == s2.charAt(s2.length()-1)) {			
			return 1+StringQ.findLongestCommonSubsequence(substring1, substring2);
		} else{
			return Math.max(StringQ.findLongestCommonSubsequence(s1, substring2), StringQ.findLongestCommonSubsequence(substring1, s2));
		}
		
	}
	
	public static boolean isSubStr(String target, String match){
		assert(target!=null && match !=null);
		int mSize = match.length();
		int tSize = target.length();
		assert(tSize>=mSize);
		
		char[] tChar = target.toCharArray();
		char[] mChar = match.toCharArray();
		
		for(int i=0; i<=tSize-mSize; i++){
			int temp = i;
			int j;
			for(j=0; j<mSize; j++){
				if(tChar[i]==mChar[j]){
					i++;
				}else{
					i = temp;
					break;
				}
			}
			if(j==mSize)
				return true;
		}		
		return false;
	}
	
	public static int isSubStrDP(String target, String match){
		boolean[][] matched = new boolean[target.length()][match.length()];
		
		for(int i=0; i<target.length(); i++){
			if(target.charAt(i) == match.charAt(0))
				matched[i][0] = true;
		}
		
		for(int j=1; j<match.length(); j++){
			for(int i=j; i<target.length(); i++){			
				if(matched[i-1][j-1] && target.charAt(i) ==  match.charAt(j))
					matched[i][j] = true;
			}
		}
				
		for(int i=0; i<target.length(); i++){
			if(matched[i][match.length()-1])
				return i;
		}
		
		return -1;
	}
	
}
