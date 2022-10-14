/**
 * Allows the user to add music to a a gui application given the path to a .wav file
 * by creating a GameMusic object. It has a method to change to a new song.
 * 
 * @author Michael Welsh
 * @version 11/30/2021
 */
package game;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameMusic {

	private AudioInputStream audio;
	private Clip clip;

	/**
	 * Constructor creates a GameMusic object that plays a song from a .wav file. It takes in 
	 * a file name as a parameter. 
	 * 
	 * File type must be .wav
	 * 
	 * @param filename - the name of a .wav file.
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public GameMusic(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		// Initialize and open audio file from the file name.
		audio = ResourceLoader.getLoader().getSoundClip(filename);
		clip = AudioSystem.getClip();
		clip.open(audio);
		
		// Lower the volume.
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-10.0f);
		
		// Start the clip and loop infinitely, or until stopped.
		clip.start();
		clip.loop(-1);
		
	}
	
	/**
	 * Method allows user to change to a new song.
	 * 
	 * @param filename - a .wav file that contains the song the user wishes to change to.
	 * @throws LineUnavailableException
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 */
	public void changeSong (String filename) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		
		// Stop the current song.
		clip.stop();
		
		
		// Allow user to specify a new song from the file name.
		audio = ResourceLoader.getLoader().getSoundClip(filename);
		clip = AudioSystem.getClip();
		clip.open(audio);
		
		
		// Start the new song. (End song only plays once)
		clip.start();

	}

}
