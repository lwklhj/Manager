package game.base;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/**
 * Created by hehef on 2017/1/21.
 */
public class Player extends BaseObject {
    private Scene scene;
    private Image image;

    private long lastTime;

    private int acc=2;
    private int verticalSpeed;
    private final int MAX_SPEED=3;
    private int upSpeed=-16;

    private double gravity=9.8;
    private double framePerSec= Duration.seconds(0.017).toSeconds();






    public Player() {
    }

    public Player(int posX,int posY,int width, int height) {
        super( posX, posY,width, height);
        image=new Image(getClass().getResource("../../image/user.png").toString(),true);

    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image, getPosX(), getPosY(), getWidth(), getHeight());

    }

    @Override
    public void update(long currentTime) {


        //当前速度 = 初始速度 + 加速度 x dt
       // 当前位置 = 初始位置 + 速度 x dt
        //speed+=
        // height = 0.5*gravity*time*time+speed*time
        //double deltaTime=(currentTime-lastTime)/1_000_000_000.0;//for m

        //lastTime=currentTime;
        /*if(lastTime>0) deltaTime=(currentTime-lastTime)/ns;
        else deltaTime=0;*/
        verticalSpeed+=acc;
        if(verticalSpeed>MAX_SPEED){
            verticalSpeed=MAX_SPEED;
        }
        //double height=0.5*gravity*framePerSec*framePerSec+speed*framePerSec;
        //System.out.println(height);
        posY+=verticalSpeed;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        scene.setOnKeyPressed(event -> onKeyPressed(event));
    }
    private void onKeyPressed(KeyEvent event){
        switch(event.getCode()){
            case RIGHT:
                moveXPos(5);
                break;
            case LEFT:
                moveXPos(-1);
                break;
            case UP:
                moveYPos(-1);
                break;
            case DOWN:
                moveYPos(+1);
                break;
            case SPACE:
                verticalSpeed=upSpeed;
                break;

        }
    }

}
