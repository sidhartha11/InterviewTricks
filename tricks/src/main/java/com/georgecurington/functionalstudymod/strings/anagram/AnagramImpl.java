/**
 * 
 */
package com.georgecurington.functionalstudymod.strings.anagram;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class AnagramImpl implements Anagram {

	private final char ch;
	private  int count=1;


	/**
	 * @param ch
	 * @param count
	 */
	public AnagramImpl(char ch) {
		super();
		this.ch = ch;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.strings.anagram.Anagram#getChar()
	 */
	@Override
	public char getChar() {
		// TODO Auto-generated method stub
		return ch;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.strings.anagram.Anagram#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.count;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ch;
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
		AnagramImpl other = (AnagramImpl) obj;
		if (ch != other.ch)
			return false;
		return true;
	}

	@Override
	public int updateCount() {
		// TODO Auto-generated method stub
		return count++;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AnagramImpl [ch=" + ch + ", count=" + count + "]";
	}

}
