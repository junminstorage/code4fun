package org.blueocean.leetcode;

import java.util.ArrayList;
import java.util.List;

public class RecursionQ {
	
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
