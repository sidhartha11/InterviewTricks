/**
 * 
 */
package com.georgecurington.functionalstudymod.io.tail;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.georgecurington.functionalstudymod.testdata.TestData;

/**
 * @author george
 *
 */
public class RecordProcessorTest {

	private static final int GROUPSIZE = 1000;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.georgecurington.functionalstudymod.io.tail.RecordProcessor#RecordProcessor(java.lang.String)}.
	 * 
	 * @throws IOException
	 */
	@Ignore
	public void testRecordProcessor() throws IOException {
		RecordProcessorIntf rp = new RecordProcessor("C:\\temp\\test.data");
		assertEquals("filename incorrect", "C:\\temp\\test.data", rp.getFilename());
	}

	/**
	 * Test method for
	 * {@link com.georgecurington.functionalstudymod.io.tail.RecordProcessor#readFile()}.
	 * 
	 * @throws IOException
	 */
	@Ignore
	public void testReadFile() throws IOException {
		RecordProcessorIntf rp = new RecordProcessor("C:\\temp\\test.data");
		assertEquals("filename incorrect", "C:\\temp\\test.data", rp.getFilename());
		rp.readFile();
	}

	/**
	 * Test method for
	 * {@link com.georgecurington.functionalstudymod.io.tail.RecordProcessor#getNextRecord()}.
	 * 
	 * @throws IOException
	 */
	@Ignore
	public void testGetNextRecord() throws IOException {
		RecordProcessorIntf rp = new RecordProcessor("C:\\temp\\test.data");
		assertEquals("filename incorrect", "C:\\temp\\test.data", rp.getFilename());

		RecordBufIntf rb = rp.getNextRecord();
		while (!rb.getRecordType().name().toLowerCase().contains("eof")) {
			assertNotNull("RecordBuff was null", rb);
			System.out.println(rb);
			Arrays.stream(rb.getRecord()).forEach(p -> {
				System.out.print((char) p);
			});
			System.out.println();
			rb = rp.getNextRecord();
		}
	}

	@Ignore
	public void testGetNextNrecords() throws IOException {
		RecordProcessorIntf rp = new RecordProcessor("C:\\temp\\test.data");
		assertEquals("filename incorrect", "C:\\temp\\test.data", rp.getFilename());

		List<RecordBufIntf> nRecords = rp.getNextNrecords(5);
		assertNotNull("getNextNrecords failure", nRecords);
		assertEquals("Incorrect Number of records", 5, nRecords.size());
		nRecords.stream().map(p -> p.getActualRecord()).forEach(p -> {
			System.out.println(Arrays.toString(p));
		});

		nRecords.stream().map(p -> p.getActualRecord()).forEach(p -> {
			Arrays.stream(p).forEach(x -> System.out.print((char) x));
			System.out.println();
			// System.out.println(Arrays.toString(p));
		});

		// RecordBufIntf rb = rp.getNextRecord();
		// while ( !rb.getRecordType().name().toLowerCase().contains("eof")) {
		// assertNotNull("RecordBuff was null", rb);
		// System.out.println(rb);
		// Arrays.stream(rb.getRecord()).forEach(p -> {
		// System.out.print((char)p);
		// });
		// System.out.println();
		// rb = rp.getNextRecord();
		// }
	}

	@Ignore
	public void testReadingFullFile() throws IOException {
		RecordProcessorIntf rp = new RecordProcessor("C:\\temp\\test.data");
		assertEquals("filename incorrect", "C:\\temp\\test.data", rp.getFilename());
		List<RecordBufIntf> nRecords = null;

		boolean morerecords = true;
		while (morerecords) {
			nRecords = rp.getNextNrecords(5);
			if (nRecords != null && nRecords.size() == 5) {
				nRecords.stream().map(p -> p.getActualRecord()).forEach(p -> {
					Arrays.stream(p).forEach(x -> System.out.print((char) x));
					System.out.println();
				});
				System.out.println("blurp");
			} else {
				System.out.println("stopping");
			    System.out.println("nRecords.size():" + nRecords.size());
				morerecords = false;
			}
		}
	}
	
	@Test
	public void testReadingFullFileUnconditional() throws IOException {
		String filename = "C:\\teeshirts\\slowfile.txt";
		ExecutorService exec = Executors.newCachedThreadPool();
		Future<Void> f = exec.submit( new Worker(filename));
//		try {
//			f.get();
//		} catch (InterruptedException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		} catch (ExecutionException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		
		try {
			System.out.println("sleeping for 5 seconds");
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//  String filename="C:\\books\\garbage\\smallfile.txt";
		//	String filename="C:\\books\\garbage\\BIGTESTFILE";
			RecordProcessorIntf rp = new RecordProcessor(filename);
			assertEquals("filename incorrect", filename, rp.getFilename());
			List<RecordBufIntf> nRecords = null;

			boolean morerecords = true;
			int cntrGroups=0;
			while (morerecords) {
				System.out.println((++cntrGroups) + ":getting group");
				nRecords = rp.getNextNrecords(GROUPSIZE);
				System.out.print(nRecords.size() + ":");
				if (nRecords != null && nRecords.size() > 0 ) {
					nRecords.stream().map(p -> p.getActualRecord()).forEach(p -> {
						Arrays.stream(p).forEach(x -> System.out.print((char) x));
						System.out.println();
					});
				} else {
					System.out.println("stopping");
				    System.out.println("nRecords.size():" + nRecords.size());
					morerecords = false;
				}
			}
			
			exec.shutdown();
			try {
				while (!exec.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
					System.out.println("waiting for Callable to stop");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * @throws IOException
	 */
	private static void slowlyWriteToAFile(String filename) throws IOException {
	//	String filename="C:\\teeshirts\\slowfile.txt";
		TestData.createTestDataSlowly(8,10000l,filename,-1,",",8,250l);
//				int strlen  /** the size of the fields **/
//				, long len   /** the number of records to create **/
//				,String file /** the name of the file to create **/
//				,int sparse  /** the number of random characters from alphabet and numbers **/
//				,String delim /** the delimiter to use **/
//				, int nmbrFields /** the number of fields **/
//				, long sleep
//				) {
	}
	
	public static class Worker implements Callable<Void> {
		private final String filename;
		public Worker(String filename) {
			this.filename = filename; 
		}
		@Override
		public Void call() throws Exception {
			slowlyWriteToAFile(filename);
			return null;
		}
		
	}
	
	
	
	
}
