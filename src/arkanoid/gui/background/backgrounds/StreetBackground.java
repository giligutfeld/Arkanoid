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
 * Class name - CloudsBackground
 * The StreetBackground draws lamp on a BasicBackground.
 */
public class StreetBackground extends AbstractBackground {

    // The colors of the shapes on the background
    private static final Color BIG_CIRCLE = new Color(255, 205, 20);
    private static final Color MEDIUM_CIRCLE = new Color(205, 73, 3);
    private static final Color SMALL_CIRCLE = new Color(255, 255, 255);
    private static final Color COLOR_BIG_RECT = new Color(24, 8, 8);
    private static final Color COLOR_MEDIUM_RECT = new Color(62, 57, 57);
    private static final Color COLOR_SMALL_RECT = new Color(82, 79, 79);

    // The location, width and height of the big rectangle.
    private static final int HEIGHT_BIG_RECT = 160;
    private static final int WIDTH_BIG_RECT = 100;
    private static final int X_BIG_RECT = 70;
    private static final int Y_BIG_RECT = 450;

    // The location, width and height of the medium rectangle.
    private static final int HEIGHT_MEDIUM_RECT = 50;
    private static final int WIDTH_MEDIUM_RECT = 30;
    private static final int X_MEDIUM_RECT = X_BIG_RECT + WIDTH_BIG_RECT / 2 - WIDTH_MEDIUM_RECT / 2;
    private static final int Y_MEDIUM_RECT = Y_BIG_RECT - HEIGHT_MEDIUM_RECT;

    // The location, width and height of the small rectangle.
    private static final int HEIGHT_SMALL_RECT = 200;
    private static final int WIDTH_SMALL_RECT = 9;
    private static final int X_SMALL_RECT = X_MEDIUM_RECT + WIDTH_MEDIUM_RECT / 2 - WIDTH_SMALL_RECT / 2;
    private static final int Y_SMALL_RECT = Y_MEDIUM_RECT - HEIGHT_SMALL_RECT;

    // The radius of the biggest circle, the decrease between the every circle.
    private static final int RADIUS = 14;
    private static final int DECREASE = 5;
    private static final int CENTER_X = X_SMALL_RECT + WIDTH_SMALL_RECT / 2;
    private static final int CENTER_Y = Y_SMALL_RECT;

    // Number of squares in the lamp, and their size.
    private static final int SQUARES_IN_ROW = 5;
    private static final int SQUARES_IN_COLUMN = 5;
    private static final int WIDTH_SQUARES = 10;
    private static final int HEIGHT_SQUARES = 22;
    private static final int FIRST_SPACE = 9;
    private static final int SPACE_SQUARES = 8;

    /**
     * @param bg is the background we add the lamp.
     */
    public StreetBackground(Background bg) {
        super(bg);

        // Add rectangle to the background.
        bg.addShape(new Rectangle(new Point(X_BIG_RECT, Y_BIG_RECT), WIDTH_BIG_RECT, HEIGHT_BIG_RECT,
                COLOR_BIG_RECT));
        bg.addShape(new Rectangle(new Point(X_MEDIUM_RECT, Y_MEDIUM_RECT), WIDTH_MEDIUM_RECT, HEIGHT_MEDIUM_RECT,
                COLOR_MEDIUM_RECT));
        bg.addShape(new Rectangle(new Point(X_SMALL_RECT, Y_SMALL_RECT), WIDTH_SMALL_RECT, HEIGHT_SMALL_RECT,
                COLOR_SMALL_RECT));

        // Add the squares in the lamp.
        for (int i = 0; i < SQUARES_IN_ROW; i++) {
            for (int j = 0; j < SQUARES_IN_COLUMN; j++) {
                bg.addShape(new Rectangle(new Point(X_BIG_RECT + FIRST_SPACE + i * (WIDTH_SQUARES + SPACE_SQUARES),
                        Y_BIG_RECT + SPACE_SQUARES + j * (HEIGHT_SQUARES + SPACE_SQUARES)), WIDTH_SQUARES,
                        HEIGHT_SQUARES, Color.WHITE));
            }
        }

        // Add circles to the background
        bg.addShape(new Circle(new Point(CENTER_X, CENTER_Y), RADIUS, BIG_CIRCLE));
        bg.addShape(new Circle(new Point(CENTER_X, CENTER_Y), RADIUS - DECREASE, MEDIUM_CIRCLE));
        bg.addShape(new Circle(new Point(CENTER_X, CENTER_Y), RADIUS - 2 * DECREASE, SMALL_CIRCLE));
    }
}
