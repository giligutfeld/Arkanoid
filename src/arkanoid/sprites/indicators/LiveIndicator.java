package arkanoid.sprites.indicators;

import arkanoid.observer.Counter;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - ScoreIndicator
 * ScoreIndicator will be in charge of displaying the current lives and it The will hold a reference
 * to the lives counter.
 */
public class LiveIndicator implements Indicator {

    private Counter lives;

    /**
     * Constructor.
     *
     * @param lives      is the count of lives the player has.
     */
    public LiveIndicator(Counter lives) {
        this.lives = lives;
    }

    /**
     * @return the lives of the indicator.
     */
    public Counter getLives() {
        return this.lives;
    }

    @Override
    public String getText() {
        return "Lives: " + getLives().getValue();
    }
}
