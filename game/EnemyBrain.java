/**
 * Creates a brain enemy object and has methods to draw and update movement
 * along the path of a tower defense game.
 * 
 * @author Michael Welsh
 * @version 11/21/2021
 *
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class EnemyBrain extends Enemy {

	// Fields
	private GameState state;
	private int enemyLife;
	
	/**
	 * Constructor for Enemy brain objects. Initializes position and game state.
	 * 
	 * @param position
	 * @param state
	 */
	public EnemyBrain (double position, GameState state) {
		
		super(position);
		this.state = state;
		enemyLife = 5;
	}
	
	/**
	 * Allows the game to update the brain's position and advance it along the path at a specified rate.
	 * Checks to see if it's reached the end and deducts points accordingly.
	 */
	public void update(double timeElapsed) {
		
		// Advance the position along the path.
		position += 0.060 * timeElapsed;
		
		// If it gets to the end, remove itself and deduct thirty points.
		if(position > 1) {
			state.removeGameObject(this);
			state.updateScore(-30);
		}	
		
		// Remove itself if it's life gets to 0 and add 20 dollars.
		// Add a cool wormhole effect.
		if(enemyLife <= 0) {
			state.addGameObject(new EffectWormhole(state, position));
			state.removeGameObject(this);
			state.updtateMoney(20);
		}
			
	}

	/**
	 * Allows the brain to draw itself at it's current position.
	 */
	public void draw(Graphics g, GameView view) {
		
		// Set up position and for a brain that moves along the path.
		Point p = ResourceLoader.getLoader().getPath("path.txt").getPathPosition(position);
				
		// Draw the Brain.
		view.drawCenteredImage(g, "Scooty_Puff01.png", p.x, p.y, 1.0);

	}
	
	/**
	 *	Allow the user to update an enimies life.
	 */
	public void updateLife(int shot) {
		enemyLife += shot;
	}

}
