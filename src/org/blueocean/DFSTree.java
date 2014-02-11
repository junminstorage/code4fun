package org.blueocean;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DFSTree {
	abstract class Node{
		String value;
		Node left;
		Node right;
		
	}
	public static Node DFS(Node root, String v){
		Node result = null;		
		Deque<Node> stack = new ArrayDeque<Node>();
		stack.addFirst(root);
		
		while(!stack.isEmpty()){
			Node n = stack.removeFirst();
			if(n.value == v){
				result = n;
				break;
			}else{
				if(n.left!=null)
					stack.addFirst(n.left);
				if(n.right!=null)
					stack.addFirst(n.right);				
			}						
		}
						
		return result;
	}
	
	public static List<Node> DFTraverse(Node root){
		List<Node> result = new ArrayList<Node>();		
		DFT(root, result);		
		return result;
	}
	
	
	private static void DFT(Node n, List<Node> result){
		if(n.left!=null)
			DFT(n.left, result);
		 
		if(n.right!=null)
			DFT(n.right, result);
		
		result.add(n);
				
	}

}


