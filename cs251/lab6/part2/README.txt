README

included:
All source files (.jar,.java, .jpg, .png, .class)
SPACE INVADERS GAME

----------------------
Introduction to Game
----------------------

This README provides instructions on how to play the SpaceInvaders game,
details on the game structure, and brief decriptions of the main classes.
This document is broken down into 5 points:

	1. Design Decisions
	2. Game Play
	3. Descriptions of classes
	4. Algorithm Details
	5. Issues, Concerns & Reflection
	6. Extra Credit
	7. Conclusion

Design Decsion discuses how and why the game is structured the 
Game Decriptiosn of classes decribes the keys used to control the game 
and how the game isscored. Descriptions of classes addresses important 
aspects of the classes and their functions. Algorithim Details 
provides insight on how moving alien,detecting/handling collision, 
and detecting end of game works. Issues, Concers
& reflection adresses the problems that resolved and encountered in the process 
of creating it.Extra Credit decribes the extra features created in 
addition to the requirements. Conclusion wraps this document with final 
thoughts on the game.

----------------
Design Decisions 
----------------

In the proceess of creating the game I incoporated the concept of class
hierachy to layout classes for my GameObjects. The GameObjects are defined
as the drawn objects in the SpaceInvaders game. My game consists of 4 game
objects Ship, Alien, Laser, and Missile. Each of these four game objects
has similar characteristics defined in the GameObject class. They are
all defined as rectangle with a x & y coordinate and width & height. Also 
they can move by x and y coordinates. They can tell when they insersect 
each other or go out bounds. The game objects extends
the GameObject class allows all the commom characteristics to be inherited
instead of defined for each individual object. 
The following diagrams demostrates the class
hiearchy of the game.

		Class Hierachy

					Game Object
					is a    is a 
					|       |
					Ship, Alien
					|        |
					has a   has a
					Laser, Missles

		is a = implements
		laser and missles implements shooter
		____________________________________
		  
		7 methods implements if make indiv then (28 methods)
		  game         Object
		    |             |
		  space craft    shootable 
		  |       |        |    |
		  ship  alien   laser  missle
		    |      |        |      |
		  space craft implements shooter

		  two methods that have same thing than add to the methods before it
		  different character goes separate defined separately.
		___________________________________________

In addition the structure of the GameObjects made two two more
classes that contains the most of the components that runs the
game, GamePanel and InvadersGameFrame. InvadersGameFrame fuctions
as the GUI class. It displays the text and button beside the game
board. The GamePanel is the class that monitors the function of the
game. The reason I decided to make two classes so that I easily
identify where the fucntion in my game versus the view. Having 
two classes for the function and model allows for easy communication
and easy access. I felt that two classes is is sufficient because
I already have a lot classes for the game objects therefore making 
more classes will be overwhelming. 

Below is a diagram illustrating the funtions and interaction 
of both classes.


 			<------getImage fileName <------ 
 			------->   Draw Image    ------->

Game Panel 	<------    GameStatus    <------- InvadersGameFrame
	        ------->    Action/key   ------->
	        			listern 

	        <-------   Reset Call    <-------
	        ------->  Resets Game   ------->
	   
	        -------> increment Score ------>
	        <--------  Display Score <------

Game Panel : Draw Objects, Update Move and Postions, 
			 Listens for action or key pressed
 			 Timer, Check for win & lose, check collison
 			 increment scores, clear board. 

InvadersGameFrame: Display scores,Update Scores,
				   Diplay button, button Listener
				   GameStatus, Reset game call, setup GamePanel
				   location of Frame, provide image file name.


-----------------------
Descriptions of Classes
-----------------------

In the game I have a total of 8 main classes and 2 interfaces. 
The following provides decription of the classes and interfaces.

1. SpaceInvaders: instantiate instance of gui class (InvaderSGameFrame),
			   	  SpaceInvaders contain a void method called run that 
			      creates a JFrame for the game frame.

2. GameObject: GameObject is an abstract class that defines the specific 
			   functionality of a object.This class implements 7 methods 
			   in Object2D interface and GameData. GameObject is the super class 
			   that Ship, Laser,Missile, and Alien concrete class inherit 
			   charecteristics from.

3.  GamePanel: contains all the logic to run the game. It provides the gameObjects 
   			   with their indivdual locations, and width and height. 
   			   the class also draws the GameObjects and checks for win and lose. 
   			   It implements KeyListener and ActionerListers that 
   			   allows the game to register movements for the game Objects. 
   			   This class communicates with the GUI.
   			   Methods: keyPressed, actionPerformed, collsionDetect, winCheck
   			   lossCheck, intAliens, paintComponent.

4. InvadersGameFrame: GUI class for the Space Invaders game. Class contains a contructor 
					  that layouts the game frame on the JFrame.There are two 
					  JPanels one representing the panel in which the game 
					  will be played  and the other panel holds the 
					  start/pause button and scores. The class also holds the
					  status of the game isPaused and it calls the GamePanel to 
					  reset the game.

5. Ship: is a type of GameObject and implements Shooter that  fires Lasers. T
         Creates a laser that is centered horizontally above the ship.

6. Alien: is a type of GameObject implements Shooter that fires Missiles. 
		  This class creates a missle that is centered horizontally on top
		  of the aliean. 

7. Missile:  is a GameObject (extends GameObject) that contains 
			 a constructor that calls super to with given 4
			 dimensions of x, y, widht , Hieght.

8. Laser:  GameObject (extends GameObject) that contains a constructor 
		   that calls super to with given 4 dimensions, and fires Lasers. 

9. GameData: Holds some constants for the game in a single 
			 location for easy access and modification.

10. Shooter: Is interface that both Ship and Alien implements to fire
			 a laser or missile.

-----------------
Algorithm Details
-----------------

The game logic is located in the GamePanel class. Some of the logic
that has to be calculated were Collison check, win and loss check,
alien movement & ship movement, and stoping object movements. 

Collision check: has 3 if statements each contains for loops that loops
through each arrayList of objects. Each has 5 procedures:
 	1. loop through arrayList using for loop
 	2. check if the game object intersects other game object
 	3. remove the objects
 	4. change the score
 	5. calls the update Scores methods form the InvadersGameFrame
 	   with the given score to display on the panel.

win and loss check both are similar in the aspect they are called
in actionPerformed to check whether the actions should be dismiss
when the gameStatus is paused, game over, or win. They both
also contributes to the reseting of the game by clearing the board
and scores. Both have 4 main procedures:
	1. clears the arrayList of objects 
	2. reset scores
	3. call delegate (InvadersGameFrame) to update gameStatus
	4. initAliens on board.

Alien movement is perfomed in the ActioinPerformed. The aliens move
after the checking that gameState is not win, loss, or paused. 
Alien movement contains 4 main procedures.
	1. introduce a movingRight boolean to check if the
	   aliens should wrap around the GamePanel when the
	   aliens hit the wall. if true then aliens x variable
	   (alienDx) moveright.
	2. after the aliens movedown reset the dy movement to zero
	3. for loops through aliens arraylist if aliens are offset
	   outside the gamePanel resets movingRight. 
	   if the aliens reaches the left side of the board
       this aliens shifts down 3 pixels.
    4. calls move method in Game Object to move aliens 
    5. check for collision,game win or loss, and request
       for repaint.

Ship movement is establish in keyPressed method. The
method register movement of ship with the arrows keys
and space bar. The movement happens using switch to
read the events. With each case the ship move x independently
when press right or left arrow, and fires lasers when
press space bar. After the x and y values are adjusted
they ship object calls move method to move.

Stoping objects movement this is done by including if 
statements before any objects is moved in actionPerfomred,
keyPressed. The process of stopping the objects is not
by stopping the timer but by skipping the actions whenever
the game status is win, loss, or paused. The if statement
return, skipping the action completly until the method is
called again. 

-----------------------------
Issues, Concerns & Reflection
-----------------------------

I encountered many problems through the process of creating the
game. Some of the main issues were about the new concepts covered
about GUIs such as using the actionPerformed,KeyListener, updating game 
scores, and Reseting the game. 

ActionPermed allowed the aliens to move across the screen, and fires missiles. 
Calculting the aliens movement was difficult because I realized that isOutOfBounds
method from GameObject didn't respond to the aliens moving offscreen when the alien 
touches the screen on the rightside. I resolved this issue by getting the including 
if statments that consider the object current x postion + the width of the 
object, becuase the object is a rectangle that draws starting from
left top corner. So when check for outBounds is called the object is no
longer tounching panel. 

My ship had trouble moving because I had to have keyListener request a request for 
focus. I later discovered that whenever I pressed the pause button and restart
the game I had to request for focus. I done the request inside
GamePanel constructor and inside the button InvadersGameFrame.

Updating the game score required that I had interaction between the GamePanel
and InvadersGameFrame. In the process I learned that they can access
each other methods by declaring the game panel inside the InvadersGameFrame
as a typed GamePanel, and setting the InvadersGameFrame delegate to be 
be on the GamePanel. 

Reseting the game requires the understanding of how to GamePanel
and InvadersGameFrame should interact with other. I had difficult 
time processing how each class knows when the game should be reseted.
This issue was resolved by using a isPaused boolean, gameStatus & restartingGame
method inside the InvadersGameFrame. isPaused determines the state of the game
is and its state is change when the game state is expected to change as well.
For, example it is used most when the button is clicked when button 
text is set to "State Game", isPaused is false. When button is equal is "Pause Game" then isPaused is true. The GamePanel can have acess to isPaused status
by the method called gameStatus that returns the boolean. restartGame method
resets the game is by first setting the isPaused to true, and change
the button's text to display "Start Game" and then calls new GamePanel
to pass a new instance of the game. The GamePanel has access to this method
by calling the delegate.(InvadersGameFrame). 

Two concerns that I have that I still cant resolve was with restarting the game.
Sometimes the game have trouble restarting it pauses momentarily after 
the game is won but could restart normally if I click the paused button.
After through examination, I figured that the problem relies inside 
the InvadersGameFrame when I call the restartGame method. Inside
the mehod there's are two statments "newGame = new GamePanel(backgroundName);
newGame.delegate=this;". This statement request the GamePanel class to create
a new GamePanel when there's one currently in use. I tried using the dispose().
method on the GamePanel but that gives me a nullpointer expection line 146 in GamePanel and closes whole window instead of restarting the game. Therefore
you will see that somethings the game processes the movements of the objects
slow and also occasionally freeze. The second conern is that I could figure
out how to deley the display the "GAME OVER" and "YOU WIN!" text to stay 
longer. I tried messing with the delay in the timer but that didn't work.

------------
Extra Credit
------------

Some of the extra features I including in this game was
that I incorporated images for the background, aliens, and ship. 
I alsoincluding indication when the game is over by drawing text
on the panel once the game is over, "YOU WON!" or "GAME OVER"
on the screen. Futhermore, I made the aliens moves faster
when there are less aliens on the GamePanel. 

----------
Conclusion
----------
Overall, in making this game I learned how to design a game
that incorporates all the concepts I learned throughout this semester
such as extending, implementing classes and interfaces, designing
methods that takes objects to test and return necessary types
when a method is called. I learned to construct methods inside
classes that return booleans or int to change the game's status or
fucntion. Some of the things I wished to do if I had more time
was add more graphics and audio to my game. Graphics like
displaying a GameOver and YouWin image when the game is over
or won. Also I wanted to include an animation in the birds movement 
so they can flap their wings. Lastly, I wanted to include audio
whenever the ship or a bird is killed. 



