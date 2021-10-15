package arkanoid.Animations;

import arkanoid.Animations.screens.PauseScreen;
import arkanoid.Constants;
import arkanoid.levels.LevelInformation;
import arkanoid.sprites.Sprite;
import arkanoid.sprites.collidables.Block;
import arkanoid.sprites.collidables.Collidable;
import arkanoid.sprites.collidables.GameEnvironment;
import arkanoid.sprites.collidables.Paddle;
import arkanoid.observer.BallRemover;
import arkanoid.observer.BlockRemover;
import arkanoid.observer.Counter;
import arkanoid.observer.ScoreTrackingListener;
import arkanoid.observer.BallAdder;
import arkanoid.sprites.SpriteCollection;
import arkanoid.gui.Point;

import arkanoid.gui.Rectangle;
import arkanoid.gui.ball.Velocity;
import arkanoid.sprites.Ball;
import arkanoid.sprites.indicators.IndicatorsLine;
import arkanoid.sprites.indicators.LiveIndicator;
import arkanoid.sprites.indicators.NameIndicator;
import arkanoid.sprites.indicators.ScoreIndicator;
import arkanoid.sprites.indicators.Indicator;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - GameLevel
 * GameLevels hold the sprites and the collidables, and will be in charge of initialize a level and run it.
 */
public class GameLevel implements Animation {

    // Balls: The radius of the balls.
    private static final int RADIUS = 7;

    // Paddle: The height of the paddle and the chances to create a special block.
    private static final int HEIGHT_PADDLE = 20;
    private static final int CHANCE_FOR_SPECIAL_BLOCK = 20;

    // The number of seconds before the game runs, from which number starts the count and the color of the line.
    private static final int NUM_OF_SECONDS = 2;
    private static final int COUNT_FROM = 3;
    public static final Color LINE_COLOR = new Color(203, 238, 181);


    // The game has a gui board, sprites, remaining blocks and environment of collidable objects.
    private KeyboardSensor keyboard;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation level;
    private Paddle paddle;

    /**
     * Constructor.
     *
     * @param level  is the level we run.
     * @param ks     is the keyboard in the game.
     * @param runner is the runner of the animation in the game.
     * @param score  is the score of the player.
     * @param lives  is the count of lives the player has.
     */
    public GameLevel(LevelInformation level, KeyboardSensor ks, AnimationRunner runner, Counter score, Counter lives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.lives = lives;
        this.score = score;
        this.runner = runner;
        this.keyboard = ks;
        this.level = level;
    }

    /**
     * @return the sprites in the game.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * @return the environment in the game.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * @return the count of the remaining blocks in the game.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * @return the count of the remaining balls in the game.
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * @return the score of the player in the game.
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * @return the count of lives remained.
     */
    public Counter getLives() {
        return this.lives;
    }

    /**
     * @return the level information of the game.
     */
    public LevelInformation getLevel() {
        return this.level;
    }

    /**
     * @return the GUI in the game.
     */
    public AnimationRunner getRunner() {
        return this.runner;
    }

    /**
     * @return the keyboard in the game.
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * @param c is the new collidable object we add to the environment.
     */
    public void addCollidable(Collidable c) {
        this.getEnvironment().addCollidable(c);
    }

    /**
     * @param s is the new sprite object we add to the environment.
     */
    public void addSprite(Sprite s) {
        this.getSprites().addSprite(s);
    }

    /**
     * Add blocks in the edges of the gui.
     */
    public void addFrame() {
        LevelInformation l = getLevel();

        // Create a listener that in charge of removing the balls that hit the "death region".
        BallRemover ballRemover = new BallRemover(this, getRemainingBalls());

        // Add four blocks in the edges of the gui according to the width and its height.
        new Block(new Point(0, Constants.TEXT_HEIGHT), Constants.WIDTH, Constants.BORDERS_WIDTH,
                Color.GRAY).addToGame(this);
        new Block(new Point(Constants.WIDTH - Constants.BORDERS_WIDTH, Constants.TEXT_HEIGHT),
                Constants.BORDERS_WIDTH, Constants.HEIGHT - Constants.TEXT_HEIGHT, Color.GRAY).addToGame(this);
        new Block(new Point(0, Constants.TEXT_HEIGHT), Constants.BORDERS_WIDTH,
                Constants.HEIGHT - Constants.TEXT_HEIGHT, Color.GRAY).addToGame(this);

        // The bottom block is a "death region" which kills the balls when hit it so add it to the hit listeners.
        Block deathRegion = new Block(new Point(0, Constants.HEIGHT), Constants.WIDTH, Constants.BORDERS_WIDTH,
                Color.GRAY);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);
    }

    /**
     * Add blocks to the game.
     */
    public void addBlocks() {
        // create a random-number generator for random colors.
        Random r = new Random();

        // Create listeners that in charge of removing the blocks and can tracking the score.
        BlockRemover blocksRemover = new BlockRemover(this, getRemainingBlocks());
        ScoreTrackingListener scoreTracking = new ScoreTrackingListener(getScore(), getLevel().getScorePerBlock());
        BallAdder ballAdder = new BallAdder(this, 1);

        for (Block b : getLevel().blocks()) {
            b.addToGame(this);
            b.addHitListener(blocksRemover);
            b.addHitListener(scoreTracking);

            // Create black special blocks that duplicates the balls.
            if (r.nextInt(CHANCE_FOR_SPECIAL_BLOCK) == 0) {
                b.addHitListener(ballAdder);
                b.setFrameColor(Color.GREEN);
            }
        }

        // Add the number of blocks to remove of the level.
        getRemainingBlocks().increase(getLevel().numberOfBlocksToRemove());
    }

    /**
     * Add balls to the game.
     */
    public void addBalls() {
        addBalls(Constants.WIDTH / 2, Constants.HEIGHT - Constants.BORDERS_WIDTH - HEIGHT_PADDLE,
                getLevel().numberOfBalls(), getLevel().initialBallVelocities());
    }

    /**
     * @param x          coordinate of the start location of the balls.
     * @param y          coordinate of the start location of the balls.
     * @param amount     is how much balls to add to the game.
     * @param velocities is all the velocities of the balls.
     */
    public void addBalls(int x, int y, int amount, List<Velocity> velocities) {
        // Add balls to the game
        for (int i = 0; i < amount; i++) {
            Ball b = new Ball(new Point(x, y), RADIUS, Color.WHITE);
            b.setVelocity(velocities.get(i));
            b.setGameEnvironment(getEnvironment());
            b.addToGame(this);
        }

        // Update the amount of the balls in the game.
        this.getRemainingBalls().increase(amount);
    }

    /**
     * Add a paddle to the game.
     */
    public void addPaddle() {
        this.paddle = new Paddle(new Rectangle(new Point(Constants.WIDTH / 2 - getLevel().paddleWidth() / 2,
                Constants.HEIGHT - HEIGHT_PADDLE), getLevel().paddleWidth(), HEIGHT_PADDLE), getKeyboard(),
                Color.ORANGE, Constants.BORDERS_WIDTH, Constants.WIDTH - Constants.BORDERS_WIDTH,
                getLevel().paddleSpeed());
        getPaddle().addToGame(this);
    }

    /**
     * @return the paddle in the game.
     */
    public Paddle getPaddle() {
        return this.paddle;
    }

    /**
     * Add the line indicators to the game.
     */
    public void addLineIndicators() {
        // Create a live indicator and add it to the game.
        List<Indicator> list = new ArrayList<>();
        list.add(new LiveIndicator(getLives()));
        list.add(new ScoreIndicator(getScore()));
        list.add(new NameIndicator(getLevel().levelName()));
        new IndicatorsLine(list, new Rectangle(new Point(0, 0), Constants.WIDTH,
                Constants.TEXT_HEIGHT, LINE_COLOR)).addToGame(this);
    }

    /**
     * @param bg is the background we add to the level.
     */
    public void addBackground(Sprite bg) {
        if (bg != null) {
            bg.addToGame(this);
        }
    }

    /**
     * Initialize a new game: Add background, Blocks, Balls, Paddle, Frame, LiveIndicator, ScoreIndicator to the game.
     */
    public void initialize() {
        addBackground(getLevel().getBackground());
        addFrame();
        addBlocks();
        addBalls();
        addPaddle();
        addLineIndicators();
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        // Countdown before turn starts and run the game
        getRunner().run(new CountdownAnimation(NUM_OF_SECONDS, COUNT_FROM, getSprites()));
        this.running = true;
        getRunner().run(this);
    }

    /**
     * @param c is the Collidable we remove from the game.
     */
    public void removeCollidable(Collidable c) {
        this.getEnvironment().removeCollidable(c);
    }

    /**
     * @param s is the Sprite we remove from the game.
     */
    public void removeSprite(Sprite s) {
        this.getSprites().removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // Draw on the surface all the sprites and notify them the time passed
        getSprites().drawAllOn(d);
        getSprites().notifyAllTimePassed();
        d.setColor(Color.BLACK);

        if (getKeyboard().isPressed("p")) {
            getRunner().run(new KeyPressStoppableAnimation(getKeyboard(), KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
        }

        // stopping condition
        if (this.getRemainingBalls().getValue() == 0) {
            this.running = false;
        }
        if (this.getRemainingBlocks().getValue() == 0) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
