package application;

import java.lang.StringBuffer;

import java.util.Stack;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class GUIManager {
	private BorderPane root;
	private TextArea textArea; // 계산기 출력 영역
	
	private VBox vBox; // 출력 영역과 버튼 모음을 수직적으로 나열
	
	private HBox[] hBoxes; // 버튼을 수평적으로 나열
	
	private Button button0;
	private Button dotButton;
	private Button eraseButton;
	private Button resultButton;
	
	private Button button1;
	private Button button2;
	private Button button3;
	private Button addButton;
	
	private Button button4;
	private Button button5;
	private Button button6;
	private Button subButton;
	
	private Button button7;
	private Button button8;
	private Button button9;
	private Button mulButton;
	
	private Button clearButton;
	private Button bracketButton;
	private Button percentButton;
	private Button divButton;
	
	private Stack<String> bracketStack; // 괄호 버튼 클릭 시, 열린 괄호 닫힌 괄호 출력 여부를 판단하기 위한 자료구조
	
	private void setButtons() {
		button0 = new Button();
		button0.setText("0");
		button0.setFont(Font.font(50));
		button0.setPrefSize(200, 90);
		dotButton = new Button();
		dotButton.setText(".");
		dotButton.setFont(Font.font(50));
		dotButton.setPrefSize(200, 90);
		eraseButton =  new Button();
		eraseButton.setText("<=");
		eraseButton.setFont(Font.font(50));
		eraseButton.setPrefSize(200, 90);
		resultButton = new Button();
		resultButton.setText("=");
		resultButton.setFont(Font.font(50));
		resultButton.setPrefSize(200, 90);
		hBoxes[4].getChildren().addAll(button0, dotButton, eraseButton, resultButton);
		
		button1 = new Button();
		button1.setText("1");
		button1.setFont(Font.font(50));
		button1.setPrefSize(200, 90);
		button2 = new Button();
		button2.setText("2");
		button2.setFont(Font.font(50));
		button2.setPrefSize(200, 90);
		button3 = new Button();
		button3.setText("3");
		button3.setFont(Font.font(50));
		button3.setPrefSize(200, 90);
		addButton = new Button();
		addButton.setText("+");
		addButton.setFont(Font.font(50));
		addButton.setPrefSize(200, 90);
		hBoxes[3].getChildren().addAll(button1, button2, button3, addButton);
		
		button4 = new Button();
		button4.setText("4");
		button4.setFont(Font.font(50));
		button4.setPrefSize(200, 90);
		button5 = new Button();
		button5.setText("5");
		button5.setFont(Font.font(50));
		button5.setPrefSize(200, 90);
		button6 = new Button();
		button6.setText("6");
		button6.setFont(Font.font(50));
		button6.setPrefSize(200, 90);
		subButton = new Button();
		subButton.setText("-");
		subButton.setFont(Font.font(50));
		subButton.setPrefSize(200, 90);
		hBoxes[2].getChildren().addAll(button4, button5, button6, subButton);
		
		button7 = new Button();
		button7.setText("7");
		button7.setFont(Font.font(50));
		button7.setPrefSize(200, 90);
		button8 = new Button();
		button8.setText("8");
		button8.setFont(Font.font(50));
		button8.setPrefSize(200, 90);
		button9 = new Button();
		button9.setText("9");
		button9.setFont(Font.font(50));
		button9.setPrefSize(200, 90);
		mulButton = new Button();
		mulButton.setText("x");
		mulButton.setFont(Font.font(50));
		mulButton.setPrefSize(200, 90);
		hBoxes[1].getChildren().addAll(button7, button8, button9, mulButton);
		
		clearButton = new Button();
		clearButton.setText("C");
		clearButton.setFont(Font.font(50));
		clearButton.setPrefSize(200, 90);
		bracketButton = new Button();
		bracketButton.setText("()");
		bracketButton.setFont(Font.font(50));
		bracketButton.setPrefSize(200, 90);
		percentButton = new Button();
		percentButton.setText("%");
		percentButton.setFont(Font.font(50));
		percentButton.setPrefSize(200, 90);
		divButton = new Button();
		divButton.setText("/");
		divButton.setFont(Font.font(50));
		divButton.setPrefSize(200, 90);
		hBoxes[0].getChildren().addAll(clearButton, bracketButton, percentButton, divButton);
	}
	
	public GUIManager() {
		root = new BorderPane();
		textArea = new TextArea(); 
		textArea.setPrefSize(800, 150);
		textArea.setFont(Font.font(50));
		
		vBox = new VBox(4);
		vBox.setPrefSize(800, 600);
		
		hBoxes = new HBox[5];
		for (int i = 0; i < 5; i++) {
			hBoxes[i] = new HBox(4);
			hBoxes[i].setPrefSize(800, 90);
		}
		
		setButtons();
		
		bracketStack = new Stack<String>(); 
	}
	
	public void setGUI() {
		root.setCenter(vBox); // vBox를 borderPane 가운데에 위치하도록 설정
		vBox.getChildren().addAll(textArea, hBoxes[0], hBoxes[1], hBoxes[2], hBoxes[3], hBoxes[4]);
	}
	
	public void setButtonEvent() {
		button0.setOnAction(event -> textArea.setText(textArea.getText() + "0"));
		dotButton.setOnAction(event -> textArea.setText(textArea.getText() + "."));
		eraseButton.setOnAction(event -> {
			String currentText = textArea.getText();
			if (!currentText.equals("")) { // 공백 스트링을 판단하지 않을 경우, currentTextBuffer.length() - 1 == -1 값이 나와 오류 출력. 이를 방지하기 위함.
				StringBuffer currentTextBuffer = new StringBuffer(currentText);
				currentTextBuffer = currentTextBuffer.deleteCharAt(currentTextBuffer.length() - 1);
				textArea.setText(currentTextBuffer.toString());
			}
		});
		resultButton.setOnAction(event -> {
			String expression = textArea.getText();
			if (!expression.equals("")) {
				Calculator calculator = new Calculator(expression);
				String result = calculator.calculate();
				textArea.setText(result);
			}
		}); 
		
		button1.setOnAction(event -> textArea.setText(textArea.getText() + "1"));
		button2.setOnAction(event -> textArea.setText(textArea.getText() + "2"));
		button3.setOnAction(event -> textArea.setText(textArea.getText() + "3"));
		addButton.setOnAction(event -> {
			String currentText = textArea.getText();
			if (!currentText.equals("")) { // 공백 스트링에 연산자가 먼저 시작하지 않도록 판단(add, sub, mul, div 모두 동일)
				String lastStr = currentText.substring(currentText.length() - 1);
				if (Utility.isOperator(lastStr) && !lastStr.equals("(") && !lastStr.equals(")")) { // 괄호 아닌 연산자 앞의 스트링이 숫자인지 아닌지 판단(add, sub, mul, div 모두 동일)
					StringBuffer currentTextBuffer = new StringBuffer(currentText);
					currentTextBuffer = currentTextBuffer.deleteCharAt(currentTextBuffer.length() - 1); // 연산자 옆에 또 다른 연산자를 두지 않게 하기 위함.(add, sub, mul, div 모두 동일)
					textArea.setText(currentTextBuffer.toString() + "+");
				}
				else {
					textArea.setText(currentText + "+");
				}
			}
		});
		
		button4.setOnAction(event -> textArea.setText(textArea.getText() + "4"));
		button5.setOnAction(event -> textArea.setText(textArea.getText() + "5"));
		button6.setOnAction(event -> textArea.setText(textArea.getText() + "6"));
		subButton.setOnAction(event -> {
			String currentText = textArea.getText();
			if (!currentText.equals("")) {
				String lastStr = currentText.substring(currentText.length() - 1);
				if (lastStr.equals("+")) { // 더하기 연산자의 경우, 옆에 빼기 연산자가 오게하지 않기 위함. 나머지는 음수를 위해 필요함.
					StringBuffer currentTextBuffer = new StringBuffer(currentText);
					currentTextBuffer = currentTextBuffer.deleteCharAt(currentTextBuffer.length() - 1);
					textArea.setText(currentTextBuffer.toString() + "-");
				}
				else {
					textArea.setText(currentText + "-");
				}
			}
			else {
				textArea.setText(currentText + "-");
			}
		});
		
		button7.setOnAction(event -> textArea.setText(textArea.getText() + "7"));
		button8.setOnAction(event -> textArea.setText(textArea.getText() + "8"));
		button9.setOnAction(event -> textArea.setText(textArea.getText() + "9"));
		mulButton.setOnAction(event -> {
			String currentText = textArea.getText();
			if (!currentText.equals("")) {
				String lastStr = currentText.substring(currentText.length() - 1);
				if (Utility.isOperator(lastStr) && !lastStr.equals("(") && !lastStr.equals(")")) {
					StringBuffer currentTextBuffer = new StringBuffer(currentText);
					currentTextBuffer = currentTextBuffer.deleteCharAt(currentTextBuffer.length() - 1);
					textArea.setText(currentTextBuffer.toString() + "x");
				}
				else {
					textArea.setText(currentText + "x");
				}
			}
		});
		
		clearButton.setOnAction(event -> {
			textArea.setText("");
			bracketStack.clear();
		});
		bracketButton.setOnAction(event -> {
			String expression = textArea.getText();
			if (expression.equals("")) {
				textArea.setText(expression + "(");
				bracketStack.push("(");
				return;
			}
			else {
				if (bracketStack.empty()) { // 괄호가 한 번도 나오지 않았다는 것을 의미
					textArea.setText(expression + "(");
					bracketStack.push("(");
					return;
				}
				
				String lastStr = expression.substring(expression.length() - 1);
				if (Utility.isNumber(lastStr)) {
					if (bracketStack.getLast() == "(") { // 숫자이고 열린 괄호가 마지막 괄효일 경우, 닫힌 괄호 출력
						textArea.setText(expression + ")");
						bracketStack.pop();
						return;
					}
					
					else {
						if (bracketStack.size() % 2 == 0) { // 열린 괄호와 닫힌 괄호는 항상 쌍으로 같이 있으므로 stack의 괄호 개수가 홀수이면 닫힌 괄호가 나와줘야 함.
							textArea.setText(expression + "(");
							bracketStack.push("(");
							return;
						}
						
						else {
							textArea.setText(expression + ")");
							bracketStack.pop();
							return;
						}
					}
				}
				
				else if (lastStr.equals(")")) {
					if (bracketStack.size() % 2 == 0) { // 열린 괄호와 닫힌 괄호는 항상 쌍으로 같이 있으므로 stack의 괄호 개수가 홀수이면 닫힌 괄호가 나와줘야 함.
						textArea.setText(expression + "(");
						bracketStack.push("(");
						return;
					}
					
					else {
						textArea.setText(expression + ")");
						bracketStack.pop();
						return;
					}
				}
				
				else { // 숫자가 아닐 경우 무조건 열린 괄호가 나와야 함.
					textArea.setText(expression + "(");
					bracketStack.push("(");
					return;
				}
			}
		});
		percentButton.setOnAction(event -> textArea.setText(textArea.getText() + "%"));
		divButton.setOnAction(event -> {
			String currentText = textArea.getText();
			if (!currentText.equals("")) {
				String lastStr = currentText.substring(currentText.length() - 1);
				if (Utility.isOperator(lastStr) && !lastStr.equals("(") && !lastStr.equals(")")) {
					StringBuffer currentTextBuffer = new StringBuffer(currentText);
					currentTextBuffer = currentTextBuffer.deleteCharAt(currentTextBuffer.length() - 1);
					textArea.setText(currentTextBuffer.toString() + "/");
				}
				else {
					textArea.setText(currentText + "/");
				}
			}
		});
	}
	
	public BorderPane getRoot() {
		return root;
	}
}