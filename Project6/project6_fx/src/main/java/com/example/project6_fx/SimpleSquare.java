package com.example.project6_fx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.lang.reflect.Array;

public class SimpleSquare {
    private int fire = 0;
    private int damage = 0;
    private int[] edges;
//    private int POI = 0;
    private boolean outside = true;
    private boolean firefighter = false;
    final private int[] coordinate;
    private String color;
    public Rectangle rect;

    SimpleSquare(int fire,  boolean outside, boolean firefighter, int[] coordinate,String color, Rectangle rect){
        this.fire =fire;
//        this.POI = POI;
        this.outside = outside;
        this.firefighter = firefighter;
        this.coordinate = coordinate;
        this.color = color;
        this.rect = rect;
        if(this.color == "WHITE"){
            this.rect.setFill(Color.WHITE);
        }
        else{
            this.rect.setFill(Color.GREEN);
        }
        this.rect.setStroke(Color.LIGHTGREY);
    }

    public int getFire() {
        return fire;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public int[] getEdges() {
        return edges;
    }

    public void setEdges(int[] edges) {
        this.edges = edges;
    }
}
