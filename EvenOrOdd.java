/**
 * EvenOrOdd game from game of games
 *
 * @author Rakan AlZagha
 * @version final version
 */

import java.util.*;

public class EvenOrOdd extends Game { //class
  private final String BEST_OF_OUTPUT = "Input best-of number (must be an odd number)";
  private final String ERROR_BEST_OF_OUTPUT = "Invalid input, please enter a valid odd number";
  private final String EVEN_OR_ODD_OUTPUT = "Input odd to be odd player or even to be even player and press enter";
  private final String ERROR_EVEN_OR_ODD_OUTPUT =  "Invalid input, please enter odd or even";
  private final String PLAYER_THROW_OUTPUT = "Input number from 1 to 5 and press enter";
  private final String ERROR_PLAYER_THROW_OUTPUT = "Invalid input, please enter a number from 1 to 5";
  private final String WIN_ROUND_OUTPUT = "You win this round!\n";
  private final String LOSE_ROUND_OUTPUT = "You lost this round!\n";
  private final String WIN_GAME_OUTPUT = "You win the game!";
  private final String LOSE_GAME_OUTPUT = "You lose the game!";
  private final int MAX_INPUT = 5;
  private int bestOutOfNum;
  private int sum;
  private String playerChoice;
  private int playerThrow;
  private int compThrow;

  /**
    * evenOrOddMain method
    *
    * @param  Scoreboard scoreboard, GetInput getInput
    * @return none
    */

  public void Main(Scoreboard scoreboard, GetInput getInput) {
      GAME_NAME = "Even and Odd";
      GAME_RULES = "In this game, you choose to be either the odd player or the even player. Then, you and the computer both choose a number between 1 and 5. The two numbers are added. If the sum of the numbers is odd, the odd player wins. If the sum of numbers is even, the even player wins. You can choose how many rounds will be played to determine a winner. Goodluck!";
      displayRules(GAME_NAME, GAME_RULES);

      playerScore = opponentScore = 0;

      bestOutOfNum = getInput.getPlayerIntInputOdd(BEST_OF_OUTPUT, ERROR_BEST_OF_OUTPUT);
      setBestOf(bestOutOfNum);

      String validAnswers[] = {"even", "odd"};
      String playerChoice = getInput.getPlayerStringInput(EVEN_OR_ODD_OUTPUT, ERROR_EVEN_OR_ODD_OUTPUT, validAnswers);

      while(playerScore <= bestOf/2 && opponentScore <= bestOf/2) {
        compThrow = getNumberInRange(MAX_INPUT);

        int playerThrow = getInput.getPlayerIntInput(PLAYER_THROW_OUTPUT, ERROR_PLAYER_THROW_OUTPUT, MAX_INPUT);

        if(throwInRange(playerThrow, MAX_INPUT)) {
            setPlayerThrow(playerThrow);
            sumOfThrows(playerThrow, compThrow);
            System.out.println("\nPlayer Choice: " + playerChoice + "\nYour Throw: " + playerThrow + "\nSum of Throws: " + sum);
            if (playerChoice.equals("even") && (checkAnswer(getSum() % 2, 0))) {
                playerScoresAPoint();
                System.out.println(WIN_ROUND_OUTPUT);
            }
            else if (playerChoice.equals("odd") && !(checkAnswer(getSum() % 2, 0))) {
                playerScoresAPoint();
                System.out.println(WIN_ROUND_OUTPUT);
            }
            else {
                opponentScoresAPoint();
                System.out.println(LOSE_ROUND_OUTPUT);
            }
        }
      }

      if(getPlayerScore() > bestOf/2){
          System.out.println(WIN_GAME_OUTPUT);
          scoreboard.incrementScore(1);
      }

      else if(getOpponentScore() > bestOf/2){
        System.out.println(LOSE_GAME_OUTPUT);
        scoreboard.incrementScore(0);
      }
  }

  /**
    * throwInRange method
    *
    * @param  int guess, int MAX_INPUT
    * @return boolean
    */

  public boolean throwInRange(int guess, int MAX_INPUT) {
      if(guess <= MAX_INPUT) {
          return true;
      }
      else {
          return false;
      }
  }

  /**
    * setPlayerThrow method
    *
    * @param  int guess
    * @return none
    */

  public void setPlayerThrow(int guess) {
      playerThrow = guess;
  }

  /**
    * sumOfThrows method
    *
    * @param  int playerThrow, int compThrow
    * @return sum
    */

  public int sumOfThrows(int playerThrow, int compThrow) {
      sum = playerThrow + compThrow;
      return sum;
  }

  /**
    * getSum method
    *
    * @param  none
    * @return sum
    */

  public int getSum() {
      return sum;
  }

  /**
    * checkAnswer abstract method
    *
    * @param  T guess, T answer
    * @return boolean
    */

  protected <T> boolean checkAnswer(T guess, T answer) {
    if(guess == answer)
      return true;
    return false;
  }
}
