package com.project4;

import processing.core.PApplet;


public class Rectangle {
	private float x;
    private float y;
    private float width;
    private float height;
    private int colorStroke;
    private int colorFill;

    public Rectangle(float inX, float inY, float inSideLength, int colorS, int colorF) {
        x = inX;
        y = inY;
        width = inSideLength;
        height = inSideLength;
        colorStroke = colorS;
        colorFill = colorF;
    }

    public Rectangle(float inX, float inY, float w, float h, int colorS, int colorF) {
        x = inX;
        y = inY;
        width = w;
        height = h;
        colorStroke = colorS;
        colorFill = colorF;
    }

    public void draw(PApplet canvas) {
        canvas.stroke(colorStroke);
        canvas.fill(colorFill);
        canvas.rect(x, y, width, height);
    }

}
