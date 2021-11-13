package com.example.project6_fx;

public class Building {
    int saved = 0;
    int perished = 0;
    int victims = 0;
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
            square.removeVictim();
            perished+=1;
            numTokens-=1;
        }
    }
    public void flipPoi(Square square){
        square.removePoi();
        double random_int = Math.random()*(2);
        double odds = (Util.victims - victims) / (Util.victims + Util.blanks - victims - blanks);
        //It's a victim!
        if (random_int >= odds){
            victims += 1;
            square.addVictim();
        }
        else{
            blanks += 1;
            numTokens -= 1;
        }
    }
    public void rescue(Square square){
        square.removeVictim();
        saved += 1;
        numTokens -= 1;
    }

    public void placePoi(){
        while(numTokens <= Util.maxTokens){
            Square loc = map.getRandomSquare();
            while(loc.hasFire() || loc.hasSmoke()){
                loc = map.getRandomSquare();
            }
            loc.addPoi();
            numTokens += 1;
        }
    }
}
