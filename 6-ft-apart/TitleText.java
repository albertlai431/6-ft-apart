import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleText here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleText extends Actor
{
    public TitleText() {
        GreenfootImage text = new GreenfootImage(250, 60);
        text.setFont(new Font("Comic Sans MS", 40));
        text.fill();
        text.setColor(Color.RED);
        text.drawString("6 ft Apart", 30, 40);
        setImage(text);
    }
}
