/**
 * This class creates the start screen for the game, that asks a user to click on it to 
 * start the game animation.
 *  
 * @author Michael Welsh
 * @version 12/1/2021
 *
 */
package game;

import java.awt.Color;
import java.awt.Graphics;


public class StartSequence extends Effects {
	
	// Fields
	private GameState state;
	private int x, y;
	
	/**
	 * Constructor for the StartSequence initializes coordinates and game state.
	 * 
	 * @param state - game state
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public StartSequence(GameState state, int x, int y) {
		
		
		this.state = state;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Update method for the start sequence. Removes itself and starts game if a
	 * user clicks on it.
	 */
	public void update(double timeElapsed) {
		
		// if start image is clicked on remove it from the game state and start the game.
		if(state.getMouseX() >= x && state.getMouseX() < x+800 && 
				state.getMouseY() >= y && state.getMouseY() < y+500 &&
				state.isMouseClicked()) {
			state.startGame();
			state.removeGameObject(this);
		}
		
	}
	
	/**
	 * Allows start sequence to draw itself.
	 */
	public void draw (Graphics g, GameView view) {
		
		// Make the background blue and draw start image.
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 850, 530);
		g.drawImage(ResourceLoader.getLoader().getImage("IntroPhoto.png"), x, y, 800, 500, null);

	}
	

	

}
