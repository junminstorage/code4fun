package org.blueocean;

import java.util.ArrayList;
import java.util.List;

public class ClientRGAQ {
	/*
	 * generic class represent each element of the square
	 */
	static class Node<T>{
		private T data;
		
		public Node(T obj){
			this.data = obj;
		}
		
		public T getData(){
			return data;
		}
	}
	
	/*
	 * 2D array is a square
	 */
	public static <T> Node<T>[][] to2DArray(T[] input){
		int rowNum = (int) Math.ceil(Math.sqrt(input.length));
		@SuppressWarnings("unchecked")
		Node<T>[][] arrays = new Node[rowNum][rowNum];
		for(int i=0; i<input.length; i++){
			Node<T> node = new Node<T>(input[i]);
			arrays[i/rowNum][i%rowNum] = node;
		}	
		return arrays;
	}
	
	/**
	 * convert 2D array to list
	 * 
	 * @param input
	 * @return
	 */
	public static <T> List<T> to1DArray(Node<T>[][] input){
		List<T> resultList = new ArrayList<T>();
		for(int i=0; i<input.length; i++){
			for(int j=0; j<input[i].length; j++)
				if(input[i][j]!=null)
					resultList.add(input[i][j].data);
		}					
		return resultList;
	}
	

}
