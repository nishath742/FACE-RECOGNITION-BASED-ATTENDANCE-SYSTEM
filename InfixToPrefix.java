import java.io.*;
import java.util.*;

public class InfixToPrefix {
    public static void main(String argv[]) throws IOException {
        String infix;
        // Create an input stream object
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        // Get input from the user
        System.out.print("\nEnter the infix expression you want to convert: ");
        infix = keyboard.readLine();
        // Output as prefix
        System.out.println("Prefix expression for the given infix expression is: " + toPrefix(infix));
    }

    private static String toPrefix(String infix) {
        Stack<Character> operators = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        StringBuilder infixReversed = new StringBuilder(infix).reverse();

        for (int i = 0; i < infixReversed.length(); i++) {
            char symbol = infixReversed.charAt(i);

            if (Character.isLetterOrDigit(symbol)) {
                postfix.append(symbol);
            } else if (symbol == ')') {
                operators.push(symbol);
            } else if (symbol == '(') {
                while (!operators.isEmpty() && operators.peek() != ')') {
                    postfix.append(operators.pop());
                }
                if (!operators.isEmpty() && operators.peek() == ')') {
                    operators.pop(); // Pop the ')'
                }
            } else {
                while (!operators.isEmpty() && precedence(symbol) < precedence(operators.peek())) {
                    postfix.append(operators.pop());
                }
                operators.push(symbol);
            }
        }

        while (!operators.isEmpty()) {
            postfix.append(operators.pop());
        }

        return postfix.reverse().toString();
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            default:
                return -1; // Lower precedence
        }
    }
}
