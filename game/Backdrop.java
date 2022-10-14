/**
 * Loads in the backdrop of a tower defense game.
 * 
 * @author Michael Welsh
 * @version 11/21/2021
 *
 */
package game;

import java.awt.Graphics;

public class Backdrop implements Animatable {
	
	
	/**
	 * Update method for the backdrop. Empty.
	 */
	public void update(double timeElapsed) {
		
		// Don't need to update the backdrop.
		
	}

	/**
	 * Calls the resource loader to load the backdrop, then draws itself.
	 */
	public void draw(Graphics g, GameView view) {
		
		// Draw the backdrop.
		g.drawImage(ResourceLoader.getLoader().getImage("FuturamaMap.jpg"), 0, 0, null);

	}

}
