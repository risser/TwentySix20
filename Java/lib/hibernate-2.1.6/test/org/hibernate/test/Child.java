//$Id: Child.java,v 1.3 2004/06/04 01:27:35 steveebersole Exp $
package org.hibernate.test;

public class Child {
	private Parent parent;
	private int count;
	private int x;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public Parent getParent() {
		return parent;
	}
	
	
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	
	
	public int getCount() {
		return count;
	}
	
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public long getId() {
		return parent.getId();
	}
	private void setId(long id) {
	}
	
}







