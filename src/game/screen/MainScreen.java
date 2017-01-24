package game.screen;

import game.base.BaseObject;
import game.base.GameScreen;
import game.base.Player;
import game.base.Rect;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * Created by hehef on 2017/1/21.
 */
public class MainScreen extends GameScreen{
    private Player player;
    private ArrayList<BaseObject> objects=new ArrayList<>();
    private int time;
    private int maxTime=100;

    public MainScreen(double width, double height) {
        super(width, height);
    }

    @Override
    public  void initiation() {
        player=new Player(50,(int)(getWidth()/2),50,50);
        player.setScene(getParent().getScene());




    }

    @Override
    protected void loadContent() {

    }

    @Override
    protected void update(long currentTime) {
        time++;
        if(time>maxTime){
            time=0;
            int topHeight=rand(60,223);
            int bottomHeight=373-100-topHeight;
            objects.add(new Rect((int)getWidth(),topHeight,100,topHeight));
            objects.add(new Rect((int)getWidth(),bottomHeight,100,bottomHeight));

        }

        player.update(currentTime);
        for(BaseObject obj:objects){
            obj.update(currentTime);
            if(obj.isInterset(player)){
                System.out.print("interset");
            }
            if(obj.getPosX()+obj.getWidth()<=0){
                objects.remove(obj);

            }
        }


    }

    @Override
    protected void draw(GraphicsContext gc) {
        player.draw(gc);
        for(BaseObject obj:objects){
            obj.draw(gc);

        }


    }
    private int rand(int min,int max){
        return (int)(Math.random()*(max-min)+min);
    }




}
