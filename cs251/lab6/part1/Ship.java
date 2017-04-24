import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;/**
 * @author Linh Tran
 * CS251.004 
 * April 8, 2014
 * Ship is a type of GameObject (extends GameObject) and is a Shooter that 
 * fires Lasers. This class creates a laser that is centered horizontally above
 * the ship and contains a contructor that calls the super class (Game Object)to 
 * create ship with a given x, y, width and height of the object.
 */
public class Ship extends GameObject implements Shooter <Laser> {
	/**
    * This Ship constructor that takes 4 ints that represents x,y, width,
    * and height of the ship. The ship is created by calling its super class,
    * which creates a rectangle that of those 4 dimensions.
    * @param take 4 integers int coordX, coordY, w, h
    */
	public Ship (int coordX, int coordY, int w, int h){
		super( coordX, coordY,w,h);
	}
    /**
    * This method creates a laser that is centered horizontally above the 
    * ship. The width and height are pre-set variables from GameData. The laser
    * x coordinate is calculated by adding the 1/2 the width of the ship to ship 
    * current x value of the ship and substract 1/2 of the laser declared width. The y value
    * is calculated by geting the ship current y value and substract the height of the laser predefined.
    * @param take 4 integers int coordX, coordY, w, h
    */
	public Laser fire(){
		return new Laser(getX()+getWidth()/2-LASER_WIDTH/2, getY()-LASER_HEIGHT, LASER_WIDTH, LASER_HEIGHT);
	}
    public void draw(Graphics g) {
        // set up the coordinates for ship shape
        int[] xs = new int[]{0, 0, width/3, width/2, 2*width/3, width, width};
        int[] ys = new int[]{height, height/2, height/2, 0, height/2, height/2, height};
        int numpoints = xs.length;

        // translate the coordinates to ship location
        for(int i = 0; i < numpoints; ++i) {
            xs[i] += x;
            ys[i] += y;
        }

        g.setColor(Color.GREEN);
        g.fillPolygon(xs, ys, numpoints);
    }
	
}