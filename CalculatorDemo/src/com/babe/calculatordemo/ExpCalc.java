package com.babe.calculatordemo;

import java.util.Stack;
import java.util.StringTokenizer;

public class ExpCalc {

	public String ingoreWhitespace(String input) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < input.length(); i++) {
			if (!Character.isSpaceChar(input.charAt(i))) {
				result.append(input.charAt(i));
			}
		}
		return result.toString();
	}

	public double calcExp(String input) {
		input = ingoreWhitespace(input.trim());
		StringTokenizer tokenizer = new StringTokenizer(input, "+-¡Á¡Â", true);

		Stack<Double> numStack = new Stack<Double>();
		Stack<String> operStack = new Stack<String>();

		while (tokenizer.hasMoreTokens()) {
			String cur = tokenizer.nextToken();
			if (isNum(cur)) {
				numStack.push(Double.parseDouble(cur));

			} else {
				if (!operStack.isEmpty()
						&& comparePriority(cur, operStack.peek())) {
					double operand2 = numStack.pop();
					double operand1 = numStack.pop();
					double operResult = compute(operand1, operand2,
							operStack.pop());
					numStack.push(operResult);
					operStack.push(cur);
				} else {
					operStack.push(cur);
				}
			}
		}

		while (!operStack.isEmpty()) {
			compute(numStack, operStack);
		}
		return numStack.pop();
	}

	private double compute(double s1, double s2, String operator) {
		double operResult = 0;
		switch (operator) {
		case "+":
			operResult = s1 + s2;
			break;
		case "-":
			operResult = s1 - s2;
			break;
		case "¡Á":
			operResult = s1 * s2;
			break;
		case "¡Â":
			if (s2 == 0) {
				operResult = 0;
			} else {
				operResult = s1 / s2;
			}
			break;
		}
		return operResult;
	}

	private void compute(Stack<Double> numStack, Stack<String> operStack) {

		while (!operStack.isEmpty()) {
			double s2 = numStack.pop();
			double s1 = numStack.pop();
			String operator = operStack.pop();
			numStack.push(compute(s1, s2, operator));
		}
	}

	private boolean comparePriority(String cur, String operatorInStack) {
		if (operatorInStack.equals("¡Á") || operatorInStack.equals("¡Â")) {
			return true;
		} else if ((operatorInStack.equals("+") || operatorInStack.equals("-"))
				&& ((cur.equals("+") || cur.equals("-")))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNum(String curElem) {
		if (curElem.equals("+") || curElem.equals("-") || curElem.equals("¡Á")
				|| curElem.equals("¡Â")) {
			return false;
		}
		return true;
	}

}
