public class Help implements Move {

    private IO io = new IO();

    /**
     * Creates a help object in where a user can see all the available commands in the game
     */
    public Help() {

    }

    @Override
    public void execute() {
        io.display("=================================");
        io.display("|       Available Commands      |");
        io.display("=================================");
        io.display("LOOK                ;Display the name of the current location and any artifacts found");
        io.display("GO XXX              ;Attempt to go in the direction XXX where XXX is any direction on the compass");
        io.display("GET XXX             ;Attempt to pick up artifact XXX from current location");
        io.display("DROP XXX            ;Drop an artifact XXX from the inventory in the current location");
        io.display("USE XXX             ;Use artifact XXX from the inventory in the current location");
        io.display("INV or INVENTORY    ;Display the player's inventory");
        io.display("HELP                ;Displays this help menu\n");
    }
}
