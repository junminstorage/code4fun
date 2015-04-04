package org.blueocean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Assert;
import org.junit.Test;

public class XEntityTagCacheTest {
	
	@Test
	public void testPutAndGet(){
		RandomStringGenerator strGen = new RandomStringGenerator();
		XEntityTagCache hash = new XEntityTagCache(16, 0.75f, new XEntityTagCache.BitOpHash[]{new XEntityTagCache.BitOpHash(XEntityTagCache.PRIME_NUMBER2), new XEntityTagCache.BitOpHash(XEntityTagCache.PRIME_NUMBER3)});
		Map<Integer, Set<String>> compare = new ConcurrentHashMap<Integer, Set<String>>();
		
		for(int i=0; i<100_000; i++){
			String s = strGen.nextString();
			Set<String> tags = new HashSet<String>(Arrays.asList(s));
			compare.put(Integer.valueOf(i), tags);
			hash.put(i, tags);
			Assert.assertTrue(compare.get(i).equals(hash.get(i)));
		}
		
		for(int i=0; i<100_000; i++){		
			compare.get(i);
			Assert.assertTrue(compare.get(i).equals(hash.get(i)));
		}		
	}
	
	@Test
	public void testUpdateAndGetTags(){
		RandomStringGenerator strGen = new RandomStringGenerator();
		XEntityTagCache hash = new XEntityTagCache(16, 0.75f, new XEntityTagCache.BitOpHash[]{new XEntityTagCache.BitOpHash(XEntityTagCache.PRIME_NUMBER2), new XEntityTagCache.BitOpHash(XEntityTagCache.PRIME_NUMBER3)});
		Map<Integer, Set<String>> compare = new ConcurrentHashMap<Integer, Set<String>>();
		
		for(int i=0; i<100_000; i++){
			String s = strGen.nextString();
			Set<String> tags = new HashSet<String>(Arrays.asList(s));
			compare.put(Integer.valueOf(i), tags);
			hash.put(i, tags);
			Assert.assertTrue(compare.get(i).equals(hash.get(i)));
		}
		
		Random ran = new Random();
		for(int i=0; i<1000; i++){
			int index = ran.nextInt(i+1);
			String s = strGen.nextString();
			Set<String> tags = new HashSet<String>(Arrays.asList(s));
			hash.updateTags(index, tags);
			Assert.assertTrue(hash.getTags(index).equals(tags));
		}		
	}
	
	@Test
	public void perfCompare(){
		RandomStringGenerator strGen = new RandomStringGenerator();
		XEntityTagCache xhash = new XEntityTagCache(16, 0.75f, new XEntityTagCache.BitOpHash[]{new XEntityTagCache.BitOpHash(XEntityTagCache.PRIME_NUMBER2), new XEntityTagCache.BitOpHash(XEntityTagCache.PRIME_NUMBER3)});
		Map<Integer, Set<String>> compare = new ConcurrentHashMap<Integer, Set<String>>();
		EntityTagCache hash = new EntityTagCache(16, 0.75f, new EntityTagCache.BitOpHash[]{new EntityTagCache.BitOpHash(EntityTagCache.PRIME_NUMBER2), new EntityTagCache.BitOpHash(EntityTagCache.PRIME_NUMBER3)});
		
		for(int i=0; i<1_000_000; i++){
			String s = strGen.nextString();
			Set<String> tags = new HashSet<String>(Arrays.asList(s));
			compare.put(Integer.valueOf(i), tags);
			hash.put(i, tags);
			xhash.put(i, tags);
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
		for(int i=0; i<1_000_000; i++){			
			xhash.get(i);
		}
		System.out.println(System.nanoTime() - start);
	}
	
	@Test
	public void testReadFile(){
		try {
			XEntityTagCache hash = XEntityTagCache.getInstance("entities.csv");
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
	public void threadTest(){
		try {
			XEntityTagCache hash = new XEntityTagCache();
			HashSet<String> oldtags = new HashSet<String>(Arrays.asList("foo", "bar"));
			hash.put(1, oldtags);
			HashSet<String> tags = new HashSet<String>(Arrays.asList("foo", "bar", "xyz"));
			Thread user = new Thread(new CacheUser(hash, oldtags, tags));		
			user.start();			
			user.join();
			Thread user2 = new Thread(new CacheUser(hash, tags, tags));
			user2.start();
			user2.join();
			Assert.assertTrue(hash.get(1).equals(tags));
		}  catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		
	}
	
	
	static class CacheUser implements Runnable {
		XEntityTagCache cache;
		Set<String> oldTags;
		Set<String> tags;
		CacheUser(XEntityTagCache xcache, Set<String> old, Set<String> ts){
			this.cache = xcache;
			this.tags = ts;
			this.oldTags = old;
		}
		@Override
		public void run() {
			Assert.assertTrue(cache.get(1).equals(oldTags));
			cache.updateTags(1, tags);
			Assert.assertTrue(cache.get(1).equals(tags));
		}
		
	}
	
	public final class RandomStringGenerator {
		  private SecureRandom random = new SecureRandom();

		  public String nextString() {
		    return new BigInteger(130, random).toString(32);
		  }
		}

}
