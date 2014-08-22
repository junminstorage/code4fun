package org.blueocean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import java.util.TreeSet;

import org.blueocean.LinkedListQ.SortedListNode;

public class TreeQ {
	static class Node {
		char v;
		int data;
		Node left;
		Node right;
		Node parent;
		Node next;
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
		//root.right = right1;
		
		Node left2 = new Node();
		left2.data = 8;
		
		//left1.left = left2;
		
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
				if(n.right!=null && n.right!=pre){
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
	
	public static void deserilization(Node root, ObjectInputStream is) throws IOException, ClassNotFoundException{
		Node n = (Node) is.readObject();
		
		if(n.v=='\0'){			
			return;
		}
		else{
			root = n;
			deserilization(root.left, is);
			deserilization(root.right, is);
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
