package arkanoid.gui;

import biuoop.DrawSurface;

import java.awt.Color;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Interface name - Shape
 * Shapes have color and can be drawn to the screen
 */
public interface Shape {

    /**
     * @param d draw the shape to there.
     */
    void drawOn(DrawSurface d);

    /**
     * @param c set the shape to be in this color.
     */
    void setColor(Color c);
}
