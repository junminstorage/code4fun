package org.blueocean;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

public class Recursive {
	
	/*
	 * 3. There is a particular sequence only uses the numbers 1, 2, 3, 4 and no two adjacent numbers are the same.
Write a program that given n1 1s, n2 2s, n3 3s, n4 4s will output the number of such sequences using all these numbers.
	 */
	public static int numberOfSeq(int n1, int n2, int n3, int n4){
		int[] result = new int[1];
		result[0]++;
		numberOfSeqUtil(n1, n2, n3, n4, 1, 0, 0, 0, 1, result);
		result[0]++;
		numberOfSeqUtil(n1, n2, n3, n4, 0, 1, 0, 0, 2, result);
		result[0]++;
		numberOfSeqUtil(n1, n2, n3, n4, 0, 0, 1, 0, 3, result);
		result[0]++;
		numberOfSeqUtil(n1, n2, n3, n4, 0, 0, 0, 1, 4, result);
		return result[0];
	}
	
	private static void numberOfSeqUtil(int n1, int n2, int n3, int n4, int k1, int k2, int k3, int k4, int lastDigit, int[] result){
		 if(lastDigit==1){
			if(k2<n2){
			result[0]++;
			numberOfSeqUtil(n1, n2, n3, n4, k1, k2+1, k3, k4, 2, result);
			}
			if(k3<n3){
			result[0]++;
			numberOfSeqUtil(n1, n2, n3, n4, k1, k2, k3+1, k4, 3, result);
			}
			if(k4<n4){
			result[0]++;
			numberOfSeqUtil(n1, n2, n3, n4, k1, k2, k3, k4+1, 4, result);
			}
		}else if(lastDigit==2){
			if(k1<n1){
			result[0]++;
			numberOfSeqUtil(n1, n2, n3, n4, k1+1, k2, k3, k4, 1, result);
			}
			if(k3<n3){
			result[0]++;
			numberOfSeqUtil(n1, n2, n3, n4, k1, k2, k3+1, k4, 3, result);
			}
			if(k4<n4){
			result[0]++;
			numberOfSeqUtil(n1, n2, n3, n4, k1, k2, k3, k4+1, 4, result);
			}
		}else if(lastDigit==3){
			if(k1<n1){
			result[0]++;
			numberOfSeqUtil(n1, n2, n3, n4, k1+1, k2, k3, k4, 1, result);
			}
			if(k2<n2){
			result[0]++;
			numberOfSeqUtil(n1, n2, n3, n4, k1, k2+1, k3, k4, 2, result);
			}
			if(k4<n4){
			result[0]++;
			numberOfSeqUtil(n1, n2, n3, n4, k1, k2, k3, k4+1, 4, result);
			}
		}else{
			if(k1<n1){
				result[0]++;
				numberOfSeqUtil(n1, n2, n3, n4, k1+1, k2, k3, k4, 1, result);
			}
			if(k2<n2){
				result[0]++;
				numberOfSeqUtil(n1, n2, n3, n4, k1, k2+1, k3, k4, 2, result);
			}
			if(k3<n3){
				result[0]++;
				numberOfSeqUtil(n1, n2, n3, n4, k1, k2, k3+1, k4, 3, result);
			}
		}
		
		
	}
	
	/**
	 * Towers of Hanoi
	 * @author junminliu
	 * @throws Exception 
	 *
	 */	
			
	public static void move8Disks() throws Exception{
		int n =3;
		Tower start = new Tower(0);
		Tower dest = new Tower(1);
		Tower buffer = new Tower(2);
		for (int i = n; i >= 1; i--) start.addTop(i);;
		start.moveTower(dest, buffer, n);
		//throw new Exception();
	}
	
	
	
	
	private enum Color{
		black, white, red
	}
	
	public static void pain(Color[][] area, int x, int y, Color oColor){
		pain(area, x, y, oColor, area[x][y]);		
	}
	
	public static void pain(Color[][] area, int x, int y, Color oColor, Color tColor){
		if(area==null)
			return;
		if(x<0 || y<0 || x>=area.length || y>=area[0].length)
			return;
		if(area[x][y]==tColor){
			area[x][y]=oColor;
		pain(area, x+1, y, oColor, tColor);
		pain(area, x-1, y, oColor, tColor);
		pain(area, x, y-1, oColor, tColor);
		pain(area, x, y+1, oColor, tColor);
		}
	}
	
	public static HashSet<String> parenthesisPermu(int n){
		HashSet<String> result = new HashSet<String>();
		if(n==0){
			result.add("");
			return result;
		}
		if(n==1){
			result.add("()");
			return result;
		}
		
		HashSet<String> preResult = parenthesisPermu(n-1);
		
		for(String s : preResult){
			result.add("()" + s);
			result.add("(" + s + ")");
			result.add(s+"()");
		}
		
		return result;
	}

	private int colPos[] = new int[8];
	/**
	 * place 8 queens in 8*8 board
	 */
	public void solve(){
		placeRow(0);
	}
	
	public void placeRow(int row){
		if(row == 8){//problem solved
			System.out.println(colPos.toString());
			return;
		}
		
		//try each column on this row
		for(int col=0; col<8; col++){
			colPos[row] = col;
			if(check(row)){
				placeRow(row+1);
			}
		}
		
	}
	
	private boolean check(int row){
		for(int i=0; i<row; i++){
			if(colPos[row] == colPos[i] || Math.abs(colPos[row]-row) == Math.abs(colPos[i]-i))
				return false;
		}
		
		return true;
	}
	
}
