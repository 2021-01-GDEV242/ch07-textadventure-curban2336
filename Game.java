
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes. Modified by Christopher Urban
 * @version 2021.03.18
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Item roomItem;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms, populate, and link their exits together.
     */
    private void createRooms()
    {
        Item photo, dumbbell, microscope, pocketWatch, soccerBall, football, mask, sandwich, stapler, pencil, wineBottle, emptyBottle, bleach, apple, handBag, chains, bikeLock, whistle, knife, note;
        
        // Create the items for the game
        photo = new Item("a small framed photograph of a lovely couple", 1, "photo");
        microscope = new Item("a small microscope, battery charged", 5, "microscope");
        pocketWatch = new Item("a small pocketwatch with a train image on the cover", 3, "pocketwatch");
        soccerBall = new Item("a size 4 soccerball", 1, "soccer ball");
        football = new Item("a slightly worn football", 1, "football");
        mask = new Item("a gilded thespian mask", 1, "thespian mask");
        sandwich = new Item("an egg salad sandwich, yum...", 0, "sandwich");
        stapler = new Item("a stapler filled with staples with a small STAPLES logo on it", 2, "stapler");
        pencil = new Item("a number 2 pencil, pretty much brand new", 0, "pencil");
        wineBottle = new Item("a fancy and unopened bottle of wine! Quite the score!", 3, "wine bottle");
        emptyBottle = new Item("an empty bottle. Looks to have once been a wine bottle...shame", 1, "empty bottle");
        bleach = new Item("a full bottle of bleach, obviously used to help clean intense messes", 4, "bleach");
        apple = new Item("a clean and shint apple,perfect for keeping doctors away", 0, "apple");
        handBag = new Item("a purse filled with a wallet, a dead phone, and a 3DS", 5, "purse");
        chains = new Item("a bundle of heavy chains, clearly designed to restrain someone, or something", 35, "chains");
        bikeLock = new Item("a bikelock lying on the ground, someone must have left it", 3, "bikelock");
        whistle = new Item("a referee whistle. Toot Toot", 0, "whistle");
        knife = new Item("a sharp yet compact butterfly knife. Wonder who left it lying around", 4, "knife");
        note = new Item("a note with crude handwriting on it. it says: Look in the cellar, see the horror", 0, "note");
        dumbbell = new Item("a small 45 lbs dumbbell, ready to give you some gains", 45, "dumbbell");
        
        Room outside, theater, pub, lab, office, cellar, dungeon, gym, cafeteria, dramaTheater, footballField, soccerField, mainHall, classOneA, storage; 
        // create the rooms
        outside = new Room("outside the main entrance of the university", photo);
        theater = new Room("in a lecture theater", pocketWatch);
        pub = new Room("in the campus pub", emptyBottle);
        lab = new Room("in a computing lab", microscope);
        office = new Room("in the computing admin office", stapler);
        cellar = new Room("in the cellar", wineBottle);
        dungeon = new Room("in the dungeon hidden below the cellar", chains);
        gym = new Room("in the gymnasium of the university", dumbbell);
        cafeteria = new Room("in the cafeteria of the university", sandwich);
        dramaTheater = new Room("in a musical theater", mask);
        footballField = new Room("at the university football field", football);
        soccerField = new Room("at the university soccer field", soccerBall);
        mainHall = new Room("in the university's main hallway", pencil);
        classOneA = new Room("in a history classroom", apple);
        storage = new Room("inside a janitors storage closet, it is rather big", note);
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", office);
        outside.setExit("west", pub);
        outside.setExit("north", footballField);

        theater.setExit("west", outside);
        theater.setExit("south", gym);

        pub.setExit("east", outside);
        pub.setExit("down", cellar);

        lab.setExit("east", mainHall);

        office.setExit("west", mainHall);
        office.setExit("north", outside);
        office.setExit("east", cafeteria);
        
        cellar.setExit("up", pub);
        cellar.setExit("down", dungeon);
        cellar.setExit("south-up", mainHall);
        
        mainHall.setExit("north-down", cellar);
        mainHall.setExit("east", office);
        mainHall.setExit("upstairs",dramaTheater);
        mainHall.setExit("south", classOneA);
        mainHall.setExit("downstairs", storage);
        mainHall.setExit("west", lab);
        
        footballField.setExit("south", outside);
        footballField.setExit("east", soccerField);
        
        soccerField.setExit("west", footballField);
        
        gym.setExit("north", theater);
        
        cafeteria.setExit("west", office);
        
        dramaTheater.setExit("downstairs", mainHall);
        
        classOneA.setExit("north", mainHall);
        
        storage.setExit("upstairs", mainHall);
        

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case LOOK:
                look();
                break;
                
            case EAT:
                System.out.println("You have eaten and are now full.");
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /** 
     * Re-print the current room's information to the player.
     * List room descriptions and exits.
     */
    private void look()
    {
       System.out.println(currentRoom.getLongDescription());
    }
}
