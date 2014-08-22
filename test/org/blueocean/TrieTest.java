package org.blueocean;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TrieTest  extends TestCase {
	public void testTrie(){
		Trie t = new Trie();
		String[] data = new String[]{"the", "tree", "data", "structure", "test", "the"};
		
		for(String s : data)
			t.add(s);
		
		Assert.assertTrue(t.find("the"));
		Assert.assertTrue(t.find("test"));
		Assert.assertFalse(t.find("thee"));
	}

}
