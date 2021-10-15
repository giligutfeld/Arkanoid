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
 * The CloudsBackground draws a target on a BasicBackground.
 */
public class TargetBackground extends AbstractBackground {

    // The radius of the biggest circle, the decrease between the every circle
    private static final int RADIUS = 130;
    private static final int DECREASE = 30;

    // The distance between the center to the start and the end of the lines.
    private static final int DISTANCE = 150;

    /**
     * @param bg is the background we add the target.
     * @param center is the center of the target.
     * @param distance is the distance between the block to the lines.
     */
    public TargetBackground(Background bg, Point center, int distance) {
        super(bg);
        double x = center.getX();
        double y = center.getY();

        // Add circles to the background
        bg.addShape(new Circle(center, RADIUS, Color.BLUE));
        bg.addShape(new Circle(center, RADIUS - 1, Color.BLACK));
        bg.addShape(new Circle(center, RADIUS - DECREASE, Color.BLUE));
        bg.addShape(new Circle(center, RADIUS - DECREASE - 1, Color.BLACK));
        bg.addShape(new Circle(center, RADIUS - 2 * DECREASE, Color.BLUE));
        bg.addShape(new Circle(center, RADIUS - 2 * DECREASE - 1, Color.BLACK));

        // Add lines to the background
        bg.addShape(new Line(x - DISTANCE, y, x - distance, y, Color.BLUE));
        bg.addShape(new Line(x + distance, y, x + DISTANCE, y, Color.BLUE));
        bg.addShape(new Line(x, y - DISTANCE, x, y - distance, Color.BLUE));
        bg.addShape(new Line(x, y + distance, x, y + DISTANCE, Color.BLUE));
    }
}
