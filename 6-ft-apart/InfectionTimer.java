import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InfectionTimer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InfectionTimer extends Actor
{
    public int curTime;
    public InfectionTimer(int maxTime) {
        this.curTime = maxTime;
    }
    
    /**
     * Act - do whatever the InfectionTimer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        curTime--;
        if (curTime == 0) {
            // ran out of time.
        }
    }
    
}
