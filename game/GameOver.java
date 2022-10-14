/**
 * This Class allows the creation of a Game Over object that displays one of four memes of 
 * Hubert J Farnsworth. 
 * 
 * @author Michael Welsh
 * @version 11/30/2021
 *
 */
package game;  

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameOver extends Effects {

	// Fields
	private int image;
	

	/**
	 * Constructor for the game over class takes in an integer (1 - 4) that represents one of 
	 * four memes to display at the end of the game.
	 * @param image - integer 1 - 4
	 */
	public GameOver(int image) {

		this.image = image;
	}

	/**
	 * This doesn't need to update anything.
	 */
	@Override
	public void update(double timeElapsed) {

	}

	/**
	 * Draws an image on the screen according to the number given as a parameter when
	 * the GameOver object is created.
	 */
	@Override
	public void draw(Graphics g, GameView view) {

		// Draw one of four Farnsworth memes.
		switch (image) {
		case 1:
			g.drawImage(ResourceLoader.getLoader().getImage("Farnsworth1.png"), 135, 50, null);
			break;
		case 2:
			g.drawImage(ResourceLoader.getLoader().getImage("Farnsworth2.jpg"), 100, 25, 650, 400, null);
			break;
		case 3:
			g.drawImage(ResourceLoader.getLoader().getImage("Farnsworth3.jpg"), 120, 5, null);
			break;
		case 4:
			g.drawImage(ResourceLoader.getLoader().getImage("Farnsworth4.jpg"), 105, 135, 650, 200, null);
			break;
		}
		
		// Paint over the bottom section of the screen with game over message and author credit.
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(new Color(218, 99, 77));
		g2d.fillRect(0, 458, 850, 72);
		
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Impact", Font.ROMAN_BASELINE, 40));
		g2d.drawString("Thanks for playing!", 280, 505);

		g2d.setFont(new Font("Impact", Font.ROMAN_BASELINE, 15));
		g2d.drawString("A game by Michael Welsh", 690, 525);
	}

}
