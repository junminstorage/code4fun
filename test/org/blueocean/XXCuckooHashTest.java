package org.blueocean;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Assert;
import org.junit.Test;

public class XXCuckooHashTest {
	
	@Test
	public void testXCuckooHash(){
		RandomStringGenerator strGen = new RandomStringGenerator();
		XXCuckooHash hash = new XXCuckooHash(16, 0.75f, new XXCuckooHash.BitOpHash[]{new XXCuckooHash.BitOpHash(XXCuckooHash.PRIME_NUMBER2), new XXCuckooHash.BitOpHash(XXCuckooHash.PRIME_NUMBER3)});
		Map<Integer, Set<String>> compare = new ConcurrentHashMap<Integer, Set<String>>();
		
		for(int i=0; i<1_000_000; i++){
			String s = strGen.nextString();
			Set<String> tags = new HashSet<String>(Arrays.asList(s));
			compare.put(Integer.valueOf(i), tags);
			hash.put(i, tags);
			Assert.assertTrue(compare.get(i).equals(hash.get(i)));
		}
		long start = System.nanoTime();
		for(int i=0; i<100; i++){
			
			compare.get(i);
			Assert.assertTrue(compare.get(i).equals(hash.get(i)));
		}
		System.out.println(System.nanoTime() - start);
		start = System.nanoTime();
		for(int i=0; i<100; i++){
			
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
