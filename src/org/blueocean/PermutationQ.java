package org.blueocean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermutationQ {
	
	/*
	 * http://www.careercup.com/question?id=5765850736885760
	 * Mapping 
'1' = 'A','B','C' 
'2' = 'D','E','F' 
... 
'9' = 

input: 112 
output :ouput = [AAD, BBD, CCD, AAE, AAF, BBE, BBF, CCE, CCF]
	 */
	
	final static Map<Integer, String[]> map = new HashMap<Integer, String[]>();
	static {map.put(1, new String[]{"A", "B", "C"});
	map.put(2, new String[]{"D", "E", "F"});
	map.put(3, new String[]{"G", "H", "I"});
	map.put(4, new String[]{"J", "K", "L"});
	map.put(5, new String[]{"M", "N", "O"});
	map.put(6, new String[]{"P", "Q", "R"});
	map.put(7, new String[]{"S", "T", "U"});
	map.put(8, new String[]{"V", "W", "X"});
	map.put(9, new String[]{"Y", "Z"});
	}
	public static List<String> getPermutationFromNumber(int number){
		List<String> result = new ArrayList<String>();
		result.add("");
		while(number>0){
			int current = number%10;			
			result = generateStrings(current, result);			
			number = number/10;			
		}	
		return result;
	}

	private static List<String> generateStrings(int current, List<String> result) {
		assert(current>0);
		List<String> newList = new ArrayList<String>();
		
		for(String s : result){
			for(String c : map.get(current))
				newList.add(c.concat(s)); //concat is better than +, + may be optimized by java compiler, sringbuilder is good too
		}
		return newList;
	}
	
	

}
