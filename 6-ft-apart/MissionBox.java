import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MissionBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MissionBox extends Actor
{
    /**
     * Act - do whatever the MissionBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int fontSize = 15;
    GreenfootImage missionBox = new GreenfootImage(150,50);
    GreenfootImage itemImg = new GreenfootImage(50,50);
    GreenfootImage missionDescription = new GreenfootImage("A bag of chips", fontSize,Color.WHITE,Color.BLACK);
    
    //everytime you make a missionbox instance, call it with the item as a parameter
    
    public MissionBox(Actor item){
        missionBox.setColor(new Color(213,172, 11));
        missionBox.fill();
        
        itemImg = item.getImage();
        itemImg.scale(missionBox.getHeight(),missionBox.getHeight());
        missionBox.drawImage(itemImg,0,0);
        missionBox.drawImage(new GreenfootImage("Next Mission: ", fontSize,Color.WHITE,Color.BLACK), itemImg.getWidth(),0);
        missionBox.drawImage(missionDescription,missionBox.getWidth()-missionDescription.getWidth()-5,fontSize+5);
        setImage(missionBox);
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
