package arkanoid.gui.background.backgrounds;

import arkanoid.gui.Circle;
import arkanoid.gui.Line;
import arkanoid.gui.Point;
import arkanoid.gui.background.AbstractBackground;
import arkanoid.gui.background.Background;

import java.awt.Color;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - CloudsBackground
 * The CloudsBackground draws a sun on a BasicBackground.
 */
public class SunBackground extends AbstractBackground {

    // The radius of the biggest circle, the decrease between the every circle
    private static final int RADIUS = 62;
    private static final int DECREASE = 9;

    // The range of the shadow and the distance between the lines
    private static final int SHADOW_RANGE = 730;
    private static final int DISTANCE = 7;

    // The colors of the shapes on the background
    private static final Color BIG_CIRCLE = new Color(219, 204, 111);
    private static final Color MEDIUM_CIRCLE = new Color(255, 205, 20);
    private static final Color SMALL_CIRCLE = new Color(255, 235, 13);

    /**
     * @param bg is the background we add the sun.
     * @param center is the center of the sun.
     * @param space is the space of the bottom we draw the lines.
     */
    public SunBackground(Background bg, Point center, int space) {
        super(bg);

        // Add lines to the background
        for (int i = 0; i < SHADOW_RANGE; i += DISTANCE) {
            super.addShape(new Line(center.getX(), center.getY(), i, space, BIG_CIRCLE));
        }

        // Add circles to the background
        super.addShape(new Circle(center, RADIUS, BIG_CIRCLE));
        super.addShape(new Circle(center, RADIUS - DECREASE, MEDIUM_CIRCLE));
        super.addShape(new Circle(center, RADIUS - 2 * DECREASE, SMALL_CIRCLE));
    }
}
