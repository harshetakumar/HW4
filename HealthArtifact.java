import java.util.Scanner;

public class HealthArtifact extends Artifact  {

    private int healthRegeneration = 25;

    public HealthArtifact(Scanner inputFile)
    {
        super(inputFile);
    }

    @Override
    public int use()
    {
       return this.healthRegeneration;
    }

}
