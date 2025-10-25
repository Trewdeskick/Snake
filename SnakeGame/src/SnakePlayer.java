package src;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * SnakePlayer renders the game logic handled via the Snake class.
 * Currently, turns grid points into: a grid, the Snake itself, and
 * a piece of food.
 * NEEDS: to remove food on collection, and handle snake growth.
 *
 * @version 0.3.0
 * @author BMO
 */
public class SnakePlayer extends Application {
    final private StackPane root = new StackPane();
    final private Pane gridLayer = new Pane();
    final private Pane fruitLayer = new Pane();
    final private Pane snakeLayer = new Pane();

    final double size = 800.0;
    final int cols = 20;
    final int rows = 20;

    private final int headX = (int) size /2;
    private final int headY = (int) size /2;

    private final List<Point2D> body = new ArrayList<>();
    private final Snake snake = new Snake(headX, headY, body);
    private final List<Node> snakeBody = new ArrayList<>();
    private final Food fruit = new Food();
    private final Logic game = new Logic(snake, fruit);

    private Direction current = null;
    
    @Override
    public void start(Stage primaryStage) {


        // Visual setup
        Scene scene = new Scene(root, size, size, Color.BLACK);


        gridLayout();
        renderSnake();
        renderMoves();

        KeyFrame kf = new KeyFrame(Duration.millis(50), _ -> {
            game.setDirection(current);
            renderSnake();
        });
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        root.getChildren().addAll(gridLayer, fruitLayer, snakeLayer);
        primaryStage.setTitle("Snake");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        root.setFocusTraversable(true);
        root.requestFocus();

    }

    /**
     * Handler to call movement logic from the Snake class
     */
    private void renderMoves() {
        root.setOnKeyPressed(event -> {
            KeyCode press = event.getCode();
            switch (press) {
                case UP:
                    current = Direction.UP;
                    break;
                case DOWN:
                    current = Direction.DOWN;
                    break;
                case LEFT:
                    current = Direction.LEFT;
                    break;
                case RIGHT:
                    current = Direction.RIGHT;
                    break;
            }
        });

    }

    /**
     * Uses an enhanced for loop to render the snake, and clears
     * the previous snake from the snakeLayer to simulate movement
     */
    private void renderSnake() {
        // could this be handled better?
        snakeLayer.getChildren().clear();

        List<Point2D> snakePoints = snake.createSnake();
        snakeBody.clear();

        // this should scale with added body Nodes
        for (Point2D p : snakePoints) {
            Rectangle segment = new Rectangle(size / cols, size / rows);
            segment.setX(p.getX());
            segment.setY(p.getY());

            segment.setFill(Color.BLACK);
            snakeBody.add(segment);

        }
        snakeLayer.getChildren().addAll(snakeBody);
    }

    /**
     * Uses a 2D array of Point2D to render a grid.
     */
    private void gridLayout() {
        Rectangle render;

        Point2D[][] grid = game.createGrid(rows, cols, size);


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
                gridLayer.getChildren().addAll(render);
            }
        }
        fruitLayer.getChildren().add(renderInitialFruit());
    }

    /**
     * Sets initial fruit spawn, could modularize via setting a first spawn
     * in the logic for the Food itself
      */
    private Rectangle renderInitialFruit() {
        Rectangle fruit = new Rectangle(size / cols - 10, size / rows - 10);
        fruit.setX(game.spawnFruit().getX() + 5);
        fruit.setY(game.spawnFruit().getY() + 5);
        fruit.setFill(Color.RED);
        return fruit;
    }



    public static void main(String[] args) {
        launch(args);
    }
}