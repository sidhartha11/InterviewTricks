/**
 * 
 */
package com.georgecurington.functionalstudymod.niov2;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre>
 * <p><b>INTERVIEW TRICKS</b></p>
 * </pre>
 * <p>
 * ================================================
 * </p>
 * <br>
 * 
 * @author George Curington
 * @version 1.0.0
 * @since Feb 21, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class WatchServiceExample {

	/**
	 * 
	 */
	public WatchServiceExample() {
		WatchService watchService = null;
		try {
			/** create a watchService **/
			watchService = createWatchService();

			/** register a path to monitor **/
			registerWatchService(watchService, "C:/rafaelnadal");

			/** pollReturnImmediate **/
			blockingTakeCall(watchService);
		} finally {
			/** close the watchService **/
			try {
				if (watchService != null)
					watchService.close();
			} catch (IOException e) {
				Utility.p("err close:" + e);
				e.printStackTrace();
			}
		}
	}

	private void blockingTakeCall(WatchService watchService) {
		while (true) {
			WatchKey key = null;
			Utility.p("calling blocking watch");
			try {
				key = watchService.take();
			} catch (InterruptedException e) {
				Utility.p("err:" + e);
				e.printStackTrace();
			}
			checkPolledEvents(watchService, key);
			
		}
		
	}

	private void checkPolledEvents(WatchService watchService, WatchKey key) {
		if ( key == null ) {
			Utility.p("key is null");
			return;
		}
		Utility.p("events");
		key.pollEvents().stream().forEach(p -> {
			Utility.p("inspecting event");
			Utility.p(p.count() + ","  + p.kind());
			
		});
		
	}

	private void pollReturnTimed(WatchService watchService) {
		while (true) {
			WatchKey key=null; 
			try {
				key = watchService.poll(2, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				Utility.p("err:" + e);
				e.printStackTrace();
			}
			if (key != null) {

				Utility.p("watching key=" + key.toString());

			} else {
				Utility.p("watcting 2 second");
			}
		}
		
	}

	private void pollReturnImmediate(WatchService watchService) {
		/** infinite loop returning immediately **/
		while (true) {
			final WatchKey key = watchService.poll();
			if (key != null) {

				Utility.p("watching key=" + key.toString());

			} else {
				Utility.p("watcting 1 second");
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				Utility.p("err:" + e);
				e.printStackTrace();
			}
		}

	}

	private void registerWatchService(WatchService watchService, String pathToWatch) {

		final Path path = Paths.get(pathToWatch);
		/** create a watch service to monitor a path **/
		try {
			/** register a path variable with this watchservice **/
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY,
					StandardWatchEventKinds.ENTRY_DELETE);
			Utility.p("created and registered watchService");
		} catch (IOException e) {
			Utility.p("err:" + e);
		}
	}

	private WatchService createWatchService() {
		WatchService watchService = null;
		try {
			watchService = FileSystems.getDefault().newWatchService();
			Utility.p("created watchService:" + watchService.toString());
		} catch (IOException e) {
			Utility.p("err:" + e);
			e.printStackTrace();
		}
		return watchService;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WatchServiceExample watchServiceExample = new WatchServiceExample();

	}

}
