package org.blueocean;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class SearchAndSort {
	
	public static boolean isAnagram(String s1, String s2){
		char[] s1Chars = s1.toCharArray();
		char[] s2Chars = s2.toCharArray();			
		Arrays.sort(s1Chars);
		Arrays.sort(s2Chars);
		System.out.println(String.valueOf(s1Chars));
		System.out.println(String.valueOf(s2Chars));
		
		return new String(s1Chars).equals(new String(s2Chars));
	}
	
	
	public void sortAnagram(String[] s){
		Arrays.sort(s, new AnagramComparator());
	}
	
	public class AnagramComparator implements Comparator<String>{
		private boolean isAnagram(String s1, String s2){
			char[] s1Chars = s1.toCharArray();
			char[] s2Chars = s2.toCharArray();			
			Arrays.sort(s1Chars);
			Arrays.sort(s2Chars);
			return s1Chars.toString() == s1Chars.toString();
		}
		
		@Override
		public int compare(String o1, String o2) {
			if(this.isAnagram(o1, o2))
				return 0;
			else
				return o1.compareTo(o2);
		}
		
	}
	
	public static int[] merge(int[] a, int []b){
		int sizeA = 0;
		for(int i=0; i<a.length; i++)
			if(a[i]!=0)
				sizeA++;
			else
				break;
		
		
		int sizeB = b.length;		
		int curA = sizeA-1;
		int curB = sizeB-1;
		
		for(int i = sizeA+sizeB-1; i>=0; i--){
			if(curA==-1&&curB>=0){
				a[i]=b[curB];
				curB--;
			}			
			else if(curB==-1&&curA>=0){
				a[i]=a[curA];
				curA--;
			}				
			else if(a[curA] > b[curB]){
				a[i]= a[curA];
				a[curA]=0;
				curA--;
			}
			else{
				a[i]=b[curB];
				curB--;
			}			
		}
		
		
		return a;
	}

}
