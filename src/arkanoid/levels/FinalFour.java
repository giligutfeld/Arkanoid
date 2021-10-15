package arkanoid.levels;

import arkanoid.Constants;
import arkanoid.gui.Point;
import arkanoid.gui.Rectangle;
import arkanoid.gui.background.BasicBackground;
import arkanoid.gui.background.backgrounds.CloudsBackground;
import arkanoid.sprites.Sprite;
import arkanoid.sprites.collidables.Block;
import arkanoid.gui.ball.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - FinalFour
 * FinalFour implements LevelInformation and has full rows of blocks.
 */
public class FinalFour implements LevelInformation {

    // The space we take from the top and the name of the level.
    private static final int SPACE = 100;
    private static final String LEVEL_NAME = "Final Four";
    private static final Color BACKGROUND_COLOR = new Color(51, 153, 255);

    // Balls: The speed, the number of the balls and the range of the start velocity of it.
    private static final int SPEED = 7;
    private static final int NUMBER_OF_BALLS = 3;
    private static final int RANGE = 60;

    // Paddles: The width of the paddle and its size of every step.
    private static final int WIDTH_PADDLE = 120;
    private static final int MOVE = 10;

    // Blocks: The height of the blocks and the number of them in every row and column.
    private static final int HEIGHT_BLOCK = 25;
    private static final int NUMBER_IN_ROW = 15;
    private static final int NUMBER_IN_COLUMN = 6;

    // Colors: The range of colors.
    private static final int COLOR_NUMBER = 256;

    private int width;
    private int height;
    private int borders;
    private int textHeight;
    private int textSpace;
    private int scoreBlock;

    /**
     * Constructor.
     *
     * @param scoreBlock is the score every block is worth.
     */
    public FinalFour(int scoreBlock) {
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
            list.add(Velocity.randomVelocity(0, RANGE, SPEED));
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
        return new CloudsBackground(new BasicBackground(BACKGROUND_COLOR, new Rectangle(new Point(0,
                Constants.BORDERS_WIDTH + Constants.TEXT_HEIGHT), Constants.WIDTH,
                Constants.HEIGHT - Constants.BORDERS_WIDTH)));
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();

        // create a random-number generator for random colors.
        Random r = new Random();

        int widthBlock = (Constants.WIDTH - 2 * Constants.BORDERS_WIDTH) / NUMBER_IN_ROW;

        // Draw in a loop blocks in columns.
        for (int i = 0; i < NUMBER_IN_COLUMN; i++) {
            // Get a random color for every row of blocks
            Color color = new Color(r.nextInt(COLOR_NUMBER), r.nextInt(COLOR_NUMBER), r.nextInt(COLOR_NUMBER));

            // Draw in a loop blocks in rows
            for (int j = 0; j < NUMBER_IN_ROW; j++) {
                Block b = new Block(new Point(Constants.WIDTH - Constants.BORDERS_WIDTH - (j + 1) * widthBlock,
                        i * HEIGHT_BLOCK + Constants.BORDERS_WIDTH + SPACE), widthBlock, HEIGHT_BLOCK, color,
                        Color.BLACK);
                list.add(b);
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_IN_COLUMN * NUMBER_IN_ROW;
    }

    @Override
    public int getScorePerBlock() {
        return this.scoreBlock;
    }
}
