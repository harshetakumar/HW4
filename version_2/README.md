CS342 Homework 2
================
Giovanni Alanis UIN: 657681506
---------------
ACCC: galani3
---------------

####Getting Started: 

To start playing this game, all you have to do is run make and it will automatically compile and run the game. 
The Makefile also includes a make clean command to remove the .class files and clean up the directory. 

```
make        //Compiles and runs java program
```
```
make clean  //Removes .class files and cleans up directory file 
```
Once it runs it will first display my student information and then ask for the gdf file to load from. If an invalid file name is entered, then the program 
will keep asking to enter a file name until a valid one is entered. 

####How to play: 
The game starts by displaying the name of the game, starting location, description of the starting location, and the available directions the user can go.
From there, the user is prompted to enter a command. The user can type in help to get a list of commands available in the game. 
The user can also input 'QUIT' or 'EXIT' to quit the game. 

####Navigation
The user can navigate through the game by typing in of the format 'GO XXX', where XXX can be any direction from the standard compass. 
Once a user has inputted direction, the game will check and display to the user and updated location based on whether the direction entered is available.
If a certain direction turns out to be locked, then the game will notify the user that the direction inputted is currently locked. 

####Inventory
The user can type in 'inv' or 'inventory' to display any current artifacts the user is holding. 

####Artifacts
#####Picking Items 
The user can type in 'get XXX', where XXX is the artifact name that is available in the current location. If the user types in an artifact that is not available, 
then the game will notify them of this.

#####Dropping Items
The user can type in 'drop XXX', where XXX is the artifact name that is currently in the user's inventory. If the user tries to drop an artifact that is not in the 
inventory, then the game will notify them of this. 

#####Using Items
The user can type in 'use XXX', where XXX is the artifact name that is currently in the user's inventory. The game will notify the user if anything happens once the 
artifact is used as well if nothing happens. 


####Extra Methods Used
In this version of the project, I implemented a ScannerHelper class which was used to help parse the gdf file. The main methods implemented were finding the next empty line
in the file and scanning through the file until the next integer is found. 

####Things to notice
I noticed that when it came to searching for the file name on my IDE, I would have to use System.getProperty("user.dir") + /src/ + file name in the 
File class to be able to find it correctly. Once I uploaded it to bert, the only way to find the file was to pass the file name directly to the 
File class. The version I am submitting will be using the file name directly into the File class.