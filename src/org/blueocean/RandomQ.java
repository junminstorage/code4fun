package org.blueocean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RandomQ {
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

}
