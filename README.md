# CS342
CS342 Homework 3

1)Giovanni Alanis UIN: 657681506
ACCC: galani3   

2)Harsheta Kumar UIN: 661363357  NetID: hkumar7

3)Prachi Thakkar NetID: pthakk23

####Compatibility: This version of the game is compatible with GDF files version >3.0.
GDF files with version <4.0: The game will play with only one player as it was previously.
GDF files with version >4.0: The game will play with the players specified in the GDF file or if none are found, then it will notify users to input the number of players and create them.

NOTE: There will be a file of version >3.0 called mysticcity.gdf and a file of version 4.0 called mysticcity4.gdf
      and modified file of version 4.0 called mysticcity5.gdf that allows the NPC to be either "attackers" or "givers"

####Updates to game

NPC characters are now present in the game.
AI has been implemented so NPC characters can execute the same moves as a normal player on their own, except for 'QUIT', 'EXIT', 'INVENTORY', and 'LOOK'
Game now recognizes GDF file version and determines what to do.

Giver NPC: The gdf file was modified so that the Leprechuan is a giver and the Ogre is an attacker. The Leprechaun is located in the Pool of Enchantment. The NPC randomly gives the selected character a random number of health points. If the character recieves a number of health points that would result in the health points exceeding 100, then their health is just set to the maximum number of points which is a hundred. 

####Getting Started:

To start playing this game, all you have to do is run make and it will automatically compile and run the game. The Makefile also includes a make clean command to remove the .class files and clean up the directory.

make        //Compiles and runs java program
make clean  //Removes .class files and cleans up directory file 
Once it runs it will first display my student information and then ask for the gdf file to load from. If an invalid file name is entered, then the program will keep asking to enter a file name until a valid one is entered.

####How to play: The game starts by displaying the name of the game. From there it will cycle through all the players displaying their starting location, description of the starting location, the available directions the user can go, any artifacts available, and any other characters present. From there, each player is prompted to enter a command. Once every player has entered a command, the screen will clear and it will notify the players on what happened based on the command they typed in. The user can type in help to get a list of commands available in the game. The user can also input 'QUIT' or 'EXIT' to quit the game.

####Navigation The user can navigate through the game by typing in of the format 'GO XXX', where XXX can be any direction from the standard compass. Once a user has inputted direction, the game will check and display to the user and updated location based on whether the direction entered is available. If a certain direction turns out to be locked, then the game will notify the user that the direction inputted is currently locked.

####Inventory The user can type in 'inv' or 'inventory' to display any current artifacts the user is holding.

####Artifacts #####Picking Items The user can type in 'get XXX', where XXX is the artifact name that is available in the current location. If the user types in an artifact that is not available, then the game will notify them of this.

#####Dropping Items The user can type in 'drop XXX', where XXX is the artifact name that is currently in the user's inventory. If the user tries to drop an artifact that is not in the inventory, then the game will notify them of this.

#####Using Items The user can type in 'use XXX', where XXX is the artifact name that is currently in the user's inventory. The game will notify the user if anything happens once the artifact is used as well if nothing happens.

####Extra Helper Classes Used


Console: Used to print out new lines to give the illusion of the game display clearing
ScannerHelper: Used to help parse the gdf file. The main methods implemented were finding the next empty line in the file and scanning through the file until the next integer is found.
Dungeon style game written in Java 
