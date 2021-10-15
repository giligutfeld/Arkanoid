package arkanoid.gui;

import biuoop.DrawSurface;

import java.awt.Color;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - Circle
 * Circles have radius, color, and location (a Point).
 * Circles also know how to draw themselves on a DrawSurface and it implements Shape.
 */
public class Circle implements Shape {

    // Every Circle has center point, radius and color
    private Point point;
    private int radius;
    private Color color;

    /**
     * Constructor.
     *
     * @param point  is the point where the center of the circle.
     * @param radius is the radius of the circle.
     * @param color  is the color of the circle.
     */
    public Circle(Point point, int radius, Color color) {
        this.point = point;
        this.radius = radius;
        this.color = color;
    }

    /**
     * @return the x coordinate of the point of the center of this circle.
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * @return the y coordinate of the point of the center of this circle.
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * @return the radius of this circle.
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * @return the center point of the ball
     */
    public Point getPoint() {
        return this.point;
    }

    /**
     * @return the color of this ball
     */
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * @param p is the new point of the ball.
     */
    public void setPoint(Point p) {
        this.point = p;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(getColor());
        d.fillCircle(getX(), getY(), getRadius());
    }
}
