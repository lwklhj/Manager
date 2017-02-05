package game.base;

import game.util.SystemConfiguration;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Created by hehef on 4/2/2017.
 */
public class TopPipe extends BaseObject{
    private boolean relocation;
    private boolean score;

    private Image image;

    public TopPipe() {
    }

    public TopPipe(int posX, int posY, int width, int height, Image image) {
        super(posX, posY, width, height);
        this.image=image;
        score=true;
        relocation=false;



    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image,getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void update(long currentTime) {
        moveX(-1);
        if(getWidth()+getX()<=0){
            setX(SystemConfiguration.getStageWidth());
            relocation=true;
            score=true;


        }
    }

    public boolean isRelocation() {
        return relocation;
    }

    public void setRelocation(boolean relocation) {
        this.relocation = relocation;
    }

    public boolean isScore() {
        return score;
    }

    public void setScore(boolean score) {
        this.score = score;
    }
}
