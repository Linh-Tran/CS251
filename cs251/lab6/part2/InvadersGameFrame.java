import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
 
 /**
 * @author Linh Tran
 * CS251.004 
 * May 2, 2014
 * InvadersGameFrame is the GUI class for the Space Invaders game.
 * This class contains a contructor that layouts the game frame on the JFrame. 
 * The layout manager used for this game is BorderLayout.
 * There are two JPanels one representing the panel in which the game will be played 
 * and the other panel holds the start/pause button and scores. This class communicates
 * with the GamePanel logic class setting one of the panel to be a GamePanel.
 */
	public class InvadersGameFrame extends JFrame implements GameData{

		private final int FONT_SIZE = 20; //font size for text 
		private final String FONT = "Serif";//text font 

		private Color background, textColor; 
		private GamePanel panelCenter, newGame;
		private JPanel panelEast;
		private JLabel pointsScored,livesLeft,birdsLives;
		//create a button and set text as "Start Game"
		public JButton button = new JButton ("Start Game");
	 	private boolean isPaused =true;//boolean that controls gameStatus
	    private int score;
	    private int livesRemaining=SHIP_LIVES;
	    private int birdsKilled=0; 
	    private String backgroundName = "Background.jpg";
	    // private boolean win;
	    // private boolean lose;

	public InvadersGameFrame () {
		super("Birds Invaders"); //JFrame Title

		background = Color.YELLOW;//set the background to black
		textColor = Color.BLACK;// set the text color to white
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //default close
		setResizable(false);//window is non-resizable	

		//panelCenter contains the "background" image on the Game Board
		panelCenter = new GamePanel(backgroundName);
		panelCenter.delegate = this;

		add(panelCenter, BorderLayout.CENTER);//use BorderLayout to center panel
		panelEast = new JPanel();
		panelEast.setBackground(background);
		panelEast.setPreferredSize(new Dimension(200,200));
		
		/* These are the three elements displayed as text on the Game Frame keep
		track of the "Score", "Lives Remaining ", and "Aliens Killed". They are
		components contained in JLabel on the panelEast.*/
		pointsScored = new JLabel("Score: "+ score);
		pointsScored.setFont(new Font (FONT,Font.BOLD,FONT_SIZE));
		pointsScored.setForeground(textColor);
		
		livesLeft = new JLabel("Lives Remaining: "+ SHIP_LIVES);
		livesLeft.setFont(new Font (FONT ,Font.BOLD,FONT_SIZE));
		livesLeft.setForeground(textColor);

		
		birdsLives = new JLabel("Birds Killed: "+ birdsKilled);
		birdsLives.setFont(new Font (FONT ,Font.BOLD,FONT_SIZE));
		birdsLives.setForeground(textColor);


		//add the components to the panelEast
		panelEast.add(pointsScored);
		panelEast.add(livesLeft);
		panelEast.add(birdsLives);
		
		 /** 
	     * This is an anonymous class that creates a new
	     * ActionListener and provide implementation for
	     * actionPerformed a void method that takes in ActionEvent
	     * e and use .getActionCommand() method to see if the
	     * text on the button is "Start Game" or "Pause Game". It also
	     * sets the gameStatus isPaused boolean to true or false
	     * depending on the button's text. Everything the game
	     * starts it requestes for focus on the gamePanel called panelCenter.
	     * Eveytime the button is clicked the this class listens
	     * to the event and set the text to "Start Game " and Pause Game
	     * @param Takes a ActionEvent e.
	     */
		button.addActionListener (new ActionListener () {
			public void actionPerformed ( ActionEvent e) {
				String text = (String)e.getActionCommand();

				if(text.equals("Start Game")){
					isPaused = false;
					button.setText("Pause Game");
					panelCenter.requestFocus();
					
				}
				if(text.equals("Pause Game")){
						
					isPaused = true;
					button.setText("Start Game");
				}
			}

		});
		//add the button to the buttom of panelEast
		panelEast.add(button, BorderLayout.SOUTH);
		//use BorderLayout to set panel East and add to JFrame.
		add(panelEast, BorderLayout.EAST);
		pack(); //pack all the components to fit on frame properly
		setVisible(true);//set all the components to be visible on frame.     

	}
	/** 
	* This void method updates the "Score"on the paneEast
	* JPanel when a bird is hit inside the GamePanel class.
	* the method takes in an int s and score local to this 
	* class (score) is set to that variable. If the 
	* score is equal to WIN_SCORE then the isPaused boolean is 
	* set to true, which pauses the game. If the score is 
	* zero then isPaused is set to false. 
	* @param int S for score in GamePanel class
	*/
	public void updateScore (int s){
		score = s;
		if (score == WIN_SCORE){
			isPaused = true;
		}
		else if (score == 0){
			isPaused = false;
		}
		pointsScored.setText("Score: " + score);
	}
	/** 
	* This void method updates the "Lives Remaining" on the paneEast
	* JPanel when the ship is hit by a bird or missile inside the 
	* GamePanel class. The method takes in an int l 
	* called livesRamaining (local variable) is set to that variable. If the 
	* if livesRemaing == 0, then the isPaused boolean is 
	* set to true, which pauses the game.
	* @param int l for the lives reaming
	*/
	public void updateLives (int l){
		livesRemaining = l;
		livesLeft.setText("Lives Remaining: " + livesRemaining);
		if (livesRemaining == 0){
			isPaused = true;
			// lose = true;
		}
	}
	/** 
	* This void method updates the "Birds Killed" on the paneEast
	* JPanel when a bird is hit by a ship inside the 
	* GamePanel class. The method takes in an int b and set it equal to
	* birdsKilled (local variable). 
	* @param int b for the birdsKilled 
	*/
	public void updateBirds (int b){
		birdsKilled =b;
		birdsLives.setText("Birds Killed: " + birdsKilled);
	}
	/** 
	* This method returns a boolean called isPaused it is called
	* in the GamePanel class to check if the game should be paused
	* when the user clicks the paused button. 
	* @return boolean isPaused.
	*/
	public boolean gameStatus(){
		return isPaused;
	}
	/** 
	* This void method restarts the game by
	* creating a new GamePanel. It starts
	* by first setting the isPaused to true so that the game is Paused
	* then it resets the text on the button to be "Start Game" and
	* lastly calles GamePanel constructor to create a new Game.
	* @param String x for the name of the background picture
	*/
	public void restartGame(){
		isPaused = true;
		button.setText("Start Game");
		newGame = new GamePanel(backgroundName);
		newGame.delegate=this;
	}


}