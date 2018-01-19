/**
 * 
 */
package com.georgecurington.functionalstudymod.io.tail;

import java.io.IOException;
import java.util.List;

/**
 * @author george
 *
 */
public interface RecordProcessorIntf {
	RecordBufIntf getNextRecord() throws IOException;

	String getFilename();

	void readFile();

	List<RecordBufIntf> getNextNrecords(int nmbrRecords) throws IOException;
}
