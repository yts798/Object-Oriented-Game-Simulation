/**
 * This class represent a hoard actor in the ShadowLife
 */
public class Hoard extends Actor implements Displayable{
    /**
     * The Name of this type of actor
     */
    public static final String NAME = "Hoard";
    private static final String IMAGEPATH = "res/images/hoard.png";
    private int stock;

    /**
     * This method construct a new object of hoard actor
     * @param position The position for new object
     */
    public Hoard(Point position) {
        super(NAME, position, IMAGEPATH);
        stock = 0;
    }

    /**
     * This method store one fruit to hoard
     */
    public void store() {
        stock += 1;
    }

    /**
     * This method draw the image and the stock number of a hoard
     */
    public void draw() {
        super.draw();
        DisplayNumber(getPosition().getX(), getPosition().getY(), stock);
    }

    /**
     * This method try to take a fruit from hoard
     * @return Whether a fruit is successfully taken
     */
    public boolean take() {
        // only a hoard with positive stock can be stolen
        if (stock <= 0) {
            return false;
        }
        else {
            stock -= 1;
            return true;
        }
    }

    /**
     * This method return current stock number of a hoard
     * @return The current stock number of a hoard
     */
    public int getStock() {
        return stock;
    }
}
