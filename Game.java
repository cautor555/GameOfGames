/**
 * Abstract Game class that each game extends.
 *
 * @author Wayne Sassano
 * @version final version
 */

import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

public abstract class Game { //class
	protected int playerScore, opponentScore; //vars all concrete classes use
	protected int bestOf = 1; //bestOf default
	protected String GAME_RULES, GAME_NAME; //displayRules parameters

	/**
		* getNumberInRange method
		*
		* @param  int max
		* @return randomNum
		*/

	protected int getNumberInRange(int max) {
		int randomNum = ThreadLocalRandom.current().nextInt(1, max + 1);
		return randomNum;
	}

	/**
		* displauRules method
		*
		* @param  String gameName, String gameRules
		* @return none
		*/

	protected void displayRules(String GAME_NAME, String GAME_RULES) {
	    	System.out.println("\n" + GAME_NAME + " Rules: \n\n" + GAME_RULES + "\n");
 	}

	/**
		* setBestOf method
		*
		* @param  int bestOfNum
		* @return none
		*/

	protected void setBestOf(int bestOfNum) {
		bestOf = bestOfNum;
	}

	/**
		* getPlayerScore method
		*
		* @param
		* @return playerScore
		*/

	protected int getPlayerScore() {
		return playerScore;
	}

	/**
		* getOpponentScore method
		*
		* @param  none
		* @return opponentScore
		*/

	protected int getOpponentScore() {
		return opponentScore;
	}

	/**
		* playerScoresAPoint method
		*
		* @param  none
		* @return none
		*/

	protected void playerScoresAPoint() {
		playerScore++;
	}

	/**
		* opponentScoresAPoint method
		*
		* @param  none
		* @return none
		*/

	protected void opponentScoresAPoint() {
		opponentScore++;
	}

	/**
		* checkAnswer abstract method
		*
		* @param  T guess, T answer
		* @return boolean
		*/

	protected abstract <T> boolean checkAnswer(T guess, T answer);

	protected abstract void Main(Scoreboard scoreboard, GetInput getInput);
}
