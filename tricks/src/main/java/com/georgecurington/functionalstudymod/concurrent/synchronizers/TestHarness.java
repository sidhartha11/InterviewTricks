package com.georgecurington.functionalstudymod.concurrent.synchronizers;

/**
 * @author george
 *
 */
public interface TestHarness {
	public static final long SLEEP_GRANULARITY = 5000;
	/** Task To execute a consumer function **/
	CustomFunction<Buffer<String>, Void> consumerFunction = buffer -> {
		int cntr = 0;
		while (true) {
			p(" " + (++cntr) + ":trying to take");
			String item = null;
			try {
				item = buffer.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					Thread.sleep(SLEEP_GRANULARITY);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			p(" took:" + item);
			if (cntr > 10)
				break;
		}
		p("out of loop");
		return null;
	};

	/** task to execute a producer **/
	CustomFunction<Buffer<String>, Void> producerFunction = buffer -> {
		int cntr = 0;
		while (true) {
			p(" " + (++cntr) + ":trying to put testString");
			try {
				buffer.put("testString");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					Thread.sleep(SLEEP_GRANULARITY);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			p(" put testString");
			if (cntr > 10)
				break;
		}
		p(" out of loop");
		return null;
	};

	public static void p(String msg) {
		System.out.println(Thread.currentThread().getName() + msg);
	}
}