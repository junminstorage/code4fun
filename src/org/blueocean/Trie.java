package org.blueocean;

import java.nio.CharBuffer;
import java.util.ArrayDeque;
import java.util.Queue;

public class Trie {
	TrieNode root;
	
	class TrieNode {
		Object data;
		TrieNode[] nodes = new TrieNode[27];
	}
	
	public void add(String s){
		if(s==null || s.isEmpty())
			return;
		
		if(root == null)
			root = new TrieNode();
		
		TrieNode current = root;
		char[] chars = s.toCharArray();
		for(int i=0; i<chars.length; i++){
			TrieNode[] nodes = current.nodes;
			int index = chars[i]-'a';
			
			if(current.nodes[chars[i]-'a']==null)
				current.nodes[chars[i]-'a'] = new TrieNode();
			current = current.nodes[chars[i]-'a'];					
		}
		current.data = s;
	}
	
	public boolean find(String s){
		if(root==null)
			return false;
		TrieNode current = root;
		char[] chars = s.toCharArray();
		for(int i=0; i<chars.length; i++){
			if(current.nodes[chars[i]-'a']==null)
				return false;
			current = current.nodes[chars[i]-'a'];					
		}		
		return true;
	}
	
	public TrieNode findLongestRepeatSubstring(){
		if(root==null)
			return null;
		
		TrieNode current = root;
		int max = 1;
		TrieNode target = root;
		Queue<TrieNode> q = new ArrayDeque<TrieNode>();
		q.add(current);
		int level = 0;
		while(!q.isEmpty()){
			Queue<TrieNode> t = new ArrayDeque<TrieNode>();
			level++;
			while(!q.isEmpty()){				
				TrieNode n = q.remove();
				TrieNode[] nodes = n.nodes;
				int counter = 0;
				for(TrieNode c : nodes){
					if(c!=null){
						t.add(c);
						counter++;
					}
				}			
				if((counter>0 && n.data!=null || counter>1) && level > max){
					max = level;
					target = n;
				}			
			}			
			q = t;			
		}	
		return target;
	}
	
	
	public String getSubstring(TrieNode n){
		if(root==null)
			return null;

		TrieNode current = n;
		StringBuffer sb = new StringBuffer();
		while(current.data==null){
			TrieNode[] nodes = current.nodes;		
			for(int i=0; i<nodes.length; i++){
				if(nodes[i]!=null){
					current = nodes[i];
					sb.append((char)(i+'a'));
					break;
				}
			}	
		}
		
		System.out.println(current.data.toString());
		System.out.println(sb.toString());
		
		return current.data.toString();
	}

}
