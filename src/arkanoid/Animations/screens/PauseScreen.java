package arkanoid.Animations.screens;

import arkanoid.Animations.Animation;
import arkanoid.Constants;
import arkanoid.gui.Point;
import arkanoid.gui.Rectangle;
import arkanoid.gui.background.BasicBackground;
import arkanoid.gui.background.backgrounds.PauseBackground;
import arkanoid.sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - PauseScreen
 * PauseScreen allows the option to pause the game when pressing a key that will display a screen with a
 * message until another key is pressed.
 */
public class PauseScreen implements Animation {

    // The size of the font of the text, the start location of the text and the text we show.
    private static final int X_TEXT = 230;
    private static final int Y_TEXT = 470;
    private static final int SHADOW = 2;
    public static final int FONT_SIZE = 32;
    private static final Color BACKGROUND_COLOR = new Color(178, 212, 208);
    public static final String MESSAGE = "Press space to continue";

    private boolean stop;

    /**
     * Constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * @return the background of the screen.
     */
    public Sprite getBackground() {
        return new PauseBackground(new BasicBackground(BACKGROUND_COLOR, new Rectangle(new Point(0, 0),
                Constants.WIDTH, Constants.HEIGHT)));
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        getBackground().drawOn(d);
        d.setColor(Color.BLACK);
        d.drawText(X_TEXT - SHADOW, Y_TEXT - SHADOW, MESSAGE, FONT_SIZE);
        d.setColor(Color.ORANGE);
        d.drawText(X_TEXT, Y_TEXT, MESSAGE, FONT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
