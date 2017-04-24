import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
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

	/**
    * This method draws the laser represented as little green 
    * rectagnels thake is placed centered above the ship. 
    * This method is called in the GamePanel paintComponent method. 
    * @param takes a Graphics g
    */
	public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(getX()+getWidth()/2-LASER_WIDTH/2, getY()-LASER_HEIGHT, LASER_WIDTH, LASER_HEIGHT);
    }
}