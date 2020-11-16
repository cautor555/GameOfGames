/**
 * GetInput class to handle input and errors for all games
 *
 * @author Rakan AlZagha, Wayne Sassano, Christian Autor, and Min Jie Teh
 * @version final version
 */

import java.util.*;

public class GetInput { //class
  private Scanner scanner; //scanner

  /**
		* getInput constructor
		*
		* @param  none
		* @return none
		*/

  public GetInput() {
    scanner = new Scanner(System.in);
  }

  /**
		* getPlayerStringInput method
		*
		* @param  String prompt, String errorMsg, String validInput[]
		* @return input
		*/

  public String getPlayerStringInput(String prompt, String errorMsg, String validInput[]) {
    String input;
    System.out.println(prompt);
    while(true) {
      if(scanner.hasNext()) {
        input = scanner.next();
        for(int i = 0; i<validInput.length; i++) {
          if(validInput[i].equals(input))
            return input;
        }
      }
      System.out.println(errorMsg);
    }
  }

  /**
		* getPlayerIntInput method
		*
		* @param  String prompt, String errorMsg, int upperbound
		* @return input
		*/

  public int getPlayerIntInput(String prompt, String errorMsg, int upperbound) {
    int input;
    String clearIncorrectInput;
    System.out.println(prompt);
    while(true) {
      if(scanner.hasNextInt()) {
        input = scanner.nextInt();
        if (input <= upperbound && input > 0) {
          return input;
        }
      }
      else
        clearIncorrectInput = scanner.next();
      System.out.println(errorMsg);
    }
  }

  /**
		* getPlayerIntInputOdd method
		*
		* @param  String prompt, String errorMsg
		* @return input
		*/

  public int getPlayerIntInputOdd(String prompt, String errorMsg) {
    int input;
    String clearIncorrectInput;
    System.out.println(prompt);
    while(true) {
      if(scanner.hasNextInt()) {
        input = scanner.nextInt();
        if (input % 2 > 0)
          return input;
      }
      else
        clearIncorrectInput = scanner.next();
      System.out.println(errorMsg);
    }
  }
}
