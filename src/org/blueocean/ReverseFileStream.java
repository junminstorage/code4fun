package org.blueocean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * read file in backwards
 * 
 * @author junminliu
 *
 */
public class ReverseFileStream extends InputStream {

	RandomAccessFile in;
	
	long fileLength;
	long currentPosition;
	
	/**
	 * create RandomAccessor and position cursor at the end of file
	 * @param f
	 */
	ReverseFileStream(File f){
		try {
			in = new RandomAccessFile(f, "r");
			fileLength = in.length();						
			currentPosition = fileLength-1; 
			in.seek(currentPosition);
		} catch (FileNotFoundException e) {
			// TODO log error message and throw application specific ex
			e.printStackTrace();
		} catch (IOException e) {
			// TODO log error message and throw application specific ex
			e.printStackTrace();
		}
		
	}
	/**
	 * read byte in backwards 
	 */
	@Override
	public int read() throws IOException {	
		if(currentPosition == 0)
			return -1;
		in.seek(--currentPosition); 
		return in.read();
	}

}
