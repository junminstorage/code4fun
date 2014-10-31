package org.blueocean;

import junit.framework.TestCase;

public class Array3StackTest extends TestCase {
	
	public void test(){
		System.out.println("sd".length());
		System.out.println("地方的收费".length());
	}
	public void testStack(){
		
		Array3Stack stack = new Array3Stack(4);
		
		System.out.println(stack.isFull());
		System.out.println(stack.isEmpty(0));
		System.out.println(stack.isEmpty(1));
		System.out.println(stack.isEmpty(2));
		
		System.out.println("==========");
		stack.push(0, 1);
		System.out.println(stack.isFull());
		System.out.println(stack.isEmpty(0));
		System.out.println(stack.isEmpty(1));
		System.out.println(stack.isEmpty(2));
		
		System.out.println("==========");
		stack.push(1, 1);
		System.out.println(stack.isFull());
		System.out.println(stack.isEmpty(0));
		System.out.println(stack.isEmpty(1));
		System.out.println(stack.isEmpty(2));
		
		System.out.println("==========");
		stack.push(2, 1);
		System.out.println(stack.isFull());
		System.out.println(stack.isEmpty(0));
		System.out.println(stack.isEmpty(1));
		System.out.println(stack.isEmpty(2));
		
		System.out.println("==========");
		stack.push(2, 2);
		System.out.println(stack.isFull());
		System.out.println(stack.isEmpty(0));
		System.out.println(stack.isEmpty(1));
		System.out.println(stack.isEmpty(2));
		
	}

}
