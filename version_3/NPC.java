import java.util.Scanner;

/**
 * Creates an NPC object where it selects the decision maker to be from AI class
 */
public class NPC extends Character {

    public NPC(Scanner inputFile) {
        super(inputFile);
        super.setDecisionMaker(new AI());
    }
}
