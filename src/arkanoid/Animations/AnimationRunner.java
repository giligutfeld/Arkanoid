package arkanoid.Animations;

import arkanoid.Constants;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - AnimationRunner
 * The AnimationRunner takes an Animation object and runs it in the gui board.
 */
public class AnimationRunner {

    private GUI gui;
    private int framesPerSecond = Constants.FRAMES_PER_SECOND;

    /**
     * Constructor.
     *
     * @param gui is the gui board in the game.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
    }

    /**
     * @return the number of frames per second.
     */
    public int getFramesPerSecond() {
        return this.framesPerSecond;
    }

    /**
     * @return the GUI in the game.
     */
    public GUI getGUI() {
        return this.gui;
    }

    /**
     * @param animation is the animation we run.
     */
    public void run(Animation animation) {
        // Create a sleeper to wait between the frames
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = Constants.MILLISECONDS / getFramesPerSecond();

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = getGUI().getDrawSurface();

            // Do the animation of the next frame,
            animation.doOneFrame(d);
            getGUI().show(d);

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
