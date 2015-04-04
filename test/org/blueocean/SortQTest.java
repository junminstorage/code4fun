package org.blueocean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.TestCase;

public class SortQTest extends TestCase {
	
	public void testCircleSort(){
		
		SortQ.circleSort(new int[]{1, 3, 0, 2});
		SortQ.circleSort(new int[]{0, 1, 2, 3});
		SortQ.circleSort(new int[]{2, 0, 1, 4, 5, 3});
	}
	
	public void testCircularSort(){
		System.out.println(Arrays.toString(SortQ.circularSort(new int[]{4, 1, 3, 8, 9, 10, -1})));
	}

	public void testinsertionSortTest(){
		SortQ.insertionSort(new int[]{2, 5, 1, 7, 3});
	}
	
	public void testfdsf(){
		
		
		Entry e1 = new Entry('a', 1);
		
		Entry e2 = new Entry('b', 11);
		
		System.out.println(e1 == e2);
		
		System.out.println(e1.equals(e2)); 
		
		System.out.println(e1.hashCode()); 
		
		System.out.println(e2.hashCode()); 
		
		Set<Entry> sorted = new TreeSet<Entry>();
		sorted.add(e2); sorted.add(e1);
		System.out.println(sorted); 
		
		Map<Entry, Integer> sorted3 = new HashMap<Entry, Integer>();
		sorted3.put(e2, 3); sorted3.put(e1, 3);
		System.out.println(sorted3); 
				
		
		Set<Entry> sorted2 = new HashSet<Entry>();
		sorted2.add(e2); sorted2.add(e1);
		
		System.out.println(sorted2); 
	}
	
	public static void sort(String input){
		int[] freq = new int[3];
		for(int i=0; i<input.length(); i++){
			freq[input.charAt(i)-'a']++;
		}
		
		Set<Entry> sorted = new TreeSet<Entry>();
		
		for(int i=0; i<3; i++){
			if(freq[i]>0){
			Entry e = new Entry((char)(i+'a'), freq[i]);
			sorted.add(e);
			}
		}
		
		for(Entry e : sorted){
			int counter = e.counter;
			while(counter-->0)
				System.out.print(e.c);
		}
	}
	
	static class  Entry implements Comparable<Entry>{
		char c;
		int counter;
		
		Entry(char f, int h){this.c = f; this.counter = h;}
		
		@Override
		public int compareTo(Entry o){
			//if(o.counter - this.counter==0)
			//	return this.c - o.c;
			return o.counter - this.counter;
		}
		
		public String toString(){
			return c + "=" + counter;
		}
		
	}
	
	private final static int SIZE = 256;
	public char most(String input){
		int[] freq = new int[SIZE];
		for(int i=0; i<input.length(); i++){
			freq[input.charAt(i)]++;
		}
		int most = 0;
		char result = 0;
		for(int i=0; i<256; i++){
			if(freq[i]>most){
				most = freq[i];
				result = (char) i;
			}
		}
		return result;
	}
}
