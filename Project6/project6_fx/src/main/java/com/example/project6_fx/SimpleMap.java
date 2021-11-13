package com.example.project6_fx;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class SimpleMap {
    public ArrayList<SimpleSquare> createMap() {
        ArrayList<SimpleSquare> map = new ArrayList<>();

        for (int i = 0; i < Util.columns; ++i) {
            for (int j = 0; j < Util.rows; ++j) {
                int[] coord = {Util.length * j, Util.length * i + 2};
                Rectangle rect = new Rectangle(coord[0],coord[1], Util.length, Util.length);
                SimpleSquare square;
                Random rn = new Random();
                int fireChance = rn.nextInt(10) + 1;
                int ffChance = rn.nextInt(27) + 1;
                int fire = 0;
                boolean ff = false;
                if(fireChance < 3){
                    fire = 2;
                }
                if(ffChance < 2){
                    ff = true;
                }
                if (i == 0 || j == 0 || i == Util.columns - 1 || j == Util.rows - 1) {
                    square = new SimpleSquare(fire, true, ff,coord,"GREEN",rect);

                } else {
                    square = new SimpleSquare(fire, false,false, coord,"WHITE",rect);
                }
                map.add(square);
            }
        }
        return map;
    }
}
