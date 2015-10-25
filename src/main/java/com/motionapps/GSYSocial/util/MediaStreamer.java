package com.motionapps.GSYSocial.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

public class MediaStreamer implements StreamingOutput{
	  private int length;
	    private RandomAccessFile raf;
	    final byte[] buf = new byte[4096];

	    public MediaStreamer(int length, RandomAccessFile raf) {
	        this.length = length;
	        this.raf = raf;
	    }
	    
	    public void write(OutputStream outputStream) throws IOException, WebApplicationException {
        	System.out.println("StreamingOutput-2");

	    	try {
	            while( length != 0) {
	                int read = raf.read(buf, 0, buf.length > length ? length : buf.length);
	                outputStream.write(buf, 0, read);
	                length -= read;
	            }
	        } finally {
	            raf.close();
	        	outputStream.close();
	        }
	    }

	    public int getLenth() {
	        return length;
	    }

}
