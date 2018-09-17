CS342 Homework 1
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


####How to play: 
The game starts by displaying the name of the game, starting location, description of the starting location, and the available directions the user can go.
From there, the user is prompted to enter a direction in the format of 'GO XXX', where XXX is either North, East, South, West, Up, or Down. 
The user can also input 'QUIT' or 'EXIT' to quit the game. 
Once a user has inputted a direction, the game will check and display to the user and updated location based on whether a direction is available.
If a certain direction turns out to be locked, then the game will notify the user that the direction inputted is currently locked. 