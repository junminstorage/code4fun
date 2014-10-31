package org.blueocean;

public class Array3Stack {
	private int[] store;
	private int capacity;
	private int size;
	
	private int leftEnd;
	private int rightEnd;
	
	private int middleLeft;
	private int middleRight;
	private boolean pushLeft;
	
	public Array3Stack(int c){
		this.capacity = c;
		this.store = new int[this.capacity];
		this.size = 0;
		this.leftEnd = -1;
		this.rightEnd = this.capacity;
		
		this.middleLeft = this.capacity;
		this.middleRight = -1;
		this.pushLeft = true;
	}
	
	public boolean isEmpty(int index){
		if(index==0)
			return this.leftEnd==-1;
		
		if(index==1)
			return this.middleLeft>this.middleRight;
		
		if(index==2)
			return this.rightEnd == this.capacity;
		
		return true;
	}
	
	public boolean isFull(){
		return size>=this.capacity;
	}
	
	public void push(int index, int v){
		if(isFull())
			return;
		
		if(index==0){
			if(leftEnd+1<middleLeft && leftEnd+1<rightEnd){
				store[++leftEnd] = v;
			}else{
				this.shiftRight();
				store[++leftEnd] = v;
			}
		}
		
		if(index==1){
			if(this.pushLeft){
				if(this.middleLeft == this.capacity){
					this.middleLeft = (this.leftEnd + this.rightEnd)/2;
					store[middleLeft] = v;
					this.middleRight = this.middleLeft;
				}else{
					if(middleLeft-1>leftEnd)
						store[--middleLeft] = v;
					else{
						this.shiftRight();
						store[--middleLeft] = v;
					}
				}
				this.pushLeft = false;
			}else{
				if(middleRight+1<rightEnd)
					store[++middleRight] = v;
				else{
					this.shiftLeft();
					store[++middleRight] = v;
				}
				this.pushLeft = true;
			}
		}
		
		if(index==2){
			if(rightEnd-1>middleRight && rightEnd-1>leftEnd)
				store[--rightEnd] = v;
			else{
				this.shiftLeft();
				store[--rightEnd] = v;
			}
		}
		size++;
	}
	
	public int pop(int index){
		if(isEmpty(index))
			return -1;
		
		if(index==0){
			return store[leftEnd--];
		}
		
		if(index==1){
			if(this.pushLeft){
				this.pushLeft = false;
				return store[middleRight--];
			}
			else{
				this.pushLeft = true;
				return store[middleLeft++];
			}
			
		}
		
		if(index==2){
			return store[rightEnd++];
		}
		
		return -1;
	}
	
	private void shiftLeft(){
		int newMiddleLeft = (this.leftEnd + this.middleLeft)/2;
		int temp = newMiddleLeft;
		for(int i= this.middleLeft; i<=this.middleRight; i++){
			store[temp++] = store[i];
		}
		this.middleLeft = newMiddleLeft;
		this.middleRight = --temp;
	}
	
	private void shiftRight(){
		int newMiddleRight = (this.rightEnd + this.middleRight)/2;
		int temp = newMiddleRight;
		for(int i= this.middleRight; i>=this.middleLeft; i--){
			store[temp--] = store[i];
		}
		this.middleLeft = ++temp;
		this.middleRight = newMiddleRight;
	}
}
