import arkanoid.Animations.AnimationRunner;
import arkanoid.Constants;
import arkanoid.GameFlow;
import arkanoid.levels.*;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - Ass6Game
 * Ass6Game creates a game object, initializes and runs it.
 */
public class Ass6Game {

    public static final int SCORE_BLOCK_START = 5;

    /**
     * @param args is the numbers of the levels we run.
     */
    public static void main(String[] args) {
        // Create a list of the levels and save the number of points every block is worth in the first level.
        List<LevelInformation> levels = new ArrayList<>();
        int scorePerBlock = SCORE_BLOCK_START;

        // Flag for check we got a valid number of level
        boolean flag = false;
        // Add all the levels in the array to the list and update the score by adding 1 to every new level.
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("1")) {
                levels.add(new DirectHit(scorePerBlock++));
                flag = true;
            }
            if (args[i].equals("2")) {
                levels.add(new WideEasy(scorePerBlock++));
                flag = true;
            }
            if (args[i].equals("3")) {
                levels.add(new Monster(scorePerBlock++));
                flag = true;
            }
            if (args[i].equals("4")) {
                levels.add(new FinalFour(scorePerBlock++));
                flag = true;
            }
        }

        if (!flag) {
            levels.add(new DirectHit(scorePerBlock++));
            levels.add(new WideEasy(scorePerBlock++));
            levels.add(new Monster(scorePerBlock++));
            levels.add(new Green3(scorePerBlock++));
            levels.add(new FinalFour(scorePerBlock++));
        }

        // Create a GUI board and run there all the levels in the list.
        AnimationRunner ar = new AnimationRunner(new GUI("Arkanoid", Constants.WIDTH, Constants.HEIGHT));
        GameFlow game = new GameFlow(ar, ar.getGUI().getKeyboardSensor());
        game.runLevels(levels);
    }
}
