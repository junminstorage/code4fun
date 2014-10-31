package org.blueocean;

import junit.framework.Assert;
import junit.framework.TestCase;

public class StackQTest extends TestCase {

	public void testStackWithQ(){
		StackQ.StackWithQ s = new StackQ.StackWithQ();
		s.push(new Integer(1));
		s.push(new Integer(2));
		s.push(new Integer(3));
		
		Assert.assertTrue( ((Integer)s.pop()).intValue() == 3);
		Assert.assertTrue( ((Integer)s.pop()).intValue() == 2);
	}
}
