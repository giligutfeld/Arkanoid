package arkanoid.gui.background;

import arkanoid.gui.Rectangle;
import arkanoid.gui.Shape;
import arkanoid.sprites.Sprite;

import java.util.List;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Interface name - Background
 * The Background has rectangle of the screen, shapes and option to add shapes to it.
 */
public interface Background extends Sprite {

    /**
     * @return the rectangle of the gui.
     */
    Rectangle getRect();

    /**
     * @return the list of the shapes in the background.
     */
    List<Shape> getShapes();

    /**
     * @param shape is the new shape in the background.
     */
    void addShape(Shape shape);
}
