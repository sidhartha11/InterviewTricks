/**
 * 
 */
package com.georgecurington.j8ia.chap2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.georgecurington.j8ia.chap2.FilteringApples.Apple;

import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * @author Owner
 *
 */
public class Main {
	private static List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));

	interface Predicate<T> {
		boolean test(T t);
	}
	
	static <T> List<T> filter(List<T> list , Predicate<T> p) {
		List<T> result = new ArrayList<>();
		for ( T ele : list ) {
			if ( p.test(ele)) {
				result.add(ele);
			}
		}
		return result;
	}
	/**
	 * 
	 */
	public Main() {
		//test_6();
	}

//	private void test_6() {
//		Button button = new Button("Send");
//		button.setOnAction(new EventHandler<ActionEvent>() {
//			public void handle(Action event) {
//				label.setText("Sent!!");
//			}
//		});
//		
//	}
	private void test_5() {
		Thread t = new Thread(() -> System.out.println("hello"));
		t.start();
		
	}
	private void test_4() {
	
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("hello");
				
			}
			
		}).start();
		
	}
	private void test_3() {
		inventory.sort((Apple a1 , Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
		inventory.forEach(System.out::println);
	}
	private void test_2() {
		Comparator<Apple> c = 
				new Comparator<Apple>() {

					@Override
					public int compare(Apple o1, Apple o2) {
						// TODO Auto-generated method stub
						return o1.getWeight().compareTo(o2.getWeight());
					}
			
		};
		inventory.sort(c);
		inventory.forEach(System.out::println);
		
	}
	private void test_1() {
		filter(inventory, (Apple a) -> "red".equals(a.getColor())).forEach(System.out::println);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();

	}


}
