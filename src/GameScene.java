import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

class GameScene extends Scene {
    private Camera camera;
    private StaticThing scoreLabel;
    private StaticThing numberLabel;
    private int numberOfLives;
    private Hero hero;
    private long lastUpdateTime = 0;

    private List<ImageView> backgroundImages = new ArrayList<>();
    private int imageCount = 50;
    private int timerCounter = 0;
    private int addImageInterval = 60;

    public GameScene(Group root, Camera camera) {
        super(root, 800, 600);
        this.camera = camera;
        this.numberOfLives = 3;

        initializeBackgroundImages();

        scoreLabel = new StaticThing(20, 20, "file:C:\\Users\\chabn\\Downloads\\Ressources audio et image pour le runner-20231204\\img\\score.png");
        numberLabel = new StaticThing(20, 20, "file:C:\\Users\\chabn\\Downloads\\Ressources audio et image pour le runner-20231204\\img\\nbre.png");

        scoreLabel.getImageView().setTranslateX(0);
        numberLabel.getImageView().setTranslateX(600);

        ((Group) this.getRoot()).getChildren().addAll(scoreLabel.getImageView(), numberLabel.getImageView());

        initializeLivesDisplay();

        hero = new Hero(115, 415, "file:C:\\Users\\chabn\\Downloads\\Ressources audio et image pour le runner-20231204\\img\\heros.png", AnimatedThing.getSituation());
        root.getChildren().add(hero.getSprite());

        AnimationTimer timer = new AnimationTimer() {
            public void handle(long time) {
                if (lastUpdateTime == 0) {
                    lastUpdateTime = time;
                    return;
                }

                hero.update();
                updateGame();
                updateBackgroundImages();
                handleImageAdding();
            }
        };

        this.setOnMouseClicked(event -> {
            hero.jump();
        });

        timer.start();
    }

    private void initializeBackgroundImages() {
        Image mapImage = new Image("file:C:\\Users\\chabn\\Downloads\\Ressources audio et image pour le runner-20231204\\img\\desert.png");

        for (int i = 0; i < imageCount; i++) {
            ImageView imageView = new ImageView(mapImage);
            imageView.setLayoutX(mapImage.getWidth() * i);
            imageView.setFitWidth(800);
            imageView.setFitHeight(600);

            backgroundImages.add(imageView);
            ((Group) this.getRoot()).getChildren().add(imageView);
        }
    }

    private void initializeLivesDisplay() {
        double totalWidth = numberOfLives * 30;
        double startX = 240;

        for (int i = 0; i < numberOfLives; i++) {
            StaticThing heart = new StaticThing(20, 20, "file:C:\\Users\\chabn\\Downloads\\Ressources audio et image pour le runner-20231204\\img\\heart.jpg");
            heart.getImageView().setTranslateX(startX + i * 30);
            heart.getImageView().setTranslateY(10);
            ((Group) this.getRoot()).getChildren().add(heart.getImageView());
        }
    }

    private void updateGame() {
        GameScene.update(camera);
    }

    private void updateBackgroundImages() {
        for (ImageView imageView : backgroundImages) {
            imageView.setLayoutX(imageView.getLayoutX() - 10);
        }
    }

    private void handleImageAdding() {
        if (timerCounter >= addImageInterval) {
            Image mapImage = new Image("file:C:\\Users\\chabn\\Downloads\\Ressources audio et image pour le runner-20231204\\img\\desert.png");
            ImageView newImage = new ImageView(mapImage);
            newImage.setLayoutX(mapImage.getWidth() * imageCount);
            backgroundImages.add(newImage);
            ((Group) this.getRoot()).getChildren().add(newImage);
            imageCount++;
            timerCounter = 0;
        } else {
            timerCounter++;
        }
    }

    public static void update(Camera camera) {
        camera.setX(camera.getX() + 1);
    }

    public Camera getGameCamera() {
        return camera;
    }

    public void render() {
        double cameraX = camera.getX();
        double width = scoreLabel.getImageView().getFitWidth();
        double lbX = -cameraX % width;
        double rbX = width + lbX;
        scoreLabel.getImageView().setX(lbX);
        scoreLabel.getImageView().setY(0);
        numberLabel.getImageView().setX(rbX);
        numberLabel.getImageView().setY(0);
    }
}
