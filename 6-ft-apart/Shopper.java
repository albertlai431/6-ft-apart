import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shopper here.
 * 
 * @author Albert 
 * @version May 2020
 */
public class Shopper extends Actor
{
    private int level;
    public int speed;
    private boolean target;
    
    public Shopper(int level){
        this.level = level;
        
        if(level == 1){
            speed = 1;
        }    
    }
    
    /**
     * Act - do whatever the Shopper wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
