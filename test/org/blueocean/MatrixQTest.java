package org.blueocean;

import junit.framework.TestCase;

public class MatrixQTest extends TestCase {
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
