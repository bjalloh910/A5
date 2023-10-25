import java.util.Queue;
import java.util.ArrayDeque;

public class Postfix {

    public static double postFixCalulator(Queue<Object> tokens) {
        ArrayDeque<Double> stack = new ArrayDeque<>();

        while (!tokens.isEmpty()) {
            Object token = tokens.poll();

            if (token instanceof Double) {
                stack.push((Double) token);
            } else if (token instanceof Character) {
                char operator = (Character) token;

                // Check if there are at least two numbers in the stack before popping
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid postfix expression");
                }

                double number2 = stack.pop();
                double number1 = stack.pop();

                double result = preformCalculation(number1, number2, operator);
                stack.push(result);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid postfix expression");
        }

        return stack.pop();
    }

    public static double preformCalculation(double number1, double number2, char operator) {
        switch (operator) {
            case '+':
                return number1 + number2;
            case '-':
                return number1 - number2;
            case '*':
                return number1 * number2;
            case '/':
                if (number2 != 0) {
                    return number1 / number2;
                } else {
                    throw new ArithmeticException("Can't divide by 0");
                }
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }


    public static void main(String[] args) {
        System.out.println(args[0]);
        ArrayDeque<Object> result = Tokenizer.readTokens(args[0]);
        System.out.println(postFixCalulator(result));
/* 
        Tokenizer.readTokens("(3 + 5)");
        
        // Test input strings
        String input1 = "Hello World 123 + 45.67 ="; // Words, numbers, and symbols
        String input2 = "Testing 1 2 3"; // Words and numbers only

        // Create a Tokenizer object
        Tokenizer tokenizer = new Tokenizer();

        // Test the readTokens method with input1
        System.out.println("Tokens from input1:");
        ArrayDeque<Object> tokens1 = tokenizer.readTokens(input1);
        System.out.println(tokens1);

        // Test the readTokens method with input2
        System.out.println("Tokens from input2:");
        ArrayDeque<Object> tokens2 = tokenizer.readTokens(input2);
        System.out.println(tokens2);

         // Assuming tokens have been stored in a Queue named 'tokens'
         ArrayDeque<Object> tokens = new ArrayDeque<>();
         tokens.add(2.0);
         tokens.add(3.0);
         tokens.add('+');
         tokens.add(4.0);
         tokens.add('*');
 
         // Evaluate the postfix expression
         double result = postFixCalulator(tokens);
         System.out.println("Result: " + result); // Output: 20.0

         ArraysDeque<Object> tokens = new ArrayDequ
    */
    }
}
