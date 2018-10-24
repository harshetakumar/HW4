/*
Author: Giovanni Alanis
ACCC: galani3
UIN: 657681506
*/

import java.util.*;

public class Place {

    private int id;
    private String name;
    private String description;
    private ArrayList<Direction> directions = new ArrayList<Direction>();
    private HashMap<String, Artifact> availableArtifacts = new HashMap<String, Artifact>();
    static private HashMap<Integer, Place> availablePlaces = new HashMap<Integer, Place>();

    Place(int id, String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        availablePlaces.put(id, this);
    }

    //Create a new place object with its id, name, and description
    Place(Scanner inputFile) {

        String placeName;
        String description = "";
        int numOfDescriptionLines;
        int placeId;

        placeId = inputFile.nextInt();
        placeName = inputFile.nextLine().split("//")[0].trim();

        numOfDescriptionLines = inputFile.nextInt();
        inputFile.nextLine();

        for (int j = 0; j < numOfDescriptionLines; j++) {
            description += inputFile.nextLine() + " ";
        }

        this.id = placeId;
        this.name = placeName;
        this.description = description;
        availablePlaces.put(placeId, this);

    }

    //Sets the name for the place
    public String name() {
        return this.name;
    }

    //Sets the description for the place
    public String description() {
        return this.description;
    }

    //Adds a new direction object to the list of current directions available
    public void addDirection(Direction newDirection) {
        this.directions.add(newDirection);
    }

    //Tries to follow the direction given by user and will return a new place if it is available
    public Place followDirection(String givenDirection) {
        for (Direction direction : this.directions) {
            if (direction.match(givenDirection)) {
                return direction.follow();
            }
        }
        return this;
    }

    //Print details about place for debugging
    public void print() {
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + name());
        System.out.println("Description: " + description());
        System.out.println("Available directions " + "(" + directions.size() + "): ");
        for (Direction direction : directions) {
            direction.print();

        }
    System.out.println("Available Artifacts: ");
        for (Artifact artifact : availableArtifacts.values()) {
            artifact.print();
        }
    }

    public Collection<Artifact> displayArtifacts() {
        return availableArtifacts.values();
    }

    public void addArtifact(Artifact artifact) {
        availableArtifacts.put(artifact.name(), artifact);
    }

    public void removeArtifact(String artifactName) {
        if (availableArtifacts.containsKey(artifactName)) {
            availableArtifacts.remove(artifactName);
        }
    }

    public void useKey(Artifact artifact) {
        boolean eventHappened = false;
        for (Direction direction : this.directions) {
            if(direction.useKey(artifact))
            {
                eventHappened = true;
            }
        }

        if(!eventHappened)
        {
            System.out.println("- Nothing happened");
        }
    }

    public HashMap<String, Artifact> getAvailableArtifacts() {

        return this.availableArtifacts;
    }


    public static Place getPlaceById(int id)
    {
        if(availablePlaces.containsKey(id))
        {
            return availablePlaces.get(id);
        }
        else
        {
            System.out.println("Could not find Place with Id: " + id);
            return null;
        }
    }

}
