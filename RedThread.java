/**
 * RedThread game from game of games
 *
 * @author Christian Autor
 * @version final version
 */

import java.util.*;

public class RedThread extends Game { //class
  private final int PLAYER_CODE = 1;
  private final int COMPUTER_CODE = 0;
  private final int GUESS_PLACEHOLDER = -1;
  private final int TOTAL_SPOOLS = 20;
  private final String MESSAGES[] = {"How many spools would you like to pick up at a time; 1-10", "Enter a spool number", "Computer is taking a turn", "You Win ", "You Lose "};
  private final String ERROR_MSGS[] = {"Invalid input, please enter an integer between 1-10", "Spool has already been picked. Please chose a number that has not yet been chosen"};
  private final String SORTED_SPOOL_COLORS[] = {"Orange", "Yellow", "Green", "Blue", "Indigo", "Violet", "Black", "White", "Brown", "Grey", "Maroon"};
  private final String RED_SPOOL_STR = "Red";
  private final String TAKEN_SPOOL = "Taken";
  private String unsortedSpoolColors[] = new String[TOTAL_SPOOLS];
  private int maxSpoolRemoval;
  private int redSpool;
  private int[] playerGuesses, computerGuesses;

  /**
    * redThreadMain method
    *
    * @param  Scoreboard scoreboard, GetInput getInput
    * @return none
    */

  public void Main(Scoreboard scoreboard, GetInput getInput) {
    GAME_NAME = "Find The Red Thread";
    GAME_RULES = "Players pick the number of spools to be removed at a time and then take turns taking n spools until one finds the red spool";
    displayRules(GAME_NAME, GAME_RULES);
    playerScore = opponentScore = 0;

    maxSpoolRemoval = getInput.getPlayerIntInput(MESSAGES[0], ERROR_MSGS[0], TOTAL_SPOOLS/2);

    playerGuesses = new int[maxSpoolRemoval];
    computerGuesses = new int[maxSpoolRemoval];
    Arrays.fill(playerGuesses, GUESS_PLACEHOLDER);
    Arrays.fill(computerGuesses, GUESS_PLACEHOLDER);

    redSpool = getNumberInRange(TOTAL_SPOOLS-1);
    unsortedSpoolColors[redSpool] = RED_SPOOL_STR;

    for(int i = 0; i<TOTAL_SPOOLS; i++) {
      if(i != redSpool)
        unsortedSpoolColors[i] = SORTED_SPOOL_COLORS[getNumberInRange(SORTED_SPOOL_COLORS.length)-1];
    }

    while(!(playerScore>bestOf/2 || opponentScore>bestOf/2)) {
      displaySpools();
      Arrays.fill(playerGuesses, GUESS_PLACEHOLDER);
      Arrays.fill(computerGuesses, GUESS_PLACEHOLDER);

      for(int i = 0; i<maxSpoolRemoval; i++) {
        playerGuesses[i] = getInput.getPlayerIntInput(MESSAGES[1], MESSAGES[1], TOTAL_SPOOLS) -1;
        while(!(guessIsValid(playerGuesses[i])) || arrayContains(i, playerGuesses))
          playerGuesses[i] = getInput.getPlayerIntInput(ERROR_MSGS[1], MESSAGES[1], TOTAL_SPOOLS) -1;
      }

      printGuesses(playerGuesses);
      if(checkGuesses(playerGuesses, redSpool)) {
        playerScoresAPoint();
        printWin(true);
        break;
      }

      System.out.println(MESSAGES[2]);
      try
      { Thread.sleep(2500); }
      catch (Exception e) { }

      for(int i = 0; i<maxSpoolRemoval; i++) {
        while(!guessIsValid(computerGuesses[i]))
          computerGuesses[i] = getNumberInRange(TOTAL_SPOOLS-1);
      }

      printGuesses(computerGuesses);
      if(checkGuesses(computerGuesses, redSpool))
      {
        opponentScoresAPoint();
        printWin(false);
        break;
      }

    }
    if(playerScore > opponentScore)
      scoreboard.incrementScore(PLAYER_CODE);
    else
      scoreboard.incrementScore(COMPUTER_CODE);
  }

  /**
    * displaySpools method
    *
    * @param  none
    * @return none
    */

  private void displaySpools() {
    System.out.print("\n");
    for(int i = 0; i<unsortedSpoolColors.length; i++) {
      if(unsortedSpoolColors[i].equals(TAKEN_SPOOL))
        System.out.print(unsortedSpoolColors[i] + " | ");
      else
        System.out.print(i+1 + " | ");
    }
    System.out.println("\n\n");
  }

  /**
    * guessIsValid method
    *
    * @param  int guess
    * @return boolean
    */

  private boolean guessIsValid(int guess) {
    if(guess == GUESS_PLACEHOLDER)
      return false;
    else if(unsortedSpoolColors[guess].equals(TAKEN_SPOOL))
      return false;
    return true;
  }

  /**
    * arrayContains method
    *
    * @param  int guessIndex, int[]guesses
    * @return boolean
    */

  private boolean arrayContains(int guessIndex, int[] guesses) {
    for(int i = 0; i<guesses.length; i++)
    {
      if(guesses[guessIndex] == guesses[i] && i!=guessIndex)
        return true;
    }
    return false;
  }

  /**
    * printGuesses method
    *
    * @param  int[] guess
    * @return none
    */

  private void printGuesses(int[] guess) {
    System.out.print("\nGuesses\n");
    for(int i = 0; i<guess.length; i++)
      System.out.print(unsortedSpoolColors[guess[i]] + " | ");
    System.out.print("\n\n");
  }

  /**
    * checkGuesses method
    *
    * @param  int[] guess, int answer
    * @return boolean
    */

  private boolean checkGuesses(int[] guess, int answer) {
    boolean win = false;
    for(int i = 0; i<guess.length; i++) {
      if(checkAnswer(guess[i], answer))
        win = true;
      else
        unsortedSpoolColors[guess[i]] = TAKEN_SPOOL;
    }
    return win;
  }

  /**
    * checkAnswer method
    *
    * @param  T guess, T answer
    * @return boolean
    */

  protected <T> boolean checkAnswer(T guess, T answer) {
    if(guess == answer)
      return true;
    return false;
  }

  /**
    * printWin method
    *
    * @param  boolean win
    * @return none
    */

  protected void printWin(boolean win) {
    if(win)
      System.out.println("\n\n" + MESSAGES[3] + GAME_NAME);
    else
      System.out.println("\n\n" + MESSAGES[4] + GAME_NAME);
  }

}
