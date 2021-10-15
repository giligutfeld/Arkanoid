package arkanoid.Animations;

import arkanoid.Constants;
import arkanoid.sprites.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.Color;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - CountdownAnimation
 * The CountdownAnimation should display the counting on top of the game screen itself, so that the player will
 * know what to expect when the game starts. The feature we will add now is an on-screen countdown from 3 to 1,
 * which will show up at the beginning of each turn. Only after the countdown reaches zero, things will start
 * moving and we will start with the game play.
 */
public class CountdownAnimation implements Animation {

    // The location and the size of the countdown.
    public static final int X_LEFT = 375;
    public static final int Y_BOTTOM = 300;
    public static final int FONT_SIZE = 60;
    public static final int SHADOW = -2;

    // Number of milliseconds in a second
    private static final int MILLISECONDS = 1000;

    private boolean stop;
    private SpriteCollection gameScreen;
    private double numOfSeconds;
    private int countFrom;
    private int milliseconds;

    /**
     * @param numOfSeconds is the number of seconds it wait until the end of the count.
     * @param countFrom    is the number of seconds it count from.
     * @param gameScreen   is the sprites we should show when we count.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.milliseconds = (int) (numOfSeconds * MILLISECONDS);
    }

    /**
     * @return the sprites in the game.
     */
    public SpriteCollection getGameScreen() {
        return this.gameScreen;
    }

    /**
     * @return the number of milliseconds that remain for the next number.
     */
    public int getMilliseconds() {
        return this.milliseconds;
    }

    /**
     * @param num is the new number of milliseconds that remain for the next number.
     */
    public void setMilliseconds(int num) {
        this.milliseconds = num;
    }

    /**
     * @return the number we count from the time.
     */
    public int getCountFrom() {
        return this.countFrom;
    }

    /**
     * @param num is the new count from.
     */
    public void setCountFrom(int num) {
        this.countFrom = num;
    }

    /**
     * @return the number of seconds between frames.
     */
    public double getNumOfSeconds() {
        return this.numOfSeconds;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        getGameScreen().drawAllOn(d);

        // Check if we need to change to the next second.
        if (getMilliseconds() < 0) {
            setCountFrom(getCountFrom() - 1);
            setMilliseconds((int) (getNumOfSeconds() * MILLISECONDS));
        }

        // Check if the seconds is over.
        if (getCountFrom() == 0) {
            this.stop = true;
            return;
        }

        // Write how much seconds left
        d.setColor(Color.GRAY);
        d.drawText(X_LEFT + SHADOW, Y_BOTTOM + SHADOW, "" + getCountFrom(), FONT_SIZE);
        d.setColor(Color.WHITE);
        d.drawText(X_LEFT, Y_BOTTOM, "" + getCountFrom(), FONT_SIZE);

        // Update the milliseconds that left for the next frame.
        setMilliseconds(getMilliseconds() - Constants.FRAMES_PER_SECOND);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}