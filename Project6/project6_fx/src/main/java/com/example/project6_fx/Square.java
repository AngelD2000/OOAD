package com.example.project6_fx;

public abstract class Square {
    //TODO: Whole class
    /**
     * Need a Rectangle object associated with each square for Javafx
     * */

    //Edges stored in order: North, South, East, West (same as Util Square directions)
    protected Edge[] edges = {null, null, null, null};
    private Firefighter FF;
    Square(){}

    public void setEdge(int direction) {
        edges[direction] = new Edge();
    }

    public Edge getEdge(int direction) {
        switch(direction) {
            case Util.north:
                return edges[0];
            case Util.south:
                return edges[1];
            case Util.east:
                return edges[2];
            case Util.west:
                return edges[3];
        }
        return null;
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

class BaseSquare extends Square {
    Square base;

    BaseSquare() {
        super();
    }

    BaseSquare(Square square) {
        this.base = square;
        this.edges = square.edges;
        this.setFF(square.getFF());
    }

}

class FireSquare extends BaseSquare {
    FireSquare(Square base){
        super(base);
    }
    @Override
    public boolean hasFire(){
        return true;
    }

    //turn fire into smoke
    @Override
    public void removeFire(){
        this.base = new SmokeSquare(this);
    }

    @Override
    public void addFire(){} // Do nothing
}

class SmokeSquare extends BaseSquare {
    SmokeSquare(Square base){
        super(base);
    }

    @Override
    public boolean hasSmoke(){
        return true;
    }

    //turn smoke into fire
    @Override
    public void addFire(){
        this.base = new FireSquare(this);
    }

    //turn smoke into base
    @Override
    public void removeFire(){
        this.base = new BaseSquare(this);
    }
}

class OutsideSquare extends BaseSquare {
    OutsideSquare(Square base){
        super(base);
    }
    @Override
    public boolean isOutside(){
        return true;
    }

    @Override
    public void addFire(){} // Do nothing

}

class POISquare extends BaseSquare {
    POISquare(Square base){
        super(base);
    }
    @Override
    public boolean hasPoi(){
        return true;
    }

    @Override
    public void addPoi() {} // Do nothing

    @Override
    public void removePoi() {
        this.base = new BaseSquare(this);
    }
}
