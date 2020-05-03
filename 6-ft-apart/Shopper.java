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
    private int level;
    public int speed;
    private boolean target;
    
    private Radius radius = new Radius();
    
    //declare imgs and stuff
    
    public Shopper(int level){
        this.level = level;
        
        if(level == 1){
            this.speed = 1;
            this.target = false;
        }    
        else if(level == 2){
            this.speed = 2;
            this.target = false;
        }
        else{
            this.speed = 2;
            this.target = true;
        }            
    }
    
    public void addedToWorld (World w){
        getWorld().addObject(radius,getX(),getY());
    }    
    
    /**
     * Act - do whatever the Shopper wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //figure out animation stuff
        
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
    
    public void animateMovementUp(){
    }    
    
    public void animateMovementDown(){
    }    
    
    public void animateMovementLeft(){
    }    
    
    public void animateMovementRight(){
    }
}