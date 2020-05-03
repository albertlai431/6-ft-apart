import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author Albert
 * @version May 2020
 */
public class Player extends Actor
{
    //animation images
    private static GreenfootImage[] rightMvt = new GreenfootImage[5];
    private static GreenfootImage[] leftMvt = new GreenfootImage[5];
    private static GreenfootImage[] upMvt = new GreenfootImage[8];
    private static GreenfootImage[] downMvt = new GreenfootImage[8];
    
    public int sanitizerCount = 0;
    public int maskCount = 0;
    private int speed = 2;
    private int width = getImage().getWidth();
    private int height = getImage().getHeight();
    private boolean isInfected = false;
    
    private Timer timer;
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move();
        isTouchingShopper();
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
            //animateMovementLeft();
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
            //animateMovementRight();
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
            //animateMovementUp();
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
            //animateMovementDown();
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
    private boolean loseOneSanitizer()
    {
        sanitizerCount--;
        //decrement scorebar
        if(sanitizerCount==0) return true;
        return false;
    }
    
    private void addOneSanitizer(){
        sanitizerCount++;
        //increment scorebar
    }    
    
    /**
     * Checks if player is touching shopper
     */
    private void isTouchingShopper(){
        if(isTouching(Shopper.class)){
            if(isInfected){
                timer.removeTime();
            }
            else{
                isInfected = true;
                timer = new Timer(5000);
                getWorld().addObject(timer,100,100); //To be changed!
            }
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
                if(loseOneSanitizer()) System.out.println("u dead");//die
            }    
        }
    }    
    
    private void isTouchingItem(){
        if(isTouching(Item.class)){
            if(isTouching(Sanitizer.class)){
                sanitizerCount++;
                //adjust scorebar
            }    
            else if(isTouching(FaceMask.class)){
                maskCount++;
                //adjust scorebar
            }    
            else{
                //call some world method to decrement item + adjust mission box
            }    
        }    
    }    
}
