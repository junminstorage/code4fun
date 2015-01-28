package org.blueocean;

import java.util.List;

import junit.framework.TestCase;

public class MatrixQTest extends TestCase {
	
	public void testtest(){
		int n = 16;
		int[] table = new int[n+1];
        table[0] = 1;
        for(int i=1; i<=n; i++){
            if(i>=4)
                table[i] = table[i-4] + table[i-1];
            else
                table[i] = table[i-1];
        }
        int result = table[n];
        boolean[] notPrime = new boolean[result+1];
        for(int i=2; i*2<=result; i=i+1){  
        	if(!notPrime[i]){
            for(int k=2; k*i<=result; k=k+1){
                notPrime[k*i] = true;
            }                
        	}
        }
        int counter=0;
        for(int i=2; i<=result; i=i+1){
            if(!notPrime[i])
                counter++;
        }
        System.out.println(counter);
        
        System.out.println(result);
	}
	
	public void testMove(){
		MatrixQ.move();
	}
	
	public void testGridSearch(){
		String[] m = new String[]{"1234567890", "0987654321", "1111111111", "1111111111", "2222222222"};
		String[] p = new String[]{"876543","111111","111112"};
		System.out.println(MatrixQ.gridSearch(m, p));
	}
	
	public void testfindFirst1(){
		System.out.println(MatrixQ.findFirst1(new int[]{0,0,1,1}, 0, 3));
		System.out.println(MatrixQ.findFirst1(new int[]{1,1,1,1}, 0, 3));
		System.out.println(MatrixQ.findFirst1(new int[]{0,0,0,0}, 0, 3));
	}
	
	public void testfindMax1Row(){
		int[][] matrix = {
				{0, 0, 0, 1, 1, 1},
				{0, 0, 0, 0, 1, 1},
				{1, 1, 1, 1, 1, 1},
				{0, 0, 1, 1, 1, 1}
		};
		
		System.out.println(MatrixQ.findMax1Row(matrix));
	}
	
	public void testfindNumberOfIslands(){
		int[][] matrix = {
				{1, 1, 1, 0, 1, 1},
				{0, 1, 1, 0, 1, 1},
				{0, 1, 0, 1, 1, 1}
		};
		System.out.println(MatrixQ.findNumberOfIslands(matrix));
	}
	
	public void testfindMaxComponent(){
		int[][] matrix = {
				{1, 1, 1, 0, 1, 1},
				{0, 1, 1, 0, 1, 1},
				{0, 1, 0, 1, 1, 1}
		};
		
		List<MatrixQ.Node> rs = MatrixQ.findMaxComponent(matrix);
		for(MatrixQ.Node n : rs){
			System.out.println(n.toString());
		}
	}
	
	public void testisPresent(){
		char[][] matrix = {
				{'a', 'b', 'c', 'd', 'e', 'f'},
				{'z', 'n', 'a', 'b', 'c', 'f'},
				{'f', 'g', 'f', 'a', 'b', 'c'},
		};
		
		System.out.println(MatrixQ.isPresent(matrix, "fnz".toCharArray()));
		System.out.println(MatrixQ.isPresent(matrix, "gng".toCharArray()));
	}

}
