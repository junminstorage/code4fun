package org.blueocean;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
 * https://www.hackerrank.com/challenges/tree-pruning
 */
public class TreePruneQ {
	
	public static class Node {
		int index;
		int weight;
		int total;
		
		List<Node> neighbors = new ArrayList<Node>();
		
		Node parent;
	
		public void removeChild(Node n){
			neighbors.remove(n);
		}
		
		public Node(int i, int w){
			this.index = i;
			this.weight = w;
		}
	}

	public static int updateSum(Node root, Set<Node> visited){
		if(root==null)
			return 0;
		
		int sum = 0;
		visited.add(root);
		for(Node n : root.neighbors){
			if(!visited.contains(n)){
				n.parent = root;
				sum +=updateSum(n, visited);
			}
		}
		
		root.total = root.weight + sum;		
		return root.total;
	}
	
	static int min = Integer.MAX_VALUE;
	static Node minNode = null;
	
	public static Node findMin(Node root){
		min = Integer.MAX_VALUE;
		minNode = null;
		Deque<Node> queue = new ArrayDeque<Node>();
		Set<Node> visited = new HashSet<Node>();
		visited.add(root);
		
		for(Node n : root.neighbors){
			queue.add(n);
			visited.add(n);
		}
		
		while(!queue.isEmpty()){
			Node c = queue.remove();
			if(c.total < min){
				min = c.total;
				minNode = c;
			}			
			for(Node n : c.neighbors){
				if(!visited.contains(n)){
					queue.add(n);
					visited.add(n);
				}
			}		
		}		
		return minNode;
	}
	
	public static void trune(Node minNode){
		Node p = minNode.parent;
		p.removeChild(minNode);
		minNode.removeChild(p);
		while(p!=null){
			p.total = p.total - minNode.total;
			p = p.parent;
		}
	}
	
	public static int truneTree(Node root, int k){
		for(int i=0; i<k; i++){
			Node min = findMin(root);
			if(min==null || min.total>=0)
				break;
			else{
				trune(min);
			}
		}
		
		return root.total;
	}
	
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		try(Scanner conin = new Scanner(System.in)){
            int num = 5; //conin.nextInt();
            int k = 2; //conin.nextInt();
            Map<Integer, Node> hash = new HashMap<Integer, Node>();
                           
            for(int i=1; i<=num; i++){
            	hash.put(i, new Node(i, conin.nextInt()));
            }
            
            conin.nextLine();
            
            while(conin.hasNextLine()){
            	Node n1 = hash.get(conin.nextInt());
            	Node n2 = hash.get(conin.nextInt());
            	n1.neighbors.add(n2);
            	n2.neighbors.add(n1);           	
            	conin.nextLine();
            }
            
            Node root = hash.get(1);
            Set<Node> visited = new HashSet<Node>();
            updateSum(root, visited);
            truneTree(root, k);
                      
		}
	}
}
