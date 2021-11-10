package com.example.project6_fx;

import java.lang.reflect.Array;

public class SimpleSquare {
    private int fire = 0;
    private int POI = 0;
    private int outside = 0;
    public boolean firefighter = false;
    final private int[] coordinate;

    SimpleSquare(int fire, int POI, int outside, boolean firefighter, int[] coordinate){
        this.fire =fire;
        this.POI = POI;
        this.outside = outside;
        this.firefighter = firefighter;
        this.coordinate = coordinate;
    }

    public void setFire(int fire) {
        this.fire = fire;
    }

    public void setPOI(int POI) {
        this.POI = POI;
    }

    public void setOutside(int outside) {
        this.outside = outside;
    }

    public Boolean isFire(){
        if(this.fire == 2){
            return true;
        }
        return false;
    }

    public Boolean isSmoke(){
        if(this.fire == 1){
            return true;
        }
        return false;
    }

    public Boolean POI(){
        if(this.POI == 1){
            return true;
        }
        return false;
    }

    public Boolean isOutside(){
        if(this.outside == 1){
            return true;
        }
        return false;
    }

    public int[] getCoordinate() {
        return coordinate;
    }
}
