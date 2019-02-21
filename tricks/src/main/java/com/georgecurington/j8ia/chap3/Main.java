/**
 * 
 */
package com.georgecurington.j8ia.chap3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import com.georgecurington.j8ia.chap3.Lambdas.Apple;

/**
 * @author Owner
 *
 */
public class Main {
	volatile int portNumber = 1337;
	@FunctionalInterface 
	interface MyRun {
		void run();
	}
	/**
	 * 
	 * Note the Adder(s) below. If the function signature had been 
	 * different here, SmartAdder would not have been 
	 * considered a FunctionalInterface because it would have contained 
	 * two abstract methods. 
	 *
	 */
	@FunctionalInterface
	public interface Adder{
	    int add(int a, int b);
	}
	@FunctionalInterface
	public interface SmartAdder extends Adder{
	    int add(int a, int b);
	}
	public interface Nothing{
	}
	
	@FunctionalInterface 
	public interface BufferedReaderProcessor {
		String process(BufferedReader b) throws IOException;
	}
	
	static class Fruit {
		private String color;
		private Integer what;
		public Fruit(String color,Integer what) {
			System.out.println(String.format("constructing Fruit with %s and %d",color,what));
			this.color = color;
			this.what = what ;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public Integer getWhat() {
			return what;
		}
		public void setWhat(Integer what) {
			this.what = what;
		}
		@Override
		public String toString() {
			return "Fruit [color=" + color + "]";
		}
	}
	
	static class Apple1 extends Fruit {
		public Apple1(Integer what) {
			super("red",what);
		}
	}
	
	static class Orange extends Fruit {
		public Orange(Integer what) {
			super("orange",what);
		}
	}
	
	private static List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));

	private static Map<String , Function<Integer, Fruit>> map = new HashMap<>();
	
	
	static {
		map.put("apple", Apple1::new);
		map.put("orange", Orange::new);
	}
	
	/**
	 * 
	 */
	public Main() {
		quiz3_7();
	}
	@FunctionalInterface
	interface TriFunction<T,U,V,R> {
		R apply(T t, U u, V v);	
	}
	
	static class Color {
		int r;
		int g;
		int b;
		public Color(int r , int g , int b) {
			this.r = r ; this.g= g ; this.b = b;
		}
		public int getR() {
			return r;
		}
		public void setR(int r) {
			this.r = r;
		}
		public int getG() {
			return g;
		}
		public void setG(int g) {
			this.g = g;
		}
		public int getB() {
			return b;
		}
		public void setB(int b) {
			this.b = b;
		}
		@Override
		public String toString() {
			return "Color [r=" + r + ", g=" + g + ", b=" + b + "]";
		}
	}
	static TriFunction<Integer,Integer,Integer,Color> rgb1() {
		 int r = ThreadLocalRandom.current().nextInt(0,256);
		 int g = ThreadLocalRandom.current().nextInt(0,256);
		 int b = ThreadLocalRandom.current().nextInt(0,256);
		 return (x,y,z) -> new Color(r,g,b);
	}
	
	static Color rgb(TriFunction<Integer,Integer,Integer,Color> c) {
		 int r = ThreadLocalRandom.current().nextInt(0,256);
		 int g = ThreadLocalRandom.current().nextInt(0,256);
		 int b = ThreadLocalRandom.current().nextInt(0,256);
		 return c.apply(r, g, b);
	}
	
	private void quiz3_7() {
		TriFunction<Integer,Integer,Integer,Color> factory = Color::new;
		List<Color> colors = new ArrayList<>();
		IntStream.rangeClosed(1,10).forEach(i -> {
			colors.add(rgb(factory));
		});
		System.out.println(colors);
		
	}

	public static Fruit giveMeFruit(String fruit, Integer weight) {
		return map.get(fruit.toLowerCase()).apply(weight);
	}
	private void example_example_13() {
		
		map.forEach((a,b) -> System.out.println("a = " + a + "\nb=" + b));
		
		System.out.println(giveMeFruit("orange",150));
		System.out.println(giveMeFruit("apple",300));
		
	}
	static Apple constructorRef1(Supplier<Apple> c) {
		return c.get();
	}
	static Apple constructorRef2(BiFunction<Integer,String,Apple> c) {
		return c.apply(100, "red");
	}
	private void example_example_12() {
		Supplier<Apple> c1 = () -> new Apple();
		Supplier<Apple> c2 = Apple::new;
		Apple a = constructorRef1(c2);
		Apple a2 = constructorRef1(c1);
		
		BiFunction<Integer, String,Apple> f2 = (n,s) -> new Apple(n,s);
		BiFunction<Integer,String,Apple> f3 = Apple::new;
		
		Apple a3 = constructorRef2(f2);
		Apple a4 = constructorRef2(f3);
		
	}
	private void example_example_11() {
		List<String> str = Arrays.asList("a", "b" , "A" , "B");
		str.sort((s1 , s2) -> s1.compareToIgnoreCase(s2));
		Collections.shuffle(str);
		str.sort(String::compareToIgnoreCase);
		
	}
	/**
	 * A method reference to an instance method of an existing object (
	 * for example, suppose you have a loal variable expensiveTransation
	 * that holds an object of type Transaction, which supports an instance 
	 * method getValue; you can write expensiveTransaction::getValue)
	 */
	static class Transaction {
		private final int me;
		public Transaction(int me) {
			this.me = me;
		}
		Transaction getValue() {
			return this;
		}
		public int getMe() {
			return me;
		}
		@Override
		public String toString() {
			return "Transaction [me=" + me + "]";
		}
	}
	static Transaction getRefFunc3(Transaction t, Supplier<Transaction> s) {
		return s.get();
	}
	private void example_methodRef_3() {
		Transaction t = new Transaction(2);
		Supplier <Transaction> s = () -> t.getValue();
		Supplier <Transaction> s2 = t::getValue;
		System.out.println(getRefFunc3(t,s));
		System.out.println(getRefFunc3(t,s2));
		
	}
	/**
	 * A method reference to an instance method of an arbitrary type(
	 * for example, the method length of a String , written String::length_
	 */
	private static Integer useRefFunc2(Function<String, Integer> f) {
		return f.apply("George Curington");
	}
	private void example_methodRef_2() {
		Function<String, Integer> f = String::length;
		Function<String, Integer> f2 = s -> s.length();
		Integer i = useRefFunc2(f);
		System.out.println("i =" + i);
		i = useRefFunc2(f2);
		
	}
	/**
	 * 1. A method reference to a static method ( for example, the method 
	 * parseInt of Integer, written Integer::parseInt )
	 * 
	 */
	private static void useRefFunc(Function <String,Integer> f) {
		System.out.println(f.apply("200"));
	}
	private void example_methodRef_1() {
		
	Function<String, Integer> tointeger = 
			 Integer::parseInt ;
	Function <String, Integer> usingRef = (s) -> Integer.parseInt(s);
		
	useRefFunc(tointeger);
	useRefFunc(usingRef);
		
	}
	private void example_10() {
		portNumber = 1225;
		Runnable r = () -> { 
			portNumber = 10;
			System.out.println(portNumber);
		};
		new Dummy(r);
//		r.run();
		
		Thread t = new Thread(r);
		t.start();
		try {
			t.join(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(portNumber);
	}
	private void example_9() {
		Comparator<Apple> c = 
				(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
	    Comparator<Apple> c2 = 
	    		(a1 , a2) -> a2.getColor().compareTo(a1.getColor());
		
	}
	private void test_8() {
		Runnable o = () -> { System.out.println("oh");};
		
	}
	private void test_7() {
		IntPredicate evenNumbers = (int i) -> i % 2 == 0 ;
		System.out.println(evenNumbers.test(1000));
		
		Predicate<Integer> oddNumbers = (Integer i)	 -> i % 2 == 1;
		System.out.println(oddNumbers.test(1000));
		
	}
	private void test_6() {
		List<Integer> li = 
				map(Arrays.asList("apple","orange","pear"), 
						(ele) -> ele.length());
		System.out.println(li);
		
	}
	/**
	 * Consumer Functional Interface is one that takes an element T and 
	 * performs some operation on it. THe FI returns void. 
	 * The method is "accept" 
	 * @param list
	 * @param c
	 */
	public static <T> void forEach(List<T> list, Consumer<T> c )
	{
		for ( T ele : list ) {
			c.accept(ele);
		}
	}
	public static <T,R> List<R> map(List<T> list , Function<T,R> f) {
		List<R> result = new ArrayList<>();
		for ( T ele : list) {
			result.add(f.apply(ele));
		}
		return result;
	}

	private void test_5() {
		forEach(inventory , (ele) -> System.out.println(" hello " + ele));
		
	}

	/**
	 * Notes:
	 * The pecurliarities of java:
	 * If you put braces around an expression you must precede the 
	 * expression with a "return" keyword
	 */
	private void test_4() {
		processFile("C:\\temp\\files.txt", (BufferedReader b) -> {
			return b.readLine() + b.readLine();});
		
				
		
	}

	private static void  processFile (String fln, BufferedReaderProcessor bp ) {
		try (
		BufferedReader b = new BufferedReader(new FileReader(fln))
		) {
			String s = bp.process(b);
			System.out.println(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void testMyRun(MyRun run) {
		run.run();
	}
	private void test_3() {
		MyRun runner2 = () -> {return;};
		MyRun runner = () -> {
			System.out.println("we are running");
			System.out.println("bye");
		};
		
		testMyRun( () -> System.out.println("running"));
		
	}

	private void test_2() {
		filter(inventory, (Apple a) -> "red".equals(a.getColor())).forEach(System.out::println);
		
	}

	private static <T>  List<T> filter(List<T> list, Predicate<T> p) {
		List<T>  result = new ArrayList<>();
		for ( T ele : list ) {
			if ( p.test(ele)) {
				result.add(ele);
			}
		}
		return result;
	}
	private void test_1() {
		// before java 8 
		Comparator<Apple> byWeight = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
		Comparator<Apple> byColor  = (Apple a1 , Apple a2) -> a1.getColor().compareTo(a2.getColor());
		inventory.sort(new Comparator<Apple>() {

			@Override
			public int compare(Apple o1, Apple o2) {
				// TODO Auto-generated method stub
				return o1.getWeight().compareTo(o2.getWeight());
			}
			
		});
		inventory.forEach(System.out::println);
		
		// after java 8
		Collections.shuffle(inventory);
		inventory.sort(byColor);
		inventory.forEach(System.out::println);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();

	}

}
