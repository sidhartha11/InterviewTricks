/**
 * 
 */
package com.georgecurington.functionalstudymod.codeFights;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since May 11, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class CodeFightsOne {
	Pattern pat = Pattern.compile("(\\d+#[0-9A-F]+#)|(\\d+)");
	private static enumBase[] baseMap = new enumBase[15];
	//.matcher("as").matches();  
    enum enumBase {
    	base2("01",0),
    	base3("012",1),
    	base4("0123",2),
    	base5("01234",3),
    	base6("012345",4),
    	base7("0123456",5),
    	base8("01234567",6),
    	base9("012345678",7),
    	base10("0123456789",8),
    	base11("0123456789A",9),
    	base12("0123456789AB",10),
    	base13("0123456789ABC",11),
    	base14("0123456789ABCD",12),
    	base15("0123456789ABCDE",13),
    	base16("0123456789ABCDEF",14);
    	
    	private Character[] coding;
    	private int index;

		private enumBase(String database,int index) {
			coding = new Character[database.length()];
    		for (int i = 0 ; i < database.length();i++){
    			coding[i] = database.charAt(i);
    		}
    		this.index = index;
    	}
		
		public Character[] getCoding(){
			return coding;
		}
		public int getIndex() { 
			return index;
		}
    }
	Map<enumBase,Character[]> conversionMap = new HashMap<>();
	
	{
		for (enumBase coding : enumBase.values() ){
			conversionMap.put(coding, coding.getCoding());
			baseMap[coding.getIndex()] = coding;
		}
	}
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

	@Ignore
	public void showCoding() {
		conversionMap.entrySet().stream().forEach(p -> System.out.println(Arrays.toString(p.getValue())));
	}
	@Test
	public void showMathing(){
		
        boolean b = false;
		b = adaNumber("10#1234567890#");
		System.out.println(b);
		b = adaNumber("3452A0");
		System.out.println(b);
		
		b = adaNumber("2#01#");
		System.out.println(b);
		b = adaNumber("1234");
		System.out.println(b);
	}

	/**
	 * @param number 
	 * 
	 */
	private boolean adaNumber(String nmbr) {
		System.out.println("looking at " + nmbr);
		if (nmbr == null || nmbr.equals("")) return false;
		String number = nmbr.trim().toUpperCase().replaceAll("_", "");
		boolean b = pat.matcher(number.toUpperCase()).matches();
		if ( !b ) {
			return false ;
		}
		String[] c = number.split("#");
		System.out.println(c.length);
		if ( c.length > 2 ) {
			return false ;
		} else if ( c.length == 2 ) {
			int x = Integer.parseInt(c[0]);
			if ( x < 2 || x > 16 ) {
				return false ;
			} else {
				// validate the base number 
				System.out.println("validating number in base " + x + ", bm=" + baseMap[x-2]);
				System.out.println("coding = " + Arrays.toString(baseMap[x-2].getCoding()));
				boolean chk = checkCoding(baseMap[x-2].getCoding(),c[1]);
			    return chk;
			}
		} else {
			System.out.printf("matching %s %b\n",number, b);
		    return b;
		}
	}

	private boolean checkCoding(Character[] coding, String input) {
		boolean bad = false;
		boolean good = true;
		for ( int i = 0 ; good && i < input.length() ; i ++ ) {
			good = false;
			for ( int j = 0 ; j < coding.length ; j++ ) {
				if ( input.charAt(i) == coding[j]) {
					good = true;
					break;
				}
			} 
		}
		return good;
	}

}
