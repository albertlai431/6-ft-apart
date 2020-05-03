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
    private ArrayList<Item> itemsToCollect = new ArrayList<Item>();
    private final int MAX_ITEMS = 4;
    private final String[]SHELF_ITEMS = {
            "apple", "banana", "orange", "pear", "peach"
        };
    private final String[]FREEZER_ITEMS = {
            "milk", "eggs", "pork", "chicken", "beef"
        };

    private MissionBox missionBox = new MissionBox();
    private ScoreText scoreText = new ScoreText();
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
        addObject(new Shopper(1), 300, 300);
        addObject(new Player(), 200, 200);
        addObject(missionBox, 100, 100);
        addObject(new Freezer(), 100, 200);
        addObject(new Shelf(), 300, 100);
        addObject(new Washroom(), 550, 350);
    }

    private void nextMission() {
        scoreText.addScore((mission - 1) / 5);
        if ((mission - 1) / 5 == 0) {
            // TODO add another shopper after every 5 missions
        }

        // generate the new list of items to collect
        int numOfItemsToCollect = Math.min(MAX_ITEMS, (mission - 1) / 5 + 1);

        itemsToCollect = new ArrayList<Item>();

        for (int i = 0; i < numOfItemsToCollect; i++) {
            // Generate a new Item
            if (Greenfoot.getRandomNumber(2) == 1) {
                String itemName = SHELF_ITEMS[Greenfoot.getRandomNumber(SHELF_ITEMS.length)];
                Food food = new Food(itemName);
                itemsToCollect.add(food);
                // TODO add to shelf
                List<Shelf> shelves = getObjects(Shelf.class);
                Shelf shelf = shelves.get(Greenfoot.getRandomNumber(shelves.size()));
                int x = shelf.getX() - shelf.getImage().getWidth() / 2 + Greenfoot.getRandomNumber(2) * shelf.getImage().getWidth();
                int y = shelf.getY() - shelf.getImage().getHeight() / 2 + Greenfoot.getRandomNumber(2) * shelf.getImage().getHeight();
                addObject(food, x, y);
            } else {
                String itemName = FREEZER_ITEMS[Greenfoot.getRandomNumber(FREEZER_ITEMS.length)];
                Food food = new Food(itemName);
                itemsToCollect.add(new Food(itemName));
                // TODO add to freezer
                List<Freezer> freezers = getObjects(Freezer.class);
                Freezer freezer = freezers.get(Greenfoot.getRandomNumber(freezers.size()));
                int x = freezer.getX() - freezer.getImage().getWidth() / 2 + Greenfoot.getRandomNumber(2) * freezer.getImage().getWidth();
                int y = freezer.getY() - freezer.getImage().getHeight() / 2 + Greenfoot.getRandomNumber(2) * freezer.getImage().getHeight();
                addObject(food, x, y);
            }
            
            
        }
        
        // potentially add a powerup
        if (getObjects(Sanitizer.class).size() == 0 && Greenfoot.getRandomNumber(mission % 5 + 2) == 0) {
            List<Shelf> shelves = getObjects(Shelf.class);
            Shelf shelf = shelves.get(Greenfoot.getRandomNumber(shelves.size()));
            int x = shelf.getX() - shelf.getImage().getWidth() / 2 + Greenfoot.getRandomNumber(2) * shelf.getImage().getWidth();
            int y = shelf.getY() - shelf.getImage().getHeight() / 2 + Greenfoot.getRandomNumber(2) * shelf.getImage().getHeight();
            addObject(new Sanitizer(), x, y);
        } else if (getObjects(FaceMask.class).size() == 0 && Greenfoot.getRandomNumber(mission % 5 + 2) == 0) {
            List<Shelf> shelves = getObjects(Shelf.class);
            Shelf shelf = shelves.get(Greenfoot.getRandomNumber(shelves.size()));
            int x = shelf.getX() - shelf.getImage().getWidth() / 2 + Greenfoot.getRandomNumber(2) * shelf.getImage().getWidth();
            int y = shelf.getY() - shelf.getImage().getHeight() / 2 + Greenfoot.getRandomNumber(2) * shelf.getImage().getHeight();
            addObject(new FaceMask(), x, y);
        }
        
        mission++;
    }

    /**
     * Call this when the player collides with an item to pick up.
     */
    public void pickUpItem(Item item) {
        if (itemsToCollect.contains(item)) {
            itemsToCollect.remove(item);

            // If all items are collected
            if (itemsToCollect.size() == 0) {
                nextMission();
            }
            missionBox.update(mission, itemsToCollect);
        }
        
        removeObject(item);
    }
    
    public void incrementSanitizerCount() {
        scoreText.addSanitizer(1);
    }
    
    public boolean decrementSanitizerCount() {
        scoreText.addSanitizer(-1);
        return scoreText.getSanitizer() == 0;
    }
    
    public void incrementMaskCount() {
        scoreText.addMask(1);
    }
    
    public void decrementMaskCount() {
        if (scoreText.getMasks() > 0) {
            scoreText.addMask(-1);
        }
    }

    public int getMission() {
        return mission;
    }

}
