package org.blueocean;

public class StringQ {
	
	public static boolean isSubStr(String target, String match){
		assert(target!=null && match !=null);
		int mSize = match.length();
		int tSize = target.length();
		assert(tSize>=mSize);
		
		char[] tChar = target.toCharArray();
		char[] mChar = match.toCharArray();
		
		for(int i=0; i<=tSize-mSize; i++){
			int temp = i;
			int j;
			for(j=0; j<mSize; j++){
				if(tChar[i]==mChar[j]){
					i++;
				}else{
					i = temp;
					break;
				}
			}
			if(j==mSize)
				return true;
		}		
		return false;
	}
}
