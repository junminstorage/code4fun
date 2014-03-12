package org.blueocean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * read file lines in reverse way in combination of ReverseFileStream.java
 * 
 * @author junminliu
 *
 */
public class ReverseBufferFileReader {
	
	BufferedReader b;
	
	public ReverseBufferFileReader(Reader in) {
		b = new BufferedReader(in);		
	}
	
	public String readLine() throws IOException{
		String line = b.readLine();
		if(line!=null && !line.isEmpty()){
			return reverseString(line);
		}
		return line;
		
	}

	private String reverseString(String line) {
		char[] chars = line.toCharArray();
		int p1 = 0;
		int p2 = chars.length-1;
		while(p1<=p2){
			char temp = chars[p1];
			chars[p1] = chars[p2];
			chars[p2] = temp;
			p1++;
			p2--;
		}	
		return String.valueOf(chars);
	}
	
	public void close() throws IOException{
		b.close();
	}

}
