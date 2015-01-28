package org.blueocean;

import java.util.LinkedList;
import java.util.Queue;

	 /*
	  * http://www.careercup.com/question?id=6515637035728896
	  */
	public class RadixSort {
	 
		public interface BitRepresentable {
			public boolean getBit(int n);
		}
		
		private static <T extends BitRepresentable> void sortByBit(T[] arr, int bit){
			if (arr==null){return;}
			Queue<T> trueQueue = new LinkedList<T>();
			Queue<T> falseQueue = new LinkedList<T>();
	 
			for (int i=0;i<arr.length;i++){
				if (arr[i].getBit(bit)){trueQueue.offer(arr[i]);}
				else {falseQueue.offer(arr[i]);}
			}
			int i=0;
			while (!falseQueue.isEmpty()){arr[i++] = falseQueue.poll();}
			while (!trueQueue.isEmpty()){arr[i++] = trueQueue.poll();}
		}
	 
		public static <T extends BitRepresentable> void radixSort(T[] arr, int numOfBits){
			if ((arr==null) || (numOfBits<1)){return;}
			for (int bit=0;bit<numOfBits;bit++){sortByBit(arr,bit);}
		}
	
	 
	public static class UniqueArrayElement {
	 
		private static class Pair implements BitRepresentable {
			private final int value;
			private final int index;
	 
			public Pair(int value, int index){
				if (index<0){throw new IllegalArgumentException("point index cannot be negative");}
				this.value = value;
				this.index = index;
			}
	 
			public int getValue(){return value;}
			public int getIndex(){return index;}
	 
			@Override
			public String toString(){
				return "(" + this.getValue() + "," + this.getIndex() + ")";
			}
	 
			@Override
			public boolean getBit(int n){
				if (n>=Integer.SIZE){return false;}
	 
				return ((value & (1 << n)) != 0);
			}
		}
	 
		private static int getFirstUniqueIndex(Pair[] pairs){
			if (pairs==null){return -1;}
			int res = -1;
			int min = -1;
			for (int i=0;i<pairs.length;i++){
				if ((i==0) || (pairs[i].getValue()!=pairs[i-1].getValue())){
					if ((res>=0) && ((min==-1) || (res<min))){min = res;}
					res = pairs[i].getIndex();
				}
				else {res = -1;}
			}
	 
			return ((res>=0) && ((min==-1) || (res<min))) ? res : min;
		}
	 
		private static Pair[] buildPairsArray(int[] arr){
			if (arr==null){return null;}
			Pair[] pairs = new Pair[arr.length];
			for (int i=0;i<arr.length;i++){
				pairs[i] = new Pair(arr[i],i);
			}
	 
			return pairs;
		}
	 
		public static int getFirstUnique(int[] arr){
			if (arr==null){
				throw new NullPointerException("Input array is null");
			}
	 
			Pair[] pairs = buildPairsArray(arr);
			RadixSort.radixSort(pairs, Integer.SIZE);
			int index = getFirstUniqueIndex(pairs);
	 
			return (index>=0) ? arr[index] : index;
		}
	 
		public static void main(String[] args) {
			final int[] arr = {2,3,4,5,6,-1,2,3,5,6};
			System.out.println(getFirstUnique(arr));
		}
		
	}
	 
	}

