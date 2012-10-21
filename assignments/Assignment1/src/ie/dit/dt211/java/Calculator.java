
package ie.dit.dt211.java;

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Simple backend for a radix 10 desktop calculator implemented using a variation of
 * Dijkstra's shunting-yard algorithm
 */
public class Calculator {
	private static final int S_START_OPERAND = 1;
	private static final int S_IN_OPERAND = 2;
	private static final int S_AFTER_POINT = 3;
	private static final int S_AFTER_SIGN = 4;
	private static final int S_AFTER_RPAREN = 5;
	
	private static final String digits = "0123456789";
	private static final String ops = "+-x/";
	private final Operator lparen = new Operator("(");
			
    Stack<Operator> opStack;
    ArrayList<String> postfix;
    StringBuilder sb;
    int state;
    boolean floatingPoint;
    int sign = 1;
    int nesting;
    
    /**
     * Constructs an infix Calculator calculator back-end ready to accept input tokens
     *
     * @throws IllegalStateException if the specified input tokens are not in valid
     * infix notation form
     */
    Calculator() {
    	reset();
    }
    
    /**
     * Constructs an infix Calculator calculator back-end, initialised with the
     * specified string of whitespace-separated input tokens, in standard infix form
     * and whether the specified input tokens are complete and suitable for evaluation
     * 
     * @throws IllegalStateException if the specified input tokens are not in valid
     * infix notation form
     */
    Calculator(String infix, boolean finish) {
    	this();
    	StringTokenizer st = new StringTokenizer(infix);
    	while (st.hasMoreTokens()) {
    		if (!append(st.nextToken())) {
    			throw new IllegalStateException(": " + state);
    		}
    	}
    	if (finish) {
    		finish();
    	}
    }

    /**
     * <p>Add a single infix input token to the calculator's pre-evaluation input. The caller
     * is expected to call append() for an many input tokens that exist the the infix
     * expression and then call finish() when done to indicate that the specified infix
     * expression is complete and ready for evaluation.</p>
     * 
     * <p>The valid input tokens are as follows</p>
     * <ul>
     * 	<li> Numeric digits [0-9] for specifying operands </li>
     * 	<li> Period [.] to specify the decimal point </li>
     *  <li> Left and right parentheses [()] for specifying subexpressions </li>
     *  <li> The negation operator [~] for specifying a negative sign on a subsequent operand
     *  <li> Arithmetic operators [+-x/] specifying add, subtract, multiply and divide respectively
     * </ul>
     * 
     * @param token A string specifying an input token
     * @return true if the specified input token is a valid next token for the current
     * pre-evaluation input token stream, i.e. it would be legal for infix notation and false
     * otherwise
     */
    public boolean append(String token) {
    	switch (state) {
    	case S_START_OPERAND:
    		if (token.charAt(0) == '~') {
    			state = S_AFTER_SIGN;
    			sign = -sign;
    			if (sign < 0) {
    				sb.append('-');
    			}
    		} else if (digits.contains(token)) {
    			state = S_IN_OPERAND;
    			sb.append(token);
    		} else if (token.charAt(0) == '(') {
    			opStack.push(new Operator(token));
    			nesting++;
    		} else {
    			return false;
    		}
    		break;
    	case S_IN_OPERAND:
    		if (digits.contains(token)) {
    			sb.append(token);
    		} else if (token.charAt(0) == '.') {
    			if (floatingPoint) {
    				return false;
    			} else {
    				sb.append(token);
    				floatingPoint = true;
    				state = S_AFTER_POINT;
    			}
    		} else if (token.charAt(0) == ')') {
    			operandEnd();
    			if (nesting > 0) {
    				while (!opStack.isEmpty() && !opStack.peek().equals(lparen)) {
                        postfix.add(opStack.pop().toString());
                    }
    				opStack.pop();
    				nesting--;
    			} else {
    				return false;
    			}
    			state = S_AFTER_RPAREN;
    		} else if (Calculator.isArithmeticOperator(token)) {
    			processOperator(token);
    		} else if (token.charAt(0) == '(') {
    			return false;
    		}
    		break;
    	case S_AFTER_POINT:
    	case S_AFTER_SIGN:
    		if (digits.contains(token)) {
    			sb.append(token);
    			state = S_IN_OPERAND;
    		} else {
    			return false;
    		}
    		break;
    	case S_AFTER_RPAREN:
    		if (Calculator.isArithmeticOperator(token)) {
    			processOperator(token);
    		} else {
    			return false;
    		}
    	}
        
        return true;
    }
    
    /**
     * <p>Indicate that the currently specified infix input stream is complete and is
     * ready for evaluation. It can be used as in the following example to test if
     * the already input tokens are valid and, if so, obtain the expression result.</p>
     * 
     * <p>Example:</p>
     * 
     * <pre>
     * 	Calculator calc = new Calculator();
     * 	.
     * 	.
     * 	.
     * 	try {
     * 		calc.finish();
     * 		double result = calc.evaluate();
     * 	} catch (IllegalStateException e) {
     * 		// Handle error
     * 	}
     * </pre>
     * 
     * @throws IllegalStateException if the already-specified input tokens do not
     * represent a complete and valid infix expression
     */
    public void finish() {
    	operandEnd();
    	if (isEvaluable()) {
	        while (!opStack.isEmpty()) {
	            postfix.add(opStack.pop().toString());
	        }
	        return;
    	}
    	throw new IllegalStateException(": " + state);
    }
    
    /**
     * Evaluate the current infix expression. Assumes that a number of append() 
     * calls and a finish() call have preceded
     * 
     * @return floating-point result
     */
    public double evaluate() {
    	if (!opStack.isEmpty()) {
        	throw new IllegalStateException(": " + state);
    	}
    	Stack<Double> stack = new Stack<Double>();
    	for (String token: postfix) {
    		double operand;
    		if (Calculator.isArithmeticOperator(token)) {
    			switch(token.charAt(0)) {
    			case '+': stack.push(stack.pop() + stack.pop()); break;
    			case '-': 
    				operand = stack.pop();
    				stack.push(stack.pop() - operand); break;
    			case 'x': stack.push(stack.pop() * stack.pop()); break;
    			case '/':
    				operand = stack.pop();
    				stack.push(stack.pop() / operand); break;
    			}
    		} else {
    			stack.push(Double.parseDouble(token));
    		}

    	}
    	return stack.pop();
    }
    
    /**
     * Reset the calculator, preparing it for a new infix input stream. Discards any
     * current state.
     */
    public void reset() {
    	opStack = new Stack<Operator>();
	    postfix = new ArrayList<String>();
	    sb = new StringBuilder();
	    state = S_START_OPERAND;
	    floatingPoint = false;
	    nesting = 0;
	    sign = 1;
    }
    
    /**
     * Tests if the specified input token is a valid arithmetic operator in this calculator
     * 
     * @param op the input token to text
     * @return true if valid, false otherwise
     */
	public static boolean isArithmeticOperator(String op) {
		return ops.contains(op);
	}
	
	/*
	 * Class private methods
	 */
	private boolean isEvaluable() {
		int operands = 0;
		int operators = 0;
		for (String token: postfix) {
			if (Calculator.isArithmeticOperator(token)) {
				operators++;
			} else {
				operands++;
			}
		}
		for (Operator op: opStack) {
			if (op.equals(lparen)) {
				return false;
			}
			operators++;
		}
		if (operands - operators != 1) {
			return false;
		}
		return true;
	}
    
	private void processOperator(String token) {
		operandEnd();
		Operator op = new Operator(token);
		while (!opStack.isEmpty() && opStack.peek().precedence() >= op.precedence()) {
		    postfix.add(opStack.pop().toString());
		}
		opStack.push(op);
	}
	
	private void operandEnd() {
		if (sb.length() != 0) {
			postfix.add(sb.toString());
		}
		sb = new StringBuilder();
		state = S_START_OPERAND;
		floatingPoint = false;
	}
	
	/**
	 * Helper class modeling an infix operator and associated precedence
	 */
    private class Operator {
        String op;
        int precedence;
        
        Operator(String op) throws IllegalArgumentException {
            char c = op.charAt(0);
            switch (c) {
            case '+':
            case '-': precedence = 1; break;
            case 'x':
            case '/': precedence = 2; break;
            case '(': precedence = 0; break;
            case ')': precedence = 0; break;
            default:
                throw new IllegalArgumentException();
            }
            this.op = op;
        }
        
        public int precedence() {
            return precedence;
        }
        
        @Override public String toString() {
            return op;
        }
        
        @Override public boolean equals(Object o) {
        	return (o instanceof Operator && this.op.compareTo(((Operator)o).op) == 0);
        }
    }
            
}
