import java.util.Scanner;

public class IO {

    public static final int TEXT = 0;
    public static final int GUI_1 = 1;
    public static final int GUI_2 = 2;
    public static final int GUI_3 = 3;
    private UserInterface implementor;
    public IO()
    {

    }

    public void display(String string)
    {
        System.out.println(string);
    }

    public String getLine()
    {
        Scanner scanner = KeyboardScanner.getKeyboardScanner();
        String userInput = scanner.nextLine();
        return userInput;

    }

    public void selectInterface(int gui)
    {
        switch(gui)
        {
            case 1:
                implementor = new PlayerGUI();
                break;
            default:
                implementor = null;
        }
    }

}
