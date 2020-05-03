import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NavigateButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartButton extends Actor
{
    public StartButton() {
        this.setImage(new GreenfootImage("  START  ", 30, Color.DARK_GRAY, Color.RED));    
    }

    /**
     * Act - do whatever the NavigateButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // hover behaviour
        if (Greenfoot.mouseMoved(this)) setImage(new GreenfootImage("  START  ", 30, Color.DARK_GRAY, Color.ORANGE));
        else if (Greenfoot.mouseMoved(null)) setImage(new GreenfootImage("  START  ", 30, Color.DARK_GRAY, Color.RED));

        if (Greenfoot.mouseClicked(this)) Greenfoot.setWorld(new StoreWorld());
    }    
}
