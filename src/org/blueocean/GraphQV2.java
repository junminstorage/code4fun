package org.blueocean;

import java.util.Stack;

public class GraphQV2 {
	
	class Node {
		char c;
		int n;	// distance away from starting node	
		Node(char c){
			this.c = c;
			this.n = 0;
		}
	}
	
	class Edge {
		boolean v;//visited
		int n;	//weight, 0 mean no edge, 1 mean connection
		Edge(int n){
			this.n = n;
		}
	}
	
	Edge[][] graph = new Edge[256][256];
	Node[] nodes = new Node[256];
	
	public void init(String[] strings){
		for(String s : strings){
			char first = s.charAt(0);
			if(nodes[first]==null)
				nodes[first] = new Node(first);
			char last = s.charAt(s.length()-1);
			if(nodes[last]==null)
				nodes[last] = new Node(last);			
			graph[first][last] = new Edge(1);			
		}
	}
	
	public int longestDistance(){
		int maxDistance = 0;
		for(Node node : nodes){
			if(node!=null){
				node.n=0;
				int dis = longestDistanceFrom(node);
				if(maxDistance < dis)
					maxDistance = dis;	
				clean();
			}
		}
		
		return maxDistance;
	}
	
	public int longestDistanceFrom(Node n){
		int max = 0;
		Stack<Node> s = new Stack<Node>();
		s.add(n);	
		while(!s.isEmpty()){
			Node t = s.pop();
			for(int i=0; i<256; i++){
				Edge e = graph[t.c][i];
				if(e!=null){
					if(!e.v){
						if(nodes[i].n < t.n+1)
							nodes[i].n = t.n+1;
						s.push(nodes[i]);
						e.v = true;
					}
				}						
			}			
		}
		
		for(Node node : nodes){
			if(node!=null && node.n>max)
				max = node.n;
		}
		
		
		return max;
	}
	
	public void clean(){
		for(Node node : nodes){
			if(node!=null)
				node.n = 0;
		}
		
		for(int i=0; i<graph.length; i++){
			for(int j=0; j<graph[0].length; j++)
				if(graph[i][j]!=null)
					graph[i][j].v = false;
		}
	}
	
}
