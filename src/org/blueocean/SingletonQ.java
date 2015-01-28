package org.blueocean;

import org.blueocean.SingletonQ.Singleton;

public class SingletonQ {
	private static SingletonQ instance;
	static int i=0;
	private SingletonQ() {}
	
	protected SingletonQ(int i) {}
	
	
	int j=0;
	private class inner{
		
		private void test(){
			j=0;
		}
	}
	
	private static class Holder {
		private void test(){
			i=0;
		}
		private static SingletonQ instance = new SingletonQ();
	}
	
	public static SingletonQ getInstance(){
		return Holder.instance;
	}
	
	
	public static class Singleton <T> {
		private static Singleton instance;
		
		private Singleton(){}
		
		public synchronized static Singleton create(){
			if(instance ==null)
				instance = new Singleton();
			return instance;
		}
	}

}
