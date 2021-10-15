package arkanoid.levels;

import arkanoid.Constants;
import arkanoid.gui.Point;
import arkanoid.gui.Rectangle;
import arkanoid.gui.background.BasicBackground;
import arkanoid.gui.background.backgrounds.SunBackground;
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
 * Class name - WideEasy
 * WideEasy implements LevelInformation and has one row of blocks and a huge paddle (easy level).
 */
public class WideEasy implements LevelInformation {

    // The space we take from the top, the center of the sun and the name of the level.
    private static final int SPACE = 250;
    private static final Point SUN_CENTER = new Point(150, 150);
    private static final String LEVEL_NAME = "Wide Easy";

    // Balls: The speed, the number of the balls and the range of the start velocity of it.
    private static final int SPEED = 8;
    private static final int NUMBER_OF_BALLS = 10;
    private static final int RANGE = 60;

    // Paddles: The width of the paddle and its size of every step.
    private static final int WIDTH_PADDLE = 600;
    private static final int MOVE = 2;

    // Blocks: The height of the blocks and the number of them in every row.
    private static final int HEIGHT_BLOCK = 25;
    private static final int NUMBER_IN_ROW = 15;

    // Colors: The range of colors.
    private static final int COLOR_NUMBER = 256;

    private int scoreBlock;

    /**
     * Constructor.
     *
     * @param scoreBlock is the score every block is worth.
     */
    public WideEasy(int scoreBlock) {
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
        return new SunBackground(new BasicBackground(Color.WHITE,
                new Rectangle(new Point(0, Constants.BORDERS_WIDTH + Constants.TEXT_HEIGHT), Constants.WIDTH,
                        Constants.HEIGHT - Constants.BORDERS_WIDTH)), SUN_CENTER,
                SPACE + Constants.BORDERS_WIDTH);
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();

        // create a random-number generator for random colors.
        Random r = new Random();

        int widthBlock = (Constants.WIDTH - 2 * Constants.BORDERS_WIDTH) / NUMBER_IN_ROW;
        Color color = null;
        int counter = 0;

        // Draw in a loop blocks in rows
        for (int j = 0; j < NUMBER_IN_ROW; j++) {
            // Get a random color for every row of blocks
            if (counter == NUMBER_IN_ROW / 2 + 1) {
                counter++;
            }
            if (counter % 2 == 0) {
                color = new Color(r.nextInt(COLOR_NUMBER), r.nextInt(COLOR_NUMBER), r.nextInt(COLOR_NUMBER));
            }
            Block b = new Block(new Point(Constants.WIDTH - Constants.BORDERS_WIDTH - (j + 1) * widthBlock,
                    Constants.BORDERS_WIDTH + SPACE), widthBlock, HEIGHT_BLOCK, color, Color.BLACK);
            list.add(b);
            counter++;
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_IN_ROW;
    }

    @Override
    public int getScorePerBlock() {
        return this.scoreBlock;
    }
}
