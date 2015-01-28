package org.blueocean;
import org.blueocean.RangeQ.IntervalTree.Interval;

import junit.framework.TestCase;

public class RangQTest extends TestCase {
	
	public class Interval implements Comparable<Interval>{
		int left, right;
		public Interval(int l, int r){
			this.left = l; this.right = r;
		}
		@Override
		public int compareTo(Interval i){
			return i!=null?this.left - i.left:1;
		}
		
		
	}
	
	public void test(){
		Interval i = new Interval(1, 2);
		i.compareTo(null);
	}

}
