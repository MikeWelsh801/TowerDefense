/**
 * This sets up a game menu to the left of the game play area that contains towers, score, money
 * and formatted background.
 * 
 * @author Michael Welsh
 * @version 11/21/2021
 *
 */
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Menu implements Animatable {

	// Fields
	private GameState state;
	private boolean objectsAdded;

	/**
	 * Constructor for the menu gives access to the game state.
	 * 
	 * @param state - game state
	 */
	public Menu(GameState state) {

		this.state = state;
		this.objectsAdded = false;

	}

	/**
	 * Adds in the towers after drawing so they don't draw behind the backdrop.
	 */
	@Override
	public void update(double timeElapsed) {

		// If it's the first time the object is being added, add the object and set the
		// add object boolean to true.
		if (!objectsAdded) {
			state.addGameObject(new TowerFryJrMenu(state, 875, 227));
			state.addGameObject(new TowerFrySrMenu(state, 980, 240));
			objectsAdded = true;
		}

	}

	/**
	 * Formats and sets up background, score, and money. Draws everything for the
	 * menu.
	 */
	@Override
	public void draw(Graphics g, GameView view) {

		// Load in menu background
		g.drawImage(ResourceLoader.getLoader().getImage("MinimalMenu.png"), 850, 0, null);

		// Add in some cool translucent boxes to hold the towers.
		for (int i = 0; i < 2; i++) {
			g.drawImage(ResourceLoader.getLoader().getImage("border.png"), 865, 250 + 140 * i, null);
			g.drawImage(ResourceLoader.getLoader().getImage("border.png"), 865 + 140, 250 + 140 * i, null);
		}

		// Cast to 2D for more effects.
		Graphics2D g2d = (Graphics2D) g;

		// Add Drop Shadow Effect to the score.
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Arial Narrow", Font.BOLD, 27));
		g2d.drawString("NOW WITH " + state.getScore() + " POINTS", 869, 177);

		// Add main font for the score.
		g2d.setColor(Color.YELLOW);
		g2d.setFont(new Font("Arial Narrow", Font.BOLD, 27));
		g2d.drawString("NOW WITH " + state.getScore() + " POINTS", 867, 175);

		// Add drop shadow effect to the money.
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		g2d.drawString("Fry's account: $" + state.getMoney() + (state.getMoney() == 0 ? "" : " Kajillion"), 867, 227);

		// Add main font for money.
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		g2d.drawString("Fry's account: $" + state.getMoney() + (state.getMoney() == 0 ? "" : " Kajillion"), 866, 226);

		// Add drop shadow effect to Jr tower price.
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		g2d.drawString("$50 Kajillion", 886, 365);

		// Add main font for Jr tower price.
		g2d.setColor(Color.YELLOW);
		g2d.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		g2d.drawString("$50 Kajillion", 885, 364);

		// Add drop shadow effect to Sr tower price.
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		g2d.drawString("$150 Kajillion", 1022, 365);

		// Add main font for Sr tower price.
		g2d.setColor(Color.YELLOW);
		g2d.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		g2d.drawString("$150 Kajillion", 1021, 364);

	}

}
