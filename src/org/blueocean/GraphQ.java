package org.blueocean;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.blueocean.GraphQ.Node;

public class GraphQ {

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
