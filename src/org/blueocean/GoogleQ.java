package org.blueocean;

import java.util.HashSet;
import java.util.Set;

public class GoogleQ {
	
	
	class JaccardDistance {
		
		final static short ALPHABET_SIZE = 256;
		public float jaccard(String s1, String s2){		
			boolean[] has1 = new boolean[ALPHABET_SIZE];
			for(char c : s1.toCharArray())
				has1[c] = true;			
			boolean[] has2 = new boolean[ALPHABET_SIZE];
			for(char c : s2.toCharArray())
				has2[c] = true;			
			int intersect = 0, union = 0;
			for(int i=0; i<ALPHABET_SIZE; i++){
				if(has1[i]&&has2[i])
					intersect++;
				if(has1[i]||has2[i])
					union++;
			}
			return (float)intersect/(float)union;
		}
		
		public float jaccard2(String s1, String s2){		
			Set<Character> union = new HashSet<Character>();
			Set<Character> intersect = new HashSet<Character>();
			for(char c: s1.toCharArray()){
				union.add(c);
				intersect.add(c);
			}
			
			Set<Character> set2 = new HashSet<Character>();
			for(char c: s2.toCharArray())
				set2.add(c);
			
			union.addAll(set2);
			intersect.retainAll(set2);
						
			return (float)intersect.size()/(float)union.size();
		}
	}
	
	
	class MovingAverage {
	
		private int capacity;
		private int size;
		private int pos;
		private double sum;
		private double[] buffer;
		  MovingAverage(int N) {
			  this.capacity = N;
			  buffer = new double[N];
		  }
		 
		  // Returns average of past N values passed to add()
		  public double add(double value) {
			  double average;
			  size++;
			  if(size<=capacity){
				  sum = sum + value;
				  average = sum / size;
			  }else{
				  sum = sum - buffer[pos] + value;
				  average = sum/capacity;
			  }
			  
			  buffer[pos] = value;
			  pos = (++pos)%capacity;
			  
			  return average;
			 
		  }

	}

}
