package org.blueocean;

import java.util.Stack;

public class Tower{
	final Stack<Integer> stack; 
	int index;
	Tower(int n){
		index = n;
		stack = new Stack<Integer>();		
	}
	
	public void addTop(int disk){
		if(disk<=0)
			throw new IllegalArgumentException("arg has to be positive" + disk);
		else if(!stack.isEmpty() && disk >= peekTop()){
			throw new IllegalArgumentException("arg has to be valid" + disk + peekTop());				
		}else{
			stack.push(disk);
		}
	}
	
	public Integer peekTop(){
			return stack.peek();
	}
	
	public Integer removeTop(){
			return stack.pop();
	}		
	
	private void moveTop(Tower dest){
		dest.addTop(removeTop());
	}
	
	public void moveTower(Tower dest, Tower buffer, int numOfDisk){
		if(numOfDisk==0)
			return;
		
			this.moveTower(buffer, dest, numOfDisk-1);
			System.out.println(numOfDisk-1 + "before");
			moveTop(dest);
			System.out.println("moveTop");
			buffer.moveTower(dest, this, numOfDisk-1);			
			System.out.println(numOfDisk-1);	
	}
}
