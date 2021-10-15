package arkanoid.Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - KeyPressStoppableAnimation
 * KeyPressStoppableAnimation decorator-class that will wrap an existing animation and add a
 * "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     *
     * @param sensor    is the keyboard of the game.
     * @param key       is the key we are waiting for it.
     * @param animation is the animation we run until the press.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * @return the animation in the game.
     */
    public Animation getAnimation() {
        return this.animation;
    }

    /**
     * @return the pressed key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return true if the key was pressed and false otherwise.
     */
    public boolean getIsAlreadyPressed() {
        return this.isAlreadyPressed;
    }

    /**
     * @return the keyboard in the game.
     */
    public KeyboardSensor getKs() {
        return this.sensor;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        getAnimation().doOneFrame(d);

        // We verify that the key press started only after the animation started.
        if (!(getKs().isPressed(getKey()))) {
            this.isAlreadyPressed = false;
        }

        // When the key is pressed we stop the animation.
        if ((getKs().isPressed(getKey())) && (!getIsAlreadyPressed())) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
