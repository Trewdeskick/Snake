import javafx.geometry.Point2D;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The Food class handles the logic for Food spawns
 *
 * @version 0.1.0
 * @author BMO
 */
public class Food {
private final Point2D[][] grid;
//private final ThreadLocalRandom rand = ThreadLocalRandom.current();

    public Food(Point2D[][] g) {
        this.grid = g;
    }

    public Point2D initialSpawn() {
        double x = grid[13][0].getX(); // fix magic numbers
        double y = grid[0][10].getY(); // fix fix fix
        return new Point2D(x,y);
    }
}