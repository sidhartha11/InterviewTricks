/**
 * 
 */
package com.georgecurington.j8ia.chap5.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Comparator.*;
import com.georgecurington.j8ia.chap4.Dish;
import com.georgecurington.j8ia.chap5.Trader;
import com.georgecurington.j8ia.chap5.Transaction;

import static com.georgecurington.j8ia.chap4.Dish.*;

import static com.georgecurington.j8ia.Utilities.*;

/**
 * @author Owner
 *
 */
public class Main {
	Trader raoul = new Trader("Raoul", "Cambridge");
	Trader mario = new Trader("Mario", "Milan");
	Trader alan = new Trader("Alan", "Cambridge");
	Trader brian = new Trader("Brian", "Cambridge");

	List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400), new Transaction(mario, 2012, 710),
			new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

	/**
	 * 
	 */
	public Main() {
		iterate_5();
		p("====");
		range_2();
		p("====");
		range_2fib();
	}

	private void iterate_5() {
		IntSupplier fib = new IntSupplier() {
			private int previous = 0;
			private int current = 1;

			public int getAsInt() {
				int oldPrevious = this.previous;
				int nextValue = this.previous + this.current;
				this.previous = this.current;
				this.current = nextValue;
				return oldPrevious;
			}
		};
		IntStream.generate(fib).limit(10).forEach(System.out::println);

	}

	private void iterate_4() {
		IntStream ones = IntStream.generate(() -> 1);
		ones.limit(10).forEach(System.out::println);

		IntStream twos = IntStream.generate(new IntSupplier() {
			public int getAsInt() {
				return 2;
			}
		});
		twos.limit(10).forEach(System.out::println);

	}

	private void iterate_3() {
		Stream.generate(Math::random).limit(20).forEach(System.out::println);

	}

	private void iterate_2() {
		Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1] }).limit(20)
				.forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

	}

	private void iterate_1() {
		Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

	}

	private void stream_2() {
		long uniqueWords = 0;
		try (
				/**
				 * Files.lines method reads the content of the file lazily as they are consumed.
				 */
				Stream<String> lines = Files.lines(Paths.get("C:\\temp\\files.txt"), Charset.defaultCharset())) {
			/**
			 * you use flatMap to produce one flattened stream of words instead of multiple
			 * streams of words for each line.
			 */
			uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
			System.out.println("count is " + uniqueWords);
		} catch (IOException e) {
			p("exception:" + e);
		}

	}

	private void stream_1() {
		Stream<String> stream = Stream.of("java 8 ", "Lambdas ", "In ", "Action");
		stream.map(String::toUpperCase).forEach(System.out::println);

		/** get an empty Stream **/
		Stream<String> emptyStream = Stream.empty();
		List<String> l = emptyStream.collect(toList());
		p("======");
		l.forEach(System.out::println);

		/** convert primitive Array to Stream **/
		int[] numbers = { 2, 3, 5, 7, 11, 13 };
		int sum = Arrays.stream(numbers).sum();
		p("sum = " + sum);
	}

	private void range_2() {

		/**
		 * Need to end up with a Stream of int arrays
		 */
		IntStream.of(1, 2, 3, 5, 8, 13, 21, 34);
		Stream<int[]> pythagoreanTriples =
				/**
				 * Using IntStream to create a range of inclusive range of primitive int values;
				 * hence to proceed as a Stream of reference types we need to box it
				 */
				IntStream.rangeClosed(1, 100).boxed()
						/**
						 * we map the boxed element to another Stream of of values ranging from the
						 * input boxed Stream Object to the limit ( 100 ). Since it is being mapped to
						 * another Stream we need to use flatMap to concatenate all values to a single
						 * stream.
						 */
						.flatMap(a -> IntStream.rangeClosed(a, 100)
								/** filter for only valid square sums of the two **/
								.filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
								/**
								 * In order to map the primitive int value to an array we have to use the
								 * mapToObj funtion of IntStream. The normal map of Intstream can only map to
								 * primitive int values.
								 */
								.mapToObj(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) }));

		/** to display a few triples we use the limit function **/
		pythagoreanTriples.limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
	}

	private void range_2fib() {

		/**
		 * Need to end up with a Stream of int arrays
		 */
		int [] v = {1, 2, 3, 5, 8, 13, 21, 34};
		Stream<int[]> pythagoreanTriples =
				/**
				 * Using IntStream to create a range of inclusive range of primitive int values;
				 * hence to proceed as a Stream of reference types we need to box it
				 */
				IntStream.rangeClosed(0, 7).boxed()
						/**
						 * we map the boxed element to another Stream of of values ranging from the
						 * input boxed Stream Object to the limit ( 100 ). Since it is being mapped to
						 * another Stream we need to use flatMap to concatenate all values to a single
						 * stream.
						 */
						.flatMap(a -> IntStream.rangeClosed(a, 7)
								/** filter for only valid square sums of the two **/
								.filter(b -> Math.sqrt(v[a] * v[a] + v[b] * v[b]) % 1 == 0)
								/**
								 * In order to map the primitive int value to an array we have to use the
								 * mapToObj funtion of IntStream. The normal map of Intstream can only map to
								 * primitive int values.
								 */
								.mapToObj(b -> new int[] { a, b, (int) Math.sqrt(v[a] * v[a] + v[b] * v[b]) }));

		/** to display a few triples we use the limit function **/
		pythagoreanTriples.limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
	}

	private void range_1() {
		/**
		 * Notes: When dealing with the primitive ( confusing ) specialization streams ,
		 * everything must be specialized until you convert it back to an Object Stream
		 * .. ---- THIS IS THE DOWNFALL OF JAVA -----> the boxed BS.
		 */
		IntPredicate even = (p) -> p % 2 == 0;
		IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(even);
		evenNumbers.forEach(System.out::println);

	}

	private void numeric_3() {
		/**
		 * Dealing with Optional values with primitive streams Note that Number is an
		 * Abstract class ... cannot really do anything with it directly except goofy
		 * Object Oriented legacy Java stuff.
		 */
		List<Integer> n = new ArrayList<>();
		OptionalInt x = n.stream().mapToInt(p -> (p * p)).max();
		OptionalInt maxCal = menu.stream().mapToInt(Dish::getCalories).max();
		p(maxCal.getAsInt());
		p(maxCal.orElse(-1));
		p(x.orElse(Integer.MAX_VALUE));

	}

	private void numeric_2() {
		/**
		 * Converting to an IntStream and back to a normal Stream
		 */

		IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
		/**
		 * Convert back to a boxed Integer Stream
		 */
		Stream<Integer> stream = intStream.boxed();
		Optional<Integer> s = stream.reduce(Integer::sum);
		double i = s.map(p -> Math.pow(p, 2)).get();
		p("double = " + i);

	}

	private void numeric_1() {
		/**
		 * calculate the number of calories in all menues
		 */
		int calories = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
		p("total calories in all menus:" + calories);

		/**
		 * Can you call sum directory instead of incurring the box/unbox cost? note: map
		 * generates a Stream<Integer> which does not have a function for summing up the
		 * values.
		 */
		// calories = menu.stream().map(Dish::getCalories).sum();
		/**
		 * Specialized Stream usage
		 */
		calories = menu.stream().mapToInt(Dish::getCalories).sum();
		p("calories:" + calories);

	}

	private void traders_additional() {
		Optional<Transaction> smallestTransaction = transactions.stream().min(comparing(Transaction::getValue));
		if (smallestTransaction.isPresent())
			p("smallest is " + smallestTransaction.get());

	}

	private void traders_8() {
		/**
		 * Find the transaction with the smallest value.
		 */
		int max = transactions.stream().map(Transaction::getValue).reduce(Integer::min).orElse(Integer.MAX_VALUE);
		p("max = " + max);
	}

	private void traders_7() {
		/**
		 * What’s the highest value of all the transactions?
		 */
		int max = transactions.stream().map(Transaction::getValue).reduce(Integer::max).orElse(-1000);
		p("max = " + max);

	}

	private void traders_6() {
		/**
		 * Print all transactions’ values from the traders living in Cambridge.
		 */
		Predicate<Transaction> inCambridge = p -> "Cambridge".equals(p.getTrader().getCity());
		transactions.stream().filter(inCambridge).map(Transaction::getValue).forEach(System.out::println);

	}

	private void traders_5() {
		/**
		 * Are any traders based in Milan?
		 */
		Optional<Trader> t = transactions.stream().map(Transaction::getTrader).filter(p -> "Milan".equals(p.getCity()))
				.findAny();
		if (t.isPresent())
			p("there are traders in Minlan");

	}

	private void traders_4() {
		/**
		 * Return a string of all traders’ names sorted alphabetically.
		 */
		String names = transactions.stream().map(n -> n.getTrader().getName()).distinct()
				.sorted(Comparator.naturalOrder()).reduce("", (a, b) -> a + ":" + b);
		p("names:" + names);

	}

	private void traders_3() {
		/**
		 * Find all traders from Cambridge and sort them by name.
		 */
		Predicate<Trader> fromCambridge = t -> "Cambridge".equals(t.getCity());
		List<Trader> l = transactions.stream().map(Transaction::getTrader).filter(fromCambridge)
				.sorted(Comparator.comparing(Trader::getName)).collect(toList());
		l.forEach(System.out::println);

	}

	private void traders_2() {
		/**
		 * What are all the unique cities where the traders work?
		 */
		List<String> cities = transactions.stream().map(Transaction::getTrader).map(Trader::getCity).distinct()
				.collect(toList());
		cities.forEach(System.out::println);

	}

	private void traders_1() {
		/**
		 * Find all transactions in the year 2011 and sort them by value (small to
		 * high).
		 */
		Predicate<Transaction> yr2011 = tran -> tran.getYear() == 2011;
		List<Transaction> l = transactions.stream().filter(yr2011).sorted(Comparator.comparing(Transaction::getValue))
				.collect(toList());
//		l.forEach(System.out::println);

		List<Transaction> l_reverse = transactions.stream().filter(yr2011)
				.sorted(Comparator.comparing(Transaction::getValue).reversed()).collect(toList());

		l.forEach(System.out::println);

		l_reverse.forEach(System.out::println);

	}

	private void fold_3() {

		List<Integer> numbers = Arrays.asList(1, 5, 4, 8, 0, 4, -1, 5);
		int min = numbers.stream().reduce((a, b) -> (a < b) ? a : b).orElse(-99);
		p("min:" + min);
		min = numbers.stream().reduce(Integer::max).get();
		p("max=" + min);

		int nmbrdishes = menu.stream().map(p -> 1).reduce(0, Integer::sum);
		p("nmbrdishes:" + nmbrdishes);

		nmbrdishes = (int) menu.stream().count();
		p("nmbrdishes:" + nmbrdishes);

		long sum = numbers.parallelStream().count();

	}

	private void fold_2() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

		Optional<Integer> sum = numbers.stream().reduce(Main::mySum);
		System.out.println("sum = " + sum);

		List<Integer> numb = new ArrayList<>();
		sum = numb.stream().reduce(Main::mySum);
		sum.ifPresent(System.out::println);

	}

	static int mySum(int a, int b) {
		return a + b;
	}

	@FunctionalInterface
	interface MyBiFunc<A, B, R> {
		R get(A a, B b);
	}

	private void fold_1() {
		/** simple addition loop **/
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		int sum = 0;
		for (int x : numbers) {
			sum += x;
		}
		p(sum);
		int[] intNumbers = { 1, 2, 3, 4, 5 };
		for (int x : intNumbers) {
			sum += x;
		}
		p(sum);

		/** using reduce **/
		/**
		 * Simple for of reduce: reduce(initialvalue , biFunction)
		 */
		sum = numbers.stream().reduce(0, (a, b) -> a + b);
		p("sum=" + sum);

		int mult = numbers.stream().reduce(1, (a, b) -> a * b);
		p("mult=" + mult);

		/**
		 * Using MyBiFunc
		 */
		int sum2 = numbers.stream().reduce(1, Main::mySum);
		System.out.println("sum2=" + sum2);

	}

	private void shortc_2() {
		List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 9);
		Optional<Integer> o = ints.stream().filter(p -> ((p * p) % 3) == 0).findFirst();
		o.ifPresent(System.out::println);

	}
//    this.name = name;
//    this.vegetarian = vegetarian;
//    this.calories = calories;
//    this.type = type;

	private void shortc_1() {
		if (menu.stream().anyMatch(Dish::isVegetarian)) {
			p("is is vegetarian");
		}
		boolean isHealthy = menu.stream().allMatch(Dish::isLessThousand);
		if (isHealthy)
			p("a healthy menu");

		boolean healthy = menu.stream().noneMatch(Dish::isGtrEqThousand);
		if (healthy)
			p("a healthy menu");

		Optional<Dish> firstVeggie = menu.stream().filter(Dish::isVegetarian).findAny();
		Dish alternate = menu.stream().filter(Dish::isGtrEqThousand).findAny()
				.orElseGet(() -> new Dish("PigEar", false, 2002, Type.MEAT));
		p("firstVeggie.isPresent()=" + firstVeggie.isPresent());
		String s = firstVeggie.map(p -> "we got a veggie dish:" + p).get();
		p("s=" + s);
		p("alternate = " + alternate);
		menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(p -> System.out.println(p));

	}

	private void map_6() {
		List<Integer> numbers1 = Arrays.asList(1, 2, 3);
		List<Integer> numbers2 = Arrays.asList(3, 4);
		numbers1.stream().forEach(System.out::println);
		p("=====");
		List<int[]> pairs = numbers1.stream().flatMap(i -> {
			System.out.println("i = " + i);
			return numbers2.stream().map(j -> {
				System.out.println("j = " + j + " , i = " + i);
				return new int[] { i, j };
			});
		}).collect(toList());

	}

	private void map_5() {
		List<Integer> numbers1 = Arrays.asList(1, 2, 3);
		List<Integer> numbers2 = Arrays.asList(3, 4);
		/**
		 * numbers1.stream generates a list of Stream Objects of type Integer
		 * numbers2.stream generates a list of Stream Objects of type Integer also.
		 * flatMap allows you to concatenate one Stream Object with other Stream
		 * Objects. So ... when Stream Object 1 is mapped to all the Stream Objects from
		 * numbers2 we get 1 , 3 , 4 as a streaming list of objects then 2 , 3 , 4 3 , 3
		 * , 4
		 * 
		 */
		List<int[]> pairs = numbers1.stream()
				.flatMap(i -> numbers2.stream().filter(j -> (j + i) % 3 == 0).map(j -> new int[] { i, j }))
				.collect(toList());
		pairs.stream().forEach(a -> System.out.println(Arrays.toString(a)));

	}

	private void map_4() {
		List<Integer> i = Arrays.asList(1, 2, 3, 4, 5);

		List<Integer> i2 = i.stream().map(p -> p * p).collect(toList());
		i2.forEach(System.out::println);
	}

	private void map_3() {
		/**
		 * Attemp #1
		 */
		List<String[]> s = menu.stream().map(Dish::getName).map(p -> p.split("")).distinct().collect(toList());

		s.forEach(System.out::println);

		/**
		 * Attempt #2
		 */
		p("=========");
		menu.stream().map(Dish::getName).map(p -> p.split("")).map(p -> Arrays.stream(p)).distinct()
				.forEach(System.out::println);
//		.collect(toList());

		/**
		 * Attempt #3
		 */
		p("======");
		List<String> s2 = menu.stream().map(Dish::getName).map(p -> p.split("")).flatMap(p -> Arrays.stream(p))
				.distinct().collect(toList());
		s2.forEach(System.out::println);

	}

	private void map_2() {
		Function<String, Integer> f = s -> s.length();
		menu.stream().map(Dish::getName).peek(System.out::println)
				/**
				 * Method Reference Function<String,Integer>
				 */
				.map(String::length)

				.forEach(System.out::println);

	}

	private void map_1() {
		Function<Dish, String> f = p -> p.getName();
		Function<Dish, String> f2 = Dish::getName;

		menu.stream()
				/**
				 * getName is a function that returns a String of the Type Dish. In this case
				 * the Functional interface is defined as: Function<Dish, String> = (p) ->
				 * p.getName
				 */
				.map(Dish::getName).forEach(System.out::println);

		menu.stream().map(f).forEach(System.out::println);
	}

	private void test_5() {
		menu.stream().filter(p -> p.getType() == Type.MEAT).limit(2).forEach(System.out::println);

	}

	private void test_4() {
		menu.stream().filter(p -> p.getCalories() > 300).skip(2).collect(toList()).forEach(System.out::println);

	}

	private void test_3() {
		List<Dish> dishes = menu.stream().filter(d -> d.getCalories() > 300).limit(3).collect(toList());
		p(dishes);
	}

	private void test_2() {
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 2, 4);
		numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);

	}

	private void test_1() {
		List<Dish> vegetarianDishes = new ArrayList<>();
		for (Dish d : menu) {
			if (d.isVegetarian()) {
				vegetarianDishes.add(d);
			}
		}
		p(vegetarianDishes.toString());

		/** USING STREAMS **/
		List<Dish> v = menu.stream().filter(Dish::isVegetarian).collect(toList());
		p(v);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();

	}

}
