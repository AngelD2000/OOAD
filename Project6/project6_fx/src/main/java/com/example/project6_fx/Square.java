package com.example.project6_fx;

public class Square {
    //TODO: Whole class
    /**
     * Need a Rectangle object associated with each square for Javafx
     * */

    Square base;
    private Firefighter FF;
    Square(){

    }
    @Override
    public boolean equals(Object otherObject) {
        return false;
    }
    //Turns fire into smoke, or removes the smoke
    public void removeFire(){}
    //Turns smoke into fire or none into smoke
    public void addFire(){}
    public boolean hasPoi(){
        return false;
    }

    //Turns none into poi
    public void addPoi() {};
    //Turns poi into none
    public void removePoi() {};
    public boolean hasVictim(){
        return false;
    }
    public void addVictim() {};
    public void removeVictim() {};

    public boolean hasFire(){
        return false;
    }

    public boolean hasSmoke(){
        return false;
    }

    public boolean isOutside(){
        return false;
    }

    public boolean hasFF() {
        return false;
    }

    public Firefighter getFF() {
        return FF;
    }

    public void setFF(Firefighter FF) {
        this.FF = FF;
    }
}

class FireSquare extends Square {
    @Override
    public boolean hasFire(){
        return true;
    }

    //turn fire into smoke
    public void removeFire(){
        this.base = new SmokeSquare();
    }

    public void addFire(){} // Do nothing
}

class SmokeSquare extends Square {
    @Override
    public boolean hasSmoke(){
        return true;
    }

    //turn smoke into fire
    public void addFire(){
        this.base = new FireSquare();
    }

    //turn smoke into base
    public void removeFire(){
        this.base = new Square();
    }
}

class OutsideSquare extends Square {
    @Override
    public boolean isOutside(){
        return true;
    }

    public void addFire(){} // Do nothing

}

class POISquare extends Square {
    @Override
    public boolean hasPoi(){
        return true;
    }

    public void addPoi() {} // Do nothing

    public void removePoi() {
        this.base = new Square();
    }
}
