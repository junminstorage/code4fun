package org.blueocean;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

public class SearchAndSortTest extends TestCase {
	public void testFindKMins(){
		List<Integer> a1 = new ArrayList<Integer>();
		List<Integer> a2 = new ArrayList<Integer>();
		
		List<Integer> re = SearchAndSort.findKMins(a1, a2, 0);
		Assert.assertEquals(re.size(), 0);
		
		a1.add(1); a1.add(4); a1.add(10); a2.add(5); a2.add(8); 
		re = SearchAndSort.findKMins(a1, a2, 1);
		Assert.assertEquals(re.size(), 1);
		System.out.println(re.toString());
		
		re = SearchAndSort.findKMins(a1, a2, 3);
		Assert.assertEquals(re.size(), 3);
		System.out.println(re.toString());
		
		re = SearchAndSort.findKMins(a1, a2, 30);
		Assert.assertEquals(re.size(), 5);
		System.out.println(re.toString());
	}
	
}
