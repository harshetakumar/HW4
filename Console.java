/**
 * Prints out numerous new lines to the terminal to give the illusion of the screen clearing
 */
public class Console {
    private static IO io = new IO();
    public static void clearScreen() {
        for (int i = 0; i < 30; i++) {
            io.display("\n");
        }
    }
}
