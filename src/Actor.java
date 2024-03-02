import bagel.*;

/**
 * This class represents an Actor
 * Actor is only a generalization of many types of actor appearing in the ShadowLife
 */
public abstract class Actor {
    private final String name;
    private final Image image;
    private Point position;

    /**
     * This method construct a new object of actor
     * @param name The name for actor
     * @param position The position for actor
     * @param imagePath The image resource location
     */
    public Actor(String name, Point position, String imagePath) {
        this.name = name;
        this.position = position;
        image = new Image(imagePath);
    }

    /**
     * This method draw the image of actor
     */
    public void draw() {
        image.drawFromTopLeft(position.getX(), position.getY());
    }

    /**
     * This method move one actor
     */
    public void move() {}

    /**
     * This method check what types of actor the current actor is standing on and perform corresponding actions
     * @param other The other actor that my actor is standing on
     */
    public void standOn(Actor other) {};

    /**
     * This method get the name of actor
     * @return The name of actor
     */
    public String getName() {
        return name;
    }

    /**
     * This method get the position of actor
     * @return The position of actor
     */
    public Point getPosition() {
        return position;
    }
}
