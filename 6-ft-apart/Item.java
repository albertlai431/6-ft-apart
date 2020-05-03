import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Item extends Actor
{
    
    private String imgName;
    
    public Item(String imgName){
        this.imgName = imgName;
        setImage(imgName+".png");
    }    
    
    public String getName(){
        return imgName;
    }
    
    public void act(){
        
    }    
}