package org.blueocean.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringQ {
	
	/*
	 * Given a random string S and another string T with unique elements,. 
	 * find the minimum consecutive sub-string of S such that it contains all the elements in T
example: 
S='adobecodebanc' 
T='abc' 
answer='banc"
	 */
	public static String miniSubstr(String s, String t){
		//q represents a sliding window which contains all characters found so far
		Deque<Item> q = new ArrayDeque<Item>();
		//map contains the character found and number of each char found in the sliding window
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int p=0, min=Integer.MAX_VALUE;
		String result = null;	
		while(p<s.length()){
			char c = s.charAt(p);
			//if found
			if(t.indexOf(c)>-1){
				//update the sliding window
				q.add(new Item(c, p));	
				//update the counter map
				if(!map.containsKey(c))
					map.put(c, 1);
				else 
					map.put(c, map.get(c)+1);	
				//shrink the sliding window if char in the head of q appears more than once in the q
				while(true){
					Item item = q.peek();
					Integer num = map.get(item.c);
					if(num>1){
						q.removeFirst();
						map.put(item.c, num-1);
					}else
						break;
				}
			}
			
			//when we find all characters, then we update result
			if(map.size() == t.length()){
				int current =  q.peekLast().p - q.peekFirst().p;
				if(current<min){
					min = current;
					result = s.substring(q.peekFirst().p, q.peekLast().p+1);
				}
			}
			
			p++;
		}		
		return result;
	}
	
	static class Item{
		char c;
		int p;
		Item(char c, int t){
			this.c = c; this.p = t;
		}
	}
	
	public static List<Integer> subStringWithAllWords(String s, Set<String> words){
		int wlen = 0;
		for(String w:words){
			wlen = w.length();
			break;
		}
		
		for(int i=0; i+wlen<=s.length(); i++){
			checkSubStr(s, words, wlen, i, i);
		}
		return result;
	}


	private static void checkSubStr(String s, Set<String> words, int wlen, int i, int start) {
		String substring = s.substring(i, i+wlen);
		if(words.contains(substring)){
			Set<String> copy = new HashSet<String>(words);
			copy.remove(substring);
			subStringWithAllWordsUtil(s, i+wlen, start, wlen, copy);
		}
	}
	
	static List<Integer> result = new ArrayList<Integer>(); 
	
	/*
	 * this method recursively walks down the string by wlen 
	 * until either the substring is not a word or all words are found
	 */
	static void subStringWithAllWordsUtil(String s, int index, int start, int wlen, Set<String> words){
		if(words.isEmpty()){
			result.add(start);
			return;
		}
		
		if(index+wlen<=s.length()){
			checkSubStr(s, words, wlen, index, start);		
		}
	}
	
	
	/*
	 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
	 * return the length of last word in the string. If the last word does not exist, return 0.
	 */
	public static int lengthOfLast(String s){
		int len = s.length();
		int i = len - 1;
		while(i>-1 && s.charAt(i)==' ')
			i--;
		int count = 0;
		while(i>-1 && s.charAt(i)!=' '){
			i--;
			count++;
		}		
		return count;
	}
	public static int lengthOfLastWord(String s){
		int len = s.length();
		int i = 0;
		while(s.charAt(i)==' ')
			i++;
		boolean inside = true;
		boolean exist = false;
		int count = 0;
		while(i<len){
			char c = s.charAt(i);
			if(inside){
				if(c == ' '){
					exist = true;
					inside = false;
				}else
					count++;
			}else{
				if(c != ' '){
					inside = true;
					count=1;
				}
			}
			
			i++;
		}
		
		return exist?count:0;
	}
	
	
	
	public static boolean matchReg(String str, String reg) {
	    return matchRegUtil(str, 0, reg, 0);
	}

	public static boolean matchRegUtil(String str, int strPos, String reg, int regPos) {
	    if(strPos>=str.length() && regPos>=reg.length()) {
	        return true;
	    }
	    else if(regPos>=reg.length()) {
	        return false;
	    }
	    else if(strPos>=str.length()) {
	        for(int i=regPos; i<reg.length(); i++) {
	            if(reg.charAt(i)!='*') {
	                return false;
	            }
	        }
	        return true;
	    }
	    if(reg.charAt(regPos)==str.charAt(strPos)||reg.charAt(regPos)=='?') {
	        return matchRegUtil(str, strPos+1, reg, regPos+1);
	    }
	    else if(reg.charAt(regPos)=='*'){
	        for(int i=strPos-1; i<str.length(); i++) {
	            if(matchRegUtil(str, i+1, reg, regPos+1)) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	public static boolean isMatch(String s, String ex){
		char c1, c2, pre = '\0';
		int p1=0, p2=0, len1 = s.length(), len2 = ex.length();
		
		while(p1<len1 && p2<len2){
			c1=s.charAt(p1);
			c2=ex.charAt(p2);
			
			if(c2 == '.'){//a - .
				p1++;p2++;pre='.';
			}else if(c2 == '*'){
				if(pre == '.' || pre == c1 )//aa - .* or //aa - a*
					p1++;
				else{//ac - a*
					p2++;pre='\0';
				}
			}else{
				if(c1==c2){//a - a
					p1++;p2++;pre=c1;
				}else{//a - c*
					if(p2+1<len2&&ex.charAt(p2+1)=='*'){
						p1++;p2++;p2++;pre='\0';
					}else
						return false;
				}
			}
		}
		
		return p1==len1 && (p2==len2 || (p2==len2-1 && ex.charAt(p2) =='*'));
	}

}
