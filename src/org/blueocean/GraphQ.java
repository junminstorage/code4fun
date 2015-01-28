package org.blueocean;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import org.blueocean.GraphQ.Node;

public class GraphQ {
	
	
	public static void sort(){
		Collections.sort(new LinkedList());
	}
	/*
	 * http://web.stcloudstate.edu/bajulstrom/cs301/examples/diameter.html
	 */
	public static int diameterOfTree(CNode[] vertices){
		int[] max = new int[1];
		CNode[] mNode = new CNode[1];
		Set<CNode> visited = new HashSet<CNode>();
		//start with any vertex in the tree
		dfs(vertices[0], 0, visited, max, mNode);
		
		CNode[] maxNode = new CNode[1];
		max[0] = 0;
		visited = new HashSet<CNode>();
		dfs(mNode[0], 0, visited, max, maxNode);
		
		return max[0];
		
	}
	public static void dfs(CNode start, int distance, Set<CNode> visited, int[] max, CNode[] maxNode){
		visited.add(start);
		max[0] = Math.max(max[0], distance);
		if(max[0]==distance)
			maxNode[0] = start;
		for(CNode n : start.outgoing){
			if(!visited.contains(n)){
				dfs(n, distance+1, visited, max, maxNode);
			}
		}
	}
	
	
	/*
	 * http://www.geeksforgeeks.org/given-sorted-dictionary-find-precedence-characters/
	 */
	public static class CNode {
		char c;
		Set<CNode> incoming = new HashSet<CNode>();
		Set<CNode> outgoing = new HashSet<CNode>();
		CNode(char i){this.c=i;}
	}
	
	public static List<Character> findLanguageOrderDFS(String[] words){
		Map<Character, CNode> nodes = new HashMap<Character, CNode>();
		Set<CNode> vertices = new HashSet<CNode>();
		createGraph(words, nodes, vertices);
		
		List<Character> result = new ArrayList<Character>();		
		//add those vertices without any incoming edges
		Set<CNode> visited = new HashSet<CNode>();
		Set<CNode> processed = new HashSet<CNode>();
		Stack<CNode> stack = new Stack<CNode>();
		for(CNode n : vertices){
			if(n.incoming.isEmpty()){
				if(visited.contains(n))
					continue;
				processed.add(n);
				for(CNode v: n.outgoing){
					if(!visited.contains(v))
						DFS(v, visited, processed, stack);
				}
				visited.add(n);
				stack.add(n);
			}
		}
		
		while(!stack.isEmpty()){
			result.add(stack.pop().c);
		}
		
		return result;
	}
	
	
	public static void DFS(CNode v, Set<CNode> visited,  Set<CNode> processed, Stack<CNode> s){
		if(visited.contains(v))
			return;
		if(processed.contains(v))
			throw new IllegalArgumentException("cycle found");
		processed.add(v);
		for(CNode n : v.outgoing){
			if(!visited.contains(n)){
				DFS(n, visited, processed, s);
			}
		}
		visited.add(v);
		s.push(v);
	}
	
	public static List<Character> findLanguageOrder(String[] words){
		List<Character> result = new ArrayList<Character>();
		
		Map<Character, CNode> nodes = new HashMap<Character, CNode>();
		Set<CNode> vertices = new HashSet<CNode>();
		createGraph(words, nodes, vertices);
		
		List<Character> temp = new ArrayList<Character>();
		//add those vertices without any incoming edges
		for(CNode n : vertices){
			if(n.incoming.isEmpty())
				temp.add(n.c);
		}
		
		//remove those vertices from the incoming list of its outgoing nodes
		while(!temp.isEmpty()){
			result.addAll(temp);
			List<Character> temp2 = new ArrayList<Character>();
			for(Character c : temp){
				CNode n = nodes.get(c);
				for(CNode o : n.outgoing){
					o.incoming.remove(n);
					if(o.incoming.isEmpty())
						temp2.add(o.c);
				}			
			}	
			temp = new ArrayList<Character>(temp2);
		}
		
		for(CNode n : vertices){
			if(!n.incoming.isEmpty())
				throw new IllegalArgumentException("can not find solution, possible wrong input");
		}
		
		return result;
	}

	private static void createGraph(String[] words,
			Map<Character, CNode> nodes, Set<CNode> vertices) {
		for(int i=0; i<words.length-1; i++){
			String current = words[i], next = words[i+1];
			int j = 0;
			for(j=0; j<current.length() && j<next.length() && current.charAt(j) == next.charAt(j); j++){}

			char c1=current.charAt(j), c2=next.charAt(j);
			CNode start = null, end = null;
			
			if(!nodes.containsKey(c1)){
				start = new CNode(c1);
				nodes.put(c1, start);
				vertices.add(start);
			}
			if(!nodes.containsKey(c2)){
				end = new CNode(c2);
				nodes.put(c2, end);
				vertices.add(end);
			}
			start = nodes.get(c1);
			end = nodes.get(c2);			
			start.outgoing.add(end);
			end.incoming.add(start);			
		}
	}
	
	
	/*
	 * https://www.hackerrank.com/challenges/even-tree
	 */
	static List<EdgeG> edges = new ArrayList<EdgeG>();
	
	public static void addEdgeG(int i, int j){
		NodeG nodeI = new NodeG(i);
		NodeG nodeJ = new NodeG(j);
		
		for(EdgeG e:edges){
			if(e.one.num == i)
				nodeI = e.one;
			if(e.two.num == i)
				nodeI = e.two;
			if(e.one.num == j)
				nodeJ = e.one;
			if(e.two.num == j)
				nodeJ = e.two;
		}
		
		edges.add(new EdgeG(nodeI, nodeJ));
		
		nodeI.neighbors.add(nodeJ);
		nodeJ.neighbors.add(nodeI);
		
	}
	
	public static int removeEdge(){
		int counter = 0;
		for(EdgeG e : edges){
			if(e.one.size(e.two)%2==0 && e.two.size(e.one)%2==0){
				//remove edge
				counter++;
				e.one.removeEdge(e.two);
				e.two.removeEdge(e.one);
			}
		}
		
		return counter;
	}
	
	public static class EdgeG{
		NodeG one;
		NodeG two;	
		public EdgeG(NodeG o, NodeG t){
			this.one = o;
			this.two = t;
		}
		public String toString(){
			return one.num + " - " + two.num;
		}
	}
	
	public static class NodeG{
		int num;
		boolean visited;
		List<NodeG> neighbors;
		
		public NodeG(int n){
			num = n;
			neighbors = new ArrayList<NodeG>();
		}
		
		public void removeEdge(NodeG two){
			neighbors.remove(two);
		}
		public int size(NodeG from){
			NodeG n = this;
			int counter = 0;
			Set<NodeG> seen = new HashSet<NodeG>();
			Stack<NodeG> s = new Stack<NodeG>();
			s.add(n);
			counter++;
			while(!s.isEmpty()){
				NodeG current = s.pop();
				seen.add(current);
				for(NodeG c : current.neighbors){
					if(c!=from && !seen.contains(c)){
						s.add(c);
						counter++;
					}
				}
			}
			return counter;
		}
	}
	
	
/*
 * **************	
 */
	
	
	public static void read(){
		try(Scanner conin = new Scanner(System.in)){

			while (conin.hasNext()) {
				int t = Integer.valueOf(conin.nextLine());
				String s = conin.nextLine();
				
				if(!s.isEmpty()){			
				char[] carray = s.toCharArray();
				Arrays.sort(carray);
				Set<String> set = new TreeSet<String>();
				set.add(String.valueOf(carray[0]));
				for(int i=1; i<carray.length; i++){
					Set<String> temp = new TreeSet<String>(set);
					for(String st : temp){
						StringBuilder sb = new StringBuilder(st);
						set.add(sb.append(carray[i]).toString());
						//set.add(st.concat(String.valueOf(carray[i])));
					} 
					set.add(String.valueOf(carray[i]));
				}

				for(String st : set)
					System.out.println(st);
				}
			}
		}
	}
	
	public static void combination(String s){
		
	}
	
	/*
	 * 3- An Adjacency matrix is given which is represented by 2d array.and each field is having cost associated.You are also given source and destination points.Find the maximum cost to reach from source to destination.
	 */
	public int maxCost(int[][] matrix, int start, int end){
	    boolean[] visited = new boolean[matrix.length];
	    int[] cost = new int[matrix.length];
	    Deque<Integer> q = new ArrayDeque<Integer>();
	    q.add(start);
	    visited[start] = true;
	    cost[start] = 0;
	    
	    while(!q.isEmpty()){
	        int current = q.remove();
	        
	        for(int i=0; i!=current && matrix[current][i]!=Integer.MAX_VALUE && i<matrix[current].length; i++){
	            
	            cost[i] = Math.max(cost[i], cost[current]+matrix[current][i]);
	            
	            if(!visited[i] && i!=end){
	                q.add(i);
	                visited[i] = true;
	            }
	        
	        }
	           
	    }
	    
	    return cost[end];
	}
	
	void dijkstra(Node start) {
		Deque<Node> s = new ArrayDeque();
		
		s.push(start);
		while (s.isEmpty() == false) {
			Node top = s.pop();
			s.pop();
			// mark top as visited;

			//check for termination condition (have we reached the target node?)
			//add all of top's unvisited neighbors to the stack.

		}
	}
	/*
	 * union-find algo
	 * http://algs4.cs.princeton.edu/15uf/
	 */
	static int size;
	static int[] nodes;
	static byte[] ranks;
	
	public static void init(int s){
		size = s;
		nodes = new int[s];
		ranks = new byte[s];		
		for(int i=0;i<nodes.length; i++)
			nodes[i] = i;	//every node is its own set	
		for(byte b : ranks)
			b = 0; //0 means empty set
	}
	
	public static int find(int p){
		if(p<0 || p>=size)
			return -1;
		if(nodes[p] == p)//reach the root
			return p;
		else{
			nodes[p] =  find(nodes[p]);
			return nodes[p];
		}
	}
	
	public static void union(int p, int q){
		int fp = find(p);
		int fq = find(q);
		
		if(fp==-1 || fq==-1)
			return;
		
		if(fp == fq)
			return;
		
		if(ranks[fp] > ranks[fq])
			nodes[fq] = fp;
		else if(ranks[fp] < ranks[fq])
			nodes[fp] = fq;
		else{
			nodes[fq] = fp;
			ranks[fp]++;
		}		
	}
	
	
	/*
	 * http://www.careercup.com/question?id=5742219382226944
	 * this solution is good for undirected graph
	 */
	
	private static void printAllPathUDG(LinkedListNode head){
		if(head==null)
			return;
		LinkedList<LinkedListNode> queue = new LinkedList<LinkedListNode>();
		LinkedList<String> path = new LinkedList<String>();
		
		head.visited = true;
		queue.addLast(head);
		path.addLast(String.valueOf(head.value));
		
		while(!queue.isEmpty()){
			LinkedListNode node = queue.removeFirst();
			String subPath = path.removeFirst();
			boolean isLeaf = true;
			for(LinkedListNode neighbor : node.neighbors){
				if(!neighbor.visited){
					isLeaf = false;
					neighbor.visited = true;
					queue.addLast(neighbor);
					path.addLast(String.valueOf(subPath + neighbor.value));
				}
			}
			
			if(isLeaf)
				System.out.println(subPath);
		
		}
		
	}
	
	
	/*
	 * http://www.careercup.com/question?id=5742219382226944
	 * this solution is good for directed graph
	 */
	public static void printAllPath(LinkedListNode graph){
		List<Integer> path = Collections.EMPTY_LIST;
		printAllPathUtil(graph, path);
		
	}
	
	private static void printAllPathUtil(LinkedListNode node, List<Integer> path){
		if(node.visited){
			System.out.println(path);
			return;
		}
			
		path.add(node.value);
		if(node.neighbors==null || node.neighbors.isEmpty() ){
			System.out.println(path);
			return;
		}
		
		node.visited = true;
		for(LinkedListNode n : node.neighbors){
			printAllPathUtil(n, path);
		}
		
	}
	
	//LinkedListNode[] nodes = new LinkedListNode[256];
	static class LinkedListNode{
		int pathSum;
		
		int value;
		List<LinkedListNode> neighbors;
		boolean visited;
		
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
	/*
	 * topology sorting a DAG
	 */
	static LinkedListNode head;
	static List<LinkedListNode> graph;
	
	public static void createTestGraph(){
		graph = new ArrayList<LinkedListNode>();
		addEdge(6, 4);
		addEdge(5, 4);
		addEdge(5, 3);
		addEdge(5, 2);
		addEdge(4, 2);
		addEdge(3, 2);
		addEdge(2, 1);
	}
	
	
	public static void minPath(LinkedListNode start, LinkedListNode end){
		Stack<LinkedListNode> starts = new Stack<LinkedListNode>();
		for(LinkedListNode n : graph){
			n.visited = false;
			n.pathSum = Integer.MAX_VALUE;
		}
		
		starts.add(start);
		start.pathSum = 0;
		
		while(!starts.isEmpty()){
			LinkedListNode current = starts.pop();
			current.visited = true;
			for(LinkedListNode c : current.neighbors){
				int p = current.value + current.pathSum;
				if(c.pathSum>p)
					c.pathSum = p;
				if(!c.visited)
					starts.add(c);
			}
		}
		
		System.out.println(end.pathSum);
	}
	
	public static void topologicalSort(List<LinkedListNode> graph){
		Stack<LinkedListNode> starts = new Stack<LinkedListNode>();
		LinkedListNode start = findNode(5);
		LinkedListNode start2 = findNode(6);
		starts.add(start);
		starts.add(start2);
		
		Stack<LinkedListNode> s = new Stack<LinkedListNode>();
		for(LinkedListNode n : starts){
			n.visited = true;
			for(LinkedListNode c : n.neighbors){
				if(c!=null && !c.visited)
					topologicalSortUtil(c, s);
			}
			s.add(n);
		}
		
		while(!s.isEmpty()){
			System.out.println(s.pop().value);
		}
	}
	
	public static void topologicalSortUtil(LinkedListNode start, Stack<LinkedListNode> s){
		start.visited = true;
		for(LinkedListNode n : start.neighbors){
			if(n!=null && !n.visited)
				topologicalSortUtil(n, s);
		}
		s.add(start);
	}
	
	public static void addEdge(int n, int m){
		LinkedListNode start = insertNode(n);
		LinkedListNode end = insertNode(m);		
		start.neighbors.add(end);			
	}
	
	public static LinkedListNode insertNode(int n){
		LinkedListNode start = findNode(n);
		if(start==null){
			start = new LinkedListNode(n);
			graph.add(start);
		}
		return start;
	}
	
	public static LinkedListNode findNode(int n){
		for(LinkedListNode node : graph){
			if(node!=null && node.value == n)
				return node;
		}
		return null;
	}
	
	//////////////////////////////////////////////////////////////
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
	
	/*
	 * http://www.geeksforgeeks.org/topological-sorting/
	 */
	public static void sort(LinkedListNode[] nodes){
		//find the node with no incoming edge
		for(LinkedListNode n:nodes){
			for(LinkedListNode neighbor : n.neighbors){
				neighbor.visited = true;
			}
		}
		
		Stack<LinkedListNode> starts = new Stack<LinkedListNode>();
		for(LinkedListNode n: nodes){
			if(!n.visited)
				starts.add(n);
		}	
		
		for(LinkedListNode n:nodes){
				n.visited = false;
		}
		
		Stack<LinkedListNode> sorted = new Stack<LinkedListNode>();
		LinkedListNode start = starts.pop();
		start.visited = true;
		sortHelper(start, sorted);
	}
	
	public static void sortHelper(LinkedListNode start, Stack<LinkedListNode> sorted){
		for(LinkedListNode n : start.neighbors){
			if(!n.visited){
				n.visited = true;
				sortHelper(n, sorted);
			}
		}		
		sorted.push(start);		
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
