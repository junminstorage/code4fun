package org.blueocean;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class StringQ {
	
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
					if(diff==2)
						return false;
				}
			}
		}
		
		return true;
	}
	
	public static int distance(String s1, String s2){
		int[][] edits = new int[s1.length()][s2.length()];
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
	
	/*
	 * Code a function that receives a string composed by words separated by spaces and returns a string where words appear in the same order but than the original string, but every word is inverted. 
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
	 * Given a string having no spaces, and a dictionary.Problem was to find if that string can be splitted in multiple strings such that all the splitted
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
