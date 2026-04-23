package com.project4;

import processing.core.PApplet;


public class Triangle {
	private float x;
	private float y;
	
	private float sideLength;
	
	private int colorStroke;
	private int colorFill;
	
	public Triangle(float inX, float inY, float inSideLength, int colorS, int colorF) {
		x = inX;
		y = inY;
		sideLength = inSideLength;
		colorStroke = colorS;
		colorFill = colorF;
		
	}
	
	public void draw(PApplet canvas) {
		float height = (float) (Math.sqrt(3) / 2 * sideLength);
        
        float x1 = x;
        float y1 = y - (2f / 3f) * height;   
        float x2 = x - sideLength / 2;
        float y2 = y + (1f / 3f) * height;   
        float x3 = x + sideLength / 2;
        float y3 = y + (1f / 3f) * height;  

		
		canvas.stroke(colorStroke);
        canvas.fill(colorFill);
        canvas.triangle(x1, y1, x2, y2, x3, y3);
	}

}
