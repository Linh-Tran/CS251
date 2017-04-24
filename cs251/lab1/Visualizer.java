import cs251.lab1.Display;
/**
 * @author Linh Tran
 * CS251.004 
 * February 4, 2014
 * Visualizer class draws three shapes Square, Circle, and Fancy Circle.
 * It uses the display class to draw pixels on a panel.
 */

public class Visualizer {
	/** Size of the dots */
	private static final int PIXEL_SIZE = 5;


	public static void main(String[] args) {
	   Display panel = new Display(10, PIXEL_SIZE);

	   // drawSquare(panel);
	   // drawCircle(panel);
	   drawSpiral(panel);
	 
	}

    /**
     * Draws a pixel tracing a square that is 50 pixels from each side of the display panel.
     * Pixel starts at the right corner draws left, down, right, and up
     * @param panel The Display that does the drawing.
     */
	// public static void drawSquare(Display panel) {
	// 	int x = 0;
	// 	int y = 0;
	
	// 	while (true){
		
 //   			for(x = 50, y= 50; x < panel.getWidth()-50; x += PIXEL_SIZE) { 
	//     		panel.drawNextPixel(x, y); //draws right
	//     	}
		
	// 		for( y = 50, y= 50; y < panel.getHeight()-50; y += PIXEL_SIZE) {
	//    			panel.drawNextPixel(x,y); //draws down
	//    		}

	//    		for ( x = panel.getWidth()-50; x >= 50; x-= PIXEL_SIZE) {
	//    			panel.drawNextPixel(x,y); //draws left
 //  			}

	//     	for ( y = panel.getHeight()-50; y >= 50; y-= PIXEL_SIZE) {
	// 			panel.drawNextPixel(x,y); //draws up
				
	//     	}
	// 	}
	// }


    /**
     * Draws a pixel tracing the circumferance of a a circle that 
     * rotates around a point on another circle's circumference.
     * @param panel The Display that does the drawing.
     */
	// public static void drawCircle(Display panel) {

	// 	int centerXBig = panel.getWidth() / 2; // panel's center X coordinate 
	// 	int centerYBig = panel.getHeight() / 2;// panel's center y coordinate

	// 	// Draw a circle starting at the top and going clock wise
	// 	double degAngBig= 270;
	// 	double degAngSmall= 270;
	// 	double radiusBig= 150;
	// 	double radiusSmall = 50;
	// 	double xBig, yBig; 
	// 	double radAngSmall = 0;
	// 	double radAngBig = 0;
	// 	double xSmall, ySmall;
		
	// 	/**Defines two points the one belonging a big circle that is not drawn
	// 	   and one to a small circle is drawn. 
	// 	   degAngBig and degAngBig increments to  */
	// 	while ( true ) {
	    	
	//     	radAngBig = ( degAngBig * Math.PI ) / 180;
	//     	xBig= centerXBig + radiusBig * Math.cos ( radAngBig );
	//     	yBig = centerYBig + radiusBig * Math.sin ( radAngBig );
	//     	degAngBig += .15;// slower change in big circle's angle shift
	    	
	//     	radAngSmall = ( degAngSmall * Math.PI ) / 180;
	//     	/**randAngSmall is divided by 10 makes the circle angle drawn 10 times 
	//     	   smaller than the big circle*/
	//     	xSmall = xBig + radiusSmall * Math.cos( radAngSmall /10);
	//     	ySmall = yBig + radiusSmall * Math.sin( radAngSmall /10);
	// 		panel.drawNextPixel ( (int) xSmall, (int) ySmall );
	//     	degAngSmall += 15;// faster change in small circle's angle shift
	// 	}
	// }
		
	 /**
     * Draws a pixel tracing who forms a spiral by
     * tracing a circle whose radius, x, y coordinates are
     * shifted invard till it reaches the center, then spins outwards.
     * @param panel The Display that does the drawing.
     */	
	public static void drawSpiral (Display panel) {

		int centerX = panel.getWidth()/2; // panel's center X coordinate 
		int centerY = panel.getHeight()/2; // panel's center X coordinate 
		
		/** Spiral startsat  the top and going clock wise inwards
		    then goes outwards.*/
		double degAng= 270; 
		double radius= 170;
		double x =0;
		double y =0; 
		double radAng = 0;

		int i = 0;

		while ( true ) {
			
			i  += 100; // i determine how the tracing point's coordinate is shifted

  			radAng = ( degAng * Math.PI ) / 180;
			x= centerX + radius * Math.cos ( radAng );
			y = centerY + radius * Math.sin ( radAng );
	    	degAng += 50;//increaments the angle of which the spiral rotates 

	   		panel.drawNextPixel ( (int) x, (int) y );

	    	
	    	/** x , y , and radius alternates in the in increaments and decrecrements
	            depending on the value of i.**/
	    	if (i% 2== 0) //i even, increament x and decrement y by .15
	    	{
	    		x+= .15;
	    		y-= .15;
	    		radius--; //decrease the radius
	    	}

	    	if (i% 2== 1) // i odd, increament y and decrement x by .15
	    	{
	    		x-= .15;
	    		y+= .15;
	    		radius++; // increase the radius
	 	 	} 

	    }
	}
}

    
	






