package arkanoid.Animations.screens;

import arkanoid.Animations.Animation;
import arkanoid.gui.Rectangle;
import arkanoid.gui.background.BasicBackground;
import arkanoid.gui.background.backgrounds.SadBackground;
import arkanoid.sprites.Sprite;
import arkanoid.observer.Counter;
import arkanoid.gui.Point;
import biuoop.DrawSurface;

import java.awt.Color;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - GameOver
 * GameOver displays a message with the score when the game ended with the player dying.
 */
public class GameOver implements Animation {

    // The size of the font of the text, the start location of the text and the text we show.
    private static final int X_TEXT = 190;
    private static final int Y_TEXT = 470;
    private static final int SHADOW = 2;
    public static final int FONT_SIZE = 32;
    public static final String MESSAGE = "Game Over. Your score is ";
    private static final Color BACKGROUND_COLOR = new Color(36, 163, 136);
    public static final Point EMOJI_CENTER = new Point(400, 300);

    private boolean stop;
    private Counter score;
    private Rectangle rect;

    /**
     * Constructor.
     *
     * @param score is the final score of the player.
     * @param rect is the rectangle of the screen.
     */
    public GameOver(Counter score, Rectangle rect) {
        this.stop = false;
        this.score = score;
        this.rect = rect;
    }

    /**
     * @return the background of the screen.
     */
    public Sprite getBackground() {
        return new SadBackground(new BasicBackground(BACKGROUND_COLOR, this.rect), EMOJI_CENTER);
    }

    /**
     * @return the score of the player in the game.
     */
    public Counter getScore() {
        return this.score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        getBackground().drawOn(d);
        d.setColor(Color.BLACK);
        d.drawText(X_TEXT - SHADOW, Y_TEXT - SHADOW, MESSAGE + getScore().getValue(), FONT_SIZE);
        d.setColor(Color.RED);
        d.drawText(X_TEXT, Y_TEXT, MESSAGE + getScore().getValue(), FONT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}