package arkanoid.observer;

import arkanoid.Animations.GameLevel;
import arkanoid.sprites.collidables.Block;
import arkanoid.sprites.Ball;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - BallRemover
 * BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain. It also implements HitListener.
 */
public class BlockRemover implements HitListener {

    // A bonus score for finish a level
    private static final int FINISH_LEVEL_SCORE = 100;

    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game          is the game it removes the blocks.
     * @param removedBlocks is the counter of the number of the blocks.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * @return the game of the remover.
     */
    public GameLevel getGame() {
        return this.game;
    }

    /**
     * @return the count of the remaining blocks of the remover.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Blocks that are hit should be removed from the game.
        if (beingHit.getCountHits() == 1) {
            beingHit.removeFromGame(getGame());
            beingHit.removeHitListener(this);
            // Update the counter of the blocks and check that there are more blocks.
            getRemainingBlocks().decrease(1);
            if (getRemainingBlocks().getValue() == 0) {
                getGame().getScore().increase(FINISH_LEVEL_SCORE);
            }
        } else {
            // Update the number of hits that remain and make the block brighter.
            beingHit.setCountHits(beingHit.getCountHits() - 1);
            beingHit.setColor(beingHit.getColor().brighter());
        }

    }
}