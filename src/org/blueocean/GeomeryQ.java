package org.blueocean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GeomeryQ {
	
	//find the line with maximum number of points
	
	/*line class using pair of relative prime numbers 
	 *to represent the line from a pair of points
	 */
	static class Line {
		int dx, dy;
		public Line(int x, int y){
			if(x==0){//take care of vertical line
				dx = 0; dy = 1;
			} else {
				int g = gcd(x, y);
				dx = x/g; 
				dy = y/g;
				if(dy<0){//this will make sure (-1, -1) and (1, 1) maps to the same line
					dy = dy*-1; dx = dx*-1;
				}
			}
		}
		@Override
		public int hashCode(){
			return dx*31+dy;
		}
		@Override
		public boolean equals(Object line){
			if(line == this)
				return true;
			if(line instanceof Line){
				return this.dx == ((Line)line).dx && this.dy == ((Line)line).dy;
			}
			return false;			
		}
		
		public static int gcd(int x, int y){
			if(y==0)
				return x;
			x = Math.abs(x);
			y = Math.abs(y);
			int r = 0;
			for(; (r=x%y)!=0; x=y, y=r){}
			return y;
		}
	}
	
	static class Point{
		int x, y;
		public Point(int x, int y){this.x = x; this.y = y;}
	}
	
	/*
	 * code loop through each points with any other points, and form a line object, store the line 
	 * and associated points in a hashtable, 
	 * the line is defined as pair of relative prime (p1.x-p.x, p1.y-p.y)
	 */
	static Set<Point> findMaxPointsLine(Point[] points){
		int max = Integer.MAX_VALUE;
		Line maxLine = null;
		Set<Point> maxPoints = new HashSet<Point>();
		for(int i=0; i<points.length; i++){
			//map store all of the lines go through points[i] and its associated points on the line, 
			//besides the points[i] itself
			Map<Line, HashSet<Point>> myLines = new HashMap<Line, HashSet<Point>>();
			
			for(int j=0; j!=i && j<points.length; j++){
				Line line = new Line(points[j].x - points[i].x, points[j].y - points[j].y);
				if(!myLines.containsKey(line)){
					HashSet<Point> set = new HashSet<Point>();
					set.add(points[j]);
					myLines.put(line, set);
				}else{
					myLines.get(line).add(points[j]);
				}
				
				if(myLines.get(line).size()>max){
					max = myLines.get(line).size();
					maxLine = line;					
				}
			}
			// add point[i] itself
 			myLines.get(maxLine).add(points[i]);
			maxPoints = myLines.get(maxLine);
		}
		
		return maxPoints;
	}
	

}
