/**
 * This class represent a gatherer actor in the ShadowLife
 */
public class Gatherer extends ActiveActor {
    /**
     * The Name of this type of actor
     */
    public static final String NAME = "Gatherer";
    private static final String IMAGEPATH = "res/images/gatherer.png";
    private static final int INITIALDIRECTION = Sign.LEFT;

    /**
     * This method construct a new object of gatherer actor
     * @param position The position for new object
     */
    public Gatherer(Point position) {
        super(NAME, position, IMAGEPATH, INITIALDIRECTION);
    }

    /**
     * This method construct a new object of gatherer actor
     * @param position The position for new object
     * @param direction The direction for new object
     */
    public Gatherer(Point position, int direction) {
        super(NAME, position, IMAGEPATH, direction);
    }

    /**
     * This method reap fruit from tree/golden-tree
     * @param other The tree/golden-tree that is going to be reaped
     */
    @Override
    public void reapFruit(Actor other) {
        if (other.getName().equals(GoldenTree.NAME) || ((Tree) other).reap()) {
            setCarrying(true);
            swerve(Sign.ONEHUNDREDEIGHTY, Sign.CLOCKWISE);
        }
    }

    /**
     * This method store fruit to stockpile/hoard
     * @param other The stockpile/hoard that is going to be stored
     */
    @Override
    public void storeFruit(Actor other) {
        if (other.getName().equals(Stockpile.NAME)) {
            if (isCarrying()) {
                setCarrying(false);
                ((Stockpile) other).store();
            }
        }
        else {
            Hoard store = (Hoard) other;
            if (isCarrying()) {
                setCarrying(false);
                ((Hoard) other).store();
            }
        }
        swerve(Sign.ONEHUNDREDEIGHTY, Sign.CLOCKWISE);
    }
}
