/**
 * This class represent a Tree actor in the ShadowLife
 */
public class Tree extends Actor implements Displayable {
    /**
     * The Name of this type of actor
     */
    public static final String NAME = "Tree";
    private static final String IMAGEPATH = "res/images/tree.png";
    private static final int FRUITMAXNUMBER = 3;
    private int fruitNumber;

    /**
     * This method construct a new object of tree actor
     * @param position The position for new object
     */

    public Tree(Point position) {
        super(NAME, position, IMAGEPATH);
        fruitNumber = FRUITMAXNUMBER;
    }

    /**
     * This method try to reap one fruit from tree
     * @return whether reaping is successful
     */
    public boolean reap() {
        // only a tree with positive fruits can be reaped
        if (fruitNumber <= 0) {
            return false;
        }
        else {
            fruitNumber -= 1;
            return true;
        }
    }

    /**
     * This method draw the image and the fruit number of a tree
     */
    @Override
    public void draw() {
        super.draw();
        DisplayNumber(getPosition().getX(), getPosition().getY(), fruitNumber);
    }
}

