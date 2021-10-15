package arkanoid.gui.background;

import arkanoid.Animations.GameLevel;
import arkanoid.gui.Rectangle;
import arkanoid.gui.Shape;
import biuoop.DrawSurface;

import java.util.List;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - AbstractBackground
 * The AbstractBackground has a BasicBackground and can add shapes to the Background.
 */
public abstract class AbstractBackground implements Background {

    private Background decorated;

    /**
     * Constructor.
     *
     * @param decorated is the background we add there the shapes.
     */
    public AbstractBackground(Background decorated) {
        this.decorated = decorated;
    }

    @Override
    public List<Shape> getShapes() {
        return this.decorated.getShapes();
    }

    @Override
    public Rectangle getRect() {
        return this.decorated.getRect();
    }

    @Override
    public void addShape(Shape shape) {
        this.decorated.addShape(shape);
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.decorated.drawOn(d);
    }

    @Override
    public void timePassed() {
        this.decorated.timePassed();
    }

    @Override
    public void addToGame(GameLevel g) {
        this.decorated.addToGame(g);
    }
}
