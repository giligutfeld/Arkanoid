package arkanoid.levels;

import arkanoid.Constants;
import arkanoid.gui.Rectangle;
import arkanoid.gui.background.BasicBackground;
import arkanoid.gui.background.backgrounds.StreetBackground;
import arkanoid.sprites.Sprite;
import arkanoid.sprites.collidables.Block;
import arkanoid.gui.Point;
import arkanoid.gui.ball.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - Green3
 * Green3 implements LevelInformation and has not full rows of blocks.
 */
public class Green3 implements LevelInformation {

    // The space we take from the top and the name of the level
    private static final int SPACE = 100;
    private static final String LEVEL_NAME = "Green 3";
    private static final Color BACKGROUND_COLOR = new Color(16, 168, 16);

    // Balls: The speed, the number of the balls and the range of the start velocity of it.
    private static final int SPEED = 6;
    private static final int NUMBER_OF_BALLS = 2;
    private static final int RANGE = 60;

    // Paddles: The width of the paddle and its size of every step.
    private static final int WIDTH_PADDLE = 180;
    private static final int MOVE = 8;

    // Blocks: The width the height of the blocks, the number of them in every row, column and the change between rows.
    private static final int WIDTH_BLOCK = 48;
    private static final int HEIGHT_BLOCK = 20;
    private static final int NUMBER_IN_ROW = 12;
    private static final int NUMBER_IN_COLUMN = 6;
    private static final int DECREASE_BETWEEN_ROWS = 1;

    // Colors: The range of colors and the the color number of the background.
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
    public Green3(int scoreBlock) {
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
        return new StreetBackground(new BasicBackground(BACKGROUND_COLOR, new Rectangle(new Point(0,
                Constants.BORDERS_WIDTH + Constants.TEXT_HEIGHT), Constants.WIDTH,
                Constants.HEIGHT - Constants.BORDERS_WIDTH)));
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();

        // create a random-number generator for random colors.
        Random r = new Random();

        // Draw in a loop blocks in columns.
        for (int i = 0; i < NUMBER_IN_COLUMN; i++) {
            // Get a random color for every row of blocks
            Color color = new Color(r.nextInt(COLOR_NUMBER), r.nextInt(COLOR_NUMBER), r.nextInt(COLOR_NUMBER));

            // Draw in a loop blocks in rows
            for (int j = 0; j < NUMBER_IN_ROW - DECREASE_BETWEEN_ROWS * i; j++) {
                Block b = new Block(new Point(Constants.WIDTH - Constants.BORDERS_WIDTH - (j + 1) * WIDTH_BLOCK,
                        i * HEIGHT_BLOCK + Constants.BORDERS_WIDTH + SPACE), WIDTH_BLOCK, HEIGHT_BLOCK,
                        color, Color.BLACK);
                list.add(b);
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_IN_COLUMN * NUMBER_IN_ROW - DECREASE_BETWEEN_ROWS
                * ((NUMBER_IN_COLUMN - 1) * NUMBER_IN_COLUMN / 2);
    }

    @Override
    public int getScorePerBlock() {
        return this.scoreBlock;
    }
}
