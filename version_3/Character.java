import java.util.HashMap;
import java.util.Scanner;

public class Character {

    private int id;
    private int placeId;
    private String name;
    private String description;
    private Place current;
    private DecisionMaker decisionMaker;
    private static HashMap<Integer, Character> availableCharacters = new HashMap<Integer, Character>();
    private HashMap<String, Artifact> inventory = new HashMap<String, Artifact>();

    /**
     * Creates a character object by passing in a file
     *
     * @param inputFile A file containing properties of a character object
     * @return Character Object
     */
    public Character(Scanner inputFile) {
        this.placeId = inputFile.nextInt();
        inputFile.nextLine();

        //Set id and name
        this.id = inputFile.nextInt();
        this.name = inputFile.nextLine().trim();

        int numOfDescription = inputFile.nextInt();
        inputFile.nextLine();

        String description = " ";
        for (int i = 0; i < numOfDescription; i++) {
            description += inputFile.nextLine().trim() + " ";
        }
        this.description = description;

        //Retrieve the place or find a random place to put the character in
        if (this.placeId > 0) {
            this.current = Place.getPlaceById(this.placeId);
            Place place = Place.getPlaceById(this.placeId);
            place.addCharacter(this);
        } else {
            Place place = Place.getRandomPlace();
            this.current = place;
            place.addCharacter(this);
        }

        //Add character to the total characters collection in the game
        availableCharacters.put(this.id, this);
    }

    public Character(int id, int placeId, String name, String description) {
        this.id = id;
        this.placeId = placeId;
        this.name = name;
        this.description = description;

        if (this.placeId > 0) {
            this.current = Place.getPlaceById(this.placeId);
            Place place = Place.getPlaceById(this.placeId);
            place.addCharacter(this);
        } else {
            Place place = Place.getRandomPlace();
            this.current = place;
            place.addCharacter(this);
        }

    }

    //Returns a character based on ID
    public static Character getCharacterByID(int id) {
        if (availableCharacters.containsKey(id)) {
            return availableCharacters.get(id);
        } else {
            System.out.println("Unable to find character with id of " + id);
            return null;
        }
    }

    //Adds artifact to the character's inventory
    public void addArtifact(Artifact artifact) {
        this.inventory.put(artifact.name(), artifact);
    }

    //Removes artifact from the character's inventory
    public void removeArtifact(String artifactName) {
        if (this.inventory.containsKey(artifactName)) {
            this.inventory.remove(artifactName);
        }
    }

    //Used to display character information in game
    public void display() {
        System.out.println(">" + this.name());
        System.out.println("Description: " + this.description);
    }

    //Used to debug the character
    public void print() {
        System.out.println("====================================");
        System.out.println("Character: " + name());
        System.out.println("====================================");
        System.out.println("ID: " + this.id);
        System.out.println("Current location: " + this.current.name());
        System.out.println("Name: " + this.name);
        System.out.println("Description: " + this.description + "\n");
        System.out.println("Inventory: ");
        for (Artifact artifact : this.inventory.values()) {
            System.out.println(artifact.name());
        }
    }

    //Prints all the characters in the game
    public static void printAll() {
        System.out.println("Number of characters: " + availableCharacters.size());
        for (Character character : availableCharacters.values()) {
            character.print();
        }
    }

    //Return character name
    public String name() {
        return this.name;
    }

    //Assigns the Decision Maker for the character based on if it's a Player or NPC
    public void setDecisionMaker(DecisionMaker decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    //Generates a move to be executed on the character
    public Move makeMove() {
        return decisionMaker.getMove(this, this.current);
    }

    //Checks if character is carrying an artifact
    public boolean checkForArtifact(String artifactName) {
        return this.inventory.containsKey(artifactName);
    }

    //Returns artifact in character's inventory
    public Artifact retrieveArtifactFromInventory(String artifactName) {
        return this.inventory.get(artifactName);
    }

    //Returns entire inventory of character
    public HashMap<String, Artifact> retrieveInventory() {
        return this.inventory;
    }

    //Updates character's location when moving place to place
    public void updateLocation(Place place) {
        this.current = place;
    }

}
