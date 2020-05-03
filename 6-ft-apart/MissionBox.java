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
    GreenfootImage missionBox = new GreenfootImage(1,1);
    GreenfootImage itemImg = new GreenfootImage(50,50);
    
    //everytime you make a missionbox instance, call it with the item as a parameter
    
    public MissionBox(){
        setImage(missionBox);
    }
    
    public void update(int missionNum, Actor[] item){
        if(item.length==1){
            fontSize = 11;
        }
        missionBox = new GreenfootImage(item.length*(itemImg.getWidth()-15)+10,itemImg.getHeight());
        missionBox.setColor(new Color(213,172, 11));
        missionBox.fill();
        for(int i = 0;i<item.length;i++){
            itemImg = item[i].getImage();
            itemImg.scale(missionBox.getHeight()-15,missionBox.getHeight()-15);
            missionBox.drawImage(itemImg,i*itemImg.getWidth()+5,missionBox.getHeight()-itemImg.getHeight());
        }
        missionBox.drawImage(new GreenfootImage("Mission "+missionNum, fontSize,Color.WHITE,Color.BLACK), 0,0);
        setImage(missionBox);
    }
    
    
    public void act() 
    {
        // Add your action code here.
    }    
}
