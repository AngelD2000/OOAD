package com.example.project6_fx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends Rectangle {
    //TODO: Whole class
    /**
     * Need a Rectangle object associated with each square for Javafx
     * */

    //Edges stored in order: North, South, East, West (same as Util Square directions)
    protected Edge[] edges = {null, null, null, null};
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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Square other = (Square) obj;
        return (Arrays.equals(getEdges(), other.getEdges()) && getFF().equals(other.getFF()));
    }

    //Turns fire into smoke, or removes the smoke
    public Square removeFire() {
        return this;
    };
    //Turns smoke into fire or none into smoke
    public Square addFire()  {
        return this;
    };
    public boolean hasPoi(){
        return false;
    }

    //Turns none into poi
    public Square addPoi() {
        return this;
    };

    //Turns poi into none
    public Square removePoi() {
        return this;
    };
    public boolean hasVictim(){
        return false;
    }

    public Square addVictim() {
        return this;
    };
    public Square removeVictim() {
        return this;
    };

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
    public SmokeSquare removeFire(){
        return new SmokeSquare(this.base);
    }

    @Override
    public Square addFire(){
        return this;
    } // Do nothing
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
    public FireSquare addFire(){
        return new FireSquare(this.base);
    }

    //turn smoke into base
    @Override
    public BaseSquare removeFire(){
        return new BaseSquare(this.base);
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
    public Square addFire(){
        return this;
    } // Do nothing

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
    public Square addPoi() {
        return this;
    } // Do nothing

    @Override
    public BaseSquare removePoi() {
         return new BaseSquare(this.base);
    }
}
