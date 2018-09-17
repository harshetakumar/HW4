/*
Author: Giovanni Alanis
ACCC: galani3
UIN: 657681506
*/

import java.util.ArrayList;

public class Place {

    private int id;
    private String name;
    private String description;
    private ArrayList<Direction> directions = new ArrayList<Direction>();

    //Create a new place object with its id, name, and description
    Place(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
        System.out.println("Available directions: ");
        for (Direction direction : directions) {
            direction.print();
            
        }
    }


}
