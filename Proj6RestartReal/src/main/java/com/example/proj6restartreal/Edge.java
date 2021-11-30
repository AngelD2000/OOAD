package com.example.proj6restartreal;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Edge{
    private Line line;
    private int damage;

    public Edge(){
        this.line = new Line();
        damage = 2;
    }

    /**
     * Does 1 damage to the wall
     */
    public void doDamage(Game game){
        Util.print("Edge Damage\n");
        game.incrementDamage();
        damage -= 1;
    }

    public int getDamage(){
        return damage;
    }


    public Line getLine() {
        return line;
    }
}
