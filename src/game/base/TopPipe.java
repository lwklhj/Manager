package game.base;

import game.util.SystemConfiguration;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by hehef on 2017/1/21.
 */
public class TopPipe extends BaseObject {
    public TopPipe() {
    }

    public TopPipe(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.AQUA);
        gc.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void update(long currentTime) {
        moveX(-1);

    }
}
