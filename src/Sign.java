/**
 * This abstract class represent a Sign
 * Sign is a generalisation of 4 types of signs
 */
public abstract class Sign extends Actor {
    /**
     * The Name of this type of actor
     */
    public static final String NAME = "Sign";
    /**
     * The up direction
     */
    public static final int UP = 0;
    /**
     * The right direction
     */
    public static final int RIGHT = 1;
    /**
     * The down direction
     */
    public static final int DOWN = 2;
    /**
     * The left direction
     */
    public static final int LEFT = 3;
    /**
     * The counterclockwise orientation
     */
    public static final int COUNTERCLOCKWISE = 0;
    /**
     * The clockwise orientation
     */
    public static final int CLOCKWISE = 1;
    /**
     * The rotation degree of 90 degrees
     */
    public static final int NINETY = 1;
    /**
     * The rotation degree of 180 degrees
     */
    public static final int ONEHUNDREDEIGHTY = 2;
    /**
     * The rotation degree of 270 degrees
     */
    public static final int TWOHUNDREDSEVENTY = 3;
    /**
     * The rotation degree of 360 degrees
     */
    public static final int THREEHUNDREDSIXTY = 4;
    /**
     * Total number of direction types
     */
    public static final int MAXDIRECTION = 4;


    /**
     * This method construct a new object of sign
     * @param name The name of sign
     * @param position The position of sign
     * @param imagePath The image resource of sign
     * @param direction The direction information of sign
     */

    public Sign(String name, Point position, String imagePath, int direction) {
        super(name, position, imagePath);
    }
}



