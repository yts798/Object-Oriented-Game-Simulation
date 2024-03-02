/**
 * This class represent a pad actor in the ShadowLife
 */
public class Pad extends Actor{
    /**
     * The Name of this type of actor
     */
    public static final String NAME = "Pad";
    private static final String IMAGEPATH = "res/images/pad.png";

    /**
     * This method construct a new object of pad actor
     * @param position The position for new object
     */
    public Pad(Point position) {
        super(NAME, position, IMAGEPATH);
    }

}