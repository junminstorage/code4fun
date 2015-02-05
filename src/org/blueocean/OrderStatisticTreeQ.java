package org.blueocean;

/*
In computer science, an order statistic tree is a variant of the binary search tree (or more generally, a B-tree[1]) that supports two additional operations beyond insertion, lookup and deletion:
Select(i) — find the i'th smallest element stored in the tree
Rank(x) – find the rank of element x in the tree, i.e. its index in the sorted list of elements of the tree
*/
public class OrderStatisticTreeQ {
	
	public class Node {
		int data;
		int size;
		Node left;
		Node right;
		
		public Node(int d, int s){
			this.data = d;
			this.size = s;
		}
	}

	/*Select(i) — find the i'th smallest element stored in the tree
	Rank(x) – find the rank of element x in the tree, i.e. its index in the sorted list of elements of the tree
	*/
	//k starts with 0
	public int findKthSmallest(Node root, int k){
		int level = k;
		Node curr = root;
		
		while(curr!=null){
			int leftSize = (curr.left ==null? 0 : curr.left.size);
			
			if(level == leftSize )
				return curr.data;
			
			if(leftSize > level)
				curr = curr.left;
			else{
				curr = curr.right;
				level = level - (leftSize+1);
			}			
		}		
		return -1;
	}
	
	public int findIndexFor(Node root, int value){
		Node curr = root;
		int passed = 0;
		while(curr!=null){
			if(curr.data == value){
				return (curr.left==null?0:curr.left.size) + passed;
			}else if(curr.data > value){
				curr = curr.left;
			}else{
				passed += (curr.left==null?0:curr.left.size) + 1;
				curr = curr.right;
			}
		}
		return -1;
	}
}
