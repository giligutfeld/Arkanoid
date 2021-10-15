package arkanoid.sprites;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - SpriteCollection
 * SpriteCollection holds a collection of sprites and supports the addition of new sprites.
 */
public class SpriteCollection {

    private List<Sprite> list;

    /**
     * Constructor.
     *
     * Add a list of sprites
     */
    public SpriteCollection() {
        this.list = new ArrayList<>();
    }

    /**
     * @return the list of the sprites.
     */
    public List<Sprite> getSprites() {
        return this.list;
    }

    /**
     * @param s add the sprite to the list.
     */
    public void addSprite(Sprite s) {
        this.getSprites().add(s);
    }

    /**
     * @param s remove the sprite from the list.
     */
    public void removeSprite(Sprite s) {
        this.getSprites().remove(s);
    }

    /**
     * Call to timesPassed() on all the sprites in the list.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the sprites before iterating over them.
        List<Sprite> sprites = new ArrayList<>(this.getSprites());
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }

    /**
     * Call to drawOn(d) on all the sprites in the list.
     *
     * @param d is the surface where we draw the sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.getSprites()) {
            s.drawOn(d);
        }
    }
}
