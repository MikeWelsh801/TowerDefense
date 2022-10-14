/**
 * Sets up the run and animations loop for a tower defense game. Has access to the 
 * game view/game state, keeps track of time, and ends the game when the score reaches
 * zero. Keeps track of when the game is started.
 */
package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameControl implements Runnable, ActionListener {
	
	// Fields
	private GameView view;
	private GameState state;
	private long previousTime;
	private double totalTime;
	private SpawnEnemies spawn;
	private boolean isTimeReset;

	/**
	 * Constructor is empty. 
	 */
	public GameControl() {
		// I moved all the code into a function named 'run' below.
	}

	/**
	 * Initializes the game by creating a GameState and GameView. Then, it starts
	 * the animation loop that will run throughout the game.
	 */
	public void run() {

		// Initialize time at zero.
		totalTime = 0;
		isTimeReset = false;

		// Build the game state.
		state = new GameState(this, 200, 100);

		// Build a view. Note that the view builds it's own frame, etc. All the work is
		// there.
		view = new GameView(state);

		// Add initial Animatable objects to the game.
		state.addGameObject(new Backdrop());
		state.addGameObject(new Menu(state));

		// Create a new wave for enemies to spawn.
		this.spawn = new SpawnEnemies(state, this);

		// Start the animation loop.
		// Set up a new timer that refreshes every 16 ms.
		Timer timer = new Timer(16, this);
		timer.start();
		previousTime = System.nanoTime();

		// Adds in the start screen.
		state.addGameObject(new StartSequence(state, 20, 30));
	}

	/**
	 * Gets called by the timer and gets called every 16 ms. to update and draw game
	 * objects.
	 */
	public void actionPerformed(ActionEvent e) {

		// Animation loop.
		
		// Start the game.
		if (state.isGameStarted()) {
			// Reset the timer only when the game starts.
			if(!isTimeReset) {
				totalTime = 0;
				isTimeReset = true;
			}
			
			// Call method that adds enemies to the game.
			spawn.run();
			
		}

		// Calculate elapsed time.
		long currentTime = System.nanoTime();
		double elapsedTime = (currentTime - previousTime) / 1_000_000_000.0;

//		System.out.println(previousTime + " " + currentTime + " " + elapsedTime);
		previousTime = currentTime;
		totalTime = totalTime + elapsedTime;

		// Update the game objects.
		state.updateAll(elapsedTime);

		// Consume the mouse click if nothing else did.
		state.consumeMouseClick();

		// Draw the game objects.
		view.repaint();

	}

	/**
	 * Allow the user to access the time.
	 * 
	 * @return time
	 */
	public double getTime() {
		return totalTime;
	}
}
