import java.util.Scanner;
import java.util.Stack;

public class JavaCalculator {
	
	public static final int TITLE_SCREEN = 0;
	public static final int CONTINUE_SCREEN = 1;
	public static final int EXIT_SCREEN = 2;

	public static void main(String[] args) {
		boolean exit = false;
		printMenu(TITLE_SCREEN);  //Print Welcome Screen
		while(!exit) {
			System.out.println(postfixConversion(takeInput()));
			exit = true;
		}
		//Exit Program
	}
	
	public static String takeInput() {
		Scanner input = new Scanner( System.in );
		String inputString = input.next();
		return inputString;
	}
	
	
	
	
	public static int precedence(char x){
		//if(x=='(' || x==')') {
		//	return 2;
		//}
		
	    if(x=='*' || x=='/'){
            return 1;                        // second highest precedence
        }
        else if(x=='+' || x=='-'){
            // lowest precedence

            return 0;
        }
        return -1; // not an operator
    }

	public static String postfixConversion(String input) {
        String invalid = "";
		String postfixExpression = "";
        Stack<Character> stack = new Stack<Character>();
        
        for (int i = 0; i < input.length(); i++) {
        	while (input.charAt(i) == ' ') {
        		++i;
            }

            if (Character.isDigit(input.charAt(i))) { 
            	postfixExpression += input.charAt(i);
            	
            	if (i+1 >= input.length() || !Character.isDigit(input.charAt(i+1))) {            	
            		postfixExpression += ' ';
                } 
            }
            
            else if (input.charAt(i) == '(') {
                    stack.push(input.charAt(i));    
            }

            else if (precedence(input.charAt(i)) != -1) {
                while ((!stack.isEmpty()) && (precedence(stack.peek()) >= precedence(input.charAt(i))) && (stack.peek() != '(')) {
                	postfixExpression += stack.peek();
                	postfixExpression += ' ';    
                	stack.pop();
                }

                    stack.push(input.charAt(i));
            }

            else if (input.charAt(i) == ')') {
            	while (!stack.isEmpty() && stack.peek() != '(') {
            		postfixExpression += stack.peek();
            		stack.pop();
            	}
            	stack.pop();
            }
        }

        while (!stack.isEmpty()) {
        	postfixExpression += stack.pop();
            postfixExpression += ' ';
        }
        return postfixExpression;
}
	
	
	
	//takes user input mathematical expression
	//checks if valid input
	//if valid, returns postfix conversion of user input
	//if invalid, returns ""
	//private static String interpretString(String userInputString) {
	//	String invalidExpression, postfixConversion = "";
	//	
	//}

	private static void printMenu(int option) {
		switch(option) {
		case 0:
			System.out.println("I would like a mathematical expression please.");
			break;
			
        case 1:
        	System.out.println("Keep going! (or type exit)");
			break;
			
        case 2:
        	System.out.println("Bye forever");
	        break;
		}
	}
	
	private static void printResult(int result) {
		String resultString = "Result: " + result;
		System.out.print(resultString);
	}
}
