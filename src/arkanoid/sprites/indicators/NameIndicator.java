package arkanoid.sprites.indicators;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - ScoreIndicator
 * ScoreIndicator will be in charge of displaying the name of the level.
 */
public class NameIndicator implements Indicator {

    private String name;

    /**
     * Constructor.
     *
     * @param name      is the name of the level.
     */
    public NameIndicator(String name) {
        this.name = name;
    }

    /**
     * @return the name of the level.
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String getText() {
        return getName();
    }
}
