package org.blueocean;

import junit.framework.TestCase;

import org.blueocean.SuffixTree.STNode;

public class SuffixTreeTest extends TestCase {

	public void testcontainsWord(){
		SuffixTree st = new SuffixTree();
		st.addString("hello", 1);
		st.addString("hell", 1);
		st.addString("world", 1);
		st.addString("super", 1);
		
		System.out.println(st.containsWord("helloworld"));
		System.out.println(st.containsWord("hellohello"));
	}
	public void testAddString(){
		STNode root = new SuffixTree().addString("adf", 3);
		System.out.println(root.data);
	}
	
	
	public void testAddAllSubString(){
		SuffixTree st = new SuffixTree();
		st.addAllSubString("adf");
		System.out.println(st.root);
	}
	
	public void testfindLongestRepeatSub(){
		SuffixTree st = new SuffixTree();
		System.out.println(st.findLongestRepeatSub("adfbgadfg"));
		//System.out.println(st.root);
	}
	
}
