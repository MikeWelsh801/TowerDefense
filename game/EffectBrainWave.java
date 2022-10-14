/**
 * Creates a brain wave that is fired from a tower to the nearest enemy.
 * Has methods to draw itself and update position. It checks whether it is 
 * near an enemy and registers a hit.
 *  
 * @author Michael Welsh
 * @version 12/1/2021
 */
package game;

import java.awt.Graphics;

public class EffectBrainWave extends Effects {
	
	// Fields
	private double xVelocity, yVelocity;
	private int x, y;
	private Enemy kill;
	private double size;
	
	/**
	 * Constructor for the BrainWave effect. Sets up information about the tower,
	 * position, and game state.
	 * 
	 * @param state - game state
	 * @param tower - the tower that is firing the effect
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public EffectBrainWave(GameState state, Tower tower, int x, int y) {
		
		// Initialize position and game state.
		this.state = state;
		this.x = x;
		this.y = y;
		
		
		
		// Get directional velocity from the tower.
		xVelocity = tower.getDeltaX();
		yVelocity = tower.getDeltaY();	
		
		// Mark the nearest enemy as the target.
		if(state.findNearestEnemy(x, y) != null)
			kill = state.findNearestEnemy(x, y);
		
		// Initialize age and size.
		age = 0;
		size = 0.03;
		
	}
	
	/**
	 * Allows the effect to update it's position, and check if
	 * it hits an enemy.
	 */
	public void update(double elapsedTime) {
		
		// Keep track of the age and increase size.
		age += elapsedTime;
		size += 0.02;
		
		// Update position along velocity vector.
		x -= (int) (4*xVelocity*elapsedTime);
		y -= (int) (4*yVelocity*elapsedTime);
		
		// If it hits a target, remove itself and decrement the enemy's life.
		if(kill.getPosition().distance(x, y) < 50) {
			state.removeGameObject(this);
			kill.updateLife(-1);
		}
		
		// Remove itself after a quarter second if it hasn't hit anything.
		if(age > 0.25)
			state.removeGameObject(this);
		
	}
	/**
	 * Allows effect to draw itself.
	 */
	@Override
	public void draw(Graphics g, GameView view) {
		
		view.drawCenteredImage(g, "SoundWave.png", x, y, size);

	}
}
