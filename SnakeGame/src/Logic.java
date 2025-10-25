package src;
import javafx.geometry.Point2D;

/**
 * A class to handle the game logic for snake.
 * Stores the grid, snake, and food for use in
 * the SnakePlayer class
 *
 * @version 0.2.0
 * @author BMO
 */
public class Logic {
    private Point2D[][] grid;
    private Snake snake;
    private Food food;

    /**
     * Default constructor for Logic
     */
    public Logic(Snake snake, Food food) {
        this.snake = snake;
        this.food = food;
    }

    public boolean gameOver() {
        if (snakeCollision()) {
            return true;
        }
        return false;
    }

    private boolean snakeCollision() {
        return false;
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
        return food.initialSpawn(grid);
    }

    public void setDirection(Direction direction) {
        snake.movement(direction);
    }
}
