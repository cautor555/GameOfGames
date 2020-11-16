/**
 * FindTheThimble game from game of games
 *
 * @author Min Jie Teh
 * @version final version
 */

import java.util.*;

public class FindTheThimble extends Game { //class
  private String userGuess = "";
  private String rightAnswer = "L";
  private final int MAX_GUESS = 2;
  private final String RIGHT_GUESS_MSG = "You guessed right";
  private final String WRONG_GUESS_MSG = "You guessed wrong";
  private final String USER_WON_MSG = "\nYou Win!";
  private final String USER_LOST_MSG = "\nYou Lose!";
  private final String BEST_OF_PROMPT = "Enter a number of rounds to play best out of: ";
  private final String BEST_OF_ERROR_MSG = "Please enter an odd number";
  private final String USER_GUESS_PROMPT = "\nEnter L or R to pick a guess: ";
  private final String USER_GUESS_ERROR_MSG = "\nPlease enter L or R";
  private final String VALID_ANSWERS[] = {"L", "R", "l", "r"};

  /**
    * FindTheThimbleMain method
    *
    * @param  Scoreboard scoreboard, GetInput getInput
    * @return none
    */

  public void Main(Scoreboard scoreboard, GetInput getInput) {
    GAME_NAME = "Find The Thimble";
    GAME_RULES = "The computer will hide a thimble in either the left hand or right hand. You must guess a hand. If you guess correctly, you win the round. If you guess wrong, you lose the round.";
    displayRules(GAME_NAME, GAME_RULES);

    bestOf = getInput.getPlayerIntInputOdd(BEST_OF_PROMPT, BEST_OF_ERROR_MSG);

    playerScore = opponentScore = 0;

    while (!(playerScore>bestOf/2) && !(opponentScore>bestOf/2)){
      hideTheThimble();
      userGuess = getInput.getPlayerStringInput(USER_GUESS_PROMPT, USER_GUESS_ERROR_MSG, VALID_ANSWERS);

      if (checkAnswer(userGuess.toUpperCase(), rightAnswer)) {
        System.out.println(RIGHT_GUESS_MSG);
        playerScoresAPoint();
      }
      else {
        System.out.println(WRONG_GUESS_MSG);
        opponentScoresAPoint();
      }

    }

    if (playerScore>opponentScore) {
      System.out.println(USER_WON_MSG);
      scoreboard.incrementScore(1);
    }
    else {
      System.out.println(USER_LOST_MSG);
      scoreboard.incrementScore(0);
    }

  }

  /**
    * hideTheThimble method
    *
    * @param  none
    * @return none
    */

  private void hideTheThimble() {
    if (getNumberInRange(MAX_GUESS)==1)
      rightAnswer = "L";
    else
      rightAnswer = "R";
  }


  /**
    * checkAnswer abstract method
    *
    * @param  T guess, T answer
    * @return boolean
    */

  protected <T> boolean checkAnswer(T guess, T answer) {
    if(guess.equals(answer))
			return true;
    else
      return false;
  }

}
