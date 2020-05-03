import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author Albert
 * @version May 2020
 */
public class Player extends Actor implements AnimationInterface
{
    //animation images
    private static GreenfootImage[] rightMvt = new GreenfootImage[4];
    private static GreenfootImage[] leftMvt = new GreenfootImage[4];
    private static GreenfootImage[] upMvt = new GreenfootImage[4];
    private static GreenfootImage[] downMvt = new GreenfootImage[4];

    //imgs w/ mask
    private static GreenfootImage[] mrightMvt = new GreenfootImage[4];
    private static GreenfootImage[] mleftMvt = new GreenfootImage[4];
    private static GreenfootImage[] mupMvt = new GreenfootImage[4];
    private static GreenfootImage[] mdownMvt = new GreenfootImage[4];

    private int speed = 2;
    private int width = getImage().getWidth();
    private int height = getImage().getHeight();
    private boolean isInfected = false;
    private long animationCount = 0;
    private boolean maskOn = false;

    private static boolean createdImages = false;
    private static final int scaleNumber = 100;
    private int frameRate = 7;
    private int imageNumber = 0;

    private Timer timer;

    public Player(){
        if(!createdImages) createImages();
        setImage("Walking Sprites/P-F1.png");
    }    

    /**
     * createImages - create images used by player class if not already done so
     */
    public static void createImages(){
        if(!createdImages){
            createdImages = true;
            for(int i=1; i<=rightMvt.length; i++)
            {
                rightMvt[i-1] = new GreenfootImage("Walking Sprites/P-R"+i+".png");
                leftMvt[i-1] = new GreenfootImage("Walking Sprites/P-L"+i+".png");
                upMvt[i-1] = new GreenfootImage("Walking Sprites/P-B"+i+".png");
                downMvt[i-1] = new GreenfootImage("Walking Sprites/P-F"+i+".png");

                mrightMvt[i-1] = new GreenfootImage("Walking Sprites/Pm-R"+i+".png");
                mleftMvt[i-1] = new GreenfootImage("Walking Sprites/Pm-L"+i+".png");
                mupMvt[i-1] = new GreenfootImage("Walking Sprites/Pm-B"+i+".png");
                mdownMvt[i-1] = new GreenfootImage("Walking Sprites/Pm-F"+i+".png");
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

    public void animateMovementUp(){
        if(animationCount%frameRate == 0)
        {
            imageNumber = (imageNumber + 1)% (upMvt.length);
            if(!maskOn) setImage(upMvt[imageNumber]);
            else setImage(mupMvt[imageNumber]);
        }
    }    

    public void animateMovementDown(){
        if(animationCount%frameRate == 0)
        {
            imageNumber = (imageNumber + 1)% (downMvt.length);
            if(!maskOn) setImage(downMvt[imageNumber]);
            else setImage(mdownMvt[imageNumber]);
        }
    }    

    public void animateMovementLeft(){
        if(animationCount%frameRate == 0)
        {
            imageNumber = (imageNumber + 1)% (leftMvt.length);
            if(!maskOn) setImage(leftMvt[imageNumber]);
            else setImage(mleftMvt[imageNumber]);
        }
    }    

    public void animateMovementRight(){
        if(animationCount%frameRate == 0)
        {
            imageNumber = (imageNumber + 1)% (rightMvt.length);
            if(!maskOn) setImage(rightMvt[imageNumber]);
            else setImage(mrightMvt[imageNumber]);
        }
    }    

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        animationCount++;
        move();
        isInfected();
        isTouchingItem();
    }    

    /**
     * move - moves the player depending on keyboard input along with wall/door detection
     */
    private void move()
    {
        int dx = 0, dy = 0;
        if(Greenfoot.isKeyDown("a"))//runs if "a" is pressed and the player is past the starting location
        {
            animateMovementLeft();
            if(Greenfoot.isKeyDown("s")){
                dx = -speed; dy = speed;
            }
            else if(Greenfoot.isKeyDown("w")){
                dx = -speed; dy = -speed;
            }
            else dx = -speed;
        }
        else if(Greenfoot.isKeyDown("d"))//runs if "d" is pressed
        {
            animateMovementRight();
            if(Greenfoot.isKeyDown("s")){
                dx = speed; dy = speed;
            }
            else if(Greenfoot.isKeyDown("w")){
                dx = speed; dy = -speed;
            }
            else dx = speed;
        }
        else if(Greenfoot.isKeyDown("w"))//runs if "d" is pressed
        {
            animateMovementUp();
            if(Greenfoot.isKeyDown("a")) {
                dx = -speed; dy = -speed;
            }
            else if(Greenfoot.isKeyDown("d")) {
                dx = speed; dy = -speed;
            }
            else dy = -speed;
        }
        else if(Greenfoot.isKeyDown("s"))//runs if "d" is pressed
        {
            animateMovementDown();
            if(Greenfoot.isKeyDown("a")) {
                dx = -speed; dy = speed;
            }
            else if(Greenfoot.isKeyDown("d")) {
                dx = speed; dy = speed;
            }
            else dy = speed;
        }

        setLocation(getX()+dx,getY()+dy);

        //checks for invalid move
        if(invalidMove()) setLocation(getX()-dx,getY()-dy);
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

    /**
     * loseOneSanitizer - takes one sanitizer away from the player
     * 
     * @return boolean              true if player has no more sanitizers
     */
    private void loseOneSanitizer()
    {
        StoreWorld w = (StoreWorld) getWorld();
        if(w.decrementSanitizerCount()){
            //gameover
        }    
    }

    /**
     * Checks if player is touching shopper
     */
    public void isTouchingShopper(){
        if(!maskOn && !isInfected){
            isInfected = true;
            timer = new Timer(5000);
            getWorld().addObject(timer,100,100); //To be changed!
        }
    }

    /**
     * What to do if player is infected
     */
    private void isInfected(){
        if(isInfected){
            if(isTouching(Washroom.class)){
                isInfected = false;
                getWorld().removeObject(timer);
            }    
            else if(timer==null){
                loseOneSanitizer();
            }    
        }
    }    

    private void isTouchingItem(){
        Item i = (Item) getOneObjectAtOffset(0, 0, Item.class);
        if(i!=null){
            StoreWorld w = (StoreWorld) getWorld();
            w.pickUpItem(i);
            if(isTouching(Sanitizer.class)){
                w.incrementSanitizerCount();
            }    
            else if(isTouching(FaceMask.class)){
                w.incrementMaskCount();
            }    
        }    
    }    

    private void getMask(){
        if(Greenfoot.isKeyDown("m")){
            StoreWorld w = (StoreWorld) getWorld();
            if(true && !isInfected){ //w.decrementMaskCount()
                maskOn = true;
            }    
        }    
    }    

    public void newMission(){
        maskOn = false;
    }    
}