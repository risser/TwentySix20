package com.twentysix20.kata.movie;

public class Transaction {
	private static final int CHILD_AGE = 13;
	private static final int SENIOR_AGE = 65;

	private static final float TICKET_GROUP = 6.0f;
	private static final float TICKET_NORMAL = 11f;
	private static final float TICKET_STUDENT = 8f;
	private static final float TICKET_SENIOR = 6f;
	private static final float TICKET_CHILD = 5.5f;
	
	private static final float DISCOUNT_MOVIE_DAY = 2.0f;
	private static final float CHARGE_LOGE = 2.0f;
	private static final float CHARGE_3D = 3.0f;
	private static final float CHARGE_LONG = 1.5f;
	private static final float CHARGE_WEEKEND = 1.5f;

	private Showing showing;
	private float total = 0;
	private int numberOfPeople = 0;

	public Transaction(Showing showing) {
		this.showing = showing;
	}

	public void buyTicket(int age, boolean isStudent) {
		float basePrice = baseTicketPrice(age, isStudent);
		total += adjustPrice(basePrice);
		numberOfPeople++;
	}

	private float adjustPrice(float basePrice) {
		float price = basePrice;
		if (showing.getDay() == Day.THU)
			price -= DISCOUNT_MOVIE_DAY;
		if (showing.getLength() > 120)
			price += CHARGE_LONG;
		if (showing.is3D())
			price += CHARGE_3D;
		if (showing.getFloor() == Floor.LOGE)
			price += CHARGE_LOGE;

		return price;
	}

	private float baseTicketPrice(int age, boolean isStudent) {
		float basePrice = isChild(age) ? TICKET_CHILD : isSenior(age) ? TICKET_SENIOR : (isStudent ? TICKET_STUDENT : TICKET_NORMAL);
		return basePrice;
	}

	private boolean isSenior(int age) {
		return age >= SENIOR_AGE;
	}

	private boolean isChild(int age) {
		return age <= CHILD_AGE;
	}

	private float groupTotal() {
		return TICKET_GROUP * numberOfPeople;
	}

	public float total() {
		if (isGroup())
			return (total < groupTotal()) ? total : groupTotal();
		else
			return total;
	}

	private boolean isGroup() {
		return numberOfPeople >= 20;
	}
}