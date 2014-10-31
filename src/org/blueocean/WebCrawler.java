package org.blueocean;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class WebCrawler {
	
	
	public void driver(){
		BlockingDeque urls = new LinkedBlockingDeque(10);
		ExecutorService service = Executors.newFixedThreadPool(10);
		List<Crawler> runners = new ArrayList<Crawler>();
		for(int i=0; i<10; i++){
			Crawler c = new Crawler(urls, runners); 
			runners.add(c);
			service.submit(c);
		}
	}
	
	private static URL[] crawl(URL url) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static class Crawler implements Runnable{
		BlockingDeque<URL> task;
		List<Crawler> runners;
		public Crawler(BlockingDeque urls, List<Crawler> rs){
			this.task = urls;
			this.runners = rs;			
		}
		public URL getTask(){
			return task.poll();
		}
		@Override
		public void run() {
			URL url;
			try {
				url = (URL)task.poll();
				if(url==null){
					for(Crawler c : runners){
						if(c.getTask()!=null){
							url = c.task.take();
						}
					}
				}
				URL[] urls = crawl(url);
				for(URL u:urls)
					task.put(u);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
	}

}
