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
		
		int counter;
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
	
	
	public boolean isSubtree(Node tree1, Node tree2){
	    if(tree2 == null)
	        return true;
	    if(tree1 == null)
	        return false;

	    if(tree1.data != tree2.data)
	        return isSubtree(tree1.left, tree2) || isSubtree(tree1.right, tree2);
	    else
	        return isSubtreeRec(tree1.left, tree2.left) && isSubtreeRec(tree1.right, tree2.right);    

	}


	public boolean isSubtreeRec(Node tree1, Node tree2){
	    if(tree2 == null)
	        return true;
	    if(tree1 == null)
	        return false;
	        
	    return tree1.data == tree2.data && isSubtreeRec(tree1.left, tree2.left) && isSubtreeRec(tree1.right, tree2.right);        

	}
	
	/*
	 * order statistics tree, log(N)
	 */
	public Node foundkth(Node root, int k){
		if(root==null || k<1)
			return null;
		
		if(root.left==null){
			if(1==k)
				return root;
			else 
				return foundkth(root.right, k-1);
		} else{
			if(root.left.counter+1==k)
				return root;
			else if(root.left.counter+1>k)
				return foundkth(root.left, k);
			else
				return foundkth(root.right, k-root.left.counter-1);
		}
		
	}
	
	/*
	 * recursive 
	 */
	public Node foundDeepestLeftRecursive(Node root){
		if(root==null)
			return null;
		Node max = root;
		int[] maxL = new int[1];
		foundDeepestLeftRecursive(root, true, 0, max, maxL);
		return max;
	}
	
	public void foundDeepestLeftRecursive(Node root, boolean isLeft, int preL, Node max, int[] maxL){
		if(root.left==null && root.right==null){
			if(isLeft && preL+1>maxL[0]){
				maxL[0]=preL+1;
				max = root;
			}			
			return;
		}
		if(root.left!=null)
			foundDeepestLeftRecursive(root.left, true, preL+1, max, maxL);
		if(root.right!=null)
			foundDeepestLeftRecursive(root.right, false, preL+1, max, maxL);
		
	}
	
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
	
	
	public List<Node> postOrderTraverseWith2Stacks(Node root){
		List<Node> result = new ArrayList<Node>();
		Stack<Node> s = new Stack<Node>();
		Stack<Node> s2 = new Stack<Node>();
		s.push(root);
		while(!s.isEmpty()){
			Node current = s.peek();
			s2.push(current);
			s.pop();
			
			if(current.left!=null)
				s.add(current.left);
			if(current.right!=null)	
				s.add(current.right);
		}
		
		while(!s2.isEmpty()){
			result.add(s2.pop());
		}
		
		return result;
	}
	
	public List<Node> postOrderTraverseWithStack(Node root){
		List<Node> result = new ArrayList<Node>();
		Stack<Node> s = new Stack<Node>();
		s.add(root);	
		Node pre = null;
		while(!s.isEmpty()){
			Node current = s.peek();
			//go down
			if(pre==null || pre.left == current || pre.right == current){
				if(current.right!=null)
					s.push(current.right);
				else if(current.left!=null)
					s.push(current.left);				
			}else if(pre!=null && current.left == pre){//go up from left
				if(current.right!=null)
					s.push(current.right);
				
			}else{//go up from right
				result.add(s.pop());
			}
			
			pre = current;
			
		}
		
		return result;
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
