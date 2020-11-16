/**
 * Scoreboard class for final output and choosing games.
 *
 * @author Christian Autor
 * @version final version
 */

public class Scoreboard { //class
  private int playerTotalScore, computerTotalScore; //player and computer scores
  private final int PLAYERCODE = 1; //1 if player wins
  private final int COMPUTERCODE = 0; //0 if computer wins
  private final int TIECODE = -1; //-1 for a tie case

  /**
    * Scoreboard constructor
    *
    * @param  none
    * @return none
    */

  public Scoreboard() {
    playerTotalScore = computerTotalScore = 0;
  }

  /**
    * incrementScore method
    *
    * @param  int addScore
    * @return none
    */

  public void incrementScore(int addScore) {
    if(addScore == PLAYERCODE)
      playerTotalScore++;
    else
      computerTotalScore++;
  }

  /**
    * getWinner private method
    *
    * @param  none
    * @return COMPUTERCODE || PLAYERCODE || TIECODE
    */

  public int getWinner() {
    if(computerTotalScore > playerTotalScore)
      return COMPUTERCODE;
    else if(playerTotalScore > computerTotalScore)
      return PLAYERCODE;
    else
      return TIECODE;
  }

  /**
    * getWinner printboard method
    *
    * @param  none
    * @return none
    */

  public void printboard() {
    System.out.println("\n" + "Scoreboard: \nPlayer: " + playerTotalScore + "\t\tComputer: " + computerTotalScore);
  }

}
