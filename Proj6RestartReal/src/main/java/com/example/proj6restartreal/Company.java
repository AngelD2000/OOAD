package com.example.proj6restartreal;

public class Company {
    Firefighter firefighters[]= null;
    Integer activeFirefighter = 0;

    Company(Game game){
        firefighters= new Firefighter[Util.numFirefighters];
        for (int i = 0; i < Util.numFirefighters; i++) {
            int[] location = Util.firefighterLocations[i];
            Util.print("This is it babyee!");
            Util.print("" + location[0] + location[1]);
            firefighters[i] = new Firefighter(game.getMap(), location[0], location[1]);
            firefighters[i].setImage(Util.firefighterImages[i]);
        }
    }

    /**
     * Gets the location of the active firefighter
     * @return The associated square
     */
    public Square getActiveLocation(){
        return firefighters[activeFirefighter].getLoc();
    }

    /**
     * Gets the current active firefighter
     * @return The associated FF
     */
    public Firefighter getActiveFf(){
        return firefighters[activeFirefighter];
    }

    /**
     * Moves the firefighter
     */
    public void move(Square square){
        firefighters[activeFirefighter].setLoc(square.getX(), square.getY());
    }

    /**
     * Goes to the next FF in the company.
     * Will also save actions of last FF and say how many actions new FF saved
     * @return The number of actions new FF had saved
     */
    public int nextFirefighter(int saved){
        getActiveFf().setActionsSaved(saved);
        activeFirefighter += 1;
        if (activeFirefighter >= Util.numFirefighters){
            activeFirefighter = 0;
        }
        return getActiveFf().getActionsSaved();
    }
}
