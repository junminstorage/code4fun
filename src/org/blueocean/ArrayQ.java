package org.blueocean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ArrayQ {
	
	/**
	 * find nearest larger element
	 * 
	 * @param input
	 * @return
	 */
	public static Map<Integer, Integer> findNLE(List<Integer> input){		
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		
		if(input==null || input.isEmpty())
			return result;
		
		Stack<Integer> s = new Stack<Integer>();
		s.push(input.get(0));
		for(int sCurr=1; sCurr<input.size(); sCurr++){
			while(!s.isEmpty()){
				if(s.peek()<input.get(sCurr))
					result.put(s.pop(), input.get(sCurr)); 
				else
					break;				
			}			
			s.push(input.get(sCurr));					
		}
		
		while(!s.isEmpty()){
			result.put(s.pop(), Integer.MIN_VALUE);
		}		
		return result;		
	}
}
