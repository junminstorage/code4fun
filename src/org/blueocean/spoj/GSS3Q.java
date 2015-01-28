package org.blueocean.spoj;

public class GSS3Q {
	
/*	http://www.spoj.com/problems/GSS3/
	You are given a sequence A of N (N <= 50000) integers between -10000 and 10000. 
	On this sequence you have to apply M (M <= 50000) operations: 
	modify the i-th element in the sequence or for given x y 
	print max{Ai + Ai+1 + .. + Aj | x<=i<=j<=y }.
*/
	
	/* at each node we store 5 piece of info
	 * data: the input number if at leaf level
	 * sum: the sum of the all elements at the interval represented by the current node
	 * prefixSum: the maximum sum of the elements starting from left bound of the current interval
	 * postfixSum: the maximum sum of the elements ending at right bound of current interval
	 * bestSum: the maximum sum of the subarrays at current interval
	 */
	public class Node {
		int data;
		int sum;
		int prefixSum;
		int postfixSum;
		int bestSum;	
	}
	
	public class SegmentTree {
		Node[] tree;
		int len;
		public SegmentTree(int[] nums){
			len = nums.length;
			int x = (int) Math.ceil(Math.log10(len)/Math.log10(2));
			tree = new Node[(int) (Math.pow(2, x)*2-1)];
			constructTree(0, nums, 0, nums.length-1);			
		}
		
		//Time Complexity for tree construction is O(n). There are total 2n-1 nodes, and value of every node is calculated only once in tree construction.
		private Node constructTree(int current, int[] nums, int start, int end){
			if(start==end){
				tree[current] = new Node();
				tree[current].data = tree[current].sum = tree[current].prefixSum 
						= tree[current].postfixSum = tree[current].bestSum = nums[start];
			}else{
				int left = current*2+1;
				int right = current*2+2;
				int mid = start + (end-start)/2;				
				Node l = constructTree(left, nums, start, mid), 
				r = constructTree(right, nums, mid+1, end);
				tree[current] = new Node();
				tree[current].sum = l.sum + r.sum;
				tree[current].prefixSum = Math.max(l.prefixSum, l.sum + r.prefixSum);
				tree[current].postfixSum = Math.max(r.postfixSum, l.postfixSum + r.sum);
				tree[current].bestSum = Math.max(Math.max(l.bestSum, r.bestSum), l.postfixSum+r.prefixSum);
				
			}
			return tree[current];
		}
		
		//Time complexity to query is O(lgN). To query a range max sum, we process at most 
		//4 nodes at every level and number of levels is O(lgN). 
		public int getMaxSum(int l, int r){
			Node result =  getMaxSumRec(l, r, 0, 0, len-1);
			return result==null? Integer.MIN_VALUE : result.bestSum;
		}
		
		private Node getMaxSumRec(int l, int r, int index, int start, int end){
			if(l<=start && r>=end)
				return tree[index];
			else if(end<l || start>r){
				return null;				
			}else{
				int mid = start + (end-start)/2;
				Node left = getMaxSumRec(l, r, 2*index+1, start, mid), 
					right = getMaxSumRec(l, r, 2*index+2, mid+1, end);
				
				if(left==null)
					return right;
				else if(right==null)
					return left;
				else{
				Node result = new Node();
							
				result.sum = left.sum + right.sum;
				result.prefixSum = Math.max(left.prefixSum, left.sum + right.prefixSum);
				result.postfixSum = Math.max(right.postfixSum, left.postfixSum + right.sum);
				result.bestSum = Math.max(Math.max(left.bestSum, right.bestSum), left.postfixSum+right.prefixSum);
				return result;
				}
			}
		}
				
		
	}

}
