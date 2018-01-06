package com.georgecurington.functionalstudymod.runningtotal1;

import java.util.Spliterators.AbstractSpliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ControllableInfiniteSequence extends AbstractSpliterator<String> {
	volatile boolean done;
	Timecntr currenttime = new Timecntr(PriceObjectImplDataGenerator.currenttime);
	private int limit;

	public ControllableInfiniteSequence() {
		super(Long.MAX_VALUE, 0);
	}

	public ControllableInfiniteSequence(int limit) {
		super(Long.MAX_VALUE, 0);
		if (limit < 0) {
			throw new IllegalAccessError("limit must be greater than zero");
		}
		this.limit = limit;
	}

	@Override
	public boolean tryAdvance(Consumer<? super String> action) {
		if (action == null) {
			throw new NullPointerException();
		}

		if (done) {
			return false;
		}

		String s = PriceObjectImplDataGenerator.generate(currenttime);
		action.accept(s);
		limit--;
		if (limit > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String... args) {
		Stream<String> stream = StreamSupport.stream(new ControllableInfiniteSequence(10), false);

		stream.map(i -> "item " + i).forEach(System.out::println);
	}
}
