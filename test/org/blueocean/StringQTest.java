package org.blueocean;

import junit.framework.Assert;
import junit.framework.TestCase;

public class StringQTest extends TestCase {
	public void testToChar(){
		String s = "afs";
		char[] chars= s.toCharArray();
		
		System.out.println(s.length() + " - " + chars.length);
		System.out.println(s.substring(0, s.length()));
		System.out.println(chars[2]);
		
	}
	
	public void testIsSub(){
		String s = "sdfdfsfdsf";
		String m = "fd";
		Assert.assertTrue(StringQ.isSubStr(s, m));
		
	}

}
