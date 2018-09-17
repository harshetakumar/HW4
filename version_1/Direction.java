/*
Author: Giovanni Alanis
ACCC: galani3
UIN: 657681506
*/

public class Direction {

    private int id;
    private Place from;
    private Place to;
    private String direction;
    private boolean isLocked = false;


    //Create a new direction object with its id, from, to, and direction
    Direction(int id, Place from, Place to, String dir) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.direction = dir;
    }

    //Check to see if this direction matches the given direction
    public boolean match(String s) {
        if (this.direction.toLowerCase().contains("north")) {
            if (s.toLowerCase().trim().matches(this.direction.toLowerCase()) || s.toLowerCase().trim().matches("n")) {
                return true;
            }
        } else if (this.direction.toLowerCase().contains("east")) {
            if (s.toLowerCase().trim().matches(this.direction.toLowerCase()) || s.toLowerCase().trim().matches("e")) {
                return true;
            }
        } else if (this.direction.toLowerCase().contains("south")) {
            if (s.toLowerCase().trim().matches(this.direction.toLowerCase()) || s.toLowerCase().trim().matches("s")) {
                return true;
            }
        } else if (this.direction.toLowerCase().contains("west")) {
            if (s.toLowerCase().trim().matches(this.direction.toLowerCase()) || s.toLowerCase().trim().matches("w")) {
                return true;
            }
        } else if (this.direction.toLowerCase().contains("up")) {
            if (s.toLowerCase().trim().matches(this.direction.toLowerCase()) || s.toLowerCase().trim().matches("u")) {
                return true;
            }
        } else if (this.direction.toLowerCase().contains("down")) {
            if (s.toLowerCase().trim().matches(this.direction.toLowerCase()) || s.toLowerCase().trim().matches("d")) {
                return true;
            }
        }

        //Return false if none return true
        return false;
    }

    //Lock this place
    public void lock() {
        this.isLocked = true;
    }

    //Unlock this place
    public void unlock() {
        this.isLocked = false;
    }

    //Check to see if this place is locked
    public boolean isLocked() {
        return this.isLocked;
    }

    //Return the place this direction goes to if it is unlocked, else return the original place
    public Place follow() {
        if (!this.isLocked) {
            return this.to;
        } else {
            System.out.println("This room is currently locked!");
            return this.from;
        }

    }

    //Print details about direction for debugging
    public void print() {
        System.out.println("ID: " + this.id);
        System.out.println("From: " + this.from.name());
        System.out.println("To: " + this.to.name());
        System.out.println("Direction: " + this.direction);
        System.out.println("Locked? : " + this.isLocked);
    }
}
