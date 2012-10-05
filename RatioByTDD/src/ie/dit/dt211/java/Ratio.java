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
		if (denominator == 1) {
			return numerator + "";	// Coercion of int to String 
		}
		return numerator + "/" + denominator;
	}
	
	// The following method is required to allow JUnit to do its
	// job. The explanation for this will be provided in tutorials/lectures
	@Override public boolean equals(Object o) {
		boolean isEqual = false;
		if (o instanceof Ratio) {
			isEqual = (numerator == ((Ratio) o).numerator) &&
					  (denominator == ((Ratio) o).denominator);
		}
		return isEqual;
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

	public static Ratio add(Ratio r1, Ratio r2) {
		// TODO
		
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
		
		return null;
	}
	
	public Ratio add(Ratio r) {
		// Instance method version of add()
		return null;
	}
	
	public static Ratio subtract(Ratio r1, Ratio r2) {
		// TODO
		
		/*
		 * HINT: See add
		 */
		return null;
	}
	
	public Ratio subtract(Ratio r) {
		// Instance method version of subtract()
		return null;
	}
	
	public static Ratio multiply(Ratio r1, Ratio r2) {
		// TODO
		
		/*
		 * HINT: Multiplication is trivial
		 */
		return null;
	}
	
	public Ratio multiply(Ratio r) {
		// Instance method version of multiply()
		return null;
	}
	
	public static Ratio divide(Ratio r1, Ratio r2) {
		// TODO
		
		/*
		 * HINT: Multiply the reciprocal of one ratio by the other
		 */
		return null;
	}
	
	public Ratio divide(Ratio r) {
		// Instance method version of divide()
		return null;
	}
	
	public Ratio simplestForm() {
		// For example, ratios like 3/6 should be converted to 1/2
		return null;
	}
	
}
