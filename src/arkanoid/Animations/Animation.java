package arkanoid.Animations;

import biuoop.DrawSurface;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Interface name - Animation
 * Animations have the game specific logic and stopping conditions.
 */
public interface Animation {

    /**
     * @param d is the surface that the animation works there.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return true when the animation should stop and false otherwise.
     */
    boolean shouldStop();
}