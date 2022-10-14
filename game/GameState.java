/**
 * Keeps array lists of objects in the game, and has methods
 * to add to the list and remove objects by adding it to an 
 * object to be removed list. Has methods to update and draw
 * all game objects simultaneously. 
 */
package game;

import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameState {
	// Fields

	// Arrays of game objects. Keeps track of which objects need to be
	// removed/added.
	private List<Animatable> gameObjects;
	private List<Animatable> objectsToRemove;
	private List<Animatable> objectsToAdd;

	// Integers to keep track of current score, money, random endgame meme, and
	// mouse location.
	private int score;
	private int money;
	private int imageNumber;
	private int mouseX, mouseY;
	private GameControl control;
	private double time;

	// Keeps track of mouse clicks and whether game is started/stopped.
	private Boolean mouseClicked, isGameOver, isGameStarted;

	// Allows music to be added to the game or changed to a new song.
	private GameMusic music;

	/**
	 * Constructor sets up the array list to hold game objects. And the list of
	 * objects that will be added/removed. It takes in the score and money as
	 * parameters. And initializes mouse states, game over/start states, music
	 * and the image to display at the end of the game.
	 */
	public GameState(GameControl control, int score, int money) {

		this.control = control;

		gameObjects = new ArrayList<Animatable>();
		objectsToRemove = new ArrayList<Animatable>();
		objectsToAdd = new ArrayList<Animatable>();
		this.score = score;
		this.money = money;

		// Initialize mouse fields.
		mouseX = mouseY = 0;
		mouseClicked = false;

		// Initialize booleans to register if game has started/stopped.
		isGameOver = false;
		isGameStarted = false;

		// Choose an image to display at the end of the game.
		imageNumber = (int) (Math.random() * 4) + 1;

		// Add music to the game.
		try {
			this.music = new GameMusic("Music.wav");
		} catch (UnsupportedAudioFileException e) {
			System.out.println("Could not play file.");
		} catch (IOException e) {
			System.out.println("Could not play file.");
		} catch (LineUnavailableException e) {
			System.out.println("Could not play file.");
		}

	}

	/**
	 * Adds a game object to temporary list of objects that will be added to the
	 * game objects every time the update function is called.
	 * 
	 * @param a an animatable game object
	 */
	public void addGameObject(Animatable a) {

		objectsToAdd.add(a);
	}

	/**
	 * Adds a game object to temporary list of objects that will be removed from the
	 * game objects every time the update function is called.
	 * 
	 * @param a an animatable game object
	 */
	public void removeGameObject(Animatable a) {

		objectsToRemove.add(a);
	}

	/**
	 * Updates the location of the Mouse to input coordinates.
	 * 
	 * @param x - an x coordinate
	 * @param y - a y coordinate
	 */
	public void setMouseLocation(int x, int y) {
		mouseX = x;
		mouseY = y;

	}

	/**
	 * @return the mouse x coordinate
	 */
	public int getMouseX() {
		return mouseX;
	}

	/**
	 * @return the mouse y coordinate
	 */
	public int getMouseY() {
		return mouseY;
	}

	/**
	 * @return true if the mouse is clicked, false otherwise
	 */
	public boolean isMouseClicked() {

		return mouseClicked;

	}

	/**
	 * Uses the mouse click so no other object can use it.
	 */
	public void consumeMouseClick() {
		mouseClicked = false;
	}

	/**
	 * Recognizes that the mouse has been clicked
	 */
	public void setMouseClicked() {
		mouseClicked = true;

	}

	/**
	 * Updates all the game objects according to their update functions, which keep
	 * track of movement.
	 */
	public void updateAll(double elapsedTime) {

		if (!isGameOver) {

			// Update all game object positions.
			for (Animatable a : gameObjects)
				a.update(elapsedTime);

			// If the score hits or drops below zero, run a series of game over actions.
			if (score <= 0) {

				// This method stops enemies from spawning.
				isGameOver = true;

				// Keep the score at zero even if enemies reach the finish after the game ends.
				score = 0;

				// This will put up a meme of Hubert Farnsworth. (We already chose a random
				// number corresponding to one of four images.
				this.addGameObject(new GameOver(imageNumber));

				// Change to game over song.
				try {
					music.changeSong("GameOverSong.wav");
				} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
					System.out.println("Could not play file.");
					System.exit(0);
				}

			}

			// Remove all game objects that are no longer
			// in use.
			gameObjects.removeAll(objectsToRemove);
			objectsToRemove.clear();

			// Add all game objects that need to be added.
			gameObjects.addAll(objectsToAdd);
			objectsToAdd.clear();

			// Pull time from the game state
			time = control.getTime();
		}
	}

	/**
	 * Draws all the game objects.
	 * 
	 * @param g a Graphics parameter to pass into the draw methods.
	 */
	public void drawAll(Graphics g, GameView view) {

		for (Animatable a : gameObjects)
			a.draw(g, view);
	}

	/**
	 * Allows user to update the score by a given integer.
	 * 
	 * @param add - an integer that will be added to the current score
	 * @return - the new score
	 */
	public int updateScore(int add) {
		score += add;
		return score;
	}

	/**
	 * @return the current score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Allows user to update the money by a given integer.
	 * 
	 * @param add - an integer that will be added to the current score
	 * @return - the new score
	 */
	public int updtateMoney(int add) {
		money += add;
		return money;
	}

	/**
	 * @return the current money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * Sets isGameStarted boolean to true.
	 */
	public void startGame() {
		isGameStarted = true;
	}

	/**
	 * Allows user to check if the game has started.
	 * 
	 * @return - isGameSarted true or false.
	 */
	public boolean isGameStarted() {
		return isGameStarted;
	}

	/**
	 * Given a set of coordinates, this method locates the nearest enemy game
	 * object.
	 * 
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @return the closest enemy to the coordinates
	 */
	public Enemy findNearestEnemy(int x, int y) {

		// Construct a point with the coordinates.
		Point p = new Point(x, y);

		// Declare enemy variables.
		Enemy currentEnemy;
		Enemy nearestEnemy = null;

		for (Animatable a : gameObjects) {

			// Check each object to see if it's an enemy.
			if (a instanceof Enemy) {
				currentEnemy = (Enemy) a;

				// Set first found enemy to nearest enemy.
				if (nearestEnemy == null)
					nearestEnemy = currentEnemy;

				// Otherwise set current enemy to nearest only if the distance to p is shorter.
				else if (currentEnemy.getPosition().distance(p) < nearestEnemy.getPosition().distance(p)) {
					nearestEnemy = currentEnemy;
				}

			}

		}

		return nearestEnemy;
	}

	/**
	 * @return - the current time.
	 */
	public double getTime() {
		return time;
	}
}
