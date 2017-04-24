import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
/**
 * @author Linh Tran
 * CS251.004 
 * April 8, 2014
 * Alien is a type of GameObject (extends GameObject) and is a Shooter that 
 * fires Missiles. This class creates a missle that is centered horizontally on top
 * of the aliean and contains a contructor that calls the super class (Game Object)to 
 * create Alien with a given x, y, width and height of the object.
 */
public class Alien extends GameObject implements Shooter <Missile> {
        private BufferedImage image;
	/**
    * This Alien constructor that takes 4 ints that represents x,y, width,
    * and height of the ship. The Alien is created by calling its super class,
    * which creates a rectangle that of those 4 dimensions.
    * @param take 4 integers int coordX, coordY, w, h
    */
    public Alien(String imageName, int coordX, int coordY, int w, int h) {
        super( coordX, coordY, w, h);
        // Using ClassLoader can find a resource in jar, too.
        ClassLoader cl = getClass().getClassLoader();
        InputStream in = cl.getResourceAsStream(imageName);
        try {
            image = ImageIO.read(in);
                
        } catch (IOException ex) {
            System.err.println("Error loading: " + imageName);
        }
    }

	/**
    * This method creates a missile that is centered horizontally above the 
    * Alien. The width and height are pre-set variables from GameData.
    * x coord is calculated by adding the half of the width of the alien from
    * its current x value and subtract half of the width of the missle. The y coord
    * calculated by adding the current height of the alien to the current y value of the alien.
    * @param take 4 integers int coordX, coordY, w, h
    */
	public Missile fire(){
		return new Missile(getX()+getWidth()/2 - MISSILE_WIDTH/2, getY()+ getHeight(), MISSILE_WIDTH, MISSILE_HEIGHT);
	}

    /**
    * This method draw the ship image given an image, x, y, width, and height value.
    * This method is called in the GamePanel paintComponent method. 
    * @param takes a Graphics g
    */
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }

}
