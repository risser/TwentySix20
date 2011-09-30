package com.twentysix20.kata.potter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookBasket {
	protected static final BigDecimal BOOK_COST = new BigDecimal(8);
	protected static final BigDecimal[] DISCOUNT = new BigDecimal[]{
		new BigDecimal(1),
		new BigDecimal(1),
		new BigDecimal("0.95"),
		new BigDecimal("0.90"),
		new BigDecimal("0.80"),
		new BigDecimal("0.75")
	};
	private List<Integer> books = new ArrayList<Integer>();

	public BookBasket(int... bookNumbers) {
		for (int num : bookNumbers)
			addBook(num);
	}

	public BigDecimal cost() {
		Set<DiscountSet> groups = new HashSet<DiscountSet>();
		for (int bookNumber : books) {
			DiscountSet chosenGroup = null;
			for (DiscountSet group : groups)
				if (!group.hasBook(bookNumber)) {
					chosenGroup = group;
					break;
				}
			if (chosenGroup == null)
				chosenGroup = new DiscountSet();
			chosenGroup.addBook(bookNumber);
			groups.add(chosenGroup);
		}

		List<DiscountSet> groupList = new ArrayList<DiscountSet>(groups);
		for (int i = 0; i < groupList.size(); i++)
			for (int j = i + 1; j < groupList.size(); j++)
				groupList.get(i).optimizeWith(groupList.get(j));

		BigDecimal cost = BigDecimal.ZERO;
		for (DiscountSet group : groups)
			cost = cost.add(group.cost());
		return cost;
	}

	public void addBook(int bookNumber) {
		books.add(bookNumber);
	}
}