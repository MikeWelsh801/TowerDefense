/**
 * Interface that contains update and draw functions for animatable game objects
 * @author Michael Welsh
 * @version 11/21/2021
 *
 */
package game;

import java.awt.Graphics;

public interface Animatable {

	public void update(double timeElapsed);
	public void draw (Graphics g, GameView view);
}
