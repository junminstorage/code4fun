package org.blueocean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatrixQ {
	
	/*
	 * Q1. Given matrix of 1s and 0s where 0 is water and 1 is land. Find number of islands.
	 * http://www.geeksforgeeks.org/amazon-interview-experience-set-142-campus-sde-1/
	 */
	public static int findNumberOfIslands(int[][] matrix){
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		int num = 0;
		for(int row=0; row<matrix.length; row++){
			for(int col=0; col<matrix[0].length; col++){
				if(!visited[row][col] && matrix[row][col]==1){	
					int[] counter = new int[1];
					counter[0] = 0;
					visitRec(row, col, visited, matrix, counter);
					num++;
					System.out.println(counter[0]);
				}
			}
		}
		
		return num;
	}
	
	public static void visitRec(int row, int col, boolean[][] visited, int[][] matrix, int[] counter){
		if(row<0 || row>=matrix.length || col<0 || col>=matrix[0].length || visited[row][col] || matrix[row][col] ==0 )
			return;
		else{
			visited[row][col]=true;
			counter[0]++;
			visitRec(row-1, col, visited, matrix, counter);
			visitRec(row+1, col, visited, matrix, counter);
			visitRec(row, col-1, visited, matrix, counter);
			visitRec(row, col+1, visited, matrix, counter);
		}
	}
	
	/*
	 * http://www.geeksforgeeks.org/find-the-row-with-maximum-number-1s/
	 * row is sorted array with 0s or 1s
	 */
	public static int findFirst1(int[] row, int start, int end){
		if(start==end)
			return row[start]==1?start:-1;
		else if(start-end==1)
			return row[start]==1?start:end;
		else{
			int mid = (start+end)/2;
			if(row[mid]==0){
				return findFirst1(row, mid+1, end);
			}else{
				if(row[mid-1]==0)
					return mid;
				else
					return findFirst1(row, start, mid-1);
			}
		}
	}
	
	/*
	 * Q3. Given a matrix of 0s and 1s find the row that contains maximum number of 1s.
	 */
	public static int findMax1Row(int[][] matrix){
		int row = 0;
		int col = matrix[0].length-1;
		int max = row;
		while(row<matrix.length && col >=0){
			if(matrix[row][col]==1){
				col--;
				max = row;
			}else
				row++;
		}
		
		return max;
	}
	
	/*
	 * http://www.careercup.com/question?id=5998719358992384
	 * Given a matrix consisting of 0's and 1's,
	 *  find the largest connected component consisting of 1's.
	 */
	public static class Node{
		int row;
		int col;
		public Node(int r, int c){
			this.row = r;
			this.col = c;
		}
		public String toString(){
			return row + " - " + col;
		}
	}
	public static List<Node> findMaxComponent(int[][] matrix){
		List<Node> result = new ArrayList<Node>();
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		for(int r=0; r<matrix.length; r++){
			for(int c=0; c<matrix[0].length; c++){		
				List<Node> current = new ArrayList<Node>();
				findMaxComponentRecursive(matrix, r-1, c, visited, current);
				if(current.size() > result.size())
					result = current;
			}
		}
		return result;	
	}
	
	public static void findMaxComponentRecursive(int[][] matrix, int r, int c, boolean[][] visited, List<Node> current){
		if(r>=0&&r<matrix.length&&c>=0&&c<matrix[0].length&& !visited[r][c] && matrix[r][c]==1){
			visited[r][c] = true;
			current.add(new Node(r, c));
			//continue
			findMaxComponentRecursive(matrix, r-1, c, visited, current);
			findMaxComponentRecursive(matrix, r+1, c, visited, current);
			findMaxComponentRecursive(matrix, r, c-1, visited, current);
			findMaxComponentRecursive(matrix, r, c+1, visited, current);
		}	
	}

	/*
	 * http://www.careercup.com/question?id=5890898499993600
	 * Given a matrix of letters and a word, check if the word is present in the matrix. 
	 * E,g., suppose matrix is: 
		a b c d e f 
		z n a b c f 
		f g f a b c 
		and given word is fnz, it is present. However, gng is not since you would be repeating g twice. 
		You can move in all the 8 directions around an element.
	 */
	public static boolean isPresent(char[][] matrix, char[] word){
	
		for(int row=0; row<matrix.length; row++){
			for(int col=0; col<matrix[0].length; col++){
				boolean[][] visited = new boolean[matrix.length][matrix[0].length];
				if(isPresentRecursive(matrix, word, 0, row, col, visited)){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean  isPresentRecursive(char[][] matrix, char[] word, int pos, int row, int col, boolean[][] visited){
		//pass the end of word
		if(pos==word.length)
			return true;
		
		if(row<0 || row>=matrix.length || col<0 || col>=matrix[0].length)
			return false;
		
		if(visited[row][col])
			return false;
		
		if(matrix[row][col]==word[pos]){	
			visited[row][col] = true;
			return 
			 isPresentRecursive(matrix, word, pos+1, row-1, col, visited) || 			
			 isPresentRecursive(matrix, word, pos+1, row+1, col, visited) || 			
			 isPresentRecursive(matrix, word, pos+1, row, col-1, visited) ||			
			 isPresentRecursive(matrix, word, pos+1, row, col+1, visited) ||			
			 isPresentRecursive(matrix, word, pos+1, row-1, col-1, visited) ||			
			 isPresentRecursive(matrix, word, pos+1, row-1, col+1, visited) ||
			 isPresentRecursive(matrix, word, pos+1, row+1, col-1, visited) ||
			 isPresentRecursive(matrix, word, pos+1, row+1, col+1, visited) ;
		}
		return false;

	}

}
