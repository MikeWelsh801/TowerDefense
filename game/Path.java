/**
 * This class allows a user to create new path objects, which can build a path
 * along a JPanel. It has methods to calculate the length of the path, draw a
 * line along the path, and find the position of a given percentage from the
 * start of the path.
 * 
 * @author Michael Welsh
 * @version November 1, 2021
 */
package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JPanel;


public class Path extends JPanel {

	/**
	 * Created serialVersionID to remove an error.
	 */
	private static final long serialVersionUID = 1L;

	// Fields for Path objects. (array of coordinates, x and y arrays, length of
	// arrays).
	private int[] x;
	private int[] y;
	private int clickCount;

	/**
	 * This constructor does the following: - It reads a number of coordinates, n,
	 * from the scanner. - It creates new array(s) (or an ArrayList) to hold the
	 * path coordinates, and stores it in the field in 'this' object. - It loops n
	 * times, each time scanning a coordinate x,y pair, creating an object to
	 * represent the coordinate (if needed), and stores the coordinate in the
	 * array(s) or ArrayList.
	 * 
	 * @param s a Scanner set up by the caller to provide a list of coordinates
	 */
	public Path(Scanner s) {

		// Capture clickCount from the scanner.
		// Initialize coordinate arrays.
		this.clickCount = s.nextInt();
		this.x = new int[clickCount];
		this.y = new int[clickCount];

		// Fill the x, y arrays by looping through the scanner object.
		for (int i = 0; i < clickCount; i++) {
			this.x[i] = s.nextInt();
			this.y[i] = s.nextInt();
			
		}

	}

	/**
	 * Draws the current path to the screen (to wherever the graphics object points)
	 * using a highly-visible color.
	 * 
	 * @param g a graphics object
	 */
	public void draw(Graphics g) {

		// Cast as a 2D graphic.
		Graphics2D g2d = (Graphics2D) g;

		// Set color and line width.
		g2d.setPaint(new Color(218, 99, 77));
		g2d.setStroke(new BasicStroke(5));

		// Loop through points and draw lines between each point.
		for (int i = 0; i < x.length - 1; i++)
			g2d.drawLine(x[i], y[i], x[i + 1], y[i + 1]);
	}

	/**
	 * Returns the total length of the path. Since the path is specified using
	 * screen coordinates, the length is in pixel units (by default).
	 * 
	 * @return the length of the path
	 */
	public double getPathLength() {

		// Initialize length at zero.
		double length = 0;

		// Calculate the distance formula between each point, and add them up.
		for (int i = 0; i < x.length - 1; i++) {
			double psum = Math.sqrt(Math.pow((x[i + 1] - x[i]), 2) + Math.pow((y[i + 1] - y[i]), 2));
			length = length + psum;
		}

		return length;
	}

	/**
	 * Given a percentage between 0% and 100%, this method calculates the location
	 * along the path that is exactly this percentage along the path. The location
	 * is returned in a Point object (integer x and y), and the location is a screen
	 * coordinate.
	 * 
	 * If the percentage is less than 0%, the starting position is returned. If the
	 * percentage is greater than 100%, the final position is returned.
	 * 
	 * If students don't want to use Point objects, they may write or use another
	 * object to represent coordinates.
	 *
	 * Caution: Students should never directly return a Point object from a path
	 * list. It could be changed by the outside caller. Instead, always create and
	 * return new point objects as the result from this method.
	 * 
	 * @param percentTraveled a distance along the path
	 * @return the screen coordinate of this position along the path
	 */
	public Point getPathPosition(double percentTraveled) {

		// Calculate the distance from the start to p by calling the getPathLength
		// method, and multiplying the total length by the percent traveled.
		double length = getPathLength();
		double distance = length * percentTraveled;

		// Initialize some useful variables. The current segment's starting and ending
		// coordinates, the length of the current segment, and the final point.
		double xStart = 0;
		double xFin = 0;
		double yStart = 0;
		double yFin = 0;
		double segmentLength = 0;
		Point p;

		// Loop through the points and calculate the length of each segment.
		for (int i = 0; i < x.length - 1; i++) {
			segmentLength = Point.distance(x[i], y[i], x[i + 1], y[i + 1]);

			// If the remaining distance from the start is greater than the current segment
			// length, subtract the current segment length from the distance.
			if (distance > segmentLength)
				distance = distance - segmentLength;

			// If the remaining distance is less than the current segment length, store the
			// coordinates of the start and end point of the current segment and break out
			// of the loop.
			else {

				xStart = x[i];
				yStart = y[i];
				xFin = x[i + 1];
				yFin = y[i + 1];
				break;
			}
		}

		// Calculate the percentage along the segment to travel by dividing the
		// remaining distance by the current segment length.
		double remPercent = distance / segmentLength;

		// Calculate a weighted average using the remaining percentage and the starting
		// points to find the point along the segment that is remPercent from the
		// starting coordinate.
		int newX = (int) ((1 - remPercent) * xStart + remPercent * xFin);
		int newY = (int) ((1 - remPercent) * yStart + remPercent * yFin);

		// Create a new point with the found coordinates.
		p = new Point(newX, newY);

		// If the percentage is less than 0, return the starting position.
		if (percentTraveled < 0)
			p = new Point(x[0], y[0]);

		// If the percentage is greater than 100, return the last position.
		if (percentTraveled > 1)
			p = new Point(x[x.length - 1], y[y.length - 1]);

		return p;
	}
	
	/**
	 * Given two points on a map, this method finds the distance to the nearest path
	 * point.
	 * 
	 * @param x - an x coordinate on the map
	 * @param y - a y coordinate on the map
	 * @return - the distance to the nearest point along the path
	 */
	public double distanceToNearestPathNode(int x, int y) {
		
		// Construct a point with the coordinates.
		Point p = new Point(x, y);
		
		// Create a path to check points.
		Path path = ResourceLoader.getLoader().getPath("path.txt");
		
		// Create an array list to hold all of the points.
		List<Point> pathArray = new ArrayList<Point>();
		
		
		// Dump path points into an array list.
		for(int i = 1; i < 1000; i++) {
			// Find 5000 positions along the path and add them to the array list.
			double position = (double)i/1000.0;
			pathArray.add(path.getPathPosition(position));
		}
		
		
		// Initialize the shortest distance as the distance to the first point.
		double shortest = p.distance(pathArray.get(0));
		
		// Loop though all the points in the path and check the distance to the point given.
		for(Point check : pathArray) {
			double distance = check.distance(p);
			
			// If we find a distance that is shorter than the current one, set it to the shortest
			// distance. 
			if (distance < shortest)
				shortest = distance;
			
		}
			
		return shortest;
		

	}
}
