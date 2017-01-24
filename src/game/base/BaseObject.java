package game.base;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by hehef on 2017/1/21.
 */
public abstract class BaseObject {

    protected int width;
    protected int height;
    protected int posX;
    protected int posY;


    public BaseObject() {
        this.width = 0;
        this.height = 0;
        this.posX = 0;
        this.posY = 0;

    }

    public BaseObject(int posX, int posY, int width, int height) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
    }
    public abstract void draw(GraphicsContext gc);
    public abstract void update(long currentTime);
    public boolean isInterset(BaseObject obj){
        if(posX+ width>obj.getPosX()&& getPosX()<obj.getPosX()+obj.getWidth()&&
                posY+ height>obj.getPosY()&& posY<obj.getPosY()+obj.getHeight())
            return true;
        return false;
    }
    public void moveXPos(int x){
        posX+=x;

    }
    public void moveYPos(double y){
        posY+=y;

    }
    //getter setter


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
