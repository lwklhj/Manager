package game.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by hehef on 4/2/2017.
 */
public class BottomPipe extends BaseObject{
    public BottomPipe() {
    }

    public BottomPipe(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.DARKGREEN);
        gc.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void update(long currentTime) {
        moveX(-1);

    }
}
