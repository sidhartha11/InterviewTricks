/**
 * 
 */
package com.georgecurington.functionalstudymod.practical.inmemorystore;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 27, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class DatastoreImpl implements Datastore {
	private final SortedSet<Contacts> contacts = new TreeSet<>();
	/**
	 * 
	 */
	public DatastoreImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.practical.inmemorystore.Datastore#command(com.georgecurington.functionalstudymod.practical.inmemorystore.Operation)
	 */
	@Override
	public boolean command(Operation op, String nationalId , String name) {
		Contacts contact = new ContactsImpl(nationalId,name);
		boolean b=false;
		switch ( op ) {
		case add:
			b = contacts.add(contact);
			break;
		case delete:
			b = contacts.remove(contact);
			break;
		case update:
			b=contacts.remove(contact);
			b=contacts.add(contact);
			break;
		default:
			throw new UnsupportedOperationException();
		}
		return b;
	}


	@Override
	public List<Contacts> find(String s) {
		List<Contacts> result = contacts
				.stream()
				.filter(p -> p.getName()
				.contains(s))
				.sorted()
				.collect(Collectors.toList());	
		return result;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.practical.inmemorystore.Datastore#all()
	 */
	@Override
	public List<Contacts> all() {
		List<Contacts> result = contacts
				.stream()
				.sorted().collect(Collectors.toList());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DatastoreImpl [contacts=" + contacts + "]";
	}

}
