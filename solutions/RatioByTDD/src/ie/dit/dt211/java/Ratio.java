package ie.dit.dt211.java;

public class Ratio {

	private int numerator;
	private int denominator;

	public Ratio(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public int getNumerator() {
		return numerator;
	}

	public int getDenominator() {
		return denominator;
	}

	@Override public String toString() {
		Ratio r = this.simplestForm();
		if (r.denominator == 1) {
			return r.numerator + "";	// Coercion of int to String 
		}
		return r.numerator + "/" + r.denominator;
	}
	
	// The following method is required to allow JUnit's assertEquals() to do its
	// job. The explanation for this will be provided in tutorials/lectures
	@Override public boolean equals(Object o) {
		if (o instanceof Ratio) {
			Ratio r1 = this.simplestForm();
			Ratio r2 = ((Ratio)o).simplestForm();
			return (r1.numerator == r2.numerator) && (r1.denominator == r2.denominator);
		}
		return false;
	}
	
	// Compute the greatest common divisor of two integers using
	// the Euclidean algorithm
	private static int gcd(int a, int b) {
		while (b != 0) {
			int tmp = a;
			a = b;
			b = tmp % b;
		}
		return a < 0 ? -a : a;
	}
	
	private static int lcm(int a, int b) {
		return (a * b) / gcd(a, b);
	}

	public static Ratio add(Ratio r1, Ratio r2) {
		/*
         * HINT: To add ratios you need to compute what is called the lowest
         * common multiple (LCM) of their denominators. One possible way of
         * computing the LCM of two integers a and b, is:
         * 
         *      lcm = (a * b) / gcd(a, b)
         * 
         * The greatest common divisor (GCD) is the number evenly divisible
         * into two integers. There is an method for calculating the GCD
         * called the Euclidean algorithm shown above.
         */
		int d = lcm(r1.denominator, r2.denominator);
		int n1 = d / r1.denominator * r1.numerator;
		int n2 = d / r2.denominator * r2.numerator;
		return new Ratio(n1 + n2, d);
	}
	
	public Ratio add(Ratio r) {
		// Instance method version of add()
		return Ratio.add(this, r);
	}
	
	public static Ratio subtract(Ratio r1, Ratio r2) {
		/*
		 * HINT: See add
		 */
		int d = lcm(r1.denominator, r2.denominator);
		int n1 = d / r1.denominator * r1.numerator;
		int n2 = d / r2.denominator * r2.numerator;
		return new Ratio(n1 - n2, d);
	}
	
	public Ratio subtract(Ratio r) {
		// Instance method version of subtract()
		return Ratio.subtract(this, r);
	}
	
	public static Ratio multiply(Ratio r1, Ratio r2) {
		/*
		 * HINT: Multiplication is trivial
		 */
		return new Ratio(r1.numerator * r2.numerator, r1.denominator * r2.denominator).simplestForm();
	}
	
	public Ratio multiply(Ratio r) {
		// Instance method version of multiply()
		return Ratio.multiply(this, r);
	}
	
	public static Ratio divide(Ratio r1, Ratio r2) {
		/*
		 * HINT: Multiply the reciprocal of one ratio by the other
		 */
		return new Ratio(r1.numerator * r2.denominator, r1.denominator * r2.numerator).simplestForm();
	}
	
	public Ratio divide(Ratio r) {
		// Instance method version of divide()
		return Ratio.divide(this, r);
	}
	
	public Ratio simplestForm() {
		int gcd = gcd(numerator, denominator);
		return new Ratio(numerator / gcd, denominator / gcd);
	}
	
}
