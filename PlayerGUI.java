import javax.swing.*;
import java.awt.*;

public class PlayerGUI implements UserInterface {

    private JFrame mainFrame;
    private JTextField events;
    private JPanel interaction;
    private JPanel directions;
    private JPanel buttonContainer;

    public PlayerGUI() {

        mainFrame = new JFrame("Dungeons and Dragons");
        mainFrame.setSize(500,500);
        mainFrame.setLayout(new GridLayout(0, 1));


        events = new JTextField();
        mainFrame.add(events);

        buttonContainer = new JPanel();
        buttonContainer.setLayout(new GridLayout(0,2));

        interaction = new JPanel();
        interaction.setLayout(new GridLayout(0,2));

        JButton inv_button = new JButton("Inventory");
        JButton get_button = new JButton("Get");
        JButton quit_button = new JButton("QUIT");

        interaction.add(inv_button);
        interaction.add(get_button);
        interaction.add(quit_button);

        buttonContainer.add(interaction);

        directions = new JPanel();
        directions.setLayout(new BorderLayout());

        JButton n_button = new JButton("NORTH");
        JButton e_button = new JButton("EAST");
        JButton s_button = new JButton("SOUTH");
        JButton w_button = new JButton("WEST");

        directions.add(n_button, BorderLayout.PAGE_START);
        directions.add(w_button, BorderLayout.LINE_START);
        directions.add(e_button, BorderLayout.LINE_END);
        directions.add(s_button, BorderLayout.PAGE_END);

        buttonContainer.add(directions);
        mainFrame.add(buttonContainer);


        mainFrame.setVisible(true);

    }

    @Override
    public void display(String string) {

    }

    @Override
    public String getLine() {
        return null;
    }
}
