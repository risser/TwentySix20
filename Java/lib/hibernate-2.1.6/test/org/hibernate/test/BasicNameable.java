//$Id: BasicNameable.java,v 1.3 2004/06/04 01:27:35 steveebersole Exp $
package org.hibernate.test;

/**
 * @author administrator
 *
 *
 */
public class BasicNameable implements Nameable {
	
	private String name;
	private Long id;
	
	/**
	 * @see Nameable#getName()
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @see Nameable#setName()
	 */
	public void setName(String n) {
		name = n;
	}
	
	/**
	 * @see Nameable#getKey()
	 */
	public Long getKey() {
		return id;
	}
	
	/**
	 * @see Nameable#setKey()
	 */
	public void setKey(Long k) {
		id = k;
	}
	
}






