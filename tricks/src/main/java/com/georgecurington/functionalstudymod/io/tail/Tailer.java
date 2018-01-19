package com.georgecurington.functionalstudymod.io.tail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * Simple implementation of the unix "tail -f" functionality.
 * <p>
 * Example Usage:
 * <pre>
 *      // Simplest invocation using static helper method:
 *      TailerListener listener = ...
 *      Tailer tailer = Tailer.create(file, listener, delay);
 *      
 *      // Alternative method using executor:
 *      TailerListener listener = ...
 *      Tailer tailer = new Tailer(file, listener, delay);
 *      Executor executor ...
 *      executor.execute(tailer);
 *      
 *      // Alternative method if you want to handle the threading yourself:
 *      TailerListener listener = ...
 *      Tailer tailer = new Tailer(file, listener, delay);
 *      Thread thread = new Thread(tailer);
 *      thread.setDaemon(true); // optional
 *      thread.start();
 *      
 *      // Remember to stop the tailer when you have done with it:
 *      tailer.stop();
 * </pre>
 *
 * @version $Id: Tailer.java 1021884 2010-10-12 18:49:16Z ggregory $
 * @since Commons IO 2.0
 */
public class Tailer implements Runnable {

    /**
     * The file which will be tailed.
     */
    private final File file;

    /**
     * The amount of time to wait for the file to be updated.
     */
    private final long delay;

    /**
     * Whether to tail from the end or start of file
     */
    private final boolean end;

    /**
     * The listener to notify of events when tailing.
     */
    private final TailerListener listener;

    /**
     * The tailer will run as long as this value is true.
     */
    private volatile boolean run = true;

    /**
     * Creates a Tailer for the given file, starting from the beginning, with the default delay of 1.0s.
     * @param file The file to follow.
     * @param listener the TailerListener to use.
     */
    public Tailer(File file, TailerListener listener) {
        this(file, listener, 1000);
    }

    /**
     * Creates a Tailer for the given file, starting from the beginning.
     * @param file the file to follow.
     * @param listener the TailerListener to use.
     * @param delay the delay between checks of the file for new content in milliseconds.
     */
    public Tailer(File file, TailerListener listener, long delay) {
        this(file, listener, 1000, false);
    }

    /**
     * Creates a Tailer for the given file, with a delay other than the default 1.0s.
     * @param file the file to follow.
     * @param listener the TailerListener to use.
     * @param delay the delay between checks of the file for new content in milliseconds.
     * @param end Set to true to tail from the end of the file, false to tail from the beginning of the file.
     */
    public Tailer(File file, TailerListener listener, long delay, boolean end) {

        this.file = file;
        this.delay = delay;
        this.end = end;

        // Save and prepare the listener
        this.listener = listener;
        listener.init(this);
    }

    /**
     * Creates and starts a Tailer for the given file.
     * 
     * @param file the file to follow.
     * @param listener the TailerListener to use.
     * @param delay the delay between checks of the file for new content in milliseconds.
     * @param end Set to true to tail from the end of the file, false to tail from the beginning of the file.
     * @return The new tailer
     */
    public static Tailer create(File file, TailerListener listener, long delay, boolean end) {
        Tailer tailer = new Tailer(file, listener, delay, end);
        Thread thread = new Thread(tailer);
        thread.setDaemon(true);
        thread.start();
        return tailer;
    }

    /**
     * Creates and starts a Tailer for the given file, starting at the beginning of the file
     * 
     * @param file the file to follow.
     * @param listener the TailerListener to use.
     * @param delay the delay between checks of the file for new content in milliseconds.
     * @return The new tailer
     */
    public static Tailer create(File file, TailerListener listener, long delay) {
        return create(file, listener, delay, false);
    }

    /**
     * Creates and starts a Tailer for the given file, starting at the beginning of the file
     * with the default delay of 1.0s
     * 
     * @param file the file to follow.
     * @param listener the TailerListener to use.
     * @return The new tailer
     */
    public static Tailer create(File file, TailerListener listener) {
        return create(file, listener, 1000, false);
    }

    /**
     * Return the file.
     *
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * Return the delay.
     *
     * @return the delay
     */
    public long getDelay() {
        return delay;
    }

    /**
     * Follows changes in the file, calling the TailerListener's handle method for each new line.
     */
    public void run() {
        RandomAccessFile reader = null;
        try {
            long last = 0; // The last time the file was checked for changes
            long position = 0; // position within the file
            // Open the file
            while (run && reader == null) {
                try {
                    reader = new RandomAccessFile(file, "r");
                } catch (FileNotFoundException e) {
                    listener.fileNotFound();
                }

                if (reader == null) {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                    }
                } else {
                    // The current position in the file
                    position = end ? file.length() : 0;
                    last = System.currentTimeMillis();
                    reader.seek(position);                    
                }
            }


            while (run) {

                // Check the file length to see if it was rotated
                long length = file.length();

                if (length < position) {

                    // File was rotated
                    listener.fileRotated();

                    // Reopen the reader after rotation
                    try {
                        // Ensure that the old file is closed iff we re-open it successfully
                        RandomAccessFile save = reader;
                        reader = new RandomAccessFile(file, "r");
                        position = 0;
                        // close old file explicitly rather than relying on GC picking up previous RAF
                        IOUtils.closeQuietly(save);
                    } catch (FileNotFoundException e) {
                        // in this case we continue to use the previous reader and position values
                        listener.fileNotFound();
                    }
                    continue;
                } else {

                    // File was not rotated

                    // See if the file needs to be read again
                    if (length > position) {

                        // The file has more content than it did last time
                        last = System.currentTimeMillis();
                        position = readLines(reader);

                    } else if (FileUtils.isFileNewer(file, last)) {

                        /* This can happen if the file is truncated or overwritten
                         * with the exact same length of information. In cases like
                         * this, the file position needs to be reset
                         */
                        position = 0;
                        reader.seek(position); // cannot be null here

                        // Now we can read new lines
                        last = System.currentTimeMillis();
                        position = readLines(reader);
                    }
                }
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                }
            }

        } catch (Exception e) {

            listener.handle(e);

        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    /**
     * Allows the tailer to complete its current loop and return.
     */
    public void stop() {
        this.run = false;
    }

    /**
     * Read new lines.
     *
     * @param reader The file to read
     * @return The new position after the lines have been read
     * @throws java.io.IOException if an I/O error occurs.
     */
    private long readLines(RandomAccessFile reader) throws IOException {
        String line = reader.readLine();
        while (line != null) {
            listener.handle(line);
            line = reader.readLine();
        }
        return reader.getFilePointer();
    }
    
    public static void main(String...args ){
    	 TailerListener listener = new TailerListener(){

			@Override
			public void init(Tailer tailer) {
				System.out.println("init called");
				
			}

			@Override
			public void fileNotFound() {
				System.out.println("FILE NOT FOUND");
				
			}

			@Override
			public void fileRotated() {
				System.out.println("file rotated");
				
			}

			@Override
			public void handle(String line) {
				System.out.println(line);
				
			}

			@Override
			public void handle(Exception ex) {
				System.out.println("exception:" + ex);
				
			}
    		 
    	 };
         Tailer tailer = Tailer.create(new File("C:\\books\\garbage\\x"), listener, 1000);
         tailer.run();
    }
    	

}