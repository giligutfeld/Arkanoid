package arkanoid.gui.background.backgrounds;

import arkanoid.gui.Point;
import arkanoid.gui.Rectangle;
import arkanoid.gui.background.AbstractBackground;
import arkanoid.gui.background.Background;
import arkanoid.sprites.collidables.Block;

import java.awt.Color;
import java.util.List;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - ShadowBackground
 * The ShadowBackground draws shadow behind the blocks in the level.
 */
public class ShadowBackground extends AbstractBackground {

    /**
     * @param bg    is the background we add the clouds.
     * @param list  is the blocks we put in the shadow.
     * @param color is the color of the shadow.
     */
    public ShadowBackground(Background bg, List<Block> list, Color color) {
        super(bg);

        for (Block b : list) {
            Rectangle rect = b.getCollisionRectangle();
            rect.setColor(b.getColor().brighter().brighter());
            bg.addShape(rect);
            rect = new Rectangle(new Point(rect.getStartX() + 1, rect.getStartY() + 1),
                    rect.getWidth() - 2, rect.getHeight() - 2);
            rect.setColor(color);
            bg.addShape(rect);
        }
    }
}
