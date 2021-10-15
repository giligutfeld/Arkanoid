package arkanoid.gui;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - Line
 * A line connects two points - a start point and an end point.
 * Lines have lengths, and may intersect with other lines.
 * It can also tell if it is the same as another line segment and it implements Shape.
 */
public class Line implements Shape {

    // Every line has start and end points and for vertical lines there are slope and intersection with axis y
    private Point start;
    private Point end;
    private double slope;
    private double yCut;
    private boolean isVertical;
    private Color color;

    /**
     * Constructor.
     *
     * @param start point we draw a line to the end point
     * @param end   point we draw a line from the start point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;

        // Check if the line is vertical
        if (start.getX() == end.getX()) {
            this.isVertical = true;
        } else {
            // The line is not vertical so we calculate its slope and the intersection with axis y
            this.slope = (end.getY() - start.getY()) / (end.getX() - start.getX());
            this.isVertical = false;
            this.yCut = start.getY() - this.slope * start.getX();
        }
    }

    /**
     * Constructor.
     *
     * @param x1 the coordinate x of the first point
     * @param x2 the coordinate y of the first point
     * @param y1 the coordinate x of the second point
     * @param y2 the coordinate y of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Constructor.
     *
     * @param x1    the coordinate x of the first point
     * @param x2    the coordinate y of the first point
     * @param y1    the coordinate x of the second point
     * @param y2    the coordinate y of the second point
     * @param color is the color of the line.
     */
    public Line(double x1, double y1, double x2, double y2, Color color) {
        this(new Point(x1, y1), new Point(x2, y2));
        this.color = color;
    }

    /**
     * @return the length of the line
     */
    public double length() {
        return this.start().distance(this.end());
    }

    /**
     * @return the middle point of the line
     */
    public Point middle() {
        return new Point((this.end.getX() + this.start.getX()) / 2, (this.end.getY() + this.start.getY()) / 2);
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
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * @return the slope of the line
     */
    public double getSlope() {
        return this.slope;
    }

    /**
     * @return true if this line is vertical and false otherwise
     */
    public boolean getVertical() {
        return this.isVertical;
    }

    /**
     * @return the intersection of this line with axis y
     */
    public double getYCut() {
        return this.yCut;
    }

    /**
     * @param other the line we check intersection with this line
     * @return true if the lines intersect and false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * @param other the line we check intersection with this line
     * @return the intersection point if the lines intersect and null otherwise
     */
    public Point intersectionWith(Line other) {
        double x, y;
        // Check if one of the lines ends where the second starts or one of them is only a dot
        if (this.continuesOrContained(other) != null) {
            return this.continuesOrContained(other);
        }

        // In case their slopes are equal or both are vertical
        if (((!this.getVertical()) && (!other.getVertical()) && (this.getSlope() == other.getSlope()))
                || ((this.getVertical()) && (other.getVertical()))) {
            return null;
        }

        // In case one of the lines is vertical their intersection must be in its x coordinate
        if (this.getVertical()) {
            x = this.start().getX();
            y = other.getSlope() * x + other.getYCut();
        } else {
            if (other.getVertical()) {
                x = other.start().getX();
            } else {
                // In case both are not vertical calculate where their intersection should be by their equations
                x = (this.getYCut() - other.getYCut()) / (other.getSlope() - this.getSlope());
            }
            y = this.getSlope() * x + this.getYCut();
        }

        // Check that their expected intersection is on both the lines
        Point intersection = new Point(x, y);
        if ((intersection.isContained(this)) && (intersection.isContained(other))) {
            return intersection;
        }
        return null;
    }

    /**
     * @param other the line we check intersection with this line
     * @return the intersection point if the lines intersect and null otherwise
     */
    public Point continuesOrContained(Line other) {

        // Check if one of the lines is only a dot and contained in the other line
        if ((this.isPoint()) && (this.start().isContained(other))) {
            return this.start();
        }
        if ((other.isPoint()) && (other.start().isContained(this))) {
            return other.start();
        }

        // Check if one of the lines ends where the second starts
        if ((this.end().equals(other.start())) && (!this.start().isContained(other))
                && (!other.end().isContained(this))) {
            return other.start();
        }
        if (((this.start().equals(other.end())) && (!this.end().isContained(other))
                && (!other.start().isContained(this)))) {
            return this.start();
        }
        if ((other.start().equals(this.start())) && (!this.end().isContained(other))
                && (!other.end().isContained(this))) {
            return this.start();
        }
        if (other.end().equals(this.end()) && (!this.start().isContained(other))
                && (!other.start().isContained(this))) {
            return this.end();
        }
        return null;
    }

    /**
     * @param other the line we compare with this line
     * @return true if the lines are equal and false otherwise
     */
    public boolean equals(Line other) {

        // Check if the the points that create the lines are equal
        if ((this.start().equals(other.start())) && (this.end().equals(other.end()))) {
            return true;
        }
        if ((this.end().equals(other.start())) && (this.start().equals(other.end()))) {
            return true;
        }
        return false;
    }

    /**
     * @return true if the the start point equals to the end point and false otherwise
     */
    public boolean isPoint() {
        if (this.start().equals(this.end())) {
            return true;
        }
        return false;
    }

    /**
     * @param rect is the rectangle we check if intersects with the line
     * @return the closest intersection point to the start of the line or null if there is not
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point closest = null;
        List<Point> points = rect.intersectionPoints(this);

        // For every point the line intersects the rectangle check which is closer to the start of the line.
        for (Point p : points) {
            closest = this.closerPointToStart(closest, p);
        }

        // return the closest intersection to the start of the line
        return closest;
    }

    /**
     * @param p1 is first point we check close to the line
     * @param p2 is second point we check close to the line
     * @return the close point to the start of the line between p1, p2
     */
    public Point closerPointToStart(Point p1, Point p2) {

        // Check the points are not null
        if (p1 == null) {
            return p2;
        }
        if (p2 == null) {
            return p1;
        }

        // Check which point is closer to the start of the line.
        if (this.start().distance(p1) < this.start().distance(p2)) {
            return p1;
        }
        return p2;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(getColor());
        d.drawLine((int) start().getX(), (int) start().getY(), (int) end().getX(), (int) end().getY());
    }
}
