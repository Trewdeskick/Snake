package src;
import javafx.geometry.Point2D;

/**
 * The Food class handles the logic for Food spawns
 *
 * @version 0.1.0
 * @author BMO
 */
public class Food {
//private final ThreadLocalRandom rand = ThreadLocalRandom.current();

    public Food() {
    }

    public Point2D initialSpawn(Point2D[][] grid) {
        double x = grid[13][0].getX(); // fix magic numbers
        double y = grid[0][10].getY(); // fix fix fix
        return new Point2D(x,y);
    }
}
