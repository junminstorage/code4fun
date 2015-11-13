package org.blueocean.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class TreeQ {
	
	public static class Tree {
		int v;
		Tree l;
		Tree r;
		public Tree(int v, Tree l, Tree r){
			this.v = v; this.l = l; this.r = r;
		}
	}
	
	public static boolean isBSTRecur(Tree root){		
		return isBSTRecurUtil(root, null, null);
	}

	public static boolean isBSTRecurUtil(Tree root, Tree max, Tree min){
		if(root==null)
			return true;
		else if( (max!=null && root.v > max.v) || (min!=null && root.v<min.v))
			return false;
		else
			return isBSTRecurUtil(root.l, root, min) && isBSTRecurUtil(root.r, max, root);
	}
	
	
	public static boolean isBSTUsingStack(Tree root){
		Stack<Tree> s = new Stack<Tree>();
		Tree p = root, pre=null;
		while(!s.isEmpty() || p!=null){
			if(p!=null){
				s.push(p);
				p = p.l;
			}
			else{
				Tree curr = s.pop();
				if(pre==null)
					pre = curr;
				else if(pre.v >= curr.v)
					return false;
				p = curr.r;
			}
		}
		return true;
	}
	
	public static boolean isBSTUsingQ(Tree root){
		Deque<Node> q = new ArrayDeque<Node>();
		q.push(new Node(root, null, null));
		while(!q.isEmpty()){
			Node c = q.pop();
			if(c.max!=null&&c.max.v<c.curr.v)
				return false;
			if(c.min!=null&&c.min.v>c.curr.v)
				return false;
			
			if(c.curr.l!=null)
				q.push(new Node(c.curr.l, c.min, c.curr));
			
			if(c.curr.r!=null)
				q.push(new Node(c.curr.r, c.curr, c.max));
		}
		return true;
	}
	
	public static class Node{
		Tree curr;
		Tree min;
		Tree max;
		public Node(Tree curr, Tree min, Tree max){
			this.curr = curr; this.min = min; this.max = max;
		}
	}
	
}
