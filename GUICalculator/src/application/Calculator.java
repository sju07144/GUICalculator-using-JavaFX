package application;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Calculator {
	private ExpressionManager expressionManager;
	
	public Calculator() {
		expressionManager = new ExpressionManager();
	}
	
	public Calculator(String expression) {
		expressionManager = new ExpressionManager(expression);
	}
	
	public void setExpression(String expression) {
		expressionManager.setExpression(expression);
	}
	
	public String calculate() {
		String result = "";
		
		try {
			expressionManager.expressionToPostfix();
			ArrayList<String> postfix = expressionManager.getPostfix();
			
			while (postfix.size() != 1) {
				int size = postfix.size();
				
				for (int i = 0; i < size; i++) {
					String op = postfix.get(i);
					if (Utility.isOperator(op)) {
						BigDecimal operand0 = new BigDecimal(postfix.get(i - 2)); // 정확한 연산을 위해 BigDecimal 사용
						BigDecimal operand1 = new BigDecimal(postfix.get(i - 1));
						
						String tempResult = "";
						
						switch (op) {
						case "+":
							tempResult = Double.toString(operand0.add(operand1).doubleValue());
							break;
						case "-":
							tempResult = Double.toString(operand0.subtract(operand1).doubleValue());
							break;
						case "x":
							tempResult = Double.toString(operand0.multiply(operand1).doubleValue());
							break;
						case "/":
							tempResult = Double.toString(operand0.doubleValue() / operand1.doubleValue()); // 분수 계산 시, 무한소수가 제대로 계산이 안됨.
							break;
						}
						
						postfix.set(i, tempResult);
						postfix.remove(i - 1);
						postfix.remove(i - 2);
						
						break;
					}
				}
			}
			
			result = postfix.get(0);
			
			if (Utility.isNumber(result))
				result = result.substring(0, result.indexOf("."));
		}
		
		catch (Exception e) {
			result = e.getMessage();
			return result;
		}
		
		return result;
	}
}