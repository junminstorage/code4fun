package org.blueocean;

public final class Node implements Comparable<Node>{
	final String w;
	final int f;
	
	public Node(String key, Integer integer) {
		this.w=key;
		this.f = integer;
	}

	@Override
	public int compareTo(Node c) {
			return (this.f - c.f) == 0? this.w.compareTo(c.w) : this.f - c.f;			
	}
}
