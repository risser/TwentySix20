package com.twentysix20.kata.potter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class DiscountSet {
	private Set<Integer> books = new HashSet<Integer>();
	
	public BigDecimal cost() {
		BigDecimal cost = BookBasket.BOOK_COST.multiply(new BigDecimal(books.size())).multiply(BookBasket.DISCOUNT[books.size()]);
		return cost;
	}

	public void addBook(int bookNumber) {
		if (hasBook(bookNumber))
			throw new IllegalArgumentException("Can't add a book that is already in the group.");
		books.add(bookNumber);
	}

	public boolean hasBook(int bookNumber) {
		return books.contains(bookNumber);
	}

	public void optimizeWith(DiscountSet otherBook) {
		if ((size() != 5) && (size() != 3))
			return;
		if ((otherBook.size() != 5) && (otherBook.size() != 3))
			return;
		if (otherBook.size() == size())
			return;

		DiscountSet five = (size() == 5 ? this : otherBook);
		DiscountSet three = (size() == 3 ? this : otherBook);

		for (int book : five.getBookSet())
			if (!three.hasBook(book)) {
				five.getBookSet().remove(book);
				three.addBook(book);
				return;
			}
	}

	private Set<Integer> getBookSet() {
		return books;
	}

	private int size() {
		return books.size();
	}
}
