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
 * The CloudsBackground draws clouds on a BasicBackground.
 */
public class CloudsBackground extends AbstractBackground {

    // Colors: Colors for the background
    private static final Color COLOR_A = new Color(203, 189, 189);
    private static final Color COLOR_B = new Color(187, 184, 172);
    private static final Color COLOR_C = new Color(168, 164, 157);

    // The centers, radius and colors of the circles
    private static final Point[] CENTERS = {new Point(100, 380), new Point(120, 400), new Point(140,
            370), new Point(170, 390), new Point(150, 410), new Point(600, 470),
            new Point(620, 500), new Point(640, 480), new Point(680, 490), new Point(660, 510)};
    private static final int[] RADIUS = {25, 25, 30, 35, 20, 25, 30, 30, 35, 20};
    private static final Color[] COLORS = {COLOR_A, COLOR_A, COLOR_B, COLOR_C, COLOR_C, COLOR_A, COLOR_A, COLOR_B,
            COLOR_C, COLOR_C};

    // The location and number of lines in the clouds
    private static final int LINES_IN_CLOUD = 10;
    private static final int DISTANCE = 10;
    private static final int Y_FIRST_CLOUD = 380;
    private static final int Y_SECOND_CLOUD = 500;
    private static final int X_FIRST_CLOUD = 95;
    private static final int X_SECOND_CLOUD = 595;
    private static final int X_FIRST_REGION = 65;
    private static final int X_SECOND_REGION = 580;

    /**
     * @param bg is the background we add the clouds.
     */
    public CloudsBackground(Background bg) {
        super(bg);

        // Add lines to the background
        for (int i = 0; i < LINES_IN_CLOUD; i++) {
            bg.addShape(new Line(X_FIRST_CLOUD + i * DISTANCE, Y_FIRST_CLOUD, X_FIRST_REGION + i * DISTANCE,
                    bg.getRect().getHeight(), Color.WHITE));
        }
        for (int i = 0; i < LINES_IN_CLOUD; i++) {
            bg.addShape(new Line(X_SECOND_CLOUD + i * DISTANCE, Y_SECOND_CLOUD, X_SECOND_REGION + i * DISTANCE,
                    bg.getRect().getHeight(), Color.WHITE));
        }

        // Add circles to the background
        for (int i = 0; i < CENTERS.length; i++) {
            bg.addShape(new Circle(CENTERS[i], RADIUS[i], COLORS[i]));
        }
    }
}
