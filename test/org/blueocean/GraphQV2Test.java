package org.blueocean;

import junit.framework.TestCase;

public class GraphQV2Test extends TestCase {
	public void testLongestDistance(){
		GraphQV2 g = new GraphQV2();
		g.init(new String[]{"ab" , "bc","cd","bf", "fg", "db"});
		System.out.println(g.longestDistance());
	}

}
