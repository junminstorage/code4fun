package org.blueocean;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import org.blueocean.GraphQ.Node;

public class GraphQ {
	
	//LinkedListNode[] nodes = new LinkedListNode[256];
	class LinkedListNode{
		int value;
		List<LinkedListNode> neighbors;
		
		LinkedListNode(int v){
			value = v;
			neighbors = new ArrayList<LinkedListNode>();
		}
	}
	
	public static boolean isEulerian(String[] words){
		if(words.length==1 && words[0].charAt(0)==words[0].charAt(words[0].length()-1))
			return true;
		int result = 0;
		int[] start = new int[256];
		int[] end = new int[256];
		
		for(String w : words){
			char s = w.charAt(0);
			char e = w.charAt(w.length()-1);
			
			if(s==e){
				start[s]++;
				end[e]++;
			}else{
				
				if(end[s]==0)
					start[s]++;
				else
					end[s]--;
				
				if(start[e]==0)
					end[e]++;
				else	
					start[e]--;	
			}
		}
		
		for(int i=0; i<256; i++){
			if(start[i]!=0 || end[i]!=0)
				return false;
		}
		
		return true;
		
	
	}

	public LinkedListNode[] createGraph(String[] words){
		LinkedListNode[] nodes = new LinkedListNode[256];	
		for(String s : words){
			if(nodes[s.charAt(0)]==null){
				nodes[s.charAt(0)] = new LinkedListNode(s.charAt(0));
			}
			
			if(nodes[s.charAt(s.length()-1)]==null){
				nodes[s.charAt(s.length()-1)] = new LinkedListNode(s.charAt(s.length()-1));
			}			
			nodes[s.charAt(0)].neighbors.add(nodes[s.charAt(s.length()-1)]);
			
			nodes[s.charAt(s.length()-1)].neighbors.add(nodes[s.charAt(0)]);
		}
		return nodes;
	}
	
	public static int isEulerian(LinkedListNode[] nodes){
		if(!isConnected(nodes))
			return 0;
		int odd = 0;
		for(int i=0;i<nodes.length; i++){
			if(nodes[i]!=null && nodes[i].neighbors.size()%2==1){
				odd++;
			}
		}
		if(odd>2)
			return 0;
		return odd ==0?2:1;
	}
	
	public static LinkedListNode foundANonZeroNode(LinkedListNode[] nodes ){
		for(int i=0;i<nodes.length; i++){
			if(nodes[i]!=null && nodes[i].neighbors.size()>0){
				return nodes[i];
			}
		}		
		return null;
	}
	
	public static boolean isConnected(LinkedListNode[] nodes ){
		LinkedListNode start = GraphQ.foundANonZeroNode(nodes);
		if(start==null)
			return false;
		
		boolean[] visited = new boolean[256];
		
		DFSVisit(start, visited);
		
		for(int i=0;i<nodes.length; i++){
			if(nodes[i]!=null && nodes[i].neighbors.size()>0 && !visited[nodes[i].value]){
				return false;
			}
		}
		
		return true;
	}

	public static void DFSVisit(LinkedListNode start, boolean[] visited) {
		Stack<LinkedListNode> s = new Stack<LinkedListNode>();
		s.add(start);
		while(!s.isEmpty()){
			LinkedListNode current = s.pop();
			visited[current.value] = true;
			for(LinkedListNode n : current.neighbors){
				if(!visited[n.value])
					s.push(n);
			}
		}
	}
	
	
	
	
	class Node{
		Object o;
		Node[] neights;
		boolean visited;
		
		protected Node clone(){
			Node n = new Node();
			n.o = this.o;
			return n;
		}
	}

	
	public Node clone(Node n, HashMap<Node, Node> copiedNodes){
		//Map<Node, Node> copiedNodes = new HashMap<Node, Node>();		
		if(copiedNodes.containsKey(n))
			return n;
		
		Node clone = n.clone();
		copiedNodes.put(n, clone);
		
		if(n.neights==null || n.neights.length==0)
			return clone;
		
		clone.neights = new Node[n.neights.length];
		for(int i=0; i<n.neights.length; i++){			
			clone.neights[i] = clone(n.neights[i], copiedNodes);
		}
		
		return clone;
		
	}
	
	
	/*
	 * BFS iterative clone a graph
	 */
	public Node clone(Node n){
		Map<Node, Node> copiedNodes = new HashMap<Node, Node>();
		Queue<Node> q = new ArrayDeque<Node>();//BFR
		
		Node clone = n.clone();
		copiedNodes.put(n, clone);
		
		q.add(n);
		
		while(!q.isEmpty()){
			Node m = q.remove();
			m.visited = true;
			clone = copiedNodes.get(m);
			
			if(m.neights!=null && m.neights.length!=0){
				clone.neights = new Node[m.neights.length];
				for(int i=0; i<m.neights.length; i++){
					
					if(copiedNodes.containsKey(m.neights[i]))
						clone.neights[i] = copiedNodes.get(m.neights[i]);
					else{
						Node nCopy = m.neights[i].clone(); 
						clone.neights[i] = nCopy;
						copiedNodes.put(m.neights[i], nCopy);
						q.add(m.neights[i]);
					}					
				}
			}
		}
		return clone;
	}
	
}
