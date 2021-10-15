package arkanoid.sprites.indicators;

import arkanoid.Animations.GameLevel;
import arkanoid.gui.Rectangle;
import arkanoid.sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

// ID: 209284512
/**
 * @author Gili Gutfeld
 * Class name - IndicatorsLine
 * IndicatorsLine will be in charge of displaying the indicators in the line. The IndicatorsLine will hold a reference
 * to a list of indicators, and will be added to the game as a sprite positioned at the top of the screen.
 */
public class IndicatorsLine implements Sprite {

    // The space from the bottom of the line.
    private static final int BOTTOM_SPACE = 2;

    private List<Indicator> list;
    private Rectangle rect;

    /**
     * @param list of indicators to display.
     * @param rect of the line.
     */
    public IndicatorsLine(List<Indicator> list, Rectangle rect) {
        this.list = list;
        this.rect = rect;
    }

    /**
     * @return te list of the indicators.
     */
    public List<Indicator> getIndicators() {
        return this.list;
    }

    /**
     * @return the rectangle of the indicator.
     */
    public Rectangle getRect() {
        return this.rect;
    }

    @Override
    public void drawOn(DrawSurface d) {
        String space = "                   ";
        d.setColor(Color.BLACK);
        String text = space;
        for (Indicator i : getIndicators()) {
            text += space + i.getText();
        }

        // Add a color and text to the line
        getRect().drawOn(d);
        d.setColor(Color.BLACK);
        d.drawText((int) getRect().getUpperLeft().getX(), (int) (getRect().getUpperLeft().getY()
                + getRect().getHeight() - BOTTOM_SPACE), text, (int) (getRect().getHeight() - 2 * BOTTOM_SPACE));
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
