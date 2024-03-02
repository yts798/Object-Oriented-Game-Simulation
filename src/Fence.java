/**
 * This class represent a fence actor in the ShadowLife
 */
public class Fence extends Actor {
    /**
     * The Name of this type of actor
     */
    public static final String NAME = "Fence";
    private static final String IMAGEPATH = "res/images/fence.png";

    /**
     * This method construct a new object of fence actor
     * @param position The position for new object
     */
    public Fence(Point position) {
        super(NAME, position, IMAGEPATH);
    }

}

