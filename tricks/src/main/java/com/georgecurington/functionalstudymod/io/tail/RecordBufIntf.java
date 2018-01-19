/**
 * 
 */
package com.georgecurington.functionalstudymod.io.tail;

/**
 * @author george
 *
 */
public interface RecordBufIntf {
int[] getRecord();
RecordType getRecordType();
int size();
int[] getActualRecord();
}
