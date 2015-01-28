package org.blueocean;

public class VisitorQ {

	public static interface Visitable{
		abstract Object accept(TraversalVisitor v);
	}
	
	public static abstract class Tree implements Visitable {
		public Object data; 
		public Tree left; 
		public Tree right;
				
	}
	
	public static class Node extends Tree {			
		@Override
		public Void accept(TraversalVisitor v) {
			v.visit(this);
			return null;
		}		
	}
	
	public static interface TraversalVisitor {
		void visit(Tree t);
	}
	
	public static class PreOrderVisitor implements TraversalVisitor{

		@Override
		public void visit(Tree t) {
			System.out.println(t.data);
			t.left.accept(this);
			t.right.accept(this);
		}	
		
		public void s() {return;}
	}
	
	///////////////////////////////////////////////////////////////
	public static interface TreeExpression2{
		abstract int accept(ExpVisitor v);
	}
	
	public static interface t extends Visitable, TreeExpression2 {
		
	}
	
	public static abstract class Tree2 {
		public String data; 
		public TreeExpression2 left; 
		public TreeExpression2 right;
				
	}
	
	public static interface ExpVisitor {
		int visit(DataNode n);
		int visit(PlusNode n);
		int visit(MinusNode n);
	}
	
	
	
	public static class DataNode extends Tree2 implements TreeExpression2 {
		DataNode(String data) {this.data = data;}

		@Override
		public int accept(ExpVisitor v) {
			return v.visit(this);
		}
		
	}
	
	public static class PlusNode extends Tree2 implements TreeExpression2 {
		PlusNode() {this.data = "+";}

		@Override
		public int accept(ExpVisitor v) {
			return v.visit(this);
		}
	}
	
	public static class MinusNode extends Tree2 implements TreeExpression2 {
		MinusNode() {this.data = "-";}

		@Override
		public int accept(ExpVisitor v) {
			return v.visit(this);
		}
	}
	
	
	
	public static class OpsVisitor implements ExpVisitor{

		@Override
		public int visit(DataNode n) {
			return Integer.valueOf(n.data);
		}

		@Override
		public int visit(PlusNode n) {
			return n.left.accept(this) + n.right.accept(this);
		}

		@Override
		public int visit(MinusNode n) {
			return n.left.accept(this) - n.right.accept(this);
		}
	}
}
