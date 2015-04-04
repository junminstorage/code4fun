package org.blueocean;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class ActivitySelection {
	
	/*
	 * http://www.geeksforgeeks.org/greedy-algorithms-set-1-activity-selection-problem/
	 * assume finish is sorted already
	 */
	public static List<Integer> schedule(int[] start, int[] finish){
		 List<Integer> tasks = new ArrayList<Integer>();
		int pre = 0;
		tasks.add(pre);
	
		Map<String, String> map = Collections.emptyMap();
		map.put("ads", "value");
		
		for(int i = 1; i<finish.length; i++){
			if(finish[pre]<start[i]){
				tasks.add(i);
				pre = i;
			}
		}		
		return tasks;
	}

}
