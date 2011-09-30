//$Id: Jay.java,v 1.3 2004/06/04 01:27:35 steveebersole Exp $
package org.hibernate.test;

/**
 * @author Gavin King
 */
public class Jay {
	private long id;
	private Eye eye;
	/**
	 * @return Returns the eye.
	 */
	public Eye getEye() {
		return eye;
	}

	/**
	 * @param eye The eye to set.
	 */
	public void setEye(Eye eye) {
		this.eye = eye;
	}

	/**
	 * @return Returns the id.
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	public Jay() {}
	
	public Jay(Eye eye) {
		eye.getJays().add(eye);
		this.eye = eye;
	}

}
