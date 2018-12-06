import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class GUI implements UserInterface {

    private JFrame mainFrame;
    private JTextArea events;
    private JPanel interaction;
    private JPanel directions;
    private JPanel buttonContainer;
    private JScrollPane scrollingTextArea;
    private JLabel playerName;
    private JProgressBar playerHealthBar;
    private JLabel playerHealthLabel;
    private JPanel playerInfo;
    private JPanel playerHealthPanel;
    private JButton invButton;
    private JButton lookButton;
    private static GUI gui = null;
    private Character c;
    private Place p;
    private String commandBuffer;
    private JTextArea textGUIEvents;

    private GUI(String gameTitle) {

        mainFrame = new JFrame(gameTitle);
        mainFrame.setSize(600, 600);
        mainFrame.setLayout(new GridLayout(0, 1));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        //Create a tabbed panel to put into the main frame, one tab will hold the GUI and one will hold the text GUI
        JTabbedPane tabbedPane = new JTabbedPane();

        //GUI container to contain all the gui components
        JPanel guiContainer = new JPanel();
        guiContainer.setLayout(new GridLayout(0, 1));

        //Create a text area to display an event after a player had made a move
        events = new JTextArea();
        events.setEditable(false);
        events.setLineWrap(true);
        events.setWrapStyleWord(true);

        //Make the text area a scrolling panel since we want to be able to see previous events
        scrollingTextArea = new JScrollPane(events, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        guiContainer.add(scrollingTextArea);

        //Create panel to hold button inputs & status
        buttonContainer = new JPanel();
        buttonContainer.setLayout(new GridLayout(0, 2));


        //Panel to hold interact buttons and status
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(0, 1));

        //Create panel to hold player information
        playerInfo = new JPanel();
        GridLayout playerInfoLayout = new GridLayout(0, 1);
        playerInfo.setLayout(playerInfoLayout);
        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        playerInfo.setBorder(loweredBevel);

        //Create a label to display the player's name
        playerName = new JLabel();

        //Panel to hold health bar
        playerHealthPanel = new JPanel();
        playerHealthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        //Create label to display health
        playerHealthLabel = new JLabel();
        playerHealthLabel.setText("Health: ");

        playerHealthPanel.add(playerHealthLabel);

        //Create a progress bar to display player's current health
        playerHealthBar = new JProgressBar(0, 100);
        playerHealthBar.setValue(100);
        playerHealthBar.setStringPainted(true);

        playerHealthPanel.add(playerHealthBar);

        playerInfo.add(playerName);
        playerInfo.add(playerHealthPanel);

        statusPanel.add(playerInfo);

        //Create panel to hold buttons for interaction
        interaction = new JPanel();
        interaction.setLayout(new GridLayout(0, 2));

        //Create a button to check inventory and one to look around
        invButton = new JButton();
        lookButton = new JButton();

        //Try to load the icon images for the buttons
        try {
            Image invIcon = ImageIO.read(getClass().getResource("resources/chest.png"));
            invButton.setIcon(new ImageIcon(invIcon));
        } catch (IOException e) {
            System.out.println("Unable to retrieve inventory button icon");
        }

        try {
            Image lookIcon = ImageIO.read(getClass().getResource("resources/magnifying_glass.png"));
            lookButton.setIcon(new ImageIcon(lookIcon));
        } catch (IOException e) {
            System.out.println("Unable to retrieve look button icon");
        }

        //Set action commands for the inventory and look button
        invButton.setActionCommand("INVENTORY");
        lookButton.setActionCommand("LOOK");

        //Set the GUI listener for both of them
        lookButton.addActionListener(new GUIListener());
        invButton.addActionListener(new GUIListener());

        interaction.add(lookButton);
        interaction.add(invButton);

        statusPanel.add(interaction);

        buttonContainer.add(statusPanel);

        //Create panel to hold buttons for moving in a direction
        directions = new JPanel();
        directions.setLayout(new GridLayout(0, 1));

        buttonContainer.add(directions);

        guiContainer.add(buttonContainer);

        //Create a panel to hold the text interface for the game
        JPanel textContainer = new JPanel();
        textContainer.setLayout(new GridLayout(0, 1));

        //Create a text area to display the events in the text interface
        textGUIEvents = new JTextArea();
        textGUIEvents.setEditable(false);
        textGUIEvents.setLineWrap(true);
        textGUIEvents.setWrapStyleWord(true);

        //Make the text area for the text interface into a scroll text area
        JScrollPane textGUIScroll = new JScrollPane(textGUIEvents, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //Create a container to hold a label and the textfield input
        JPanel textGUIContainer = new JPanel(new FlowLayout());

        JLabel notifyForCommand = new JLabel("Enter a command>");
        JTextField textGUIInput = new JTextField(30);
        textGUIInput.addActionListener(new GUIListener());

        textGUIContainer.add(notifyForCommand);
        textGUIContainer.add(textGUIInput);

        //Add the text area and input fields into the text tab
        textContainer.add(textGUIScroll);
        textContainer.add(textGUIContainer);

        //Label the first tab as GUI
        tabbedPane.addTab("GUI", null, guiContainer, "Switch to GUI");
        tabbedPane.setVisible(true);

        //Label the second tab as GUI
        tabbedPane.addTab("Text", null, textContainer, "Switch to text");
        tabbedPane.setVisible(true);

        //Add the tab panel to the mainframe
        mainFrame.add(tabbedPane);
        mainFrame.setVisible(true);

    }

    @Override
    public void display(String text) {

        //Display any string that is passed in the GUI text area and the text interface text area
        if (text.equals("\n")) {
            events.append(text);
            textGUIEvents.append(text);
        } else {
            events.append(text + "\n");
            textGUIEvents.append(text + "\n");
        }

        //Try to make the scroll pane auto scroll to the most recent events
        JScrollBar vertical = scrollingTextArea.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
        scrollingTextArea.revalidate();
        scrollingTextArea.repaint();
    }

    @Override
    public String getLine() {

        //Wait until the GUI sends a notifyAll() command, then we can read from the commandBuffer string and return it
        synchronized (mainFrame) {
            try {
                mainFrame.wait();
                return commandBuffer;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        return "Nothing was found";
    }

    public static GUI getPlayerGUI(String gameTitle) {
        if (gui == null) {
            gui = new GUI(gameTitle);
        }

        return gui;
    }

    public void updateGUI(Character c, Place p) {
        this.c = c;
        this.p = p;

        //Update the player info panel with the current player's information
        playerName.setText(" Player: " + c.name());
        playerHealthBar.setValue(c.checkHealth());

        //Remove the previous character's direction buttons
        directions.removeAll();

        //Retrieve the directions for the the current player's turn
        ArrayList<Direction> availableDirections = p.getDirections();

        //Iterate through directions, create new buttons for directions, and add gui listener
        for (Direction direction : availableDirections) {
            JButton button = new JButton("GO " + direction.directionType());
            button.setActionCommand("GO " + direction.directionType());
            button.addActionListener(new GUIListener());

            directions.add(button);
        }

        //Need this step in order to make sure the changes are updated on GUI
        directions.revalidate();
        directions.repaint();

    }


    private class GUIListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //Check which button was pressed or check if something was typed in the text interface

            if (e.getActionCommand() == "INVENTORY") {

                //If the inventory button was clicked, then we first want to check if there is any inventory to display
                if (c.retrieveInventory().size() > 0) {

                    //Create a pop up to display the player's current inventory
                    JDialog inventoryDialog = new JDialog(mainFrame, c.name() + " Inventory");
                    inventoryDialog.setLayout(new GridLayout(0, 2));
                    inventoryDialog.setSize(300, 200);
                    inventoryDialog.setLocationRelativeTo(null);

                    //Add a window listener to the inventory pop up, if the player closes out the inventory, then count it as a move
                    inventoryDialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                            commandBuffer = "";
                            synchronized (mainFrame) {
                                mainFrame.notifyAll();
                            }
                        }
                    });


                    //Create a panel to hold a list of the inventory and available buttons
                    JPanel artifactPanel = new JPanel();
                    artifactPanel.setLayout(new GridLayout(0, 1));

                    //Create a list to display the user's inventory
                    final DefaultListModel artifactNames = new DefaultListModel();
                    for (Artifact artifact : c.retrieveInventory().values()) {
                        artifactNames.addElement(artifact.name());
                    }

                    JList artifactList = new JList(artifactNames);
                    artifactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    JScrollPane artifactScrollList = new JScrollPane(artifactList);

                    artifactPanel.add(artifactScrollList);

                    inventoryDialog.add(artifactPanel);

                    //Create a panel to hold the use and drop buttons in the inventory pop up
                    JPanel inventoryButtonPanel = new JPanel();
                    inventoryButtonPanel.setLayout(new GridLayout(0, 1));

                    JButton useButton = new JButton("Use");
                    JButton dropButton = new JButton("Drop");

                    //Add event listeners to the use and drop button
                    useButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (artifactList.getSelectedIndex() != -1) {
                                commandBuffer = "use " + artifactList.getSelectedValue();
                                inventoryDialog.setVisible(false);
                                synchronized (mainFrame) {
                                    mainFrame.notifyAll();
                                }
                            }
                        }
                    });

                    dropButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (artifactList.getSelectedIndex() != -1) {
                                commandBuffer = "drop " + artifactList.getSelectedValue();
                                inventoryDialog.setVisible(false);
                                synchronized (mainFrame) {
                                    mainFrame.notifyAll();
                                }
                            }
                        }
                    });

                    inventoryButtonPanel.add(useButton);
                    inventoryButtonPanel.add(dropButton);

                    inventoryDialog.add(inventoryButtonPanel);

                    inventoryDialog.setVisible(true);
                } else {

                    //If the player has no inventory then notify the player about it
                    JOptionPane.showMessageDialog(mainFrame, "You have no artifacts in your inventory", c.name() + " Inventory", JOptionPane.PLAIN_MESSAGE);
                    commandBuffer = "";
                    synchronized (mainFrame) {
                        mainFrame.notifyAll();
                    }
                }

            }
            //If the look button was clicked then create a pop up that will display any characters present, available artifacts, and available directions
            else if (e.getActionCommand() == "LOOK") {

                //Create pop up to display when the look button is clicked
                JDialog lookDialog = new JDialog(mainFrame, "Look");
                lookDialog.setLayout(new GridLayout(2, 1));
                lookDialog.setSize(500, 400);
                lookDialog.setLocationRelativeTo(null);

                //Add window listener to the pop up so when it is closed it counts as a move
                lookDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        commandBuffer = "";
                        synchronized (mainFrame) {
                            mainFrame.notifyAll();
                        }
                    }
                });

                //Create a text area that will display the current location's description and available directions
                JTextArea lookEvent = new JTextArea();
                lookEvent.setEditable(false);
                lookEvent.setLineWrap(true);
                lookEvent.setWrapStyleWord(true);

                //Preset text to display on the text area
                lookEvent.append("==========================================\n");
                lookEvent.append("* PLAYER: " + c.name() + " looked around\n");
                lookEvent.append("==========================================\n");
                lookEvent.append("Current location: " + p.name() + "\n");
                lookEvent.append("Description: " + p.description() + "\n");

                lookDialog.add(lookEvent);

                //Create panel that will hold the available artifacts list and the characters present
                JPanel characterAndArtifactInteraction = new JPanel();
                characterAndArtifactInteraction.setLayout(new GridLayout(1, 0));

                //Create text area to display the characters present at current location
                JTextArea charactersPresent = new JTextArea();
                if (p.getCharacters().size() > 1) {
                    charactersPresent.append("Characters Present:\n");
                    for (Character character : p.getCharacters().values()) {
                        charactersPresent.append(character.name() + "\n");
                    }
                    characterAndArtifactInteraction.add(charactersPresent);
                }

                //Display available artifacts in a list in current place
                if (p.getAvailableArtifacts().size() > 0) {
                    final DefaultListModel artifactNames = new DefaultListModel();
                    for (Artifact artifact : p.getAvailableArtifacts().values()) {
                        artifactNames.addElement(artifact.name());
                    }

                    JList lookArtifactList = new JList(artifactNames);
                    lookArtifactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    JScrollPane artifactScrollList = new JScrollPane(lookArtifactList);

                    characterAndArtifactInteraction.add(artifactScrollList);

                    //Create a get button to be able to select which artifact to get
                    JButton getButton = new JButton("Get");
                    getButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (lookArtifactList.getSelectedIndex() != -1) {
                                commandBuffer = "get " + lookArtifactList.getSelectedValue();
                                lookDialog.setVisible(false);
                                synchronized (mainFrame) {
                                    mainFrame.notifyAll();
                                }

                            }
                        }
                    });

                    characterAndArtifactInteraction.add(getButton);
                }

                lookDialog.add(characterAndArtifactInteraction);
                lookDialog.setVisible(true);


            }
            //If the button has a command that starts with GO then we know the player is trying to move in a direction
            else if (e.getActionCommand().startsWith("GO")) {
                commandBuffer = e.getActionCommand();
                synchronized (mainFrame) {
                    mainFrame.notifyAll();
                }
            }
            //If we reach this point, then we assume the player is using the text interface version of the game
            else {
                commandBuffer = e.getActionCommand();
                synchronized (mainFrame) {
                    mainFrame.notifyAll();
                }
            }
        }
    }


}
