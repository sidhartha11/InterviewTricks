/**
 * 
 */
package com.georgecurington.functionalstudymod.practical.inmemorystore;

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
public class ContactsImpl implements Contacts, Comparable<ContactsImpl> {

	private final String name;
	private final String nationalId;
	/**
	 * 
	 */
	public ContactsImpl(String nationalId, String name) {
		this.name = name;
		this.nationalId = nationalId;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getNationalId() {
		return nationalId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nationalId == null) ? 0 : nationalId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactsImpl other = (ContactsImpl) obj;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
		if (nationalId == null) {
			if (other.nationalId != null)
				return false;
		} else if (!nationalId.equals(other.nationalId))
			return false;
		return true;
	}

	@Override
	public int compareTo(ContactsImpl arg0) {
		int i = getNationalId().compareTo(arg0.getNationalId());
		return i;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContactsImpl [name=" + name + ", nationalId=" + nationalId + "]";
	}


}
