import java.util.Scanner;
import java.io.StringReader;
import java.util.ArrayDeque;


/** 
 *  Shows use of StreamTokenizer.
 *  @author Nick Howe
 *  @version 26 September 2013
 */
public class Tokenizer {
  /** Pattern that matches on words */
  public static final String WORD = "[a-zA-Z]*\\b";

  /** Pattern that matches on arithmetic symbols */
  public static final String SYMBOL = "[^\\w]";

  /** 
   *  Converts the input string to a queue of tokens 
   *  @param input  the string to convert
   *  @return  the queue of tokens
   */
  public static ArrayDeque<Object> readTokens(String input) {
    Scanner scanner = new Scanner(new StringReader(input));
    ArrayDeque<Object> queue = new ArrayDeque<>();
    // Below is a complicated regular expression that will split the
    // input string at various boundaries.
    scanner.useDelimiter
      ("(\\s+"                            // whitespace
      +"|(?<=[a-zA-Z])(?=[^a-zA-Z])"      // word->non-word
      +"|(?<=[^a-zA-Z])(?=[a-zA-Z])"      // non-word->word
      +"|(?<=[^0-9\\056])(?=[0-9\\056])"  // non-number->number
      +"|(?<=[0-9\\056])(?=[^0-9\\056])"  // number->non-number
      +"|(?<=[^\\w])(?=[^\\w]))");        // symbol->symbol
    
    while (scanner.hasNext()) {
      if (scanner.hasNextDouble()) {
        queue.add(scanner.nextDouble());     // Tokens are being added to the queue 
      } else if (scanner.hasNext(WORD)) {
        queue.add(scanner.next(WORD));
      } else if (scanner.hasNext(SYMBOL)) {
        queue.add(scanner.next(SYMBOL).charAt(0));
      } else {
        queue.add(scanner.next());
      }
    }
    scanner.close();
    return queue;
  }
//using a deque 
  /** Run short test */
  public static void main(String[] args) {
    if (args.length==0) {
      System.err.println("Usage:  java Tokenizer <expr>");
    } else {
      readTokens(args[0]);
    }
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
    */
  }
}
