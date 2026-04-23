package com.project4;

import processing.core.*;

import java.util.ArrayList;
import java.io.File;

/**
 * Shape Paint
 * 
 * Creates a canvas window to paint into with
 * various shape stamps.
 * 
 * Controls
 * Left Mouse Click: Add shape to canvas.
 * Right Mouse Click: Undo adding previous shape.
 * 1: Change shape.
 * 2: Change stroke color.
 * 3: Change fill color.
 * 4: Change background color.
 * +: Increase size.
 * -: Decrease size.
 * 0: Save a screenshot to the screenshot path.
 * Backspace: Undo adding previous shapes.
 * DEL: Clear canvas.
 * Escape: Exit program.
 */
public class ShapePaint extends PApplet {
	
	float size = 100f;
	final float sizeInc = 10.0f;
	
	boolean canTakeScreenshot = false;
	
	final int MODE_CIRCLE = 0;
	final int MODE_SQUARE = 1;
	final int MODE_RECTANGLE = 2;
	final int MODE_TRIANGLE = 3;
	final int MODE_MAX = 4;
	int mode = 0;
	
	final int[] MODES = {MODE_CIRCLE, MODE_SQUARE, MODE_RECTANGLE, MODE_TRIANGLE};
	
	final int WHITE   = color(255, 255, 255, 255);
	final int BLACK   = color(  0,   0,   0, 255);
	final int RED     = color(255,   0,   0, 255);
	final int BLUE    = color(0, 43, 255, 255);
	final int GREEN   = color(45, 255, 42, 255);
	final int YELLOW  = color(245, 255, 42, 255);
	final int ORANGE  = color(245, 172, 42, 255);
	final int PURPLE  = color(146, 45, 234, 255);
	final int STRAWBERRYRED = color(249, 57, 67, 255);
	final int HOTPINK = color(329, 91, 68, 255);
	final int TURQUIOSE = color(174, 70, 59, 255);
	final int SEAGREEN = color(147, 93, 28, 255);
	final int INFERNO = color(0, 93, 33, 255);
	
	int colorIndexStroke = 0;
	int colorIndexFill = 0;
	int colorIndexBackground = 0;
	// TODO: Create an int array of colors.
	int[] colors = {BLACK, WHITE, RED, BLUE, GREEN, YELLOW, ORANGE, PURPLE, STRAWBERRYRED, HOTPINK, SEAGREEN, INFERNO};
	
	// TODO: Create lists here to hold instances of our shapes.
	ArrayList<Circle> circles = new ArrayList<Circle>();
	ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	ArrayList<Triangle> triangles = new ArrayList<Triangle>();
	ArrayList<Integer> shapeList = new ArrayList<Integer>();


	
	
	/**
	 * The starting method of our program.
	 * @param args Program command line arguments.
	 */
	public static void main(String[] args) {
		// The String argument here to our PApplet
		// main method must match the package and 
		// class name of our program.
		PApplet.main("com.project4.ShapePaint");
	}
	
	/**
	 * Startup settings. First method called 
	 * after main. Used to initialize
	 * properties of our window/canvas.
	 */
	public void settings() {
		// Set the size of the window.
		size(500, 500);
	}

	/**
	 * Setup. Called after settings(). 
	 * Used to initialize other elements of 
	 * the drawing program.
	 */
	public void setup() {
		windowTitle("Shape Paint");
		stroke(255, 0, 0, 255);
		strokeWeight(5.0f);
		
		// Set rectangle draw mode so <x,y> 
		// represents the center of a rectangle.
		rectMode(CENTER);
		
		determineStartingScreenshotNumber();
	}

	/**
	 * Draw method.
	 * Called every frame to redraw our canvas.
	 */
	public void draw() {
		// Clear background.
		drawBackground();
		
		// Draw our shape ArrayLists here.
		drawShapes();
		
		// Check if we should take a screenshot.
		checkForScreenshot();
		
		// Draw a shape at our mouse <x,y> position.
		drawShapeAtMouse();
	}
	
	/**
	 * Draw a single, solid color as the background.
	 */
	public void drawBackground()
	{
		// TODO: Replace hard-coded BLACK here with 
		// contents of the color array at the index
		// of colorIndexBackground.
		background(colors[colorIndexBackground]);
	}
	
	/**
	 * Draws each shape in our shape lists.
	 * Takes each shape list and iterates 
	 * through them, calling the draw method 
	 * of each shape with "this" as argument.
	 */
	public void drawShapes()
	{
		// TODO: Iterate through each of our shape ArrayLists,
		// and call the draw method of each shape in the lists.
		for(Circle c : circles) {
			c.draw(this);
		}
		
		for(Rectangle r : rectangles) {
			r.draw(this);
		}
		
		for(Triangle t : triangles) {
			t.draw(this);
		}
		
	}
	
	/**
	 * Checks if we should take a screenshot.
	 */
	public void checkForScreenshot() 
	{
		if (canTakeScreenshot)
		{
			saveScreenshot();
			canTakeScreenshot = false;
		}
	}
	
	/**
	 * Draws the currently selected shape 
	 * at the current mouse <x,y> position.
	 */
	public void drawShapeAtMouse()
	{
		int x = mouseX;
		int y = mouseY;

		// TODO: Create an instance of our current mode's shape
		// using the switch statement below, and draw that shape 
		// at the mouse's <x,y>. Until then, we'll just temporarily 
		// draw a white dot at the mouse position.
		
		// TODO: Fill in.
		switch (MODES[mode])
		{
		case MODE_CIRCLE:
			Circle circle = new Circle(x, y, size, colors[colorIndexStroke], colors[colorIndexFill]);
			circle.draw(this);
			break;
			
		case MODE_SQUARE:
			Rectangle square = new Rectangle(x, y, size, size, colors[colorIndexStroke], colors[colorIndexFill]);
			square.draw(this);
			break;
			
		case MODE_RECTANGLE:
			float w = size * 1.6f;
			float h = size;
			Rectangle rectangle = new Rectangle(x, y, w, h, colors[colorIndexStroke], colors[colorIndexFill]);
			rectangle.draw(this);
			break;
			
		case MODE_TRIANGLE:
			Triangle triangle = new Triangle(x, y, size, colors[colorIndexStroke], colors[colorIndexFill]);
			triangle.draw(this);
			break;
		}
	}

	/**
	 * Called when a mouse button is released.
	 * Left: Add a shape.
	 * Right: Undo a shape.
	 */
	public void mouseReleased()
	{
		if (mouseButton == LEFT) 
		{
			System.out.println("Clicked left mouse button.");
			
			shapeList.add(MODES[mode]);
			
			// TODO: First, Remember which shape we just made here
			// so we can "undo" it later if we want to.
			// TODO: Second, add a new shape to our shape ArrayLists.
			switch (MODES[mode])
            {
            case MODE_CIRCLE:
                circles.add(
                    new Circle(
                        mouseX, mouseY, 
                        size, 
                        colors[colorIndexStroke], 
                        colors[colorIndexFill]));
                break;
                
            case MODE_SQUARE:
                rectangles.add(
                    new Rectangle(
                        mouseX, mouseY, 
                        size, size, 
                        colors[colorIndexStroke], 
                        colors[colorIndexFill]));
                break;
                
            case MODE_RECTANGLE:
                rectangles.add(
                    new Rectangle(
                        mouseX, mouseY, 
                        size*2.0f, size, 
                        colors[colorIndexStroke], 
                        colors[colorIndexFill]));
                break;
                
            case MODE_TRIANGLE:
                triangles.add(
                    new Triangle(
                        mouseX, mouseY, 
                        size, 
                        colors[colorIndexStroke], 
                        colors[colorIndexFill]));
                break;
            }
		} 
		else if (mouseButton == RIGHT) 
		{
			System.out.println("Clicked right mouse button.");
			
			undo();
		}
	}
	
	/**
	 * Remove the last shape added.
	 */
	public void undo()
	{
		
		// TODO: Undo last shape added.
		if (shapeList.isEmpty()) {
			return;
		}
		int lastShape = shapeList.remove(shapeList.size() - 1);
		
		switch (lastShape)
		{
		case MODE_CIRCLE:
			if(circles.size() > 0) {
				circles.remove(circles.size() - 1);
			}
			break;
			
		case MODE_SQUARE:
		case MODE_RECTANGLE:
			if(rectangles.size() > 0) {
				rectangles.remove(rectangles.size() - 1);
			}
			break;
			
		case MODE_TRIANGLE:
			if(triangles.size() > 0) {
				triangles.remove(triangles.size() - 1);
			}
			break;
		}
	}
	
	/**
	 * Clear the entire canvas.
	 * I.E. Delete all of our placed shapes.
	 */
	void clearCanvas()
	{
		// TODO: Clear all of our shape lists.
		triangles.clear();
		rectangles.clear();
		circles.clear();
	}

	/**
	 * Called when a keyboard key is pressed.
	 * Handle controls like changing the shape type,
	 * stroke and fill colors, and size.
	 */
	public void keyPressed()
	{
		switch (key)
		{
		case '+':
		case '=':
			size += sizeInc;
			break;
		case '-':
		case '_':
			size -= sizeInc;
			break;
		
		case '1':
			mode = (mode+1)%MODE_MAX;
			break;
			
		case '2':
			// TODO: Update the stroke color index.
			colorIndexStroke = colorIndexStroke + 1;
			if(colorIndexStroke == colors.length)
			{
				colorIndexStroke = 0;
			}
			break;
		case '3':
			// TODO: Update the fill color index.
			colorIndexFill = colorIndexFill + 1;
			if(colorIndexFill == colors.length){
				colorIndexFill = 0;
			}
			break;
		case '4':
			// TODO: Update the background color index.
			colorIndexBackground = colorIndexBackground + 1;
			if(colorIndexBackground == colors.length) 
			{
				colorIndexBackground = 0;
			}
			break;
			
		case DELETE:
			clearCanvas();
			break;
			
		case BACKSPACE:
			undo();
			break;
			
		case '0':
		case 's':
		case 'S':
			canTakeScreenshot = true;
			break;
		}
	}
	
	/**
	 * Save a screenshot.
	 */
	final String screenshotPath = "./screenshots/";
	final String filename = "masterpiece_";
	final String extension = ".png";
	int pictureNumber = 1;
	void saveScreenshot() 
	{
		String screenshotFilename = 
			screenshotPath + filename + 
			((pictureNumber < 100) ? "0" : "") +
			((pictureNumber < 10)  ? "0" : "") +
			(pictureNumber++) + 
			extension;
		save(screenshotFilename);
		System.out.println(
			"Saved screenshot: " + screenshotFilename);
	}
	
	/**
	 * Look at our screenshot directory and check
	 * what the next image filename number should be.
	 */
	void determineStartingScreenshotNumber()
	{
		File directory = new File(screenshotPath);
		File[] files = directory.listFiles();
		if (files != null) {
			pictureNumber = files.length+1;
		} else {
			System.out.println("Error reading screenshot directory: " + screenshotPath);
		}
	}
}
