package arkanoid.sprites.indicators;

import arkanoid.observer.Counter;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - ScoreIndicator
 * ScoreIndicator will be in charge of displaying the current score and it The will hold a reference
 * to the scores counter.
 */
public class ScoreIndicator implements Indicator {

    // Every ScoreIndicator has counter and color and.
    private Counter score;

    /**
     * Constructor.
     *
     * @param score      is the score the indicator points.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * @return the score of the indicator.
     */
    public Counter getScore() {
        return this.score;
    }

    @Override
    public String getText() {
        return "Score: " + getScore().getValue();
    }
}
