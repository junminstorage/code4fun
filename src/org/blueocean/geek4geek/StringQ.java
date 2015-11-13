package org.blueocean.geek4geek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringQ {
	
	public static void printSuperSetIteratively(char[] chars){
		int size = chars.length;
		int superSetSize = (int)Math.pow(2, size);
		List<List<Character>> list2 = new ArrayList<List<Character>>();
		for(int i=0; i<superSetSize; i++){
			
			List<Character> list = new ArrayList<Character>();
			for(int j=0; j<size; j++){
				if((i&1<<j)>0){
					list.add(chars[j]);
				}
			}
			list2.add(list);
		}
		
		System.out.println(list2);
	}
	
	
	public static void printSuperSet(char[] chars){
		List<List<Character>> re = getSuperSetRec(chars, 0);
		System.out.println(re);
	}
	
	private static List<List<Character>> getSuperSetRec(char[] chars, int index){
		if(index == chars.length){
			List<List<Character>> list = new ArrayList<List<Character>>();
			List<Character> l = Collections.emptyList();
			list.add(l);
			return list;
		}
		else{
			List<List<Character>> list = getSuperSetRec(chars, index+1);
			List<List<Character>> res = new ArrayList<List<Character>>(list);
			for(List<Character> l : list){
				List<Character> temp = new ArrayList<Character>(l);
				temp.add(chars[index]);
				res.add(temp);
			}
			return res;
		}
	}
	
	public static String LCS(String s1, String s2){
		int len1 = s1.length();
		int len2 = s2.length();
		int[][] table = new int[len1+1][len2+1];
		//int[0][] = 0 and int[][0] = 0;
		
		int max = 0;
		int maxRow = -1;
		int maxCol = -1;
		for(int r=1; r<=len1; r++){
			for(int c=1; c<=len2; c++){
				if(s1.charAt(r-1) == s2.charAt(c-1))
					table[r][c] = table[r-1][c-1]+1;
				else
					table[r][c] = 0;
				if(table[r][c]>max){
					max = table[r][c];
					maxRow = r;
					maxCol = c;
				}
			}
		}
		
		return s1.substring(maxRow-max, maxRow);
		
	}

}
