package game.base;

import game.util.SystemConfiguration;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by hehef on 2017/1/21.
 */
public class Rect extends BaseObject {
    public Rect() {
    }

    public Rect(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.AQUA);
        gc.fillRect(getPosX(), getPosY(), getWidth(), getHeight());
    }

    @Override
    public void update(long currentTime) {
        moveXPos(-1);


    }
}
