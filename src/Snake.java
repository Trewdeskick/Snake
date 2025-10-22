import javafx.geometry.Point2D;
import javafx.scene.Node;
import java.util.ArrayList;
import java.util.List;

/**
 * A logic class for the game of Snake
 * In Snake the goal of the player is to collect fruit and make
 * the Snake grow until no more spaces are available on the board.
 * If the player collides the 'head' of the snake into the walls or
 * the body of the Snake itself, they have lost.
 *
 * @version 0.1.0
 * @author BMO
 */
public class Snake extends Node {
/*
When Player calls this it's only for the logic, not the constructor
It needs to access the (x,y) of the head and body.
 */
    private final double headX;
    private final double headY;
    private Point2D[][] grid;

    /**
     * Constructor for the logic class to be used in rendering
     *
     * @param headX a double representing the current X coordinate of the head
     * @param headY a double representing the current Y coordinate of the head
     */
    public Snake(double headX, double headY) {
        this.headX = headX;
        this.headY = headY;
    }

    /**
     * Uses headX and headY to create the points for the Snake
     *
     * @return the list of coordinates for body positions
     */
    public List<Point2D> createSnake() {
        /*
        Requires further work for when body points are added
         */

        List<Point2D> body = new ArrayList<>();

        body.add(new Point2D(headX, headY));
        body.add(new Point2D(headX - 40, headY));
        body.add(new Point2D(headX - 80, headY));

        return body;
    }

    /**
     * Creates a grid for rendering and movement logic.
     *
     * @param r rows
     * @param c columns
     * @param s size of Pane, the pane is square so both height and width
     *          are equal
     * @return an array of 2-Dimensional points for each valid space in the grid
     */
    public Point2D[][] createGrid(int r, int c, double s) {
        grid = new Point2D[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                grid[i][j] = new Point2D((s/r) * i, (s/c) * j);
            }
        }
        return grid;
    }

    /**
     * Method spawns fruit, in current version only handles the first spawn
     *
     * @return the coordinates to place the initial fruit
     */
    public Point2D spawnFruit() {
        Food food = new Food(this.grid);
        return food.initialSpawn();
    }


    /**
     * coming in a future version
     */
    private void grow() {

    }

}
