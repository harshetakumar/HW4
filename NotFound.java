/**
 * Creates a NotFound object in where a player is notified if a typed in command was invalid
 */
public class NotFound implements Move {

    private Character character;
    private IO io = new IO();

    public NotFound(Character character) {
        this.character = character;
    }

    @Override
    public void execute() {
        io.display("================================================");
        io.display("PLAYER: " + character.name() + " did nothing");
        io.display("================================================\n");
    }
}
