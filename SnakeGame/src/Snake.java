package src;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import java.util.List;

/**
 * A logic class for the game of Snake
 * In Snake the goal of the player is to collect fruit and make
 * the Snake grow until no more spaces are available on the board.
 * If the player collides the 'head' of the snake into the walls or
 * the body of the Snake itself, they have lost.
 *
 * @version 0.2.0
 * @author BMO
 */
public class Snake {
/*
When Player calls this it's only for the logic, not the constructor
It needs to access the (x,y) of the head and body.
 */
    private final double headX;
    private final double headY;
    private List<Point2D> body = new ArrayList<>();

    /**
     * Constructor for the logic class to be used in rendering
     *
     * @param headX a double representing the current X coordinate of the head
     * @param headY a double representing the current Y coordinate of the head
     */
    public Snake(double headX, double headY, List<Point2D> body) {
        this.headX = headX;
        this.headY = headY;

        body.add(new Point2D(headX, headY));
        body.add(new Point2D(headX - 40, headY));
        body.add(new Point2D(headX - 80, headY));
        this.body = body;
    }

    /**
     * Controls how the Snake moves
     * STILL NEEDS: LOGIC CHECKS AND LOOP FOR CONTINUOUS MOVEMENT
     *
     * @param pressed the key that is pressed
     * @return a new list of points for the body
     */
    public List<Point2D> movement(KeyCode pressed) {
        double x = body.getFirst().getX();
        double y = body.getFirst().getY();
        int direction = 40;
            if (pressed == KeyCode.UP) {
                body.addFirst(new Point2D(x, y - direction));
                body.removeLast();
            } else if (pressed == KeyCode.DOWN) {
                body.addFirst(new Point2D(x, y + direction));
                body.removeLast();
            } else if (pressed == KeyCode.LEFT) {
                body.addFirst(new Point2D(x - direction, y));
                body.removeLast();
            } else if (pressed == KeyCode.RIGHT) {
                body.addFirst(new Point2D(x + direction, y));
                body.removeLast();
            }
        return body;
        }

    /**
     * Uses headX and headY to create the points for the Snake
     * for the initial positioning of the snake
     *
     * @return the list of coordinates for body positions
     */
    public List<Point2D> createSnake() {
        /*
        Requires further work for when body points are added
         */
        return body;
    }


    /**
     * coming in a future version
     */
    private void grow() {

    }

}
