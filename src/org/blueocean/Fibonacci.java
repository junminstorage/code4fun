package org.blueocean;

public class Fibonacci {
	public static int f(int n) throws Exception{
		if(n<0)
			throw new Exception();
		else if(n==0)
			return 0;
		else if(n==1)
			return 1;
		else			
			return f(n-1) + f(n-2);
	}
	
	public static int f2(int n) throws Exception{		
		if(n<0)
			throw new Exception();
		else if(n==0)
			return 0;
		else if(n==1)
			return 1;
		else{
			int sum_pre = 1;
			int sum_pre2 = 0;
			for(int i=2; i<=n; i++){
				int temp = sum_pre;
				sum_pre = sum_pre + sum_pre2;
				sum_pre2 = temp;
			}
			return sum_pre;
		}
		
	}

}
