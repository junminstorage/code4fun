package org.blueocean;

import java.util.List;

import junit.framework.TestCase;

public class MatrixQTest extends TestCase {
	
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
