import java.util.Scanner;

public class DarkPlace extends Place {

    private IO io = new IO();

    public DarkPlace(Scanner inputFile) {
        super(inputFile);
        super.illuminated = false;
    }

    @Override
    public void use(Artifact artifact) {
        if(super.illuminated)
        {
            //If the place is already illuminated that return false
            io.display("================================================");
            io.display("* The current place already has plenty of light");
            io.display("================================================");

        }
        else
        {
            //If the place is not illluminated, then illuminate it and return true
            super.illuminated = true;
            io.display("================================================");
            io.display("* The current place illuminated itself with the blazing torch");
            io.display("================================================");
        }

    }
}
