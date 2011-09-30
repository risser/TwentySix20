//$Id: D.java,v 1.3 2004/06/04 01:27:35 steveebersole Exp $
package org.hibernate.test;

public class D {
	private Long id;
	private float amount;
	private A reverse;
	
	public D() {
		// try to induce an infinite loop in the lazy-loading machinery
		setAmount(100.0f);
		getAmount();
	}	
	/**
	 * Returns the amount.
	 * @return float
	 */
	public float getAmount() {
		return amount;
	}
	
	/**
	 * Returns the id.
	 * @return long
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the amount.
	 * @param amount The amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	/**
	 * Sets the id.
	 * @param id The id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	public A getReverse() {
		return reverse;
	}

	public void setReverse(A a) {
		reverse = a;
	}

}






