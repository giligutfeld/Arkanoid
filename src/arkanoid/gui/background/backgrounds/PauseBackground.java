package arkanoid.gui.background.backgrounds;

import arkanoid.gui.background.AbstractBackground;
import arkanoid.gui.Circle;
import arkanoid.gui.Point;
import arkanoid.gui.Rectangle;
import arkanoid.gui.background.Background;

import java.awt.Color;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - PauseBackground
 * The CloudsBackground draws a pause button on a BasicBackground.
 */
public class PauseBackground extends AbstractBackground {

    // The radius of the circles and the center.
    private static final int RADIUS_A = 100;
    private static final int RADIUS_B = 99;
    private static final int RADIUS_C = 95;
    private static final Point CENTER = new Point(400, 300);

    // The location of the rectangles and the size of them.
    private static final int X_LEFT_RECT = 350;
    private static final int X_RIGHT_RECT = 410;
    private static final int Y_RECT = 240;
    private static final int WIDTH = 40;
    private static final int HEIGHT = 120;

    /**
     * @param bg is the background we add the pause button.
     */
    public PauseBackground(Background bg) {
        super(bg);
        bg.addShape(new Circle(CENTER, RADIUS_A, Color.BLACK));
        bg.addShape(new Circle(CENTER, RADIUS_B, Color.WHITE));
        bg.addShape(new Circle(CENTER, RADIUS_C, Color.ORANGE));
        bg.addShape(new Rectangle(new Point(X_LEFT_RECT, Y_RECT), WIDTH, HEIGHT, Color.BLACK));
        bg.addShape(new Rectangle(new Point(X_RIGHT_RECT, Y_RECT), WIDTH, HEIGHT, Color.BLACK));
        bg.addShape(new Rectangle(new Point(X_LEFT_RECT + 1, Y_RECT + 1), WIDTH - 2,
                HEIGHT - 2, Color.WHITE));
        bg.addShape(new Rectangle(new Point(X_RIGHT_RECT + 1, Y_RECT + 1), WIDTH - 2,
                HEIGHT - 2, Color.WHITE));
    }
}
