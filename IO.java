import java.util.Scanner;

public class IO {

    public static final int TEXT = 0;
    public static final int GUI_1 = 1;
    public static final int GUI_2 = 2;
    public static final int GUI_3 = 3;
    private static UserInterface implementor;


    public void display(String text)
    {
        implementor.display(text);
    }

    public String getLine()
    {
        if(implementor instanceof TextInterface) {
            Scanner scanner = KeyboardScanner.getKeyboardScanner();
            String userInput = scanner.nextLine();
            return userInput;
        }
        else
        {
            return implementor.getLine();
        }

    }

    public void selectInterface(int gui)
    {
        switch(gui)
        {
            case 1:
                implementor = GUI.getPlayerGUI("Game");
                break;
            default:
                implementor = new TextInterface();
        }
    }

    public void selectInterface(int gui, String gameTitle)
    {
        switch(gui)
        {
            case 1:
                implementor = GUI.getPlayerGUI(gameTitle);
                break;
            default:
                implementor = new TextInterface();
        }
    }

    public void updateGUI(Character c, Place p)
    {
        if(implementor instanceof GUI)
        {
            ((GUI) implementor).updateGUI(c, p);
        }
    }

    public int checkGUI()
    {
        if(implementor instanceof TextInterface)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }

}
