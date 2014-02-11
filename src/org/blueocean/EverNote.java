package org.blueocean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class EverNote {
	
	private Map<String, Integer> data = new HashMap<String, Integer>();
	
	public List<String> returnKWords(int k){
		MinHeap heap = new MinHeap();
		
		for(String key : data.keySet()){
			Node n = new Node(key, data.get(key));
			heap.add(n);			
		}
		
		return null;
	}

}
