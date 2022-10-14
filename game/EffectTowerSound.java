/**
 * This class lets the game create and play sound effects.
 * 
 * @author Michael Welsh
 * @version 12/1/2021
 */
package game;

import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class EffectTowerSound extends Effects {
	
	private Clip clip;

	/**
	 * Constructor creates a GameMusic object that plays a sound effect from a .wav file. It takes in 
	 * a file name as a parameter. 
	 * 
	 * File type must be .wav
	 * 
	 * @param filename - the name of a .wav file.
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public EffectTowerSound(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		// Initialize and open audio file from the file name.
		this.clip = AudioSystem.getClip();
		clip.open(ResourceLoader.getLoader().getSoundClip(filename));
		
		
		
	}
	
	/**
	 * Allows user to play the sound effect.
	 */
	public void playSound() {
		clip.start();
	}

}
