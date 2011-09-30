package com.twentysix20.cardstore.supplyrecord;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class SupplyRecords implements Set<SupplyRecord> {
	private Set<SupplyRecord> records = new HashSet<SupplyRecord>();

	public SupplyRecords() {}

	public SupplyRecords(SupplyRecord...records) {
		for (SupplyRecord record : records)
			add(record);
	}

	public SupplyRecords(Collection<? extends SupplyRecord> c) {
		addAll(c);
	}
//
//	@Override
//	public Set<SupplyRecord> recordSet() {
//		return records;
//	}

	@Override
	public boolean add(SupplyRecord e) {
		return records.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends SupplyRecord> c) {
		return records.addAll(c);
	}

	public boolean addAll(StandardSupplyRecord[] array) {
		return records.addAll(Arrays.asList(array));
	}

	@Override
	public void clear() {
		records.clear();
	}

	@Override
	public boolean contains(Object o) {
		return records.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return records.containsAll(c);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof SupplyRecords)) return false;
		SupplyRecords other = (SupplyRecords)o;
		return records.equals(other.records);
	}

	@Override
	public boolean isEmpty() {
		return records.isEmpty();
	}

	@Override
	public Iterator<SupplyRecord> iterator() {
		return records.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return records.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return records.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return records.retainAll(c);
	}

	@Override
	public int size() {
		return records.size();
	}

	@Override
	public Object[] toArray() {
		return records.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return records.toArray(a);
	}

	@Override
	public String toString() {
		return records.toString();
	}
}