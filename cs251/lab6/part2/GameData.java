/**
 * Holds some constants for the game in a single location for easy
 * access and modification.
 */
public interface GameData {


    /** Width of a laser fired by player's ship. */
    int LASER_WIDTH = 2;
    /** Height of a laser fired by player's ship. */
    int LASER_HEIGHT = 15;
    /** How far a laser moves in a single step. */
    int LASER_SPEED = 10;

    /** Width of a missile fired by an alien. */
    int MISSILE_WIDTH = 5;
    /** Height of a missile fired by an alien. */
    int MISSILE_HEIGHT = 20;
    /** How far a missile moves in a single step. */
    int MISSILE_SPEED = 8;
    /** Ship initial x position. */
    
    int SHIP_INITX = 350;
     /** Ship initial y position. */
    int SHIP_INITY = 500;
     /** Width of the ship. */
    int SHIP_WIDTH = 100;
     /** Height of the ship. */
    int SHIP_HEIGHT = 100;
     /**Wining Score */
    
    int WIN_SCORE = 240;
     /** Ship initial lives. */
    int SHIP_LIVES = 3;
     /** Points per alien kill */
    int SCORE = 10;

    /** Width of game area. */
    int GAME_BOARD_WIDTH = 700;
    /** Height of game area. */
    int GAME_BOARD_HEIGHT = 600;

}

