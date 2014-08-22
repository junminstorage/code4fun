package org.blueocean;

import junit.framework.TestCase;

import org.blueocean.SuffixTree.STNode;

public class SuffixTreeTest extends TestCase {

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
