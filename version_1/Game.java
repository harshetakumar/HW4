/*
Author: Giovanni Alanis
ACCC: galani3
UIN: 657681506
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private String gameName;
    private ArrayList<Place> places = new ArrayList<Place>();
    private Place currentPlace;

    //Creates a new game object with it's name
    Game(String name) {
        this.gameName = name;
    }

    //Add a new place to the list of current places available
    public void addPlace(Place newPlace) {
        this.places.add(newPlace);
    }

    //Print game details for debugging
    public void print() {
        System.out.println("Name of the game: " + this.gameName);
        System.out.println("Places in game: ");
        for (Place place : places) {
            place.print();
            System.out.println();
        }
        System.out.println("Starting point: " + this.currentPlace.name() + "\n");
    }

    //Used to set the starting point in the game before the play method is called
    public void setCurrentPlace(Place currentPlace) {
        this.currentPlace = currentPlace;
    }


    public void play() {
        System.out.println("----------------------");
        System.out.println("| Welcome to " + this.gameName + " |");
        System.out.println("----------------------");

        System.out.println("Current location: " + currentPlace.name());

        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        while (!userInput.toLowerCase().contains("quit") && !userInput.toLowerCase().contains("exit") && !this.currentPlace.name().toLowerCase().contains("exit")) {

            System.out.println("Description: " + currentPlace.description());

            System.out.print("Enter a direction>");
            userInput = scanner.nextLine();
            System.out.println();

            if (userInput.toLowerCase().contains("look")) {
                System.out.println("Current location: " + this.currentPlace.name());
            }
            else if(userInput.toLowerCase().contains("go"))
            {
                String [] directions = userInput.split(" ");
                this.currentPlace = this.currentPlace.followDirection(directions[1]);
            }

        }
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
