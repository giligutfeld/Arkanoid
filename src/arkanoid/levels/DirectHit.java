package arkanoid.levels;

import arkanoid.Constants;
import arkanoid.gui.Point;
import arkanoid.gui.Rectangle;
import arkanoid.gui.background.BasicBackground;
import arkanoid.gui.background.backgrounds.TargetBackground;
import arkanoid.sprites.Sprite;
import arkanoid.sprites.collidables.Block;
import arkanoid.gui.ball.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - DirectHit
 * DirectHit implements LevelInformation and has one block to hit precisely.
 */
public class DirectHit implements LevelInformation {

    // The space we take from the top and the name of the level.
    private static final int SPACE = 145;
    private static final String LEVEL_NAME = "Direct Hit";

    // Balls: The speed, the number of the balls and the range and direction of the start velocity of it.
    private static final int SPEED = 8;
    private static final int NUMBER_OF_BALLS = 1;

    // Paddles: The width of the paddle and its size of every step.
    private static final int WIDTH_PADDLE = 80;
    private static final int MOVE = 12;

    // Blocks: The width the height of the blocks.
    private static final int WIDTH_BLOCK = 31;
    private static final int HEIGHT_BLOCK = 31;

    private int scoreBlock;

    /**
     * Constructor.
     *
     * @param scoreBlock is the score every block is worth.
     */
    public DirectHit(int scoreBlock) {
        this.scoreBlock = scoreBlock;
    }

    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            list.add(Velocity.fromAngleAndSpeed(0, SPEED));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return MOVE;
    }

    @Override
    public int paddleWidth() {
        return WIDTH_PADDLE;
    }

    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    @Override
    public Sprite getBackground() {
        return new TargetBackground(new BasicBackground(Color.BLACK, new Rectangle(new Point(0,
                Constants.BORDERS_WIDTH + Constants.TEXT_HEIGHT), Constants.WIDTH, Constants.HEIGHT
                - Constants.BORDERS_WIDTH)), new Point(Constants.WIDTH / 2, SPACE + HEIGHT_BLOCK / 2),
                WIDTH_BLOCK / 2 + 5);
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        Block b = new Block(new Point(Constants.WIDTH / 2 - WIDTH_BLOCK / 2, SPACE), WIDTH_BLOCK,
                HEIGHT_BLOCK, Color.RED, Color.RED);
        list.add(b);
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public int getScorePerBlock() {
        return this.scoreBlock;
    }
}
