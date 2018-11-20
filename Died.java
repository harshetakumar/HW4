public class Died implements Move {
    private Character character;
    private IO io = new IO();
    
    public Died(Character character)
    {
        this.character = character;
    }

    public void execute()
    {
        //Notify the user that the player has fainted
        io.display("==========================================");
        io.display("* " + character.name() + " has been slained!");
        io.display("==========================================");

        //Tell the character to leave the game
        character.leaveGame();
    }
}
