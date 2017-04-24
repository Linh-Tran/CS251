import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author Linh Tran
 * CS251.004 
 * May 2, 2014
 * GamePanel contains all the logic to run the game. It creates Ship,Missiles,Lasers,and Aliens 
 * from their respected clasess. It provides the gameObjects with their indivdual locations,
 * and width and height. The class also draws the GameObjects and checks for win and lose. 
 * Most importantly it implements KeyListener and ActionerListers that allows the game 
 * to register movements for the game Objects. This class communicates with the GUI class 
 * (InvadersGameFrame) through a object declared as delegate. Delegate is a InvadersGameFrame
 * object. Delegate is called whenever is gameStatus is win or loss. That way the game came
 * restart. 
 */
public class GamePanel extends JPanel implements KeyListener, GameData, ActionListener {
	private BufferedImage image;
	private Ship ship = new Ship("Shooter.png",SHIP_INITX,SHIP_INITY,SHIP_WIDTH,SHIP_HEIGHT);
    private Alien alien;
    private ArrayList <Missile> missile = new ArrayList <Missile> ();
    private ArrayList <Laser> laser= new ArrayList <Laser> ();
    private ArrayList <Alien> aliens;
    private int shipDx, shipDy, alienDx,alienDy, laserDx, laserDy;
    private Timer timer;
    private boolean movingRight;
    public  InvadersGameFrame delegate;
    private int score, birdsKilled;
    private int livesRemaining= SHIP_LIVES; //decalered as 3 in GameData
    private Random r = new Random();
    private int counter;
    private boolean win = false;
    private boolean loss = false;
    private boolean collisonDetect =false;

    /**
     * This constructor uses image io to load the background picture "BackGround.jpg"
     * fromt the InvadersGameFrame. it also add keyListern, setFocus, and calls initAliens
     * initAliens is a method that adds Alien objects to to the arrayList callled aliens and
     * set their lcoation and size. The constructor also creates a timer with a inital Delay 
     * of 1000 miliseconds. 
     * @param String imageName for the namae for which is set as the game's background.
     */
    public GamePanel(String imageName) {
            movingRight = true;
            // Using ClassLoader can find a resource in jar, too.
            ClassLoader cl = getClass().getClassLoader();
            InputStream in = cl.getResourceAsStream(imageName);
            try {
                image = ImageIO.read(in);
                setPreferredSize(new Dimension(GAME_BOARD_WIDTH,GAME_BOARD_HEIGHT));
                
            } catch (IOException ex) {
                System.err.println("Error loading: " + imageName);
            }
            addKeyListener(this);
            setFocusable(true);
            requestFocus();
            initAliens();
            timer = new Timer(20, this);
            timer.setInitialDelay(1000);
            timer.start();  
    }
    /**
     * KeyPressed is a method that takes in a keyEvent ke
     * this method listenrs to the arrows keys in the keyBoard
     * and moves the ship object based on the arrow keys pressed.
     * @param KeyEvent ke  
     */
    public void keyPressed(KeyEvent ke) {
        /*cheks if the gameStaus is paused, win, or loss, so that the ship
         * ship won't move when the game is paused. Disable the ship movements
         * by using return statement to skip actions.
         */
        if (delegate.gameStatus() || win|| loss) return;
        switch (ke.getKeyCode()) {
            /*if the right arrow in keyboard is pressed the ship moves 10 right*/
            case KeyEvent.VK_RIGHT: {
                shipDx = 10;
                shipDy = 0;
                if (ship.getX()+ship.getWidth() >= GAME_BOARD_WIDTH){
                    shipDx = 0;
                }
                ship.move(shipDx,shipDy);
                 
            }
            break;
            /*if the left arrow in keyboard is pressed the  ship will move 10 left*/
            case KeyEvent.VK_LEFT: {
                shipDx = -10;
                shipDy = 0;
                if (ship.getX() <= 0){
                    shipDx = 0;
                }
                ship.move(shipDx,shipDy);
                 
            }
            break;
           /*if tthe space bar is pressed the ship shoots the aliens
            *by calling ship.fire() inside the Ship class*/
            case KeyEvent.VK_SPACE: {
                Laser l= ship.fire();
                laser.add(l);
                l.move(0,-1);                            
            }
            break;
        }
        //request for repaint to ensure that the ship position is updated
         repaint(); 
    }
 
 
    //When a key is typed (once)
    public void keyTyped(KeyEvent e) {}
     
    //When a key is released (typed or pressed)
    public void keyReleased(KeyEvent e) {
         if (delegate.gameStatus() || win|| loss) return;
    }

    /**
     * This method takes in an ActionEvent called event
     * that allows the GameObjects to move acoording to the 
     * timer. This method provides logic in the GameObjects movement.
     * and updates the score, lives, and birdsKilled in the InvadersGameFrame.
     * @param ActionEvent event
     */
    public void actionPerformed(ActionEvent event){
        collisonDetect();//checks missiles hit the ship before ship shoots
        /*checks if the gameStaus is paused, win, or loss, so that the ship
         * ship won't move when the game is paused. Disable the ship movements
         * by using return statement to skip actions.
         */

        if (delegate.gameStatus() || win|| loss) {
            //if the user won the game disable all objects action and 
            //then resets the win and loss boolean
            if (win) {win = false;}
            if (loss){loss= false;}
            return;
        }
        counter ++;//timer counter to monitor the missles
        /*chekcs the boolean movingRight is true so that the
         * aliens stays in the GamePanel. if movingRight is 
         * true then increased the alienaDx to move from left to right.
         */
        if (movingRight){ 
                alienDx =1;
                 //speeds up the alien when there are less aliens alive
                 if (collisonDetect){
                    alienDx++;
                    alienDy++;//shifts the aliens down 
                 }
        }
        /*chekcs the boolean movingRight is false then the aliens
         * will move back in the opposite direction right to left
         * by decreasing alienDx.
         */
        else if (!movingRight){
                alienDx =-1;
                //if there are less aliens then they will move faster
                //across the screen
                 if (collisonDetect){
                    alienDx--;
                }

        } 
        alienDy =0;//sets the y movements of the aliens to be zero

        //for loop through the aliens array to make sure
        //that the aliens don't move out of bounds of the GamePanel
        for (int i = 0; i< aliens.size(); i++){
            Alien a = aliens.get(i);
            //if aliens are offset outside the gamePanel resets movingRight
            if (a.getX()+a.getWidth()>=GAME_BOARD_WIDTH){
                movingRight =false;
               
            }
            //if the aliens reaches the left side of the board
            //this aliens shifts down 2 pixels.
            else if (a.getX()<=0){
                alienDy =5;
                movingRight =true;
            }
            //move the aliens by some x and y values called form 
            //GameObejct class.
            a.move(alienDx,alienDy);
        }
        /*checks if the game is sitll being played to 
         *to create a random number and asigned 
         *to radom positions in the aliens arraylist
         */
        if(!win && !loss && aliens.size()!=0){
            int c= r.nextInt(aliens.size())+1;
            Alien a = aliens.get(c-1);
            Missile m = a.fire();
            /*for loops through missile arraylist and 
             * and move the missiles*/
            for(int i =0; i<missile.size(); i++){
                m = missile.get(i);
                m.move(0,1);
            }
            /*fires a missile whenever counter gets to 150
             * and resets the counter to zero to restart timing
             * then called move method to move the missile */
            if (counter==150){
                counter =0;
                m=a.fire();
                missile.add(m);
                m.move(0,1);
            }
        }
        /* for loops through laser array and move each laser
         * up the screen when laser is fired */
        for (int i = 0; i < laser.size(); i++){
            Laser l = laser.get(i);
            l.move(0,-2);
        }

        collisonDetect();//check for collsion before restarting objects Movements
        winCheck();
        lossCheck();
        repaint();//request to update screen drawing
    }

    /**
     * this method checks provides logic to check
     * wither any game object collided with other other 
     * and updates the scores on the game by calling methods
     * from the  the delegate's InvaderGameFrame. The method
     * returns a boolean called collsionDetect used in ActionPerformed
     * to determine whether the Alien's speed should increase.
     * @return boolean collisionDectect for actionPerform
     */
    public boolean collisonDetect(){
        //if missile intersects ship then remove missile & ship and
        //subtract from livesRemaining. Then call delegate to change
        //livesRemaing on Panel.
         for(int k =0; k< missile.size(); k++){
            Missile m = missile.get(k);
            if(m.intersects(ship)){
                missile.remove(m);
                livesRemaining--;
                delegate.updateLives(livesRemaining);
            }
        }
        //if aliens intersects ship then remove the alien & ship and
        //subtract from livesRemaining. Then call delegate to change
        //livesRemaing on Panel
        for (int i = 0; i< aliens.size(); i++){
            Alien a = aliens.get(i);
            if(a.intersects(ship)){
                aliens.remove(a);
                livesRemaining--;
                delegate.updateLives(livesRemaining);
            }

        }
        //if laser intersects ship then remove alien & laser and
        //add to score. Then call delegate to change
        //score on Panel
        for (int j = 0; j <laser.size(); j++){
            Laser l = laser.get(j);
            for(int i = 0; i<aliens.size(); i++){
                Alien a = aliens.get(i);
                if(l.intersects(a)){
                    collisonDetect = true;
                    aliens.remove(a);
                    laser.remove(l);
                    score = score +SCORE;
                    birdsKilled++;
                    delegate.updateScore(score);
                    delegate.updateBirds(birdsKilled);
                }
            }
        }
        return collisonDetect;//return collisionDect boolean
    }
    /**
     * This method checkes if the user win the game, if he
     * did the all the objects in the arraylists are cleared
     * from the board and reset the scores to their original state
     * then checks of the gameStaus if the game is pause
     * calls the initAliens() method to draw aliens back on the GamePanel
     * lastly it returns true for win.
     * @return boolean win.
     */
    public boolean winCheck(){
        if (score ==WIN_SCORE){
            missile.clear();
            aliens.clear();
            laser.clear();
            livesRemaining =SHIP_LIVES;
            score =0;
            birdsKilled=0;
            delegate.updateScore(score);
            delegate.updateLives(livesRemaining);
            delegate.updateBirds(birdsKilled);
            if(!delegate.gameStatus()){
                initAliens();
            }
            win = true;
        }
       return win;
    }
     /**
     * This method checkes if the user loss the game, if he
     * did the all the objects in the arraylists are cleared
     * from the board and reset the scores to their original state
     * then checks of the gameStaus if the game is pause
     * calls the initAliens() method to draw aliens back on the GamePanel
     * lastly it returns true for loss.
     * @return boolean loss
     */
     public boolean lossCheck(){
        if (livesRemaining ==0){
            missile.clear();
            aliens.clear();
            laser.clear();
            livesRemaining =SHIP_LIVES;
            score =0;
            birdsKilled=0;
            delegate.updateScore(score);
            delegate.updateLives(livesRemaining);
            delegate.updateBirds(birdsKilled);
            if(!delegate.gameStatus()){
                initAliens();
            }
            loss = true;
        }
        return loss;
    }
     /**
     * This method intiializes the positions of the arrayList of Birds
     * on the GamePanel by using two for loops outer one for the row and inner
     * for the colunms. Then the returns the arrayLIst aliens.
     * @return aliens arrayList.
     */
    public ArrayList initAliens (){
        aliens = new ArrayList <>();

        int alienCount = 0;
        for (int row=0;row<2;row++) {
            for (int col=0;col<6;col++) {
               aliens.add(new Alien("Bird2.png",120+(col*80),(50)+row*60,50,50));
               aliens.add(new Alien("Bird3.png",120+(col*80),(180)+row*60,50,50));
               alienCount++;
            }
        }
        return aliens;
    }
     /**
     * This method is responsible for displaying objects on the GamePanel
     * it calls it of the GameObject's draw methods. It overides
     * the paintComponent of the indivdual objectsTakes in a Graphics g
     * to draw objects.
     * @param Graphcis g
     */

    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(),getHeight(), null);//draw background image
        ship.draw(g);//call ship to draw
        for(Alien a:aliens) {
            a.draw(g);
        }
        for (int i = 0; i <laser.size(); i++){
            Laser l = laser.get(i);
            l.draw(g);
        } 
        for(int i = 0; i< missile.size(); i++){
            Missile m = (Missile)missile.get(i);
            m.draw(g);
        }
        /* if the user wins then this will draw a string on the panel, "YOU WON"
         * in a green color centered on the GamePanel. Then calls the restartGame
         * method to clear the board and display a new game.
         */
        if (win){
            g.setColor(Color.GREEN);
            g.setFont(new Font("Serif", Font.BOLD, 35)); 
            g.drawString("YOU WON!", GAME_BOARD_WIDTH/2-30, GAME_BOARD_HEIGHT/2-20);
            delegate.restartGame();
        }
        /* if the user wins then this will draw a string on the panel, "GAME OVER"
         * in a red color centered on the GamePanel. Then calls the restartGame
         * method to clear the board and display a new game.
         */
        if (loss){
            g.setColor(Color.RED);
            g.setFont(new Font("Serif", Font.BOLD, 35));
            g.drawString("GAME OVER", GAME_BOARD_WIDTH/2-30,GAME_BOARD_HEIGHT/2-20);
            delegate.restartGame();
        }
        repaint();
    }

}