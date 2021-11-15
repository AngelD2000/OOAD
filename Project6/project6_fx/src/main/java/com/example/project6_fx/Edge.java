package com.example.project6_fx;

public class Edge {
    /**
     * Each edge object needs a line objects to display
     * //lineAdd.setStroke(Color.BLACK);  - set the color of the wall to black
     *
     *
     * Line object should be with each edge - argument is (start X position, start Y position, end X position, end Y position)
     *
     * Vertical right edge - the right wall
     * = new Line(square.rect.getWidth() + square.rect.getX(), square.rect.getY(), square.rect.getWidth() + square.rect.getX(), square.rect.getHeight());
     *
     *  Vertical left edge - the left wall
     *  = new Line(square.rect.getX(), square.rect.getY(), square.rect.getX(), square.rect.getHeight());
     *
     *  Horizontal top edge - the top wall
     *  = new Line(square.rect.getX(), square.rect.getY(), square.rect.getWidth() + square.rect.getX(), square.rect.getY());
     *
     *  Horizontal bottom edge - the bottom wall
     *  = new Line(square.rect.getX(), square.rect.getY() + square.rect.getHeight(), square.rect.getWidth() + square.rect.getX(), square.rect.getY() + square.rect.getHeight());
     *
     * */
    public Edge(){

    }

    /**
     * Does 1 damage to the wall
     */
    public void doDamage(){

    }
}
