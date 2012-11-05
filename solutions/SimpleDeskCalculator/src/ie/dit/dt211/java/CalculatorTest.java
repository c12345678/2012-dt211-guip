package ie.dit.dt211.java;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {

	Calculator calculator;
	
	public void makeCalculator(String tokens, boolean evaluate) {
		calculator = new Calculator(tokens, evaluate);
	}

	@Test public void singleDigit() {
		makeCalculator("1", false);
	}
	
	@Test public void integer() {
		makeCalculator("1 2", false);
	}

	@Test public void negativeInteger() {
		makeCalculator("~ 1", false);
	}
	
	@Test public void floatingPoint() {
		makeCalculator("1 . 0", false);
	}
	
	@Test public void singleDigitThenOperator() {
		makeCalculator("1 +", false);
	}
	
	@Test public void integerThenOperator() {
		makeCalculator("1 2 +", false);
	}
	
	@Test public void digitOperatorDigit() {
		makeCalculator("1 + 2", true);
		assertEquals(3.0, calculator.evaluate(), 0.0);
	}

	@Test public void simpleAddition() {
		makeCalculator("1 2 + 3 4", true);
		assertEquals(46.0, calculator.evaluate(), 0.0);
	}
	
	@Test public void expressionWithNegativeNumber() {
		makeCalculator("~ 1 2 + 3 4", true);
		assertEquals(22.0, calculator.evaluate(), 0.0);
	}
	
	@Test public void simpleSubtraction() {
		makeCalculator("3 . 4 - 1 . 2", true);
		assertEquals(2.2, calculator.evaluate(), 0.0);
	}
	
	@Test public void numberOperatorNumberOperator() {
		makeCalculator("1 2 + 3 4 x", false);
	}
	
	@Test public void numberOperatorNumberOperatorNumber() {
		makeCalculator("1 2 + 3 4 x 5 6", true);
		assertEquals(1916.0, calculator.evaluate(), 0.0);
	}
	
	@Test public void openParen() {
		makeCalculator("(", false);
	}
	
	@Test public void openParenThenNumber() {
		makeCalculator("( 1 2", false);
	}
	
	@Test public void justParenthesisedExpression() {
		makeCalculator("( 1 2 + 3 4 )", true);
		assertEquals(46.0, calculator.evaluate(), 0.0);
	}
	
	@Test public void expressionThenParenthesisedExpression() {
		makeCalculator("1 2 x ( 3 4 + 5 6 )", true);
		assertEquals(1080.0, calculator.evaluate(), 0.0);
	}
	
	@Test public void simpleFloatingPointExpression() {
		makeCalculator("1 . 2 x ( 3 . 4 + 5 . 6 )", true);
		assertEquals(10.799999999999999, calculator.evaluate(), 0.0);
	}
	
	@Test public void complexFloatingPointExpression() {
		makeCalculator("1 . 2 x ( 3 . 4 + 5 . 6 ) / 2 . 0 - ( 6 . 6 + 2 . 4 ) x ~ 0 . 3", true);
		assertEquals(8.1, calculator.evaluate(), 0.0);
	}
	
	@Test(expected=IllegalStateException.class)
	public void startWithPoint() {
		makeCalculator(".", false);
	}
	
	@Test(expected=IllegalStateException.class)
	public void startWithRparen() {
		makeCalculator(")", false);
	}
	
	@Test(expected=IllegalStateException.class)
	public void startWithOperator() {
		makeCalculator("x", false);
	}
	
	@Test(expected=IllegalStateException.class)
	public void operatorFollowOperator() {
		makeCalculator("2 x / 3", false);
	}
	
	@Test(expected=IllegalStateException.class)
	public void lparenFollowsOperand() {
		makeCalculator("2 (", false);
	}
	
	@Test(expected=IllegalStateException.class)
	public void operatorFollowsLparen() {
		makeCalculator("( x", false);
	}
	
	@Test(expected=IllegalStateException.class)
	public void pointNotFollowedByDigit() {
		makeCalculator("1 . +", false);
	}
	
	@Test(expected=IllegalStateException.class)
	public void pointFollowedByPoint() {
		makeCalculator("1 . .", false);
	}
	
	@Test(expected=IllegalStateException.class)
	public void doubleNegative() {
		makeCalculator("~ ~", false);
	}
	
	@Test(expected=IllegalStateException.class)
	public void pointAfterNegative() {
		makeCalculator("~ .", false);
	}
}
