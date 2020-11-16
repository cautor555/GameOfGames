/**
 * GuessTheNumber game from game of games
 *
 * @author Rakan AlZagha
 * @version final version
 */

import java.util.*;

public class GuessTheNumber extends Game { //class
  private final String WIN_GAME_OUTPUT = "\nWin!";
  private final String TRY_AGAIN_OUTPUT = "Try again!\n";
  private final String LOSE_GAME_OUTPUT = "\nComputer wins, you lose.";
  private final String GUESS_NUMBER_OUTPUT = "Pick a number within the range:";
  private final String ERROR_GUESS_NUMBER_OUTPUT = "Guess not within range:";
  private final int MAX_RANGE = 100;
  private int compRange;
  private int numGuesses; //best out of abstract
  private int guessingNumber; //get number in range abstract
  private int playerGuess;
  private int guessesTaken;

  /**
    * guessTheNumberMain method
    *
    * @param  Scoreboard scoreboard, GetInput getInput
    * @return none
    */

  public void Main(Scoreboard scoreboard, GetInput getInput) {
      GAME_NAME = "Guess the Number";
      GAME_RULES = "Rules: The computer will set the range of a number and number of guesses. The computer will then think of a number within the given range. The player guessing will then be given the range and number of guesses they get to guess the number. If you guess the number within the given number of guesses you win, otherwise you lose.";
      displayRules(GAME_NAME, GAME_RULES);

      playerScore = opponentScore = 0;

      compRange = getNumberInRange(MAX_RANGE);

      numGuesses = getNumberInRange(compRange/2);
      setBestOf(numGuesses);

      guessingNumber = getNumberInRange(compRange);

      displayRangeAndGuess(compRange, bestOf);

      guessesTaken = 1;

      while(guessesTaken < MAX_RANGE){
          playerGuess = getInput.getPlayerIntInput(GUESS_NUMBER_OUTPUT, ERROR_GUESS_NUMBER_OUTPUT, compRange);
          if(checkAnswer(playerGuess, guessingNumber)){
              System.out.println(WIN_GAME_OUTPUT);
              playerScoresAPoint();
              guessesTaken++;
              break;
          }
          else{
              if(guessesTaken < bestOf){
                System.out.println(TRY_AGAIN_OUTPUT);
                guessesTaken = guessesTaken + 1;
                continue;
              }
              else{
                opponentScoresAPoint();
                System.out.println(LOSE_GAME_OUTPUT);
                break;
              }
          }
      }

    if(getPlayerScore() == 1){
      scoreboard.incrementScore(1);
    }
    else{
      scoreboard.incrementScore(0);
    }
  }

  /**
    * displayRangeAndGuess method
    *
    * @param  int range, int guesses
    * @return none
    */

  public void displayRangeAndGuess(int range, int guesses) {
    System.out.println("\nRange is 1 : " + range);
    System.out.println("Guesses: " + guesses + "\n");
  }

  /**
    * numInRange method
    *
    * @param  int guess, int compRange
    * @return boolean
    */

  public boolean numInRange(int guess, int compRange) {
      if(guess <= compRange) {
          return true;
      }
      else {
          return false;
      }
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
