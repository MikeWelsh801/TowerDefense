package game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;


public class BlueCircle implements Animatable {

	// Field 
	private double position;

	
	public BlueCircle (double p) {
		position = p;
	}

	public void update(double timeElapsed) {
		position += 0.001;
		if(position > 1)
			position = 0.0;
	}


	public void draw(Graphics g, GameView view) {
		
		
		// Set up position and for a circle that moves along the path.
		Point p = ResourceLoader.getLoader().getPath("path.txt").getPathPosition(position);
		
		
		// Draw the ball.
		g.setColor(Color.BLUE);
		g.fillOval(p.x-15, p.y-15, 30, 30);
	}
	

	
}
