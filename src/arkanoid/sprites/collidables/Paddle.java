package arkanoid.sprites.collidables;

import arkanoid.Animations.GameLevel;
import arkanoid.sprites.Sprite;
import arkanoid.gui.Line;
import arkanoid.gui.Point;
import arkanoid.gui.Rectangle;
import arkanoid.sprites.Ball;
import arkanoid.gui.ball.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - Paddle
 * The Paddle is the player in the game. It is a rectangle that is controlled by the arrow keys, and moves according
 * to the player key presses. It implements the Sprite and the Collidable interfaces. It knows how to move to the left
 * and to the right. Paddles have size (width and height), color and location (a Point). Paddles also know how to draw
 * themselves on a DrawSurface and add themselves to game.
 */
public class Paddle implements Sprite, Collidable {

    // The number of regions the paddle has
    public static final int REGIONS = 15;

    // The number of degrees in a circle, the degrees in the left and the degrees between the regions
    private static final int DEGREES = 360;
    private static final int DEGREES_LEFT = 300;
    private static final int DEGREES_BETWEEN_REGIONS = ((DEGREES - DEGREES_LEFT) * 2) / (REGIONS - 1);

    // Every block has rectangle, color and frame color to draw it and edges to move between them with the keyboard
    private Rectangle rectangle;
    private KeyboardSensor keyboard;
    private Color color;
    private int leftEdge;
    private int rightEdge;
    private int move;

    /**
     * Constructor.
     *
     * @param rect      is the rectangle of the paddle, where it starts.
     * @param color     is the color of the paddle
     * @param keyboard  is the keys the user can press to move
     * @param leftEdge  is the left edge the paddle can move to
     * @param rightEdge is the right edge the paddle can move to
     * @param move      is the size of the step of the paddle.
     */
    public Paddle(Rectangle rect, KeyboardSensor keyboard, Color color, int leftEdge, int rightEdge, int move) {
        this.rectangle = rect;
        this.keyboard = keyboard;
        this.color = color;
        this.leftEdge = leftEdge;
        this.rightEdge = rightEdge;
        this.move = move;
    }

    /**
     * @return the move the paddle do every step.
     */
    public int getMove() {
        return this.move;
    }

    /**
     * @return the keyboard of the paddle
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * @return the color of the paddle
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return the left edge of the paddle
     */
    public int getLeftEdge() {
        return this.leftEdge;
    }

    /**
     * @return the right edge of the paddle
     */
    public int getRightEdge() {
        return this.rightEdge;
    }

    /**
     * @return the rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * @param x the new location of the paddle
     */
    public void setRectangleLocation(int x) {
        Rectangle rect = this.getCollisionRectangle();
        this.rectangle = new Rectangle(new Point(x, rect.getUpperLeft().getY()), rect.getWidth(), rect.getHeight());
    }

    /**
     * Move the paddle one step to the left if it can.
     */
    public void moveLeft() {
        // Move the paddle one step or to the end of the board
        if (getCollisionRectangle().getStartX() - getMove() >= getLeftEdge()) {
            setRectangleLocation((int) getCollisionRectangle().getUpperLeft().getX() - getMove());
        } else {
            setRectangleLocation(getLeftEdge());
        }
    }

    /**
     * Move the paddle one step to the right if it can.
     */
    public void moveRight() {
        Rectangle rect = getCollisionRectangle();
        // Move the paddle one step or to the end of the board
        if (rect.getWidth() + rect.getStartX() + getMove() <= this.getRightEdge()) {
            setRectangleLocation((int) getCollisionRectangle().getUpperLeft().getX() + getMove());
        } else {
            this.setRectangleLocation(getRightEdge() - (int) rect.getWidth());
        }
    }

    /**
     * Everytime the time is over we check if the user pressed to move.
     */
    public void timePassed() {
        // In case the user pressed on the left key
        if (this.getKeyboard().isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }

        // In case the user pressed on the right key
        if (this.getKeyboard().isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        getCollisionRectangle().drawFramedRectangle(d, Color.BLACK, getColor());
    }

    /**
     * The behavior of the ball's bounce depends on where it hits the paddle.
     *
     * @param collisionPoint is where the ball has collided.
     * @return the region number the ball has collided.
     */
    public int regionNumber(Point collisionPoint) {
        Line top = this.getCollisionRectangle().getTop();

        // Find the left point of the top line of the paddle
        Point left = top.start();
        if (left.getX() > top.end().getX()) {
            left = top.end();
        }

        // For every region of the paddle check if the collision point was there
        for (int i = 1; i <= REGIONS; i++) {
            Point right = new Point(left.getX() + (top.length() / REGIONS), top.start().getY());
            if (collisionPoint.isContained(new Line(left, right))) {
                return i;
            }
            left = right;
        }
        return 0;
    }

    /**
     * If the ball hits on the paddle it changes his velocity according to the collision point.
     *
     * @param hitter          is the ball that hit the Collidable.
     * @param collisionPoint  is the point where the ball collides
     * @param currentVelocity is the velocity the ball has before the collision
     * @return the new velocity of the ball after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Check the ball collided in the top line of the paddle.
        if (collisionPoint.isContained(this.getCollisionRectangle().getTop())) {
            int regionNumber = this.regionNumber(collisionPoint); // Find the region number the collision occurred.

            // Otherwise, change the velocity according to the region of the collision
            return Velocity.fromAngleAndSpeed((DEGREES_LEFT + (regionNumber - 1) * DEGREES_BETWEEN_REGIONS)
                    % DEGREES, currentVelocity.getSpeed());
        }
        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
    }

    /**
     * @param g is the game we add there the paddle.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
