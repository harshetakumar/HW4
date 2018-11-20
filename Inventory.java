public class Inventory implements Move {

    private Character character;
    private IO io = new IO();

    /**
     * Creates a Inventory object in where it displays a character's current inventory
     *
     * @param character The character to check inventory from
     * @return Inventory object
     */
    public Inventory(Character character) {
        this.character = character;
    }

    /**
     * Executes the inventory object to display the characters' current inventory
     */
    @Override
    public void execute() {
        int totalValue = 0;
        int totalMobility = 0;

        //Display the character's inventory
        io.display("================================================");
        io.display("PLAYER: " + character.name() + " Inventory");
        io.display("================================================");
        for (Artifact userArtifact : this.character.retrieveInventory().values()) {
            io.display("Name: " + userArtifact.name());
            io.display("Value: " + userArtifact.value());
            io.display("Description: " + userArtifact.description());

            //Check to see if artifact weights more than one pound
            if (userArtifact.size() > 1) {
                io.display("Mobility: " + userArtifact.size() + " pounds");
            } else {
                io.display("Mobility: " + userArtifact.size() + " pound.");
            }

            //Calculate total value and total mobility in inventory
            totalValue += userArtifact.value();
            totalMobility += userArtifact.size();
            io.display("\n");

        }

        //Print out statistics
        io.display("Total Value: " + totalValue);
        io.display("Total Mobility: " + totalMobility);
        io.display("\n");
    }
}
