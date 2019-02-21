/**
 * 
 */
package com.georgecurington.j8ia.chap4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.georgecurington.j8ia.chap4.Dish.*;

import static com.georgecurington.j8ia.Utilities.*;
/**
 * @author Owner
 *
 */
public class Main {

	/**
	 * 
	 * 
	 */
	public Main() {
		after_java8_5();
	}

	private void after_java8_5() {
		long count = 
				menu.stream()
				.filter(d -> d.getCalories() > 300 )
				.distinct()
				.limit(3)
				.count();
		p("count = "  + count);
		
	}

	private void after_java8_4() {
		List<String> names = 
				menu.stream()
				.filter(d -> {
					p("filtering " + d.getName() );
					return d.getCalories() > 300;
				})
				.map( d -> {
					p("mapping " + d.getName());
					return d.getName();
				})
				.limit(3)
				.collect(toList());
		p(names.toString());
	}

	private void after_java8_3() {
		List<String> names = new ArrayList<>();
		for ( Dish d : menu ) {
			names.add(d.getName());
		}
		
		names = new ArrayList<>();
		Iterator<Dish> iterator = menu.iterator();
		while ( iterator.hasNext()) {
			Dish d = iterator.next();
			names.add(d.getName());
		}
		
		names = menu.stream().map(Dish::getName).collect(toList());
	}

	private void after_java8_2() {
		List<String> title = Arrays.asList("Java8", "In" , "Action");
		Stream<String> s = title.stream();
		s.forEach(System.out::println);
		s.forEach(p -> System.out.println(p));
	}

	/**
	 *  public interface Stream<T> extends BaseStream<T, Stream<T>> 
	 *  There is a default implementation of stream() in the Collection
	 *  interface.
	 *      default Stream<E> stream() {
     *        return StreamSupport.stream(spliterator(), false);
     *      }
     *  It returns a StreamSupport.stream(spliterator() , false)
     *  the utilities, filter, map,sorted  etc of stream() appear to be located 
     *  in ReferencePipeline.java 
	 *  
	 *  
	 */
	private void after_java8() {
		List<String> lowCaloricDishesName = 
				menu.stream()
				/** use filter from the Stream interface **/
				.filter(d -> d.getCalories() < 400)
				/** sort the stream by the input comparator **/
				.sorted(comparing(Dish::getCalories))
				/** extract only the name of the object **/
				.map(Dish::getName)
				/** collect into a list **/
				.collect(toList());
		lowCaloricDishesName.forEach(System.out::println);
		
		/** example of creating and using a parallel stream **/
		lowCaloricDishesName = 
				menu.parallelStream()
				.filter( d -> d.getCalories() < 400)
				.sorted(comparing(Dish::getCalories))
				.map(Dish::getName)
				.collect(toList());
		lowCaloricDishesName.forEach(System.out::println);
		
		/** example using groupingBy **/
		Map<Dish.Type, List<Dish>> dishesByType = 
				menu.stream().collect(groupingBy(Dish::getType));
		dishesByType.forEach((l,r) -> System.out.println("l=" + l +", r=" + r));
		
	}

	private void before_java8() {
		List<Dish> lowCaloricDishes = new ArrayList<>();
		for (Dish d: menu) {
			if (d.getCalories() < 400) {
				lowCaloricDishes.add(d);
			}
		}
		lowCaloricDishes.forEach(System.out::println);
		
		/** now sort the dishes the old way **/
		/** note that compare must be public to avoid visibility decrease **/
		Collections.sort(lowCaloricDishes, new Comparator<Dish> () {
			public int compare(Dish d1 , Dish d2) {
			//	return d1.getCalories().compareTo(d2.getCalories());
				return Integer.compare(d1.getCalories(), d2.getCalories());
			}
		});
		
		List<String> lowCaloricDishesName = new ArrayList<>();
		for ( Dish d : lowCaloricDishes) {
			lowCaloricDishesName.add(d.getName());
		}
		lowCaloricDishesName.forEach(System.out::println);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();

	}

}
