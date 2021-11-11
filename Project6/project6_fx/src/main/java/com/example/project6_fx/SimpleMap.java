package com.example.project6_fx;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class SimpleMap {
    public ArrayList<SimpleSquare> createMap() {
        ArrayList<SimpleSquare> map = new ArrayList<>();

        for (int i = 0; i < Util.columns; ++i) {
            for (int j = 0; j < Util.rows; ++j) {
                int[] coord = {Util.length * j, Util.length * i + 2};
                Rectangle rect = new Rectangle(coord[0],coord[1], Util.length, Util.length);
                SimpleSquare square;
                if (i == 0 || j == 0 || i == Util.columns - 1 || j == Util.rows - 1) {
                    square = new SimpleSquare(coord,"GREEN",rect);
                } else {
                    square = new SimpleSquare(coord,"WHITE",rect);
                }
                map.add(square);
            }
        }
        return map;
    }
}
