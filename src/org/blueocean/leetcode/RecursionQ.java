package org.blueocean.leetcode;

import java.util.ArrayList;
import java.util.List;

public class RecursionQ {
	
	 public static boolean isMatch2(String str, String reg) {
	        assert str!=null && reg!=null;
	        return matchHelper(str, reg, 0, 0);
	    }

	    public static boolean matchHelper(String str, String reg, int i, int j) {
	        if(i==str.length() && j==reg.length())
	            return true;
	        if((i>=str.length() && str.length()>0) || j>=reg.length())
	            return false;
	        if(j<reg.length() -1 && reg.charAt(j + 1)=='*') // because char after j is *, so we should decide where to skip * in reg
	            if (matchHelper(str, reg, i, j + 2)) {  // consider * is 0 times
	                return true;
	            } else    // consider * happens from 1, 2 and more times
	                for (; i < str.length() && (str.charAt(i) == reg.charAt(j) || reg.charAt(j) == '.'); i++)
	                    if (matchHelper(str, reg, i + 1, j + 2))
	                        return true;
	        return (str.charAt(i)==reg.charAt(j) || reg.charAt(j)=='.') && matchHelper(str, reg, i+1, j+1);
	    }
	
	/*
	 * isMatch(".*", "abc")
	 * isMatch("a*ab", "aaab") 
	 * isMatch("abc", "abc")
	 * isMatch("a*b", "b")
	 */
	public static boolean isMatch(String str, String reg){
		return isMatchRec(str, reg, 0, 0);
	}
	
	private static boolean isMatchRec(String str, String reg, int i, int j){
		int lenS = str.length(), lenR = reg.length();
		//if reach the end of both strings
		if(i==lenS && j==lenR)
			return true;
		/*
		 * a*aa -> aaab
		 * .*aa -> aaasd
		 */
		if(j<lenR && reg.charAt(j)=='*'){
			char pre = '\0';
			if(j-1>=0){
				pre = reg.charAt(j-1);
				//eating all of a after a* in reg
				while(j+1<lenR && reg.charAt(j+1) == pre)
					j++;
				//eating all of a or any chars in str
				while(i<lenS && (pre=='.' || str.charAt(i) == pre))
					i++;
				return isMatchRec(str, reg, i, j+1);
			}	
		}	
		//if reach one of string's end but not both
		if(i==lenS || j==lenR)
			return false;
		
		//if both not finished
		if(str.charAt(i)==reg.charAt(j) || reg.charAt(j)=='.')
			return isMatchRec(str, reg, i+1, j+1);
		//a*b -> b
		if(str.charAt(i)!=reg.charAt(j) && j+1<lenR && reg.charAt(j+1)=='*')
			return isMatchRec(str, reg, i, j+2);
		
		return false;
		
	}
	
	
	public static boolean matchReg(String str, String reg) {
	    return matchRegUtil(str, 0, reg, 0);
	}

	public static boolean matchRegUtil(String str, int strPos, String reg, int regPos) {
	    if(strPos>=str.length() && regPos>=reg.length()) {
	        return true;
	    }
	    else if(strPos>=str.length()||regPos>=reg.length()) {
	        return false;
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
	
	public static List<String> palindromePartitioning(String s) {
		 
		List<String> result = new ArrayList<String>();
	 
		if (s == null)
			return result;
	 
		if (s.length() <= 1) {
			result.add(s);
			return result;
		}
	 
		int length = s.length();
	 
		int[][] table = new int[length][length];
	 
		// l is length, i is index of left boundary, j is index of right boundary
		for (int l = 1; l <= length; l++) {
			for (int i = 0; i <= length - l; i++) {
				int j = i + l - 1;
				if (s.charAt(i) == s.charAt(j)) {
					if (l == 1 || l == 2) {
						table[i][j] = 1;
					} else {
						table[i][j] = table[i + 1][j - 1];
					}
					if (table[i][j] == 1) {
						result.add(s.substring(i, j + 1));
					}
				} else {
					table[i][j] = 0;
				}
			}
		}
	 
		return result;
	}
	
	/*
	 * Given a string s, partition s such that every substring of the partition is a palindrome.
	 */
	/*
	 * we can think all possible palidrome substrings form a tree
	 * each substring is the node
	 * two nodes connect each other when one's starting index is equal to 
	 * the other's end index
	 * 
	 * roots are the palidrome substrings starting at index 0
	 * leaves are the node with starting index equals to string's length
	 * a path from root to leaf is the palidrome partition
	 * 
	 */
	public static boolean hasPaliPartitions(String s, int start){
		//reach leaf level, then we find the palidrome partition
		if(start==s.length())
			return true;
		for(int j=start+1;j<=s.length();j++){
			//depth first search
			if(isPali(s, start, j-1) && hasPaliPartitions(s, j)){
				return true;
			}
		}
		return false;
	}
	
	public static void findAllPali(String s){
		dfs(s, 0, new ArrayList<String>());
	}
	public static void dfs(String s, int start, List<String> current){
		//reach leaf level, then we find the palidrome partition
		if(start==s.length()){
			System.out.println(current);
			return;
		}
		for(int j=start+1;j<=s.length();j++){
			//depth first search
			//find one node
			if(isPali(s, start, j-1)){ 
				/*
				 * before we go down this branch
				 * we make copy of the path
				 * then pass it down
				 */
				List<String> copy = new ArrayList<String>(current);
				copy.add(s.substring(start, j));
				dfs(s, j, copy);
			}
		}
	}
	
	
	public static boolean isPali(String s, int i, int j){
		while(i<j){
			if(s.charAt(i)!=s.charAt(j))
				return false;
			i++;j--;
		}
		return true;
	}

}
