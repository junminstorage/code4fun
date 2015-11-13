package org.blueocean.geek4geek;

import java.util.Stack;

public class StackQ {

	static class Node {
		int pos;
		char c;
		Node(int p, char c){
			this.pos = p;
			this.c = c;
		}
	}
	public static int maxValidParenthesis(String input){
		int max = 0;
		Stack<Node> s = new Stack<Node>();
		for(int i=0, j= input.length(); i<j; i++){
			char c = input.charAt(i);
			if(s.isEmpty() || c == '(' || s.peek().c == ')')
				s.push(new Node(i, c));
			else{
				s.pop();
				max = Math.max(i - (s.isEmpty()?-1:s.peek().pos), max);
			}
		}
		return max;
	}
}
