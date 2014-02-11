package org.blueocean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MySet {
	
	static List<String> stringPerm(String s){		
		List<String> result = new ArrayList<String>();
		
		if(s==null || s.isEmpty())
			return result;
		
		if(s.length() == 1){
			result.add(s);
			return result;
		}
		
		char s0 = s.charAt(0);
		
		List<String> subResult = stringPerm(s.substring(1));
		for(String sub : subResult){
			result.addAll(addCharToString(s0, sub));
		}
		
		return result;
		
	}
	
	private static List<String> addCharToString(char c, String s){
		List<String> t = new ArrayList<String>();
		for(int index = 0; index <= s.length(); index++){
		 t.add(s.substring(0, index) + c + s.substring(index));
		}
		
		return t;
	}
	
	private String addCharToStringByIndex(char c, String s, int index){
		return s.substring(0, index) + c + s.substring(index);
	}
	
	static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index) {
		ArrayList<ArrayList<Integer>> allsubsets;
		if (set.size() == index) {
			allsubsets = new ArrayList<ArrayList<Integer>>();
			allsubsets.add(new ArrayList<Integer>()); // Empty set
		}else{
			allsubsets = getSubsets(set, index + 1);
			int item = set.get(index);
			ArrayList<ArrayList<Integer>> moresubsets =
					new ArrayList<ArrayList<Integer>>();
			for(ArrayList<Integer> subset : allsubsets) {
				ArrayList<Integer> newsubset = new ArrayList<Integer>();
				newsubset.addAll(subset); //
				newsubset.add(item);
				moresubsets.add(newsubset);
				
			}
			allsubsets.addAll(moresubsets);
			
		}
		return allsubsets;
	}
	public static List<ArrayList<String>> getAll(ArrayList<String> in){
		List<ArrayList<String>> re = new ArrayList<ArrayList<String>>();
				
		if(in==null || in.isEmpty()){
			re.add(new ArrayList<String>());
			return re;
		}
		
		String first = in.remove(0);
		
		List<ArrayList<String>> temp = getAll(in);
		//System.out.println(temp.size());
		for(ArrayList<String> item : temp){
			ArrayList<String> clone = new ArrayList<String>();
			clone.addAll(item);
			re.add(clone);
			
			item.add(first);
			re.add(item);
		}
				
		return re;
		
	}

}
