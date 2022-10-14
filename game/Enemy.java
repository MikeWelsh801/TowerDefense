/**
 * Superclass for any enemies in a tower defense game.
 * 
 * @author Michael Welsh
 * @version 11/21/2021
 *
 */
package game;

import java.awt.Graphics;
import java.awt.Point;


public abstract class Enemy implements Animatable {

	// Fields
	protected double position;
	protected int enemyLife;

	/**
	 * Constructor used by all enemy subclasses. Initializes the game state and
	 * position.
	 * 
	 * @param position
	 * @param state
	 */
	public Enemy(double position) {
		this.position = position;
	}

	/**
	 * Calls the path class to locate an enemies current position on the map.
	 * @return a point position of the enemy
	 */
	public Point getPosition() {

		// Get the position of an enemy object.
		Point p = ResourceLoader.getLoader().getPath("path.txt").getPathPosition(position);
		return p;
	}

	@Override
	public void update(double timeElapsed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g, GameView view) {
		// TODO Auto-generated method stub

	}
	
	abstract void updateLife(int shot);

}
