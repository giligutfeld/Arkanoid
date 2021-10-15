package arkanoid.sprites.collidables;

import arkanoid.gui.Point;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - CollisionInfo
 * CollisionInfos have the data of the collision, the collision point and the collision object.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor.
     *
     * @param collisionPoint  is the location point of the collision.
     * @param collisionObject is the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
