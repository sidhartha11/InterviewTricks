/**
 * 
 */
package com.georgecurington.j8ia.chap2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.georgecurington.j8ia.chap2.FilteringApples.Apple;

/**
 * @author Owner
 *
 */

public class MainNonGeneric {
	private static List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));

	public interface PrettyPrint {
		String formatter(Apple apple);
	}
	public interface ApplePredicate {
		boolean test(Apple apple);
	}
	
	static class AppleHeavyWeightPredicate implements ApplePredicate {

		@Override
		public boolean test(Apple apple) {
			// TODO Auto-generated method stub
			return apple.getWeight() > 130 ;
		}
		
	}
	
	static class AppleGreenColorPredicate implements ApplePredicate {

		@Override
		public boolean test(Apple apple) {
			// TODO Auto-generated method stub
			return "green".equals(apple.getColor());
		}
		
	}

	/**
	 * 
	 */
	public MainNonGeneric() {
		// TODO Auto-generated constructor stub
	}



	public static List<Apple> filterGreenApples_1(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();

		for (Apple apple : inventory) {
			if ("green".equals(apple.getColor())) {
				result.add(apple);
			}
		}

		return result;
	}

	public static List<Apple> filterGreenApples_2(List<Apple> inventory, String color) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getColor().equals(color)) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesUgly(List<Apple> inventory, String color, int weight, boolean flag) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if ((flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight)) {
				result.add(apple);
			}
		}
		return result;
	}
	public static List<Apple> filterApplesLambda(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		for ( Apple apple: inventory ) {
			if ( p.test(apple) ) {
				result.add(apple);
			}
		}
		return result;
	}
	public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
		List<Apple> result = new ArrayList<>();
		for ( Apple apple: inventory ) {
			if ( p.test(apple) ) {
				result.add(apple);
			}
		}
		return result;
	}
	public static void  formatApples(List<Apple> inventory, PrettyPrint p) {
		for ( Apple apple: inventory ) {
			String s = p.formatter(apple);
			System.out.println(s);
		}
	}
	
	/**
	 * @param args
	 */
	private static void runner() {
		System.out.println("running");
		test_3();
	}
	private static void test_3() {
		filterApplesLambda
		(inventory, (Apple a) -> "red".equals(a.getColor())).forEach(System.out::println);;
	}



	private static void test_1() {
		List<Apple> l = filterApples(inventory ,
		new ApplePredicate(){
			public boolean test (Apple apple) {
				return "red".equals(apple.getColor());
			}
		}) ; 
		l.forEach(System.out::println);
	}
	public static void main(String[] args) {
		runner();
	}



	private static void test_2() {
		formatApples ( inventory , new PrettyPrint() {

			@Override
			public String formatter(Apple apple) {
				// TODO Auto-generated method stub
				return 
				String.format("Apple is %s in color and %d in weight", apple.getColor(),apple.getWeight());
			}
			
		});
		
	}

}
