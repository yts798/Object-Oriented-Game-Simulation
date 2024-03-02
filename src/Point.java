/**
 * This class Represents the coordinates of an actor in the game
 */
public class Point {
    private int x;
    private int y;

    /**
     * This method construct a new object of point
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * This method construct a new object of point
     * @param x x-coordinate of a point
     * @param y y-coordinate of a point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method construct a new object of point, copying the value of other
     * @param other the point which will be copied
     */
    public Point(Point other) {
        this.x = other.x;
        this.y = other.y;
    }

    /**
     * This method return the x-coordinate of a point
     * @return x-coordinate of a point
     */
    public int getX() {
        return x;
    }

    /**
     * This method return the y-coordinate of a point
     * @return y-coordinate of a point
     */
    public int getY() {
        return y;
    }

    /**
     * This method set a new x-coordinate for a point
     * @param x new x-coordinate for a point
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * This method set a new y-coordinate for a point
     * @param y new y-coordinate for a point
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * This method compare the location of two points
     * @param point the location of a point that is going to be compared
     * @return whether they are located in the same location
     */
    public boolean equals(Point point) {
        if (this.x == point.x && this.y == point.y) {
            return true;
        }
        else {
            return false;
        }
    }
}
