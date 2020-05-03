import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Food here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Food extends Item
{
    private String imgName;
    
    public Food(String name) {
        super("Food/" + name);
        imgName = name;
    }
}
