package arkanoid.sprites;

import arkanoid.Animations.GameLevel;
import arkanoid.sprites.collidables.GameEnvironment;
import arkanoid.sprites.collidables.CollisionInfo;
import arkanoid.gui.Line;
import arkanoid.gui.Point;
import arkanoid.gui.ball.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - Ball
 * Balls (actually, a circle) have size (radius), color, and location (a Point).
 * Balls also know how to draw themselves on a DrawSurface.
 */
public class Ball implements Sprite {

    // Every ball has center point, radius, color, velocity and a frame to move there
    private Point point;
    private int radius;
    private Color color;
    private Velocity v;
    private GameEnvironment environment;

    /**
     * Constructor.
     *
     * @param point  is the point where the center of the ball
     * @param radius is the radius of the ball
     * @param color  is the color of the ball
     */
    public Ball(Point point, int radius, Color color) {
        this.point = point;
        this.radius = radius;
        this.color = color;
        this.v = new Velocity(0, 0);
    }

    /**
     * Constructor.
     *
     * @param x      is the x coordinate of the point where the center of the ball
     * @param y      is the y coordinate of the point where the center of the ball
     * @param radius is the radius of the ball
     * @param color  is the color of the ball
     */
    public Ball(double x, double y, int radius, java.awt.Color color) {
        this(new Point(x, y), radius, color);
    }

    /**
     * @return the x coordinate of the point of the center of this ball
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * @return the y coordinate of the point of the center of this ball
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * @return the radius of this ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return the center point of the ball
     */
    public Point getPoint() {
        return this.point;
    }

    /**
     * @return the game environment the ball can move there
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * @return the color of this ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return the velocity of this ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * @return the trajectory of the ball (line between its place and the place it will be after one step
     */
    public Line getTrajectory() {
        Point start = this.getPoint();
        Velocity velocity = this.getVelocity();
        return new Line(start, new Point(start.getX() + velocity.getDx(), start.getY() + velocity.getDy()));
    }

    /**
     * @param p is the new point of the ball.
     */
    public void setPoint(Point p) {
        this.point = p;
    }

    /**
     * @param d is where we draw this ball on
     */
    public void drawOn(DrawSurface d) {
        // Firstly, draw the frame on the surface
        d.setColor(Color.BLACK);
        d.fillCircle(getX(), getY(), getSize());

        // Then, draw the rectangle on the surface
        d.setColor(getColor());
        d.fillCircle(getX(), getY(), getSize() - 1);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * @param velocity is the new velocity we put in this ball
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * @param e is the new environment we put in this ball
     */
    public void setGameEnvironment(GameEnvironment e) {
        this.environment = e;
    }

    /**
     * @param dx is the dx of the velocity of this ball
     * @param dy is the dy of the velocity of this ball
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * moves the ball one step according to the velocity of this ball.
     */
    public void moveOneStep() {
        CollisionInfo info = this.getEnvironment().getClosestCollision(this.getTrajectory());

        // If the ball collided change its velocity, otherwise change its location according to its velocity
        if ((info != null) && (info.collisionPoint() != null)) {
            almostCollisionPoint(info);
            setVelocity(info.collisionObject().hit(this, info.collisionPoint(), getVelocity()));
        } else {
            setPoint(getVelocity().applyToPoint(this.getPoint()));
        }
    }

    /**
     * Put the ball "almost" at the collision point.
     *
     * @param info is the info about the collision occurred.
     */
    public void almostCollisionPoint(CollisionInfo info) {

        int addDX = 0;
        int addDY = 0;

        // In case the collision is not in the corner check which rib of the object contains the collision point
        if (!info.collisionPoint().isCorner(info.collisionObject().getCollisionRectangle())) {
            if (info.collisionPoint().isContained(info.collisionObject().getCollisionRectangle().getTop())) {
                addDY--;
            } else if (info.collisionPoint().isContained(info.collisionObject().getCollisionRectangle().getBottom())) {
                addDY++;
            }
            if (info.collisionPoint().isContained(info.collisionObject().getCollisionRectangle().getRight())) {
                addDX++;
            } else if (info.collisionPoint().isContained(info.collisionObject().getCollisionRectangle().getLeft())) {
                addDX--;
            }
        } else {
            // In case the collision is in the corner check where the ball would be if he continues almost
            if (this.getPoint().getX() < info.collisionPoint().getX()) {
                addDX--;
            } else {
                addDX++;
            }
            if (this.getPoint().getY() < info.collisionPoint().getY()) {
                addDY--;
            } else {
                addDY++;
            }
        }

        // Put the ball "almost" where is the collision point.
        this.setPoint(new Point(info.collisionPoint().getX() + addDX, info.collisionPoint().getY() + addDY));
    }

    /**
     * @param game is the game where we move from the ball.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
