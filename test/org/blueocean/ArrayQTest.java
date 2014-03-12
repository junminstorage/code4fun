package org.blueocean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class ArrayQTest extends TestCase {
	List<Integer> test;
	/*public void setUp(){
		Integer[] input = {3, 5, 8, 1, 9};
		test = Arrays.asList(input);
	}*/
	
	public void testFindNLE(){
		Integer[] input = {3, 5, 8, 1, 9};
		test = Arrays.asList(input);
		Map<Integer, Integer> result = ArrayQ.findNLE(test);		
		System.out.println(result.toString());
		
		Integer[] inputinput = {3, 5, 38, 1, 9};
		test = Arrays.asList(inputinput);
		result = ArrayQ.findNLE(test);		
		System.out.println(result.toString());
		
		test = Arrays.asList(new Integer[]{5, 0, 8, 3, 17, 9, 9});
		result = ArrayQ.findNLE(test);		
		System.out.println(result.toString());
	}
}
