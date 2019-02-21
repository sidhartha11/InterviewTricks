/**
 * 
 */
package com.georgecurington.j8ia.chap3;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import static java.util.Comparator.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import com.georgecurington.j8ia.chap3.Lambdas.Apple;

/**
 * @author Owner
 *
 */
public class Summary {
	private static List<Apple> inventory = Arrays.asList(
			new Apple(80, "green")
			, new Apple(155, "green")
			, new Apple(120, "red")
			, new Apple(180,"red")
			, new Apple(10,"pink")
			);

	/**
	 * The first solution is to used the normal pre java8 approach to sorting
	 * by creating a Comparator that can be passed to the sort routine. 
	 *
	 */
	private class AppleComparator implements Comparator<Apple> {
		
		public int compare(Apple a1 , Apple a2) {
			return a1.getWeight().compareTo(a2.getWeight());
		}
		
	}
	
	/**
	 * This is an inner class:
	 * Rulses
	 * 1. Inner classes that are non-static cannot have static methods.
	 * Only static inner classes and toplevel classes may have static methods.
	 * Hence, I will make this a static inner class in order to follow the 
	 * author's book. 
	 *
	 */
	public static class Letter {
		public static String addHeader(String text) {
			return "From Raoul, Mario and Alan: " + text;
		}
		
		public static String addFooter(String text) {
			return text + " Kind regards";
		}
		
		public static String checkSpelling(String text) {
			return text.replaceAll("labda" ,  "lambda");
		}
	}
	
	/**
	 * 
	 */
	public Summary() {
		comp_2();
	}

	private void comp_2() {
		/**
		 * Note, Letter::addHeader a static method reference fullfills
		 * Function<String, String> functionalinterface contract:
		 * It takes a String argument and returns a String result.
		 */
		String letter = "The labda artwork should be finished soon!";
		
		Function<String, String> addHeader = Letter::addHeader;
		Function<String, String> transformationPipeline = 
				addHeader.andThen(Letter::checkSpelling)
				.andThen(Letter::addFooter);
		
		System.out.println(transformationPipeline.apply(letter));
		
	}

	/**
	 * java.util.function.Function
	 * Function<T,R> 
	 * R apply(T t) 
	 */
	private void comp_1() {
		Function<Integer, Integer> f = x -> x + 1 ;
		Function<Integer, Integer> g = x -> x * 2 ;
		Function<Integer, Integer> h = f.andThen(g);
		int result = h.apply(1);
		System.out.println(result);
		
		/** compose performs the inner computation first **/
		Function<Integer, Integer> c = f.compose(g);
		result = c.apply(1);
		System.out.println(result);
			
	}

	private static List<Apple> getSomeApples(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		System.out.println("======================");
		for (Apple ele : inventory) {
			if ( p.test(ele)) {
				result.add(ele);
			}
		}
		return result;
	}
	private void test_6() {
		/** using compositional predicates **/
		Predicate<Apple> redApple = (a) -> "red".equals(a.getColor());
		Predicate<Apple> notRedApple = redApple.negate();
		getSomeApples(inventory,notRedApple).forEach(System.out::println);
		Predicate<Apple> redAndHeavy = redApple.and(a -> a.getWeight() > 150);
		getSomeApples(inventory, redAndHeavy).forEach(System.out::println);
		Predicate<Apple> orPink = redAndHeavy.or(p -> "pink".equals(p.getColor()));
		getSomeApples(inventory, orPink).forEach(System.out::println);
	}

	private void test_5() {
		/** chanining lambda expresions for sorting **/
		inventory
		.sort(
				comparing(Apple::getWeight)
				.reversed()
				.thenComparing(Apple::getColor));
	}

	private void test_4() {
		/** sorting in reverse order using lambda expression **/
		inventory.sort(comparing(Apple::getWeight).reversed());
		
	}

	/**
	 *  Here we can just pass a lambda expression that represents a functional
	 *  interface , an interface with one abstract method. 
	 *  void sort(Comparator<? super E> c) --> question is how does this
	 *  type of generic declaration seamlessly map to a lambda
	 *  declaration of type: (a,b) -> c 
	 */
	private void test_3() {
		inventory.sort((a1,a2) -> a1.getWeight().compareTo(a2.getWeight()));
		inventory.forEach(System.out::println);
		
		/**
		 * The lambda expression here can be simplified furtther by using a static
		 * utility function from the Comparator interface:
		 * 
		 */
		Collections.shuffle(inventory);
		Comparator<Apple> c = Comparator.comparing((Apple a) -> a.getWeight());
		inventory.sort(c);
		/**
		 * Using method references 
		 */
		inventory.sort(Comparator.comparing(Apple::getWeight));
		
	}

	private void test_2() {
		/** first create an anonomous class for the Comparator **/
		/** note that when creating an anonomous generic class
		 * the generic parameters are required.
		 */
		Comparator<Apple> c = 
				new Comparator<Apple>() {
			/** note: visibility must be public here **/
			    public int compare(Apple a1, Apple a2) {
				return a1.getWeight().compareTo(a2.getWeight());
			}
		};
		
		inventory.sort(new Comparator<Apple>() {
			public int compare(Apple a1, Apple a2) {
				return a1.getWeight().compareTo(a2.getWeight());
			}
		});
		
		inventory.forEach(System.out::println);
		
	}

	private void test_1() {
		/**
		 * Using the old method of passing a Comparator to the List.sort method
		 * Note: AppleComparator is an inner class but since it is being created
		 * inside its enclosing class we do not need an actual objec to create it.
		 */
		inventory.sort(new AppleComparator());
		inventory.forEach(System.out::println);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Summary();

	}

}
