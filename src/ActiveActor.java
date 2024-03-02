/**
 * This class represents an Active Actor
 * Active Actor is only a generalization of actors which can move, including gatherer and thief
 */
public abstract class ActiveActor extends Actor{
    private static boolean INITIALCARRYING = false;
    private static boolean INITIALACTIVE = true;
    private final String name;
    private int direction;
    private boolean carrying;
    private boolean active;

    /**
     * This method construct a new object of active actor
     * @param name The name of active actor
     * @param position The position of active actor in the wolrd
     * @param imagePath The image resource location
     * @param direction The default moving direction of active actor
     */
    public ActiveActor(String name, Point position, String imagePath, int direction) {
        super(name, position, imagePath);
        this.name = name;
        this.direction = direction;
        this.carrying = INITIALCARRYING;
        this.active = INITIALACTIVE;
    }

    /**
     * This method make active actor swerve
     * @param degree The degrees that active actor should swerve
     * @param orientation The orientation of swerving
     */
    public void swerve(int degree, int orientation) {
        // deal with orientation
        if (orientation == Sign.COUNTERCLOCKWISE) {
            degree = Sign.THREEHUNDREDSIXTY - degree;
        }
        // change direction
        direction = (direction + degree) % Sign.MAXDIRECTION;
    }

    /**
     * This method move active actor to a new position
     * @param x The distance of travel in x axis
     * @param y The distance of travel in y axis
     */
    public void moveTo(int x, int y) {
        getPosition().setX(getPosition().getX()+x);
        getPosition().setY(getPosition().getY()+y);
    }

    /**
     * This method move active actor one tile based on direction
     */
    @Override
    public void move() {
        if (active) {
            switch (direction) {
                case Sign.UP:
                    moveTo(0, -ShadowLife.TILELENGTH);
                    break;
                case Sign.DOWN:
                    moveTo(0, ShadowLife.TILELENGTH);
                    break;
                case Sign.LEFT:
                    moveTo(-ShadowLife.TILELENGTH, 0);
                    break;
                case Sign.RIGHT:
                    moveTo(ShadowLife.TILELENGTH, 0);
                    break;
            }
        }
    }

    /**
     * This abstract method reap fruit from tree/golden-tree
     * @param other The tree/golden-tree that is going to be reaped
     */
    public abstract void reapFruit(Actor other);


    /**
     * This abstract method store fruit to stockpile/hoard
     * @param other The stockpile/hoard that is going to be stored
     */
    public abstract void storeFruit(Actor other);

    /**
     * This method perform thief-only behaviour, including standing on pad and avoid gatherer
     * @param other The other actor that thief should react, including pad and gatherer
     */
    public void thiefBehaviour(Actor other) {};

    /**
     * This method perform active actor action based on the actors they are standing on
     * @param other The other actors my active actors standing on
     */
    @Override
    public void standOn(Actor other) {
        // detect which type of actor our activeactor is standing on
        if (other.getName().equals(Fence.NAME)) {
            swerve(Sign.ONEHUNDREDEIGHTY, Sign.CLOCKWISE);
            move();
            active = false;
        }

        // alter direction to match the sign
        else if (other.getName().equals(SignUp.NAME)) {
            direction = Sign.UP;
        }

        else if (other.getName().equals(SignRight.NAME)) {
            direction = Sign.RIGHT;
        }

        else if (other.getName().equals(SignDown.NAME)) {
            direction = Sign.DOWN;
        }

        else if (other.getName().equals(SignLeft.NAME)) {
            direction = Sign.LEFT;
        }

        else if (name.equals(Thief.NAME) && (other.getName().equals(Pad.NAME
        ) || other.getName().equals(Gatherer.NAME))) {
            thiefBehaviour(other);
        }

        else if (!carrying && (other.getName().equals(Tree.NAME) || other.getName().equals(GoldenTree.NAME))) {
            reapFruit(other);
        }
        else if (other.getName().equals(Stockpile.NAME) || other.getName().equals(Hoard.NAME)) {
            storeFruit(other);
        }
    }

    /**
     * This method return the direction of an active actor
     * @return The direction of an active actor
     */
    public int getDirection() {
        return direction;
    }

    /**
     * This method return the carrying status of an active actor
     * @return The carrying status of an active actor
     */
    public boolean isCarrying() {
        return carrying;
    }

    /**
     * This method return the active status of an active actor
     * @return The active status of an active actor
     */
    public boolean isActive() {
        return active;
    }

    /**
     * This method set the carrying status of an active actor
     * @param carrying The new carrying status for an active actor
     */
    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }
}

