/**
 * 
 */
package com.georgecurington.functionalstudymod.runningtotal1;

/**
 * @author george
 *
 */
public class IdTimeObjectImpl implements IdTimeObject, Comparable<IdTimeObjectImpl> {

	private final long id;
	private final long milliSeconds;
	/**
	 * @param id
	 * @param milliSeconds
	 */
	public IdTimeObjectImpl(long id, long milliSeconds) {
		super();
		this.id = id;
		this.milliSeconds = milliSeconds;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.runningtotal1.IdTimeObject#getMilliSeconds()
	 */
	@Override
	public long getMilliSeconds() {
		// TODO Auto-generated method stub
		return this.milliSeconds;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.runningtotal1.IdTimeObject#getId()
	 */
	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public int compareTo(IdTimeObjectImpl o) {
		long me = getId() + getMilliSeconds();
		long you = o.getId() + o.getMilliSeconds();
		if (me < you){
			return -1;
		} else if ( me > you) {
			return 1;
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (milliSeconds ^ (milliSeconds >>> 32));
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
		IdTimeObjectImpl other = (IdTimeObjectImpl) obj;
		if (id != other.id)
			return false;
		if (milliSeconds != other.milliSeconds)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IdTimeObjectImpl [id=" + id + ", milliSeconds=" + milliSeconds + "]";
	}

}
