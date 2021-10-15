package arkanoid.observer;

import arkanoid.Animations.GameLevel;
import arkanoid.sprites.collidables.Block;
import arkanoid.sprites.Ball;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - BallRemover
 * BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain. It also implements HitListener.
 */
public class BallRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param game is the game it removes the balls.
     * @param remainingBalls is the counter of the number of the balls.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * @return the game of the remover.
     */
    public GameLevel getGame() {
        return this.game;
    }

    /**
     * @return the count of the remaining balls of the remover.
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Balls that are hit the "death region" should be removed from the game.
        hitter.removeFromGame(this.getGame());

        // Update the counter of the balls and check that there are more balls.
        this.getRemainingBalls().decrease(1);
    }
}