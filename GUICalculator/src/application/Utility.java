package application;

public class Utility {
	
	public static boolean isNumber(String s) { 
		return s.matches("[-]?[0-9]+|[-]?[0-9]+[.][0]*");
	}
		
	public static boolean isFloatingPoint(String s) {
		return s.matches("[-]?[0-9]+[.][0-9]+");
	}

	public static boolean isPercentage(String s) {
		return s.matches("[-]?[0-9]+[%]|[-]?[0-9]+[.][0-9]+[%]");
	}
	
	public static boolean isOperator(String s) { // 괄호를 연산자에 표함시켜 후위 표기법으로 변환해주기 쉽게하기 위함
		return s.matches("[+]|[-]|[x]|[/]|[(]|[)]");
	}
	
	public static String percentageToFloatingPoint(String s) throws Exception {
		if (s.charAt(s.length() - 1) == '%')
			s = s.substring(0, s.length() - 1);
		else
			throw new Exception("Invalid expression!!");
		return s;
	}
	
	public static int operationOrder(String op) { // +- 연산자보다 x/ 연산자의 우선순위가 더 높은 것을 나타내기 위함
		switch (op) {
		case "+":
		case "-":
			return 1;
		case "x":
		case "/":
			return 3;
		default:
			return -1;
		}
	}
}
