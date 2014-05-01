package org.blueocean;

public class StringQ {
	
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
}
