/**
 * 
 */
package com.georgecurington.functionalstudymod.io.tail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author george
 *
 */
public class RecordProcessor implements RecordProcessorIntf {

	private static final int RECORDMAXSIZE=1024 * 4;
	private int[] inputbuffer = new int[RECORDMAXSIZE];
	static final int EOF = -1;
	private static final int NEWLINE = -2;
	private static final int LF = 10;
	private static final int CR = 13;
	private static final long WAITINGFORDATATIME = 5000l;
	private final String filename;
	private FileInputStream inputStream;
	private int eofIndicator;

	/**
	 * @throws IOException
	 * 
	 */
	public RecordProcessor(String filename) throws IOException {
		this.filename = filename;
		openFile(filename);
	}

	protected void openFile(String inputFile) throws IOException {
		eofIndicator = 0;
		inputStream = new FileInputStream(inputFile);
	}

	@Override
	public void readFile() {
		int byteRead;
		try {
			while ((byteRead = inputStream.read()) != -1) {
				System.out.println(byteRead);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * This returns the next byte of data. If the data is a newline we should
	 * have this in windows. (TBD test linux) the following ascii sequence for
	 * Windows: 13 a carriage return 10 a line feed
	 * 
	 * @return
	 * @throws IOException
	 */
	public int readByte() throws IOException {
		int byteRead = EOF;
//		if ( inputStream.available() == 0 && eofIndicator != EOF  ) {
//			while (inputStream.available() == 0 ){
//				System.out.println("waiting for data");
//				try {
//					Thread.sleep(WAITINGFORDATATIME);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
		if (eofIndicator != EOF) {
			byteRead = inputStream.read();
			if (byteRead == EOF) {
				inputStream.close();
				eofIndicator = EOF;
				return byteRead;
			} else {
				if (byteRead == CR) {
					inputStream.mark(1);
					int next = inputStream.read();
					if (next == LF) {
						return NEWLINE;
					} else {
						inputStream.reset();
						return byteRead;
					}
				} else {
					return byteRead;
				}
			}
		}
		return byteRead;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.georgecurington.functionalstudymod.io.tail.RecordProcessorIntf#
	 * getNextRecord()
	 */
	@Override
	public RecordBufIntf getNextRecord() throws IOException {
		int cntr=0;
		int byteRead=0;
		boolean readingrecord = true;
		while ( readingrecord ){
			while ( (byteRead = readByte()) != NEWLINE && byteRead != EOF && ++cntr < RECORDMAXSIZE ) {
				inputbuffer[cntr-1] = byteRead;
			}
			readingrecord = false;
		}
		/** determine the type of record **/
		RecordType rt = RecordType.undetermined;
		if ( byteRead == NEWLINE ) {
			rt = RecordType.typeNormal;
		} else if ( byteRead == EOF ){
			if ( cntr == 0 )
			rt = RecordType.typeEofEmpty;
			else 
				rt = RecordType.typeEofFull;
		} else {
			/** overflow **/
			rt = RecordType.overflow;
		}
		RecordBufIntf rb = new RecordBuf(cntr,rt,inputbuffer);
		return rb;
	}

	@Override
	public String getFilename() {
		// TODO Auto-generated method stub
		return filename;
	}

	@Override
	public List<RecordBufIntf> getNextNrecords(int nmbrRecords) throws IOException {
		List<RecordBufIntf> list = new ArrayList<>();
		RecordBufIntf rb = getNextRecord();
		int cntr=0;
		while ( !rb.getRecordType().name().toLowerCase().contains("eof") && ++cntr <= nmbrRecords) {
			list.add(rb);
			if ( cntr < nmbrRecords ) {
			    rb = getNextRecord();
			}
		}
		if ( cntr <= nmbrRecords ){
			/** determine the type of record **/
			if ( rb.getRecordType() == RecordType.typeEofFull ) {
				list.add(rb);
			}
		}
		return list;
	}

}
