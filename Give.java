import java.util.Random;
import java.util.Scanner;

public class Give implements Move {

    private Place place;
    private Character character;
    private IO io = new IO();

    //constructor
    public Give(Place place, Character character) {
        this.place = place;
        this.character = character;
    }

    public void execute() {

        Random rand = new Random();

        //Generate random health to give to the selected character
        int randomReplenish = rand.nextInt(20) + 1;

        //Increase the characters health
        this.character.increaseHealth(randomReplenish);

        //Used for debugging purposes
        //io.display(character.name() + " gained " + randomReplenish + " points of health");
    }

}
