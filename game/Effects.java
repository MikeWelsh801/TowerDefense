/**
 * Superclass for any animatable effects in a tower defense game.
 * @author Michael Welsh
 * @version 11/21/2021
 */
package game;

import java.awt.Graphics;


public abstract class Effects implements Animatable {

	// Fields
	
	protected double age;
	protected GameState state;
	
	@Override
	public void update(double timeElapsed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g, GameView view) {
		// TODO Auto-generated method stub

	}

}
