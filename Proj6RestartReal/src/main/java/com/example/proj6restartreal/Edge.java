package com.example.proj6restartreal;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Edge{
    private Line line;

    public Edge(){
        //Create a line
        this.line = new Line();
        this.line.setFill(Color.BLACK);
        this.line.setStrokeWidth(4);
    }

    /**
     * Does 1 damage to the wall
     */
    public void doDamage(Game game){
        Util.print("Edge Damage\n");
        game.incrementDamage();
    }

    public void getDamage(){

    }


    public Line getLine() {
        return line;
    }
}
