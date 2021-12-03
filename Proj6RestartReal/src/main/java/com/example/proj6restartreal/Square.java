package com.example.proj6restartreal;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Interface defining Square behaviors
 */
public interface Square {
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);

    Rectangle getRectangle();

    Edge getEdge(int direction);
    void setEdge(int direction, Edge edge);
    Edge[] getEdges();

    /**
     * Checks if a square has fire on it
     * @return boolean
     */
    boolean hasFire();

    /**
     * Checks if a square has smoke on it
     * @return boolean
     */
    boolean hasSmoke();

    /**
     * Turn fire into smoke or remove smoke
     * @return new Square
     */
    Square removeFire();

    /**
     * Turns smoke into fire or none into smoke
     * @return new Square
     */
    Square addFire();

    /**
     * Checks if a square has a POI on it
     * @return boolean
     */
    boolean hasPoi();

    /**
     * Turns none into poi
     * @return new Square
     */
    Square addPoi();

    /**
     * Turns poi into none
     * @return new Square
     */
    Square removePoi();

    /**
     * Checks if a square has a victim
     * @return boolean
     */
    boolean hasVictim();

    /**
     * Turns POI/None into a Victim
     * @return new Square
     */
    Square addVictim();

    /**
     * Turns Victim into none
     * @return new Square
     */
    Square removeVictim();

    /**
     * Checks if a square is an outside square
     * @return boolean
     */
    boolean isOutside();

    /**
     * Turns none into an outside square
     * @return new Square
     */
    Square addOutside();

    /**
     * Turns an outside square into none
     * @return new Square
     */
    Square removeOutside();

    /**
     * Checks if a square has a FireFighter on it
     * @return boolean
     */
    boolean hasFF();

    /**
     * Removes a FireFighter from a square
     */
    void removeFF();

    Firefighter getFF();
    void setFF(Firefighter FF);
}

/**
 * Decorator for Squares to inherit from. Acts as a passthrough to the base square's implementation of functions unless
 */
class SquareDecorator implements Square {
    Square base;

    SquareDecorator(Square square) {
        base = square;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals(Object obj) {
        return base.equals(obj);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getX() {
        return base.getX();
    }
    /**
     * @inheritDoc
     */
    @Override
    public int getY() {
        return base.getY();
    }
    /**
     * @inheritDoc
     */
    @Override
    public void setX(int x) {
        base.setX(x);
    }
    /**
     * @inheritDoc
     */
    @Override
    public void setY(int y) {
        base.setY(y);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Rectangle getRectangle() {
        return base.getRectangle();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setEdge(int direction, Edge edge) {
        base.setEdge(direction, edge);
    }
    /**
     * @inheritDoc
     */
    @Override
    public Edge getEdge(int direction) {
        return base.getEdge(direction);
    }
    /**
     * @inheritDoc
     */
    @Override
    public Edge[] getEdges() {
        return base.getEdges();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasFire(){
        return base.hasFire();
    }
    /**
     * @inheritDoc
     */
    @Override
    public boolean hasSmoke(){
        return base.hasSmoke();
    }
    /**
     * @inheritDoc
     */
    @Override
    public Square removeFire() {
        return base.removeFire();

    }
    /**
     * @inheritDoc
     */
    @Override
    public Square addFire()  {
        return base.addFire();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPoi(){
        return base.hasPoi();
    }
    /**
     * @inheritDoc
     */
    @Override
    public Square addPoi() {
        return base.addPoi();
    }
    /**
     * @inheritDoc
     */
    @Override
    public Square removePoi() {
        return base.removePoi();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasVictim(){
        return base.hasVictim();
    }
    /**
     * @inheritDoc
     */
    @Override
    public Square addVictim() {
        return base.addVictim();
    }
    /**
     * @inheritDoc
     */
    @Override
    public Square removeVictim() {
        return base.removeVictim();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isOutside(){
        return base.isOutside();
    }
    /**
     * @inheritDoc
     */
    @Override
    public Square addOutside() {
        return base.addOutside();
    }
    /**
     * @inheritDoc
     */
    @Override
    public Square removeOutside() { return base.removeOutside(); }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasFF() {
        return base.hasFF();
    }
    /**
     * @inheritDoc
     */
    @Override
    public Firefighter getFF() {
        return base.getFF();
    }
    /**
     * @inheritDoc
     */
    @Override
    public void setFF(Firefighter FF) {
        base.setFF(FF);
    }
    /**
     * @inheritDoc
     */
    @Override
    public void removeFF() {
        base.removeFF();
    }
}

/**
 * Base Square that will represent empty tiles
 */
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

    /**
     * @inheritDoc
     */
    @Override
    public int getX() {
        return x;
    }
    /**
     * @inheritDoc
     */
    @Override
    public int getY(){
        return y;
    }
    /**
     * @inheritDoc
     */
    @Override
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setEdge(int direction, Edge edge) {
        edges[direction] = edge;
//        if(setNull) {
//            edges[direction] = null;
//        }
//        else {
//            edges[direction] = edge;
//        }

    }

    /**
     * @inheritDoc
     */
    @Override
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

    /**
     * @inheritDoc
     */
    @Override
    public Square removeFire() {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public final boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        if(!(obj instanceof Square)) {
            return false;
        }
        final Square other = (Square) obj;
        return getX() == other.getX() && getY() == other.getY();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Firefighter getFF() {
        return FF;
    }
    /**
     * @inheritDoc
     */
    @Override
    public void setFF(Firefighter FF) {
        this.FF = FF;
    }
    /**
     * @inheritDoc
     */
    @Override
    public void removeFF() {
        this.FF = null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Edge[] getEdges() {
        return edges;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Square addFire() {
        return new SmokeSquare(this);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPoi() {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Square addPoi() {
        return new PoiSquare(this);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Square removePoi() {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasVictim() {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Square addVictim() {
        return new VictSquare(this);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Square removeVictim() {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasFire() {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasSmoke() {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isOutside() {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Square addOutside() {
        return new OutsideSquare(this);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Square removeOutside() {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasFF() {
        if(getFF()==null){
            return false;
        }
        return true;
    }
}

/**
 * Square decorator with Fire on it
 */
class FireSquare extends SquareDecorator {
    FireSquare(Square base){
        super(base);
    }

    /**
     * @inheritDoc
     * @return True
     */
    @Override
    public boolean hasFire(){
        return true;
    }

    /**
     * @inheritDoc
     * @Return new SmokeSquare
     */
    @Override
    public SmokeSquare removeFire(){
        return new SmokeSquare(this.base);
    }

}

/**
 * Square decorator with smoke on it
 */
class SmokeSquare extends SquareDecorator {
    SmokeSquare(Square base){
        super(base);
    }

    /**
     * @inheritDoc
     * @return True
     */
    @Override
    public boolean hasSmoke(){
        return true;
    }

    /**
     * @inheritDoc
     * @return new FireSquare
     */
    @Override
    public FireSquare addFire(){
        return new FireSquare(this.base);
    }

    /**
     * @inheritDoc
     * @return base square
     */
    @Override
    public Square removeFire(){
        return this.base;
    }
}

/**
 * Square decorator that is outside the building
 */
class OutsideSquare extends SquareDecorator {
    OutsideSquare(Square base){
        super(base);
    }

    /**
     * @inheritDoc
     * @return True
     */
    @Override
    public boolean isOutside(){
        return true;
    }


    /**
     * Can't be used on outside squares so returns the current object
     * @return this object
     */
    @Override
    public Square addFire() {
        return this;
    }

    /**
     * Can't be used on outside squares so returns the current object
     * @return this object
     */
    @Override
    public Square addPoi() {
        return this;
    }

    /**
     * Can't be used on outside squares so returns the current object
     * @return this object
     */
    @Override
    public Square addVictim() {
        return this;
    }

    /**
     * Can't be used on outside squares so returns the current object
     * @return this object
     */
    @Override
    public Square removeVictim() {
        return this;
    }
}

/**
 * Square decorator that has a POI on it
 */
class PoiSquare extends SquareDecorator {
    PoiSquare(Square base){
        super(base);
    }
    /**
     * @inheritDoc
     * @return True
     */
    @Override
    public boolean hasPoi(){
        return true;
    }

    /**
     * @inheritDoc
     * @return base square
     */
    @Override
    public Square removePoi() {
        return this.base;
    }
}

/**
 * Square decorator that has a Victim on it
 */
class VictSquare extends SquareDecorator {
    VictSquare(Square base){
        super(base);
    }

    /**
     * @inheritDoc
     * @return True
     */
    @Override
    public boolean hasVictim(){
        return true;
    }

    /**
     * @inheritDoc
     * @return base square
     */
    @Override
    public Square removeVictim() {
        return this.base;
    }
}
