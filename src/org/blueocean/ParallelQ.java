package org.blueocean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ParallelQ {
	/*
	 * http://www.careercup.com/question?id=5653584985194496
	 * process large file using threads
	 */
	public void ConsumerProducerDriver(){
		ArrayBlockingQueue<String> works = new ArrayBlockingQueue<String>(10);
		new Thread(new Producer(works)).start();
		ExecutorService pool = Executors.newFixedThreadPool(10);
		for(int i=0; i<10; i++)
			pool.submit(new Consumer(works));
		
	}
	public class Producer implements Runnable {
		ArrayBlockingQueue<String> buffer;
		Producer(ArrayBlockingQueue<String> works){
			this.buffer = works;
		}
		
		@Override
		public void run() {
			try {
				this.buffer.put(null);
			} catch (InterruptedException e) {
				Thread.interrupted();
			}
			
		}		
	}
	
	public class Consumer implements Runnable {
		ArrayBlockingQueue<String> buffer;
		Consumer(ArrayBlockingQueue<String> works){
			this.buffer = works;
		}
		
		@Override
		public void run() {
			try {
				this.buffer.take();
			} catch (InterruptedException e) {
				Thread.interrupted();
			}
			
		}		
	}
	
	
	
	
	
	public static class ProcessFileTask extends RecursiveTask{
		File file;
		int start;
		int end;
		final int threshold = 124_000;
		
		ProcessFileTask(File f, int s, int e){
			this.file = f;
			this.start = s;
			this.end = e;
		}
		
		@Override
		protected Object compute() {
			if(end-start+1<=threshold){
				try {
					RandomAccessFile fP = new RandomAccessFile(file, "r");
					fP.seek(start);
					//fP.readByte();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			int m = (start+end)/2;
			
			ProcessFileTask p1 = new ProcessFileTask(file, start, m);
			ProcessFileTask p2 = new ProcessFileTask(file, start, m);
			invokeAll(p1, p2);
			
			p1.join();
			p2.join();
			
			return null;
		}
		
	}
	
	public static class Result{
		
	}
	public static void parallelProcessFile(final File f){
		ExecutorService pool = Executors.newFixedThreadPool(10);
		long size = f.length();
		final int fixSize = 1240_000;
		for(int i=0; i<size/fixSize+1; i++){
			final long start = fixSize*i;
			final long end = fixSize*(i+1)>size?size-1:fixSize*(i-1);
		
			pool.submit(new Callable<Result>(){
			@Override
			public Result call() throws Exception {
				return processFile(f, start, end);
			}
			
		});
		}
		
	}
	
	public static Result processFile(File f, long start, long end){
		return new Result();
	}
	
	private  int t;
	public static class IOUtils{

		public static String toString(InputStream input, Charset utf8) {
			// TODO Auto-generated method stub
			return null;
		}
		
	};
	/*
	 * parallel image download
	 */
	public static class ImageInfo {

	}
	public static class Renderer {
		private final ExecutorService executor;
		
		Renderer(ExecutorService e) {executor = e;}
		
		/*
		 * A lot of syntax boilerplate, but the basic idea is simple: wrap long-running computations in Callable<String> and submit() them to a thread pool of 10 threads. Submitting returns some implementation of Future<String>, most likely somehow linked to your task and thread pool. 
		 */
		void renderPage(String url){
			final List<String> images = getListOfImages(url);
			List<Future<ImageInfo>> tasks = new ArrayList<Future<ImageInfo>>();
			CompletionService<ImageInfo> service = new ExecutorCompletionService(this.executor);
		
			for(final String imgUrl : images){
				Callable c = new Callable<ImageInfo>(){
					@Override
					public ImageInfo call() throws Exception {
						String s = imgUrl;
						return new ImageInfo();
					}};
					
				Future<ImageInfo> future = service.submit(c);
			}
				
			for(String s  : images){
				try {
					ImageInfo result = service.take().get();
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		}

		private final ExecutorService pool = Executors.newFixedThreadPool(10);
		public Future<String> dowloadParallel(final URL url){
			
			return pool.submit(new Callable<String>(){

				@Override
				public String call() throws Exception {
					return downloadContents(url);
				}
				
			});
		}
		
		
		private List<String> getListOfImages(String url) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public String downloadContents(URL url) throws IOException {
		    try(InputStream input = url.openStream()) {
		        return IOUtils.toString(input, StandardCharsets.UTF_8);
		    }
		}
		
	}
	
	
	/*
	 * fork and join
	 */
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
