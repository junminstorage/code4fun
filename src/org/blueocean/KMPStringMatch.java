package org.blueocean;

public class KMPStringMatch {
	 
   
    public int[] preProcessPattern(char[] pattern) {
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
 
    public void searchSubString(char[] text, char[] pattern) {
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
                j = b[j];
            }
        }
    }
 
}
