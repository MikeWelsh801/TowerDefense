/**
 * Tower super class. This tells the other towers how to behave.
 * 
 * @author Michael Welsh
 * @version 11/21/2021
 *
 */
package game;

import java.awt.Graphics;


public abstract class Tower implements Animatable {

	// Field
	protected GameState state;
	protected double deltaX, deltaY;

	/**
	 * Constructor for towers. Gives access to the game state
	 * 
	 * @param state - game state
	 * @param control TODO
	 */
	public Tower(GameState state) {
		this.state = state;
	}

	@Override
	public void update(double timeElapsed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g, GameView view) {
		// TODO Auto-generated method stub

	}
	protected abstract double getDeltaX();
	
	protected abstract double getDeltaY();
		

	
}
