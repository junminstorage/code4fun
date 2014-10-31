package org.blueocean;

public class MatrixQ {

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
