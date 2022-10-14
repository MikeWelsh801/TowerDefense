/**
 * Creates a Nibbler poop that is fired from a tower to the nearest enemy.
 * Has methods to draw itself and update position. It checks whether it is 
 * near an enemy and registers a hit.
 *  
 * @author Michael Welsh
 * @version 12/1/2021
 */
package game;

import java.awt.Graphics;

public class EffectNibblerSteamer extends Effects {

	// Fields
	private double xVelocity, yVelocity;
	private int x, y;
	private Enemy kill;
	
	/**
	 * Constructor for the Nibbler poop effect. Sets up information about the tower,
	 * position, and game state.
	 * 
	 * @param state - game state
	 * @param tower - the tower that is firing the effect
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public EffectNibblerSteamer(GameState state, Tower tower, int x, int y) {

		// Initialize position and game state.
		this.state = state;
		this.x = x;
		this.y = y;

		// Get directional velocity from the tower.
		xVelocity = tower.getDeltaX();
		yVelocity = tower.getDeltaY();
		
		// Mark the nearest enemy as the target.
		if (state.findNearestEnemy(x, y) != null)
			kill = state.findNearestEnemy(x, y);
		
		// Initialize age.
		age = 0;

	}
	/**
	 * Allows the effect to update it's position, and check if
	 * it hits an enemy.
	 */
	public void update(double elapsedTime) {

		//Keep track of how long effect has existed.
		age += elapsedTime;
		
		// Update position along velocity vector.
		x -= (int) (7 * xVelocity * elapsedTime);
		y -= (int) (7 * yVelocity * elapsedTime);

		// If it hits a target, remove itself and decrement the enemy's life.
		if (kill.getPosition().distance(x, y) < 30) {
			state.removeGameObject(this);
			kill.updateLife(-1);
		}
		
		// If it hasn't hit anything after a quarter second remove itself from the game.
		if(age > 0.2)
			state.removeGameObject(this);

	}
	/**
	 * Allows effect to draw itself.
	 */
	@Override
	public void draw(Graphics g, GameView view) {

		view.drawCenteredImage(g, "NibblerPoop.png", x, y, 0.5);

	}
}
