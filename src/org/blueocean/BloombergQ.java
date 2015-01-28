package org.blueocean;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import org.blueocean.SortQTest.Entry;

public class BloombergQ {
	
	public static void sort(String input){
		int[] freq = new int[SIZE];
		for(int i=0; i<input.length(); i++)
			freq[input.charAt(i)]++;
		
		Set<Entry> sorted = new TreeSet<Entry>();		
		for(int i=0; i<3; i++){
			if(freq[i]>0)
				sorted.add(new Entry((char)i, freq[i]));
		}
		
		for(Entry e : sorted){
			int counter = e.counter;
			while(counter-->0)
				System.out.print(e.c);
		}
	}
	
	public void sort2(String input){
        Map<Character, Integer> store = new HashMap<Character, Integer>();
        for(int i=0; i<input.length(); i++){
            char c = input.charAt(i);
            if(store.containsKey(c)){
                store.put(c, store.get(c)+1);
            }else
                store.put(c, 1);
        }
        Set<Entry> sorted = new TreeSet<Entry>();       
        for(Character k : store.keySet()){        
                sorted.add(new Entry(k, store.get(k)));
        }
        
        for(Entry e : sorted){
            int counter = e.counter;
            while(counter-->0)
                System.out.print(e.c);
        }
    }
	
	static class  Entry implements Comparable<Entry>{
		char c;
		int counter;
		
		Entry(char f, int h){this.c = f; this.counter = h;}
		
		@Override
		public int compareTo(Entry o){
			//if(o.counter - this.counter==0)
			//	return this.c - o.c;
			return o.counter - this.counter;
		}
		
		public String toString(){
			return c + "=" + counter;
		}
		
	}
	
	//time O(SIZE + input.length())
	//space O(SIZE)
	private final static int SIZE = 256;
	public char most(String input){
		int[] freq = new int[SIZE];
		int mostIndex = -1;
		int max = 0;
		for(int i=0; i<input.length(); i++){
			char c = input.charAt(i);
			freq[c]++;
			if(freq[c]>max){
				max = freq[c];
				mostIndex = c;
			}
		}
		return (char)mostIndex;
	}
	
	//space O(unique characters in input)
	//time: O(length of input)
	public char most2(String input){
		Map<Character, Integer> store = new HashMap<Character, Integer>();
		for(int i=0; i<input.length(); i++){
			char c = input.charAt(i);
			if(store.containsKey(c)){
				store.put(c, store.get(c)+1);
			}else
				store.put(c, 1);
		}
		int max = 0;
		char most = '0';
		for(Character c : store.keySet()){
			if(store.get(c)>max){
				max = store.get(c);
				most = c;
			}
		}
		return most;
	}
	
	//http://www.careercup.com/question?id=5190388790853632
	public void printLaudary(String[] input){
		Comparator<String> c = new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		};
		Map<String, Integer> store = new TreeMap<String, Integer>(c);
		for(String s : input){
			if(!store.containsKey(s)){
				store.put(s, 1);
			}else{
				store.put(s, store.get(s)+1);
			}
		}
		
		for(String k : store.keySet()){
			int count = store.get(k);
			if(k.contains("sock")){			 
				int pair = count/2;
				if(pair>=1)
					System.out.format("%d|%s%n", pair, k);
				int solo = count%2;
				if(solo==1)
					System.out.format("%d|%s%n", 0, k);
			}else
				System.out.format("%d|%s%n", count, k);
		}
	}
	
	
	public class BSTNode {
		int start;
		BSTNode left, right;
	}
	
	public List<BSTNode> rangeSearch(int start, int end){
		BSTNode tree = new BSTNode();
		List<BSTNode> result = new ArrayList<BSTNode>();
		dfs(start, end, tree, result);
		return result;
	}
	
	public void dfs(int start, int end, BSTNode curr, List<BSTNode> result){
		if(curr==null)
			return;
		if(curr.start>=start && curr.start<=end)
			result.add(curr);
		
		if (curr.start<=start){
			dfs(start, end, curr.right, result);
		}else{
			dfs(start, end, curr.left, result);
			if(curr.start<end)
				dfs(start, end, curr.right, result);
		}
	}
	
	public static void fibonacciTo(int n){
		int pre = 0;
		int curr = 1;
		for(int f = 0; pre<=n; f = pre + curr, pre = curr, curr = f){
			System.out.println(pre);
		}
	}
	
	public interface A {
		 int i = 1; //implies final and static and public
	}
	
	public abstract class C {
		 private int i; //implies final and static and public
		 private void test(){}
		 abstract void test2();
	}
	
	public class B implements A {
		public  void test() {
			//can not do this, for i is final and constant
			//i = 3;
		}
	}
	
	public class Airport {
		public Airport(String from) {
			this.name = from;
			this.destinations = new ArrayList<Airport>();
		}
		String name;
		List<Airport> destinations;
		Airport from;
	}
	
	public class Edge{
		String from;
		String to;
	}
	
	public Stack<Airport> sortAirports(List<Edge> edges){
		Map<String, Airport> graph = new HashMap<String, Airport>();
		buildGraph(edges, graph);
		
		Set<String> visited = new HashSet<String>();
		Stack<Airport> s = new Stack<Airport>();
		for(Airport a : graph.values()){
				sort(a, s, visited);
		}	
		return s;
	}
	//topological sort
	public void sort(Airport a, Stack<Airport> s, Set<String> visited){
		if(visited.contains(a.name))
			return;
		visited.add(a.name);
		for(Airport d : a.destinations){
				sort(d, s, visited);
		}
		s.add(a);
	}
	
	public boolean hasCycle(Map<String, Airport> graph){
		Set<String> visited = new HashSet<String>();
		Set<String> processed = new HashSet<String>();
		for(Airport a : graph.values()){
			if(!visited.contains(a)){
				try {
					hasCycleUtil(a, processed, visited);
				} catch (CycleFoundException e) {
					return true;
				}
			}
		}
		
		return false;
	}
		
	public class CycleFoundException extends Exception {
		private static final long serialVersionUID = 647828360427128755L;
		public CycleFoundException(String message) {super(message);}
	}
	
	private void hasCycleUtil(Airport a, Set<String> processed,
			Set<String> visited) throws CycleFoundException {
		if(processed.contains(a.name))
			throw new CycleFoundException("Cycle Found");
		if(!visited.contains(a.name)){
			processed.add(a.name);
			
			for(Airport c : a.destinations){
				hasCycleUtil(c, processed, visited);
			}				
			processed.remove(a.name);
			visited.add(a.name);
		}
	}
	
	public Stack<Airport> findRoute(List<Edge> edges, String from, String to){
		Map<String, Airport> graph = new HashMap<String, Airport>();
		buildGraph(edges, graph);
		return findPath(graph, from, to);
	}
	
	public void buildGraph(List<Edge> edges, Map<String, Airport> graph){		
		for(Edge e : edges){
			if(!graph.containsKey(e.from)){
				graph.put(e.from, new Airport(e.from));
			}
			
			if(!graph.containsKey(e.to)){
				graph.put(e.to, new Airport(e.to));
			}			
			graph.get(e.from).destinations.add(graph.get(e.to));
		}
	}
	
	public Stack<Airport> findPath(Map<String, Airport> graph, String from, String to){
		Airport fromNode = graph.get(from);
		Airport toNode = graph.get(to);	
		if(fromNode == toNode)
			return null;
		dfs(fromNode, toNode);
		Stack<Airport> route = new Stack<Airport>();
		while(toNode!=null){
			route.add(toNode);
			toNode = toNode.from;
		}
		return route;
	}
	
	public void dfs(Airport current, Airport toNode){
		if(current.name.equals(toNode.name))
			return;
		
		for(Airport c:  current.destinations){
			  c.from = current;
			  dfs(c, toNode);
		}
	}
	
	
	// This is the text editor interface. 
	// Anything you type or change here will be seen by the other person in real time.

	// Employees = ( { 1, 1 }, { 2, 1 }, { 3, 1 }, { }, { 5, 7 }, { 6, 3 }, { 7, 3 }, { 8, 4 },  ... ), CEO = 1
	// WhoReportsTo( 3 ) --> ( 6, 7 )
	// WhoReportsTo( 2 ) --> ( 4, 8 )

	// level traversal of tree given by node
	//connected, undirected graph with simple clcyle
	// tree, topologicaly sort
	//build
	//level travel
	public class Tree{
	    public Tree(int id1) {
			this.id = id1;
		}
		int id;
	    List<Tree> children;
	}

	public class Pair {
	    int id1; int id2;
	}

	//127 intern

	//Integer i1 = 1228;
	//Integer i2 = 1278;

	//immutable
	public final class MyId {
	    int id;
	    public MyId(int i) {this.id = i;}
	    
	    @Override
	    public int hashCode(){
	        return id;
	    }
	    
	    @Override
		public 
	    boolean equals(Object o){
	        if(o == this)
	            return true;
	        if(o instanceof MyId)
	            return ((MyId)o).id == this.id;
	            
	        return false;    
	        
	    }
	    
	}

	static Map<MyId, Tree> store = new HashMap<>();
	//assume the input is valid
	//no cycle dection in tree build
	public void createTree(List<Pair> pairs){
	        //build tree
	        for(Pair p : pairs){
	            MyId p1 = new MyId(p.id1);
	            if(!store.containsKey(p1)){
	                Tree node = new Tree(p.id1);
	                store.put(p1, node);
	            }
	            
	            MyId p2 = new MyId(p.id2);
	            if(!store.containsKey(p.id2)){
	                Tree node = new Tree(p.id2);
	                store.put(p2, node);
	            }
	            
	            //create the relationship between two id1, id2
	            store.get(p1).children.add(store.get(p2));
	        }
	    
	}

	public void levelTravel(int id){
	    //FIFO
	    Deque<Tree> q = new ArrayDeque<Tree>();
	    Tree node = store.get(new MyId(id));
	    q.addLast(node);
	    //level traversal of tree 
	    while(!q.isEmpty()){
	        Tree curr = q.removeFirst();
	        for(Tree c: curr.children){
	            System.out.println(c.id); 
	            q.addLast(c);
	        }
	    }
	}
	
	/*
	 * http://www.careercup.com/question?id=5648334022770688
	 */
	class Node{
		
	}
	
	static Node parent(Node p){
		return p;
	}
	
	public static Node findNCA(Node n1, Node n2){
		Arrays.asList(new Integer[1]);
		
		if(n1==null || n2==null)
			return null;
		if(n1==n2)
			return parent(n1);
		
		Set<Node> seen = new HashSet<Node>();
		while(n2!=null){
			Node p = parent(n2);
			seen.add(p);
			n2 = p;
		}
		
		while(n1!=null){
			Node p = parent(n1);
			if(seen.contains(p))
				return p;
			else{
				n1 = p;
			}
		}
		
		return null;
	}

	public static Node findNCA2(Node n1, Node n2){
	    if(n1==null || n2==null)
	        return null;
	    
	   int d1 = depth(n1);
	   int d2 = depth(n2);
	   
	   if(d1>d2){
	       Node t = n1;
	       n2 = t;
	       n1 = n2;
	   }
	   
	   int diff = Math.abs(d1-d2);
	   while(diff-->0)
	       n2 = parent(n2);
	  
	   while(n2!=null && n2!=n1){
	       n2 = parent(n2); n1 = parent(n1);
	   }    

	   return n2;
	}

	public static int depth(Node n1){
	    int d = -1;
	    while(n1!=null){
	        n1 = parent(n1);
	        d++;
	    }
	    return d;
	}

}
