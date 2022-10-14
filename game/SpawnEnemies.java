/**
 * This class controls enemy spawn times and sends enemies according to a wave
 * function that takes in time as an input.
 * 
 * @author Michael Welsh 11/21/2021
 *
 */
package game;

import java.util.Scanner;


public class SpawnEnemies {

	// Fields
	private GameState state;
	private GameControl control;
	private double time;
	private double spawnTime, sphereSpawnTime;
	private Scanner spawn, sphereSpawn;

	/**
	 * Constructor for the spawn enemy class. Sets up scanners that read spawn times
	 * from txt files. Initializes the first spawn times.
	 * 
	 * @param state   - game state
	 * @param control - game control
	 */
	public SpawnEnemies(GameState state, GameControl control) {

		// Build the SpawnEnemies object
		this.state = state;
		this.control = control;

		// Set up loader to create scanner objects for brain and info sphere.
		ClassLoader loader = this.getClass().getClassLoader();

		// Create scanner objects that can read in spawn times for brains and info
		// spheres.
		spawn = new Scanner(loader.getResourceAsStream("spawn.txt"));
		sphereSpawn = new Scanner(loader.getResourceAsStream("SpawnBig.txt"));

		// Set up first spawn time as the first time in the list.
		this.spawnTime = spawn.nextDouble();
		this.sphereSpawnTime = sphereSpawn.nextDouble();

	}

	/**
	 * Checks the time and spawns enemies when the time matches a scanned value.
	 */
	public void run() {

		// Check the time since the wave started every time wave is called.
		time = control.getTime();

		// Spawn a brain every time the timer passes the current spawn time and advance
		// to the next spawn time in the list.
		if (time > spawnTime) {
			state.addGameObject(new EnemyBrain(0, state));
			spawnTime = spawn.nextDouble();
		}

		// Spawn an info sphere every time the timer passes the current spawn time and
		// advance to the next spawn time in the list.
		if (time > sphereSpawnTime) {
			state.addGameObject(new EnemyInfosphere(0, state));
			sphereSpawnTime = sphereSpawn.nextDouble();
		}

	}

}
