/**
 * This class represent a stockpile actor in the ShadowLife
 */
public class Stockpile extends Actor implements Displayable {
    /**
     * The Name of this type of actor
     */
    public static final String NAME = "Stockpile";
    private static final String IMAGEPATH = "res/images/cherries.png";
    private int stock;

    /**
     * This method construct a new object of sign left actor
     * @param position The position for new object
     */
    public Stockpile(Point position) {
        super(NAME, position, IMAGEPATH);
        stock = 0;
    }

    /**
     * This method store one fruit to stockpile
     */
    public void store() {
        stock += 1;
    }

    /**
     * This method draw the image and the stock number of a stockpile
     */
    public void draw() {
        super.draw();
        DisplayNumber(getPosition().getX(), getPosition().getY(), stock);
    }

    /**
     * This method try to take a fruit from stockpile
     * @return Whether a fruit is successfully taken
     */
    public boolean take() {
        // only a stockpile with positive stock can be stolen
        if (stock <= 0) {
            return false;
        }
        else {
            stock -= 1;
            return true;
        }
    }

    /**
     * This method return current stock number of a stockpile
     * @return The current stock number of a stockpile
     */
    public int getStock() {
        return stock;
    }
}