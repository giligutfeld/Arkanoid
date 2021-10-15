package arkanoid.gui.background.backgrounds;

import arkanoid.gui.background.AbstractBackground;
import arkanoid.gui.Circle;
import arkanoid.gui.Point;
import arkanoid.gui.background.Background;

import java.awt.Color;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - HappyBackground
 * The CloudsBackground draws happy emoji on a BasicBackground.
 */
public class HappyBackground extends AbstractBackground {

    // The radius of and the center of the  of the emoji.
    private static final int RADIUS_A = 100;
    private static final int RADIUS_B = 99;
    private static final int RADIUS_C = 15;
    private static final int RADIUS_D = 4;
    private static final int RADIUS_E = 20;
    private static final int DISTANCE_A = 35;
    private static final int DISTANCE = 5;
    private static final int Y_MOUTH = 350;

    /**
     * @param bg is the background we add happy emoji.
     * @param center is the center of the emoji.
     */
    public HappyBackground(Background bg, Point center) {
        super(bg);
        bg.addShape(new Circle(center, RADIUS_A, Color.BLACK));
        bg.addShape(new Circle(center, RADIUS_B, Color.ORANGE));
        bg.addShape(new Circle(new Point(center.getX() - DISTANCE_A, center.getY() - DISTANCE_A),
                RADIUS_C, Color.BLACK));
        bg.addShape(new Circle(new Point(center.getX() + DISTANCE_A, center.getY() - DISTANCE_A),
                RADIUS_C, Color.BLACK));
        bg.addShape(new Circle(new Point(center.getX() - DISTANCE_A - DISTANCE,
                center.getY() - DISTANCE_A - DISTANCE), RADIUS_D, Color.GRAY));
        bg.addShape(new Circle(new Point(center.getX() + DISTANCE_A - DISTANCE,
                center.getY() - DISTANCE_A - DISTANCE), RADIUS_D, Color.GRAY));
        bg.addShape(new Circle(new Point(center.getX(), Y_MOUTH), RADIUS_E, Color.BLACK));
    }
}
