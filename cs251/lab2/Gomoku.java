import cs251.lab2.GomokuModel;
import cs251.lab2.GomokuGUI;
import java.util.Random;

/**
 * @author Linh Tran
 * CS251.004 
 * February 16, 2014
 * Visualizer Gomoku class implements GomokuModel. Have four classes
 * that are implemented called doClick, newGame, boardString, and setComputerPlayer.
 * The objective of this game for the player (user) to get five in a row diagnally,
 * vertically, or horizontally. The class has a setting which when user pass COMPUTER
 * into command line the user can battle with computer player (AI). Once the user wins the
 * game restarts and the winner goes first. 
 */

public class Gomoku implements GomokuModel {

	Outcome gameResult = null; 
	private static boolean firstPlayTurn = true; 
	private static boolean computerPlayTurn = true;;
	private Square [][] board = new Square[GomokuModel.NUM_VSQUARES]
	[GomokuModel.NUM_HSQUARES];
	
public static void main ( String [] args) {
	Gomoku game = new Gomoku (); //creates a new instance of Gomoku game
	
	if (args.length > 0) { 
		game.setComputerPlayer (args[0]);
	}

	GomokuGUI.showGUI(game);
}

/**
* This method is called when the user clicks in the board. If the
* square is already occupied, nothing about the state of the game
* changes. If however, an empty square is clicked, then it should
* be filled with a value representing the player currently in
* turn. Method also contains the movement for computer player when 
* setComputerPlayer is enabled. Method also calls the dectectForWIn method
* to check the status of the game.
* @param takes in two ints row and col that is passed when user clicks board
* @return an Outcome called result
*/

public GomokuModel.Outcome doClick ( int row , int col ){
	
	
	int compRow = row; //computer player's row and col
	int compCol = col;

	Square sqClicked; 
	Square compClicked;
	sqClicked = board[row][col]; //got the square at the location of player's click
	compClicked = board[compRow][compCol]; // at the location of computer's piece


	gameResult  = GomokuModel.Outcome.GAME_NOT_OVER; //set game Outcome GAME_NOT_OVER default

		/**Checks Wwhose turn it is and
		   whether a square is empty when clicked and assign Square type
		   to the user */

		if (firstPlayTurn){ //if the first player turn then he will get CROSS
			if (sqClicked == GomokuModel.Square.EMPTY){
				sqClicked  = GomokuModel.Square.CROSS;
			}

		}

		else{  //else he will get a RING 
			if (sqClicked == GomokuModel.Square.EMPTY){
				sqClicked = GomokuModel.Square.RING;
			}

	}
		/**Checks if it is the computer player's turn and 
			whether a square is empty when clicked and assign Square type
		   to the user */
		if (computerPlayTurn){ //if user picks CROSS then computer player gets RING

			if (compClicked == GomokuModel.Square.EMPTY && 
				sqClicked== GomokuModel.Square.CROSS){ 
				compClicked = GomokuModel.Square.RING;
			}

			else { //else  computer player gets CROSS
				compClicked = GomokuModel.Square.CROSS;
			}	

			/**The computerPlayer's logic in movement. For loops through rows of the 
			   board if the index i is odd then the computer will have be in the same
			   colunm as the user except move down one square. When index is divisible by 
			   3 then his movement will be random both row and colunms in respect to the 
			   player by numbers of either 1,2,or 3. Lastly, if the index of i is odd then 
			   computer will place piece 1 units right of the player */
			for (int i = 0; i< NUM_VSQUARES; i++){
				if (i%2 == 0){
					if (board[row+1][col]==GomokuModel.Square.EMPTY) 
					{
						compRow +=1; //comp piece is one piece down of user
					}
				}
				if (i%2 == 1){
					if (board[row][col+1]==GomokuModel.Square.EMPTY)
					{
						compCol +=1; //comp piece is one piece right of user
					}
				}
				 if (i%3==0){ //comp piece random in both horiz and vert direction 
					Random ran = new Random();
		    		int compRanR= ran.nextInt(3);
		    		int compRanC = ran.nextInt(3);
		    		if (board[row+ compRanC][col+compRanR]
		    				==GomokuModel.Square.EMPTY){

		    			if(compRanR + row< NUM_VSQUARES){
		    				compRow = compRanR + row;
		    			}
		    			if( compRanC + col< NUM_HSQUARES)
							compCol = compRanC + col;
					}
				}
			}
			board[compRow][compCol] = compClicked; //updates the board which comp plays
        }
   
	board[row][col] = sqClicked; //updates board which player clickes
	detectForWin( row , col ); //
	
	return  gameResult;
}

/**
 * This method is called after the user clicks in the board. This method checks whether
 * any player has scored five in a row in either horizontal, vertical, diagonal direction.
 * It identifies which piece has won. This method checks the all the possible wins by 
 * looping through the rows and col horiz and vertical and diagonal.
 * If it finds five in a row in any direction
 * the result changed based on who won.
 * @param takes in two ints row and col that is passed when user clicks board
 * @return an outcome of whether CROSS_WINS or RING_WINS
 */

public Outcome detectForWin ( int row , int col){

	Square sqClicked = board[row][col]; //creastes square of the current location of user's click


	/**This loops checks for win horizontally by looping through the row 
	   when j is at NUM-HSQUARES. It increments i vertically and checks
	   the next row It starts looking from left to right and compares it with the row 
	   and col user clicks.when five in a row horizontally checks wither CROSS clicked or RING 
	   and change the gameReult */
	for (int i =0; i < GomokuModel.NUM_VSQUARES; i++){
		for (int j = 2; j <GomokuModel.NUM_HSQUARES-2; j ++){
			if (board[i][j-2]== sqClicked && board[i][j-1]== sqClicked &&
				board[i][j]== sqClicked && board[i][j+1]== sqClicked &&
				board[i][j+2]== sqClicked){
				if (sqClicked == GomokuModel.Square.CROSS) {
					gameResult =  GomokuModel.Outcome.CROSS_WINS;
				}
				else if (sqClicked == GomokuModel.Square.RING){
					gameResult = GomokuModel.Outcome.RING_WINS;	
				}
			}
		}
	}

	/**This loops checks for win vertically by looping through the colunms 
	   when j is at NUM-HSQUARES, It increments i vertically and checks
	   the next colunms It starts looking from top to bottoms and compares it with the row 
	   and col user clicks.when five in a row vertically checks wither CROSS clicked or RING 
	   and change the gameReult */
	for (int i = 2; i < GomokuModel.NUM_VSQUARES-2; i ++){
		for (int j = 0; j < GomokuModel.NUM_HSQUARES; j ++){
			if (board[i-2][j]== sqClicked && board[i-1][j]== sqClicked &&
				board[i][j]== sqClicked && board[i+1][j]== sqClicked &&
				board[i+2][j]== sqClicked){
				if (sqClicked == GomokuModel.Square.CROSS) {
					gameResult =  GomokuModel.Outcome.CROSS_WINS;
				}
				else if (sqClicked == GomokuModel.Square.RING){
					gameResult = GomokuModel.Outcome.RING_WINS;	
				}
			}
		}
	}

	/**This loops checks for win diagonally by looping through both the rows the colunms 
	   when j is at NUM-HSQUARES, It increments i vertically and j horizontally and checks
	   the next colunms It starts looking from top left corner to bottoms right and compares it with the row 
	   and col user clicks.when five in a row diagonallychecks wither CROSS clicked or RING 
	   and change the gameReult*/
	for (int i = 2; i < GomokuModel.NUM_VSQUARES-2; i ++){
		for (int j = 2; j <GomokuModel.NUM_HSQUARES-2; j ++){
			if (board[i-2][j-2]== sqClicked && board[i-1][j-1]== sqClicked &&
				board[i][j]== sqClicked && board[i+1][j+1]== sqClicked &&
				board[i+2][j+2]== sqClicked){
				if (sqClicked == GomokuModel.Square.CROSS) {
					gameResult =  GomokuModel.Outcome.CROSS_WINS;
				}
				else if (sqClicked == GomokuModel.Square.RING){
					gameResult = GomokuModel.Outcome.RING_WINS;	
				}
			}
		}
	}

	/**This loops checks for win diagonally by looping through both the rows the colunms 
	   when j is at NUM-HSQUARES, It increments i vertically and j horizontally and checks
	   the next colunms It starts looking from top right corner to bottoms left and compares it with the row 
	   and col user clicks when five in a row diangolly checks wither CROSS clicked or RING 
	   and change the gameReult*/
	for (int i = 2; i < GomokuModel.NUM_VSQUARES-2; i ++){
		for (int j = 2; j <GomokuModel.NUM_HSQUARES-2; j ++){
			if (board[i-2][j+2]== sqClicked && board[i-1][j+1]== sqClicked &&
				board[i][j]== sqClicked && board[i+1][j-1]== sqClicked &&
				board[i+2][j-2]== sqClicked){
				if (sqClicked == GomokuModel.Square.CROSS) {
					gameResult =  GomokuModel.Outcome.CROSS_WINS;
				}
				else if (sqClicked == GomokuModel.Square.RING){
					gameResult = GomokuModel.Outcome.RING_WINS;	
				}
			}
		}
	}
	return  gameResult; //returns game result to doClick
}

 /**
  * Starts a new game, resets the game board to empty. Pick a
  * random player to go first.Makes it so that the player who 
  * won the last game
  * gets to go first in the next round. This method is called by
  * the GUI whenever a new game is supposed to be started, this
  * includes before the first game.
  */

public void newGame () {

	/**Select the player who goes first based on the outcome of the pervious
	 * game if CROSS_WINS then the player plays first otherwise he is 2nd*/

	if ( gameResult == GomokuModel.Outcome.CROSS_WINS){ 

		firstPlayTurn = true;
	}

	if ( gameResult == GomokuModel.Outcome.RING_WINS){

		firstPlayTurn = false;
	}


	/**At the beginning of th game players gets to go randomly*/
	else{
		
		Random ran = new Random();

		firstPlayTurn = ran.nextBoolean();
	}

	//clears the board by looping through each col and row and 
	//setting them to EMPTY
	for (int row = 0; row < GomokuModel.NUM_VSQUARES; row++){

		for (int col = 0; col < GomokuModel.NUM_HSQUARES; col++){

			board[row][col] = GomokuModel.Square.EMPTY;	
		}		
	}
}	
 /**
  * Get a string representation of the board using Square.toChar method
  * to convert string into chars. Eachline in the board is separated 
  * by a new line character '\n'. Creates two strings one representing
  * the indivdual lines of the rows and representing the all of the 
  * rows combined 
  * @return String representation of the board
  */
public String boardString () {

	String bString = ""; //board String of the whole board
	String line = ""; //line for each row

	/**This loops through the each of the rows and colunms of the board and add
	 * each character of row and stores in the string line, then add to board string called bString
	 * with a line \n to separate the rows. */
	
	for (int row = 0; row < GomokuModel.NUM_VSQUARES; row++){

		line = "";

		for (int col = 0; col < GomokuModel.NUM_HSQUARES; col++){

				line = line + board[row][col].toChar();
		}

		bString += line + "\n";

	}	

	return bString;	
}
/**
 * Configure whether a computer player will be used.
 * NONE - no computer player (the default)
 * COMPUTER - one of the players is the computer
 * @param opponent String for computer player type. 
 */
public void setComputerPlayer (String opponent) {

	if ( opponent.equals("NONE")){

		computerPlayTurn = false;
	}

	if ( opponent.equals("COMPUTER")){

		computerPlayTurn = true;
	}
}
}


