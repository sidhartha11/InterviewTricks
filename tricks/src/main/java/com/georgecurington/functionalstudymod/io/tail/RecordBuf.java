/**
 * 
 */
package com.georgecurington.functionalstudymod.io.tail;

import java.util.Arrays;

/**
 * @author george
 *
 */
public class RecordBuf implements RecordBufIntf {
	private final int size;
	private final RecordType recordType;

	/**
	 * @param size
	 * @param recordType
	 * @param record
	 */
	public RecordBuf(int size, RecordType recordType, int[] record) {
		super();
		this.size = size;
		this.recordType = recordType;
		this.record = record;
		actualRecord = new int[0];
		if ( size > 0 ) {
			actualRecord = new int[size];
			actualRecord=Arrays.copyOf(record, size);
		}
	}
	private final int[] record;
	private int[] actualRecord;
	@Override
	public int[] getActualRecord(){
		return actualRecord;
	}
	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.io.tail.RecordBufIntf#getRecord()
	 */
	@Override
	public int[] getRecord() {
		// TODO Auto-generated method stub
		return record;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.io.tail.RecordBufIntf#getRecordType()
	 */
	@Override
	public RecordType getRecordType() {
		// TODO Auto-generated method stub
		return recordType;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.io.tail.RecordBufIntf#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RecordBuf [size=" + size + ", recordType=" + recordType + ", record=" + Arrays.toString(record)
				+ ", actualRecord=" + Arrays.toString(actualRecord) + "]";
	}

}
