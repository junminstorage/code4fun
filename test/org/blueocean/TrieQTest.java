package org.blueocean;

import java.util.List;

import junit.framework.TestCase;

public class TrieQTest  extends TestCase {
	
	public void test(){
		TrieQ q = new TrieQ();
		q.initTrie("fdsfdsf");
		List<Integer> result = q.findMatches("fds");
		System.out.println(result);
	}

}
