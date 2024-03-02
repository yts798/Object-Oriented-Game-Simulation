/**
 * This class represent a sign down actor in the ShadowLife
 */
public class SignDown extends Sign {
    /**
     * The Name of this type of actor
     */
    public static final String NAME = "SignDown";
    private static final String IMAGEPATH = "res/images/down.png";

    /**
     * This method construct a new object of sign down actor
     * @param position The position for new object
     */
    public SignDown(Point position) {
        super(NAME, position, IMAGEPATH, Sign.DOWN);
    }

}
