import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * @author Linh Tran
 * CS251.004 
 * April 8, 2014
 * Missile is a GameObject (extends GameObject) that contains a constructor 
 * that calls super to with given 4 dimensions of x, y, widht , and height.
 * fires Missiles. 
 */
public class Missile extends GameObject implements GameData {
	/**
	 *This Missile constructor creates a Missile object by 
	 * calliing the super class with 4 diminsions x, y, height
	 * and width.
	 * @param int coordx, coordY, h, w
	 */
	public Missile( int coordX, int coordY, int w, int h){
		super( coordX, coordY, MISSILE_WIDTH, MISSILE_HEIGHT);
	}
 	
 	/**
    * This method draws the missiles represented as white arcs 
    * using the graphics from the java draw imports thake is placed centered 
    * below the aliens. 
    * This method is called in the GamePanel paintComponent method. 
    * @param takes a Graphics g
    */
	public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillArc(getX(), getY(), getWidth(), getHeight(), 180, 180);
    }
}