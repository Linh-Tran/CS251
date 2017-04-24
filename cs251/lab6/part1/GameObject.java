import java.util.*;
import java.awt.Rectangle;
import java.awt.*;
/**
 * @author Linh Tran
 * CS251.004 
 * April 8, 2014
 * GameObject is an abstract class that defines the specific functionality 
 * of a object.This class implements 7 methods in Object2D interface and GameData. GameObject
 * is the super class that Ship, Laser,Missile, and Alien concrete class inherit charecteristics from.
 */
public class GameObject implements Object2D, GameData{
	// created protected variables so access to its subclass.
    protected int x, y, dx, dy, width, height;
    protected boolean intersect;
    protected Rectangle r;  

    /**
    * This GameObject constructor that takes 4 ints that represents x,y, width,
    * and height of a GameObject. It takes those 4 dimensions and create a rectangle
    * called r.
    * @param take 4 integers int coordX, coordY, w, h
    */
    public GameObject ( int coordX, int coordY, int w, int h ){
        r= new Rectangle(coordX,coordY,w,h);
    }
     

    /** @return x coordinate of upper left corner of object. */
    public int getX(){

       return (int)r.getX();
    }

    /** @return y coordinate of upper left corner of object. */
    public int getY(){
    	return (int)r.getY();

    }

    public void move(long delta) {
        // update the location of the entity based on move speeds

        x += (delta * dx) / 1000;
        y += (delta * dy) / 1000;
    }

    // public void setHorizontalMovement(double dx) {
    //     this.dx = dx;
    // }
    
    // public void setVerticalMovement(double dy) {
    //     this.dy = dy;
    // }
    
    // public double getHorizontalMovement() {
    //     return dx;
    // }
    
    // public double getVerticalMovement() {
    //     return dy;
    // }
    
    // public void draw(Graphics g) {
    //     GameObject.draw(g,(int) x,(int) y);
    // }
    
    /** @return object width. */
    public int getWidth(){
    	return (int)r.getWidth();
    }

    /** @return object height. */
    public int getHeight(){
    	return (int)r.getHeight();
    }

    /**
     * Get the bounding rectangle for the object.
     * @return Bounding rectangle.
     */
    public Rectangle getBoundingRectangle(){

        return r.getBounds();

    }
    /**
     * Checks if the whether the current object intersects another object
     * using a predefined .intersects method of java.awt.Rectangle
     * @param other The other object to check.
     * @return True if objects intersect.
     */
    public boolean intersects(Object2D other){
        
        return r.intersects(other.getBoundingRectangle());
    }

    /** 
     * Checks if any part of the current object is outside of the board by 
     * creating a rectangle the size of the gameBoard and using .contains to see
     * if any part of the board is touching the object. And "!" because we want
     * to know when the object is outside the board.
     * @return True if part of object is out of bounds.
     */
    public boolean isOutOfBounds(){
        Rectangle boardRect = new Rectangle(0,0,GameData.GAME_BOARD_WIDTH,GameData.GAME_BOARD_HEIGHT);
        return !boardRect.contains(r);
    }

    /** 
     * This method gets the current coordinate pts of a gameObject by calling this.getClass().getName()
     * this gets the name of which is the object comes from and obtain its name type. The x and y coordinates
     * are obtained form the methods getX() and getY() create above this method.
     * @return coordinate pts of a GameObject in this format Ship is at (124, 55).
     */
    public String toString () {
        return this.getClass().getName() + " at (" + getX() + ", " + getY() + ")" ;
    }

    // public abstract void draw(Graphics g);

}