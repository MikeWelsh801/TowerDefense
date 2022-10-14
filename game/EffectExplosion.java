/**
 * This class creates a wormhole effect.
 * Has methods to draw itself and update position. 
 *  
 * @author Michael Welsh
 * @version 12/1/2021
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class EffectExplosion extends Effects {

	// Fields
	private double position, size;
	
	
	/**
	 * Constructor for wormhole effect. Sets up information position and game state.
	 * 
	 * @param state - game state
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public EffectExplosion(GameState state, double position) {

		// Initialize position and game state.
		this.state = state;
		this.position = position;
		
		// Initialize age.
		age = 0;
		size = 0.2;

	}
	/**
	 * Allows the effect to update it's position.
	 */
	public void update(double elapsedTime) {

		//Keep track of how long effect has existed.
		age += elapsedTime;
		
		//Grow quickly, then remove itself.
		size += 0.05;
		if(age > 0.28)
			state.removeGameObject(this);

	}
	/**
	 * Allows effect to draw itself.
	 */
	@Override
	public void draw(Graphics g, GameView view) {

		// Find location and draw itself.
		Point p = ResourceLoader.getLoader().getPath("path.txt").getPathPosition(position);
		view.drawCenteredImage(g, "explosion.png", p.x, p.y, size);

	}
}
