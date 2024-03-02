/**
 * This class represent a mitosis pool actor in the ShadowLife
 */
public class MitosisPool extends Actor {
    /**
     * The Name of this type of actor
     */
    public static final String NAME = "Pool";
    private static final String IMAGEPATH = "res/images/pool.png";

    /**
     * This method construct a new object of mitosis pool actor
     * @param position The position for new object
     */
    public MitosisPool(Point position) {
        super(NAME, position, IMAGEPATH);
    }

}



