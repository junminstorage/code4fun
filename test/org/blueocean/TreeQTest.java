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
		
		//left1.left = left2;
		left2.parent = left1;
		
		Node right2 = new Node();
		right2.data = 10;
		
		//left1.right = right2;
		right2.parent = left1;
		
		Node left3 = new Node();
		left3.data = 13;
		
		//right1.left = left3;
				
		Node right3 = new Node();
		right3.data = 15;
		
		//right1.right = right3;
		
		Node right4 = new Node();
		right4.data = 18;
		
		right3.right = right4;
		
		Node right5 = new Node();
		right5.data = 20;
		
		right4.right = right5;
		
		return root;
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
}
