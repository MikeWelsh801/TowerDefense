/**
 * This class creates a Fry Jr. menu object. That can create a movable Fry Jr. object.
 * Plays a funny sound effect the first time a user clicks on it.
 * 
 * @author Michael Welsh
 * @version 12/1/2021
 */
package game;

import java.awt.Graphics;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class TowerFryJrMenu extends Tower {
	
	// Fields
	int x, y;
	private EffectTowerSound sound;
	
	
	/**
	 * Constructor for the fry Jr. menu tower. Takes in position and the game state
	 * as parameters. Initializes the sound effect.
	 * 
	 * @param state - the game state
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public TowerFryJrMenu (GameState state, int x, int y) {
		
		
		super(state);
		this.x = x;
		this.y = y;
		
		try {
			sound = new EffectTowerSound("FryBrainThingLong.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			System.out.println("Could not play file.");
		}
	
	}
	
	/**
	 * Creates a moving tower object when clicked on (if the game is started).
	 * Plays a funny sound clip the first time a player clicks on it.
	 */
	@Override
	public void update(double timeElapsed) {
		
		if(state.getMouseX() >= x && state.getMouseX() < x+105 && 
				state.getMouseY() >= y && state.getMouseY() < y+150 &&
				state.isMouseClicked() && state.isGameStarted()) {
			state.addGameObject(new TowerFryJrMove(state));
			sound.playSound();
		}
		
	}

	/**
	 * Allows menu tower to draw itself.
	 */
	@Override
	public void draw(Graphics g, GameView view) {
		
		g.drawImage(ResourceLoader.getLoader().getImage("JrFry.png"), x, y, null);
		

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
