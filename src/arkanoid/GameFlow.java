package arkanoid;

import arkanoid.Animations.AnimationRunner;
import arkanoid.Animations.screens.GameOver;
import arkanoid.Animations.screens.YouWin;
import arkanoid.Animations.GameLevel;
import arkanoid.Animations.KeyPressStoppableAnimation;
import arkanoid.gui.Point;
import arkanoid.gui.Rectangle;
import arkanoid.levels.LevelInformation;
import arkanoid.observer.Counter;
import biuoop.KeyboardSensor;

import java.util.List;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - GameFlow
 * The GameFlow will be in charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {

    // The count of lives the player has.
    private static final int LIVES = 3;

    private KeyboardSensor ks;
    private AnimationRunner ar;
    private Counter score;
    private Counter lives;

    /**
     * Constructor.
     *
     * @param ar is the animation we put.
     * @param ks is the keyboard in the game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.score = new Counter();
        this.lives = new Counter();
        getLives().increase(LIVES);
        this.ar = ar;
        this.ks = ks;
    }

    /**
     * @return the count of lives remained.
     */
    public Counter getLives() {
        return this.lives;
    }

    /**
     * @return the score of the player in the game.
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * @return the keyboard in the game.
     */
    public KeyboardSensor getKs() {
        return this.ks;
    }

    /**
     * @return the animation in the game.
     */
    public AnimationRunner getAr() {
        return this.ar;
    }

    /**
     * @param levels is the levels we run in the game.
     */
    public void runLevels(List<LevelInformation> levels) {

        // Every level we initialize and run it until there are no balls or no blocks.
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, getKs(), getAr(), getScore(), getLives());
            level.initialize();

            while ((level.getRemainingBlocks().getValue() > 0) && (getLives().getValue() > 0)) {
                level.run();

                // When there are no balls, the player loses live.
                if (level.getRemainingBalls().getValue() <= 0) {
                    getLives().decrease(1);
                    level.addBalls();
                    level.getPaddle().getCollisionRectangle().getUpperLeft().setX(Constants.WIDTH / 2
                            - level.getLevel().paddleWidth() / 2);
                }
            }

            // When there are no lives, the player lose and we run the GameOver screen.
            if (getLives().getValue() <= 0) {
                getAr().run(new KeyPressStoppableAnimation(getKs(), KeyboardSensor.SPACE_KEY,
                        new GameOver(getScore(), new Rectangle(new Point(0, 0), Constants.WIDTH,
                                Constants.HEIGHT))));
                getAr().getGUI().close();
            }
        }

        // When there are no levels, the player win and we run the YouWin screen.
        getAr().run(new KeyPressStoppableAnimation(getKs(), KeyboardSensor.SPACE_KEY, new YouWin(getScore(),
                new Rectangle(new Point(0, 0), Constants.WIDTH, Constants.HEIGHT))));
        getAr().getGUI().close();
    }
}
