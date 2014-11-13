package org.blueocean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamConstants;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.blueocean.LinkedListQ.SortedListNode;

public class TreeQ {
	static class Node {
		char v;
		int data;
		List<Node> children;
		boolean visited;
		
		Node left;
		Node right;
		Node parent;
		Node next;
		int level;
		public Node(char c){
			v = c;
		}
		
		public Node(){
		}

		public Node(int v2) {
			data = v2;
		}		
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
		
		//left1.right = right2;
		
		Node left3 = new Node();
		left3.data = 13;
		
		//right1.left = left3;
				
		Node right3 = new Node();
		right3.data = 15;
		
		//right1.right = right3;
		return root;
	}
	
	
	/*
	http://www.careercup.com/page?pid=facebook-interview-questions&n=2
	*/

	public static void sinkZero(Node root){
	    if(root==null)
	        return;
	        
	    sinkZero(root.left);
	    sinkZero(root.right);
	     if(root.data ==0){
	         if(root.left!=null && root.left.data!=0){
	             root.data = root.left.data;
	             root.left.data = 0;
	             sinkZeroTopDown(root.left);
	         }else if(root.right!=null && root.right.data!=0){
	             root.data = root.right.data;
	             root.right.data = 0;
	             sinkZeroTopDown(root.right);
	         }
	     }
	}


	public static  void sinkZeroTopDown(Node root){
	    if(root==null)
	        return;
	     
	    if(root.data ==0){
	         if(root.left!=null && root.left.data!=0){
	             root.data = root.left.data;
	             root.left.data = 0;
	             sinkZeroTopDown(root.left);
	         }else if(root.right!=null && root.right.data!=0){
	             root.data = root.right.data;
	             root.right.data = 0;
	             sinkZeroTopDown(root.right);
	         }
	     }    

	}
	
	/*
	 * 
	 */
	public static int findMedianInAVL(Node root){
		if(root==null)
			return -1;
		
	    Stack<Node> sL = new Stack<Node>();
	    Stack<Node> sR = new Stack<Node>();
	    Node left = root;
	    Node right = root;
	    Node preR = null;
	    Node preL = null;
	    
	    while( (!sL.isEmpty() || left!=null) && (!sR.isEmpty()|| right!=null) ){
	        if(left!=null){
	            sL.push(left);
	            left = left.left;
	        }else if(right!=null){
	            sR.push(right);
	            right = right.right;
	        }else{
	            Node l = sL.pop();
	            Node r = sR.pop();
	            
	            if(l==r)
	                return l.data;
	            else if(preR!=null && l==preR && preL!=null && r==preL)
	                return (l.data + r.data)/2;
	            else{
	                preL = l;
	                preR = r;
	                
	                left = l.right;
	                right = r.left;
	            }              
	        }    
	    }
	    
	    return -1;
	}
	/*
	 * http://www.geeksforgeeks.org/amazon-interview-experience-set-142-campus-sde-1/
	 * Given a node in a binary tree, find all the nodes which are at distance K from it. 
	 * Root node is also given.
	 * 
	 */
	//using bottom up approach to find the nodes above target
	//then using up-down approach to find the nodes below target
	public static int treeVisit(Node current, Node target, int k){
		
		if(current==null)
			return -1;
		if(current == target)
			return 0;
				
		int dL = treeVisit(current.left, target, k);
		
		int dR = treeVisit(current.right, target, k);
		
		if(dL!=-1){
			
			if(dL+1==k){
				System.out.println(current.data);
			}else{
				findKDistanceNode(current.right, k-dL-2);
			}
			
			return dL + 1;
		}else if(dR!=-1){
			
			if(dR+1==k){
				System.out.println(current.data);
			}else{
				findKDistanceNode(current.left, k-dL-2);
			}
			
			return dR + 1;
		}else
			return -1;	
	}
	
	
	public static List<Node> findKDistanceNodeFromGivenNode(Node root, Node t, int k){
		if(root==null)
			return Collections.EMPTY_LIST;
		
		if(root==t)
			return findKDistanceNode(root, k);
		else{
			int dL = findDistance(1, root.left, t);
			int dR = findDistance(1, root.right, t);
			List<Node> list1 = new ArrayList<Node>();
			List<Node> list2 = new ArrayList<Node>();
			if(dL==-1){
				list1= findKDistanceNode(root.right, k+dR-1);
				
				if(dR-k>0)
					list2= findKDistanceNode(root.right, dR-k-1);
				else if(dR-k<0)
					list2= findKDistanceNode(root.left, k-dR-1);
				else
					list2.add(root);
			}else{
				list1= findKDistanceNode(root.left, k+dL-1);
				if(dL-k>0)
					list2= findKDistanceNode(root.left, dL-k-1);
				else if(dL-k<0)
					list2= findKDistanceNode(root.right, k-dL-1);
				else
					list2.add(root);
			}
			list1.addAll(list2);
			return list1;
		}
	}
	
	public static int findDistance(int d, Node c, Node t){
		if(c==null)
			return -1;
		else if(c==t){
			return d;
		}
		else{
			int dL = findDistance(d+1, c.left, t);
			int dR = findDistance(d+1, c.right, t);
			return dL==-1?dR:dL;
		}
	}
	
	public static List<Node> findKDistanceNode(Node root, int k){
		List<Node> result = new ArrayList<Node>();
		
		if(root==null)
			return result;
	
		findKDistanceNodeRec(root, 0, k, result);	
		return result;
	}
	
	public static void findKDistanceNodeRec(Node c, int d, int k, List<Node> result){
		if(c==null)
			return;
		if(d == k){
			result.add(c);
			return;
		}else{
			findKDistanceNodeRec(c.left, d+1, k, result);
			findKDistanceNodeRec(c.right, d+1, k, result);
		}
		
	}
	
	/*
	 * 	Given a binary tree all the leaf nodes in the form of a doubly linked list. Find the height of the tree.
	 */
	public static int heightOfBSTWithDLLLeaves(Node head){
		Node current = head;
		
		Set<Node> visited = new HashSet<Node>();
		Node leaf = travel(head, visited);
		
		Set<Node> leaves = new HashSet<Node>();
		current = leaf;
		while(!leaves.contains(current)){
			current = current.next;
		}
		
		return h(head, leaves);
	}
	
	private static int h(Node head, Set<Node> leaves){
		if(leaves.contains(head))
			return 0;
		return 1 + Math.max(h(head.left,  leaves), h(head.right, leaves));
	}
	
	
	private static Node travel(Node head, Set<Node> v){
		if(v.contains(head))
			return head;
		
		v.add(head);
		travel(head.left, v);
		travel(head.right, v);
		
		return null;
		
	}
	
	
	/*
	 * In a BST two nodes were swapped. Given the pointer to root node find the two nodes and rectify the tree. He asked the approach then asked me to code the same in collabedit.
http://www.geeksforgeeks.org/fix-two-swapped-nodes-of-bst/
	 */
	public static void fix2SwappedNodeOfBST(Node head){
		if(head==null)
			return;
		Node p1 = null;
		Node p2 = null;
		Stack<Node> s = new Stack<Node>();
		Node current = head;
		Node pre = null;
		Node last = null;
		Node second = null;
		int counter = 0;
		while(!s.isEmpty() || current!=null){
			if(current!=null){
				s.add(current);
				current = current.left;
			}else{
				Node c = s.pop();
				counter++;
				if(counter==2)
					second = c;
				last = c;
				if(pre!=null){
					//do the checking
					if(pre.data > c.data){
						if(p1==null)
							p1 = pre;
						else
							p2 = c;
					}
				}
				
				pre = c;
				//move to next
				current = c.right;				
			}
		}
		
		if(p1==null)
			return;
		
		if(p2==null){
			if(last.data < p1.data)
				p2 = last;
			
			else if(second!=null && p1.data > second.data) 
				p2 = second;
		}
		
		if(p1!=null && p2!=null){
			int temp = p1.data;
			p1.data = p2.data;
			p2.data = temp;
		}
		
	}
	
	
	/*
	 * http://www.careercup.com/question?id=6215580004646912
	 * Print a tree in Level Order with a newline after each depth
	 */
	public static void levelTraveseTree(Node tree){
		Deque<Node> q = new ArrayDeque<Node>();
		q.add(tree);

		while(!q.isEmpty()){
			StringBuilder sb = new StringBuilder();
			Deque<Node> temp = new ArrayDeque<Node>();
			while(!q.isEmpty()){
				Node c = q.removeFirst();
				if(c.left!=null)
					temp.addLast(c.left);
				if(c.right!=null)
					temp.addLast(c.right);
				sb.append(c.data);
			}
			System.out.println(sb.toString());			
			q = temp;
		}
		
	}
	
	public static void levelOrderTraversalOfTree(Node head){
		if(head==null)
			return;
		int h = 12;
		for(int i=0; i<h; i++)
			levelOrderTraversalOfTree(head, 0, i);
	}
	
	public static void levelOrderTraversalOfTree(Node current, int currentLevel, int level){
		if(current==null)
			return;
		if(currentLevel == level)
			System.out.println(current.data);
		else{
			levelOrderTraversalOfTree(current.left, currentLevel+1, level);
			levelOrderTraversalOfTree(current.right, currentLevel+1, level);
		}
	}
	
	//level order traversal of tree
	public static void levelTraveseTreeRec(Node head){
		if(head==null)
			return;
		Deque<Node> temp = new ArrayDeque<Node>();
		temp.addFirst(head);
		levelTraveseTreeRec(temp);
	}
	
	public static void levelTraveseTreeRec(Deque<Node> preLevel){
		if(preLevel.isEmpty())
			return;

		StringBuilder sb = new StringBuilder();
		Deque<Node> temp = new ArrayDeque<Node>();
		while(!preLevel.isEmpty()){
			Node c = preLevel.removeFirst();
			if(c.left!=null)
				temp.addLast(c.left);
			if(c.right!=null)
				temp.addLast(c.right);
			sb.append(c.data);
		}
		System.out.println(sb.toString());			
		levelTraveseTreeRec(temp);
		
	}
	
	/*
	 * http://www.careercup.com/question?id=6266917077647360
	 */
	//bottom up
	public static Node revertTree(Node tree){
		if(tree==null)
			return null;
		
		if(tree.left==null && tree.right==null)
			return tree;
		
		Node left = tree.left;
		Node pre = revertTree(tree.left);
		
		left.right = tree;
		left.left = tree.right;
		tree.left = null;
		tree.right = null;
		
		return pre;
		
	}
	
	//top down
	public static Node revertTreeTD(Node head){
		Node h = revertTreeTDUtil(head);
		head.left = null;
		head.right = null;
		return h;
	}
	public static Node revertTreeTDUtil(Node tree){
		if(tree.left==null)
			return tree;
		
		Node left = tree.left;
		
		left.right = tree;
		left.left = tree.right;
		
		return revertTree(tree.left);
		
	}
	
	/*
	 * http://www.careercup.com/question?id=5735068173598720
	 */
	public static int maxDepth(Node tree){
		if(tree==null)
			return 0;
		int[] max = new int[1];
		maxDepthRecur(tree, 0, max);
		return max[0];
	}
	
	private static void maxDepthRecur(Node node, int currDepth, int[] max){
		if(node==null){
			if(currDepth>max[0])
				max[0] = currDepth;
			return;
		}else{
			currDepth++;
			maxDepthRecur(node.left, currDepth, max);
			maxDepthRecur(node.right, currDepth, max);
		}
				
	}
	//solution 2
	public static int maxDepth2(Node tree){
		if(tree==null)
			return 0;
		return 1+ Math.max(maxDepth2(tree.left), maxDepth2(tree.right));
	}
	
	/*
	 * Print all paths of a binary tree from root to leaf. 
	 */
	public static void printAllPath(Node tree){
		if(tree==null)
			return;
		List<Integer> path = new ArrayList<Integer>();
		printAllPath(tree, path);
	}
	
	public static void printAllPath(Node tree, List<Integer> path){
		path.add(tree.data);
		if(tree.left==null&&tree.right==null){	
			System.out.println(path);
			return;
		}
		
		if(tree.left!=null)
			printAllPath(tree.left, new ArrayList<Integer>(path));
		if(tree.right!=null)
			printAllPath(tree.right, new ArrayList<Integer>(path));			
		
	}
	
	
	/*
	 * Are two Binary Trees mirror image of each other?
	 */
	public static boolean isMirror(Node tree1, Node tree2){
		if(tree1==null && tree2==null)
			return true;
		if(tree1==null || tree2==null)
			return false;
		
		return tree1.data == tree2.data && isMirror(tree1.left, tree2.right) 
				&& isMirror(tree1.right, tree2.left); 
	}
	
	
	/*
	 * findNextInOrderSuccessor with a stack
	 */
	public static Node findNextInOrderSuccessor2(Node tree, Node target){
		if(target==null || tree==null)
			return null;
		Stack<Node> s = new Stack<Node>();
		Node current = tree;
		Node pre = null;
		while(!s.isEmpty() || current!=null){
			if(current!=null){
				s.add(current);
				current = current.left;
			}else{
				Node p = s.pop();				
				if(pre!=null && pre == target)
					return p;
				pre = p;
				current = p.right;	
			}
		}		
		return null;
	}
	
	
	/*
	 * findNextInOrderSuccessor with morris traversal
	 * Node current = root;
		while(current!=null){			
			if(current.left==null){
				System.out.println(current.data);
				current = current.right;
			}else{
				
				Node pre = current.left;
				Node temp = findRightMostNode(current.left);
				
				if(temp==pre){//go up
					pre.right = null;
					System.out.println(current.data);
					current = current.right;//this will either go up to its parent or go down to its child
				}else{//go down to left	
					temp.right = current;
					current = current.left;
				}
			}
			
		}
	 */
	public static Node findNextInOrderSuccessor3(Node tree, Node target){
		Node current = tree;
		Node pre = null;
		Node found = null;
		while(current!=null){
			if(current.left==null){
				System.out.println(current.data);
				if(pre!=null && pre == target)
					found = current;
				pre = current;
				current = current.right;//may follow the thread -- go up
			}else{
				Node left = current.left;		
				Node temp = findRightMostNode(left, current);
				
				if(temp==pre){//check if it is going up
					System.out.println(current.data);
					if(pre!=null && pre == target)
						found = current;
					pre = current;
					current = current.right;					
					temp.right = null;
				}else{
					temp.right = current;//threaded
					current = left;//go down left
				}	
			}				
		}
		
		return found;
	}
	
	private static Node findRightMostNode(Node left, Node current) {
		while(left.right!=null && left.right!= current)
			left = left.right;
		return left;
		
	}
	
	
	
	/*
	 * 3. Given a bst, update the value of every node 
	 * with sum of value of all nodes greater than and equal to the value of current node.
	 */
	public static void updateTreeWithSum(Node root){
		if(root==null)
			return;		
		int[] sum = new int[1];
		updateTreeWithSumUtil(root, sum);		
		return;
	}
	
	//reverse in-order traversal recursively
	public static void updateTreeWithSumUtil(Node root, int[] sum){
		if(root == null)
			return;				
		updateTreeWithSumUtil(root.right, sum);		
		root.data = root.data + sum[0];		
		sum[0] = root.data;		
		updateTreeWithSumUtil(root.left, sum);		
	}
	//reverse in-order traversal iteratively
	public static void updateTreeWithSumUtil(Node root){
		Stack<Node> s = new Stack<Node>();
		Node current = root;
		int sum = 0;
		
		while(!s.isEmpty() || current!=null){
			if(current!=null){
				s.add(current);
				current = current.right;
			}else{
				Node p = s.pop();
				p.data = p.data + sum;
				sum = p.data;
				current = p.left;
			}
		}
		
		return;
	}
	
	
	/*
	 * convert a binary tree in a doubly link list.
	 */
	public static class  DLinkedList{
		public Node data;
		public DLinkedList pre;
		public DLinkedList next;	
	}
	
	//solution 1 return head and tail, do it recursively
	public static DLinkedList[] toDLL(Node root){
		if(root==null){
			DLinkedList[] list = new DLinkedList[2];
			return list;
		}
				
		DLinkedList[] left = toDLL(root.left);
		DLinkedList[] right = toDLL(root.right);
		
		DLinkedList current = new DLinkedList();
		current.data = root;
		
		if(left[1]!=null)
			left[1].next = current;
		
		current.pre = left[1];
		current.next = right[0];
		
		if(right[0]!=null)
			right[0].pre = current;
		
		DLinkedList[] list = new DLinkedList[2];
		list[0] = left[0]==null?current:left[0];
		list[1] = right[1]==null?current:right[1];
		return list;
	}
	
	public static DLinkedList[] toDLLInorder(Node root){
		Stack<Node> s = new Stack<Node>();
		DLinkedList head = null;
		Node current = root;
		DLinkedList pre = null;
		while(!s.isEmpty() || current!=null){
			if(current!=null){
				s.add(current);
				current = current.left;
			}else{
				Node p = s.pop();
				DLinkedList c = new DLinkedList();
				c.data = p;
				if(head==null){
					head = c;
					pre = c;
				}else{
					pre.next = c;
					c.pre = pre;
					pre = c;
					current = p.right;
				}				
			}				
		}
		DLinkedList[] list = new DLinkedList[2];
		list[0] = head;
		list[1] = pre;
		return list;
	}
	
	/*
	 * Given a binary tree. I need to print the nodes in vertical line zigzag manner.
	 */
	public static void verticalZigZag(Node root){
		Map<Integer, Stack<Node>> map = new HashMap<Integer, Stack<Node>>();
		int[] minL = new int[1];
		int[] maxL = new int[1];
		
		PreOrderTraversal(root, map, 0, minL, maxL);
		
		for(int l=minL[0]; l<=maxL[0]; l++){
			Stack<Node> s = map.get(l);
			while(!s.isEmpty())
				System.out.println(s.pop().data);
		}
		
		for(int l=0; l<=maxL[0]-minL[0]; l++){
			verticalZigZagUtil(root, 0-minL[0], l);			
		}
		
	}
	
	
	public static void verticalZigZagUtil(Node root, int vLevel, int target){
		if(root==null)
			return;
		if(vLevel==target){
			if(target%2==0){
				System.out.println(root.data);
				verticalZigZagUtil(root.left, vLevel-1, target);
				verticalZigZagUtil(root.right, vLevel+1, target);
			}else{
				verticalZigZagUtil(root.left, vLevel-1, target);
				verticalZigZagUtil(root.right, vLevel+1, target);
				System.out.println(root.data);
			}
		}else{
			verticalZigZagUtil(root.left, vLevel-1, target);
			verticalZigZagUtil(root.right, vLevel+1, target);
		}
	}
	
	
	public static void PreOrderTraversal(Node root, Map<Integer, Stack<Node>> map, int vLevel, int[] minL, int[]maxL){
		if(root==null)
			return;
		
		if(map.containsKey(vLevel)){
			map.get(vLevel).add(root);
		}else{
			Stack<Node> s = new Stack<Node>();
			s.add(root);
			map.put(vLevel, s);
		}
		
		minL[0] = minL[0]>vLevel?vLevel:minL[0];
		maxL[0] = maxL[0]<vLevel?vLevel:maxL[0];
		
		PreOrderTraversal(root.left, map, vLevel-1, minL, maxL);
		PreOrderTraversal(root.right, map, vLevel+1, minL, maxL);
		
	}
	
	/*
	 * http://www.geeksforgeeks.org/amazon-interview-set-70-on-campus/
	 */
	public static Node createTree(int[] pre, int[] in){
		return createTreeUtil(pre, 0, pre.length-1, in, 0, in.length-1 );
	}
	
	public static Node createTreeUtil(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd){
		if(preStart==-1 || inStart==-1 || preStart>preEnd || inStart>inEnd)
			return null;
		Node root = new Node(pre[preStart]);
		
		int rootIndex = findIndex(in, inStart, inEnd, pre[preStart]);
		
		if(rootIndex!=-1){
			root.left =  createTreeUtil(pre, preStart+1, preStart+rootIndex-inStart, in, inStart, rootIndex-1);		
			root.right = createTreeUtil(pre, preStart+rootIndex-inStart+1, preEnd, in, rootIndex+1, inEnd);
		}
		return root;
	}
	
	public static int findIndex(int[] list, int start, int end, int k){
		for(int i=start; i<=end; i++){
			if(list[i] == k)
				return i;
		}
		
		return -1;
	}
	
	public static void postOrder(Node root){
		if(root==null)
			return;
		postOrder(root.left);
		postOrder(root.right);
		
		System.out.println(root.data);
	}
	
	/*
	 * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
	 */
	public static void morrisTraveral(Node root){
		Node current = root;
		Node pre = null;
		while(current!=null){			
			if(current.left==null){
				System.out.println(current.data);
				pre = current;
				current = current.right;
			}else{
				
				Node temp = findRightMostNode(current.left);
				
				if(pre!=null && temp==pre){//go up
					pre.right = null;
					System.out.println(current.data);
					current = current.right;//this will either go up to its parent or go down to its child
				}else{//go down to left	
					temp.right = current;
					current = current.left;
				}
			}
			
		}
	}
	
	private static Node findRightMostNode(Node left) {
		while(left.right!=null)
			left = left.right;
		return left;
		
	}

	/*
	 * http://www.geeksforgeeks.org/amazon-interview-set-125-on-campus-for-internship/
	 * Q3 – Reverse level order traversal.
	 */
	public static void reverseLevelTraversal(Deque<Node> queue){
		if(queue.isEmpty())
			return;
		Deque<Node> nextQueue = new ArrayDeque<Node>();
		StringBuffer sb = new StringBuffer();
		while(!queue.isEmpty()){
			Node n = queue.remove();
			sb.append(n.data + " ");
			if(n.right!=null)
				nextQueue.add(n.right);
			if(n.left!=null)
				nextQueue.add(n.left);
		}
		
		reverseLevelTraversal(nextQueue);		
		System.out.println(sb.toString());		
	}
	
	/*
	 * http://www.geeksforgeeks.org/amazon-interview-set-125-on-campus-for-internship/
	 */
	public static void rotateAlternate(Node root, int level){
		if(root==null)
			return;
		rotateAlternate(root.left, level+1);
		rotateAlternate(root.right, level+1);
		
		if(level%2==0){
			Node temp = root.left;
			root.left = root.right;
			root.right = temp;
		}
	}
	
	public static void rotate(Node root){
		if(root==null)
			return;
		rotate(root.left);
		rotate(root.right);
		
		Node temp = root.left;
		root.left = root.right;
		root.right = temp;
	}
		
	public static void rotateIterative(Node root){
		Deque<Node> q = new ArrayDeque<Node>();
		q.add(root);
		
		while(!q.isEmpty()){
			Node current = q.remove();
			
			if(current.left!=null)
				q.add(current.left);
			if(current.right!=null)
				q.add(current.right);
			
			Node temp = current.left;
			current.left = current.right;
			current.right = temp;			
		}		
	}
	
	/*
	 * http://www.geeksforgeeks.org/level-order-tree-traversal/
	 */
	public static void levelOrderBTreeTraversal(Node root){
		Deque<Node> q = new ArrayDeque<Node>();
		q.add(root);

		while(!q.isEmpty()){
			Node current = q.remove();
			System.out.println(current.data);
			if(current.left!=null)
				q.add(current.left);
			if(current.right!=null)
				q.add(current.right);
		}			

	}

	
	/*
	 * http://www.geeksforgeeks.org/connect-nodes-at-same-level/
	 */
	public static void connectBTreeBy1Q(Node root){
		Deque<Node> q = new ArrayDeque<Node>();
		root.level = 0;
		q.add(root);
		Node previous = null;
		while(!q.isEmpty()){
			Node current = q.remove();		
			if(previous==null)
				previous = current;
			else{
				if(previous.level == current.level)
					previous.next = current;
				previous = current;									
			}			
			if(current.left!=null){
				current.left.level = current.level+1;
				q.add(current.left);
			}
			if(current.right!=null){
				current.right.level = current.level+1;
				q.add(current.right);
			}
		}	
		
	}
	public static void connectBTreeBy2Q(Node root){
		Deque<Node> q = new ArrayDeque<Node>();
		q.add(root);
		while(!q.isEmpty()){
			Deque<Node> temp = new ArrayDeque<Node>();
			Node previous = null;
			while(!q.isEmpty()){
				Node current = q.remove();
				if(previous==null)
					previous = current;
				else{
					previous.next = current;
					previous = current;
				}
				if(current.left!=null)
					temp.add(current.left);
				if(current.right!=null)
					temp.add(current.right);
			}			
			q = temp;
		}
	}
	
	/*
	 * http://www.geeksforgeeks.org/connect-nodes-at-same-level/
	 */
	public static void connectCompleteTree(Node root){
		if(root==null)
			return;
		
		connectCompleteTreeHelper(root.left, root);
		connectCompleteTreeHelper(root.right, root);
	}
	
	/*
	 * http://www.geeksforgeeks.org/connect-nodes-at-same-level/
	 */
	public static void connectCompleteTreeHelper(Node root, Node parent){
		if(root==null)
			return;
		
		if(parent.left == root){
			root.next = parent.right;
		}else{
			root.next = parent.next.left;
		}
		
		connectCompleteTreeHelper(root.left, root);
		connectCompleteTreeHelper(root.right, root);
	}
	
	public static int findKthMin(Node root, int k){
		Stack<Node> s = new Stack<Node>();
		Node current = root;
		Node previous = null;
		int i = 0;
		int result = Integer.MIN_VALUE;
		while(!s.isEmpty() || current!=null){
			if(current!=null){
				s.add(current);
				current = current.left;
			}else{
				Node n = s.pop();
				i++;
				if(i==k){
					result =  n.data;
					break;
				}
				current = n.right;				 
			}
		}		
		return result;
	}
	
	public static int findKthMax(Node root, int k){
		Stack<Node> s = new Stack<Node>();
		Node current = root;
		Node previous = null;
		int i = 0;
		int result = Integer.MIN_VALUE;
		while(!s.isEmpty() || current!=null){
			if(current!=null){
				s.add(current);
				current = current.right;
			}else{
				Node n = s.pop();
				i++;
				if(i==k){
					result =  n.data;
					break;
				}
				current = n.left;				 
			}
		}		
		return result;
	}
	
	/*
	 * http://www.geeksforgeeks.org/amazon-interview-set-122-campus-internship/#comment-1564648138
	 * F2F Q1
	 */
	public static int findNumberOfIteration(Node root){
		Stack<Node> s = new Stack<Node>();

		root.visited = true;
		s.add(root);	
		int i = 0;

		while(!s.isEmpty()){
			Stack<Node> temp = new Stack<Node>();
			i++;
			while(!s.isEmpty()){				
				Node n = s.pop();
				for(Node c : n.children){
					if(!c.visited){
						c.visited = true;
						temp.add(c);
						temp.add(n);
						break;
					}
				}		
			}		
			s = temp;
		}
		
		return i;
	}
	
	public static boolean isSymmertrc(Node root1, Node root2){
		if(root1==null || root2==null )
			return root1==null && root2==null;
		
		return isSymmertrc(root1.left, root2.right) && isSymmertrc(root1.right, root2.left);
				
	}
	
	public static boolean hDistance(Node root, int target, int d, int[] result){
		if(root==null)
			return false;
		
		if(root.data==target){
			result[0] = d;
			return true;
		}
		 
		return hDistance(root.left, target, d-1, result) || hDistance(root.right, target, d+1, result);
		
	}
	
	
	/**
	 * Given a Binary Tree where every node has following structure. struct node { int key; struct node *left,*right,*random; }
	 * The random pointer points to any random node of 
	 * the binary tree and can even point to NULL, clone the given binary tree.
	 * @param root
	 */
	public static void travelTree2(Node root){
		if(root==null)
			return;
		
		Node clone = root.next;
		Node rootNext = root.next.next;
		Node cloneNext = clone.next.next;
		
		travelTree2(root.left);
		travelTree2(root.right);
		
		root.next = rootNext;
		clone.next = cloneNext;
	}
	
	public static void cloneTreeWithRandomPointer(Node root){
		Map<Node, Node> toClone = new HashMap<Node, Node>();
		Node clone = cloneTreeInit(root, toClone);
		
		travelTree(root, toClone);
	}
	
	public static void travelTree(Node root, Map<Node, Node> toClone){
		if(root==null)
			return;
		
		Node clone = root.next;
		
		root.next = root.next.next;
		clone.next = toClone.get(root.next);
		
		travelTree(root.left, toClone);
		travelTree(root.right, toClone);
	}
	/*
	 * clone the tree from top to down
	 * then store the mapping from original node to clone
	 */
	public static Node cloneTreeInit(Node root, Map<Node, Node> toClone){
		if(root==null)
			return null;
		
		Node clone = new Node();
		clone.data = root.data;
		
		clone.next = root.next;
		root.next = clone;
		
		toClone.put(root, clone);
		cloneTree(root.left, clone, true, toClone);
		cloneTree(root.right, clone, false, toClone);
		return clone;
	}
	
	public static void cloneTree(Node root, Node parent, boolean isLeft, Map<Node, Node> toClone){
		if(root==null)
			return;
		else{
			Node clone = new Node();
			clone.data = root.data;
			
			clone.next = root.next;
			root.next = clone;
			
			toClone.put(root, clone);
			if(isLeft)
				parent.left = clone;
			else
				parent.right=clone;
			cloneTree(root.left, clone, true, toClone);
			cloneTree(root.right, clone, false, toClone);
		}
	}
	
	/*
	 * http://www.geeksforgeeks.org/vertical-sum-in-a-given-binary-tree/
	 */
	public static void printVerticalSum(Node root){
		int[] min = new int[0];
		int[] max = new int[0];
		findVLevelMinMax(root, min, max, 0);
		//index + min[0] -> vlevel 
		int[] sum = new int[max[0]-min[0]+1];
		printVerticalSumHelper(root, sum, 0, min);
		for(int i : sum){
			System.out.println(i);
		}
	}
	
	private static void printVerticalSumHelper(Node root, int[] sum, int vlevel, int min[]){
		if(root==null)
			return;
		sum[vlevel-min[0]] = sum[vlevel-min[0]] + root.data;
		printVerticalSumHelper(root.left, sum, vlevel-1, min);
		printVerticalSumHelper(root.right, sum, vlevel+1, min);
	}
	
	/*
	 * 
	 */
	public static void printNode(Node root){
		int height = 0;
		int width = 0;
		boolean left = true;
		
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				printNodeHelper(root, 0, 0, i, j, left);
				left = !left;
			}
		}
	}
	/**
	 * 
	 * @param root
	 * @param hlevel
	 * @param vlevel
	 * @param left
	 */
	public static void printNodeHelper(Node root, int currhlevel, int currvlevel, int hlevel, int vlevel, boolean left){
		if(root==null)
			return;
		if(currhlevel == hlevel && currvlevel == vlevel){
			
		}else{
			printNodeHelper(root.left, currhlevel++, currvlevel--, hlevel, vlevel, left);
			printNodeHelper(root.right, currhlevel++, currvlevel++, hlevel, vlevel, left);
		}
		
	}
	
	/*
	 * Print a Binary Tree in Vertical Order 
	 */
	public static void printBTVertically(Node root){
		
		int[] min = new int[0];
		int[] max = new int[0];
		findVLevelMinMax(root, min, max, 0);
		
		for(int i = min[0]; i<=max[0]; i++){
			printVLevel(root, 0, i);
		}
		
	}
	

	/*
	 * Print a Binary Tree in Vertical Order 
	 */
	public static void printBTVerticallyOnEachLevel(Node root, int level){
		
		int[] min = new int[0];
		int[] max = new int[0];
		findVLevelMinMax(root, min, max, 0);
		
		int start = min[0]; 
		int end = max[0];
		boolean left = true;
		while(start < end ){
			if(left){
				//left, level, start, hash[start]
				
				//if the beginning or hash[start]<2
				//start = start + 2;				
			}else{
				//left, level, end, hash[end]
				
				//if the end or hash[end]<2
				//end = end - 2;
			}
			left = !left;
			
		}
		
	}
	
	public static void findVLevelMinMax(Node root, int[] min, int[] max, int level){
		if(root==null)
			return;
		if(level>max[0])
			max[0] = level;
		if(level<min[0])
			min[0] = level;
		
		findVLevelMinMax(root.left, min, max, level-1);
		findVLevelMinMax(root.right, min, max, level+1);
	}
	
	public static void printVLevel(Node root, int current, int level){
		if(root==null)
			return;
		if(current==level){
			System.out.println(root.data);	
		}
		printVLevel(root.left, current-1, level);
		printVLevel(root.right, current+1, level);
	}
	
	/*
	 * Then there were 3-4 coding questions. She just discussed approach.
-Update all nodes in a bst to be sum of all elements greater than or equal to it.
- Stock problem/ Given an array ‘arr’ find maximum difference between two elements (max(arr[i]-arr[j]) where i>=j).
	 */
	/*
	 * print pair of number with sum in BST
	 */
	public static void printPairSum2(Node root, int sum){
		if(root==null)
			return;
		BSTSearch(root, sum - root.data);
		
		printPairSum2(root.left, sum);
		printPairSum2(root.right, sum);
		
	}
	
	public static void BSTSearch(Node root, int k){
		
	}
	
	public static void printPairSum(Node root, int sum){
		if(root==null)
			return;
		
		Stack<Node> left = new Stack<Node>();
		Node leftP = root;
		while(leftP!=null){
			left.add(leftP);
			leftP = leftP.left;
		}
		
		Stack<Node> right = new Stack<Node>();
		Node rightP = root;
		while(rightP!=null){
			right.add(rightP);
			rightP = rightP.right;
		}
		boolean shiftRight = true;
		boolean shiftLeft = true;
		while((leftP==null && rightP==null) || (leftP!=null && rightP!=null && leftP.data <= rightP.data)){
			if(leftP!=null && shiftRight){
				left.add(leftP);
				leftP = leftP.left;
			}else if(rightP!=null && shiftLeft){
				right.add(rightP);
				rightP = rightP.right;
			}else{
				leftP = left.pop();
				rightP = right.pop();
				
				if(leftP.data + rightP.data==sum){
					System.out.println(leftP.data + "" + rightP.data);
					leftP = leftP.right;	
					rightP = rightP.left;
				}else if(leftP.data + rightP.data>sum){
					leftP = leftP.right;	
					shiftLeft = false;
					shiftRight = true;
				}else{
					rightP = rightP.left;
					shiftRight = false;
					shiftLeft = true;
				}
			}
		}
	}
	
	/**
	 * connect to next node
	 * @param root
	 */
	public static void connectNext(Node current, Node parent){
		if(current!=parent.right)
			current.next = parent.right;
		else
			current.next = parent.next.left;		
		connectNext(current.left, current);
		connectNext(current.right, current);	
	}
	
	public static void printLevel(Node current){
		if(current!=null){
			printLevel(current.left);
			//doing actual printing
			while(current!=null){
				current = current.next;
			}
		}
	}
	
	public static int level(Node root, int n1){
		if(root==null)
			return 0;
		if(root.data== n1)
			return 1;
		else{
			int level = level(root.left, n1);
			if(level>0)
				return level+1;
			
			level = level(root.right, n1);
			if(level>0)
				return level+1;
			
		}
		
		return 0;
	}
	
	public static boolean isCousin(Node root, int n1, int n2){
		Stack<Node> current = new Stack<Node>();
		current.add(root);
		
		Node parent1 = null;
		Node parent2 = null;
		
		int level = 0;		
		while(!current.isEmpty()){
			Stack<Node> next = new Stack<Node>();		

			while(!current.isEmpty()){
				Node node = current.pop();
				if(node.left!=null){
					next.add(node.left);
					if(node.left.data == n1)
						parent1 = node;
					
					if(node.left.data == n2)
						parent2 = node;
				}
				if(node.right!=null){
					next.add(node.right);
					if(node.right.data==n2)
						parent2 = node;
					if(node.right.data==n1)
						parent1 = node;
				
				}				
			}

			if(parent1!=null && parent2!=null && parent1!=parent2)
				return true;
			
			if(parent1!=null && parent2!=null && parent1==parent2)
				return false;
			
			if(parent1!=null || parent2!=null)
				return false;
			  
			current = next;
			level++;

		}		
		
		return false;
	}
	
	public static void printBTLevelOrderTraversalBottomUp(Node root){
		Stack<Node> preLevel = new Stack<Node>();
		preLevel.add(root);
		PrintBTLevelOrderTraversalBottomUp(preLevel);
	}
	
	public static void PrintBTLevelOrderTraversalBottomUp(Stack<Node> preLevel){
		if(preLevel.isEmpty())
			return;
		Stack<Node> currentLevel = new Stack<Node>();
		for(Node n : preLevel){
			if(n.right!=null)
				currentLevel.add(n.right);
			if(n.left!=null)
				currentLevel.add(n.left);
		}	
		PrintBTLevelOrderTraversalBottomUp(currentLevel);
		
		System.out.println("=========");
		for(Node n : preLevel){
			System.out.println(n.data);
		}
	}

	public static void FlattenABinaryTreetoLinkedListPostOrder(Node root){
		Stack<Node> s = new Stack<Node>();
		Node c = root;
		Node pre = null;
		while(!s.isEmpty()||c!=null){
			if(c!=null){
				s.push(c);
				//if(c.right!=null)
				//	s.push(c.right);
				c = c.left;			
			}else{

				Node n = s.peek();
				if(n.right!=null && n.right!=pre){//n.right == pre mean it goes up from right
					c = n.right;
				}else{
					n = s.pop();
					System.out.println(n.data);
					pre = n;
					c = null;
				}
			}


		}
	}
	
	public static void FlattenABinaryTreetoLinkedListInOrder(Node root){
		Stack<Node> s = new Stack<Node>();
		Node c = root;
		Node pre = null;
		while(!s.isEmpty()||c!=null){
			if(c!=null){
				s.push(c);		
				c = c.left;			
			}
			else{				
				Node n = s.pop();
				if(pre!=null){
					pre.right = n;
					pre.left = null;
				}
				pre = n;
				c = n.right;
			}			
		}	
	}
	
	public static void FlattenABinaryTreetoLinkedListPreOrder(Node root){
		Stack<Node> s = new Stack<Node>();
		Node c = root;
		while(!s.isEmpty()||c!=null){
			if(c.left!=null){
				if(c.right!=null)
					s.push(c.right);		
				c.right = c.left;
				c.left = null;
				c = c.right;				
			}
			else{				
				Node n = s.pop();
				c.right = n;
				c = n;
			}			
		}	
	}
	
	public static void treeToLinkedListInOrder(Node root, Node head, Node tail){
		if(root==null)
			return;
		
		treeToLinkedListInOrder(root.left, head, tail);
		
		if(head==null)
			head = root;
		if(tail==null){
			tail=head;
		}else{
			tail.next = root;
			tail = root;
		}
		
		treeToLinkedListInOrder(root.right, head, tail);
		
	}
	
	public static void buildBSTFromInOrder2(ObjectInputStream is, Node n, int currentV,  int min, int max) throws ClassNotFoundException, IOException{
		
		if(currentV>min && currentV<max){
			n = new Node(currentV);
			Object o = is.readObject();
			if(is!=null){
				buildBSTFromInOrder2(is, n.left,  ((Node)o).data,  min, n.data);						
				buildBSTFromInOrder2(is, n.right, ((Node)o).data, n.data, max);
			}
		}		
	}
	
	public static Node buildBSTFromInOrder(int[] nums, int[] ind,  int min, int max){
		Node n = null;
		int start = ind[0];
		if(start>=nums.length)
			return null;
		
		if(nums[start]>min && nums[start]<max){
			n = new Node(nums[start]);
			ind[0] ++ ;
			n.left = buildBSTFromInOrder(nums, ind, min, n.data);						
			n.right = buildBSTFromInOrder(nums, ind, n.data, max);
			return n;
		}		
		return null;
	}
	
	/*
	 * serialize and deserialize tree using nick format
	 */
	public static void printNickFormat(Node root){
		if(root==null)
			return;
		StringBuilder sb = new StringBuilder();
		/*sb.append(root.data);
		sb.append("(");
		printNickFormat(root.left, sb);
		printNickFormat(root.right, sb);
		sb.append(")");*/
		printNickFormat(root, sb);
		System.out.println(sb.toString());
	}
	
	public static void printNickFormat(Node root, StringBuilder sb){
		if(root==null){
			sb.append("#");
			return;
		}
		
		sb.append(root.data);
		sb.append("(");
		printNickFormat(root.left, sb);
		printNickFormat(root.right, sb);
		sb.append(")");
	}
	
	/*
	 * should use StringReader
	 */
	public static Node nickFormatToTree(String nick){
		StringTokenizer st = new StringTokenizer(nick);
		
		return nickFormatToTree(st);
	}
	
	public static Node nickFormatToTree(StringTokenizer st){
		if(st.hasMoreTokens()){
			String input = st.nextToken();
			if(input.equals("#"))
				return null;
			else{
				Node t = new Node();
				t.data = Integer.valueOf(input);
				st.nextToken();//eat (
				t.left = nickFormatToTree(st);
				t.right = nickFormatToTree(st);
				st.nextToken();//eat )
				return t;
			}			
		}else
			return null;
	}
	
	
	
	public static void serilization(Node root, ObjectOutputStream os) throws IOException{
		if(root==null){			
			Node nullObj = new Node();
			os.writeObject(nullObj);
		}
		else{
			os.writeObject(root);
			serilization(root.left, os);
			serilization(root.right, os);
		}					
	}
	
	public static void deserilization(Node root, boolean isLeft, ObjectInputStream is) throws IOException, ClassNotFoundException{
		if(is.available()>0){		
		Node n = (Node) is.readObject();	
		if(n.v=='\0'){//is leaf object, return			
			return;
		}
		else{
			if(isLeft)
				root.left = n;
			else
				root.right = n;
			deserilization(n, true, is);
			deserilization(n, false, is);
		}					
		}
	}
	
	public static boolean isTreeEqualRecursively(Node r1, Node r2){
		if(r1==null && r2==null)
			return true;
		else if (r1==null || r2 == null)
			return false;
		else{
			return r1.data == r2.data && isTreeEqualRecursively(r1.left, r2.left) && isTreeEqualRecursively(r1.right, r2.right);
		}
	}
	
	
	public static boolean isTreeEqualIteratively(Node r1, Node r2){
		Stack<Node> s1 = new Stack<Node>();
		if(r1!=null)
			s1.add(r1);
		
		Stack<Node> s2 = new Stack<Node>();
		if(r2!=null)
			s2.add(r2);
		
		while(!s1.isEmpty() && !s2.isEmpty()){
			Node n1 = s1.pop();
			Node n2 = s2.pop();
			
			if(n1.data!=n2.data)
				return false;
			
			if(n1.left!=null && n2.left!=null){	
					s1.push(n1.left);
					s2.push(n2.left);				
			}		
			if(n1.left==null || n2.left==null){
				return false;
			}				
			if(n1.right!=null && n2.right!=null){
					s1.push(n1.right);
					s2.push(n2.right);
			}			
			if(n1.right==null || n2.right==null){
				return false;
			}
		}
		
		if(s1.isEmpty() || s2.isEmpty())
			return false;
		
		return true;
		
	}
	
	
	public static int Diameter = Integer.MIN_VALUE;
	
	public static int findDiameter(Node root){
		if(root==null)
			return 0;
		
		int LMax = findDiameter(root.left);
		int RMax = findDiameter(root.right);
		
		MaxSum = Math.max(Diameter, LMax + RMax + 1);
		
		return Math.max(LMax, RMax) + 1;
	}
	
	public static int MaxSum = Integer.MIN_VALUE;
	
	public static int findMaxSum(Node root){
		if(root==null)
			return 0;
		
		int LMax = findMaxSum(root.left);
		int RMax = findMaxSum(root.right);
		
		MaxSum = Math.max(MaxSum, Math.max(Math.max(LMax, RMax), LMax + RMax + root.data));
		
		return Math.max(LMax, RMax) + root.data;
	}
	
	/*
	 * http://www.geeksforgeeks.org/connect-nodes-at-same-level/
	 * 
	 * only works for complete tree
	 */
	public static void connectNodesAtSameLevel(Node root){
		if(root==null)
			return;
		
		if(root.left!=null)
			root.left.next = root.right;
		if(root.right!=null && root.next!=null)
			root.right.next = root.next.left;
		
		connectNodesAtSameLevel(root.left);
		connectNodesAtSameLevel(root.right);
		
	}
	
	//for any tree
	public static void connectNodesAtSameLevel2(Node root){
		Deque<Node> q = new ArrayDeque<Node>();
		q.add(root);
		
		while(!q.isEmpty()){
			Deque<Node> t = new ArrayDeque<Node>();
			Node pre = null;
			while(!q.isEmpty()){
				Node n = q.remove();
				if(pre!=null)
					pre.next = n;
				else
					pre = n;
				
				if(n.left!=null)
					t.add(n.left);
				if(n.right!=null)
					t.add(n.right);
				
			}
			
			q = t;
		}
		
	}
	
	public static void connectNodesAtSameLevel3(Node root){
		Deque<Node> q = new ArrayDeque<Node>();
		q.add(root);

		Node pre = null;
		while(!q.isEmpty()){
			Node n = q.remove();
			if(pre!=null)
				pre.next = n;
			else
				pre = n;

			if(n.left!=null)
				q.add(n.left);
			if(n.right!=null)
				q.add(n.right);

			if(n.next==null)//reach the end of level
				pre = null;

		}	
	}
	
	
	public static void populateNextRight(Node root){
		root.next = null;		
		Node leftMost = root;		
		
		while(leftMost!=null){
			Node current = leftMost;
			while(current!=null){
				if(null!=current.left)
					current.left.next = current.right;
				
				if(null!=current.right)
					current.right.next = (current.next== null? null : current.next.left);
				
				current = current.next;
			}						
			leftMost = leftMost.left;
		}				
	}
	
	/*
	 * only works for complete tree
	 */
	public static void populateNextRight2(Node root){	
		if(root==null)
			return;
		
		Node current = root;

		if(null!=current.left)
			current.left.next = current.right;

		if(null!=current.right)
			current.right.next = (current.next== null? null : current.next.left);

		populateNextRight2(root.left);
		populateNextRight2(root.right);

	}
	
	public static Node linkedListToTree(SortedListNode root, int start, int end){
		if(start>end)
			return null;
		
		int mid = (start + end)/2;
		
		Node left = linkedListToTree(root, start, mid - 1);
		
		Node n = new Node(root.v);
		
		n.left = left;
		
		root = root.next;
		
		Node right = linkedListToTree(root, mid + 1, end);
		
		n.right = right;
		
		return n;
		
	}
	
	public static void printVerticalZigZag(Node root, int height){
		int lvl = 0;
		
		Object[] nums = new Object[2*height+1];
		for(int i = 0; i<nums.length; i++){
			nums[i] = new ArrayList<Node>();
		}
		
		ArrayList<Node> level = new ArrayList<Node>();
		level.add(root);
		
		ArrayList<Integer> level_index = new ArrayList<Integer>();
		level_index.add(height);
		
		((ArrayList<Node>) nums[height]).add(root);
		
		while(!level.isEmpty()){
			lvl ++;
			ArrayList<Node> temp_level = new ArrayList<Node>();
			ArrayList<Integer> temp_index = new ArrayList<Integer>();
			for(int i = 0; i<level.size(); i++){
				Node n = level.get(i);
				int index = level_index.get(i);		
				if(n.left!=null){
					temp_level.add(n.left);
					temp_index.add(index-1);
					((ArrayList<Node>) nums[index-1]).add(n.left);
				}
				
				if(n.right!=null){
					temp_level.add(n.right);
					temp_index.add(index+1);
					((ArrayList<Node>) nums[index+1]).add(n.right);
				}
			}			
			level = temp_level;
			level_index = temp_index;		
		}
		
		for(int j=0; j<2*height+1; j++){		
			System.out.println((ArrayList<Node>) nums[j]);
		}
		
	}
	
	
	public static void printVerticalSums(Node root, int height){
		int lvl = 0;
		
		int[][] nums = new int[height+1][2*height+1];
		
		ArrayList<Node> level = new ArrayList<Node>();
		level.add(root);
		
		ArrayList<Integer> level_index = new ArrayList<Integer>();
		level_index.add(height);
		
		nums[0][height] = root.data;
		
		while(!level.isEmpty()){
			lvl ++;
			ArrayList<Node> temp_level = new ArrayList<Node>();
			ArrayList<Integer> temp_index = new ArrayList<Integer>();
			for(int i = 0; i<level.size(); i++){
				Node n = level.get(i);
				int index = level_index.get(i);		
				if(n.left!=null){
					temp_level.add(n.left);
					temp_index.add(index-1);					
					nums[lvl][index-1] += n.left.data;
				}
				
				if(n.right!=null){
					temp_level.add(n.right);
					temp_index.add(index+1);
					nums[lvl][index+1] += n.right.data;
				}
			}			
			level = temp_level;
			level_index = temp_index;		
		}
		
		for(int j=0; j<2*height+1; j++){
			int sum = 0;
			for(int i=0; i<height+1; i++)
				sum += nums[i][j];
			
			System.out.println(sum);
		}
		
	}
	
	
	public static void printPathSum(Node root, int sum, List<Node> list){
		if(root==null)
			return;
		
		if(root.data == sum){
			System.out.println("find a path");
			for(Node n : list)
				System.out.println(n.data);
			System.out.println(root.data);
		}
		
		list.add(root);
		List<Node> copy = new ArrayList<Node>(list);
		
		
		List<Node> copy2 = new ArrayList<Node>(list);
		

		printPathSum(root.left,  sum - root.data, copy);  
		printPathSum(root.right, sum - root.data, copy2);  
			
	}
	
	public static void outputTreeString(Node root, StringBuilder sb){
		if(root==null)
			return;
		sb.append("(");		
		outputTreeString(root.left, sb);		
		sb.append(root.data);		
		outputTreeString(root.right, sb);
		sb.append(")");		
	}
	
	public static void mirrorTreeInPlace(Node root){
		if(root==null)
			return;		
		mirrorTreeInPlace(root.left);
		mirrorTreeInPlace(root.right);		
		Node temp = root.left;		
		root.left = root.right;
		root.right = temp;		
	}
	
	public static void mirrorTreeInPlace2(Node root){
		if(root==null)
			return;		
		else{
			Node temp = root.left;		
			root.left = root.right;
			root.right = temp;	
			mirrorTreeInPlace(root.left);
			mirrorTreeInPlace(root.right);		
		}
		
	}
	
	public static void printLeftAndRighMost(Node root){
		Queue<Node> queue = new ArrayDeque<Node>();
		queue.add(root);
		
		while(!queue.isEmpty()){			
			Queue<Node> queueTemp = new ArrayDeque<Node>();
			Node n = null;
			int index = 0;
			while(queue.isEmpty()){
				 n = queue.remove();
				 if(index == 0)
					 System.out.println("left most: " + n.data);
				 if(n.left!=null)
					 queueTemp.add(n.left);
				 if(n.right!=null)
					 queueTemp.add(n.right);
				 index++;
			}			
			if(n!=null)
				System.out.println("right most: " + n.data);
			queue = queueTemp;
		}
	}
	
	public static boolean pruneTree(Node root, int k){
		if(k>1 && root.left==null && root.right ==null){
			System.out.println(root.data);
			root = null;
			return true;
		}		
		boolean lflag= false;
		boolean rflag=false;
		if(root.left!=null)
			lflag = pruneTree(root.left, k-1);
		
		if(root.right!=null)
			rflag = pruneTree(root.right, k-1);		
		System.out.println(root.data + " - " + lflag + rflag);
		if(lflag)
			root.left = null;
		if(rflag)
			root.right = null;
		return lflag && rflag;
	}
	
	/*
	 * make sure the depth is passed down recursively
	 */
	public static int findMinDepth(Node root, int depth){
		assert(root!=null);
		
		if(root==null)
			return Integer.MAX_VALUE;
		
		if(root.left == null  && root.right == null)
			return depth;
		
		int left = findMinDepth(root.left, depth+1);
		int right = findMinDepth(root.right, depth+1);

		return Math.min(left, right);
	}
	
	/*
	 * bottom-up approach
	 */
	public static int findMinDepthRec(Node root){
		assert(root!=null);
		
		if(root==null)
			return 0;
		
		int left = findMinDepthRec(root.left);
		int right = findMinDepthRec(root.right);

		return Math.min(left, right)+1;
	}
	
	/*
	 * iteratively 
	 */
	public static int findMinDepth2(Node root){
		assert(root!=null);
		
		Deque<Node> q = new ArrayDeque<Node>();
		q.add(root);
		int level = 0;
				
		while(!q.isEmpty()){
			Deque<Node> t = new ArrayDeque<Node>();
			level++;
			while(!q.isEmpty()){			
				Node n = q.remove();							
				if(n.left==null && n.right==null)
					return level;

				if(n.left!=null){
					t.add(n.left);
				}

				if(n.right!=null){
					t.add(n.right);
				}
			}			
			q = t;			
		}			
		
		return level;
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
	
	public static boolean isBST3(Node root, int min, int max){
		if(root==null)
			return true;
		
		return 
				isBST3(root.left, min, root.data) &&
				 (root.data > min && root.data < max) &&
				 isBST3(root.right, root.data, max);				 						
	}
	
	public static boolean isBST4(Node root){
		if(root==null)
			return false;
		Node pre = null;
		Node curr = root;
		Stack<Node> s = new Stack<Node>();		
		while(s.isEmpty() || curr!=null){
			if(curr!=null){
				s.add(curr);
				curr = curr.left;
			}else{
				curr = s.pop();
				if(pre!=null && pre.data>curr.data)
					return false;				
				pre = curr;
				curr = curr.right;				
			}		
		}
		return true;				 						
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
	
	public static Node isBSTAndTreeToDLL(Node root){
		if(root==null)
			return null;		
		Stack<Node> s = new Stack<Node>();		
		Node head = null;
		Node n = root;
		Node pre = null;
		while(!s.isEmpty() || n!=null){					
			if(n!=null){
				s.push(n);
				n = n.left;
			}
			else{
				Node p = s.pop();				
				//this is the left most node
				if(head == null)
					head = p;				
				if(pre!=null){
					pre.right = p;
					p.left = pre;
				}				
				pre=p;				
				n = p.right;
			}							
		}		
		//handle the last node
		pre.right = head;
		head.left = pre;
		return head;
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
	
	public static boolean equal(Node root1, Node root2){
		Stack<Node> s1 = new Stack<Node>();
		Node curr1 = root1;
		
		Stack<Node> s2 = new Stack<Node>();
		Node curr2 = root2;
		
		Node pre = null;
		
		while((!s1.isEmpty() || curr1!=null) && (!s2.isEmpty() || curr2!=null)){
			if(curr1!=null ){
				s1.push(curr1);
				curr1 = curr1.left;
			}else if(curr2!=null){
				s2.push(curr2);
				curr2 = curr2.left;
			}else{
				curr1 = s1.pop();
				curr2 = s2.pop();
				if(curr1.data != curr2.data)
					return false;
				
				curr1 = curr1.right;
				curr2 = curr2.right;
			}		
		}
		
		if(s1.isEmpty() && s2.isEmpty() && curr1==null && curr2==null)
			return true;
		
		if(curr1==null || curr2==null)
			return false;
		
		if(s1.isEmpty() || s2.isEmpty())
			return false;
		
		return true;
		
	}
	
	public static void zigZagBottomUp(Stack<Node> s){
		if(s.isEmpty())
			return;
		Stack<Node> tem = new Stack<Node>();
		Stack<Node> copy = new Stack<Node>();
		while(!s.isEmpty()){
			Node curr = s.pop();
			copy.push(curr);
			if(curr.left!=null)
				tem.push(curr.left);
			if(curr.right!=null)
				tem.push(curr.right);	
		}
		zigZagBottomUp(tem);
		while(copy.isEmpty())
			System.out.println(copy.pop());
		
	}
	
	//add next pointer
	public static void zigZag2(Node root){
		Stack<Node> s = new Stack<Node>();
		s.add(root);
		int level = 0;
		Node pre = null;
		while(!s.isEmpty()){
			Stack<Node> t = new Stack<Node>();
			while(!s.isEmpty()){
				Node curr = s.pop();
				if(pre==null)
					pre = curr;
				else{
					pre.next = curr;
					pre = curr;
				}
				if(level%2==0){
					if(curr.left!=null)
						t.add(curr.left);
					if(curr.right!=null)
						t.add(curr.right);					
				}else{
					if(curr.right!=null)
						t.add(curr.right);
					if(curr.left!=null)
						t.add(curr.left);
				}			
			}			
			s = t;
			level++;
			pre = null;
		}
	}
	
	
	public static void zigZag(Node root){
		Stack<Node> current = new Stack<Node>();
		current.add(root);
		
		int level = 0;		
		while(!current.isEmpty()){
			Stack<Node> next = new Stack<Node>();		
			
			while(!current.isEmpty()){
				Node node = current.pop();
				System.out.print(node.data+" ");
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
			
			current = next;
			level++;
			
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
	
	public static boolean isSubTree2(Node t1, Node t2){
		if(t1 == null)
			return false;
		if(t2 == null)
			return true;
		
		return areIdentical(t1, t2)  || isSubTree2(t1.left, t2) || isSubTree2(t1.right, t2);
		
	}
	
	
	public static boolean areIdentical(Node t1, Node t2){
		if(t2 == null && t2 == null)
			return true;
		if(t1 == null || t2 ==null)
			return false;
		
		return (t1.data == t2.data) && areIdentical(t1.left, t2.left) && areIdentical(t1.right, t2.right);
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
	
	
	public static int maxSumFromRoot(Node root){		
		return maxSumFromRootHelper(root, 0);
	}
	
	public static int maxSumFromRootHelper(Node root, int sum){
		if(root == null)
			return sum;
		else{
			return Math.max(maxSumFromRootHelper(root.left, root.data + sum),
				   maxSumFromRootHelper(root.right, root.data + sum));
		}
	}
	
	static int max_sum = 0;
	static Node maxNode = null;
	public static void findLeaveWithMaxSumFromRoot(Node root, int sum){
		if(root == null)
			return;
		
		sum = sum + root.data;
		if(root.left == null && root.right ==null){
			if(sum > max_sum){
				max_sum = sum;
				maxNode = root;
			}
		}
		else{
			findLeaveWithMaxSumFromRoot(root.left, sum);
			findLeaveWithMaxSumFromRoot(root.right, sum);
		}
	}
	
	
	public static Node constructTree(String pre, String in){
		System.out.println(pre + "-" + in);
		if(pre.isEmpty())
			return null;
		if(pre.length() == 1)
			return new Node(pre.charAt(0));
		
		Node root = new Node(pre.charAt(0));
		String[] ins = splitStringBy(in, pre.charAt(0));
		root.left = constructTree(pre.substring(1, ins[0].length()+1), ins[0]);
		root.right = constructTree(pre.substring(ins[0].length()+1), ins[1]);
		return root;
	}
	
	public static String[] splitStringBy(String str, char root){		
		String[] re = new String[2];
		re[0] = str.substring(0, str.indexOf(root));
		re[1] = str.substring(str.indexOf(root)+1);
		return re;
	}

	
	public class KaryNode{
		Object value;
		KaryNode[] children;
		public KaryNode(Object v){
			this.value = v;
			children = new KaryNode[k];
		}
		public void addChild(KaryNode n) {
			for(int i=0;i<children.length;i++){
				if(children[i]!=null)
					children[i] = n;
			}
			
		}
	}
	
	private int k;
	private KaryNode root;
	 
	public TreeQ(int k){
		this.k = k;
	}
	
	//BFS add nodes on each level from top to down
	public List<Object> toArray(){
		List<Object> nodes = new ArrayList<Object>();
		Deque<KaryNode> s = new ArrayDeque<KaryNode>();
		s.add(root);
		while(!s.isEmpty()){
			KaryNode node = s.remove();
			nodes.add(node.value);
			for(int i=0 ; i<node.children.length; i++){
				s.add(node.children[i]);
			}						
		}		
		return nodes;
	}
	
	//iterate through list 
	public KaryNode toKaryTree(List<Object> list){
			Map<Integer, KaryNode> map = new HashMap<Integer, KaryNode>();
			KaryNode root = new KaryNode(list.get(0));
			map.put(0,  root);		
			for(int i=1; i<list.size(); i++){
				if(list.get(i)!=null){
					KaryNode n = new KaryNode(list.get(i));
					int parent = (i-1)/k;	
					map.get(parent).addChild(n);
				}				
			}		
			return root;
	}
	
	
	public boolean printPath(Node root, Node leaf){
		if(root==null)
			return false;
		if(root == leaf || printPath(root.left, leaf) || printPath(root.right, leaf)){
			System.out.println(root.data);
			return true;
		}else{
			return false;
		}
	}
}
