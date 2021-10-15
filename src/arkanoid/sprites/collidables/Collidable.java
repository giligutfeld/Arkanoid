package arkanoid.sprites.collidables;

import arkanoid.gui.Point;
import arkanoid.gui.Rectangle;
import arkanoid.sprites.Ball;
import arkanoid.gui.ball.Velocity;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Interface name - Collidable
 * The Collidable interface will be used by things that can be collided with.
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     @param hitter is the ball that hit the Collidable.
     @param currentVelocity is the velocity of the object before the collision.
     @param collisionPoint is the point where the object collided.
     @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}