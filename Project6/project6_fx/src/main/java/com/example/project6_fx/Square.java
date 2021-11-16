package com.example.project6_fx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends Rectangle {
    //TODO: Whole class
    /**
     * Need a Rectangle object associated with each square for Javafx
     * */

    Square base;
    //Edges stored in order: North, South, East, West (same as Util Square directions)
    private Edge[] edges = {null, null, null, null};
    private Firefighter FF;
    private Rectangle rectangle;
    Square(){
       this.rectangle = new Rectangle();
       this.rectangle.setStroke(Color.LIGHTGREY);

    }
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

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Edge[] getEdges() {
        return edges;
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
