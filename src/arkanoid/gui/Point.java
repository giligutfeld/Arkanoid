package arkanoid.gui;

import java.util.Random;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - Point
 * A point has an x and a y value, and can measure the distance to other points,
 * and if it is equal to another point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor.
     *
     * @param x the coordinate X of the point
     * @param y the coordinate y of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor.
     *
     * @param x the coordinate X of the point
     * @param y the coordinate y of the point
     */
    public Point(int x, int y) {
        this(x, (double) (y));
    }

    /**
     * Constructor.
     *
     * @param left   is the left edge of the coordinates we random
     * @param right  is the right edge of the coordinates we random
     * @param top    is the top edge of the coordinates we random
     * @param bottom is the bottom edge of the coordinates we random
     */
    public Point(int left, int right, int top, int bottom) {
        Random rand = new Random(); // create a random-number generator
        if (right >= left) {
            this.x = rand.nextDouble() + rand.nextInt(right - left) + left; // get double in range left to right
        } else {
            this.x = rand.nextDouble() + rand.nextInt(left - right) + left; // get double in range right to left
        }
        if (bottom > top) {
            this.y = rand.nextDouble() + rand.nextInt(bottom - top) + top; // get double in top up to bottom
        } else {
            this.y = rand.nextDouble() + rand.nextInt(top - bottom) + bottom;
        }
    }

    /**
     * Constructor.
     *
     * @param left   is the left edge of the coordinates we random
     * @param right  is the right edge of the coordinates we random
     * @param top    is the top edge of the coordinates we random
     * @param bottom is the bottom edge of the coordinates we random
     */
    public Point(double left, double right, double top, double bottom) {
        this((int) left, (int) right, (int) top, (int) bottom);
    }

    /**
     * @param other the point we measure the distance of this point
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(other.getX() - this.getX(), 2) + Math.pow(other.getY() - this.getY(), 2));
    }

    /**
     * @param other the point we equalize to this point
     * @return true if the points are equal (level accuracy: 2 digits after point), false otherwise
     */
    public boolean equals(Point other) {
        double epsilon = Math.pow(10, -8);
        if ((Math.abs(this.getX() - other.getX()) < epsilon) && (Math.abs(this.getY() - other.getY()) < epsilon)) {
            return true;
        }
        return false;
    }

    /**
     * @return the x coordinate of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @param num is the new x coordinate of the point.
     */
    public void setX(double num) {
        this.x = num;
    }

    /**
     * @return the y coordinate of the point
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param line the line we check if the point is contained there
     * @return true if the line contains the point and false otherwise
     */
    public boolean isContained(Line line) {

        // Check that the point is on the line according to the start and the end points of the line
        if (((this.getX() < line.start().getX()) && (this.getX() < line.end().getX()))
                || ((this.getX() > line.start().getX()) && (this.getX() > line.end().getX()))) {
            return false;
        }
        if ((line.getVertical()) && (((this.getY() < line.start().getY()) && (this.getY() < line.end().getY()))
                || ((this.getY() > line.start().getY()) && (this.getY() > line.end().getY())))) {
            return false;
        }

        // Check the point is on the line according to its equation
        if ((this.equals(new Point(this.getX(), line.getSlope() * this.getX() + line.getYCut())))
                || ((line.getVertical()) && (line.start().getX() == this.getX()))) {
            return true;
        }
        return false;
    }

    /**
     * @param rect the rectangle we check if the point is contained there
     * @return true if the rectangle contains the point and false otherwise
     */
    public boolean isContained(Rectangle rect) {
        if (rect == null) {
            return false;
        }

        // Check if the point is contained in the rectangle
        if ((this.getX() >= rect.getStartX()) && (this.getX() <= rect.getStartX() + rect.getWidth())
                && (this.getY() >= rect.getStartY()) && (this.getY() <= rect.getStartY() + rect.getHeight())) {
            return true;
        }
        return false;
    }

    /**
     * @param rect the rectangle we check if the point is a corner of it
     * @return true if the point is a corner of the rectangle
     */
    public boolean isCorner(Rectangle rect) {
        // Check if the point is one of the corners of the rectangle
        if ((this.equals(rect.getTop().start())) || (this.equals(rect.getTop().end()))
                || (this.equals(rect.getBottom().start())) || (this.equals(rect.getBottom().end()))) {
            return true;
        }
        return false;
    }
}
