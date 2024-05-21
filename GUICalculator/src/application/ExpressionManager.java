package application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class ExpressionManager {
	private String expression;
	private ArrayList<String> infix;
	private ArrayList<String> postfix;
	
	public ExpressionManager() {
		expression = "";
		infix = new ArrayList<>();
		postfix = new ArrayList<>();
	}
	
	public ExpressionManager(String expression) {
		this.expression = expression;
		
		this.infix = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(this.expression, "+-x/()", true);
		while (tokenizer.hasMoreTokens()) {
			this.infix.add(tokenizer.nextToken());
		}
		
		// 음수 처리
		if (this.infix.get(0).equals("-")) {
			String subOp = this.infix.remove(0);
			this.infix.set(0, subOp + this.infix.get(0));
		}
		
		for (int i = 1; i < this.infix.size(); i++) {
			String curStr = this.infix.get(i);
			String prevStr = this.infix.get(i - 1);
			if (curStr.equals("-") && Utility.isOperator(prevStr) && !prevStr.equals(")")) {
				String subOp = this.infix.remove(i);
				this.infix.set(i, subOp + this.infix.get(i));
			}
		}
		
		this.postfix = new ArrayList<>();
	}
	
	public void setExpression(String expression) {
		this.expression = expression;
		StringTokenizer tokenizer = new StringTokenizer(this.expression, "+-x/()", true);
		while (tokenizer.hasMoreTokens()) {
			infix.add(tokenizer.nextToken());
		}
		
		// 음수 처리
		if (this.infix.get(0) == "-") {
			String subOp = this.infix.remove(0);
			this.infix.set(0, subOp + this.infix.get(0));
		}
		
		int size = this.infix.size();
		for (int i = 1; i < size; i++) {
			String curStr = this.infix.get(i);
			String prevStr = this.infix.get(i - 1);
			if (curStr.equals("-") && Utility.isOperator(prevStr) && !prevStr.equals(")")) {
				String subOp = this.infix.remove(i);
				this.infix.set(i, subOp + this.infix.get(i));
			}
		}

	}
	
	public String getExpression() {
		return expression;
	}
	
	public ArrayList<String> getInfix() {
		return infix;
	}
	
	public ArrayList<String> getPostfix() {
		return postfix;
	}
	
	public void expressionToPostfix() throws Exception {
		String last = infix.getLast();
		if (Utility.isOperator(last) && !last.equals(")"))
			throw new Exception("Invalid expression!");
		
		Stack<String> operatorStack = new Stack<>();
		
		int infixSize = infix.size();
		for (int i = 0; i < infixSize; i++) {
			String s = infix.get(i);
			if (Utility.isNumber(s) || Utility.isFloatingPoint(s)) {
				postfix.add(s);
			}
			else if (Utility.isPercentage(s)) { // 퍼센트를 소수로 변환
				s = Utility.percentageToFloatingPoint(s);
				BigDecimal percentage = new BigDecimal(s); // 정확한 소수 연산을 위해 BigDecimal 사용
				double result = percentage.multiply(BigDecimal.valueOf(0.01)).doubleValue();
				s = Double.toString(result);
				postfix.add(s);
			}
			else if (Utility.isOperator(s)) {
				if (operatorStack.empty() && !s.equals("("))
					operatorStack.push(s);
				
				else if (s.equals("(")) {
					if (i != 0) {
						String prev = infix.get(i - 1);
						if (Utility.isNumber(prev) || prev.equals(")"))
							operatorStack.push("x");
					}
					
					operatorStack.push(s);
				}
				
				else if (s.equals(")")) {
					while (true) {
						String top = operatorStack.pop();
						if (top.equals("("))
							break;
						
						postfix.add(top);
					}
				}
				
				else {
					String top = operatorStack.getLast();
					
					if (Utility.operationOrder(top) < Utility.operationOrder(s)) {
						operatorStack.push(s);
					}
					
					else {
						while (!operatorStack.empty()) {
							String operator = operatorStack.pop();
							
							if (operator.equals("(")) {
								operatorStack.push(operator);
								break;
							}
							
							postfix.add(operator);
						}
						operatorStack.push(s);
					}
				}
				
			}
			else 
				throw new Exception("Invalid expression!");
		}
		
		while (!operatorStack.empty()) {
			String operator = operatorStack.pop();
			postfix.add(operator);
		}
	}
}
