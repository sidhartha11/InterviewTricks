/**
 * 
 */
package com.georgecurington.functionalstudymod.design.factory;

/**
 * <pre>
 * Is a subclass of the abstract Factory. A concrete implementation
 * that will be returned to a client.
 * </pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class PC extends Computer {
	
	private String ram;
	private String hdd;
	private String cpu;

	/**
	 * @param ram
	 * @param hdd
	 * @param cpu
	 */
	public PC(String ram, String hdd, String cpu) {
		super();
		this.ram = ram;
		this.hdd = hdd;
		this.cpu = cpu;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.factory.Computer#getRAM()
	 */
	@Override
	public String getRAM() {
		// TODO Auto-generated method stub
		return this.ram;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.factory.Computer#getHDD()
	 */
	@Override
	public String getHDD() {
		// TODO Auto-generated method stub
		return this.hdd;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.factory.Computer#getCPU()
	 */
	@Override
	public String getCPU() {
		// TODO Auto-generated method stub
		return this.cpu;
	}

}
