package org.blueocean;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelQ {	
	public static class SumTask extends RecursiveTask<Long> {
		long i;
		long j;
		int[] nums;
		public SumTask(int[] nums, long i, long j){
			this.nums = nums;
			this.i = i;
			this.j = j;
			System.out.println(i);
			System.out.println(j);
		}
		@Override
		protected Long compute() {
			if(j-i<=250_000){
				System.out.println(j-i);
				System.out.println("return 0");
				return 0L;
			}

			SumTask task1 = new SumTask(nums, i, (i+j)/2);
			SumTask task2 = new SumTask(nums, (i+j)/2, j);
			 invokeAll(task1, task2);

			//task1.fork();
			//task2.fork();
			System.out.println("forking" + (i+j)/2);
			
			return task1.join()+task2.join();		
		}		
	}
	
	public static void main(String[] arg){
		int[] num = new int[1_000_000];
		//long t = System.nanoTime();
		ForkJoinPool forkJoinPool = new ForkJoinPool(3);
		long t = System.nanoTime();
		
		SumTask task = new SumTask(num, 0, 1_000_000);
		long sum = forkJoinPool.invoke(task);
		System.out.println(sum);
		
		System.out.println(System.nanoTime() - t);
		System.out.println("===="+num.length);
		t = System.nanoTime();		
		sum = 0;
		for(int i=0; i<num.length; i++)
			sum = sum + num[i];
		System.out.println(System.nanoTime() - t);
		
	}

}
