/**
 * This class represent a golden tree actor in the ShadowLife
 */
public class GoldenTree extends Actor {
    /**
     * The Name of this type of actor
     */
    public static final String NAME = "GoldenTree";
    private static final String IMAGEPATH = "res/images/gold-tree.png";
    private static final int INFINITY = 1;

    /**
     * This method construct a new object of golden tree actor
     * @param position The position for new object
     */
    public GoldenTree(Point position) {
        super(NAME, position, IMAGEPATH);
    }
}

