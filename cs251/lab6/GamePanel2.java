import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
public class GamePanel2 extends JPanel implements GameData, ActionListener {
	private BufferedImage image;
	private Ship ship;
    private Alien alien;
    private ArrayList <Alien> aliens;
    public final int score;
    public final int lives;
    public final int birdsLives;
    public final int level;
    public final boolean isGameOver;

    public ScoreEvent(int score, int lives, int birdsLives, int level, boolean isGameOver) {
        this.score = score;
        this.lives = lives;
        this.birdsLives = birdsLives;
        this.level = level;
        this.isGameOver = isGameOver;    
    } 

    public static interface ScoreListener {
        void updateScore(ScoreEvent ev);
    }


 
    public GamePanel2(String imageName) {
            // Using ClassLoader can find a resource in jar, too.
        ship = new Ship("Shooter.png",350,500,100,100);
        ClassLoader cl = getClass().getClassLoader();
        InputStream in = cl.getResourceAsStream(imageName);
        try {
            image = ImageIO.read(in);
            setPreferredSize(new Dimension(700,600));
                
        } catch (IOException ex) {
            System.err.println("Error loading: " + imageName);
        }

        addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ev) {
                    if(isPlaying) {
                        switch (ev.getKeyCode()) {
                        case KeyEvent.VK_DOWN:
                            moveCurrentDown(false);
                            break;
                        case KeyEvent.VK_LEFT:
                            moveCurrentLeft();
                            break;
                        case KeyEvent.VK_RIGHT:
                            moveCurrentRight();
                            break;
                        case KeyEvent.VK_UP:
                            rotateCurrent();
                            break;
                        case KeyEvent.VK_SPACE:
                            moveCurrentDown(true);
                            break;
                        }
                    }
                }
            });     
           
        initAliens();

    }

    public void actionPerformed(ActionEvent ev) {
        if(isPlaying) {
            moveCurrentDown(false);
        }
    }

    public ArrayList initAliens (){
        aliens = new ArrayList <>();

        int alienCount = 0;
        for (int row=0;row<2;row++) {
            for (int x=0;x<6;x++) {
               aliens.add(new Alien("Bird2.png",120+(x*80),(50)+row*60,50,50));
               aliens.add(new Alien("Bird3.png",120+(x*80),(180)+row*60,50,50));
                alienCount++;
            }
        }
        return aliens;
    }

    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        ship.draw(g);  
        for(Alien x : aliens) {
            x.draw(g);
        }
        
    }
}