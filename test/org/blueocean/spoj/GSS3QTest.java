package org.blueocean.spoj;

import org.blueocean.spoj.GSS3Q.SegmentTree;

import junit.framework.TestCase;

public class GSS3QTest  extends TestCase {
	
	public void testRange(){
		GSS3Q q = new GSS3Q();
		SegmentTree tree = q.new SegmentTree(new int[]{3, 4, 2, 1});
		
		System.out.println(tree.getMaxSum(0, 1));
		System.out.println(tree.getMaxSum(0, 3));
	}

}
