package com.example.proj6restartreal;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

public interface Square {
    public int getX();
    public int getY();
    public void setX(int x);
    public void setY(int y);

    public Rectangle getRectangle();

    public void setEdge(int direction);

    public Edge getEdge(int direction);

    public Edge[] getEdges();

    //Turns fire into smoke, or removes the smoke
    public Square removeFire();
    //Turns smoke into fire or none into smoke
    public Square addFire();
    public boolean hasPoi();

    //Turns none into poi
    public Square addPoi();

    //Turns poi into none
    public Square removePoi();
    public boolean hasVictim();

    public Square addVictim();
    public Square removeVictim();

    public boolean hasFire();

    public boolean hasSmoke();

    public boolean isOutside();

    public Square addOutside();

    public Square removeOutside();

    public boolean hasFF();
    public Firefighter getFF();
    public void setFF(Firefighter FF);
    public void removeFF();
}

class SquareDecorator implements Square {
    Square base;

    SquareDecorator(Square square) {
        base = square;
    }

    @Override
    public boolean equals(Object obj) {
        return base.equals(obj);
    }

    @Override
    public int getX() {
        return base.getX();
    }

    @Override
    public int getY() {
        return base.getY();
    }

    @Override
    public void setX(int x) {
        base.setX(x);
    }

    @Override
    public void setY(int y) {
        base.setY(y);
    }

    @Override
    public Rectangle getRectangle() {
        return base.getRectangle();
    }

    @Override
    public void setEdge(int direction) {
        base.setEdge(direction);
    }

    @Override
    public Edge getEdge(int direction) {
        return base.getEdge(direction);
    }

    @Override
    public Edge[] getEdges() {
        return base.getEdges();
    }

    //Turns fire into smoke, or removes the smoke
    public Square removeFire() {
        return base.removeFire();
    }
    //Turns smoke into fire or none into smoke
    public Square addFire()  {
        return base.addFire();
    }
    public boolean hasPoi(){
        return base.hasPoi();
    }

    //Turns none into poi
    public Square addPoi() {
        return base.addPoi();
    }

    //Turns poi into none
    public Square removePoi() {
        return base.removePoi();
    }
    public boolean hasVictim(){
        return base.hasVictim();
    }

    public Square addVictim() {
        return base.addVictim();
    }
    public Square removeVictim() {
        return base.removeVictim();
    }

    public boolean hasFire(){
        return base.hasFire();
    }

    public boolean hasSmoke(){
        return base.hasSmoke();
    }

    public boolean isOutside(){
        return base.isOutside();
    }
    public Square addOutside() {
        return base.addOutside();
    }
    public Square removeOutside() { return base.removeOutside(); }

    public boolean hasFF() {
        return base.hasFF();
    }

    @Override
    public Firefighter getFF() {
        return base.getFF();
    }

    @Override
    public void setFF(Firefighter FF) {
        base.setFF(FF);
    }
    @Override
    public void removeFF() {
        base.removeFF();
    }
}

class BaseSquare implements Square {
    protected Edge[] edges = {null, null, null, null};
    private Firefighter FF = null;
    private Rectangle rectangle;
    private int x = 0;
    private int y = 0;
    BaseSquare(int x, int y) {
        super();
        this.rectangle = new Rectangle();
        this.rectangle.setStroke(Color.LIGHTGREY);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
    public Square removeFire() {
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
        if(!Arrays.equals(getEdges(), other.getEdges())){
            return false;
        }

        if(FF != null && !FF.equals(other.getFF())) {
            return false;
        }
        return true;
    }

    public Firefighter getFF() {
        return FF;
    }

    public void setFF(Firefighter FF) {
        this.FF = FF;
    }
    public void removeFF() {
        this.FF = null;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Edge[] getEdges() {
        return edges;
    }

    @Override
    public Square addFire() {
        return new FireSquare(this);
    }

    @Override
    public boolean hasPoi() {
        return false;
    }

    @Override
    public Square addPoi() {
        return new PoiSquare(this);
    }

    @Override
    public Square removePoi() {
        return null;
    }

    @Override
    public boolean hasVictim() {
        return false;
    }

    @Override
    public Square addVictim() {
        return new VictSquare(this);
    }

    @Override
    public Square removeVictim() {
        return null;
    }

    @Override
    public boolean hasFire() {
        return false;
    }

    @Override
    public boolean hasSmoke() {
        return false;
    }

    @Override
    public boolean isOutside() {
        return false;
    }

    @Override
    public Square addOutside() {
        return new OutsideSquare(this);
    }

    @Override
    public Square removeOutside() {
        return null;
    }

    @Override
    public boolean hasFF() {
        if(getFF()==null){
            return false;
        }
        return true;
    }
}

class FireSquare extends SquareDecorator {
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

}

class SmokeSquare extends SquareDecorator {
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
    public Square removeFire(){
        return this.base;
    }
}

class OutsideSquare extends SquareDecorator {
    OutsideSquare(Square base){
        super(base);
    }

    @Override
    public boolean isOutside(){
        return true;
    }

    //Can't add fire to outside
    @Override
    public Square addFire() {
        return this;
    }
    //Can't add poi to outside
    @Override
    public Square addPoi() {
        return this;
    }
    //Can't add victim to outside
    @Override
    public Square addVictim() {
        return this;
    }
}

class PoiSquare extends SquareDecorator {
    PoiSquare(Square base){
        super(base);
    }
    @Override
    public boolean hasPoi(){
        return true;
    }

    @Override
    public Square removePoi() {
        return this.base;
    }
}

class VictSquare extends SquareDecorator {
    VictSquare(Square base){
        super(base);
    }

    @Override
    public boolean hasVictim(){
        return true;
    }

    @Override
    public Square removeVictim() {
        return this.base;
    }
}
