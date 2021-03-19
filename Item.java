import java.util.ArrayList;

/**
 * Class Item - an item in the adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Item" represents one object in the scenery of the game. Many Items can be in each of the
 * rooms. Items have a description and weight to them. 
 *
 * @author Christopher Urban
 * @version 2021.03.18
 */
public class Item
{
    private String description;
    private int weight;
    private String name;
    private ArrayList<Item> items;        // stores items in every room.

    /**
     * Constructor for objects of class Item
     */
    public Item(String description, int weight, String name)
    {
        // initialise instance variables
        this.description = description;
        this.weight = weight;
        this.name = name;
        ArrayList items = new ArrayList(20);
    }

    /**
     * @return The description of the item
     * (the one that was defined in the constructor).
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return The weight of the item
     * (the one that was defined in the constructor).
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * @return The name of the item
     * (the one that was defined in the constructor).
     */
    public String getName()
    {
        return name;
    }
}
