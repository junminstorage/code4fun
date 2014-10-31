package org.blueocean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class StringList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient int size = 0;
	private transient Entry head = null;
	
	private static class Entry{
		String data;
		Entry next;
		Entry previous;	
	}
	
	private void writeObject(ObjectOutputStream os) throws IOException{
		os.defaultWriteObject();
		os.writeInt(size);
		Entry current = head;
		while(current!=null){
			os.writeObject(current.data);
			current = head.next;
		}		
	}
	
	private void readObject(ObjectInputStream is) throws ClassNotFoundException, IOException {
		is.defaultReadObject();
		this.size = is.readInt();
		this.head = (Entry) is.readObject();
		int i = this.size;
		Entry pre = this.head;
		while(i-->0){
			Entry v = (Entry) is.readObject();
			pre.next = v;
			v.previous = pre;
			pre = v;
		}
	}

}
