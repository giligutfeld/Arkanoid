package arkanoid.sprites.collidables;

import arkanoid.Animations.GameLevel;
import arkanoid.sprites.Sprite;
import arkanoid.observer.HitListener;
import arkanoid.observer.HitNotifier;
import arkanoid.gui.Point;
import arkanoid.gui.Rectangle;
import arkanoid.sprites.Ball;
import arkanoid.gui.ball.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - Block
 * Blocks (actually, a rectangle) have size (width and height), color and location (a Point). Blocks implements
 * Collidable and Sprite and also know how to draw themselves on a DrawSurface and add themselves to game.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    // Every block has rectangle, color, frame color to draw it and a list of listeners
    private Rectangle rectangle;
    private Color color;
    private Color frameColor;
    private List<HitListener> hitListeners;
    private int countHits;

    /**
     * Constructor.
     *
     * @param upperLeft  is the location of the block, where it starts.
     * @param width      is the width of the block
     * @param height     is the height of the block
     * @param color      is the color of the block
     * @param frameColor is the color of the frame around the block
     */
    public Block(Point upperLeft, double width, double height, Color color, Color frameColor) {
        this(new Rectangle(upperLeft, width, height), color, frameColor, 1);
    }

    /**
     * Constructor.
     *
     * @param rect       is the rectangle of the block.
     * @param color      is the color of the block
     * @param frameColor is the color of the frame around the block
     * @param countHits  is the count of times the player have to hit the block to remove it.
     */
    public Block(Rectangle rect, Color color, Color frameColor, int countHits) {
        this.rectangle = rect;
        this.color = color;
        this.frameColor = frameColor;
        this.hitListeners = new ArrayList<>();
        this.countHits = countHits;
    }

    /**
     * Constructor.
     *
     * @param upperLeft  is the location of the block, where it starts.
     * @param width      is the width of the block
     * @param height     is the height of the block
     * @param color      is the color of the block
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        // We create a block without a frame (actually the frame in the same color of the block)
        this(new Rectangle(upperLeft, width, height), color, color, 1);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * @return the color of the block
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return the count of times the player have to hit the block to remove it.
     */
    public int getCountHits() {
        return this.countHits;
    }

    /**
     * @param num is the new number of times the player have to hit the block.
     */
    public void setCountHits(int num) {
        this.countHits = num;
    }

    /**
     * @return the color of the frame of the block
     */
    public Color getFrameColor() {
        return this.frameColor;
    }

    /**
     * @return the list of the listeners of the block.
     */
    public List<HitListener> getHitListeners() {
        return this.hitListeners;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);

        // Check if the object hit the corner of the block
        if (collisionPoint.isCorner(this.getCollisionRectangle())) {
            return new Velocity(-1 * currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }

        // Check if the object hit the top or bottom of the block and we need to change its dy
        if ((collisionPoint.isContained(this.getCollisionRectangle().getBottom()))
                || (collisionPoint.isContained(this.getCollisionRectangle().getTop()))) {
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }

        // Check if the object hit the right or left of the block and we need to change its dy
        if ((collisionPoint.isContained(this.getCollisionRectangle().getRight()))
                || (collisionPoint.isContained(this.getCollisionRectangle().getLeft()))) {
            return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
        }

        // Otherwise, the collision point is wrong and we dont change the velocity
        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        getCollisionRectangle().drawFramedRectangle(d, getFrameColor(), getColor());
    }

    /**
     * @param c is the color of the block.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * @param c is the color of the frame of the block.
     */
    public void setFrameColor(Color c) {
        this.frameColor = c;
    }

    /**
     * @param game is the game where we move from the block.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * @param hitter is the ball that hit the block
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.getHitListeners());

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.getHitListeners().add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.getHitListeners().remove(hl);
    }
}
