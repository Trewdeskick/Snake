package src;
import javafx.geometry.Point2D;
import java.util.List;

/**
 * A class to handle the game logic for snake.
 * Stores the grid, snake, and food for use in
 * the SnakePlayer class
 *
 * @version 0.3.0
 * @author BMO
 */
public class Logic {
    private Point2D[][] grid;
    private final Snake snake;
    private final Food food;
    private Direction previous;
    private int firstFruit = 0;

    /**
     * Constructor for Logic
     *
     * @param snake the Snake object
     * @param food the Food object
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

    /**
     * Checks if the head of the snake has the same coordinates as
     * the body
     *
     * @return true if snake collides with itself
     */
    private boolean snakeCollision() {
        List<Point2D> currentBody = snake.createSnake();
        for (int i = 2; i < currentBody.size(); i++) {
            if (currentBody.getFirst().getX() == currentBody.get(i).getX() &&
                    currentBody.getFirst().getY() == currentBody.get(i).getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if fruit has been collided with
     *
     * @return true if the head touches the fruit
     */
    public boolean fruitCheck() {
        List<Point2D> currentBody = snake.createSnake();
        return currentBody.getFirst().getX() == food.getLocation().getX() &&
                currentBody.getFirst().getY() == food.getLocation().getY();
    }

    /**
     * Spawns fruit in same or new location dependent on if the fruit has been collided
     * with
     *
     * @return an (x,y) coordinate representing the next fruit location
     */
    public Point2D fruitCollision() {
        if (fruitCheck()) {
            snake.grow();
            food.randomSpawn(grid);
            return food.getLocation();
        }
        return food.getLocation();
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
        if (firstFruit < 1) {
            firstFruit++;
            return food.initialSpawn(grid);
        } else {
            return food.randomSpawn(grid);
        }
    }

    /**
     * Sets the direction of the Snake
     *
     * @param direction an Enumerated type representing the direction
     */
    public void setDirection(Direction direction) {
        if (isValidMove(direction, previous)) {
            previous = snake.movement(direction);
        } else {
            // IF REMOVED ALLOWS SNAKE TO BREAK: USE TO DEBUG
            snake.movement(previous);
        }
    }

    /**
     * Returns true if the move is valid to be used in setDirection.
     *
     * @param direction an Enumerated type representing the direction
     * @param prev an Enumerated type representing the previous direction
     * @return true if the move is valid, false if not
     */
    private boolean isValidMove(Direction direction, Direction prev) {
        if (prev == Direction.UP && direction == Direction.DOWN) {
            return false;
        } else if (prev == Direction.DOWN && direction == Direction.UP) {
            return false;
        } else if (prev == Direction.LEFT && direction == Direction.RIGHT) {
            return false;
        } else if (prev == Direction.RIGHT && direction == Direction.LEFT) {
            return false;
        } else {
            return true;
        }
    }
}
