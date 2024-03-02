import bagel.*;

/**
 * This interface realise the functionality of drawing text in the window
 */
public interface Displayable {
    /**
     * The path of font resource
     */
    public static final String FONTPATH = "res/VeraMono.ttf";
    /**
     * The size of the font
     */
    public static final int SIZE = 24;
    /**
     * The font resource we are using
     */
    public static final Font FONT = new Font(FONTPATH, SIZE);

    /**
     * This method draw the value in (x, y) coordinates
     * @param x x coordinate of drawing
     * @param y y coordinate of drawing
     * @param value context of drawing
     */
    default void DisplayNumber(int x, int y, int value) {
        Displayable.FONT.drawString(String.valueOf(value), x, y);
    }
}
