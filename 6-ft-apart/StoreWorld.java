import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Main World
 * 
 * @author Enoch
 * @version May 2020
 */
public class StoreWorld extends World
{
    private int mission = 0; // it will go up to 1 in the constructor after calling nextMission the first time
    private int score = 0;
    private ArrayList<Item> itemsToCollect = new ArrayList<Item>();
    private final int MAX_ITEMS = 4;
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
        nextMission();
        missionBox.update(mission, itemsToCollect);
        setPaintOrder(ScoreText.class, MissionBox.class, Player.class, Item.class, Shopper.class, Obstacle.class);
    }

    private void prepare() {
        // TODO set up the world layout
        
        addObject(new Shopper(1), 300, 300);
        addObject(new Player(), 200, 200);
        addObject(missionBox, 100, 100);
        
    }

    private void nextMission() {
        score += (mission - 1) / 5;
        if ((mission - 1) / 5 == 0) {
            // TODO add another shopper after every 5 missions
        }

        // generate the new list of items to collect
        int numOfItemsToCollect = (mission - 1) / 5 + 1;

        itemsToCollect = new ArrayList<Item>();

        for (int i = 0; i < numOfItemsToCollect; i++) {
            // Generate a new Item
            if (Greenfoot.getRandomNumber(2) == 1) {
                String itemName = SHELF_ITEMS[Greenfoot.getRandomNumber(SHELF_ITEMS.length)];
                itemsToCollect.add(new Food(itemName));
                // TODO add to shelf
            } else {
                String itemName = FREEZER_ITEMS[Greenfoot.getRandomNumber(FREEZER_ITEMS.length)];
                itemsToCollect.add(new Food(itemName));
                // TODO add to freezer
            }
        }
        mission++;
    }

    /**
     * Call this when the player collides with an item to pick up.
     */
    public void afterItemPickup(Item item) {
        if (itemsToCollect.contains(item)) {
            itemsToCollect.remove(item);

            // If all items are collected
            if (itemsToCollect.size() == 0) {
                nextMission();
            }
            missionBox.update(mission, itemsToCollect);
        }
        
    }

    public int getMission() {
        return mission;
    }

    public int getScore() {
        return score;
    }

}
