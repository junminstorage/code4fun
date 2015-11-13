package org.blueocean.leetcode;

import org.junit.Test;

public class PermutationQTest {
	@Test
	public void permuRank(){
		System.out.println(PermutationQ.permutationByRank(3, 1));
		System.out.println(PermutationQ.permutationByRank(3, 2));
		System.out.println(PermutationQ.permutationByRank(3, 6));
		
		System.out.println(PermutationQ.permutationByRank(4, 2));
		System.out.println(PermutationQ.permutationByRank(4, 3));
	}

}
