package org.blueocean;

import java.util.ArrayList;
import java.util.List;


public class KMPStringMatch {
	
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
	 
   /*
    * p    :  a   b   c   a   b   d   a   b   c
p[i] :  0   1   2   3   4   5   6   7   8
b[i] : -1   0   0   0   1   2   0   1   2   3
    */
    public static int[] preProcessPattern(char[] pattern) {
        int i = 0, j = -1;
        int pLength = pattern.length;
        int[] b = new int[pLength + 1];
 
        b[i] = j;
        while (i < pLength) {
            while (j >= 0 && pattern[i] != pattern[j]) {
                // if there is mismatch consider next widest border
                j = b[j];
            }
            i++;
            j++;
            b[i] = j;
        }
       
        return b;
    }
 
    public static void searchSubString(char[] text, char[] pattern) {
        int i = 0, j = 0;
        // pattern and text lengths
        int pLength = pattern.length;
        int txtLen = text.length;
 
        // initialize new array and preprocess the pattern
        int[] b = preProcessPattern(pattern);
 
        while (i < txtLen) {
            while (j >= 0 && text[i] != pattern[j]) {
                
                j = b[j];
            }
            i++;
            j++;
 
            // a match is found
            if (j == pLength) {
            	System.out.println("found "+ (i - j));
                j = b[j];
            }
        }
    }
 
}
