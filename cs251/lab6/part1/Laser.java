import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
/**
 * @author Linh Tran
 * CS251.004 
 * April 8, 2014
 * Laser is a GameObject (extends GameObject) that contains a constructor 
 * that calls super to with given 4 dimensions of x, y, widht , and height.
 * fires Lasers. 
 */
public class Laser extends GameObject implements GameData{
	/**
	 *This Laser constructor creates a Laser object by 
	 * calliing the super class with 4 diminsions x, y, height
	 * and width.
	 * @param int coordx, coordY, h, w
	 */
	public Laser ( int coordX, int coordY, int w, int h){

		super( coordX, coordY, LASER_WIDTH, LASER_HEIGHT);
	}

	public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
    }
}