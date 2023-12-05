import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;

public abstract class AnimatedThing {
    private double positionX;
    private double positionY;
    private ImageView sprite;
    private double animationSpeed;

    private int currentFrame;
    private int totalFrames;
    private double frameWidth;
    private double frameHeight;
    private static String currentSituation;

    public AnimatedThing(double x, double y, String fileName, String situation) {
        this.positionX = x;
        this.positionY = y;
        switch (situation) {
            case "run":
                this.animationSpeed = 0;
                this.currentFrame = 0;
                this.totalFrames = 6;
                this.frameWidth = 85;
                this.frameHeight = 100;
                break;
            case "jump":
                this.animationSpeed = 1.6;
                this.currentFrame = 0;
                this.totalFrames = 2;
                this.frameWidth = 85;
                this.frameHeight = 100;
                break;
            case "shoot":
                this.animationSpeed = 3.3;
                this.currentFrame = 0;
                this.totalFrames = 6;
                this.frameWidth = 82;
                this.frameHeight = 100;
                break;
            case "jShoot":
                this.animationSpeed = 4.92;
                this.currentFrame = 0;
                this.totalFrames = 2;
                this.frameWidth = 85;
                this.frameHeight = 100;
                break;
        }

        Image spriteSheet = new Image(fileName);
        sprite = new ImageView(spriteSheet);

        updateFrame();

        sprite.setX(positionX);
        sprite.setY(positionY);
    }

    public void setSituation(String situation) {
        currentSituation = situation;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void jump() {
        if (getY() == 415) {
            setY(315);
            sprite.setY(getY());
        }
    }

    public void back() {
        sprite.setY(415);
    }

    public double getX() {
        return positionX;
    }

    public void setX(double x) {
        this.positionX = x;
    }

    public void setY(double y) {
        this.positionY = y;
    }

    public double getY() {
        return positionY;
    }

    public void updateFrame() {
        sprite.setViewport(new Rectangle2D(currentFrame * frameWidth, animationSpeed * frameHeight, frameWidth, frameHeight));
    }

    public void update() {
        currentFrame = (currentFrame + 1) % totalFrames;
        // TranslateTransition
        for (int i = 0; i < 10000000; i++) {
            currentFrame = (currentFrame) % totalFrames;
        }
        updateFrame();
    }

    public static String getSituation() {
        System.out.println(currentSituation);
        if (currentSituation == null) return "run";
        return currentSituation;
    }
}
