import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StoreWorld extends World
{
    private int mission = 1;
    private int score = 0;
    private ArrayList<Item> itemsToCollect = new ArrayList<Item>();
    private final int MAX_ITEMS = 4;
    private int leftToCollect = 0;
    private final String[]SHELF_ITEMS = {
            "apple", "banana", "orange", "pear", "peach"
        };
    private final String[]FREEZER_ITEMS = {
            "milk", "eggs", "pork", "chicken", "beef"
        };
    
    private MissionBox missionBox = new MissionBox();
    public StoreWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        prepare();
    }

    private void prepare() {
    }
    
    @Override
    public void act() {

    }

    /**
     * Call this when the player collides with an item to pick up
     */
    public void pickUpItem(Item item) {
        if (itemsToCollect.contains(item)) {
            itemsToCollect.remove(item);

            // If all items are collected
            if (itemsToCollect.size() == 0) {

                score += (mission - 1) / 5;
                if ((mission - 1) / 5 == 0) {
                    // TODO add another shopper after every 5 missions
                }

                // generate the new list of items to collect
                int numOfItemsToCollect = (mission - 1) / 5 + 1;

                itemsToCollect = new ArrayList<Item>();

                for (int i = 0; i < numOfItemsToCollect; i++) {
                    // Generate a new Item for itemsToCollect[i], place them in the World
                }
                mission++;
            }
        }
    }

    public int getMission() {
        return mission;
    }

    public int getScore() {
        return score;
    }

    public int getItemsUncollected() {
        return leftToCollect;
    }
}
