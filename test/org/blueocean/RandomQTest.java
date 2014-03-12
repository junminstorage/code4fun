package org.blueocean;

import junit.framework.TestCase;

public class RandomQTest extends TestCase {
	public void testkthMin(){
		int[] x = {2, 4, 5, 7, 8};
		int[] y = {3, 6, 9, 10, 11};
		
		System.out.println(RandomQ.kthMin(x, y, 1)+"-");
		System.out.println(RandomQ.kthMin(x, y, 2)+"-");
		System.out.println(RandomQ.kthMin(x, y, 3)+"-");
		System.out.println(RandomQ.kthMin(x, y, 4)+"-");
		System.out.println(RandomQ.kthMin(x, y, 5)+"-");
		System.out.println(RandomQ.kthMin(x, y, 6)+"-");
		System.out.println(RandomQ.kthMin(x, y, 7)+"-");
		System.out.println(RandomQ.kthMin(x, y, 8)+"-");
	}
	
	public void testkthMin2(){
		int[] x = {2, 4, 5, 7, 8};
		int[] y = {3, 6, 9, 10, 11};
		
		System.out.println(RandomQ.kthMin2(x, y, 1)+"-");
		System.out.println(RandomQ.kthMin2(x, y, 2)+"-");
		System.out.println(RandomQ.kthMin2(x, y, 3)+"-");
		System.out.println(RandomQ.kthMin2(x, y, 4)+"-");
		System.out.println(RandomQ.kthMin2(x, y, 5)+"-");
		System.out.println(RandomQ.kthMin2(x, y, 6)+"-");
		System.out.println(RandomQ.kthMin2(x, y, 7)+"-");
		System.out.println(RandomQ.kthMin2(x, y, 8)+"-");
		System.out.println(RandomQ.kthMin2(x, y, 9)+"-");
	}
}
