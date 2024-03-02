/**
 * This class represent a thief actor in the ShadowLife
 */
public class Thief extends ActiveActor {
    /**
     * The Name of this type of actor
     */
    public static final String NAME = "Thief";
    private static final String IMAGEPATH = "res/images/thief.png";
    private static final int INITIALDIRECTION = Sign.UP;
    private static final boolean INITIALCONSUMING = false;
    private boolean consuming;

    /**
     * This method construct a new object of thief actor
     * @param position The position for new object
     */
    public Thief(Point position) {
        super(NAME, position, IMAGEPATH, INITIALDIRECTION);
        consuming = INITIALCONSUMING;
    }

    /**
     * This method construct a new object of gatherer actor
     * @param position The position for new object
     * @param direction The direction for new object
     */
    public Thief(Point position, int direction) {
        super(NAME, position, IMAGEPATH, direction);
    }

    /**
     * This method perform thief reaction when standing on a stockpile
     * @param other The stockpile a thief is standing on
     */
    public void takeStockpile(Actor other) {
        boolean carryingStatus = isCarrying();
        Stockpile stockpile = (Stockpile) other;
        if (!carryingStatus) {
            if (stockpile.take()) {
                setCarrying(true);
                consuming = false;
                swerve(Sign.NINETY, Sign.CLOCKWISE);
            }
        }
        else {
            swerve(Sign.NINETY, Sign.CLOCKWISE);
        }
    }

    /**
     * This method perform thief reaction when standing on a hoard
     * @param other The hoard a thief is standing on
     */
    public void takeHoard(Actor other) {
        boolean carryingStatus = isCarrying();
        Hoard hoard = (Hoard) other;
        if (consuming) {
            consuming = false;
            if  (!carryingStatus) {
                if (hoard.take())  {
                    setCarrying(true);
                }
                else {
                    swerve(Sign.NINETY, Sign.CLOCKWISE);
                }
            }
        }
        else {
            if (carryingStatus) {
                setCarrying(false);
                hoard.store();
                swerve(Sign.NINETY, Sign.CLOCKWISE);
            }
        }
    }

    /**
     * This method let thief reap fruit from tree/golden-tree
     * @param other The tree/golden-tree that is going to be reaped
     */
    @Override
    public void reapFruit(Actor other) {
        if (other.getName().equals(GoldenTree.NAME) || ((Tree) other).reap()) {
            setCarrying(true);
        }
    }

    /**
     * This method let thief store fruit to stockpile/hoard
     * @param other The stockpile/hoard that is going to be stored
     */
    @Override
    public void storeFruit(Actor other) {
        if (other.getName().equals(Hoard.NAME)) {
            takeHoard(other);
        }

        else if (other.getName().equals(Stockpile.NAME)) {
            takeStockpile(other);
        }
    }

    /**
     * This method perform thief-only behaviour, including standing on pad and avoid gatherer
     * @param other The other actor that thief should react, including pad and gatherer
     */
    @Override
    public void thiefBehaviour(Actor other) {
        if (other.getName().equals(Pad.NAME)) {
            consuming = true;
        }

        else if (other.getName().equals(Gatherer.NAME)) {
            swerve(Sign.TWOHUNDREDSEVENTY, Sign.CLOCKWISE);
        }
    }
}

