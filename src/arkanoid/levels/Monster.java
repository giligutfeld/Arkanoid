package arkanoid.levels;

import arkanoid.Constants;
import arkanoid.gui.Rectangle;
import arkanoid.gui.background.BasicBackground;
import arkanoid.gui.background.backgrounds.ShadowBackground;
import arkanoid.gui.background.backgrounds.SpaceBackground;
import arkanoid.sprites.Sprite;
import arkanoid.sprites.collidables.Block;
import arkanoid.gui.Point;
import arkanoid.gui.ball.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - Monster
 * Monster implements LevelInformation and draws a monster.
 */
public class Monster implements LevelInformation {

    // The space we take from the top and the name of the level
    private static final String LEVEL_NAME = "The monster";
    private static final Color BACKGROUND_COLOR = new Color(5, 2, 52);
    private static final Color MONSTER_COLOR = new Color(66, 64, 64);

    // Balls: The speed, the number of the balls and the range of the start velocity of it.
    private static final int SPEED = 6;
    private static final int NUMBER_OF_BALLS = 3;
    private static final int RANGE = 60;

    // Paddles: The width of the paddle and its size of every step.
    private static final int WIDTH_PADDLE = 180;
    private static final int MOVE = 8;

    // Blocks: The width the height of the blocks, the number of them in every row, column and the change between rows.
    private static final int WIDTH_BLOCK = 48;
    private static final int HEIGHT_BLOCK = 20;
    private static final int[][] YELLOWS = {{0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0}};
    private static final int[][] GRAYS = {{0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0}, {0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0}, {0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0}};
    private static final int NUMBER_OF_BLOCKS = 85;

    private static final int LEFT = 100;
    private static final int TOP = 100;
    private static final int STARS_COUNT = 200;

    private int scoreBlock;

    /**
     * Constructor.
     *
     * @param scoreBlock is the score every block is worth.
     */
    public Monster(int scoreBlock) {
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
        return new ShadowBackground(new SpaceBackground(new BasicBackground(BACKGROUND_COLOR,
                new Rectangle(new Point(0, Constants.BORDERS_WIDTH + Constants.TEXT_HEIGHT), Constants.WIDTH,
                        Constants.HEIGHT - Constants.BORDERS_WIDTH)), STARS_COUNT), blocks(),
                BACKGROUND_COLOR.brighter());
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();

        int space = TOP;
        for (int i = 0; i < YELLOWS.length; i++) {
            addLine(list, YELLOWS[i], space, Color.YELLOW);
            space += HEIGHT_BLOCK;
        }
        for (int i = 0; i < GRAYS.length; i++) {
            addLine(list, GRAYS[i], space, MONSTER_COLOR);
            space += HEIGHT_BLOCK;
        }
        addEyes(list);

        return list;
    }

    /**
     * @param list   is the list of blocks.
     * @param line   is a line of blocks.
     * @param space is the height of the line.
     * @param color  is the color of the blocks in the line
     */
    public void addLine(List<Block> list, int[] line, int space, Color color) {
        for (int i = 0; i < line.length; i++) {
            if (line[i] == 1) {
                Block b = new Block(new Rectangle(new Point(LEFT + (i + 1) * WIDTH_BLOCK, space),
                        WIDTH_BLOCK, HEIGHT_BLOCK), color, Color.BLACK, 2);
                list.add(b);
            }
        }
    }

    /**
     * @param list is the list of blocks.
     */
    public void addEyes(List<Block> list) {
        Block b = new Block(new Point(LEFT + WIDTH_BLOCK * 4, TOP + HEIGHT_BLOCK * 5), WIDTH_BLOCK,
                HEIGHT_BLOCK, Color.RED, Color.BLACK);
        list.add(b);
        b = new Block(new Point(LEFT + WIDTH_BLOCK * 4, TOP + HEIGHT_BLOCK * 6), WIDTH_BLOCK,
                HEIGHT_BLOCK, Color.RED, Color.BLACK);
        list.add(b);
        b = new Block(new Point(LEFT + WIDTH_BLOCK * 8, TOP + HEIGHT_BLOCK * 5), WIDTH_BLOCK,
                HEIGHT_BLOCK, Color.RED, Color.BLACK);
        list.add(b);
        b = new Block(new Point(LEFT + WIDTH_BLOCK * 8, TOP + HEIGHT_BLOCK * 6), WIDTH_BLOCK,
                HEIGHT_BLOCK, Color.RED, Color.BLACK);
        list.add(b);
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS;
    }

    @Override
    public int getScorePerBlock() {
        return this.scoreBlock;
    }
}
