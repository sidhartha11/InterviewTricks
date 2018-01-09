/**
 * 
 */
package com.georgecurington.functionalstudymod.strings.anagram;

import static org.junit.Assert.assertTrue;

import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.georgecurington.functionalstudymod.testdata.TestData;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class AnagramImplTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.strings.anagram.AnagramImpl#AnagramImpl(char)}.
	 */
	@Ignore
	public void testAnagramImpl() {
		String teststring1 = "AppleAppleAppleBob";
		String teststring2 = "elppAelppAelppABobXrX";
		AnagramHashIntf anagram1 = new AnagramHash(teststring1);
		AnagramHashIntf anagram2 = new AnagramHash(teststring2);
		assertTrue ("this should be an anagram", anagram1.isAnagram(anagram2));
		
		teststring1 = "The grey fox jumped over the moon";
		teststring2 = "over the moon jumped fox The grey";
		anagram1 = new AnagramHash(teststring1);
		anagram2 = new AnagramHash(teststring2);
		assertTrue ("this should be an anagram", anagram1.isAnagram(anagram2));
	}
	
	@Ignore
	public void testSearchAnagrams(){
		IntStream.range(0,Integer.MAX_VALUE).forEach(p -> {
			String s = TestData.getSaltString(8,14,-1);
			AnagramHashIntf anagram1 = new AnagramHash(s);
			String s2 = TestData.getSaltString(8,14,-1);
			AnagramHashIntf anagram2 = new AnagramHash(s2);
			if ( anagram1.isAnagram(anagram2,true) ) {
				System.out.println(s + "," + s2);
			}
		});
	}
	
	@Test
	public void fromStackOverflow(){

		String str1 = "The grey fox jumped over the moon";
		String str2 = "over the moon jumped fox The bobh";
		int i,j;

		boolean Flag=true;
		i=str1.length();
		j=str2.length();
        System.out.println((i * j));
		int cntr=0;

		if(i==j){
		for(int m=0;m<i;m++){
		    for(int n=0;n<i;n++){
		    	System.out.println("m=" + m + ",n=" + n);
		    	cntr++;
		        if(str1.charAt(m)==str2.charAt(n)){
		           Flag=true;
		           break;
		          }
		          else
		          Flag=false;
		    }
		}
		}
		else{
		Flag=false;
		}
        System.out.println("cntr=" + cntr);
		if(Flag)
		System.out.println("String is Anagram");
		else
		System.out.println("String is not Anagram");
	}

}
