/**
 * PlayGames game from game of games
 *
 * @author Christian Autor and contributors
 * @version final version
 */

public class PlayGames {
  private static boolean continuePlay = true;
  private static int currentGame;
  private final static int NUM_GAMES = 5;
  private final static String INITIAL_PROMPT = "You can choose between 5 different games to play against the computer. You can play as many games as you want, and you get 1 point for each game you win. If you have more points than the computer when you quit, then you win. Otherwise, you lose!";
  private final static String CHOOSE_GAME_PROMPT = "Input number to select game to play (1: Find the Thimble, 2: Coin Flip, 3: Guess the Number, 4: Even Odd, 5: Find the Red Thread)";
  private final static String CHOOSE_GAME_ERROR = "Invalid selection, please choose again";
  private final static String CONTINUE_PROMPT = "Input 'quit' to quit Game of Games or 'cont' to continue playing";
  private final static String CONTINUE_ERROR = "invalid input, please enter 'quit' or 'cont'";
  private final static String QUIT = "quit";
  private final static String CONTINUE = "cont";
  private final static String WIN = "You win!";
  private final static String LOSE = "You lose!";
  private final static String TIE = "You tie!";
  private final static int PLAYERCODE = 1; //1 if player wins
  private final static int COMPUTERCODE = 0; //0 if computer wins
  private final static int TIECODE = -1; //-1 for a tie case

  /**
    * main method
    *
    * @param  String args[]
    * @return none
    */

  public static void main(String args[]) {
    System.out.println(INITIAL_PROMPT);
    Scoreboard scoreboard = new Scoreboard();
    GetInput getInput = new GetInput();

    while(continuePlay) {
      currentGame = getInput.getPlayerIntInput(CHOOSE_GAME_PROMPT, CHOOSE_GAME_ERROR, NUM_GAMES);
      Game game = new FindTheThimble();

      switch(currentGame) {
        case 1:
          game = new FindTheThimble();
          break;
        case 2:
          game = new CoinFlip();
          break;
        case 3:
          game = new GuessTheNumber();
          break;
        case 4:
          game = new EvenOrOdd();
          break;
        case 5:
          game = new RedThread();
          break;
      }
      game.Main(scoreboard, getInput);

      scoreboard.printboard();

      if(getInput.getPlayerStringInput(CONTINUE_PROMPT, CONTINUE_ERROR, new String[]{QUIT,CONTINUE}).equals(QUIT))
        continuePlay = false;
    }
    System.out.println("\n\n");

    if(scoreboard.getWinner() == PLAYERCODE)
      System.out.println(WIN);
    else if(scoreboard.getWinner() == COMPUTERCODE)
      System.out.println(LOSE);
    else
      System.out.println(TIE);

    System.out.println("\n");
    scoreboard.printboard();
  }
}
