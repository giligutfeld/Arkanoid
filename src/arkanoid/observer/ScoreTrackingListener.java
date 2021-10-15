package arkanoid.observer;

import arkanoid.sprites.collidables.Block;
import arkanoid.sprites.Ball;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - ScoreTrackingListener
 * ScoreTrackingListener is in charge to update the counter of the score when blocks are being hit and removed.
 * the player should receive some points whenever the ball hits a block. We will implement the following scoring rule:
 * hitting a block is worth 5 points. Clearning an entire level (destroying all blocks) is worth another 100 points.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;
    private int blockScore;

    /**
     * Constructor.
     *
     * @param scoreCounter is counter of the score.
     * @param blockScore   is the points every block is worth.
     */
    public ScoreTrackingListener(Counter scoreCounter, int blockScore) {
        this.currentScore = scoreCounter;
        this.blockScore = blockScore;
    }

    /**
     * @return the points every block is worth.
     */
    public int getBlockScore() {
        return this.blockScore;
    }

    /**
     * @param num is the new number of points every block is worth.
     */
    public void setBlockScore(int num) {
        this.blockScore = num;
    }

    /**
     * @return the score of the score tracking.
     */
    public Counter getScore() {
        return this.currentScore;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        getScore().increase(getBlockScore());
    }
}