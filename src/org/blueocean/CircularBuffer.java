package org.blueocean;

import java.util.concurrent.Semaphore;

/*
 * this implementation using a "size" to manage the buffer 
 */
public class CircularBuffer {
	private int size;
	private int capacity;
	private int start;
	
	private Semaphore NotFull;
	private Semaphore NotEmpty;
	private Semaphore mutex;
	
	private Object[] data;
	
	public CircularBuffer(int c){
		this.data = new Object[c];
		this.capacity = c;
		this.size = 0;
		this.start = 0;
	}
	
	public CircularBuffer(int c, boolean thread){
		this(c);
		
		if(thread){
			mutex = new Semaphore(1);
			NotFull = new Semaphore(this.capacity);
			NotEmpty = new Semaphore(0);
		}		
	}
	
	public void producerThread(){
		Runnable r = new Runnable(){
			@Override
			public void run() {
				try {
					NotFull.acquire();
					mutex.acquire();
					int writeIndex = (start + size)%capacity;
					data[writeIndex] = new Object();
					size ++;
					mutex.release();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					NotEmpty.release();
				}
				
			}
			
		};
		
		new Thread(r).start();
	}
	
	public void consumerThread(){
		Runnable r = new Runnable(){
			@Override
			public void run() {
				try {
					NotEmpty.acquire();
					mutex.acquire();
					Object d = data[start];
					start = (start + 1)%capacity;
					size --;
					mutex.release();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					NotFull.release();
				}
				
			}
			
		};
		
		new Thread(r).start();
	}
	
	
	public boolean isEmpty(){
		return this.size == 0;
	}
	
	public boolean isFull(){
		return this.size == this.capacity;
	}
	
	public Object read(){
		if(this.isEmpty())
			return null;
		Object d = data[start];
		start = (start + 1)%capacity;
		this.size --;
		return d;		
	}
	
	public void writeWithoutOverwrite(Object d){
		if(this.isFull())
			return;
		
		int writeIndex = (start + size)%this.capacity;
		data[writeIndex] = d;
		size ++;
	}
	
	public void writeWithOverwrite(Object d){
		int writeIndex = (start + size)%this.capacity;
		data[writeIndex] = d;
		
		if(this.isFull()){
			start = (start + 1)%capacity;
		}else
			size ++;
	}

}
