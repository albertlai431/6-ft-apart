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
            "Apple", "Banana", "Orange", "Pear", "Peach"
        };
    private final String[]FREEZER_ITEMS = {
            "Milk", "Chicken", "Steak"
        };

    private MissionBox missionBox = new MissionBox();
    private ScoreText scoreText = new ScoreText();
    private Player player = new Player();
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
        addObject(new Shopper(1), 300, 350);
        addObject(player, 100, 100);
        addObject(missionBox, 200, 50);
        addObject(new Freezer(10, 200), 590, 200);
        addObject(new Shelf(200, 20), 300, 100);
        addObject(new Shelf(200, 20), 300, 250);
        addObject(new Washroom(), 550, 350);
        addObject(scoreText, 500, 375);
    }

    private void nextMission() {
        scoreText.addScore((mission - 1) / 5);
        player.newMission();
        if ((mission - 1) / 5 == 0) {
            addObject(new Shopper((mission - 1) % 5 + 6), 0, 300);
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
                List<Shelf> shelves = getObjects(Shelf.class);
                Shelf shelf = shelves.get(Greenfoot.getRandomNumber(shelves.size()));
                int x = shelf.getX() - shelf.width / 2 + Greenfoot.getRandomNumber(shelf.width);
                int y = shelf.getY() - shelf.height / 2 - 1 + Greenfoot.getRandomNumber(2) * (shelf.height + 2);
                addObject(food, x, y);
            } else {
                String itemName = FREEZER_ITEMS[Greenfoot.getRandomNumber(FREEZER_ITEMS.length)];
                Food food = new Food(itemName);
                itemsToCollect.add(food);
                List<Freezer> freezers = getObjects(Freezer.class);
                Freezer freezer = freezers.get(Greenfoot.getRandomNumber(freezers.size()));
                int x = freezer.getX() - freezer.getImage().getWidth() / 2 - 1;
                int y = freezer.getY() - freezer.getImage().getHeight() / 2 - 1 + Greenfoot.getRandomNumber(2) * (freezer.getImage().getHeight() + 1);
                addObject(food, x, y);
            }
            
            
        }
        
        // potentially add a powerup
        if (getObjects(Sanitizer.class).size() == 0 && Greenfoot.getRandomNumber(mission / 5 + 2) == 0) {
            List<Shelf> shelves = getObjects(Shelf.class);
            Shelf shelf = shelves.get(Greenfoot.getRandomNumber(shelves.size()));
            int x = shelf.getX() - shelf.getImage().getWidth() / 2 + Greenfoot.getRandomNumber(2) * shelf.getImage().getWidth();
            int y = shelf.getY() - shelf.getImage().getHeight() / 2 + Greenfoot.getRandomNumber(2) * shelf.getImage().getHeight();
            addObject(new Sanitizer(), x, y);
        } else if (getObjects(FaceMask.class).size() == 0 && Greenfoot.getRandomNumber(mission / 5 + 2) == 0) {
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
            //System.out.println("updatinggg");
            missionBox.update(mission, itemsToCollect);
        } //else System.out.println("not there");
        
        removeObject(item);
    }
    
    public void incrementSanitizerCount() {
        scoreText.addSanitizer(1);
    }
    
    public boolean decrementSanitizerCount() {
        if (scoreText.getSanitizer() > 0) {
           scoreText.addSanitizer(-1);
        }
        return scoreText.getSanitizer() == 0;
    }
    
    public void incrementMaskCount() {
        scoreText.addMask(1);
    }
    
    public boolean decrementMaskCount() {
        if (scoreText.getMasks() > 0) {
            scoreText.addMask(-1);
        }
        return scoreText.getMasks() == 0;
    }

    public int getMission() {
        return mission;
    }

}
