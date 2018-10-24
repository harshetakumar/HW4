/*
Author: Giovanni Alanis
ACCC: galani3
UIN: 657681506
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Game {

    private String gameName;
    private static Place currentPlace = null;
    private HashMap<String, Artifact> inventory = new HashMap<String, Artifact>();

    //Creates a new game object with it's name
    Game(Scanner inputFile) {

        String[] fileLine;
        String[] parsedFileLine;

        //Hardcode the nowhere and exit place for now
        Place nowhere = new Place(0, "Nowhere", "Leads to nowhere");
        Place exit = new Place(1, "exit", "Lead to exit");


        while (inputFile.hasNextLine()) {

            //Read in a line from the file
            fileLine = inputFile.nextLine().split("//");
            parsedFileLine = fileLine[0].split("\\s+");

            //Check if the line begins with one of the magic words
            if (parsedFileLine[0].equals("GDF")) {

                //Print out game version
                System.out.println("Version: " + parsedFileLine[1]);

                //Print out game name
                String gameName = "";
                for (int i = 2; i < parsedFileLine.length; i++) {
                    gameName += parsedFileLine[i] + " ";
                }
                System.out.println("Game Name: " + gameName);
                this.gameName = gameName;
            } else if (parsedFileLine[0].equals("PLACES")) {

                //Read in the number of places in the game
                int numOfPlaces = Integer.parseInt(parsedFileLine[1].trim());

                for (int i = 0; i < numOfPlaces; i++) {

                    //Use scanner helper to find the next integer
                    ScannerHelper.getNextInt(inputFile);


                    //Pass place block to place constructor
                    Place place = new Place(inputFile);

                    //Set the first place as the starting place
                    if (currentPlace == null) {
                        currentPlace = place;
                    }
                }

            } else if (parsedFileLine[0].equals("DIRECTIONS")) {

                //Read in the number of directions in the game
                int numOfDirections = Integer.parseInt(parsedFileLine[1].trim());

                //Use scanner helper class to find a clean empty line to work from
                ScannerHelper.getEmptyLine(inputFile);

                for (int i = 0; i < numOfDirections; i++) {

                    //Pass direction block to direction constructor
                    Direction direction = new Direction(inputFile);
                }

            } else if (parsedFileLine[0].equals("ARTIFACTS")) {

                //Read in the number of artifacts
                int numOfArtifacts = Integer.parseInt(parsedFileLine[1]);
                for (int i = 0; i < numOfArtifacts; i++) {

                    //Use scanner helper class to find a clean empty line to work from
                    ScannerHelper.getEmptyLine(inputFile);

                    //Pass artifact block to artifact constructor
                    Artifact artifact = new Artifact(inputFile);
                }
            }

        }

    }

    //Print game details for debugging
    public void print() {
        System.out.println("Name of the game: " + this.gameName);
        System.out.println("Player Inventory: " + this.inventory);
        System.out.println("Starting point: " + currentPlace.name() + "\n");
    }

    //Used to set the starting point in the game before the play method is called
    public void setCurrentPlace(Place place) {
        currentPlace = place;
    }

    //Returns the current place of the game
    public static Place getCurrentPlace() {
        return currentPlace;
    }

    public void play() {

        //Print out welcome title
        System.out.println("------------------------------------");
        System.out.println("| Welcome to " + this.gameName + " |");
        System.out.println("------------------------------------");

        System.out.println("Current location: " + currentPlace.name());

        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        //Read in user command until they either type in quit, exit, or have gone into a place that is an exit
        while (!userInput.toLowerCase().contains("quit") && !userInput.toLowerCase().contains("exit") && !currentPlace.name().toLowerCase().contains("exit")) {

            System.out.println("Location description: " + currentPlace.description());

            System.out.print("Enter a command>");
            userInput = scanner.nextLine();
            System.out.println();

            //If user types in look then display the location name and the artifacts in the place
            if (userInput.toLowerCase().trim().startsWith("look")) {
                System.out.println("Current location: " + currentPlace.name());
                System.out.println("Artifacts Available: ");
                for (Artifact artifact_name : currentPlace.displayArtifacts()) {
                    System.out.println("-" + artifact_name.name() + ": " + artifact_name.description());
                }
            }
            //If user types in go then try to move the character to specified direction
            else if (userInput.toLowerCase().trim().startsWith("go")) {
                String[] directions = userInput.toLowerCase().split("go");
                currentPlace = currentPlace.followDirection(directions[1]);

            }
            //If user types in get then try to pick up artifact in current location
            else if (userInput.toLowerCase().trim().startsWith("get")) {
                String[] getArtifact = userInput.toLowerCase().split("get");
                if (currentPlace.getAvailableArtifacts().containsKey(getArtifact[1].trim())) {
                    addArtifact(currentPlace.getAvailableArtifacts().get(getArtifact[1].trim()));
                    currentPlace.removeArtifact(getArtifact[1].trim());
                    System.out.println("-> " + getArtifact[1].trim() + " has been added to your inventory");
                } else {
                    System.out.println("That item does not seem to be here");
                }

            }
            //If user types in drop then drop an artifact in current location
            else if (userInput.toLowerCase().trim().startsWith("drop")) {
                String[] dropArtifact = userInput.toLowerCase().split("drop");
                if (this.inventory.containsKey(dropArtifact[1].trim())) {
                    currentPlace.addArtifact(this.inventory.get(dropArtifact[1].trim()));
                    this.inventory.remove(dropArtifact[1].trim());
                    System.out.println("-> " + dropArtifact[1].trim() + " was dropped");
                } else {
                    System.out.println("You are not carrying that item");
                }

            }
            //If user types in use then try to use the specified artifact to see if it does anything based on the current location
            else if (userInput.toLowerCase().trim().startsWith("use")) {
                String[] useArtifact = userInput.toLowerCase().split("use");
                if (this.inventory.containsKey(useArtifact[1].trim())) {
                    System.out.println("-> Used " + useArtifact[1].trim());
                    currentPlace.useKey(this.inventory.get(useArtifact[1].trim()));
                } else {
                    System.out.println("Can't use it if you don't have it");
                }

            }
            //If user types in inv or inventory then display the player's current inventory
            else if (userInput.toLowerCase().contains("inv") || userInput.toLowerCase().contains("inventory")) {
                int totalValue = 0;
                int totalMobility = 0;

                System.out.println("=================================");
                System.out.println("|           Inventory           |");
                System.out.println("=================================");
                for (Artifact userArtifact : this.inventory.values()) {
                    System.out.println("Name: " + userArtifact.name());
                    System.out.println("Value: " + userArtifact.value());
                    System.out.println("Description: " + userArtifact.description());
                    if (userArtifact.size() > 1) {
                        System.out.println("Mobility: " + userArtifact.size() + " pounds");
                    } else {
                        System.out.println("Mobility: " + userArtifact.size() + " pound.");
                    }

                    totalValue += userArtifact.value();
                    totalMobility += userArtifact.size();
                    System.out.println();

                }
                System.out.println("Total Value: " + totalValue);
                System.out.println("Total Mobility: " + totalMobility);
                System.out.println("_________________________________");
                System.out.println();
            }
            else if(userInput.toLowerCase().contains("help"))
            {
                System.out.println("=================================");
                System.out.println("|       Available Commands      |");
                System.out.println("=================================");
                System.out.println("LOOK                ;Display the name of the current location and any artifacts found");
                System.out.println("GO XXX              ;Attempt to go in the direction XXX where XXX is any direction on the compass");
                System.out.println("GET XXX             ;Attempt to pick up artifact XXX from current location");
                System.out.println("DROP XXX            ;Drop an artifact XXX from the inventory in the current location");
                System.out.println("USE XXX             ;Use artifact XXX from the inventory in the current location");
                System.out.println("INV or INVENTORY    ;Display the player's inventory");
                System.out.println("HELP                ;Displays this help menu");
            }

        }
        System.out.println("Thanks for playing!");
        scanner.close();
    }

    public void addArtifact(Artifact artifact) {
        this.inventory.put(artifact.name(), artifact);
    }

    public void removeArtifact(Artifact artifact) {
        if (this.inventory.containsKey(artifact.name())) {
            this.inventory.remove(artifact.name());
        }
    }

}
