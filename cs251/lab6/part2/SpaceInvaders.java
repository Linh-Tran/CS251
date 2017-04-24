import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
/**
 * @author Linh Tran
 * CS251.004 
 * April 16, 2014
 * SpaceInvaders contain main method that starts the game.
 * This class instantiate instance of gui class (InvaderSGameFrame),
 * SpaceInvaders contain a void method called run that creates
 * a JFrame for the game frame.
 * There are two JPanels one representing the panel in which the game will be played 
 * and the other panel holds the start/pause button and scores.
 */
public class SpaceInvaders {

	public static void main ( String [] args ) {
		SwingUtilities.invokeLater (new Runnable () {
		 /**
		 * 
		 * void run create an instance of the InvadersGameFrame
		 * and set the frame visible and the close operation to exit
		 * when clicked the "x" on the corner of the frame.
		 */
		public void run () {
			JFrame frame = new InvadersGameFrame();
			frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE );
			frame.setVisible ( true );
		}
		});
	}
}