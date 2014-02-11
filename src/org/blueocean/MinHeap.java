package org.blueocean;

import java.util.ArrayList;
import java.util.List;
 
public class MinHeap {
	List<Node> data;
	int max;
	
	MinHeap (){
		data = new ArrayList<Node>();
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Node n: data){
			sb.append(n.f + n.w);
		}
		return sb.toString();
	}
	public Node peekMin(){
		return data.get(0);
	}
	
	public int size(){
		return data.size();
	}
	
	public void add(Node n){
		data.add(n);
		int size = data.size();
		bubbleUp(size-1);
	}
	
	public void bubbleUp(int i){
		if(i==0)
			return;
		int parent = (i -1)/2;
		if(parent>=0 && data.get(parent).compareTo(data.get(i))>0){
			swap(i, parent);
			bubbleUp(parent);
		}
	}
	
	public void bubbleDown(int i){
		if(i==data.size()-1)
			return;
		int left = i*2+1;
		int right = i*2+2;
		int min=0;
		Node minN = null;
		if(left<=data.size()-1){
			minN = data.get(left);
			min = left;
		}
		if(right<=data.size()-1){
			Node rightN = data.get(right);
			if(rightN.compareTo(minN)<0){
				minN = rightN;
				min = right;
			}
		}
		
		if(minN!=null && minN.compareTo(data.get(i))<0) {
			swap(i, min);
			bubbleDown(min);
		}		
	}

	public void swap(int i, int j){
		Node temp = data.get(i);
		data.set(i, data.get(j));
		data.set(j, temp);
	}
	
	public Node removeFirst(){		
		if(!data.isEmpty()){
			int size = data.size();
			Node result = data.get(0);
			data.set(0, data.get(size-1));
			if(size>1)
				data.remove(size-1);
			bubbleDown(0);
			return result;
		}else{
			return null;
		}
	}

	public void add(String key, Integer integer) {
		// TODO Auto-generated method stub
		
	}
}

