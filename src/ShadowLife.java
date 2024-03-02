import bagel.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * This class demonstrates a ShadowLife world where each actors are display and perform their actions
 */
public class ShadowLife extends AbstractGame {
    /**
     * The length of 1 tile
     */
    public static final int TILELENGTH = 64;
    private static final int NAMELOCATION = 0;
    private static final int XLOCATION = 1;
    private static final int YLOCATION = 2;
    private static final String BACKGROUNDPATH = "res/images/background.png";
    private static final String TIMEDOUT = "Timed out";
    private int tickRate;
    private int tickCount;
    private int maxTick;
    private String worldPath;
    private Image background;
    private long prevTime;
    private ArrayList<Actor> actors = new ArrayList<Actor>();
    private ArrayList<Actor> duplicates = new ArrayList<Actor>();


    /**
     * This method check whether current actor is standing on other actors
     * @param actor the actor we want to check
     * @return the actor that is being standed by our input actor
     */
    public Actor checkStandOn (Actor actor) {
        // check any actors currently share the same position with gatherer/thief
        for (Actor other : actors) {
            if (other.getPosition().equals(actor.getPosition())) {
                if (actor.getName().equals(Gatherer.NAME)) {
                    if (!(other.getName().equals(Gatherer.NAME) || other.getName().equals(Thief.NAME))) {
                        return other;
                    }
                }
                else if (actor.getName().equals(Thief.NAME)) {
                    if (!other.getName().equals(Thief.NAME)) {
                        return other;
                    }
                }
            }
        }
        return null;
    }

    /**
     * This method perform mitosis pool functionality for gatherer/thief
     */
    public void mitosis () {
        for (Actor actor : duplicates) {
            // Destroy old actor
            actors.remove(actor);

            // extract information of old actor
            int oldDirection = ((ActiveActor) actor).getDirection();
            int newDirection1 = (oldDirection + Sign.NINETY) % Sign.MAXDIRECTION;
            int newDirection2 = (oldDirection + Sign.TWOHUNDREDSEVENTY) % Sign.MAXDIRECTION;
            Point position1 = new Point(actor.getPosition());
            Point position2 = new Point(actor.getPosition());
            ActiveActor actor1;
            ActiveActor actor2;

            // detect the types of actor
            if (actor.getName().equals(Gatherer.NAME)) {
                actor1 = new Gatherer(position1, newDirection1);
                actor2 = new Gatherer(position2, newDirection2);
            }
            else {
                actor1 = new Thief(position1, newDirection1);
                actor2 = new Thief(position2, newDirection2);
            }

            // get new actors out of pool
            actor1.move();
            actor2.move();
            // preserve them
            actors.add(actor1);
            actors.add(actor2);
        }
        duplicates.clear();
    }


    /**
     * This static method is provided by Eleanor McMurtry and aims to provide alternatives of command line arguments
     * @return The parsed contents of args.txt
     */
    private static String[] argsFromFile() {
        try {
            return Files.readString(Path.of("args.txt"), Charset.defaultCharset()) .split(" ");
        } catch (IOException e) { e.printStackTrace();
        }
        return null;
    }

    /**
     * This method store information from args.txt
     * @param data the The parsed contents of args.txt
     */
    public void processCommandline(String[] data) {
        // prevent wrong format of input
        if (data.length != 3) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }
        try{
            tickRate = Integer.parseInt(data[0]);
            maxTick = Integer.parseInt(data[1]);
        } catch (NumberFormatException e) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }
        if (tickRate < 0 || maxTick < 0) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }
        worldPath = data[2];
    }

    /**
     * This method read world information from csv file
     */
    public void loadWorld() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(worldPath))) {
            String line;
            // parse each line and store their information by creating new actors
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.out.println(String.format("error: in file \"%s\" at line %d", worldPath, count));
                    System.exit(-1);
                }
                String type = parts[0];
                int x = 0;
                int y = 0;
                try{
                    x = Integer.parseInt(parts[1]);
                    y = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e) {
                    System.out.println(String.format("error: in file \"%s\" at line %d", worldPath, count));
                    System.exit(-1);
                }
                if (x < 0 || y < 0) {
                    System.out.println(String.format("error: in file \"%s\" at line %d", worldPath, count));
                    System.exit(-1);
                }
                Point position = new Point(x, y);
                switch (type) {
                    case Tree.NAME:
                        actors.add(new Tree(position));
                        break;
                    case GoldenTree.NAME:
                        actors.add(new GoldenTree(position));
                        break;
                    case Stockpile.NAME:
                        actors.add(new Stockpile(position));
                        break;
                    case Hoard.NAME:
                        actors.add(new Hoard(position));
                        break;
                    case Pad.NAME:
                        actors.add(new Pad(position));
                        break;
                    case Fence.NAME:
                        actors.add(new Fence(position));
                        break;
                    case MitosisPool.NAME:
                        actors.add(new MitosisPool(position));
                        break;
                    case SignUp.NAME:
                        actors.add(new SignUp(position));
                        break;
                    case SignDown.NAME:
                        actors.add(new SignDown(position));
                        break;
                    case SignLeft.NAME:
                        actors.add(new SignLeft(position));
                        break;
                    case SignRight.NAME:
                        actors.add(new SignRight(position));
                        break;
                    case Gatherer.NAME:
                        actors.add(new Gatherer(position));
                        break;
                    case Thief.NAME:
                        actors.add(new Thief(position));
                        break;
                    default:
                        System.out.println(String.format("error: in file \"%s\" at line %d", worldPath, count));
                        System.exit(-1);
                }
                count += 1;
            }
        } catch (IOException e) {
            System.out.println(String.format("error: file \"%s\" not found", worldPath));
            System.exit(-1);
        }
    }

    /**
     * This method check whether all gatherers/thiefs are inactive
     * @return whether all gatherers/thiefs are inactive
     */
    public boolean checkInactive() {
        for (Actor actor : actors) {
            if (actor.getName().equals(Gatherer.NAME) || actor.getName().equals(Thief.NAME)) {
                ActiveActor one = (ActiveActor) actor;
                if (one.isActive()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method print final results after the all actors are inactive
     */
    public void printResult() {
        Window.close();
        System.out.println(String.format("%d ticks", tickCount));
        // print fruit number in stockpiles and hoards
        for (Actor actor : actors) {
            if (actor.getName().equals(Stockpile.NAME)) {
                System.out.println(((Stockpile) actor).getStock());
            }
            else if (actor.getName().equals(Hoard.NAME)) {
                System.out.println(((Hoard) actor).getStock());
            }
        }
        System.exit(0);
    }

    /**
     * This method counstruct a new object of Shadowlife and initialise it
     */
    public ShadowLife() {
        background = new Image(BACKGROUNDPATH);
        prevTime = System.currentTimeMillis();
        tickCount = 0;
        processCommandline(argsFromFile());
        loadWorld();
    }

    /**
     * This method update the Shadowlife game window
     * @param input The input from keyboard
     */
    @Override
    protected void update(Input input) {
        // render all elements
        background.drawFromTopLeft(0, 0);
        for (Actor actor : actors) {
            actor.draw();
        }

        // check whether a tick passes
        if (tickCount <= maxTick) {
            if (System.currentTimeMillis() - prevTime > tickRate) {
                if (checkInactive()) {
                    printResult();
                }

                prevTime = System.currentTimeMillis();
                tickCount += 1;

                // move actors and perform standing on functionality
                for (Actor actor : actors) {
                    actor.move();
                    if (actor.getName().equals(Gatherer.NAME) || actor.getName().equals(Thief.NAME)) {
                        Actor other  = checkStandOn(actor);
                        // deal with mitosis pool functionality in shadowlife and deal with others in standOn method
                        if (other != null) {
                            if (!other.getName().equals(MitosisPool.NAME)) {
                                actor.standOn(other);
                            }
                            else {
                                duplicates.add(actor);
                            }
                        }
                    }
                }

                // detect whether perform mitosis
                if (!duplicates.isEmpty()) {
                    mitosis();
                }
            }
        }

        // the maximum tick has been reached
        else {
            System.out.println(TIMEDOUT);
            Window.close();
            System.exit(-1);
        }
    }

    /**
     * This is the main method which start our game
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new ShadowLife().run();
    }
}