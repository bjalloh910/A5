import java.util.ArrayDeque;
import java.util.Queue;


public class Calculate {
   /**
   * Converts the input infix expression tokens to postfix notation (Reverse Polish Notation).
   * 
   * @param tokens The input infix expression tokens in a queue.
   * @return A queue containing the tokens in postfix notation, or null if there are mismatched parentheses.
   */
  public static Queue<Object> Calculation(Queue<Object> tokens){
    //instance of the type arraydeque 
    ArrayDeque<Character> operatorstack = new ArrayDeque<>();
    ArrayDeque<Object> outputqueue = new ArrayDeque<>();
      
    while(!tokens.isEmpty()){ //While there are tokens to be read
      Object token = tokens.poll(); // Read a token
         
      if( token instanceof Double){ //check if it's a number
        outputqueue.add((Double) token);  // Add it to the output queue
      }
      if( token instanceof Character){ //if it's character 
        char queueOperator = (char) token; //converting the token from a object to a primitive type to use it as a char variable
        while(!operatorstack.isEmpty() && operatorstack.peek() != '(' && hasHigherPrecedence(operatorstack.peek(), queueOperator)){ //while there is an operator token at the top of the stack (the "stack operator"), and the stack operator has greater precedence than the queue operator,
          outputqueue.add(operatorstack.pop()); //pop the stack operator off the stack and add it to the output queue.
        }
        operatorstack.push(queueOperator); //when no more high-precedence stack operators remain, finally push the queue operator onto the stack.
      } else if(token.equals('(')){ //if left
         operatorstack.push((char) token); //push it to stack
      } else if(token.equals(')')){ //if right
        while(!operatorstack.isEmpty() && operatorstack.peek() != '(') { //Until the token at the top of the stack is a left parenthesis
        outputqueue.add(operatorstack.pop()); //pop operators off the stack and put it into the output queue
        }
        if(!operatorstack.isEmpty() && operatorstack.peek() == '('){  // Pop the left parenthesis from the stack, but not onto the output queue
          operatorstack.pop();
        } else { // If the stack runs out without finding a left parenthesis
          System.out.println("Mismatched parentheses."); //then there are mismatched parentheses.
          return null; 
        }
      }
    }
    while(!operatorstack.isEmpty()){ // Pop any remaining operators from the stack to the output queu
        if (operatorstack.peek() == '(') { //If the token on the top of the stack is a parenthesis
          System.out.println("Mismatched parentheses."); //then there are mismatched parentheses.
          return null;
      }
      outputqueue.add(operatorstack.pop()); //If it is an operator, pop it onto the output queue.
    }
    return outputqueue; // Return the postfix expression in the output queue
  }

  /**
 * Determines whether the operator at the top of the stack has higher precedence
 * than the operator in the input queue based on their precedence values.
 *
 * @param stackOperator The operator at the top of the stack.
 * @param queueOperator The operator in the input queue.
 * @return True if stackOperator has higher precedence, false otherwise.
 */
  private static boolean hasHigherPrecedence(char stackOperator, char queueOperator) {
    // Retrieve precedence values for the operators
    int stackPrecedence = getPrecedence(stackOperator);
    int queuePrecedence = getPrecedence(queueOperator);
    // Compare the precedence values of the operators
    return stackPrecedence > queuePrecedence;
  }
  /**
 * Retrieves the precedence value of the given operator.
 * Operators '+' and '-' have lower precedence (1), '*' and '/' have higher precedence (2),
 * and other operators are assumed to have precedence 0.
 *
 * @param operator The operator for which to determine the precedence.
 * @return The precedence value of the operator.
 */
  private static int getPrecedence(char operator){
    // Assign precedence values to operators: '+' and '-' have precedence 1, '*' and '/' have precedence 2
    if(operator == '+' || operator == '-'){
      return 1;
    } else if(operator == '*' || operator == '/'){
      return 2;
    }
    // Default precedence for other operators or non-operator characters is 0
    return 0;
  }
   //function that checks if it's an operator based on the object given to fix peek because you can't compare an object to a character for peek.
   public static void main(String[] args) {
   

}
  

    // Note: To evaluate the postfix expression, you would need to implement
    // an evaluation algorithm, which is not included in the provided code.

}
