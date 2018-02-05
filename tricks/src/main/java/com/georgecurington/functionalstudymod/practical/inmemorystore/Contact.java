/**
 * 
 */
package com.georgecurington.functionalstudymod.practical.inmemorystore;

import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * <pre>
 * <p><b>INTERVIEW TRICKS</b></p>
 * --- note this file is not being used ----
 * </pre>
 * <p>
 * ================================================
 * </p>
 * <br>
 * 
 * @author George Curington
 * @version 1.0.0
 * @since Feb 4, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class Contact  {
long nationalId;
String name;
	private Comparator<Contact> contactComparator = new Comparator<Contact>(){

		@Override
		public int compare(Contact arg0, Contact arg1) {
			long l = arg0.getNationalId();
			long r = arg1.getNationalId();
			if ( l < r ) return -1;
			if ( l > r ) return 1;
			
			return 0;
		}
		
	};
	ContactsStore inMemoryStore = new ContactsStore() {
		SortedSet<Contact> contacts = new TreeSet<>(contactComparator);

		@Override
		public void command(String command, Contact data) {
			switch (command) {
			case "ADD":
				contacts.add(data);
				break;
			case "UPDATE":
				contacts.remove(data);
				contacts.add(data);
				break;
			case "DELETE":
				contacts.remove(data);
				break;
			default:
			}

		}

		@Override
		public List<Contact> find(String partialName) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Contact> all() {
			// TODO Auto-generated method stub
			return null;
		}

	};

	public String getName() { return name; }
	public long getNationalId(){ return nationalId;}
	/**
	 * 
	 */
	public Contact() {
		// TODO Auto-generated constructor stub
	}

}
