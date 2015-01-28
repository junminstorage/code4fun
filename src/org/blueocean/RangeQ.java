package org.blueocean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RangeQ {
	
	public static class Test {
		private Test(){}
	}
	
	public static class SubTest extends Test {
		public SubTest(){}
	}
	
	/*
	Every node of Interval Tree stores following information.
	a) i: An interval which is represented as a pair [low, high]
	b) max: Maximum high value in subtree rooted with this node.
	*/
	public class IntervalTree{
		
		public class Node implements Comparable<Node>{
			int max;
			Interval interval;
			
			Node left, right;
			public Node(Interval inter) {
				this.interval = inter; 
				this.max = inter.right;
			}
			@Override
			public int compareTo(Node o){
				return this.interval.left - o.interval.left;
			}
			
			public boolean overlap(Interval inter){
				return this.interval.left <= inter.right && this.interval.right >= inter.left; 
			}
		}
		
		//immutable class
		public class Interval implements Comparable<Interval>{
			int left, right;
			public Interval(int l, int r){
				this.left = l; this.right = r;
			}
			@Override
			public int compareTo(Interval i){
				return this.left - i.left;
			}
			
			public boolean overlap(Interval inter){
				return this.left <= inter.right && this.right >= inter.left; 
			}
			
		}
		
		private Node tree;
		
		public IntervalTree(Interval[] pairs){
			treeBuild(pairs);
		}
		
		/*
		 * this implementation assuming static input, use array to present the ordered intervals
		 * for dynamically input such as add/delete on the fly, 
		 * we can use balanced interval tree, then in-order travel to process the tree
		 * storage O(n)
		 * query time O(n)
		 * construction: O(nlog(n))
		 * insertion: o(n)
		 */
		public int totalLength(Interval[] pairs){
			//sort by the left corr
			Arrays.sort(pairs);
			List<Interval> merged = new ArrayList<Interval>();
			merged.add(pairs[0]);
			
			for(int i=1; i<pairs.length; i++){
				int size = merged.size();
				Interval lastInter = merged.get(size-1);
				if(lastInter.overlap(pairs[i])){
					lastInter.right = Math.max(lastInter.right, pairs[i].right);
				}else{
					merged.add(pairs[i]);
				}
			}
			
			int sum = 0;
			for(Interval inter : merged){
				sum += inter.right - inter.left;
			}
			return sum;
		}
		
		public Interval findOne(Interval input){
			Node curr = tree;
			while(curr!=null){
				if(curr.overlap(input))
					return curr.interval;
				else if(curr.left==null)
					curr = curr.right;
				else if(curr.left.max < input.left)
					curr = curr.right;
				else
					curr = curr.left;
			}
			
			return null;
		}
		
		public List<Interval> findAll(Interval input){
			List<Interval> result = new ArrayList<Interval>();
			findAll(tree, input, result);
			return result;
		}
		
		//in-order travel with condition check against curr.max and curr.left
		public void findAll(Node curr, Interval input, List<Interval> result){
			if(curr==null)
				return;
				
			if(curr.max < input.left)
				return;
			
			if(curr.left!=null)
				findAll(curr.left, input, result);
			
			if(curr.overlap(input))
				result.add(curr.interval);		
			
			if(input.right < curr.interval.left)
				return;
			
			if(curr.right!=null)
				findAll(curr.right, input, result);
			
		}
		
		
		
		//a simple tree build without balanced
		//ideally we should use splay tree, or r-b tree
		private void treeBuild(Interval[] pairs){
			tree = new Node(pairs[0]);			
			for(int i=1; i<pairs.length; i++)
				insert(pairs[i], tree);
		}	
		
		private void insert(Interval inter, Node curr){
			curr.max = Math.max(curr.max, inter.right);
			if(inter.left<=curr.interval.left){
				if(curr.left==null){
					curr.left = new Node(inter);
				}else
					insert(inter, curr.left);
			}
			else{
				if(curr.right==null){
					curr.right = new Node(inter);
				}else
					insert(inter, curr.right);
			}
		}
		
	}
	
	//BST with items on leave
	//use for given a range, find all points
	public class RangeTree{
		
	}
	
	public class BST {
		public class Node {
			int i; Node left, right;
		}
		
		private Node tree;
		private void BSTBuild(int[] nums){
			//omit here
		}
		//Time Complexity: O(n) where n is the total number of keys in tree
		public void findRange(Node current, int l, int r){
			if(current==null)
				return;
			if(current.i>l)
				findRange(current.left, l, r);
			if(current.i >=l && current.i<=l)
				System.out.println(current.i);
			if(current.i<r)
				findRange(current.right, l, r);
		}
	}
	
/*	Segment tree
	1. Leaf Nodes are the elements of the input array.
	2. Each internal node represents some merging of the leaf nodes. The merging may be different for different problems. For this problem, merging is sum of leaves under a node.
*/

	/*
	 * a segment tree representation using array
	 * note this supports min query only, but can be adopted to other types like sum, max and etc.
	 */
	public class SegmentTree {
		int[] tree;
		int len;
		public SegmentTree(int[] nums){
			len = nums.length;
			int x = (int) Math.ceil(Math.log10(len)/Math.log10(2));
			tree = new int[(int) (Math.pow(2, x)*2-1)];
			constructTree(0, nums, 0, nums.length-1);			
		}
		
		//Time Complexity for tree construction is O(n). There are total 2n-1 nodes, and value of every node is calculated only once in tree construction.
		private int constructTree(int current, int[] nums, int start, int end){
			if(start==end){
				tree[current] = nums[start];
			}else{
				int left = current*2+1;
				int right = current*2+2;
				int mid = start + (end-start)/2;				
				tree[current] = Math.min(constructTree(left, nums, start, mid), constructTree(right, nums, mid+1, end));				
			}
			return tree[current];
		}
		
		//Time complexity to query is O(Logn). To query a range minimum, we process at most 
		//two nodes at every level and number of levels is O(Logn). 
		public int getMin(int l, int r){
			return getMinRec(l, r, 0, 0, len-1);
		}
		
		private int getMinRec(int l, int r, int index, int start, int end){
			if(l<=start && r>=end)
				return tree[index];
			else if(end<l || start>r){
				return Integer.MAX_VALUE;
				
			}else{
				int mid = start + (end-start)/2;
				return Math.min(getMinRec(l, r, 2*index+1, start, mid), getMinRec(l, r, 2*index+2, mid+1, end));
			}
		}
		
		//update value of input at index
		public void update(int index, int v){
			   update(index, v, 0, 0, len-1);

		}

		private int update(int index, int v, int treeIndex, int start, int end){
			  if(index<start || index >end)
			        return tree[treeIndex];
			  
			  if(index == start && index == end){
				  tree[treeIndex] = v;
				  return v;
			  }
			  		        
			  int mid = start + (end-start)/2;
			  tree[treeIndex] = Math.min(update(index, v, 2*treeIndex+1, start, mid), update(index, v, 2*treeIndex+2, mid+1, end));			        
			  return tree[treeIndex];
			        
		}
		
		
		
	}

}
