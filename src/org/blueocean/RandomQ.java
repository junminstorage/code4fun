package org.blueocean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RandomQ {
	
	public static void shuffle(List<?> list, Random rnd) {
	    for (int i = list.size(); i > 1; i--)
	        swap(list, i - 1, rnd.nextInt(i));
	}
	
	public static <E> void swap(List<E> a, int i, int j) {
	    E tmp = a.get(i);
	    a.set(i, a.get(j));
	    a.set(j, tmp);
	}
	
	/*
	 * Suppose we are given a set L of n line segments in the plane, where the endpoints of each 
segment lie on the unit circle x^2 + y^2 = 1, and all 2n endpoints are distinct. Describe 
and analyze an algorithm to compute the largest subset of L in which no pair of segments 
intersects.

http://www.careercup.com/question?id=5685788633202688
	 */
	
	/*
	*  Code a function that receives an array with duplicates and 
	*  returns a new array keeping the original order *  of the elements but with the duplicates removed. 
	*/

	public static <T> T[] removeDuplicates(T[] input){
		LinkedHashMap<T, Boolean> map = new LinkedHashMap<T, Boolean>();
	    
	    for(int i=0; i<input.length; i++){
	        if(!map.containsKey(input[i])){
	            map.put(input[i], true);
	        }
	    }    
	    
	    Set<T> keys = map.keySet();
	    
	    return (T[]) keys.toArray();
	}

	/*
	 * http://www.geeksforgeeks.org/shuffle-a-given-array/
	 * Fisher–Yates shuffle Algorithm
	 */
	public static void reservoir(int[] numbers){
		for(int i=numbers.length-1; i>0; i--){//no need to choose first
			int pick = (int)Math.random()*(i+1);
			int t = numbers[i];
			numbers[i] = numbers[pick];
			numbers[pick] = t;
		}
	}
	
	/*
	 * reservoir sampling
	 */
	public static int[] reservoir(int k, int[] numbers){
		assert(k>0);
		int[] result = new int[k];
		for(int i=0;i<k;i++)
			result[i] = numbers[i];
		
		for(int i=k; i<numbers.length; i++){
			int j = (int) (Math.random()*(i+1));
			if(j<k)
				result[j] = numbers[i];
		}
		
		return result;
	}
	
	/*
	 * Given a function bool Zoo(int x) and its called for y times ,
	 *  how you will generate the true with probability x/y times .
	 */
	static int counter = 0;;
	public boolean generate(int x){		
		
		
		if(counter++<x)
			return true;
		else
			return false;
	}
	
	public int y;
	public boolean zoo(int x){		
		int r = (int) (Math.random()*y+1);
		System.out.println(r + "-" + y);
		if(r<=x)
			return true;
		else
			return false;
		
	}
	
	/*
	 * http://www.geeksforgeeks.org/print-all-combinations-of-balanced-parentheses/
	 */
	public static void printAllParentheses(int num){
		printAllParentheses("", 0, num);
	}
	public static void printAllParentheses(String pre, int numOfLeft, int num){
		if(pre.isEmpty()){
			printAllParentheses("(", 1, num);
		}
		else if(pre.length()==num*2)
			System.out.println(pre);
		else{
			int numOfRight = pre.length() - numOfLeft;
			if(numOfLeft>numOfRight)
				printAllParentheses(pre+")", numOfLeft, num);
			
			if(numOfLeft<num)
				printAllParentheses(pre+"(", numOfLeft+1, num);
		}
	}
	
	public static List<String> allWordsFromPhonePad(int number){
		HashMap<Integer, String > h = new HashMap<Integer, String>(){{
	        put(1,"");
	        put(2, "ABC");
	        put(3, "DEF");
	        put(4, "GHI");
	        put(5, "JKL");
	        put(6, "MNO");
	        put(7, "PQRS");
	        put(8, "TUV");
	        put(9, "WXYZ");
	        put(0, "");
	    }};
		
	    List<String> result = new ArrayList<String>();
	    result.add("");
	    while(number>0){
	    	int last = number%10;
	    	List<String> temp = new ArrayList<String>();
		    
	    	for(String s: result){
	    		String map = h.get(last);
	    		for(char c : map.toCharArray()){
	    			temp.add(String.valueOf(c) + s);
	    		}
	    	}	    	
	    	number = (number-last)/10;
	    	if(!temp.isEmpty())
	    		result = temp;
	    }
	    
	    return result;
	}
	
	public static void allParenthesis(int k){
		Map<String, Integer> list = new HashMap<String, Integer>();
		list.put("(", 1);
		
		for(int i = 1 ; i < k*2; i++){
			Map<String, Integer> temp = new HashMap<String, Integer>();
			
			for(String s : list.keySet()){					
				
				int left = list.get(s);
				int right = s.length() - left;
				
				if(left<k){						
					temp.put(s + "(", left+1); 
				}
				if(left>right){
					temp.put(s + ")", left);
				}				
			}			
			list = temp;		
		}
		
		//for(String s: list.keySet())
			System.out.println(list.size());
		
	}
	
	
	/**
	 * check if two squres overlap
	 * @param a1 square1 top left 
	 * @param a2 square2 botton right
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static boolean overlapSquare(int[] a1, int[] a2, int[] b1, int[] b2){
		boolean x = a1[1] < b2[1] || a2[1] >b1[1] || a2[0] < b1[0] || b2[0] < a1[0];		
		return !x;
	}
	
	public static int kthMin2(int[]x, int[]y, int k){
		assert(x!=null && y!=null && k==0);
		
		int m = x.length;
		int n = y.length;
		
		if(k>=m+n)
			return Math.max(x[m-1], y[n-1]);
		else{
			if(k==1)
				return Math.min(x[0], y[0]);
			
			int i = -1;
			int j = -1;
			
			while(i+j<k-2){
				int a = (i+1<x.length)? x[i+1]: Integer.MAX_VALUE;
				int b = (j+1<y.length)? y[j+1]: Integer.MAX_VALUE;
				
				if(a<b)
					i++;
				else
					j++;
			}
			//System.out.println(i + " - " + j);
			return Math.max(x[i], y[j]);
		}
		
	}
	
	
	public static int kthMin(int[]x, int[]y, int k){
		assert(x!=null && y!=null && k==0);
		
		int m = x.length;
		int n = y.length;
		
		if(k>=m+n)
			return Math.max(x[m-1], y[n-1]);
		else{
			if(m>n)
				return kthMin(y, x, k, 0, n-1);
			else
				return kthMin(x, y, k, 0, m-1);
		}		
	}
	
	/**
	 * x is shorter than y
	 * @param x
	 * @param y
	 * @param k
	 * @param x1
	 * @param x2
	 * @return
	 */
	
	
	private static int kthMin(int[] x, int[]y, int k, int x1, int x2){
		//System.out.println(x1 + "-" + x2);
		int m = x2-x1;
		int m2 = (x1+x2)/2;
		
		if(m==0){
			if(k>=2)
				return Math.max(y[k-2], x[x1]); 
			else
				return Math.min(y[0], x[x1]);
		}
		
		if(k>m2+1+y.length)
			return kthMin(x, y, k, m2, x2);
		
		if(k<=m2+1){
			if(x[m2]<=y[0])
				return x[k-1];
			else
				return kthMin(x, y, k, x1, m2);
		}else{	
			int r = Integer.MAX_VALUE;
			if(k-m2-1 < y.length)
				r = y[k-m2-1];
			if(x[m2+1]<r && x[m2+1] >y[k-m2-2])
				return Math.max(x[m2], y[k-m2-2]);
			else{
				if(x[m2+1]>r)
					return kthMin(x,y,k,x1,m2);
				else{
					if(x2-m2==1)
						return x[m2+1];
					else
						return kthMin(x,y,k,m2,x2);
				}
			}
		}			
	}
	
	/**
	 * always RIGHT!
	 * @param x
	 * @param y
	 * @param k
	 * @param x1
	 * @param x2
	 * @return
	 */
	public int median(int[]x, int[]y, int k, int x1, int x2){
		int m2 = (x1+x2)/2;
		int n2 = k-m2;
		
		if(x[m2+1] > y[n2] && x[m2+1] < y[n2+1]){
			int k1 = Math.max(x[m2], y[n2]);
			int k2 = Math.min(x[m2], y[n2]);
			return k1+k2;
		}else if(x[m2+1] > y[n2+1]){
			return median(x, y, k, x1, m2);
		}else{
			return median(x, y, k, m2, x2);
		}
		
	}
	
	public int median(int[] x, int[] y){
		assert(x!=null && y!=null);
		
		int m = x.length/2;
		int n = y.length/2;
		int k = (m+n)/2;		
		/**
		 * find kth and kth+1 min 
		 */		
		return median(x, y, k, 0, x.length-1);
		
	}
	
	/*
	 * implement the MIT solution
	 * http://www2.myoops.org/course_material/mit/NR/rdonlyres/Electrical-Engineering-and-Computer-Science/6-046JFall-2005/30C68118-E436-4FE3-8C79-6BAFBB07D935/0/ps9sol.pdf
	 */
	public static int findMedianMit(int[] x, int[] y){
		assert(x!=null && y!=null);		
		/**
		 * find kth and kth+1 min 
		 */		
		return median(x, y, 0, x.length-1);		
	}

	private static int median(int[] x, int[] y, int l, int r) {
		int m = x.length;
		int n = y.length;
		
		if(l>r)
			return median(y, x, Math.max(0,(m+n)/2-l), Math.min(n-1, (m+n)/2-l));

		
		int i = (l+r)/2;
		int j = (m+n)/2 - i-1;
		
		int yL = j<0?Integer.MIN_VALUE : y[j];
		int yR = (j+1)>n-1?Integer.MAX_VALUE : y[j+1];
		
		if(x[i] >= yL && x[i] <= yR)// NEED TO HANDLE EVEN/ODD CASES
			return x[i];
		else if(x[i] < yL)
			return median(x, y, i+1, r);
		else
			return median(x, y, l, i-1);
	
	}
	
	

}
