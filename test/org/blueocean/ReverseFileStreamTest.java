package org.blueocean;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

import junit.framework.TestCase;
import static java.lang.System.out;

public class ReverseFileStreamTest extends TestCase {
	
	public void setUp(){
		try {
			PrintWriter w
			   = new PrintWriter(new BufferedWriter(new FileWriter("foo.out")));
			w.println("123");
			w.println("234");
			w.println("345");
			w.close();
			
		} catch (IOException e) {
			// TODO log error message and throw application specific ex
			e.printStackTrace();
		}
		  
		
	}
	public void testReverseRead(){		
		BufferedReader in
	   = new BufferedReader(new InputStreamReader(new ReverseFileStream(new File("foo.out"))));
		
		try {
			String line = in.readLine();
			while(line!=null){
				out.println(line);
				line = in.readLine();
			}
		} catch (IOException e) {
			// TODO log error message and throw application specific ex
			e.printStackTrace();
		}
		
		out.close();
		
	}
	
	public void testReverseRead2(){		
		ReverseBufferFileReader in
	   = new ReverseBufferFileReader(new InputStreamReader(new ReverseFileStream(new File("foo.out"))));
		
		try {
			String line = in.readLine();
			while(line!=null){
				out.println(line);
				line = in.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.close();
		
	}

}
