package org.blueocean;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ShortestPathQ {
	
	/*
	 * Dijkstra algorithm for any graph with positive weights
	 * the algorithm essentially is BFS using a priority queue
	 */
	/*
	 * here i assume graph is represented as adjacent matrix, D
	 * vertex is represented as number of 0, 1, ..., n-1
	 * the value at each cell D[i][j] represent weight of the edge
	 * from vertex i to j
	 * 
	 * O(VlgV+ElgV)
	 */
	
	//a custom PQ to hold the list of vertex ordered by their distances from source
	class PQ {
		//the binary heap represented as array
		int[] heap;
		//keep track of vertex position in the heap
		int[] indices;
		
		int[] distance;
		int size;
		
		//time complexity O(size)
		PQ(int s, int[] d){
			size = s;
			heap = new int[s];
			distance = d;
			heapify();
		}
		
		//time complexity O(size)
		void heapify(){
			for(int i = 0; i<size; i++){
				heap[i] = i;
				indices[i] = i;
			}
			
			for(int i=size/2; i>=0; i--)
				bubbleDown(i);
		}
		
		//time complexity O(lg(size))
		void bubbleDown(int index){
			int target = index;
			int left = index*2+1;
			if(left<size && distance[left] < distance[target])
				target = left;
			int right = index*2+1;
			if(right<size && distance[right] < distance[target])
				target = right;
			if(target!=index){
				swap(target, index);
				bubbleDown(target);
			}
		}
		
		void swap(int i, int j){
			int tem = heap[i];
			heap[i] = heap[j];
			heap[j] = tem;
			
			tem = indices[i];
			indices[i] = indices[j];
			indices[j] = tem;
		}
		
		//time complexity O(lg(size))
		void bubbleUp(int index){
			int target = index;
			int parent = (index-1)/2;
			if(parent>=0 && distance[parent] > distance[target])
				target = parent;
			if(target!=index){
				swap(target, index);
				bubbleUp(target);
			}
		}
		
		int min(){
			return heap[0];
		}
		
		//time complexity O(lg(size))
		int removeMin(){
			int i = heap[0];
			heap[0] = heap[--size];
			bubbleDown(0);	
			return i;
		}
		
		int indexOf(int vertex){
			return indices[vertex];
		}
		
		boolean isEmpty(){
			return size == 0;
		}
	}
	
	class Graph {
		int[][] w;
		int size;
		Graph(int size){
			this.size = size;
			w = new int[size][size]; 
		}
		
		/*
		 * for undirected graph representation
		 * call addEdge twice addEdge(i, j w) and addEdge(j, i, w)
		 * 
		 * weight zero means no edge between two vertex
		 */		
		void addEdge(int i, int j, int we){
			w[i][j] = we;
		}
		
		/*
		 * O(VE) algorithm can report negative cycle
		 */
		int[] bellmanford(int source){
			int[] distance = new int[this.size];
			int[] parent = new int[this.size];
			//initialize			
			for(int i=0;i<this.size; i++){
				distance[i] = Integer.MAX_VALUE;
				parent[i] = -1;
			}
			
			distance[source] = 0;
			parent[source] = -1;
			
			for(int i = 1; i< this.size; i++){				
				for(int u = 0; u<this.size; u++){
					for(int v=0; v<this.size; v++){
						if(w[u][v]!=0){//there is edge from u->v
							//relaxation
							if(distance[v] > distance[u] + w[u][v]){
								distance[v] = distance[u] + w[u][v];
								parent[v] = u;
							}							
						}						
					}					
				}				
			}
			
			for(int u = 0; u<this.size; u++){
				for(int v=0; v<this.size; v++){
					if(w[u][v]!=0){//there is edge from u->v
						if(distance[v] > distance[u] + w[u][v]){
							throw new IllegalStateException("Graph has negative cycle!");
						}							
					}						
				}					
			}
			
			return distance;
		}
		
		
		
		/* O(VlgV+ElgV)
		 * given vertex, dijkstra algorithm will calculate 
		 * shortest path from it to any vertex in the graph 
		 */
		int[] dijkstra(int source){
			int[] distance;
			int[] parent;
			//initialize
			distance = new int[this.size];
			parent = new int[this.size];
			for(int i=0;i<this.size; i++){
				distance[i] = Integer.MAX_VALUE;
				parent[i] = -1;
			}
			
			distance[source] = 0;
			parent[source] = -1;
			
			PQ queue = new PQ(this.size, distance);
			
			while(!queue.isEmpty()){
				int u = queue.removeMin();
				for(int v = 0; v < size; v++){//this should be all edges from u
					if(w[u][v] != 0){//there is edge from u to v
						//relaxation
						if(distance[v] > distance[u] + w[u][v]){
							distance[v] = distance[u] + w[u][v];
							parent[v] = u;
							queue.bubbleUp(queue.indexOf(v));
						}
					}
				}
			}
			
			return distance;
		}
		
		class VertexComaprator implements Comparator<Integer> {
			int[] distance;
			public VertexComaprator(int[] d){
				this.distance = d;
			}
			
			@Override
			public int compare(Integer i, Integer j){
				if(distance[i] == distance[j])
					return 0;
				else if(distance[i] > distance[j])
					return 1;
				else
					return -1;
			}			
		}
		
		int[] dijkstraUsingJavaPQ(int source){
			final int[] distance;
			int[] parent;
			//initialize
			distance = new int[this.size];
			parent = new int[this.size];
			for(int i=0;i<this.size; i++){
				distance[i] = Integer.MAX_VALUE;
				parent[i] = -1;
			}
			
			distance[source] = 0;
			parent[source] = -1;
			
			PriorityQueue<Integer> queue = new PriorityQueue<Integer>(this.size, new Comparator<Integer>(){
				@Override
				public int compare(Integer i, Integer j){
					if(distance[i] == distance[j])
						return 0;
					else if(distance[i] > distance[j])
						return 1;
					else
						return -1;
				}	
			});
			
			for(int i = 0; i<size; i++)
				queue.add(i);
			
			while(!queue.isEmpty()){
				int u = queue.remove();
				for(int v = 0; v < size; v++){
					if(w[u][v] != 0){//there is edge from u to v
						//relaxation O(size)
						if(distance[v] > distance[u] + w[u][v]){
							distance[v] = distance[u] + w[u][v];
							parent[v] = u;
							queue.remove(v);//remove by equals()
							queue.add(v);
						}
					}
				}
			}
			
			return distance;
		}
		
		
		
		
	}
		

}
