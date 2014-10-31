package org.blueocean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrieQ {
	Node head;
	
	class Node {
		List<Integer> indices;
		Node[] children;
	}
	
	public Node initTrie(String s){
		head = new Node();		
		for(int i=0; i<s.length(); i++){
			this.addSuffix(s.substring(i), i);
		}
		return head;
	}
	
	public void addSuffix(String s, int i){
			addChar(s, head, 0, i);
	}
	
	//recursive function
	void addChar(String s, Node head, int index, int subIndex){
		if(index>=s.length())
			return;
		
		char c = s.charAt(index);
		
		if(head.children==null)
			head.children = new Node[26];
		
		if(head.children[c-'a']==null)
			head.children[c-'a'] = new Node();
		
		if(head.indices==null)
			head.indices = new ArrayList<Integer>();
		
		head.indices.add(index+subIndex);
		
		addChar(s, head.children[c-'a'], index+1, subIndex);
				
	}
	
	public List<Integer> findMatches(String s){
		return findMatchUtil(s, head, 0);
	}
	
	public List<Integer> findMatchUtil(String s, Node head, int index){
		if(index>=s.length())
			return head.indices;
		else{
			
			if(head!=null && head.children!=null && head.children[s.charAt(index)-'a']!=null){
				if(index==s.length()-1)
					return head.indices;
				else
					return findMatchUtil(s, head.children[s.charAt(index)-'a'], index+1);
			}else{
				return Collections.EMPTY_LIST;
			}						
		}		
	}
	
	
}
