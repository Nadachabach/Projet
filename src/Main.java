
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameScene gameScene = new GameScene(new Group(),new Camera(800, 600));

        primaryStage.setTitle("Game App");
        primaryStage.setScene(gameScene);
        primaryStage.show();
        primaryStage.setResizable(false);
        gameScene.render();

    }
}