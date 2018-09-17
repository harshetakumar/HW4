/*
Author: Giovanni Alanis
ACCC: galani3
UIN: 657681506
*/

public class GameTester {

    public static void main(String[] args) {
        System.out.println("Giovanni Alanis");
        System.out.println("ACCC: galani3");
        System.out.println("UIN: 657681506\n");

        //Create game object
        Game myGame = new Game("Dungeon");

        //Create place objects for the game
        Place entrance_hall = new Place(12, "Entrance Hall", "You are standing in the entrance hall of the great six-room dungeon. \nThere are doors to the east and north, and a stairway leading down. \nThe main exit (from the game) is to the west.");
        Place ogre_lair = new Place(13, "Ogre's Lair", "You have entered the Ogre's Lair! Better leave before he wakes up ... \nThere are doors to the south and east.");
        Place treasure_storeroom = new Place(23, "Treasure Storeroom", "You have found a storeroom full of gold, jewels, and treasure! \nThere are doors to the north and south.");
        Place pool_of_enchantment = new Place(22, "Pool of Enchantment", "You are in a round room with a clear enchanting pool of water. \nThere are doors to the north and west. \nThere is a slide leading downwards to the floor below.");
        Place potions_lab = new Place(21, "Potions Lab", "There is a cauldron of thick green goop here, bubbling slowly over a cool blue flame. \nDoors lead to the west and east.");
        Place potions_storeroom = new Place(11, "Potions Storeroom", "This room has shelves full of bottles and jars. \nSome labels read \"Powdered bat\'s wings\" and \"Toad eyes\". \nThere is a door to the east, and a stairway leading up");
        Place exit = new Place(1, "Exit", "Exit");
        Place nowhere = new Place(0, "Nowhere", "Nowhere");

        //Setup directions for the entrance hall
        Direction entrance_hall_east = new Direction(8, entrance_hall, pool_of_enchantment, "East");
        Direction entrance_hall_north = new Direction(4, entrance_hall, ogre_lair, "North");
        Direction entrance_hall_down = new Direction(10, entrance_hall, potions_storeroom, "Down");
        Direction entrance_hall_west = new Direction(7, entrance_hall, exit, "West");

        //Setup directions for the ogre lair
        Direction ogre_lair_east = new Direction(2, ogre_lair, treasure_storeroom, "East");
        Direction ogre_lair_south = new Direction(3, ogre_lair, entrance_hall, "South");

        //Setup directions for the treasure storeroom
        Direction treasure_storeroom_north = new Direction(1, treasure_storeroom, ogre_lair, "North");
        Direction treasure_storeroom_south = new Direction(6, treasure_storeroom, pool_of_enchantment, "South");

        //Setup directions for the pool of enchantment
        Direction pool_of_enchantment_north = new Direction(5, pool_of_enchantment, treasure_storeroom, "North");
        pool_of_enchantment_north.lock();

        Direction pool_of_enchantment_west = new Direction(9, pool_of_enchantment, entrance_hall, "West");
        Direction pool_of_enchantment_down = new Direction(12, pool_of_enchantment, potions_lab, "Down");

        //Setup directions for the potions lab
        Direction potions_lab_west = new Direction(13, potions_lab, potions_storeroom, "West");
        Direction potions_lab_east = new Direction(15, potions_lab, nowhere, "East");
        potions_lab_east.lock();

        //Setup directions for the potions storeroom
        Direction potions_storeroom_east = new Direction(14, potions_storeroom, potions_lab, "East");
        potions_storeroom_east.lock();

        Direction potions_storeroom_up = new Direction(11, potions_storeroom, entrance_hall, "Up");

        //Add directions to the entrance hall
        entrance_hall.addDirection(entrance_hall_east);
        entrance_hall.addDirection(entrance_hall_north);
        entrance_hall.addDirection(entrance_hall_down);
        entrance_hall.addDirection(entrance_hall_west);

        //Add directions to the ogre lair
        ogre_lair.addDirection(ogre_lair_east);
        ogre_lair.addDirection(ogre_lair_south);

        //Add directions to the treasure storeroom
        treasure_storeroom.addDirection(treasure_storeroom_north);
        treasure_storeroom.addDirection(treasure_storeroom_south);

        //Add directions to the pool of enchantment
        pool_of_enchantment.addDirection(pool_of_enchantment_west);
        pool_of_enchantment.addDirection(pool_of_enchantment_down);
        pool_of_enchantment.addDirection(pool_of_enchantment_north);

        //Add directions to the potions lab
        potions_lab.addDirection(potions_lab_west);
        potions_lab.addDirection(potions_lab_east);

        //Add directions to the potions storeroom
        potions_storeroom.addDirection(potions_storeroom_east);
        potions_storeroom.addDirection(potions_storeroom_up);

        //Add places to the game object
        myGame.addPlace(entrance_hall);
        myGame.addPlace(ogre_lair);
        myGame.addPlace(treasure_storeroom);
        myGame.addPlace(pool_of_enchantment);
        myGame.addPlace(potions_lab);
        myGame.addPlace(potions_storeroom);

        //Initialize the starting point for the game
        myGame.setCurrentPlace(entrance_hall);

        //Print game details for debugging
        myGame.print();

        //Play the game
        myGame.play();
    }
}
