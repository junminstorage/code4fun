package org.blueocean;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ActivitySelection {
	
	/*
	 * http://www.geeksforgeeks.org/greedy-algorithms-set-1-activity-selection-problem/
	 * assume finish is sorted already
	 */
	public static List<Integer> schedule(int[] start, int[] finish){
		 List<Integer> tasks = new ArrayList<Integer>();
		int pre = 0;
		tasks.add(pre);
		
		for(int i = 1; i<finish.length; i++){
			if(finish[pre]<start[i]){
				tasks.add(i);
				pre = i;
			}
		}		
		return tasks;
	}

}
