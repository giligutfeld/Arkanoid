package arkanoid.gui.background;

import arkanoid.Animations.GameLevel;
import arkanoid.gui.Rectangle;
import arkanoid.gui.Shape;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - BasicBackgroumnd
 * The BasicBackgroumnd has one rectangle with color that is the color of the background. It implements the
 * Background interface.
 */
public class BasicBackground implements Background {

    private List<Shape> shapes;
    private Rectangle rect;

    /**
     * constructor.
     *
     * @param color is the color of the background.
     * @param rect  is the rectangle of the background.
     */
    public BasicBackground(Color color, Rectangle rect) {
        rect.setColor(color);
        this.rect = rect;
        this.shapes = new ArrayList<>();
        this.addShape(rect);
    }

    @Override
    public Rectangle getRect() {
        return this.rect;
    }

    @Override
    public void addShape(Shape shape) {
        getShapes().add(shape);
    }

    @Override
    public List<Shape> getShapes() {
        return this.shapes;
    }

    @Override
    public void drawOn(DrawSurface d) {
        for (Shape s : getShapes()) {
            s.drawOn(d);
        }
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
