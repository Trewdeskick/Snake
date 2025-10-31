package src;
import javafx.geometry.Point2D;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The Food class handles the logic for Food spawns
 *
 * @version 0.2.0
 * @author BMO
 */
public class Food {
    private Point2D location;
private final ThreadLocalRandom rand = ThreadLocalRandom.current();

    public Food() {
    }

    public Point2D initialSpawn(Point2D[][] grid) {
        double x = grid[13][0].getX(); // fix magic numbers
        double y = grid[0][10].getY(); // fix fix fix
        return location = new Point2D(x,y);
    }

    public Point2D randomSpawn(Point2D[][] grid) {
        int randX = rand.nextInt(0, 20);
        int randY = rand.nextInt(0,20);

        double x = grid[randX][randY].getX();
        double y = grid[randX][randY].getY();

        return location = new Point2D(x,y);
    }

    public Point2D getLocation() {
        return location;
    }
}
