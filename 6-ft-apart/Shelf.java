import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shelf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Shelf extends Obstacle
{
    public int width;
    public int height;
    public Shelf(int width, int height) {
        GreenfootImage image = getImage();
        image.scale(width,height);
        setImage(image);
        this.width=width;
        this.height=height;
    }
    /**
     * Act - do whatever the Shelf wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    }    
}
