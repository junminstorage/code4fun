package org.blueocean;

import java.util.ArrayList;
import java.util.List;

public class Kruskal {

	public static class Edge implements Comparable<Edge>{
		int v1;
		int v2;
		double weight;
		public Edge(int v1, int v2, double w){
			this.v1 = v1;
			this.v2 = v2;
			this.weight = w;
		}
		@Override
		public int compareTo(Edge o) {
			return weight - o.weight > 0? 1: -1;
		}
	}
	
	//link all edge starting from the same vertex
	public static class LinkedListNode{
		Edge data;
		LinkedListNode next;
	}
	
	
	public static class Graph {
		List<LinkedListNode> heads;
	}
	
	public static class  MinHeap{
		List<Comparable> store;
		int size;
		
		public MinHeap(){
			store = new ArrayList<Comparable>();
		}
		
		public void add(Comparable d){
			store.add(d);
			bubbleUp(store.size()-1);
		}

		private void bubbleUp(int i) {
			// TODO Auto-generated method stub			
		}
		
		public Comparable pop(){
			if(this.isEmpty())
				return null;
			
			Comparable n = store.get(0);
			bubbleDown(0);
			return n;
		}
		
		private void bubbleDown(int i) {
			// TODO Auto-generated method stub
			
		}

		public boolean isEmpty(){
			return store.size() == 0;
		}
	}
	
	public static class UnionFind{
		private int[] nodes;
		private byte[] ranks;
		private int size;
		
		public UnionFind(int s){
			size = s;
			nodes = new int[s];
			ranks = new byte[s];		
			for(int i=0;i<nodes.length; i++)
				nodes[i] = i;	//every node is its own set	
			for(byte b : ranks)
				b = 0; //0 means empty set
		}
		
		public void union(int p, int q){
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
		
		public int find(int p){
			if(nodes[p] == p)
				return p;
			return find(nodes[p]);
		}
		
		public boolean isConnected(int p, int q){
			return find(p) == find(q);
		}
		
	}
	
	public List<Edge> Kruskal(String[] input, int size){
		List<Edge> result = new ArrayList<Edge>();
		
		MinHeap pq = new MinHeap();
		for(String s: input){
			String[] temp = s.split(",");	
			pq.add(new Edge(Integer.valueOf(temp[0]), Integer.valueOf(temp[1]), Double.valueOf(temp[2])));
		}
		
		UnionFind uf = new UnionFind(size);
		
		while(!pq.isEmpty()){
			Edge e = (Edge) pq.pop();
			
			if(!uf.isConnected(e.v1, e.v2)){
				uf.union(e.v1, e.v2);
				result.add(e);
			}			
		}
		
		return result;
	}
	
	
}
