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
        this.setImage("6ftButton.PNG");
        GreenfootImage image = getImage();
        image.scale(200, 200);
        setImage(image);
    }

    /**
     * Act - do whatever the NavigateButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // hover behaviour
        if (Greenfoot.mouseMoved(this)) setImage("6ftHover.PNG");
        else if (Greenfoot.mouseMoved(null)) setImage("6ftButton.PNG");
        
        GreenfootImage image = getImage();
        image.scale(200, 200);
        setImage(image);
        
        setLocation(150,250);
        
        if (Greenfoot.mouseClicked(this)) Greenfoot.setWorld(new StoreWorld());
    }    
}