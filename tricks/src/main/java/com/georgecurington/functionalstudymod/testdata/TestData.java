/**
 * 
 */
package com.georgecurington.functionalstudymod.testdata;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public interface TestData { 

	public static final String [] palindromes = {
			"93ONNO39" ,
			"52BYNYB25" ,
			"AVQ99QVA" ,
			"KJML3LMJK" ,
			"HHFU8UFHH" ,
			"1AE_I_EA1" ,
			"SZT5S5TZS" ,
			"7RQ99QR7" ,
			"4MR33RM4" ,
			"0XY0N0YX0" ,
			"9F1OO1F9" ,
			"RY8DD8YR" ,
			"6KDTKTDK6" ,
			"9PFLLFP9" ,
			"ZGVIIVGZ" ,
			"WYF_3_FYW" ,
			"T_FKKF_T" ,
			"LJE66EJL" ,
			"K6H99H6K" ,
			"VWQN99NQWV" ,
			"BOHPPHOB" ,
			"NBPY_YPBN" ,
			"PGBXDXBGP" ,
			"QV3Y7Y3VQ" ,
			"6ISVVSI6" ,
			"MP3IUI3PM" ,
			"GUR11RUG" ,
			"D8FD2DF8D" ,
			"5XTSISTX5" ,
			"576E4E675" ,
			"IUC8X8CUI" ,
			"J7XX0XX7J" ,
			"VGKHFHKGV" ,
			"R17M1M71R" ,
			"F_OV8VO_F" ,
			"QV4664VQ" ,
			"T1QF8FQ1T" ,
			"5L0Z7Z0L5" ,
			"W32T_T23W" ,
			"DCJQQJCD" ,
			"6FD00DF6" ,
			"8D3W66W3D8" ,
			"7LMYYML7" ,
			"GR7WW7RG" ,
			"8C_LL_C8" ,
			"DYBMTMBYD" ,
			"02K7H7K20" ,
			"MOJ3P3JOM" ,
			"6GB1O1BG6" ,
			"TZHUHUHZT" ,
			"F8WOOW8F" ,
			"PVTKKTVP" ,
			"LUU7G7UUL" ,
			"7IRAARI7" ,
			"2SVE0EVS2" ,
			"3MVJ9JVM3" ,
			"DEW0A0WED" ,
			"1P9BXB9P1" ,
			"7V0__0V7" ,
			"F0BK2KB0F" ,
			"52T44T25" ,
			"TVFGGFVT" ,
			"UB_MOM_BU" ,
			"O70FF07O" ,
			"GAYGBGYAG" ,
			"ITGQ8QGTI" ,
			"UVEWOWEVU" ,
			"AJV0Q0VJA" ,
			"G3VTLTV3G" ,
			"SI1RLR1IS" ,
			"E6OQQO6E" ,
			"W5RAIAR5W" ,
			"C1D1O1D1C" ,
			"PZOE6EOZP" ,
			"0ZV767VZ0" ,
			"70TXWXT07" ,
			"NA0AA0AN" ,
			"0MTNNTM0" ,
			"SUKTTKUS" ,
			"COWA9AWOC" ,
			"51J66J15" ,
			"SG7667GS" ,
			"IOP77POI" ,
			"HEZGJGZEH" ,
			"FH1YGY1HF" ,
			"1D9XDX9D1" ,
			"LZZJJZZL" ,
			"S7GBSBG7S" ,
			"4CZV3VZC4" ,
			"5AM7I7MA5" ,
			"76J22J67" ,
			"CHPGQGPHC" ,
			"CU2OO2UC" ,
			"G2SCACS2G" ,
			"S8VSASV8S" ,
			"8KLQQLK8" ,
			"2VS44SV2" ,
			"4ZQ55QZ4" ,
			"0B_030_B0" 
	};
	
	public static final String[]  authors = 
		{
		"Patricia Aakhus " ,
		"Hans Aanrud " ,
		"David Aaron " ,
		"Rachel Aaron" ,
		"Soazig Aaron " ,
		"Ben Aaronovitch " ,
		"Alexander Aaronsohn " ,
		"Héctor Abad Faciolince " ,
		"Christopher Abani " ,
		"Sait Faik Abasiyanik " ,
		"Carmine Abate " ,
		"Christina Abbey " ,
		"Edward Abbey " ,
		"Henry Abbey " ,
		"Lynn Abbey " ,
		"Edwin A. Abbott " ,
		"Abhilash Pudukad " ,
		"Eleanor Hallowell Abbott " ,
		"George Frederick Abbott " ,
		"Jacob Abbott " ,
		"John S. C. Abbott " ,
		"Megan Abbott " ,
		"Mohammed ibn Hajj al-Abdari al-Fasi " ,
		"Mohammed al-Abdari al-Hihi " ,
		"Abdelkrim al-Khattabi " ,
		"Abd al-Qadir al-Fasi " ,
		"Kobo Abe " ,
		"Shana Abé" ,
		"Peter Abelard " ,
		"Robert Abernathy " ,
		"Walter Abish " ,
		"Leila Abouzeid " ,
		"Daniel Abraham " ,
		"Marc Abrahams" ,
		"Abu al-Abbas as-Sabti " ,
		"Abu Imran al-Fasi " ,
		"Abu Muqri Mohammed al-Battiwi " ,
		"Milton Abramowitz " ,
		"Mohammed Achaari " ,
		"Chinua Achebe " ,
		"Said Achtouk " ,
		"André Aciman " ,
		"Forrest J. Ackerman " ,
		"Anna Adams " ,
		"Douglas Adams " ,
		"Richard Adams " ,
		"Robert Adams " ,
		"Abd al-Wahhab Adarrak " ,
		"Mirza Adeeb " ,
		"Chimamanda Ngozi Adichie" ,
		"Halide Edip Adivar " ,
		"Hakim Syed Zillur Rahman " ,
		"Jürgen Habermas " ,
		"David Hackworth" ,
		"Mark Haddon" ,
		"Margaret Peterson Haddix" ,
		"Sebastian Haffner " ,
		"H. Rider Haggard " ,
		"Emily Hahn " ,
		"Qurratulain Haider" ,
		"Arthur Hailey " ,
		"Yusuf Has Hajib" ,
		"Jack C. Haldeman II " ,
		"Joe Haldeman " ,
		"Alex Haley " ,
		"Parnell Hall " ,
		"Bruce Barrymore Halpenny" ,
		"Rosalie Ham" ,
		"Barbara Hambly" ,
		"Mohsin Hamid " ,
		"Edmond Hamilton " ,
		"Hervey Hamilton " ,
		"Marguerite Hamilton" ,
		"Patrick Hamilton " ,
		"Peter F. Hamilton " ,
		"Ruth Hamilton" ,
		"Dashiell Hammett " ,
		"Knut Hamsun" ,
		"Daniel Handler " ,
		"Kristin Hannah " ,
		"Mark Victor Hansen, coauthor of Chicken Soup for the Soul" ,
		"Bergtóra Hanusardóttir " ,
		"Thomas Hardy " ,
		"Charles Harness" ,
		"E. Lynn Harris " ,
		"Joanne Harris" ,
		"Joel Chandler Harris " ,
		"Sam Harris " ,
		"Thomas Harris" ,
		"Wilson Harris " ,
		"Elizabeth Harrison" ,
		"Harry Harrison " ,
		"M. John Harrison " ,
		"Lou Harry, " ,
		"Christopher Hart " ,
		"Michael H. Hart" ,
		"Peter Härtling " ,
		"William K. Hartmann" ,
		"Caroline Harvey" ,
		"Alamgir Hashmi" ,
		"Henry Hasse " ,
		"Havank" ,
		"Ethan Hawke" ,
		"Karen Hawkins" ,
		"Paula Hawkins" ,
		"Noah Hawley" ,
		"Julian Hawthorne " ,
		"Nathaniel Hawthorne" ,
		"Ernest Haycox" ,
		"Jon Haylett" ,
		"Shirley Hazzard" 
		};
	String[] miscTags = {
			"sci-fi", "java", "scala",
			"romance", "history" , "drama",
			"python" , "elastic", "mongodb" ,
			"restful" , "micro-services" , "docker" ,
			"akka" , "XML" , "JAXB" , "Spring Framework"
	};
	String[] namesUniverse = {
			"10 Cloverfield Lane " ,
			"The 5th Wave " ,
			"Alien " ,
			"Aliens " ,
			"Alien 3 " ,
			"Alien Resurrection " ,
			"Alien: Covenant " ,
			"Attack the Block " ,
			"Battle: Los Angeles " ,
			"Battleship " ,
			"The Blob " ,
			"The Blob " ,
			"Chicken Little " ,
			"Close Encounters of the Third Kind " ,
			"Cloverfield " ,
			"The Darkest Hour " ,
			"The Day the Earth Stood Still " ,
			"The Day the Earth Stopped " ,
			"Decoys " ,
			"End of the World " ,
			"The Faculty " ,
			"Grabbers " ,
			"Hangar 18 " ,
			"I Come in Peace " ,
			"Impostor " ,
			"Independence Day " ,
			"Independence Day: Resurgence " ,
			"Independence Day-saster " ,
			"Invasion " ,
			"Invasion " ,
			"The Invasion " ,
			"Invasion: Earth " ,
			"Invasion of the Body Snatchers " ,
			"Invasion of the Body Snatchers " ,
			"Invasion of the Les-Body Snatchers " ,
			"Invasion of the Pod People " ,
			"Invasion of the Saucer Men " ,
			"Koi... Mil Gaya " ,
			"Kronos " ,
			"Lifted " ,
			"Liquid Sky " ,
			"Mars Attacks! " ,
			"Monsters vs. Aliens " ,
			"Night Skies " ,
			"Pitch Black " ,
			"Planet 51 " ,
			"Progeny " ,
			"The Shadow Men " ,
			"Signs " ,
			"Sky Captain and the World of Tomorrow " ,
			"Skyline " ,
			"Slither " ,
			"Starship Troopers: Invasion " ,
			"Strange Invaders " ,
			"Suburban Commando " ,
			"Terminal Invasion " ,
			"They Live " ,
			"Transmorphers " ,
			"Transmorphers: Fall of Man " ,
			"U.F.O. " ,
			"Under the Dome " ,
			"The Visitor " ,
			"The War of the Worlds " ,
			"War of the Worlds " ,
			"Within the Rock " ,
			"Anaconda " ,
			"Anaconda 3: Offspring " ,
			"Anacondas: The Hunt for the Blood Orchid " ,
			"Anacondas: Trail of Blood " ,
			"Backcountry " ,
			"Bait " ,
			"Bats: Human Harvest " ,
			"Ben " ,
			"Birdemic: Shock and Terror " ,
			"The Birds " ,
			"The Birds II: Land's End " ,
			"Blood Monkey " ,
			"Blood Sand " ,
			"Burning Bright " ,
			"Congo " ,
			"Cruel Jaws " ,
			"Dark Tide " ,
			"Day of the Animals " ,
			"Deadly Eyes " ,
			"Deep Blue Sea " ,
			"Empire of the Ants " ,
			"Flu Bird Horror " ,
			"Food of the Gods " ,
			"The Giant Spider Invasion " ,
			"Grizzly Park " ,
			"Grizzly Rage " ,
			"In the Heart of the Sea " ,
			"The Intruder " ,
			"Jaws " ,
			"Jaws 2 " ,
			"Jaws 3-D " ,
			"Jaws in Japan " ,
			"Jaws: The Revenge " ,
			"Jersey Shore Shark Attack " ,
			"KAW " ,
			"Killer Fish " ,
			"The Killer Shrews " ,
			"Killing Birds " ,
			"Komodo " ,
			"Kraken: Tentacles of the Deep " ,
			"Lake Placid " ,
			"Lake Placid 2 " ,
			"Lake Placid 3 " ,
			"Lake Placid: The Final Chapter " ,
			"Lake Placid vs. Anaconda " ,
			"Malibu Shark Attack " ,
			"Mammoth " ,
			"Maneater " ,
			"Matatalim na Pangil sa Gubat " ,
			"Night of the Lepus " ,
			"Nightwing " ,
			"Prophecy " ,
			"Paulie " ,
			"The Rats " ,
			"Rats - Notte di terrore " ,
			"Reptilicus " ,
			"Rogue " ,
			"The Shallows " ,
			"Shark Attack " ,
			"Shark Swarm " ,
			"Sharknado " ,
			"Sharknado 2: The Second One " ,
			"Sharknado 3: Oh Hell No! " ,
			"Sharknado: The Fourth Awakens " ,
			"Silent Warnings " ,
			"Snakehead Swamp " ,
			"Snow Shark " ,
			"Squirm " ,
			"Sssssss " ,
			"Swamp Shark " ,
			"Tentacles " ,
			"Willard " ,
			"Willard " ,
			"Zoombies " ,
			"Zoo " ,
			"Ants! " ,
			"Arachnophobia " ,
			"Bee Movie" ,
			"The Black Scorpion " ,
			"Black Swarm " ,
			"Bug " ,
			"Bugs " ,
			"Eight Legged Freaks " ,
			"The Giant Spider Invasion " ,
			"The Hive " ,
			"Ice Spiders " ,
			"Kingdom of the Spiders " ,
			"Lavalantula " ,
			"2 Lava 2 Lantula! " ,
			"Leeches! " ,
			"Legion of Fire: Killer Ants! " ,
			"Locusts " ,
			"Locusts: The 8th Plague " ,
			"Naked Jungle " ,
			"Phase IV " ,
			"The Savage Bees " ,
			"Slices of Life " ,
			"Skeeter " ,
			"The Swarm " ,
			"Tarantula " ,
			"Terror Out of the Sky " ,
			"Them! " ,
			"They Nest " 
};
	String TESTJSON = 
	"	{ " +
	"		\"container\" : [ " +
	"		{ " +
	"		\"author\" : \"Robert Abernathy \",  " +
	"		\"published\" : \"1962-07-20T00:00:00.000-0400\",  " +
	"		\"articleName\" : \"10 Cloverfield Lane \",  " +
	"		\"tags\" : \"[sci-fi, drama, python, Spring Framework, elastic, java, XML, micro-services, JAXB, scala]\"  " +
	"		} " +
	"		, " +
	"		{ " +
	"		\"author\" : \"Ruth Hamilton\",  " +
	"		\"published\" : \"1981-07-16T00:00:00.000-0400\",  " +
	"		\"articleName\" : \"Lake Placid vs. Anaconda \",  " +
	"		\"tags\" : \"[history, akka, romance, docker, restful, mongodb, XML, scala, sci-fi]\"  " +
	"		} " +
	"		] " +
	"		} " 
	;
	
	/**
	 * @param strlen the size of the field to create
	 * @param len The number of records to dump into the created file
	 * @param file The name of the file to create
	 * @param sparse Controls the number of random characters form the alphabet and numbers to use
	 * @param delim The delimiter to use between fields
	 * @param nmbrFields THe number of fields to create. 
	 */
	public static void createTestData(
			int strlen  /** the size of the fields **/
			, long len   /** the number of records to create **/
			,String file /** the name of the file to create **/
			,int sparse  /** the number of random characters from alphabet and numbers **/
			,String delim /** the delimiter to use **/
			, int nmbrFields /** the number of fields **/
			) {
		Charset charset = Charset.forName("US-ASCII");
		Path p1 = Paths.get(file);
		StringBuilder sb=new StringBuilder();
		try (BufferedWriter writer = Files.newBufferedWriter(p1, charset)) {
			for ( long i=0; i < len; i++) {
				sb.setLength(0);
				for ( int x=0; x < nmbrFields; x++) {
				if ( x != 0 ){
					sb.append(delim);
				}
				String s1 = getSaltString(strlen,sparse);
				sb.append(s1);
				}
				sb.append(System.lineSeparator());
				writer.write(sb.toString(), 0, sb.length());
//				logger.info(s3);
			}
		    
		    
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
	}
	
	public static void createTestDataSlowly(
			int strlen  /** the size of the fields **/
			, long len   /** the number of records to create **/
			,String file /** the name of the file to create **/
			,int sparse  /** the number of random characters from alphabet and numbers **/
			,String delim /** the delimiter to use **/
			, int nmbrFields /** the number of fields **/
			, long sleep
			) {
		Charset charset = Charset.forName("US-ASCII");
		Path p1 = Paths.get(file);
		StringBuilder sb=new StringBuilder();
		try (BufferedWriter writer = Files.newBufferedWriter(p1, charset)) {
			for ( long i=0; i < len; i++) {
				sb.setLength(0);
				for ( int x=0; x < nmbrFields; x++) {
				if ( x != 0 ){
					sb.append(delim);
				}
				String s1 = getSaltString(strlen,sparse);
				sb.append(s1);
				}
				sb.append(System.lineSeparator());
				writer.write(sb.toString(), 0, sb.length());
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				logger.info(s3);
			}
		    
		    
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
	}
	
	public static  String getSaltString(int len,int sparse) {
        String SALTCHARS = "ABCDEFGHIJKLMN_OPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        int index=0;
        while (salt.length() < len) { // length of the random string.
        	if ( sparse > 0 )
            index = (int) (rnd.nextFloat() * sparse);
        	else
        	index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

	public static String getSaltString(int min, int max, int sparse) {
	       String SALTCHARS = "ABCDEFGHIJKLMN_OPQRSTUVWXYZ1234567890";
	        StringBuilder salt = new StringBuilder();
	        Random rnd = new Random();
	        int index=0;
	        int len = ThreadLocalRandom.current().nextInt(min, max+1);
	        while (salt.length() < len) { // length of the random string.
	        	if ( sparse > 0 )
	            index = (int) (rnd.nextFloat() * sparse);
	        	else
	        	index = (int) (rnd.nextFloat() * SALTCHARS.length());
	            salt.append(SALTCHARS.charAt(index));
	        }
	        String saltStr = salt.toString();
	        return saltStr;
	}
	
	
}