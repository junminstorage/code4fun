package org.blueocean;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class CuckooHashTest {

	@Test
	public void testHashFunc(){
		CuckooHash.HashFunc func = new CuckooHash.HashFunc(1);
		Assert.assertTrue(func.hash(1, 100) == func.hash(1, 100));
		CuckooHash.HashFunc func2 = new CuckooHash.HashFunc(2);
		Assert.assertTrue(func2.hash(1, 100) == func2.hash(1, 100));		
		Assert.assertTrue(func.hash(1, 100) != func2.hash(1, 100));
	}
	
	@Test
	public void testHashFunc2(){
		CuckooHash.BitOpHash func = new CuckooHash.BitOpHash(CuckooHash.PRIME_NUMBER3);
		func.reset(100);
		Assert.assertTrue(func.hash(1, 100) == func.hash(1, 100));
		CuckooHash.BitOpHash func2 = new CuckooHash.BitOpHash(CuckooHash.PRIME_NUMBER2);
		func2.reset(100);
		Assert.assertTrue(func2.hash(1, 100) == func2.hash(1, 100));		
	//	Assert.assertTrue(func.hash(1, 100) != func2.hash(1, 100));
		
		System.out.println(func.hash(1, 100) + "-" + func2.hash(1, 100));
	}
	
	@Test
	public void testInsert(){
		CuckooHash hash = new CuckooHash();
		HashSet<String> tags = new HashSet<String>(Arrays.asList("foo", "bar"));
		hash.insert(1, tags, true);
		Assert.assertTrue(hash.get(1).equals(tags));
		
	}
	
	@Test
	public void testGet(){
		CuckooHash hash = new CuckooHash();
		Assert.assertTrue(hash.get(1).equals(Collections.EMPTY_SET));
		HashSet<String> tags = new HashSet<String>(Arrays.asList("foo", "bar"));
		hash.insert(1, tags, true);
		Assert.assertTrue(hash.get(1).equals(tags));
		//Assert.assertTrue(hash.get(1) != tags );
		try{
			hash.get(1).add("yoo");
			Assert.assertTrue(false);
		}catch(UnsupportedOperationException e){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testCuckooHashWithRandomHash(){
		RandomStringGenerator strGen = new RandomStringGenerator();
		CuckooHash hash = new CuckooHash();
		Map<Integer, Set<String>> compare = new HashMap<Integer, Set<String>>();
		
		for(int i=0; i<30000; i++){
			String s = strGen.nextString();
			Set<String> tags = new HashSet<String>(Arrays.asList(s));
			compare.put(Integer.valueOf(i), tags);
			hash.put(i, tags);
			Assert.assertTrue(compare.get(i).equals(hash.get(i)));
		}
		long start = System.nanoTime();
		for(int i=0; i<1; i++){
			
			compare.get(i);
		}
		System.out.println(System.nanoTime() - start);
		start = System.nanoTime();
		for(int i=0; i<1; i++){
			
			hash.get(i);
		}
		System.out.println(System.nanoTime() - start);
	}
	
	@Test
	public void testCuckooHashWithBitOpHash(){
		RandomStringGenerator strGen = new RandomStringGenerator();
		CuckooHash hash = new CuckooHash(16, 0.75f, new CuckooHash.BitOpHash[]{new CuckooHash.BitOpHash(CuckooHash.PRIME_NUMBER2), new CuckooHash.BitOpHash(CuckooHash.PRIME_NUMBER3)});
		Map<Integer, Set<String>> compare = new HashMap<Integer, Set<String>>();
		
		for(int i=0; i<16000; i++){
			String s = strGen.nextString();
			Set<String> tags = new HashSet<String>(Arrays.asList(s));
			compare.put(Integer.valueOf(i), tags);
			hash.put(i, tags);
			Assert.assertTrue(compare.get(i).equals(hash.get(i)));
		}
		long start = System.nanoTime();
		for(int i=0; i<16000; i++){
			
			compare.get(i);
			System.out.println(i);
			Assert.assertTrue(compare.get(i).equals(hash.get(i)));
		}
		System.out.println(System.nanoTime() - start);
//		start = System.nanoTime();
//		for(int i=0; i<1000000; i++){
//			
//			hash.get(i);
//		}
//		System.out.println(System.nanoTime() - start);
	}
	
	public final class RandomStringGenerator {
		  private SecureRandom random = new SecureRandom();

		  public String nextString() {
		    return new BigInteger(130, random).toString(32);
		  }
		}
}
