package arkanoid.gui.ball;

import arkanoid.gui.Point;

import java.util.Random;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - Velocity
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     *
     * @param dx is the how much pixels the ball moves right and left every step it does
     * @param dy is the how much pixels the ball moves up and down every step it does
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return the dx of the velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the dy of the velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @return the speed of the velocity.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(getDx(), 2) + Math.pow(getDy(), 2));
    }

    /**
     * @return the angle of the velocity.
     */
    public double getAngle() {
        return Math.asin(dx / dy);
    }

    /**
     * @param p is the point where the ball was until now
     * @return new point with the new position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(getDx() + p.getX(), getDy() + p.getY());
    }

    /**
     * @param angle the direction of the velocity
     * @param speed the speed of the velocity
     * @return new velocity according to the angle and the speed we got
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Calculate the dx speed and dy speed
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * @param direction is where to random the velocity (north, south, east or west).
     * @param range is the angle that the velocity can be random.
     * @param speed the speed of the velocity.
     * @return a random new velocity according to the direction, range and speed we got.
     */
    public static Velocity randomVelocity(int direction, int range, int speed) {
        // create a random-number generator for random colors.
        Random rand = new Random();
        return Velocity.fromAngleAndSpeed(direction + rand.nextInt(range) - range / 2, speed);
    }
}
