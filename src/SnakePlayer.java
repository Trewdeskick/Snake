import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;

/**
 * SnakePlayer renders the game logic handled via the Snake class.
 * Currently, turns grid points into: a grid, the Snake itself, and
 * a piece of food.
 *
 * NEEDS: to remove food on collection, and handle snake growth.
 * As well as a Timeline for movement.
 *
 * @version 0.1.0
 * @author BMO
 */
public class SnakePlayer extends Application {
    final double size = 800.0;
    final int cols = 20;
    final int rows = 20;
    private final int headX = (int) size /2;
    private final int headY = (int) size /2;
    private final Snake snake = new Snake(headX, headY);
    
    @Override
    public void start(Stage primaryStage) {





        // Visual setup
        Pane root = new Pane();
        Scene scene = new Scene(root, size, size, Color.BLACK);

        gridLayout(root);
        renderSnake(root);

        Stage stage = new Stage();
        stage.setTitle("Snake");
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();

    }

    private void renderSnake(Pane pane) {
        List<Point2D> snakePoints = snake.createSnake();
        // could this be handled better?
        int i = -1;

        // this should scale with added body Nodes
        for (Point2D p : snakePoints) {
            Rectangle segment = new Rectangle(size / cols, size / rows);
            i++;
            segment.setX(p.getX());
            segment.setY(p.getY());
            if (i % 2 == 0) {
                segment.setFill(Color.BLACK);
            } else {
                segment.setFill(Color.BLACK.brighter());
            }
            pane.getChildren().add(segment);
        }
    }


    /* un-void this if adding array to grid storage later
    still a possible thought, although I think the Snake class
    can handle any problem this would solve itself
     */

    /**
     * Uses a 2D array of Point2D to render a grid.
     *
     * @param pane the window to add elements to
     */
    private void gridLayout(Pane pane) {
        Rectangle render;

        Point2D[][] grid = snake.createGrid(rows, cols, size);

        // creates grid. uses (i+j) % 2 to create the checkerboard pattern
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((i + j) % 2 == 0) {
                    render = new Rectangle(size / cols, size / rows, Color.FORESTGREEN);
                } else {
                    render = new Rectangle(size / cols, size / rows, Color.PALEGREEN);
                }
                render.setX(grid[i][j].getX());
                render.setY(grid[i][j].getY());
                pane.getChildren().addAll(render);
            }
        }
        // is this the correct place for this call?
        pane.getChildren().add(renderInitialFruit());
    }

    /**
     * Sets initial fruit spawn, could modularize via setting a first spawn
     * in the logic for the Food itself
      */
    private Rectangle renderInitialFruit() {
        Rectangle fruit = new Rectangle(size / cols - 10, size / rows - 10);
        fruit.setX(snake.spawnFruit().getX() + 5);
        fruit.setY(snake.spawnFruit().getY() + 5);
        fruit.setFill(Color.RED);
        return fruit;
    }



    public static void main(String[] args) {
        launch(args);
    }
}