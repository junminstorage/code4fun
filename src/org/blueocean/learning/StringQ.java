package org.blueocean.learning;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class StringQ {
	//
	public static Character firstNonRepeatChar(String s){
		int[] found = new int[256];
		char[] chars = s.toCharArray();
		
		for(int i=0; i< chars.length; i++)
			found[chars[i]]++;
		
		for(int i=0; i< chars.length; i++){
			if(found[chars[i]]==1)
				return chars[i];
		}
		
		return null;			
	}
	
	static List<String> result = new ArrayList<String>();	
	public static void permutate(String s, int i){	
		//System.out.println(s);
		if(i==s.length()-1){
			result.add(s);
			return;
		}
		
		for(int j = i; j<s.length(); j++){
			char[] chars = s.toCharArray();
			
			char t = chars[i];
			chars[i] = chars[j];
			chars[j] = t;
			
			permutate(new String(chars), i+1);			
		}			
	}
	
	
	/**
	 * convert string to int
	 * 
	 * @param s
	 * @return
	 */
	public static int atoi(String s){
		char[] chars = s.toCharArray();
		
		int current = 0;
		char sign = '+';
		
		//discard blanks
		while(current<s.length() && chars[current] ==' ' ){
			current++;
		}
		//reach the end, return 0
		if(current==s.length())
			return 0;
		
		char c = chars[current];
		//check the first non-zero char
		if(c!='-' && c!='+' && (c<=48 || c>57))
			return 0;
		
		if(c=='-')
			sign = '-';
		
		if(c=='-' || c=='+')
			current++;
		
		//reach the end or non-1-9, return 0
		if(current>=s.length() || chars[current]<=48 || chars[current]>57)
			return 0;
				
		long result = 0;		
		while(current<s.length() && chars[current]>=48 && chars[current]<=57){
			result = result * 10 + chars[current]-48;
			if(result>=Integer.MAX_VALUE && sign =='+')
				return Integer.MAX_VALUE;
			if(result-1>=Integer.MAX_VALUE && sign =='-')
				return Integer.MIN_VALUE;			
			current++;			
		}
		
		if(sign=='-')
			result = result * -1;
		
		return	Long.valueOf(result).intValue();
	}
	
	/*
	 * public static String longestPalindrome1(String s) {
	 */
	public static String longestPalindrome(String s){
		char[] chars = s.toCharArray();
		String result = "";
		for(int current = 0; current<s.length();current++){
			int pre = current;
			int post = current;	
			while(pre>=0 && post<s.length() && chars[pre] ==  chars[post]){
				--pre;++post;
			}
			pre++;post--;	
			if( result.length() <= s.substring(pre, post+1).length()){
				result = s.substring(pre, post+1);	
			}
		}
		
		for(int current = 0; current<s.length()-1;current++){
			int pre = current;
			int post = current+1;
			while(pre>=0 && post<s.length() && chars[pre] ==  chars[post]){
				--pre;++post;
			}			
			pre++;post--;
			if( result.length() <= s.substring(pre, post+1).length()){
				result = s.substring(pre, post+1);	
			}		
		}
		return result;
	}
	
	/*
	 * �abcbbbbcccbdddadacb�, the longest substring that 
	 * contains 2 unique character is �bcbbbbcccb�.
	 * 
	 */
	public static String longestSubStrWithTwoChars(String s){
		if(s==null || s.isEmpty())
			return null;
		
		char[] chars = s.toCharArray();
		String result = null; //current longest substr with two chars
		
		int current = 0;//current pos in string
		int length = 0; //current longest substr length
		int pos = 0;    //current longest substr starting pos
		
		for(current=0; current<chars.length; current++){
			pos = current;			
			Set<Character> found = new HashSet<Character>();
			while(current<chars.length){
				found.add(chars[current]);
				if(found.size()<3 )
					current++;
				else
					break;
			}
			if(length < current-pos){
					result = s.substring(pos, current);	
					length = current - pos;
			}			
			current = pos;
		}
		return result;
	}
	
	/**
	 * input: aabccdaga 
	 * output: abc
	 * 
	 * @param s
	 */
	public static int LongestSubStrWithUniqueChars(String s){
		if(s==null || s.isEmpty())
			return 0;
		
		char[] chars = s.toCharArray();
		
		int current = 0;//current pos in string
		int length = 0; //current longest substr length
		int pos = 0;    //current longest substr starting pos
		
		for(current =0 ; current<s.length(); current++){
			pos = current;
			boolean[] found = new boolean[256]; 		
			while(current<s.length() && !found[chars[current]]){				
				found[chars[current]] = true;
				current++;
			}			
			length = Math.max(length, current - pos);				
			current = pos;
		}		
		return length;		
	}

	public static void main(String[] arg){
		int j = StringQ.LongestSubStrWithUniqueChars("adrdg");
		System.out.println(j+"");
		
		String  s = StringQ.longestSubStrWithTwoChars("abcbbbbcccbdddadacb");
		System.out.println(s+"");
		
		s = StringQ.longestPalindrome("abcbbbbcccbdddadacb");
		System.out.println(s+"");
		
		j = StringQ.atoi("   x999");
		System.out.println(j);
		
		StringQ.permutate("abcd", 0);
		
		for(String s1 : StringQ.result)
			System.out.println(s1);
		
		Character c = StringQ.firstNonRepeatChar("afdfeedr");
		System.out.println(c);
	}
}
