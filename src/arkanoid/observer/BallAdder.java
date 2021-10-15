package arkanoid.observer;

import arkanoid.Animations.GameLevel;
import arkanoid.sprites.collidables.Block;
import arkanoid.gui.Rectangle;
import arkanoid.sprites.Ball;
import arkanoid.gui.ball.Velocity;

import java.util.ArrayList;
import java.util.List;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - BallAdder
 * BallAdder is in charge of duplicating balls to the game. It also implements HitListener.
 */
public class BallAdder implements HitListener {

    // The range of colors and the degrees of the direction the ball can start move
    private static final int SPEED = 6;
    private static final int RANGE = 60;
    private static final int DEGREES_BETWEEN_DIRECTIONS = 90;

    private GameLevel game;
    private int amount;

    /**
     * Constructor.
     *
     * @param game   is the game it removes the balls.
     * @param amount is the number of balls we add after a hit.
     */
    public BallAdder(GameLevel game, int amount) {
        this.game = game;
        this.amount = amount;
    }

    /**
     * @return the game of the adder.
     */
    public GameLevel getGame() {
        return this.game;
    }

    /**
     * @return the amount of balls of the adder.
     */
    public int getAmount() {
        return this.amount;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        int direction = 0;
        Rectangle rect = beingHit.getCollisionRectangle();

        // Check from where the ball came to send there new balls.
        if (hitter.getX() > rect.getStartX() + rect.getWidth()) {
            direction += DEGREES_BETWEEN_DIRECTIONS;
        } else if (hitter.getY() > rect.getStartY() + rect.getHeight()) {
            direction += 2 * DEGREES_BETWEEN_DIRECTIONS;
        } else if (hitter.getX() > rect.getStartX() + rect.getWidth()) {
            direction -= DEGREES_BETWEEN_DIRECTIONS;
        }

        // Balls that are hit the "special block" should be duplicated.
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < getAmount(); i++) {
            list.add(Velocity.randomVelocity(direction, RANGE, SPEED));
        }
        getGame().addBalls(hitter.getX(), hitter.getY(), getAmount(), list);
    }
}