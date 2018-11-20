import java.util.HashMap;

public class Exit implements Move {
    private Character character;
    private IO io = new IO();

    /**
     * Creates a exit object that will remove a character from the game
     *
     * @param character Character that will be removed from the game
     * @return Exit object
     */
    public Exit(Character character) {
        this.character = character;
    }

    /**
     * Removes character from game
     *
     * @return Execute object
     */
    @Override
    public void execute() {

        //Notify user of event
        io.display("================================================");
        io.display("PLAYER: " + character.name() + " has left the game");
        io.display("================================================\n");

        //Tell the character to leave the game
        character.leaveGame();
    }
}
