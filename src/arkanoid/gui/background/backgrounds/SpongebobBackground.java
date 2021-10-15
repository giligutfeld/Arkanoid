package arkanoid.gui.background.backgrounds;

// ID: 209284512

import arkanoid.Constants;
import arkanoid.gui.Circle;
import arkanoid.gui.Line;
import arkanoid.gui.Point;
import arkanoid.gui.Rectangle;
import arkanoid.gui.background.AbstractBackground;
import arkanoid.gui.background.Background;

import java.awt.Color;

/**
 * @author Gili Gutfeld
 * Class name - SpongebobBackground
 * The SpongebobBackground draws Bikini Bottom on a BasicBackground.
 */
public class SpongebobBackground extends AbstractBackground {
    // The location and number of lines in the clouds
    private static final int X_PATRICK = 150;
    private static final int Y_PATRICK = 380;
    private static final int X_SQUIDWARD = 320;
    private static final int Y_SQUIDWARD = 240;
    private static final int X_SPONGEBOB = 550;
    private static final int Y_SPONGEBOB = 280;

    /**
     * @param bg is the background we add the clouds.
     */
    public SpongebobBackground(Background bg) {
        super(bg);

        // The sky
        bg.addShape(new Rectangle(new Point(0, 0), Constants.WIDTH, Constants.HEIGHT, Color.CYAN));

        // Patrick's home
        bg.addShape(new Circle(new Point(X_PATRICK, Y_PATRICK), 90, Color.BLACK));

        // SpongeBob's home
        bg.addShape(new Circle(new Point(X_SPONGEBOB + 90, Y_SPONGEBOB), 90, Color.ORANGE));
        bg.addShape(new Rectangle(new Point(X_SPONGEBOB, Y_SPONGEBOB), 180, Y_SPONGEBOB, Color.ORANGE));
        bg.addShape(new Circle(new Point(X_SPONGEBOB + 50, Y_SPONGEBOB), 30, Color.GRAY));
        bg.addShape(new Circle(new Point(X_SPONGEBOB + 90, Y_SPONGEBOB + 70), 36, Color.GRAY));
        bg.addShape(new Rectangle(new Point(X_SPONGEBOB + 54, Y_SPONGEBOB + 70), 72, 100, Color.GRAY));

        bg.addShape(new Line(550, 170, 600, 150, Color.GREEN));

        // Squidward's home
        bg.addShape(new Rectangle(new Point(X_SQUIDWARD, Y_SQUIDWARD), 160, 400, Color.BLUE));
        bg.addShape(new Rectangle(new Point(X_SQUIDWARD + 30, Y_SQUIDWARD - 20), 100, 20, Color.BLUE));
        bg.addShape(new Rectangle(new Point(X_SQUIDWARD - 20, Y_SQUIDWARD + 20), 20, 60, Color.BLUE));
        bg.addShape(new Rectangle(new Point(X_SQUIDWARD + 160, Y_SQUIDWARD + 20), 20, 60, Color.BLUE));
        bg.addShape(new Circle(new Point(X_SQUIDWARD + 40, Y_SQUIDWARD + 40), 25, Color.GRAY));
        bg.addShape(new Circle(new Point(X_SQUIDWARD + 120, Y_SQUIDWARD + 40), 25, Color.GRAY));

        // The beach
        bg.addShape(new Rectangle(new Point(0, Constants.HEIGHT - 200), Constants.WIDTH, 200, Color.YELLOW));


    }
}
