import java.util.HashMap;
import java.util.Random;

public class GivingAI implements DecisionMaker {

    private IO io = new IO();

    @Override
    public Move getMove(Character character, Place place) {

        Random rand = new Random();

        //Generate random number to decide what move to make
        int randomMove = rand.nextInt(3);


        //Used for debugging purposes
        //io.display("Getting health from : " + character.name());

        //Get all the available characters from current place to see who to give health to
        HashMap<String, Character> availableCharacters = place.getCharacters();

        //Only pick a character if there is at least one character to pick from
        if (availableCharacters.size() > 0) {
            int randomCharacter = rand.nextInt(availableCharacters.size());
            int counter = 0;

            for (Character availableCharacter : availableCharacters.values()) {
                //Check to see when the counter equals the randomly selected number, check to make sure the character we give health to is only a player, check the player is still playing
                if (counter == randomCharacter && availableCharacter instanceof Player && availableCharacter.isPlaying()) {

                    //Used for more debugging
                    //io.display(character.name() + " is going to give health points " + availableCharacter.name());

                    //Notify player who are they receiving health from
                    io.display("================================================");
                    io.display("* " + character.name() + " is healing you");
                    io.display("================================================");

                    //Create health object on selected character
                    return new Give(place, availableCharacter);
                }

                counter++;
            }
        }


        //If there is no characters to give health points to then just make the Attacker wait
        return new Wait();
    }
}

	
	


