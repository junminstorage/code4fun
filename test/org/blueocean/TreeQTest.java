package org.blueocean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.blueocean.TreeQ.Node;
import org.junit.Assert;

import junit.framework.TestCase;

public class TreeQTest extends TestCase {
	
	
	public Node createBST2(){
		Node root = new Node();
		root.data = 9;
		
		Node left1 = new Node();
		left1.data = 8;
		
		Node right1 = new Node();
		right1.data = 12;
		
		root.left = left1;
		root.right = right1;
		
		
		Node left2 = new Node();
		left2.data = 10;
		
		right1.left = left2;
		
		Node right2 = new Node();
		right2.data = 14;
		
		right1.right = right2;
		
		return root;
	}
	
	public Node createBST(){
		Node root = new Node();
		root.data = 12;
		
		Node left1 = new Node();
		left1.data = 9;
		
		Node right1 = new Node();
		right1.data = 14;
		
		root.left = left1;
		root.right = right1;
		left1.parent = root;
		right1.parent = root;
		
		
		Node left2 = new Node();
		left2.data = 8;
		
		//Node left21 = new Node();
		//left21.data = 38;
		
		//left2.left = left21;
		
		left1.left = left2;
		left2.parent = left1;
		
		Node right2 = new Node();
		right2.data = 10;
		
		left1.right = right2;
		
		
		Node left3 = new Node();
		left3.data = 11;
		
		left1.right = left3;
		
		right2.parent = left1;
		/*
		Node left3 = new Node();
		left3.data = 995;
		
		right1.left = left3;
				
		Node right3 = new Node();
		right3.data = 995;
		
		right1.right = right3;
		
		Node right4 = new Node();
		right4.data = 0;
		
		right3.right = right4;
		
		Node right5 = new Node();
		right5.data = -20;
		
		right4.right = right5;
		*/
		return root;
	}
	
	public void testisCousin(){
		TreeQ.Node root1 = createBST();
		System.out.println(TreeQ.isCousin(root1, 8, 11));
	}
	
	public void testLevel(){
		TreeQ.Node root1 = createBST();
		System.out.println(TreeQ.level(root1, 8));
	}
	
	public void testPrintBTLevelOrderTraversalBottomUp(){
		TreeQ.Node root1 = createBST();
		
		TreeQ.printBTLevelOrderTraversalBottomUp(root1);
	}
	
	public void testFlattenABinaryTreetoLinkedListPostOrder(){
		TreeQ.Node root1 = createBST();
		
		TreeQ.FlattenABinaryTreetoLinkedListPostOrder(root1);
	}
	
	public void testEqual(){
		TreeQ.Node root1 = createBST();
		TreeQ.Node root2 = createBST2();
		
		System.out.println(TreeQ.equal(root1, root2));
	}
	public void testBuildBSTFromInOrder(){
		Node n = TreeQ.buildBSTFromInOrder(new int[]{3,  2,  1, 5, 4}, new int[]{0}, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public void testPrintVerticalSum(){
		TreeQ.Node root = createBST();
		TreeQ.printVerticalSums(root, 2);
	}
	
	public void testPrintPathSum(){
		TreeQ.Node root = createBST();
		TreeQ.printPathSum(root, 1021, new ArrayList());
	}
	
	public void testOutputTreeString(){
		TreeQ.Node root = createBST();
		StringBuilder sb = new StringBuilder();
		TreeQ.outputTreeString(root, sb);
		
		System.out.println(sb.toString());
	}
	
	public void testMirrorTreeInPlace(){
		TreeQ.Node root = createBST();
		StringBuilder sb = new StringBuilder();
		TreeQ.outputTreeString(root, sb);	
		System.out.println(sb.toString());
		sb = new StringBuilder();
		TreeQ.mirrorTreeInPlace(root);
		TreeQ.outputTreeString(root, sb);	
		System.out.println(sb.toString());
		
		TreeQ.Node root2 = createBST();
		TreeQ.mirrorTreeInPlace2(root2);
		sb = new StringBuilder();
		TreeQ.outputTreeString(root2, sb);	
		System.out.println(sb.toString());
	}
	
	public void testPruneTree(){
		TreeQ.Node root = createBST();
		TreeQ.pruneTree(root, 4);
	}
	
	public void testmaxSumFromRoot(){
		TreeQ.Node root = createBST();
		System.out.println(TreeQ.maxSumFromRoot(root));
	}
	
	public void testfindLeaveWithMaxSumFromRoot(){
		TreeQ.Node root = createBST();
		TreeQ.findLeaveWithMaxSumFromRoot(root, 0);
		
		System.out.println(TreeQ.max_sum);
		System.out.println(TreeQ.maxNode.data);
		
	}
	
	public void testZigZag2(){
		TreeQ.Node root = createBST();
		TreeQ.zigZag2(root);
		
		Node curr = root;
		while(curr!=null){
			System.out.println(curr.data);
			curr = curr.next;
		}
	}
	
	public void testZigZag(){
		TreeQ.Node root = createBST();
		TreeQ.zigZag(root);
		
		
	}
	public void testFindMinDepth(){
		TreeQ.Node root = createBST();
		
		System.out.println(TreeQ.findMinDepth(root, 1));
		System.out.println(TreeQ.findMinDepth2(root));
		
	}
	public void testTreeToList(){
		TreeQ.Node root = createBST();
		List<TreeQ.Node> list = new ArrayList<TreeQ.Node>();
		TreeQ.treeToList(root, list );
		
		for(TreeQ.Node i : list){
			System.out.println(" " + i.data);
		}
	}
	
	public void testIsBST2(){
		TreeQ.Node root = createBST();
		Assert.assertTrue(TreeQ.isBST2(root));
		Assert.assertFalse(TreeQ.isBST(root));
	}
	
	public void testTreeToDLList(){
		TreeQ.Node root = createBST();
		
		TreeQ.treeToDLList(root);
		Node node = TreeQ.head;
		while(node!=null){
			System.out.println(node.data);
			node = node.left;
		}		
	}
	
	public void testIsBSTAndTreeToDLL(){
		TreeQ.Node root = createBST();
		
		Node node = TreeQ.isBSTAndTreeToDLL(root);;
		int counter = 0;
		while(node!=null&& counter<10){
			System.out.println(node.data);
			node = node.right;
			counter++;
		}		
	}
	
	public void testBuildTreeFromOrderedList(){
		Integer[] num = {1, 2, 3, 4, 5, 6, 7};
		List<Integer> list = Arrays.asList(num);
		Node root = TreeQ.buildTreeFromOrderedList(list);
		System.out.println(root.data);
	}

	public void testInOrderVisitLeaves(){
		TreeQ.Node root = createBST();
		List<Node> list = TreeQ.inOrderVisitLeaves(root);
		for(Node n: list)
			System.out.println(n.data);
	}
	
	public void testTreeToLL(){
		TreeQ.Node root = createBST();
		List<LinkedList<Node>> result = TreeQ.treeToLL(root);
		for(LinkedList<Node> l : result){
			for(Node n : l){
				System.out.print(n.data + " - ");
			}
			System.out.println("");
		}
	}
	
	public void testFindNextInOrderSuccessor(){
		TreeQ.Node root = createBST();
		Node n = TreeQ.findNextInOrderSuccessor(root);
		Assert.assertEquals(14, n.data);
		
		n = TreeQ.findNextInOrderSuccessor(root.left);
		Assert.assertEquals(10, n.data);
		
		n = TreeQ.findNextInOrderSuccessor(root.left.left);
		Assert.assertEquals(9, n.data);
		
		n = TreeQ.findNextInOrderSuccessor(root.left.right);
		Assert.assertEquals(12, n.data);
			
	}
	
	public void testConstructTree(){
		TreeQ.Node n = TreeQ.constructTree("ABDECF", "DBEAFC");
		
		System.out.println(n.v);
		
		System.out.println(n.left.v);
		
		System.out.println(n.left.left.v);
		
		System.out.println(n.right.v);
		
		System.out.println(n.right.left.v);
		
		
		
	}
}
