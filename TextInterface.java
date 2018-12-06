import java.util.Scanner;

public class TextInterface implements UserInterface {

    @Override
    public void display(String text)
    {
        System.out.println(text);
    }

    @Override
    public String getLine()
    {
        Scanner scanner = KeyboardScanner.getKeyboardScanner();
        String userInput = scanner.nextLine();
        return userInput;
    }
}
