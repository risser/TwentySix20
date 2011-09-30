package com.twentysix20.kata.fraction;

import javax.swing.JOptionPane;

public class Fraction {

	public static Fraction ZERO = new Fraction(0);
	public static Fraction ONE = new Fraction(1);

	private int numerator; // the numerator
	private int denominator; // the denominator

	public Fraction(int numerator, int denominator) {

		// dealing with x/0 throwing exception
		if (denominator == 0) {
			throw new IllegalArgumentException("Denominator must be > 0");
		}

		this.numerator = numerator;
		this.denominator = denominator;
	}

	// input constructor
	public Fraction() {

		int num, den;
		String numStr, denStr;
		while (true) {
			try {
				numStr = JOptionPane.showInputDialog(null, "Input numerator:");
				if (numStr == null)
					System.exit(0);
				num = Integer.parseInt(numStr);
				denStr = JOptionPane
						.showInputDialog(null, "Input denominator:");
				if (denStr == null)
					System.exit(0);
				den = Integer.parseInt(denStr);
				if (den == 0) {
					// dealing with x/0 throwing exception
					throw new IllegalArgumentException(
							"Denominator must be > 0");
				}
				break;
			} catch (Exception e) {
				System.out.println(e);
			}

		}

		numerator = num;
		denominator = den;

	}

	// double constructor
	public Fraction(double d) {

		int offset = 1;
		while (d * offset != (int) (d * offset)) {
			offset *= 10;
		}
		numerator = (int) (d * offset);
		denominator = offset;

	}

	// string constructor
	public Fraction(String numerator, String denominator) {

		int num = Integer.parseInt(numerator);
		int den = Integer.parseInt(denominator);
		if (den == 0)
		{
			// dealing with x/0 throwing exception
			throw new IllegalArgumentException("Denominator must be > 0");
		}

		this.numerator = num;
		this.denominator = den;

	}

	// adding methods
	public Fraction add(Fraction arg) {
		return new Fraction(arg.numerator * denominator + numerator
				* arg.denominator, denominator * arg.denominator);

	}

	public Fraction add(int i) {
		return new Fraction(i * denominator + numerator, denominator);

	}

	public Fraction add(double d) {
		Fraction addedVal = new Fraction(d);
		return new Fraction(addedVal.numerator * denominator + numerator
				* addedVal.denominator, denominator * addedVal.denominator);

	}

	// end of adding methods

	// multiplication methods
	public Fraction mul(Fraction arg) {
		return new Fraction(arg.numerator * numerator, arg.denominator
				* denominator);

	}

	public Fraction mul(int i) {
		return new Fraction(i * numerator, denominator);

	}

	public Fraction mul(double d) {
		Fraction mulVal = new Fraction(d);
		return new Fraction(mulVal.numerator * numerator, mulVal.denominator
				* denominator);

	}

	// end of multiplications

	// subtraction methods
	public Fraction sub(Fraction arg) {
		return new Fraction(arg.numerator * denominator - numerator
				* arg.denominator, denominator * arg.denominator);

	}

	public Fraction sub(int i) {
		return new Fraction(numerator - i * denominator, denominator);
	}

	public Fraction sub(double d) {
		Fraction subVal = new Fraction(d);
		return new Fraction(subVal.numerator * denominator - numerator
				* subVal.denominator, denominator * subVal.denominator);

	}

	// end of subtraction

	// division methods
	public Fraction div(Fraction arg) {
		if (arg.numerator == 0) {
			throw new ArithmeticException("Dividing by 0 bad idea ;)");
		}

		int newDen = denominator * arg.numerator;
		int newNum = numerator * arg.denominator;
		return new Fraction(newNum, newDen);
	}

	public Fraction div(int i) {
		if (i == 0) {
			throw new ArithmeticException("Dividing by 0 bad idea ;)");
		}
		return new Fraction(numerator, denominator / i);

	}

	public Fraction div(double d) {
		if (d == 0) {
			throw new ArithmeticException("Dividing by 0 bad idea ;)");
		}

		Fraction divVal = new Fraction(d);
		int newDen = denominator * divVal.numerator;
		int newNum = numerator * divVal.denominator;
		return new Fraction(newNum, newDen);

	}

	// end of division

	// comparison methods
	public boolean equals(Fraction arg) {
		return arg.numerator * denominator == arg.denominator * numerator;

	}

	public boolean lessThan(Fraction arg) {
		if (arg.denominator * numerator < denominator * arg.numerator)
			return true;

		else
			return false;

	}

	public boolean greaterThan(Fraction arg) {
		if (arg.denominator * numerator > denominator * arg.numerator)
			return true;

		else
			return false;

	}

	// problem with (-2,1), (1,-1) etc...
	public boolean lessThanOrEqual(Fraction arg) {
		if (arg.denominator * numerator <= denominator * arg.numerator)
			return true;

		else
			return false;

	}

	public boolean greaterThanOrEqual(Fraction arg) {
		if (arg.denominator * numerator >= denominator * arg.numerator)
			return true;

		else
			return false;

	}

	// end of comparsion

	public String toString() {
		Fraction red = reduce();
		return red.numerator + "\\" + red.denominator;
	}

	// Working reduction
	// I don't know how to implement "void reduce()"
	private Fraction reduce() //
	{
		int n = numerator;
		int gcd = denominator;
		int tmp;
		if (n < gcd) {
			tmp = n;
			n = gcd;
			gcd = tmp;
		}
		while (n != 0) {
			tmp = n;
			n = gcd % n;
			gcd = tmp;
		}
		return new Fraction(numerator / gcd, denominator / gcd);
	}

	public static void main(String[] args) {
		Fraction a = new Fraction();
		Fraction i = new Fraction(-2, 1);
		Fraction d = new Fraction(1, -1);
		Fraction s = new Fraction("25", "75");
		System.out.println(i.lessThanOrEqual(d));

		/*
		 * Printing resultsJOptionPane.showMessageDialog(null,
		 * "DEFINED RATIONAL NUMBERS:\n-------------------------------------------\n"
		 * + "User rational number: "+a + "\nRational [1] (integer): "+i +
		 * "\nRational [2] (double): "+d + "\nRational [3] (string): "+s +
		 * "\n-------------------------------------------\nADDITION:\n-------------------------------------------\n"
		 * 
		 * // Addition +a + " + " +i+ " = " + a.add(i) + "\n" +a + " + " +d+
		 * " = " + a.add(d) + "\n" +a + " + " +s+ " = " + a.add(s) + "\n" +d +
		 * " + " +s+ " = " + d.add(s) + "\n"
		 * 
		 * +
		 * "-------------------------------------------\nSUBTRACTION:\n-------------------------------------------\n"
		 * 
		 * // Subtraction +i + " - " +a+ " = " + a.sub(i) + "\n" +d + " - " +a+
		 * " = " + a.sub(d) + "\n" +s + " - " +a+ " = " + a.sub(s) + "\n" +d +
		 * " - " +s+ " = " + s.sub(d) + "\n"
		 * 
		 * +
		 * "-------------------------------------------\nMULTIPLICATION:\n-------------------------------------------\n"
		 * 
		 * // Multiplication +a + " * " +i+ " = " + a.mul(i) + "\n" +a + " * "
		 * +d+ " = " + a.mul(d) + "\n" +a + " * " +s+ " = " + a.mul(s) + "\n" +d
		 * + " * " +s+ " = " + s.mul(d) + "\n"
		 * 
		 * +
		 * "-------------------------------------------\nDIVISION:\n-------------------------------------------\n"
		 * 
		 * // Division +a + " : " +i+ " = " + a.div(i) + "\n" +a + " : " +d+
		 * " = " + a.div(d) + "\n" +a + " : " +s+ " = " + a.div(s) + "\n" +d +
		 * " : " +s+ " = " + s.div(d) + "\n"
		 * 
		 * +
		 * "-------------------------------------------\nCOMPARSIONS:\n-------------------------------------------\n"
		 * 
		 * // Comparsions +a + " = " +i+ " = " + a.equals(i) + "\n" +a + " < "
		 * +d+ " = " + a.lessThan(d) + "\n" +a + " > " +s+ " = " +
		 * a.greaterThan(s) + "\n" +a + " <= " +s+ " = " + a.lessThanOrEqual(s)
		 * + "\n" +a + " >= " +d+ " = " + a.greaterThanOrEqual(d) + "\n",
		 * "Results", JOptionPane.PLAIN_MESSAGE);
		 */
	}
}