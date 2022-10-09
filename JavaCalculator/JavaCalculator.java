import java.util.Scanner;
import java.util.Stack;

public class JavaCalculator {
	
	public static final int TITLE_SCREEN = 0;                                                     //Constant values for UI messages
	public static final int INSTRUCTION_SCREEN = 1;
	public static final int ERROR_INVALID_CHARACTER = 2;
	public static final int ERROR_DOUBLE_OPERATOR = 3;
	public static final int ERROR_DOUBLE_NUMBER = 4;
	public static final int ERROR_END_OPERATOR = 5;
	public static final int EXIT_SCREEN = 6;
	static Scanner input = new Scanner(System.in);                                                //Create Scanner Object

	
	//Handles taking in user input
	//Passes input on to other functions to validate it as a mathematical expression, and calculate the result
	public static void main(String[] args) {
		boolean exit = false;
		printMenu(TITLE_SCREEN);                                                                   //Print welcome message
		while(!exit) {                                                                             //Run program until user enters "exit"
			printMenu(INSTRUCTION_SCREEN);                                                         //Print Instructions to user
			String userInput = input.nextLine();                                                   //Take user input
			if(userInput.equalsIgnoreCase("exit")) exit = true;                                    //Check if user chose to exit
			
			else {
				String postfixExpression = inputInterpret(userInput);                              //Validate input expression, convert to postfix if valid, print error if not
				
				if (!postfixExpression.equalsIgnoreCase("")) {                                     //If valid input, calculate result of expression, if invalid return to Instruction & Input screen
					System.out.println("for testing, postfix expression: " + postfixExpression);   //testing line
				    printResult(evaluatePostfix(postfixExpression));                               //Print Result of calculation, evaluatePostFix() returns an integer.
				}
			}
		}
		input.close();                                                                             //Close Scanner object
	}
	
	//------------NOT YET IMPLEMENTED------------
	//Takes integer postfix expression String (Whitespace separating each value and operator e.g. "5 10 6 * +")
	//Evaluates and returns the resulting integer
	public static int evaluatePostfix(String expression) {
		int temp = 1;
		return temp;
	}

	
	//-------------NEGATIVE VALUES IN USER INPUT NOT HANDLED YET---------------
	//Takes user-input mathematical expression (infix notation)
	//Checks if valid expression,
	//If valid, returns postfix conversion of infix expression
	//If invalid,  prints errors and returns ""
	public static String inputInterpret(String input) {
        String invalidExpression = "";                                                        //Initialising return Strings
		String postfixExpression = "";
        Stack<Character> stack = new Stack<Character>();
        boolean lastCharacterDigit = false;                                                   //Flag used for validating expressions. False if last character(s) processed was a numeric value, True if the last character processed was an operator
        
        for (int i = 0; i < input.length(); i++) {
        	//process whitespace
        	while (input.charAt(i) == ' ') {
        		++i;
            }

        	//process number values
            if (Character.isDigit(input.charAt(i))) {                                         //Check if character at position i is a numeric value
            	if (lastCharacterDigit) {                                                     
            		printMenu(ERROR_DOUBLE_NUMBER);                                           //If the character is a numeric value, and the last element of the user input processed was also a numeric value,
            		return invalidExpression;                                                 //An error message is printed and the string is said to be invalid, as there were two numbers after one another with no operator in between.
            	}
            	
            	postfixExpression += input.charAt(i);
            	if (i+1 >= input.length() || !Character.isDigit(input.charAt(i+1))) {            	
            		postfixExpression += ' ';
            		lastCharacterDigit = true;
                } 
            }

            //process operators
            else if (checkPrecedence(input.charAt(i)) != -1) {                                //Check if character at position i is an operator
            	if (!lastCharacterDigit) {
            		printMenu(ERROR_DOUBLE_OPERATOR);                                         //If the character is an operator, and the last element of the user input processed was also an operator,
            		return invalidExpression;                                                 //An error message is printed and the string is said to be invalid, as there were two operators after one another with no number in between.
            	}
            	
                while ((!stack.isEmpty()) && (checkPrecedence(stack.peek()) >= checkPrecedence(input.charAt(i))) && (stack.peek() != '(')) {
                	postfixExpression += stack.pop();
                	postfixExpression += ' ';
                }
                stack.push(input.charAt(i));
                lastCharacterDigit = false;
            }
            
            else {
        		printMenu(ERROR_INVALID_CHARACTER);                                            //Print Error message for invalid character used
        		return invalidExpression;
        	}
        }

        if(!lastCharacterDigit) {
            printMenu(ERROR_END_OPERATOR);                                            //Print Error message for invalid character used
		    return invalidExpression;
        }
        
        else {
            while (!stack.isEmpty()) {
            	postfixExpression += stack.pop();
                postfixExpression += ' ';
            }
            return postfixExpression;
        }
}
	
	//takes character of mathematical operator
	//returns an integer relating to its precedence level
	//returns -1 if not a valid operator
	public static int checkPrecedence(char operator){
	    if(operator == '*'){ //higher precedence
            return 1;
        }
        else if(operator == '+' || operator == '-'){  // lower precedence
            return 0;
        }
        return -1; // invalid operator
    }
	
	//takes integer to navigate to a specific message for the user and prints it.
    //return type void
	private static void printMenu(int option) {
		switch(option) {
		case 0:
			System.out.println("Hello welcome to the best calculator in this room");
			break;
			
        case 1:
        	System.out.print("Enter a simple mathematical expression (or \"exit\"): ");
			break;
			
        case 2:
        	System.out.println("invalid character, only numerics and +, -, * please thanks");
        	break;
			
        case 3:
        	System.out.println("two operators no number inbetween");
	        break;
	        
        case 4:
        	System.out.println("two numbers no operator inbetween");
        	break;
		
	    case 5:
    	    System.out.println("Can't end expression with an operator");
    	    break;
	    
	    case 6:
    	    System.out.println("Goodbye!");
    	    break;
	    }
		
	}
	
	//takes integer result from calculations and prints it to screen with message
	//return type void
	private static void printResult(int result) {
		String resultString = "Result: " + result;
		System.out.println(resultString);
	}
}
