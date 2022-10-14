/**
 * This class allows objects to be loaded to a resource map. Then, if the game object is needed again,
 * it is pulled from the map so that it does not need to be reloaded. It contains methods for loading
 * images and a game path.
 * 
 * @author Michael Welsh
 * @version 11/21/2021
 *
 */
package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ResourceLoader {

	// Initialize fields (map to store resources) and instance of resource loader.
	private Map<String, BufferedImage> resources;
	private Map<String, Path> paths;
	private Map<String, AudioInputStream> sounds;
	static private ResourceLoader instance;

	/**
	 * The constructor for the ResourceLoader class creates a new TreeMap that will
	 * be used to store the filenames and files used in a tower defense game.
	 */
	private ResourceLoader() {

		// Build the maps in the constructor.
		this.resources = new HashMap<String, BufferedImage>();
		this.paths = new HashMap<String, Path>();
		this.sounds = new HashMap<String, AudioInputStream>();

	}

	/**
	 * This method takes in a filename from the user and retrieves a corresponding
	 * image from the resources map if it exists. If it does not yet exist in the
	 * resources map, it adds the image to the map and returns the image to the
	 * caller.
	 * 
	 * @param fileName - the name of an image file that getImage is retrieving
	 * @return image - a buffered image created from the image file
	 */
	public BufferedImage getImage(String fileName) {

		// Check for the image in resources map. If it exists,
		// return image.
		if (resources.containsKey(fileName)) {
			return resources.get(fileName);
		}
		// Otherwise, load a new image.
		else
			try {
				// Set up the program to load in an image.
				ClassLoader loader = this.getClass().getClassLoader();
				InputStream is = loader.getResourceAsStream(fileName);
				BufferedImage image = ImageIO.read(is);

				// Add the image to the map.
				resources.put(fileName, image);

				// Return the image.
				return image;

				// Catch IO exceptions. Print error message, exit application, and return null.
			} catch (IOException e) {
				System.out.println("Error - could not load image.");
				System.exit(0);
				return null;
			}

	}

	public AudioInputStream getSoundClip(String fileName) {
		// Check for the image in resources map. If it exists,
		// return image.
		if (sounds.containsKey(fileName)) {
			return sounds.get(fileName);
		}
		// Otherwise, load a new image.
		else
			try {
				// Set up the program to load in an image.
				ClassLoader loader = this.getClass().getClassLoader();
				InputStream is = loader.getResourceAsStream(fileName);
				AudioInputStream audio = AudioSystem.getAudioInputStream(is);
				// Add the image to the map.
				sounds.put(fileName, audio);

				// Return the image.
				return audio;

				// Catch IO exceptions. Print error message, exit application, and return null.
			} catch (IOException e) {
				System.out.println("Error - could not load image.");
				System.exit(0);
				return null;
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}

	/**
	 * This method takes in a filename from the user and retrieves a corresponding
	 * path from the resources map if it exists. If it does not yet exist in the
	 * resources map, it adds the path to the map and returns the path to the
	 * caller.
	 * 
	 * The file must be a .txt file containing coordinates, so the method can create
	 * a new path object if one is not already found.
	 * 
	 * @param fileName - the name of an txt file containing coordinates along a path
	 * @return path - a new path object created from the txt file
	 */
	public Path getPath(String fileName) {

		// Check the map for the filename. If it exists, return the path.
		if (paths.containsKey(fileName)) {
			return paths.get(fileName);
		}

		// Otherwise, load a new path from the resources folder.
		else {
			// Create a new path from the .txt file and return it to the caller.
			ClassLoader loader = this.getClass().getClassLoader();
			Scanner pathScanner = new Scanner(loader.getResourceAsStream(fileName));
			Path path = new Path(pathScanner);
			return path;
		}
	}

	/**
	 * Allow a user to create a new ResourceLoader object, but only allow one
	 * instance of the object.
	 * 
	 * @return the instance of the object.
	 */
	static public ResourceLoader getLoader() {
		if (instance == null)
			instance = new ResourceLoader();

		return instance;
	}

}
