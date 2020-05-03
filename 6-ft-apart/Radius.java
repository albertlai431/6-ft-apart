import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Radius here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Radius extends Actor
{
    
    public static final int radius = 25;
    
    public Radius(){
        getImage().scale(radius*2,radius*2);
        getImage().setTransparency(0);
    }
    
    public void act(){
        
    }    
}