package org.blueocean;

import java.util.List;

import org.blueocean.ClientRGAQ.Node;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ClientRGAQTest  extends TestCase {
	
	public void testTo2DArray(){
		Node<String>[][] to2dArray = ClientRGAQ.to2DArray(new String[]{"A"});
		
		ClientRGAQ.Node<String>[][] result = to2dArray;
		
		Assert.assertEquals(result.length, 1);
		
		result = ClientRGAQ.to2DArray(new String[]{"A", "B"});
		
		Assert.assertEquals(result.length, 2);
		
		result = ClientRGAQ.to2DArray(new String[]{"A", "B", "C"});
		
		Assert.assertEquals(result.length, 2);
		
		result = ClientRGAQ.to2DArray(new String[]{"A", "B", "C", "D"});
		
		Assert.assertEquals(result.length, 2);
		
		result = ClientRGAQ.to2DArray(new String[]{"A", "B", "C", "D", "E"});
		
		Assert.assertEquals(result.length, 3);
		
		Assert.assertNotNull(result[0][1]);
		
		Assert.assertEquals(result[0][1].getData(), "B");
		
		Assert.assertNull(result[1][2]);
		
	}
	
	public void testTo1DArray(){
		Node<String>[][] to2dArray = ClientRGAQ.to2DArray(new String[]{"A"});
		
		List<String> t = ClientRGAQ.to1DArray(to2dArray);
		
		Assert.assertEquals(t.size(), 1);
		
		Assert.assertEquals(t.get(0), "A");
		
		to2dArray = ClientRGAQ.to2DArray(new String[]{"A", "B", "C", "D", "E"});
		
		t = ClientRGAQ.to1DArray(to2dArray);
		
		Assert.assertEquals(t.size(), 5);
		
		to2dArray = ClientRGAQ.to2DArray(new String[]{"A", "B", "C", null,"D", "E"});
		
		t = ClientRGAQ.to1DArray(to2dArray);
		
		Assert.assertNull(t.get(3));
		
		Assert.assertEquals(t.get(2), "C");
		
		Assert.assertEquals(t.size(), 6);
	}

}
