import java.util.Scanner;
import java.util.Stack;

public class JavaCalculator {
	
	public static final int TITLE_SCREEN = 0;
	public static final int CONTINUE_SCREEN = 1;
	public static final int ERROR_SCREEN = 2;
	public static final int EXIT_SCREEN = 3;
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		boolean exit = false;
		printMenu(TITLE_SCREEN);  //Print Welcome Screen
		while(!exit) {
			exit = calculator();
		}
		input.close();
	}
	
	public static boolean calculator() {
		String userInput = takeInput();
		if (userInput.equalsIgnoreCase("exit")) return true;
		else {
			String postfixExpression = inputInterpret(userInput);
			if (postfixExpression.equalsIgnoreCase("")) {
				printMenu(ERROR_SCREEN);
				return false;
			}
			else {
		    System.out.println("for testing, postfix expression: " + postfixExpression);
			printResult(postfixCalculator(postfixExpression));
			printMenu(CONTINUE_SCREEN);
			return false;
			}
		}
	}
	
	public static int postfixCalculator(String expression) {
		int temp = 1;
		return temp;
	}
	
	public static String takeInput() {
		//Scanner input = new Scanner(System.in);
		String inputString = input.nextLine();
		return inputString;
	}
	
	//takes user input mathematical expression
		//checks if valid input
		//if valid, returns postfix conversion of user input
		//if invalid, returns ""
	public static String inputInterpret(String input) {
        String invalidExpression = "";
		String postfixExpression = "";
        Stack<Character> stack = new Stack<Character>();
        boolean lastCharacterDigit = false;
        
        for (int i = 0; i < input.length(); i++) {
        	
        	//process whitespace
        	while (input.charAt(i) == ' ') {
        		++i;
            }

        	//process number values
            if (Character.isDigit(input.charAt(i))) { 
            	if (lastCharacterDigit) return invalidExpression;
            	
            	postfixExpression += input.charAt(i);
            	if (i+1 >= input.length() || !Character.isDigit(input.charAt(i+1))) {            	
            		postfixExpression += ' ';
            		lastCharacterDigit = true;
                } 
            }

            //process operators
            else if (precedence(input.charAt(i)) != -1) {
            	if (!lastCharacterDigit) return invalidExpression;
            	
                while ((!stack.isEmpty()) && (precedence(stack.peek()) >= precedence(input.charAt(i))) && (stack.peek() != '(')) {
                	postfixExpression += stack.pop();
                	postfixExpression += ' ';
                }
                stack.push(input.charAt(i));
                lastCharacterDigit = false;
            }
            
            else return invalidExpression;
        }

        while (!stack.isEmpty()) {
        	postfixExpression += stack.pop();
            postfixExpression += ' ';
        }
        return postfixExpression;
}
	
	public static int precedence(char operator){
	    if(operator == '*' || operator == '/'){ //higher precedence
            return 1;
        }
        else if(operator == '+' || operator == '-'){  // lower precedence
            return 0;
        }
        return -1; // not an operator
    }
	
	private static void printMenu(int option) {
		switch(option) {
		case 0:
			System.out.println("I would like a mathematical expression please.");
			break;
			
        case 1:
        	System.out.println("Keep going! (or type exit)");
			break;
			
        case 2:
        	System.out.println("Invalid, try again");
        	break;
			
        case 3:
        	System.out.println("Bye forever");
	        break;
		}
	}
	
	private static void printResult(int result) {
		String resultString = "Result: " + result;
		System.out.println(resultString);
	}
}
