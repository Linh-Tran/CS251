import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
 
 /**
 * @author Linh Tran
 * CS251.004 
 * April 16, 2014
 * InvaderGameFrame is the GUI class for the Space Invaders game.
 * This class contains a contructor that layouts the game frame on the JFrame. 
 * The layout manager used for this game is BorderLayout.
 * There are two JPanels one representing the panel in which the game will be played 
 * and the other panel holds the start/pause button and scores.
 */
	public class GameFrame extends JFrame implements GamePanel.ActionListener {
		private static final String START = "START";
		private static final Stirng PAUSE = "PAUSE";

		private JButton pauseButton;
		private boolean isPaused = true;

		private final int FONT_SIZE = 20; //font size for text 
		private final String FONT = "Serif";//text font 

		private JLabel scoreLabel = new JLabel();
   		private JLabel livesLabel = new JLabel();
    	private JLabel birdsLabel = new JLabel();
    	private JLabel livesLabel = new JLabel();

    	private GamePanel panel;
    	private Timer timer;

		private Color background = Color.YELLOW;
		private Color textColor = Color.BLACK;
		private JPanel panelCenter, panelEast;
		private JLabel pointsScored,livesLeft,birdsLives; 
		//create a button and set text as "Start Game"
		// private JButton button = new JButton ("Start Game");
	    private int score, livesRemaining, birdsKilled; 
	    
	public GameFrame () {
		super("Birds Invaders"); //JFrame Title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //default close
		setResizable(false);//window is non-resizable
		
		panel = new GamePanel("Background.jpg");
		panel.addScoreListener(this);
		timer = new Timer (DELAY, panel);
        timer.setInitialDelay(0);
        JPanel boardPanel = new JPanel();
        boardPanel.add(panel);

        pauseButton = new JButton(START);
        pauseButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    isPaused = !isPaused;
                    pauseButton.setText(isPaused ? START : PAUSE);
                    panel.setPlaying(!isPaused);
                    if(isPaused) {
                        timer.stop();
                    } else {
                        timer.start();
                        panel.requestFocusInWindow();
                    }
                }
            });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(pauseButton);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.PAGE_AXIS));
        scorePanel.setBorder(BorderFactory.createEtchedBorder());
        scorePanel.add(scoreLabel);
        scorePanel.add(livesLabel);
        scorePanel.add(birdsLabel);
        updateScore(0,0,0);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(previewPanel, BorderLayout.PAGE_START);
        rightPanel.add(scorePanel, BorderLayout.PAGE_END);

        getContentPane().add(boardPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
        getContentPane().add(rightPanel, BorderLayout.LINE_END);
        pack();

         private void updateScore(final int score, final int lives, final int birdsLives, final int level) {
        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    scoreLabel.setText("Score: " + score);
                    scoreLabel.setFont(new Font (FONT ,Font.BOLD,FONT_SIZE));
					scoreLabel.setForeground(textColor);
                    livesLabel.setText("Lives Remaining: " + lives);
                    livesLabel.setFont(new Font (FONT ,Font.BOLD,FONT_SIZE));
					livesLabel.setForeground(textColor);
                    birdsLabel.setText("Birds Killed: " + birdsLives);
                    birdsLabel.setFont(new Font (FONT ,Font.BOLD,FONT_SIZE));
					birdsLabel.setForeground(textColor);
                    levelLabel.setText("Level: " + level);
                    levelLabel.setFont(new Font (FONT ,Font.BOLD,FONT_SIZE));
					levelLabel.setForeground(textColor);

                }
            });
    	}

		score = 0;
		livesRemaining = 0;
		birdsKilled = 0;

		background = Color.YELLOW;//set the background to black
		textColor = Color.BLACK;// set the text color to white
		
			

		//panelCenter contains the "background" image on the Game panel
		panelCenter = new GamePanel("Background.jpg");

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
		
		livesLeft = new JLabel("Lives Remaining: "+ livesRemaining);
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
	     * text on the button is "Start Game" or "Pause Game".
	     * Eveytime the button is clicked the this class listens
	     * to the event and set the text to "Start Game " and Pause Game
	     * @param Takes a ActionEvent e.
	     */
		button.addActionListener (new ActionListener () {
			public void actionPerformed ( ActionEvent e) {
				String text = (String)e.getActionCommand();
				if(text.equals("Start Game")){
					button.setText("Pause Game");
				}
				else
				{
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
		

}