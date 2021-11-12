package com.example.project6_fx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class SimpleGame {
    private SimpleMap generateMap = new SimpleMap();
    ArrayList<SimpleSquare> map = generateMap.createMap();

    public void event(){
        ViewManager manager = new ViewManager();
        SimpleSquare sq = map.get(13);
        //manager.updateSquare(sq,"FireFighter");
    }

}
