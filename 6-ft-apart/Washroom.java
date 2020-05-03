import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Washroom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Washroom extends Actor
{
    public int width = 70;
    public int height = 70;
    /**
     * Act - do whatever the Washroom wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Washroom()
    {
        GreenfootImage image = getImage();
        image.scale(width,height);
        setImage(image);
    }
    public void act() 
    {
        setLocation(getWorld().getWidth()-width/2, 0+height/2);
    }    
}
