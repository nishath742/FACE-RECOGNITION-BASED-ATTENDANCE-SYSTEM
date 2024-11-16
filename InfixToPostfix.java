import java.io.*;
import java.util.*;

public class InfixToPostfix {
    public static void main(String argv[]) throws IOException {
        String infix;
        Scanner sc=new Scanner(System.in);
        System.out.print("\nEnter the infix expression you want to convert: ");
        infix = sc.nextLine();
        System.out.println("Postfix expression for the given infix expression is:" + toPostfix(infix));
    }

    private static String toPostfix(String infix) {
        Stack<Character> operators = new Stack<>();
        char symbol;
        String postfix = "";
        for (int i = 0; i < infix.length(); ++i) {
            // While there is input to be read
            symbol = infix.charAt(i);
            // If it's an operand, add it to the string
            if (Character.isLetter(symbol)) {
                postfix = postfix + symbol;
            } else if (symbol == '(') {
                // Push (
                operators.push(symbol);
            } else if (symbol == ')') {
                // Push everything back to (
                while (operators.peek() != '(') {
                    postfix = postfix + operators.pop();
                }
                operators.pop(); // Remove '('
            } else {
                // Print operators occurring before it that have greater precedence
                while (!operators.isEmpty() && !(operators.peek() == '(') && prec(symbol) <= prec(operators.peek())) {
                    postfix = postfix + operators.pop();
                }
                operators.push(symbol);
            }
        }
        while (!operators.isEmpty())
            postfix = postfix + operators.pop();
        return postfix;
    }

    static int prec(char x) {
        if (x == '+' || x == '-')
            return 1;
        if (x == '*' || x == '/' || x == '%')
            return 2;
        return 0;
    }
}
