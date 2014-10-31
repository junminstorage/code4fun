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
		
		BufferedReader in2
		   = new BufferedReader(new InputStreamReader(new ReverseFileStream(new File("foo.out"))));
		
		/*
		 * For file-based I/O using binary data, always use a BufferedInputStream 
		 * or BufferedOutputStream to wrap the underlying file stream. 
		 * For file-based I/O using character (string) data, always 
		 * wrap the underlying stream with a BufferedReader or BufferedWriter.

		 */
		/*
		 * In complex applications (particularly application servers) with multiple classloaders, making those class loaders parallel-capable can solve issues where they are bottlenecked on the system or bootclass classloader.
Applications that do a lot of classloading through a single classloader in a single thread may benefit from disabling the parallel-capable feature of Java 7.
		 */
		/*
		 * Java’s default Random class is expensive to initialize, but once initialized, it can be reused.
In muti-threaded code, the ThreadLocalRandom class is preferred.
The SecureRandom class will show arbitrary, completely random performance. Performance tests on code using that class must be carefully planned.
		 */
		try {
			
			String line = in.readLine();
			
			while(line!=null){
				out.println(line);
				out.println(in2.readLine());
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
