/**
 * CoinFlip game from game of games
 *
 * @author Wayne Sassano
 * @version final version
 */

import java.util.*;

public class CoinFlip extends Game {
  private int playerMode=0;
  private final static int PLAYER_IS_GUESSER = 1;
  private final String VALID_ANSWERS_GAME_ROLE[] = {"Guesser", "Flipper", "guesser", "flipper"};
  private final String VALID_ANSWERS_FLIP[] = {"flip", "Flip"};

  /**
    * coinFlipMain method
    *
    * @param  Scoreboard scoreboard, GetInput getInput
    * @return none
    */

  public void Main(Scoreboard scoreboard, GetInput getInput) {
    GAME_NAME = "Coin Flip";
    GAME_RULES = "Instructions: Choose to either be the 'flipper' or 'guesser'. If you choose guesser, you choose either 'heads' or 'tails'. Otherwise, you flip the coin.";
  	displayRules(GAME_NAME, GAME_RULES);
  	bestOf = getInput.getPlayerIntInputOdd("Enter a number of rounds to play best out of: ", "Error: Please enter an odd integer: ");

  	playerScore = opponentScore = 0;

  	String modeChoice = getInput.getPlayerStringInput("Enter 'Guesser' if you would like to play as the Guesser, or 'Flipper' to be the Flipper as the Computer Guesses:  "," Error: Please enter either 'Guesser' or 'Flipper': " ,VALID_ANSWERS_GAME_ROLE);

  	if(modeChoice.equals("Guesser") || modeChoice.equals("guesser")) {
  		playerMode = PLAYER_IS_GUESSER;
  	}


    while(playerScore <= bestOf / 2 && opponentScore <= bestOf / 2 ) {
  	  if(playerMode == PLAYER_IS_GUESSER){

    		int guess = getInput.getPlayerIntInput("Enter your guess as 1 for Heads or 2 for Tails:  ","Error: Please enter your guess as 1 for Heads or 2 for Tails:", 2);
    		int result = getNumberInRange(2);
    		if(checkAnswer(result, guess)) {
    			playerScoresAPoint();
    		}
        else {
    			opponentScoresAPoint();
    		}
    	}

      else {
    		String flipCommand = getInput.getPlayerStringInput("Enter 'Flip' to flip the coin:","Error: Please enter 'Flip' to flip the coin:" , VALID_ANSWERS_FLIP);

    		if(flipCommand.equals("Flip") || flipCommand.equals("flip")){
    			int result = getNumberInRange(2);
    			int computerGuess = getNumberInRange(2);
				String sideOfCoin[] = {"Heads", "Tails"};
				System.out.println("Computer guessed " + sideOfCoin[computerGuess-1] + " and you flipped " + sideOfCoin[result-1]);
    			if (checkAnswer(computerGuess, result)){
    					opponentScoresAPoint();
    		  }
          else{
      				playerScoresAPoint();
      		}
    	  }
    	}
    }

    if(playerScore >= bestOf / 2) {
    	scoreboard.incrementScore(1);
    }
    else {
    	scoreboard.incrementScore(0);
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
