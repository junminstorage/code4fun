package org.blueocean;

public class CombinatoricsQ {
	
	public static void histogramOfDices(int n, int m){
		int[][] table = new int[n+1][n*m+1];
		
		for(int k=1; k<=m; k++)
			table[1][k] = 1;
		
		for(int i=2; i<=n; i++){
			for(int k=i; k<=i*m; k++){
				table[i][k] = 0;
				for(int j=1; j<=m && k-j>=i-1; j++){
					table[i][k] += table[i-1][k-j];						
				}								
			}
		}
		
		for(int k=n; k<=n*m; k++){
			System.out.println(k+ "-" +table[n][k]);
		}
	}

}
