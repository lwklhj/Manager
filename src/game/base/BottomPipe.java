package game.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by hehef on 5/2/2017.
 */
public class BottomPipe extends BaseObject {
    private Image image;
    public BottomPipe() {
    }

    public BottomPipe(double x, double y, double width, double height,Image image) {
        super(x, y, width, height);
        this.image=image;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image,getX(), getY(), getWidth(), getHeight());

    }

    @Override
    public void update(long currentTime) {
        moveX(-1);
    }
}
