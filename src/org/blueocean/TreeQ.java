package org.blueocean;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class TreeQ {
	static class Node {
		int data;
		Node left;
		Node right;
		Node parent;
	}
	
	public static Node createBST(){
		Node root = new Node();
		root.data = 12;
		
		Node left1 = new Node();
		left1.data = 9;
		
		Node right1 = new Node();
		right1.data = 14;
		
		root.left = left1;
		root.right = right1;
		
		Node left2 = new Node();
		left2.data = 8;
		
		left1.left = left2;
		
		Node right2 = new Node();
		right2.data = 10;
		
		left1.right = right2;
		
		Node left3 = new Node();
		left3.data = 13;
		
		right1.left = left3;
				
		Node right3 = new Node();
		right3.data = 15;
		
		right1.right = right3;
		return root;
	}
	
	/**
	 * assume Object is comparable
	 * @param root
	 * @return
	 */
	public static boolean isBST(Node root){
		assert(root!=null);
		
		List<Node> list = new ArrayList<Node>();
		treeToList(root, list);
		for(int i=1; i<list.size(); i++)
			if(list.get(i).data < list.get(i-1).data)
				return false;		
		return true;
	}
	
	/*
	 * this is wrong, why?
	 */
	public static boolean isBST2(Node root){
		assert(root!=null);
		
		if(root.left==null && root.right==null)
			return true;
		
		if( (root.left==null || isBST2(root.left)) && (root.right==null || isBST2(root.right))){
			if((root.left==null || root.left.data < root.data) && (root.right==null || root.right.data > root.data))
				return true;
			else{
				return false;
			}
		}				
		else
			return false;		
	}
	
	public static void treeToList(Node root, List list){
		if(root==null)
			return;
		treeToList(root.left, list);
		list.add(root);
		treeToList(root.right, list);
				
	}
	
	static Node head = new Node();
	
	
	public static void treeToDLList(Node root){
		if(root==null)
			return;
		treeToDLList(root.left);
		
		head.right = root;
		root.left=head;
		head=root;
		
		treeToDLList(root.right);				
	}
	
	public static Node buildTreeFromOrderedList(List<Integer> input){
		assert(input!=null && !input.isEmpty());
		Node root = new Node();
		root.data = input.get(input.size()/2);
		root.left =  buildTreeFromOrderedList(input, 0, input.size()/2-1);
		root.right =  buildTreeFromOrderedList(input, input.size()/2+1, input.size()-1);		
		return root;
	}
	
	private static Node buildTreeFromOrderedList(List<Integer> input, int left, int right){
		System.out.println(left + " - " + right);
		if(right<left)
			return null;
		
		Node root = new Node();
		root.data = input.get((right+left)/2);
		root.left =  buildTreeFromOrderedList(input, left, (right+left)/2-1);
		root.right =  buildTreeFromOrderedList(input, (right+left)/2+1, right);				
		return root;
		
	}
	
	public static void printByLevel(Node root){
		assert(root!=null);
		int l = 0;
		Queue q  = new ArrayDeque();		
		q.add(root);
		q.add(Integer.valueOf(l));
		
		int pl = 1;
		while(!q.isEmpty()){
			Node n = (Node)q.remove();
			System.out.println(n);
			l = (Integer)q.remove();
			if(pl==l){
				System.out.println("\n");
				pl++;
			}
			if(n.left!=null)
				q.add(n.left);
			q.add(Integer.valueOf(l+1));
			if(n.right!=null)
				q.add(n.right);
			q.add(Integer.valueOf(l+1));
		}		
	}
	
	public static void zigZag(Node root){
		Stack<Node> current = new Stack<Node>();
		Stack<Node> next = new Stack<Node>();		
		current.add(root);
		
		int level = 0;		
		while(!current.isEmpty()){
			while(!current.isEmpty()){
				Node node = current.pop();
				System.out.print(node+" ");
				if(level%2==0){
					if(node.left!=null)
						next.add(node.left);
					if(node.right!=null)
						next.add(node.right);
				}else{
					if(node.right!=null)
						next.add(node.right);
					if(node.left!=null)
						next.add(node.left);
				}
			}
			System.out.println("");
			if(!next.isEmpty()){
				current = next;
				level++;
			}
		}		
	}
	
	/**
	 * in-order visit the tree, and print out the leaves
	 * 
	 * @param root
	 * @return
	 */
	public static List<Node> inOrderVisitLeaves(Node root){
		List<Node> list = new ArrayList<Node>();
		inOrderVisitLeaves(root, list);
		return list;
	}
	
	private static void inOrderVisitLeaves(Node root, List<Node> list){
		if(root==null)
			return;
		else{
			inOrderVisitLeaves(root.left, list);
			if(root.left==null && root.right==null)
				list.add(root);
			inOrderVisitLeaves(root.right, list);
		}
		
	}
	
	public static boolean isSubtree(Node tree1, Node tree2){		
		if(tree1==null || tree2==null)
			return false;
		
		if(tree1.data == tree2.data){
			return treeMatch(tree1, tree2);
		}else{
			return isSubtree(tree1.left, tree2) || isSubtree(tree1.right, tree2);
		}
					
	}
	
	private static boolean treeMatch(Node t1, Node t2){
		if(t2==null)
			return true;
		if(t2!=null && t1==null)
			return false;
		if(t1.data == t2.data)
			return treeMatch(t1.left, t2.left)&&treeMatch(t1.right, t2.right);
		else
			return false;
	}
	
	/**
	 * print each level as LinkedList
	 * @param root
	 * @return
	 */
	public static List<LinkedList<Node>> treeToLL(Node root){
		assert(root!=null);
		
		List<LinkedList<Node>> result = new LinkedList<LinkedList<Node>>();
		LinkedList<Node> current = new LinkedList<Node>();
		
		current.add(root);
		result.add(current);
		
		while(!current.isEmpty()){
			LinkedList<Node> temp = new LinkedList<Node>();
			for(Node n: current){
				if(n.left!=null)
					temp.add(n.left);
				if(n.right!=null)
					temp.add(n.right);
			}
			
			result.add(temp);			
			current = temp;
		}
		
		return result;				
	}
	
	public static Node findNextInOrderSuccessor(Node n){
		assert(n!=null);
		
		if(n.right!=null){
			//findLeftMostChild(Node n.right);
			Node r = n.right;
			while(r.left!=null)
				r = r.left;
			return r;
		}else{
			//findRightLeastParent
			Node up = n.parent;
			while(up!=null && up.left.data!=n.data){
				n = up;
				up = up.parent;				
			}
			return up;
		}				
	}
	
	/**
	 * find LCA for binary search tree
	 * @param n1
	 * @param n2
	 * @param r
	 * @return
	 */
	public static Node findLCABSTree(Node n1, Node n2, Node r){
		assert(n1!=null && n2!=null && r!=null);
		
		if(n1.data==n2.data)
			return n1;
		
		if(r.data == n2.data || r.data==n1.data)
			return r;
		else if(r.data > n2.data && r.data>n1.data)
			return findLCABSTree(n1, n2, r.left);
		else if(r.data < n2.data && r.data<n1.data)
			return findLCABSTree(n1, n2, r.right);
		else
			return r;
		
	}
	
	/**
	 * find LCA for binary tree
	 * if node has pointer to parent
	 * 
	 */
	public static Node findLCABTree(Node n1, Node n2){
		assert(n1!=null && n2!=null);		
		Set<Integer> visited = new HashSet<Integer>();		
		while(n1!=null || n2!=null){
			if(n1!=null){
				if(visited.contains(n1.data))
					return n1;
				else
					visited.add(n1.data);
				
				n1 = n1.parent;
			}
			
			if(n2!=null){
				if(visited.contains(n2.data))
					return n2;
				else
					visited.add(n2.data);
				n2 = n2.parent;
			}
		}		
		return null;
	}
	
	/**
	 * find LCA for binary tree
	 * without pointer to parent
	 * 
	 */
	public static Node findLCABTree(Node n1, Node n2, Node r){
		assert(n1!=null && n2!=null);		
		
		if(r==null)
			return null;
		
		if(r.data == n1.data || r.data == n2.data)
			return r;
		
		Node lChild = findLCABTree(n1, n2, r.left);
		Node rChild = findLCABTree(n1, n2, r.right);
		
		if(lChild !=null && rChild !=null)
			return r;
		else if(lChild == null)
			return rChild;
		else
			return lChild;
				
	}
	

	
}
