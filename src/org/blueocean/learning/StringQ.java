package org.blueocean.learning;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;


public class StringQ {
	
	/*
	 * http://www.geeksforgeeks.org/amazon-interview-experience-set-149-campus-internship/
	 */
	
	public static class Result implements Comparable<Result>{
	    String w;
	    String n;
	    int index;
	    
	    public Result(String wi, String ni, int i){
	        this.w = wi;
	        this.n = ni;
	        this.index = i;
	    }
	    @Override
	    public int compareTo(Result o){
	        if(n.compareTo(o.n)==0)
	            return index - o.index;
	        return o.n.compareTo(n);
	    }
	    
	}
	public static void keypad(String[] words){
	    Set<Result> re = new TreeSet<Result>();
	    for(int i=0; i<words.length; i++){
	    String w = words[i];
	    StringBuilder sb = new StringBuilder();
	    for(int j=0; j<w.length(); j++){
	        char c = w.charAt(j);
	        if(c>='A' && c<='Z')
	            c = (char)(c + 'a'- 'A');
	        int m = (c-'a')/3 + 2;
	        if(m==10)
	            m = 9;    
	        sb.append(m);
	    }
	    re.add(new Result(w, sb.toString(), i));
	    }
	    
	    for(Result r : re){
	        System.out.println(r.n + '\t' + r.w);
	    }
	    
	}
	
	boolean[] extractWord(String text){
		final int N = 2^20;
		
		int[] rand = new int[256];
		int[] codetable = new int[256];
		for(int i=0; i<256; i++){
			codetable[i] = isWord((char)i) ? rand[i] : 0;
		}
		
		boolean fv[] = new boolean[N];
		int wordhash = 0;
		for(char c: text.toCharArray()){
			int code = codetable[c];
			if(code!=0)
				wordhash = wordhash>>1 + code;
				else{
					if(wordhash!=0)
						fv[wordhash%N] = true;
						wordhash = 0;
				}
		}
		
		return fv;
	}
	
	private boolean isWord(char i) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	http://www.careercup.com/page?pid=facebook-interview-questions&n=5
	O(len*m^len)
	*/

	public List<String> printComb(Map<String, List<Character>> map, String s){
	    int len = s.length();
	    List<String> result = new ArrayList<String>();
	    result.add("");
	    for(int i = 0; i<len; i++){
	        List<String> temp = new ArrayList<String>();
	        char c = s.charAt(i);
	        for(String sV : result){	            
	            for(Character cv : map.get(c))
	                temp.add(String.valueOf(sV.concat(cv.toString())));
	        }
	        
	        result = temp;
	    }

	    return result;
	}
	
	/*
	 * Given a current absolute path, e.g., "/usr/bin/mail", and 
	 * a relative one, e.g, "../../../etc/xyz/../abc" 
	 * return the absolute path created from the combination of the first two paths. 
	 * In the example strings, the answer should be "/etc/abc".
	 */

	public static String printPath(String ab, String rel){
		Stack<String> s = new Stack<String>();
		String[] temp = ab.split("/");
		for(String st : temp){
			if(!st.isEmpty())
				s.push(st);
		}

		temp = rel.split("/");
		for(String st : temp){
			if(st.equals(".."))
				s.pop();
			else{
				s.push(st);    
			}
		}

		String result = "/";
		while(!s.isEmpty()){
			result = "/" + s.pop() + result;
		}
		return result;
	}
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
