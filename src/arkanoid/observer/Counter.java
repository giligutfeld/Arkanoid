package arkanoid.observer;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - Counter
 * Counter is a simple class that is used for counting things. It can increase, decrese and return the count.
 */
public class Counter {
    private int count;

    /**
     * Constructor.
     *
     * Initialize a new Counter from 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * @param number add to current count.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * @param number subtract from current count.
     */
    public void decrease(int number) {
        this.count -= number;

    }

    /**
     * @return the current count.
     */
    public int getValue() {
        return this.count;
    }
}