/**
 * This class represent a sign up actor in the ShadowLife
 */
public class SignUp extends Sign {
    /**
     * The Name of this type of actor
     */
    public static final String NAME = "SignUp";
    private static final String IMAGEPATH = "res/images/up.png";

    /**
     * This method construct a new object of sign up actor
     * @param position The position for new object
     */
    public SignUp(Point position) {
        super(NAME, position, IMAGEPATH, Sign.UP);
    }

}

