package org.blueocean;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class FileQ {
	
	public interface Folder<T, U>
	{
	    U fold(U u, Queue<T> list, Function2<T, U, U> function);
	}

	public interface Function2<T, U, R>
	{
	    R apply(T t, U u);
	}

	public class MyFolder<T, U> implements Folder<T, U>
	{
	    public U fold(U u, Queue<T> ts, Function2<T, U, U> function)
	    {
	        if(u == null || ts == null || function == null)
	            throw new IllegalArgumentException();

	        //if (ts.isEmpty()) {
	        //    return u;
	        //}
	        	    
	        while(!ts.isEmpty()){        	
	        	U newU = function.apply(ts.poll(), u);
	        	u = newU;
	        }
	        
	        
	        // The recursive implementation will overflow the stack for
	        // any data set of real size, your job is to implement a
	        // non-recursive solution
	        // return fold(function.apply(ts.poll(), u), ts, function);
	        return u;
	    }
	}
	
	
	private static final int SIZE = 0;
	
	public void BufferedInputStreamwithbytearrayreads(String name) throws IOException{
	BufferedInputStream f = new BufferedInputStream(
		    new FileInputStream( name ) );
		byte[] barray = new byte[SIZE];
		long checkSum = 0L;
		int nRead;
		while ( (nRead=f.read( barray, 0, SIZE )) != -1 )
		    for ( int i=0; i<nRead; i++ )
		        checkSum += barray[i];
	}
	
	public void RandomAccessFilewithbytearrayreads(String name) throws IOException{
	RandomAccessFile f = new RandomAccessFile( name, "r" );
	byte[] barray = new byte[SIZE];
	long checkSum = 0L;
	int nRead;
	while ( (nRead=f.read( barray, 0, SIZE )) != -1 )
	    for ( int i=0; i<nRead; i++ )
	        checkSum += barray[i];
		
	}
	
	/*
	 * A ByteBuffer created with the allocateDirect() method may directly use storage deeper in the JVM or OS. This can reduce 
	 * copying of data upward into your application's array, saving some overhead.
	 */
	public void FileChannelwithdirectByteBufferandbytearraygets(String name) throws IOException{
		FileInputStream f = new FileInputStream( name );
		FileChannel ch = f.getChannel( );
		int BIGSIZE = 0;
		ByteBuffer bb = ByteBuffer.allocateDirect( BIGSIZE );
		byte[] barray = new byte[SIZE];
		long checkSum = 0L;
		int nRead, nGet;
		while ( (nRead=ch.read( bb )) != -1 )
		{
		    if ( nRead == 0 )
		        continue;
		    bb.position( 0 );
		    bb.limit( nRead );
		    while( bb.hasRemaining( ) )
		    {
		        nGet = Math.min( bb.remaining( ), SIZE );
		        bb.get( barray, 0, nGet );
		        for ( int i=0; i<nGet; i++ )
		            checkSum += barray[i];
		    }
		    bb.clear( );
		}
	}
	public void n(String file) throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        // process the line.
		    }
		    // line is not visible here.
		}
		
		//try (Stream<String> lines = Files.lines(file, Charset.defaultCharset())) {
		//	  lines.forEachOrdered(System.out::println);
		//	}
	}
	/*
	 * FileChannel's map method returns a MappedByteBuffer that memory maps part or all of the file into the address space of the application. This gives more direct access to the file without an 
	 * intermediate buffer. Call the get() method on MappedByteBuffer to get the next byte.
	 */
	//FileChannel with MappedByteBuffer and byte gets
	public void FileChannelwithMappedByteBufferandbytearraygets(String name) throws IOException{
		FileInputStream f = new FileInputStream( name );
		FileChannel ch = f.getChannel( );
		MappedByteBuffer mb = ch.map( MapMode.READ_ONLY, 0L, ch.size( ) );
		byte[] barray = new byte[SIZE];
		long checkSum = 0L;
		int nGet;
		while( mb.hasRemaining( ) )
		{
			nGet = Math.min( mb.remaining( ), SIZE );
			mb.get( barray, 0, nGet );
			for ( int i=0; i<nGet; i++ )
				checkSum += barray[i];
		}
	}
	
	public void readLargeFileIntoMappedbyte() throws IOException{
	// 200GB
    long len = 200L * 1024 * 1024 * 1024;
    File file = new File("C:\\huge.dat");

    RandomAccessFile raf = new RandomAccessFile(file, "rw");
    raf.setLength(len);
    FileChannel chan = raf.getChannel();

    long t0 = System.currentTimeMillis();
    
    List<MappedByteBuffer> maps = new ArrayList<MappedByteBuffer>(); 

    long off = 0;
    while (off < len)
    {
       long chunk = Math.min(len - off, Integer.MAX_VALUE);
       MappedByteBuffer map;
       map = chan.map(MapMode.READ_WRITE, off, chunk);
       off += map.capacity();
       maps.add(map);
    }
    raf.close();

    long t1 = System.currentTimeMillis();

    System.out.println("took: " + (t1 - t0) + "ms");

	}
	
}
