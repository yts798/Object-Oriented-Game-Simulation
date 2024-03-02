/**
 * This class represent a sign left actor in the ShadowLife
 */
public class SignLeft extends Sign {
    /**
     * The Name of this type of actor
     */
    public static final String NAME = "SignLeft";
    private static final String IMAGEPATH = "res/images/left.png";

    /**
     * This method construct a new object of sign left actor
     * @param position The position for new object
     */
    public SignLeft(Point position) {
        super(NAME, position, IMAGEPATH, Sign.LEFT);
    }

}


