import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Shopper here.
 * 
 * @author Albert 
 * @version May 2020
 */
public class Shopper extends Actor implements AnimationInterface
{

    private static GreenfootImage[] rightMvt1 = new GreenfootImage[4];
    private static GreenfootImage[] leftMvt1 = new GreenfootImage[4];
    private static GreenfootImage[] upMvt1 = new GreenfootImage[4];
    private static GreenfootImage[] downMvt1 = new GreenfootImage[4];

    private static GreenfootImage[] rightMvt2 = new GreenfootImage[4];
    private static GreenfootImage[] leftMvt2 = new GreenfootImage[4];
    private static GreenfootImage[] upMvt2 = new GreenfootImage[4];
    private static GreenfootImage[] downMvt2 = new GreenfootImage[4];

    private static GreenfootImage[] rightMvt3 = new GreenfootImage[4];
    private static GreenfootImage[] leftMvt3 = new GreenfootImage[4];
    private static GreenfootImage[] upMvt3 = new GreenfootImage[4];
    private static GreenfootImage[] downMvt3 = new GreenfootImage[4];

    private int level;
    public int speed;

    //If we scale stuff this won't work!!
    private int width = getImage().getWidth();
    private int height = getImage().getHeight();

    private Radius radius = new Radius();
    private static boolean createdImages = false;
    private int frameRate = 7;
    private int imageNumber = 0;
    private long animationCount = 0;

    //declare imgs and stuff

    public Shopper(int level){
        this.level = level;
        this.speed = level;
    }

    public void addedToWorld (World w){
        getWorld().addObject(radius,getX(),getY());
    }    

    /**
     * createImages - create images used by player class if not already done so
     */
    public static void createImages(){
        if(!createdImages){
            createdImages = true;
            for(int i=1; i<=4; i++)
            {
                rightMvt1[i-1] = new GreenfootImage("Walking Sprites/EA-R"+i+".png");
                leftMvt1[i-1] = new GreenfootImage("Walking Sprites/EA-L"+i+".png");
                upMvt1[i-1] = new GreenfootImage("Walking Sprites/EA-B"+i+".png");
                downMvt1[i-1] = new GreenfootImage("Walking Sprites/EA-F"+i+".png");

                rightMvt2[i-1] = new GreenfootImage("Walking Sprites/EB-R"+i+".png");
                leftMvt2[i-1] = new GreenfootImage("Walking Sprites/EB-L"+i+".png");
                upMvt2[i-1] = new GreenfootImage("Walking Sprites/EB-B"+i+".png");
                downMvt2[i-1] = new GreenfootImage("Walking Sprites/EB-F"+i+".png");

                rightMvt3[i-1] = new GreenfootImage("Walking Sprites/EC-R"+i+".png");
                leftMvt3[i-1] = new GreenfootImage("Walking Sprites/EC-L"+i+".png");
                upMvt3[i-1] = new GreenfootImage("Walking Sprites/EC-B"+i+".png");
                downMvt3[i-1] = new GreenfootImage("Walking Sprites/EC-F"+i+".png");
            }
            /*
            for(int i=0; i<rightMvt.length;i++)
            {
            rightMvt[i].scale(rightMvt[i].getWidth()*scaleNumber/100,rightMvt[i].getHeight()*scaleNumber/100);
            leftMvt[i].scale(leftMvt[i].getWidth()*scaleNumber/100,leftMvt[i].getHeight()*scaleNumber/100);
            }
            for(int i=0; i<upMvt.length;i++)
            {
            upMvt[i].scale(upMvt[i].getWidth()*scaleNumber/100,upMvt[i].getHeight()*scaleNumber/100);
            downMvt[i].scale(downMvt[i].getWidth()*scaleNumber/100,downMvt[i].getHeight()*scaleNumber/100);
            }
             */

        }
    }    

    /**
     * Act - do whatever the Shopper wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //figure out animation stuff
        animationCount++;
        move();

        //Radius
        if(getObjectsInRange(100,Player.class).size()>0){
            radius.getImage().setTransparency(150);
            ArrayList <Player> playerList = (ArrayList) getObjectsInRange(Radius.radius,Player.class);
            if(playerList.size()>0){
                playerList.get(0).isTouchingShopper();
            }    
        }    
        else radius.getImage().setTransparency(0);
    }    

    private void move(){
        if(animationCount%frameRate == 0){
            int dx = 0, dy = 0;
            int random = Greenfoot.getRandomNumber(4);
            if(random==0){
                animateMovementUp();
                dy = -speed;
            }    
            else if(random==1){
                animateMovementDown();
                dy = speed;
            }    
            else if(random==2){
                animateMovementLeft();
                dx = -speed;
            }    
            else{
                animateMovementRight();
                dx = speed;
            }    

            setLocation(getX()+dx,getY()+dy);
            if(invalidMove()) setLocation(getX()-dx,getY()-dy);
        }
    }    

    /**
     * invalidMove - checks if the last move was invalid
     * 
     * @return boolean              true if the move was invalid and false if not
     */
    private boolean invalidMove(){
        for(int i =-1; i<=1;i++){
            for(int j =-1;j<=1;j++){
                if(getOneObjectAtOffset(i*width/2, j*height/2, Obstacle.class)!=null){
                    return true;
                }    
            }   
        }
        return false;
    }    

    public void animateMovementUp(){
        if(animationCount%frameRate == 0)
        {
            imageNumber = (imageNumber + 1)% (4);
            if(level==1) setImage(downMvt1[imageNumber]);
            else if(level==2) setImage(downMvt2[imageNumber]);
            else setImage(downMvt3[imageNumber]);
        }    
    }    

    public void animateMovementDown(){
        if(animationCount%frameRate == 0)
        {
            imageNumber = (imageNumber + 1)% (4);
            if(level==1) setImage(upMvt1[imageNumber]);
            else if(level==2) setImage(upMvt2[imageNumber]);
            else setImage(upMvt3[imageNumber]);
        }   
    }    

    public void animateMovementLeft(){
        if(animationCount%frameRate == 0)
        {
            imageNumber = (imageNumber + 1)% (4);
            if(level==1) setImage(leftMvt1[imageNumber]);
            else if(level==2) setImage(leftMvt2[imageNumber]);
            else setImage(leftMvt3[imageNumber]);
        }   
    }    

    public void animateMovementRight(){
        if(animationCount%frameRate == 0)
        {
            imageNumber = (imageNumber + 1)% (4);
            if(level==1) setImage(rightMvt1[imageNumber]);
            else if(level==2) setImage(rightMvt2[imageNumber]);
            else setImage(rightMvt3[imageNumber]);
        }  
    }
}