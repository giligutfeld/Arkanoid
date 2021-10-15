package arkanoid.levels;

import arkanoid.sprites.Sprite;
import arkanoid.sprites.collidables.Block;
import arkanoid.gui.ball.Velocity;

import java.util.List;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Interface name - LevelInformation
 * The LevelInformation interface specifies the information required to fully describe a level.
 */
public interface LevelInformation {

    /**
     * @return the number of the balls in the level.
     */
    int numberOfBalls();

    /**
     * @return the initial velocities of the balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * @return the name of the level.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * @return a a list of the blocks that make up this level.
     */
    List<Block> blocks();

    /**
     * @return the number of blocks that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();

    /**
     * @return the score per block.
     */
    int getScorePerBlock();
}
