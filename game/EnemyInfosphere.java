/**
 * Creates a Infosphere enemy object and has methods to draw and update movement
 * along the path of a tower defense game.
 * 
 * @author Michael Welsh
 * @version 11/21/2021
 *
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class EnemyInfosphere extends Enemy {

	// Fields
	private GameState state;

	/**
	 * Constructor for the Infosphere. Inherits parameters position and gamestate
	 * from the Enemy super class.
	 * 
	 * @param position - location to spawn enemy
	 * @param state    - game state.
	 */
	public EnemyInfosphere(double position, GameState state) {

		super(position);
		this.state = state;
		enemyLife = 10;
	}

	/**
	 * Updates the position of the sphere along the path and removes itself/deducts
	 * points if it reaches the end.
	 */
	public void update(double timeElapsed) {

		// Advance the position along the path.
		position += 0.030 * timeElapsed;

		// If it gets to the end, remove itself and deduct one hundred points.
		if (position > 1) {
			state.removeGameObject(this);
			state.updateScore(-100);
		}

		// If Infosphere is killed add 100 dollars, remove itself and add 4 new brain
		// enemies.
		if (enemyLife <= 0) {
			state.addGameObject(new EffectExplosion(state, position));
			state.removeGameObject(this);
			state.addGameObject(new EnemyBrain(position - 0.05, state));
			state.addGameObject(new EnemyBrain(position - 0.02, state));
			state.addGameObject(new EnemyBrain(position + 0.00, state));
			state.addGameObject(new EnemyBrain(position + 0.02, state));
			state.updtateMoney(100);
			
		}
	}

	/**
	 * Allows the infosphere to draw itself.
	 */
	public void draw(Graphics g, GameView view) {

		// Set up position and for a info sphere that moves along the path.
		Point p = ResourceLoader.getLoader().getPath("path.txt").getPathPosition(position);

		// Draw the info sphere.
		view.drawCenteredImage(g, "Infosphere.png", p.x, p.y, 0.83);

	}

	/**
	 * Allows the user to update the enemy's life.
	 */
	@Override
	void updateLife(int shot) {
		enemyLife += shot;

	}

}
