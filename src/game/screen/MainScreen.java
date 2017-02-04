package game.screen;

import game.base.*;
import game.util.SystemConfiguration;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by hehef on 2017/1/21.
 */
public class MainScreen extends GameScreen{
    private int score;
    private int groundHeight=80;
    private Player player;
    private int minY;
    private int maxY;

    private ArrayList<Pipe> pipeList =new ArrayList<>();
    private ArrayList<Ground> groudList=new ArrayList<>();

    private Image playerImage;
    private Image backgroundImage;
    private Image groundImage;
    private Text scoreText;

    public MainScreen(double width, double height) {
        super(width, height);
    }

    @Override
    public  void initiation() {
        player=new Player(50,(int)(getWidth()/2),50,50,playerImage);
        player.setScene(getParent().getScene());

        //init ground
        int numOfGround=SystemConfiguration.getStageWidth()/50+1;
        int x=0;

        int y=SystemConfiguration.getStageHeight()-groundHeight;
        int width=50;
        for(int i=0;i<numOfGround;i++){
            Ground ground=new Ground(x,y,width,groundHeight,groundImage);
            groudList.add(ground);
            x+=width;
        }
        scoreText=new Text("0");
        minY=100;
        maxY=SystemConfiguration.getStageHeight()-100;

        //init pipe
        int pipeX=SystemConfiguration.getStageWidth();
        int pipeY=getRandom();
        for(int i=0;i<2;i++){
            Pipe bottom=new Pipe(pipeX,pipeY,80,SystemConfiguration.getStageHeight()-pipeY);

            //Pipe top=new Pipe(pipeX,pipeY,80,);

            pipeList.add(bottom);
            pipeList.add(top);

        }
    }

    @Override
    protected void loadContent() {
        playerImage=new Image(getClass().getResource("../../image/user.png").toString(),true);
        backgroundImage=new Image(getClass().getResource("../../image/gamebackground.png").toString(),true);
        groundImage=new Image(getClass().getResource("../../image/stage_ground.png").toString(),true);






    }

    @Override
    protected void update(long currentTime) {
        scoreText.setText(""+score);


        player.update(currentTime);
        for(BaseObject obj: pipeList){
            obj.update(currentTime);
            if(obj.isInterset(player)){
                //stop();
            }
            if(obj.getX()+obj.getWidth()<=0){
                pipeList.remove(obj);

            }
        }
        for(Ground g:groudList){
            g.update(currentTime);
            if(g.getX()+g.getWidth()<=0){
                g.setX(SystemConfiguration.getStageWidth());
            }
            if(g.isInterset(player)){
                player.setY(SystemConfiguration.getStageHeight()-groundHeight-player.getHeight());
            }
        }


    }

    @Override
    protected void draw(GraphicsContext gc) {

        gc.drawImage(backgroundImage,0,0, SystemConfiguration.getStageWidth(),SystemConfiguration.getStageHeight()-groundHeight);
        for(Ground g:groudList) g.draw(gc);

        //back object in first
        player.draw(gc);
        for(BaseObject obj: pipeList){
            obj.draw(gc);
        }


    }
    private int getRandom(){
        return ThreadLocalRandom.current().nextInt(minY, maxY + 1);

    }





}
