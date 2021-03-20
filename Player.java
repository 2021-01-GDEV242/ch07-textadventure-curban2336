import java.util.ArrayList;
/**
 * Object created at the start of the game to establish the players inventory
 *
 * @author Christopher Urban
 * @version 2021.03.19
 */
public class Player
{
    // the inventory of the player
    private ArrayList<Item> inventory;
    
    // the location of the player
    private Room currentRoom;
    
    // the carrying capacity of the player
    private int carryingCapacity;
    private int currentCapacity = 0;
    
    //How many points of health the player has
    private int health;

    /**
     * Constructor for the player
     */
    public Player()
    {
        // initialise the inventory
        inventory = new ArrayList<Item>();
        health = 30;
        carryingCapacity = 25;
    }

    /**
     * method to check if the selected item can be added to the inventory of the player. If it can
     * then the item is added and the total capacity the player is currently holding increases.
     *
     * @param  item is the item being added to the inventory
     * 
     */
    public void collect(Item item)
    {
        //Check to see if the item can be picked up
        if(currentCapacity+item.getWeight()<=carryingCapacity){
            inventory.add(item);
            currentCapacity+=item.getWeight();
        }
        else{
            System.out.println("The item is too heavy, carrying this would be too much for your carrying capacity");
        }
    }
    
    /**
     * method to drop a specified item in the current room 
     * then the item's weight is removed from the total capacity the player is currently holding.
     *
     * @param  item is the item being removed from the inventory
     * 
     */
    public void despose(Item item)
    {
        //remove item from inventory and remove weight of item from currentCapacity
        currentCapacity-=item.getWeight();
        inventory.remove(item);
    }
    
    /**
     * method to set the current location of the player to the room they are in
     * 
     *
     * @param  room the room that currentRoom will identify as
     * 
     */
    public void setRoom(Room room)
    {
        //set the currentRoom as the room entered
        currentRoom = room;
    }
    
    /**
     * take an item from a room.
     * @param itemVariable the item being placed.
     * 
     */
    public Item getItem(String itemVariable) 
    {
        Item filler = null;
        for(Item item : inventory){
            if(item.getName().equals(itemVariable)){
                filler = item;
            }
        }
        return filler;
    }
    
    /**
     * Return a string listing the items in the players inventory, for example
     * "Items: apple  pencil".
     * @return Details of the room's exits.
     */
    public String getItemString()
    {
        String returnString = "Items:";
        int sum = 0;
        for(Item item : inventory) {
            returnString += "  " + item.getName();
            sum+=item.getWeight();
        }
        return returnString + ". Total Weight: " + sum + " lbs";
    }
    
    /**
     * 
     * 
     *method to raise the amount of weight the player can carry by 10 pounds
     * 
     */
    public void cookieBoost()
    {
        //set the currentRoom as the room entered
        carryingCapacity += 10;
    }
    
    /**
     * 
     * method to return the health of the player.
     * 
     */
    public int getHealth()
    {
        return health;
    }
    
    /**
     * 
     * method to return the modified health of the player.
     * 
     */
    public int changeHealth(int modifier)
    {
        return health+=modifier;
    }
    
    /**
     * 
     * method to return the location of the player.
     * 
     */
    public Room getCurrentRoom()
    {
        //Check to see if the item can be picked up
        return currentRoom;
    }
}
