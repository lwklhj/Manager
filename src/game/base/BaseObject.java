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

    protected DoubleProperty width;
    protected DoubleProperty height;
    protected DoubleProperty x;
    protected DoubleProperty y;



    public BaseObject() {
        width=new SimpleDoubleProperty(0);
        height=new SimpleDoubleProperty(0);
        x=new SimpleDoubleProperty(0);
        y=new SimpleDoubleProperty(0);

    }

    public BaseObject(double x, double y, double width, double height) {
        this.width=new SimpleDoubleProperty(width);
        this.height=new SimpleDoubleProperty(height);
        this.x=new SimpleDoubleProperty(x);
        this.y=new SimpleDoubleProperty(y);
    }
    public abstract void draw(GraphicsContext gc);
    public abstract void update(long currentTime);
    public boolean isInterset(BaseObject obj){
        if(getX()+ getWidth()>obj.getX()&& getX()<obj.getX()+obj.getWidth()&&
               getY()+ getHeight()>obj.getY()&& getY()<obj.getY()+obj.getHeight())
            return true;
        return false;
    }
    public void moveX(int x){
        setX(getX()+x);



    }
    public void moveY(double y){
        setY(getY()+y);


    }
    //getter setter


    public double getWidth() {
        return width.get();
    }

    public DoubleProperty widthProperty() {
        return width;
    }

    public void setWidth(double width) {
        this.width.set(width);
    }

    public double getHeight() {
        return height.get();
    }

    public DoubleProperty heightProperty() {
        return height;
    }

    public void setHeight(double height) {
        this.height.set(height);
    }

    public double getX() {
        return x.get();
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public void setX(double x) {
        this.x.set(x);
    }

    public double getY() {
        return y.get();
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public void setY(double y) {
        this.y.set(y);
    }
}
