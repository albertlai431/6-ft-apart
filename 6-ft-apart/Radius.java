import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Radius here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Radius extends Actor
{
    
    private Shopper s;
    public static final int radius = 100;
    
    public Radius(Shopper s){
        this.s = s;
        setImage("circle.png");
        getImage().scale(radius*2,radius*2);
        getImage().setTransparency(0);        
    }
    
    public void act(){
        setLocation(s.getX(),s.getY());
        if(isTouching(Player.class)){
            Player p = (Player) getWorld().getObjects(Player.class).get(0);
            p.isTouchingShopper();
        }
    }    
}