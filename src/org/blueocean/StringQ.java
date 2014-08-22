package org.blueocean;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

public class StringQ {
	
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
