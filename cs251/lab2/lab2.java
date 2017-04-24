	import cs251.lab2.GomokuModel;
	import cs251.lab2.GomokuGUI;

	import java.util.Random;

	public class Gomoku implements GomokuModel {

		Outcome gameResult = null;
		private static boolean firstPlayTurn = true; 
		private static boolean computerPlayTurn = false; 
		private Square [][] board = new Square[GomokuModel.NUM_VSQUARES][GomokuModel.NUM_HSQUARES];
		private static Random ran = new Random();  
		
	public static void main ( String [] args) {
		Gomoku game = new Gomoku ();
		
		if (args.length > 0) {
			game.setComputerPlayer (args[0]);
		}

		GomokuGUI.showGUI(game);
	}


	public GomokuModel.Outcome doClick ( int row , int col ){
		
		int rowOld = row;
		int colOld = col;
		Square squareClicked = board[row][col]; //got the square at the location of click

		gameResult  = GomokuModel.Outcome.GAME_NOT_OVER;
		char sym = board[row][col].toChar();// convert square into char


		
		int crossCount = 0;
		int ringCount = 0;
		
		

		// System.out.println(crossCount);

		
		// else if ((crossCount && ringCount)== SQUARES_IN_LINE_FOR_WIN)
		// {
		// 	gameResult = GomokuModel.Outcome.DRAW;
		// }

		
		if (sym == ' ') { //if char at the clicked square is empty assign a x or o

			if (firstPlayTurn){ //if the first player turn then he will get x
				sym = 'x';

				squareClicked  = GomokuModel.Square.CROSS;
				firstPlayTurn = false;

			}

			else{  //else he will get 'o' inside the clicked
			
				sym = 'o';
				squareClicked = GomokuModel.Square.RING;
				firstPlayTurn = true;

			}
		}


		//left to right 
		// 
		// for (row=rowOld-4; row<= rowOld+4; row++){
		// 	if (sym == 'x' && row == rowOld -1 && col==colOld)   { row--; crossCount++;}
		// 	else
		// 	row



		// }


		// for (col=colOld-4; col<=colOld+4; col++){

		// 		//check for x's in a row


				
		// 		if ( row==0 || sym != 'x') { row++; crossCount = 0;}


		// 		if (sym == 'x' && row == rowOld -1 && col==colOld-1) { row--; crossCount++;}

		// 		if (sym == 'x' && row == rowOld -1 && col==colOld+1) { row--; crossCount++;}

		// 		if (sym == 'x' && row == rowOld && col==colOld-1)    { col--; crossCount++;}
		// 		if (sym == 'x' && row == rowOld && col==colOld+1)    { col++; crossCount++;}

		// 		if (sym == 'x' && row == rowOld +1 && col==colOld)   { row++; crossCount++;}
		// 		if (sym == 'x' && row == rowOld +1 && col==colOld-1) { row++; crossCount++;}
		// 		if (sym == 'x' && row == rowOld +1 && col==colOld+1) { row++; crossCount++;}

		// 		//check for o's row

		// 		if (sym == 'o' && row == rowOld -1 && col==colOld)   {ringCount++;}
		// 		if (sym == 'o' && row == rowOld -1 && col==colOld-1) {ringCount++;}
		// 		if (sym == 'o' && row == rowOld -1 && col==colOld+1) {ringCount++;}

		// 		if (sym == 'o' && row == rowOld  && col==colOld-1)   {ringCount++;}
		// 		if (sym == 'o' && row == rowOld  && col==colOld+1)   {ringCount++;}

		// 		if (sym == 'o' && row == rowOld +1 && col==colOld)   {ringCount++;}
		// 		if (sym == 'o' && row == rowOld +1 && col==colOld-1) {ringCount++;}
		// 		if (sym == 'o' && row == rowOld +1 && col==colOld+1) {ringCount++;}
		// 	}

		if (crossCount ==  SQUARES_IN_LINE_FOR_WIN)
		{
			gameResult = GomokuModel.Outcome.CROSS_WINS;
		}
		else if (ringCount == SQUARES_IN_LINE_FOR_WIN)
		{
			gameResult = GomokuModel.Outcome.RING_WINS;
		}


		}
		
		// if (sym = ' '){

		// 	int randRow = ran.nextInt(GomokuModel.NUM_VSQUARES+1);
		// 	int randCol = ran.nextInt(GomokuModel.NUM_HSQUARES+1);
			
		// 	if (computerPlayTurn){
		// 		sym = 'x';
		// 		squareClicked = GomokuModel.Square.CROSS;
		// 		computerPlayTurn = false;
		// 	}
		// 	else 
		// 	{
		// 		sym = 'o';
		// 		squareClicked = GomokuModel.Square.RING;
		// 		computerPlayTurn = true;
		// 	}	
		// }

		// System.out.println (boardString());

		board[row][col] = squareClicked;
		return  gameResult;
	}

	public void newGame () {

		if ( gameResult == GomokuModel.Outcome.CROSS_WINS){ 

			firstPlayTurn = true;
		}

		if ( gameResult == GomokuModel.Outcome.RING_WINS){

			firstPlayTurn = false;
		}

		else{
			
			Random ran = new Random();

			firstPlayTurn = ran.nextBoolean();
		}

		//clears the board
		for (int row = 0; row < GomokuModel.NUM_VSQUARES; row++){

			for (int col = 0; col < GomokuModel.NUM_HSQUARES; col++){

				board[row][col] = GomokuModel.Square.EMPTY;	
			}		
		}
	}	

	public String boardString ( ) {

		String bString = "";
		String singleLine = "";
		
		for (int row = 0; row < GomokuModel.NUM_VSQUARES; row++){

			singleLine = "";

			for (int col = 0; col < GomokuModel.NUM_HSQUARES; col++){

					singleLine = singleLine + board[row][col].toChar();
			}

		bString += singleLine + "\n";

		}

			//System.out.println(bString);		

		return bString;	
	}

	public void setComputerPlayer (String opponent) {

		if ( opponent == "NONE"){

			computerPlayTurn = false;
		}

		if ( opponent == "COMPUTER"){

			computerPlayTurn = true;
		}
	}
	}


