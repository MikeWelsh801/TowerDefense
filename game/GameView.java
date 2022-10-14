/**
 * The GameView class creates a GameView object that contains a background,
 * path, enemies, towers, and menus for a Tower Defense game. It initializes
 * a JPanel loads in/draws all of the graphic elements of the game.
 * 
 * @author Michael Welsh
 * @version November 1, 2021
 */
package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView extends JPanel implements MouseListener, MouseMotionListener {
	// This constant is needed to get rid of a warning. It won't matter to us.

	private static final long serialVersionUID = 1L;

	// Fields -- These variables will be part of the GameView object (that we make
	// in GameControl).
	private GameState state;

	/**
	 * Our GameView constructor. The 'view' is the GUI (Graphical User Interface)
	 * and this constructor builds a JFrame (window) so the user can see our
	 * 'drawing'.
	 */
	public GameView(GameState state) {

		// Give access to the game state.
		this.state = state;

		// Build the frame. The frame object represents the application 'window'.
		JFrame frame = new JFrame("Fry's Enlightenment");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add a drawing area to the frame (a panel). Note that 'this' object IS the
		// panel that we need, so we add it.
		JPanel p = this;
		frame.setContentPane(p);

		// Set the size of 'this' panel to match the size of the backdrop.
		Dimension d = new Dimension(850 + 298, 530);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setMaximumSize(d);

		// Change jFrame Icon to planet express logo.
		frame.setIconImage(ResourceLoader.getLoader().getImage("FuturamaLogo.png"));

		// Allow the JFrame to layout the window (by 'packing' it) and make it visible.
		frame.pack();
		frame.setVisible(true);

		// Set the frame location so it opens in the middle of the screen.
		frame.setLocationRelativeTo(null);

		// This panel can send mouse events to any object that wants to 'listen' to
		// those events. I've removed the lines of code for the mouse listener and
		// timer, feel free to re-add them as needed.
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	/**
	 * Draws our game. This function will be called automatically when Java needs to
	 * repaint our window. Use the repaint() function call (on this object) to cause
	 * this function to be executed.
	 * 
	 * @param Graphics g the Graphics object to use for drawing
	 */
	public void paint(Graphics g) {

		// Draw everything.
		state.drawAll(g, this);
	}

	/*
	 * The following methods are required for mouse events. I've collapsed some of
	 * them to make it easier to see which one you need. Also note: You'll need to
	 * register 'this' object as a listener to its own events. See the missing code
	 * in the constructor.
	 */

	/**
	 * Changes the isMouseClicked boolean in game state to true. I've added a
	 * diagnostic line that prints the current run time on a click. I'll comment it
	 * out for the final version.
	 */
	public void mousePressed(MouseEvent e) {

		// System.out.println(control.getTime());
		state.setMouseClicked();
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	/**
	 * Sets the current mouse location to where the mouse is.
	 */
	@Override
	public void mouseDragged(MouseEvent e) {

		state.setMouseLocation(e.getX(), e.getY());

	}

	/**
	 * Sets the current mouse location to where the mouse is.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {

		state.setMouseLocation(e.getX(), e.getY());

	}

	/**
	 * This allows the user to draw in an image from it's center position.
	 * 
	 * @param g          - a graphics object
	 * @param imageName- the name of the image file
	 * @param x          - x coordinate
	 * @param y          - y coordinate
	 * @param scale      - scale of the image being drawn (default is one)
	 */
	public void drawCenteredImage(Graphics g, String imageName, int x, int y, double scale) {

		// Load the image find width, height, and center coordinates.
		BufferedImage image = ResourceLoader.getLoader().getImage(imageName);
		int width = image.getWidth();
		int height = image.getHeight();
		int centerX = x - (int) (width / 2 * scale);
		int centerY = y - (int) (height / 2 * scale);

		// Draw image from the center.
		g.drawImage(image, centerX, centerY, (int) (width * scale), (int) (height * scale), null);

	}

}
