package arkanoid.gui;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - Rectangle
 * Rectangles have locatio, width and height. Our Rectangles will all be aligned with the axes.
 * htey can return us a line of the points a line intersects with and it implements Shape.
 */
public class Rectangle implements Shape {

    // Every rectangle has location, width and height.
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;

    /**
     * Constructor.
     *
     * @param upperLeft is the location of the rectangle.
     * @param width     is the width of the rectangle.
     * @param height    is the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructor.
     *
     * @param upperLeft is the location of the rectangle.
     * @param width     is the width of the rectangle.
     * @param height    is the height of the rectangle.
     * @param color is the color of the line.
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this(upperLeft, width, height);
        this.color = color;
    }

    /**
     * @param line intersects with the rectangle
     * @return a (possibly empty) List of intersection points with the line
     */
    public List<Point> intersectionPoints(Line line) {
        // Create a list of the intersection point between the line and the rectangle
        List<Point> list = new ArrayList<>();

        // For every rib of the rectangle check if the line intersects with it
        if (line.isIntersecting(this.getTop())) {
            list.add(line.intersectionWith(this.getTop()));
        }
        if (line.isIntersecting(this.getBottom())) {
            list.add(line.intersectionWith(this.getBottom()));
        }
        if (line.isIntersecting(this.getRight())) {
            list.add(line.intersectionWith(this.getRight()));
        }
        if (line.isIntersecting(this.getLeft())) {
            list.add(line.intersectionWith(this.getLeft()));
        }
        return list;
    }

    /**
     * @return the coordinate x of the location of the rectangle
     */
    public double getStartX() {
        return this.getUpperLeft().getX();
    }

    /**
     * @return the coordinate y of the location of the rectangle
     */
    public double getStartY() {
        return this.getUpperLeft().getY();
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the top line of the rectangle
     */
    public Line getTop() {
        return new Line(this.getStartX(), this.getStartY(), this.getStartX() + this.getWidth(), this.getStartY());
    }

    /**
     * @return the botoom line of the rectangle
     */
    public Line getBottom() {
        return new Line(this.getStartX(), this.getStartY() + this.getHeight(),
                this.getStartX() + this.getWidth(), this.getStartY() + this.getHeight());
    }

    /**
     * @return the right line of the rectangle
     */
    public Line getRight() {
        return new Line(this.getStartX() + this.getWidth(), this.getStartY(),
                this.getStartX() + this.getWidth(), this.getStartY() + this.getHeight());
    }

    /**
     * @return the left line of the rectangle
     */
    public Line getLeft() {
        return new Line(this.getStartX(), this.getStartY(), this.getStartX(), this.getStartY() + this.getHeight());
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
     * @param d          is where we draw this rectangle on.
     * @param frameColor is the color of the frame around th rectangle.
     * @param c      is the color of the rectangle we draw.
     */
    public void drawFramedRectangle(DrawSurface d, Color frameColor, Color c) {
        // Firstly, draw the frame on the surface
        d.setColor(frameColor);
        d.fillRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(), (int) getWidth(), (int) getHeight());

        // Then, draw the rectangle on the surface
        d.setColor(c);
        d.fillRectangle((int) getUpperLeft().getX() + 1, (int) getUpperLeft().getY() + 1,
                (int) getWidth() - 2, (int) getHeight() - 2);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(getColor());
        d.fillRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(), (int) getWidth(), (int) getHeight());
    }
}
