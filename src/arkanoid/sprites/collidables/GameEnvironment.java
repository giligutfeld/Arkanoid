package arkanoid.sprites.collidables;

import arkanoid.gui.Point;
import arkanoid.gui.Line;

import java.util.ArrayList;
import java.util.List;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - GameEnvironment
 * GameEnvironment holds a collection of collidable objects and supports addition
 * and we can get the closest collision.
 */
public class GameEnvironment {

    private List<Collidable> environment;

    /**
     * Constructor.
     *
     * Add a list of collidable objects
     */
    public GameEnvironment() {
        this.environment = new ArrayList<>();
    }

    /**
     * @return the list with the collidable objects.
     */
    public List<Collidable> getEnvironment() {
        return this.environment;
    }

    /**
     * @param c add it to the environment.
     */
    public void addCollidable(Collidable c) {
        this.getEnvironment().add(c);
    }

    /**
     * @param c remove it from the environment.
     */
    public void removeCollidable(Collidable c) {
        this.getEnvironment().remove(c);
    }

    /**
     * @param trajectory is the line the object moves on.
     * @return the information about the closest collision that is going to occur
     * or null if it will not collide with any of the collidables.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        // Check if the environment is empty
        if (this.getEnvironment().isEmpty()) {
            return null;
        }

        Collidable closestCollidable = null;
        Point closest = null;

        // Make a copy of the environment before iterating over them.
        List<Collidable> list = new ArrayList<>(this.getEnvironment());

        // For every collidable object check if its intersect with the trajectory of the ball.
        for (Collidable c : list) {
            Point next = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());

            if (next != null) {
                // Check if the  collidable object is closer than the previous object
                if ((closest == null) || (next.distance(trajectory.start()) < closest.distance(trajectory.start()))) {
                    closest = next;
                    closestCollidable = c;
                }
            }
        }

        // return the closest collidable object to the ball if it exists
        if (closest == null) {
            return null;
        }
        return new CollisionInfo(closest, closestCollidable);
    }
}
