/**
 * This class represent a sign right actor in the ShadowLife
 */
public class SignRight extends Sign {
    /**
     * The Name of this type of actor
     */
    public static final String NAME = "SignRight";
    private static final String IMAGEPATH = "res/images/right.png";

    /**
     * This method construct a new object of sign right actor
     * @param position The position for new object
     */
    public SignRight(Point position) {
        super(NAME, position, IMAGEPATH, Sign.RIGHT);
    }

}

