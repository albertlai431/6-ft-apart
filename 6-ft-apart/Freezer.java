import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Freezer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Freezer extends Obstacle
{
    /**
     * Act - do whatever the Freezer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setLocation(getWorld().getWidth()/2,getWorld().getHeight()/2);
        
    }    
}
