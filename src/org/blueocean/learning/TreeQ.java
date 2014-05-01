package org.blueocean.learning;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class TreeQ {
	
	class Node{
		Node left;
		Node right;
		Node next;
		Object data;
		
		boolean isLeft;
	}
	
	public static void main(String[] arg){
		TreeQ q = new TreeQ();
		
		Node root = q.new Node();
		Node n1 = q.new Node();
		Node n2 = q.new Node();
		
		Node n3 = q.new Node();
		Node n4 = q.new Node();
		
		
		root.left = n1;
		root.right = n2;
		
		n1.right = n3;
		n3.left = n4;
		
		int level = q.foundDeepestLeftLeaf2(root);
		
		System.out.println(level);
	}
	
	/*
	 * recursive 
	 */
	
	public int foundDeepestLeftLeaf2(Node root){
		
		int levelLeft = 0;
		int levelRight = 0;
		
		if(root.left!=null)
			levelLeft = foundDeepestLeftLeafHelper(root.left, true, 0);
		
		if(root.right!=null)
			levelRight = foundDeepestLeftLeafHelper(root.right, false, 0);
		
		return Math.max(levelLeft, levelRight);
	}
	
	public int foundDeepestLeftLeafHelper(Node root, boolean left, int level){
		if(root.left==null && root.right==null){
			if(left)		
				return level+1;
			else 
				return 0;
		}
		
		int levelLeft = 0;
		int levelRight = 0;
		
		if(root.left!=null)
			levelLeft = foundDeepestLeftLeafHelper(root.left, true, level+1);
		
		if(root.right!=null)
			levelRight = foundDeepestLeftLeafHelper(root.right, false, level+1);
		
		return Math.max(levelLeft, levelRight);		

	}
	
	
	/*
	 * iterative
	 */
	public void foundDeepestLeftLeaf(Node root){
		if(root==null)
			return;
		
		Stack<Node> s = new Stack<Node>();						
		int level = 0;		
		root.isLeft = true;
		Node found = root;
		s.add(root);
		
		while(!s.isEmpty()){
			Stack<Node> t = new Stack<Node>();		
			while(!s.isEmpty()){						
				Node n = s.pop();			
				if(n.left!=null){
					n.left.isLeft = true;
					t.add(n.left);
				}
				else if(n.right!=null)
					t.add(n.right);
				else{
					if(n.isLeft)
						found = n;
				}
			}
			level++;
			s = t;
		}
		
	}
	
	/**
	 * root->left->right
	 * @param root
	 */
	public List<Node> preOrderTraverse(Node root){
		List<Node> result = new ArrayList<Node>();
		if(root==null)
			return result;
		
		Stack<Node> s = new Stack<Node>();		
		s.push(root);		
		while(!s.isEmpty()){
			Node n = s.pop();
			result.add(n);			
			if(n.right!=null)
				s.push(n.right);			
			if(n.left!=null)
				s.push(n.left);			
		}					
		return result;
	}
	
	public List<Node> inOrderTraverse(Node root){
		List<Node> result = new ArrayList<Node>();
		if(root==null)
			return result;
		
		Stack<Node> s = new Stack<Node>();		

		Node n = root;
		
		while(!s.isEmpty() || n!=null){					
			if(n!=null){
				s.push(n);
				n = n.left;
			}
			else{
				Node pre= s.pop();
				result.add(pre);
				n = pre.right;
			}							
		}					
		return result;
	}
	
	public boolean isBST(Node root){
		if(root==null)
			return false;
		
		Stack<Node> s = new Stack<Node>();		

		Node n = root;
		Node pre = null;
		while(!s.isEmpty() || n!=null){					
			if(n!=null){
				s.push(n);
				n = n.left;
			}
			else{
				Node p = s.pop();				
				if(pre!=null && (Integer)pre.data > (Integer)p.data)
						return false;
				pre=p;				
				n = p.right;
			}							
		}					
		return true;
	}
	
	

	public void flattenTree(Node root){		
		Node p = root;
		Stack<Node> s = new Stack<Node>();
		while(p!=null || !s.isEmpty()){	
			if(p.right!=null)
				s.push(p.right);
			
			if(p.left!=null){
				p.right = p.left;
				p.left = null;					
			}else{	
				if(!s.isEmpty()){
					p.right = s.pop();
				}
			}
			
			p = p.right;
		}
	}
}
