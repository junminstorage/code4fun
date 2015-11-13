package org.blueocean.geek4geek;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class StringQTest {
	
	@Test
	public void lcs(){
		assertEquals("abc", StringQ.LCS("abcd", "babc"));
		assertEquals("abecd", StringQ.LCS("abecd", "babecd"));
	}
	
	@Test
	public void printSuperSet(){
		
		final List l;
		{
			l = Collections.EMPTY_LIST;
		}
		
		l = Collections.emptyList();
		
		StringQ.printSuperSet(new char[]{'a','b','c'});
		
		StringQ.printSuperSetIteratively(new char[]{'a','b','c'});
	}
	
	@Test
	public void test(){
		Set<List<Character>> t = new HashSet<List<Character>>();
		//Collections.sort
		t.add(Arrays.asList(new Character[]{'a', 'b', 'a'}));
		t.add(Arrays.asList(new Character[]{'a', 'b', 'a'}));
		
		//t.add(new TreeSet(Arrays.asList(new Character[]{'a', 'a', 'b'})));
		
		//t.add(Arrays.asSet(new Character[]{'a', 'a', 'b'}));
		//t.add(Arrays.asList(new Character[]{'a', 'b', 'a'}));
		
		System.out.println(t);
		
	}

}
