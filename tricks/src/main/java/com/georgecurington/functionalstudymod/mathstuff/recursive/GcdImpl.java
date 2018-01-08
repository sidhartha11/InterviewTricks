/**
 * 
 */
package com.georgecurington.functionalstudymod.mathstuff.recursive;

/**
 * @author george
 *
 */
public class GcdImpl {

	/**
	 * recursive greatest common denominator 
	 */
	public GcdImpl() {

	}

	private long gcd(long x, long y) {
		if ( y == 0 ) {
			return x;
		} else {
			return gcd ( y , x % y );
		}
	}
	
	static public void main(String ...args){
		GcdImpl gcd = new GcdImpl();
		long g = gcd.gcd(126, 18);
		System.out.println(g);
	}

}
