package org.blueocean;

public class DynaProgQ {
	
	/*
	 * 
	 * 3. There is a particular sequence only uses the numbers 1, 2, 3, 4 and no two adjacent numbers are the same.
	 *	Write a program that given n1 1s, n2 2s, n3 3s, n4 4s will output the number of such sequences using all these numbers.
	 */
	public static int numberOfSeq(int n1, int n2, int n3, int n4){
		int[][][][] table1 = new int[n1+1][n2+1][n3+1][n4+1];
		int[][][][] table2 = new int[n1+1][n2+1][n3+1][n4+1];
		int[][][][] table3 = new int[n1+1][n2+1][n3+1][n4+1];
		int[][][][] table4 = new int[n1+1][n2+1][n3+1][n4+1];
		
		for(int l=1; l<n4; l++){
			table4[0][0][0][l] = 1;
		}
		
		for(int i=1; i<n1; i++){
			table1[i][0][0][0] = 1;
		}
		
		for(int j=1; j<n2; j++){
			table2[0][j][0][0] = 1;
		}
		
		for(int k=1; k<n3; k++){
			table3[0][0][1][0] = 1;
		}
		
		for(int i=0; i<=n1; i++){
			for(int j=0; j<=n2; j++){
				for(int k=0; k<n3; k++){
					for(int l=0; l<n4; l++){
						table1[i][j][k][l] = i-1<0?0:(table2[i-1][j][k][l] + table3[i-1][j][k][l] + table4[i-1][j][k][l]);
						table2[i][j][k][l] = j-1<0?0:(table1[i][j-1][k][l] + table3[i][j-1][k][l] + table4[i][j-1][k][l]);
						table3[i][j][k][l] = k-1<0?0:(table1[i][j][k-1][l] + table2[i][j][k-1][l] + table4[i][j][k-1][l]);
						table4[i][j][k][l] = l-1<0?0:(table1[i][j][k][l-1] + table3[i][j][k][l-1] + table4[i][j][k][l-1]);
					}
				}
			}
		}
			
		return table1[n1][n2][n3][n4]+table2[n1][n2][n3][n4]+table3[n1][n2][n3][n4]+table4[n1][n2][n3][n4];
	}
	
	
	/*
	 * 2. There are “n” ticket windows in the railway station. ith window has ai tickets available. Price of a ticket is equal to the number of tickets remaining in that window at that time. When “m” tickets have been sold, what’s the maximum amount of money the railway station can earn? 
	 * exa. n=2, m=4 in 2 window available tickets are : 2 , 5 from 2nd wicket sold 4 tickets so 5+4+3+2=14.
	 */
	public static int maxRevenue(int[] a, int m){
		int[][] maxTable = new int[a.length+1][m+1];
		//sell 0 ticket
		for(int i=0; i<=a.length; i++)
			maxTable[i][0] = 0;
		//only 0 window
		for(int j=0; j<=m; j++)
			maxTable[0][j] = 0;
				
		for(int i=1; i<=a.length; i++){
			for(int j=1; j<=m; j++){
				maxTable[i][j] = 0;
				
				for(int k=0; k<=a[i-1] && k<=j; k++){		
					int	t = maxTable[i-1][j-k] + k*a[i-1] - k*(k-1)/2;			
					if(maxTable[i][j]<t)
						maxTable[i][j] = t;
				}				
			}			
		}		
		return maxTable[a.length][m];
	}
	
	/*
	 * http://www.geeksforgeeks.org/amazon-interview-set-48-campus-sde-1/
	 * 1. N number of jars are kept in a linear fashion. Each jar contains a color whose value ranges from 0-99. 
	 * Now you can mix any two adjacent jars having colors ‘a’ and ‘b’ (both integers), 
	 * and it will produce a new color of the value (a+b) mod 100 and will also produce smoke with value (a*b). 
	 * Mix all the jars in a way such that in the end only one jar remains and total smoke produced is minimum.
	 */
	public static int jarMix(int[] jar){
		int[][] table = new int[jar.length][jar.length];
		int[][] sum = new int[jar.length][jar.length];
		for(int i=0; i<jar.length; i++){
			table[i][i] = jar[i];
			sum[i][i] = jar[i];
		}
		
		for(int step = 1; step < jar.length; step++){
			for(int i=0; i+step<jar.length; i++){
				//table[i][i+step]
				table[i][i+step] = Integer.MAX_VALUE;
				//i<=k<i+step
				for(int k = i; k<i+step; k++){
					int temp =  table[i][k] + table[k+1][i+step] + sum[i][k]*sum[k+1][i+step];
					if(table[i][i+step] > temp){
						table[i][i+step] = temp;
						sum[i][i+step] = (sum[i][k] + sum[k+1][i+step])%100;
					}
				}
			}			
		}				
		return table[0][jar.length-1];
	}
	
	
	/*
	 * http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
	 */
	
	public static int knapSack(int[] values, int[] weights, int capacity){
		int[][] table = new int[values.length+1][capacity+1];
		for(int w=0;w<=capacity;w++)
			table[0][w] = 0;
		
		for(int i=1; i<=values.length; i++){
			for(int w=0; w<=capacity; w++){
				if(weights[i] > w){
					table[i][w] = table[i-1][w];
				}else{
					table[i][w] = Math.max(table[i-1][capacity-weights[i]]+values[i-1], table[i-1][capacity]);
				}				
			}
		}		
		return table[values.length][capacity];
	}
	
	/*
	 * http://www.geeksforgeeks.org/dynamic-programming-set-34-assembly-line-scheduling/
	 */
	public static int[] assemblyLineScheduling(){
		int a[][] = {{4, 5, 3, 2}, {2, 10, 1, 4}};
		int t[][] = {{0, 7, 4, 5}, {0, 9, 2, 8}};
		int e[] = {10, 12}, x[] = {18, 7};
		
		//store the final time exiting from each station
		int result[][] = new int[2][a[0].length];
		
		//initialization
		result[0][0] = e[0] + a[0][0];
		result[1][0] = e[1] + a[1][0];
		
		for(int i=1;i<a[0].length; i++){
			result[0][i] = Math.min(result[0][i-1] + a[0][i], result[1][i-1] + t[1][i]+a[0][i]);			
			result[1][i] = Math.min(result[1][i-1] + a[1][i], result[0][i-1] + t[0][i]+a[1][i]);
		}
		
		int finalT[] = new int[2];
		finalT[0] = result[0][a[0].length] + x[0];
		finalT[1] = result[1][a[0].length] + x[1];
		
		return finalT;
	}
	
	
	/*
	 * http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/
	 */
	public static int matrixChainOrder(int[] matricies){
		int[][] table = new int[matricies.length][matricies.length];
		
		for(int i=1; i<matricies.length; i++){
			table[i][i] = 0;
		}
		
		for(int i=1; i<matricies.length-1; i++){
			table[i][i+1] = matricies[i-1]*matricies[i]*matricies[i+1];
		}
		
		for(int step = 2; step < matricies.length-1; step++){
			for(int i=1; i<matricies.length-step; i++){
				table[i][i+step] = Integer.MAX_VALUE;
				for(int k=i; k<i+step; k++){
					int t = table[i][k] + table[k+1][i+step] + matricies[i-1]*matricies[k]*matricies[i+step];
					if(t<table[i][i+step])
						table[i][i+step] = t;
				}					
			}			
		}
		
		return table[1][matricies.length-1];
	}
	
	/*
	 * N number of jars are kept in a linear fashion. Each jar contains a color whose value ranges from 0-99. Now you can mix any two adjacent jars having colors ‘a’ and ‘b’ (both integers), and it will produce a new color of the value (a+b) mod 100 and will also produce smoke with value (a*b).
	 *  Mix all the jars in a way such that in the end only one jar remains and total smoke produced is minimum.
	 */
	
}
