package arkanoid.sprites;

import arkanoid.Animations.GameLevel;
import biuoop.DrawSurface;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Interface name - Sprite
 * The Sprite interface is a game object that can be drawn to the screen (and which is not just a background image)
 * and can be notified that time has passed
 */
public interface Sprite {
    /**
     * @param d draw the sprite to there.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * @param g add the sprite to there.
     */
    void addToGame(GameLevel g);
}
