package org.hibernate.test;

import java.io.Serializable;

public class NestingComponent implements Serializable {
	private ComponentCollection nested;
	public ComponentCollection getNested() {
		return nested;
	}

	public void setNested(ComponentCollection collection) {
		nested = collection;
	}

}
