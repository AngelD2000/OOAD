package com.example.proj6restartreal;

public class Building {
    //POI constants
    public static final int maxVictims =12;
    public static final int maxBlanks = 6;
    public static final int maxTokens = 3;

    int saved = 0;
    int perished = 0;
    double victims = 0.0;
    int blanks = 0;
    int numTokens = 0;

    Map map = null;

    Building(Map map){
        this.map = map;
    }

    int getSaved(){
        return saved;
    }
    void incrementSaved(){
        saved+=1;
    }

    int getPerished(){
        return perished;
    }
    void incrementPerished(){
        perished += 1;
    }

    public void killPoi(Square square){
        if(square.hasPoi()){
            flipPoi(square);
        }
        if(square.hasVictim()){
            map.updateSquare(square, Util.removeVict);
            perished+=1;
            numTokens-=1;
        }
    }
    public void flipPoi(Square square){
        map.updateSquare(square, Util.removePoi);
        double random_int = Math.random();
        double odds = (maxVictims - victims) / (maxVictims + maxBlanks - victims - blanks);
        Util.print( "" + random_int + " and the odds were" + odds);
        Util.print( "Max victims" + maxVictims + " victims" + victims);
        //It's a victim!
        if (random_int <= odds){
            victims += 1;
            map.updateSquare(square, Util.addVict);
        }
        else{
            blanks += 1;
            numTokens -= 1;
        }
    }
    public void rescue(Square square){
        map.updateSquare(square, Util.removeVict);
        saved += 1;
        numTokens -= 1;
    }

    public void placePoi(){
        while(numTokens < maxTokens){
            Square loc = map.getRandomSquare();
            while(loc.hasFire() || loc.hasSmoke() || loc.hasPoi() || loc.hasFF() || loc.hasVictim()){
                loc = map.getRandomSquare();
            }
            map.updateSquare(loc, Util.addPoi);
            Util.print("POI: " + loc.getX() + ", " + loc.getY() + "\n");
            numTokens += 1;
        }
    }
}
