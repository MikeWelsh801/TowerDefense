/**
 * This class creates a movable fry Sr. tower that follows the mouse around and removes itself
 * when the player clicks to place it. It can check to make sure it's in a valid position. 
 * 
 * @author Michael Welsh
 * @version 12/1/2021
 */
package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class TowerFrySrMove extends Tower {

	// Fields
	double range;
	
	/**
	 * Constructor for fry Sr. tower. Initializes game state and range.
	 * 
	 * @param state - game state
	 */
	public TowerFrySrMove(GameState state) {

		super(state);
		range = 120;

	}

	/**
	 * Updates the position of the tower and checks to see if the mouse has been
	 * clicked and if the tower is in a valid position to be set. If so, it removes
	 * itself from the game and calls the game state to create a new stationary
	 * tower.
	 */
	@Override
	public void update(double timeElapsed) {

		Path path = ResourceLoader.getLoader().getPath("path.txt");

		// Check to make sure it's not on the path.
		if (state.isMouseClicked() && path.distanceToNearestPathNode(state.getMouseX(), state.getMouseY()) > 75) {

			// Remove itself on click.
			state.removeGameObject(this);

			// If it's not in the menu, and there is enough money, create a new tower. (If a
			// player doesn't want to buy a tower, they can set it back in the menu).
			if (state.getMouseX() < 850 && state.getMoney() >= 150) {
				state.addGameObject(new TowerFrySr(state, state.getMouseX(), state.getMouseY()));
				state.updtateMoney(-150);
			}

		}

	}

	/**
	 * Draws itself at it's current position. Also draws a ring that shows the
	 * player the tower's range.
	 */
	@Override
	public void draw(Graphics g, GameView view) {

		// Draw the tower.
		view.drawCenteredImage(g, "FrySr.png", state.getMouseX(), state.getMouseY(), 1);

		// Draw a circle to show the range before the player sets it down.
		
		// Cast as a 2D graphic.
		Graphics2D g2d = (Graphics2D) g;

		// Set color and line width.
		g2d.setPaint(new Color(218, 99, 77));
		g2d.setStroke(new BasicStroke(5));
		
		g2d.drawOval(state.getMouseX() - (int) (range), state.getMouseY() - (int) (range), (int) range*2,
				(int) range*2);
	}

	@Override
	protected double getDeltaX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected double getDeltaY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
