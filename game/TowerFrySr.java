/**
 * This class creates a stationary tower that can locate and shoot at enemies.
 * Has methods to shoot brain waves every second, and get position vectors for shots.
 * 
 * @author Michael Welsh
 * @version 12/1/2021
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class TowerFrySr extends Tower {

	// Fields
	private int x, y;
	private double range;
	private double deltaX, deltaY;

	private double spawnTime;

	private boolean firstCall;

	/**
	 * Constructor for the fry Sr. stationary tower. Takes in position and the game
	 * state as parameters. Initializes the range.
	 * 
	 * @param state - the game state
	 * @param x     - x coordinate
	 * @param y     - y coordinate
	 */
	public TowerFrySr(GameState state, int x, int y) {

		super(state);
		this.x = x;
		this.y = y;
		range = 120;

		// Set first call to be true so that the spawn time resets correctly when the
		// tower shoots lightning. (remove this to create an exploit where the tower will
		// rapid fire for at an enemy for a split second if tower is placed while an
		// enemy is within range)
		firstCall = true;

	}
	
	/**
	 * Allows tower to locate and shoot at enemies.
	 */
	@Override
	public void update(double timeElapsed) {

		// Fire brain waves at nearby enemies.

		// Don't look for enemies if there are none on the screen.
		// (avoid nullPointerException).
		if (state.findNearestEnemy(x, y) != null) {

			Enemy targetEnemy = state.findNearestEnemy(x, y);
			Point enemyPosition = targetEnemy.getPosition();

			// Calculate position vectors for shots and shoot 
			// when an enemy is in range.
			deltaX = x - enemyPosition.x;
			deltaY = y - enemyPosition.y;
			if (enemyPosition.distance(x, y) < range)
				shootTurret();
			// Reset first call boolean when no enemy is around, so shots fire correctly.
			else
				firstCall = true;

		}
	}
	/**
	 * Allows tower to draw itself.
	 */
	@Override
	public void draw(Graphics g, GameView view) {
		// Draw itself at current position.
		view.drawCenteredImage(g, "FrySr.png", x, y, 0.9);

	}
	/**
	 * Returns an x velocity vector for the shot effects.
	 */
	@Override
	protected double getDeltaX() {
		return deltaX;
	}
	/**
	 * Returns an y velocity vector for the shot effects.
	 */
	@Override
	protected double getDeltaY() {
		return deltaY;
	}
	/**
	 * Allows the tower to shoot Nibbler poop.
	 */
	public void shootTurret() {

		// Reset the timer on the first shot. (If you don't do this the tower will rapid fire).
		if (firstCall) {
			spawnTime = state.getTime();
			firstCall = false;
		}
		// Fire one shot every quarter second.
		if (state.getTime() > spawnTime) {
			state.addGameObject(new EffectNibblerSteamer(state, this, x, y));
			spawnTime += 0.25;
		}
	}

}
