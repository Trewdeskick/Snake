package src;
/**
 * A class to handle the game logic for snake. As of now
 * mainly stores a constant grid to be used.
 *
 * @version 0.1.0
 * @author BMO
 */
public class Logic {
    private Point2D[][] grid;

    /**
     * Default constructor for Logic
     */
    public Logic() {

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
}
