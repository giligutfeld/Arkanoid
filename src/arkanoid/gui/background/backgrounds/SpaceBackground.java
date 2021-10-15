package arkanoid.gui.background.backgrounds;

import arkanoid.Constants;
import arkanoid.gui.Circle;
import arkanoid.gui.Point;
import arkanoid.gui.background.AbstractBackground;
import arkanoid.gui.background.Background;

import java.awt.Color;
import java.util.Random;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - CloudsBackground
 * The CloudsBackground draws a target on a BasicBackground.
 */
public class SpaceBackground extends AbstractBackground {

    // The radius of the biggest circle, the decrease between the every circle
    private static final int MAX_RADIUS = 9;
    private static final Color SHADOW_COLOR = new Color(23, 18, 102);

    /**
     * @param bg is the background we add the space.
     * @param count is the count of stars in the space.
     */
    public SpaceBackground(Background bg, int count) {
        super(bg);

        // create a random-number generator for random colors.
        Random r = new Random();

        for (int i = 0; i < count; i++) {
            Circle c = new Circle(new Point(r.nextInt(Constants.WIDTH), r.nextInt(Constants.HEIGHT)),
                    r.nextInt(MAX_RADIUS), SHADOW_COLOR);

            bg.addShape(c);
            bg.addShape(new Circle(new Point(c.getX(), c.getY()), c.getRadius() / 2, Color.WHITE));
        }

    }
}
