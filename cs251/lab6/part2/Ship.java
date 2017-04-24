import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * @author Linh Tran
 * CS251.004 
 * April 8, 2014
 * Ship is a type of GameObject (extends GameObject) and is a Shooter that 
 * fires Lasers. This class creates a laser that is centered horizontally above
 * the ship and contains a contructor that calls the super class (Game Object)to 
 * create ship with a given x, y, width and height of the object.
 */
public class Ship extends GameObject implements Shooter <Laser>, GameData{
	
    private BufferedImage image;
    /**
    * This Ship constructor that takes 4 ints that represents x,y, width,
    * and height of the Ship. The Ship is created by calling its super class,
    * which creates a rectangle that of those 4 dimensions.
    * @param take 4 integers int coordX, coordY, w, h
    */
    public Ship(String imageName, int coordX, int coordY, int w, int h) {
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
    * This method creates a laser that is centered horizontally above the 
    * Ship. The width and height are pre-set variables from GameData. The laser
    * x coordinate is calculated by adding the 1/2 the width of the Ship to Ship 
    * current x value of the Ship and substract 1/2 of the laser declared width. The y value
    * is calculated by geting the Ship current y value and substract the height of the laser predefined.
    * @param take 4 integers int coordX, coordY, w, h
    */
	public Laser fire(){
		return new Laser(getX()+getWidth()/2-LASER_WIDTH/2, getY()-LASER_HEIGHT, LASER_WIDTH, LASER_HEIGHT);
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