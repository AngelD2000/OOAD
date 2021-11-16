package com.example.project6_fx;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Util {
    public static final int length = 60;
    public static final int columns = 6;
    public static final int rows = 10;
    public static final int setDisplayX = 700;
    //Feel free to change the screen size
    public static final int HEIGHT = 500;
    public static final int WIDTH = 900;
    //Firefighter creation constants
    public static final int green = 0;
    public static final int black = 1;
    public static final int red = 2;
    public static final int blue = 3;
    public static final int numFirefighters = 4;
    public static final String[] firefighterImages = new String[]{"/graphics/green_ff.png",
            "/graphics/black_ff.png", "/graphics/red_ff.png", "/graphics/blue_ff.png"};
    public static final Integer[][] firefighterLocations = new Integer[][]{{1, 1}, {2, 2}, {3, 3}, {4, 4}};
    //Firefighter actions
    public static final int move = 0;
    public static final int drag = 1;
    public static final int hose = 2;
    public static final int chop = 3;

    //Map constants
    public static final int notAdjacent = 0;
    public static final int wallBetween = 0;
    public static final int adjacent = 0;

    //Square directions
    public static final int north = 0;
    public static final int south = 1;
    public static final int east = 2;
    public static final int west = 3;

    public static void print(String content){
        System.out.println(content);
    }

}
