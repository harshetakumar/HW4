import java.util.Scanner;

public class Artifact {

    private String name;
    private String description;
    private int value;
    private int mobility;
    private int keyPattern;
    private int id;


    //Create a new artifact object using the scanner to read from file
    public Artifact(Scanner fileInput) {

        String [] fileLine;
        String [] parsedFileLine;
        String description = "";
        int numOfDescriptionLines;

        Place artifact_place = Place.getPlaceById(Integer.parseInt(fileInput.nextLine().trim()));

        this.id = fileInput.nextInt();
        this.value = fileInput.nextInt();
        this.mobility = fileInput.nextInt();
        this.keyPattern = fileInput.nextInt();
        this.name = fileInput.nextLine().toLowerCase().split("//")[0].trim();
        numOfDescriptionLines = Integer.parseInt(fileInput.nextLine());
        for(int i = 0; i < numOfDescriptionLines; i++)
        {
            description += fileInput.nextLine() + " ";
        }
        this.description = description;

        artifact_place.addArtifact(this);

    }

    //Returns the value of the artifact
    public int value() {
        return this.value;
    }

    //Returns the mobility of the artifact
    public int size() {
        return this.mobility;
    }

    //Returns the name of the artifact
    public String name() {
        return this.name;
    }

    //Returns the description
    public String description() {
        return this.description;
    }

    //Returns the key pattern to try to use artifact
    public int use() {
        return this.keyPattern;
    }

    //Prints out artifact information for debuggin purposes
    public void print() {
        System.out.println("Name: " + name());
        System.out.println("Description: " + description());
        System.out.println("Value: " + value());
        System.out.println("Mobility: " + size());
        System.out.println("Key Pattern: " + use());
    }


}
