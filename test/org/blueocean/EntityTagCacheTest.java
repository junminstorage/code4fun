package org.blueocean;

import java.io.FileNotFoundException;
import java.io.IOException;
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

public class EntityTagCacheTest {

	@Test
	public void testRandomHashFunc(){
		EntityTagCache.HashFunc func = new EntityTagCache.HashFunc(1);
		Assert.assertTrue(func.hash(1, 100) == func.hash(1, 100));
		EntityTagCache.HashFunc func2 = new EntityTagCache.HashFunc(2);
		Assert.assertTrue(func2.hash(1, 100) == func2.hash(1, 100));		
		Assert.assertTrue(func.hash(1, 100) != func2.hash(1, 100));
	}
	
	@Test
	public void testBitOpsHashFunc(){
		EntityTagCache.BitOpHash func = new EntityTagCache.BitOpHash(EntityTagCache.PRIME_NUMBER3);
		func.reset(100);
		Assert.assertTrue(func.hash(1, 100) == func.hash(1, 100));
		EntityTagCache.BitOpHash func2 = new EntityTagCache.BitOpHash(EntityTagCache.PRIME_NUMBER2);
		func2.reset(100);
		Assert.assertTrue(func2.hash(1, 100) == func2.hash(1, 100));		
	}
	
	@Test
	public void testInsert(){
		EntityTagCache hash = new EntityTagCache();
		HashSet<String> tags = new HashSet<String>(Arrays.asList("foo", "bar"));
		hash.insert(1, tags);
		Assert.assertTrue(hash.get(1).equals(tags));
		
	}
	
	@Test
	public void testInsertAndGet(){
		EntityTagCache hash = new EntityTagCache();
		Assert.assertTrue(hash.get(1).equals(Collections.EMPTY_SET));
		HashSet<String> tags = new HashSet<String>(Arrays.asList("foo", "bar"));
		hash.insert(1, tags);
		Assert.assertTrue(hash.get(1).equals(tags));
		Assert.assertTrue(hash.get(1) != tags );
		try{
			hash.get(1).add("yoo");
			Assert.assertTrue(false);
		}catch(UnsupportedOperationException e){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testEntityTagCacheWithRandomHash(){
		RandomStringGenerator strGen = new RandomStringGenerator();
		EntityTagCache hash = new EntityTagCache(16, 0.75f, 
				new EntityTagCache.IntHashFuncI[]{new EntityTagCache.HashFunc(2), new EntityTagCache.HashFunc(3)});
		
		Map<Integer, Set<String>> compare = new HashMap<Integer, Set<String>>();
		
		for(int i=0; i<30000; i++){
			String s = strGen.nextString();
			Set<String> tags = new HashSet<String>(Arrays.asList(s));
			compare.put(Integer.valueOf(i), tags);
			hash.put(i, tags);
			Assert.assertTrue(compare.get(i).equals(hash.get(i)));
		}
		for(int i=0; i<1000; i++){			
			Assert.assertTrue(compare.get(i).equals(hash.get(i)));
		}
		
		long start = System.nanoTime();
		for(int i=0; i<1000; i++){			
			compare.get(i);
		}
		System.out.println(System.nanoTime() - start);
		start = System.nanoTime();
		for(int i=0; i<1000; i++){			
			hash.get(i);
		}
		System.out.println(System.nanoTime() - start);
	}
		
	@Test
	public void testReadFile(){
		try {
			EntityTagCache hash = EntityTagCache.getInstance("entities.csv");
			System.out.println(hash.get(1));
			Assert.assertTrue(hash.get(1).size()==2);
			HashSet<String> tags = new HashSet<String>(Arrays.asList("foo", "bar"));
			Assert.assertTrue(hash.get(1).equals(tags));
			Assert.assertTrue(hash.get(3).isEmpty());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
	}
	
	@Test
	public void testMemoryUsage(){
		RandomStringGenerator strGen = new RandomStringGenerator();
		EntityTagCache hash = new EntityTagCache(16, 0.75f, new EntityTagCache.BitOpHash[]{new EntityTagCache.BitOpHash(EntityTagCache.PRIME_NUMBER2), new EntityTagCache.BitOpHash(EntityTagCache.PRIME_NUMBER3)});
		Map<Integer, Set<String>> compare = new HashMap<Integer, Set<String>>();
		
		for(int i=0; i<1_000_000; i++){
			String s = strGen.nextString();
			Set<String> tags = new HashSet<String>(Arrays.asList(s));
			//compare.put(Integer.valueOf(i), tags);
			hash.put(i, tags);
			//Assert.assertTrue(compare.get(i).equals(hash.get(i)));
		}
		
	    Runtime runtime = Runtime.getRuntime();
	    runtime.gc();
	    long memory = runtime.totalMemory() - runtime.freeMemory();
	    System.out.println("Used memory is bytes: " + memory);
	}
	
	
	@Test
	public void perfCompare(){
		RandomStringGenerator strGen = new RandomStringGenerator();
		EntityTagCache hash = new EntityTagCache(16, 0.75f, new EntityTagCache.BitOpHash[]{new EntityTagCache.BitOpHash(EntityTagCache.PRIME_NUMBER2), new EntityTagCache.BitOpHash(EntityTagCache.PRIME_NUMBER3)});
		Map<Integer, Set<String>> compare = new HashMap<Integer, Set<String>>();
		
		for(int i=0; i<1_000_000; i++){
			String s = strGen.nextString();
			Set<String> tags = new HashSet<String>(Arrays.asList(s));
			compare.put(Integer.valueOf(i), tags);
			hash.put(i, tags);
			Assert.assertTrue(compare.get(i).equals(hash.get(i)));
		}
			
		for(int i=0; i<1_000_000; i++){			
			Assert.assertTrue(compare.get(i).equals(hash.get(i)));
		}
		
		long start = System.nanoTime();
		for(int i=0; i<1_000_000; i++){			
			compare.get(i);
		}
		System.out.println(System.nanoTime() - start);
		start = System.nanoTime();
		for(int i=0; i<1_000_000; i++){
			
			hash.get(i);
		}
		System.out.println(System.nanoTime() - start);
		
		start = System.nanoTime();
		for(int i=0; i<100_000; i++){	
			compare.get(i);
		}
		System.out.println(System.nanoTime() - start);
		start = System.nanoTime();
		for(int i=0; i<100_000; i++){
			
			hash.get(i);
		}
		System.out.println(System.nanoTime() - start);
		
		start = System.nanoTime();
		for(int i=0; i<1000; i++){
			compare.get(i);
		}
		System.out.println(System.nanoTime() - start);
		start = System.nanoTime();
		for(int i=0; i<1000; i++){			
			hash.get(i);
		}
		System.out.println(System.nanoTime() - start);
		
		start = System.nanoTime();
		for(int i=0; i<10; i++){
			compare.get(i);
		}
		System.out.println(System.nanoTime() - start);
		start = System.nanoTime();
		for(int i=0; i<10; i++){			
			hash.get(i);
		}
		System.out.println(System.nanoTime() - start);
	}
	
	public final class RandomStringGenerator {
		private SecureRandom random = new SecureRandom();

		public String nextString() {
			return new BigInteger(130, random).toString(32);
		}
	}
}
