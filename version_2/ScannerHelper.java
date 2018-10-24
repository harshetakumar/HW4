import java.util.Scanner;


//Scanner Helper class was designed to help retrieve either clean lines or the next available integer in the file
public class ScannerHelper{

    static public void getEmptyLine(Scanner file)
    {
        while(!file.nextLine().isEmpty())
        {
            file.nextLine();
        }
    }

    static public void getNextInt(Scanner file)
    {
        while (!file.hasNextInt()) {
            file.nextLine();
        }
    }
}
