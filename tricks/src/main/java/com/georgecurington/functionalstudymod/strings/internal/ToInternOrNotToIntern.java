/**
 * 
 */
package com.georgecurington.functionalstudymod.strings.internal;

/**
 * <pre>
 * The tricks involving String usage in java are alway lerking around the
 * corner waiting to rear their ugly heads at the most inappropriate times.
 * Like during a silly interview, for instance. The typical question always comes
 * up:
 * ehhh ...
 * What is the difference between these two statements:
 * <pre>
 * String one   = "apple";
 * String two   = new String("apple");
 * String three = "apple".intern();
 * </pre>
 * 
 * <p>Here is the JavaDoc documentation located in the String.java class of the language
 * package:
 * <pre>
 * The {@code String} class represents character strings. All
 * string literals in Java programs, such as {@code "abc"}, are
 * implemented as instances of this class.
 * <p>
 * Strings are constant; their values cannot be changed after they
 * are created. String buffers support mutable strings.
 * Because String objects are immutable they can be shared. For example:
 * <blockquote><pre>
 *     String str = "abc";
 * </pre></blockquote><p>
 * is equivalent to:
 * <blockquote><pre>
 *     char data[] = {'a', 'b', 'c'};
 *     String str = new String(data);
 * </pre></blockquote><p>
 * Here are some more examples of how strings can be used:
 * <blockquote><pre>
 *     System.out.println("abc");
 *     String cde = "cde";
 *     System.out.println("abc" + cde);
 *     String c = "abc".substring(2,3);
 *     String d = cde.substring(1, 2);
 * </pre></blockquote>
 * <p>
 * The class {@code String} includes methods for examining
 * individual characters of the sequence, for comparing strings, for
 * searching strings, for extracting substrings, and for creating a
 * copy of a string with all characters translated to uppercase or to
 * lowercase. Case mapping is based on the Unicode Standard version
 * specified by the {@link java.lang.Character Character} class.
 * <p>
 * The Java language provides special support for the string
 * concatenation operator (&nbsp;+&nbsp;), and for conversion of
 * other objects to strings. String concatenation is implemented
 * through the {@code StringBuilder}(or {@code StringBuffer})
 * class and its {@code append} method.
 * String conversions are implemented through the method
 * {@code toString}, defined by {@code Object} and
 * inherited by all classes in Java. For additional information on
 * string concatenation and conversion, see Gosling, Joy, and Steele,
 * <i>The Java Language Specification</i>.
 *
 * <p> Unless otherwise noted, passing a <tt>null</tt> argument to a constructor
 * or method in this class will cause a {@link NullPointerException} to be
 * thrown.
 *
 * <p>A {@code String} represents a string in the UTF-16 format
 * in which <em>supplementary characters</em> are represented by <em>surrogate
 * pairs</em> (see the section <a href="Character.html#unicode">Unicode
 * Character Representations</a> in the {@code Character} class for
 * more information).
 * Index values refer to {@code char} code units, so a supplementary
 * character uses two positions in a {@code String}.
 * <p>The {@code String} class provides methods for dealing with
 * Unicode code points (i.e., characters), in addition to those for
 * dealing with Unicode code units (i.e., {@code char} values).
 *</pre>
 *
 * @author  Lee Boynton
 * @author  Arthur van Hoff
 * @author  Martin Buchholz
 * @author  Ulf Zibis
 * @see     java.lang.Object#toString()
 * @see     java.lang.StringBuffer
 * @see     java.lang.StringBuilder
 * @see     java.nio.charset.Charset
 * @since   JDK1.0
 * 
 * ---- And interesting thing to notice about String.java is the Declaration of the String
 * class and also the declaration of the intern method:
 * 
 * public final class String
 *  implements java.io.Serializable, Comparable<String>, CharSequence {
 * 
 * public native String intern();
 * 
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class ToInternOrNotToIntern {

	/**
	 * 
	 */
	public ToInternOrNotToIntern() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		example1();

	}

	/**
	 * <p> A note on the intern method:
	 * <pre>
	 * ================================
	 * Returns a canonical representation for the string object. 
     * A pool of strings, initially empty, is maintained privately by the class String. 
     * When the intern method is invoked, if the pool already contains a string equal 
     * to this String object as determined by the equals(Object) method, 
     * then the string from the pool is returned. 
     * Otherwise, this String object is added to the pool and a 
     * reference to this String object is returned. 
     * </pre>
	 * 
	 */
	private static void example1() {
		 String one   = "apple";
		 String two   = new String("apple");
		 String three = "apple".intern();
		 String four  = two.intern();
		 String five  = one;
		 String six = "apple";
		 String le = "le";
		 
		 
		 boolean oneAndTwoAreSameObject = one == two; // false
		 boolean oneAndThreeAreSameObject = one == three; // true
		 boolean twoAndFourAreSameObject = two == four;	 // false
		 boolean oneAndFiveAreSameObject = one == five;	 // true
		 boolean oneAndSixAreSameObject = one == six;	 // true
		 
		 boolean runtimeCatenationWithConstants = // true
				 "apple" == "app" + "le";
		 
		 boolean runtimeCatenationWithOutConstants = // false, new object is created
				 "apple" == "app" + le;

		 
		 System.out.println(String.format("oneAndTwoAreSameObject=%b", oneAndTwoAreSameObject));
		 System.out.println(String.format("oneAndThreeAreSameObject=%b", oneAndThreeAreSameObject));
		 System.out.println(String.format("twoAndFourAreSameObject=%b", twoAndFourAreSameObject));
		 System.out.println(String.format("oneAndFiveAreSameObject=%b", oneAndFiveAreSameObject));
		 System.out.println(String.format("oneAndSixAreSameObject=%b", oneAndSixAreSameObject));

		 System.out.println(String.format("runtimeCatenationWithConstants=%b", runtimeCatenationWithConstants));
		 System.out.println(String.format("runtimeCatenationWithOutConstants=%b", runtimeCatenationWithOutConstants));

		 
	}

}
