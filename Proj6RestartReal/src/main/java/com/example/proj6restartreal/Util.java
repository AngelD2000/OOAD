package com.example.proj6restartreal;

import java.io.File;

public class Util {
    //Size of square
    public static final int length = 60;
    //SIze of map
    public static final int columns = 8;
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

    /**
     * Firefighter paths
     * */

    private static String ffBlackPath = new File("/src/main/graphics/black_ff.png").toURI().toString();
    private static String ffBluePath = new File("/src/main/graphics/blue_ff.png").toURI().toString();
    private static String ffGreenPath = new File("/src/main/graphics/green_ff.png").toURI().toString();
    private static String ffRedPath = new File("/src/main/graphics/red_ff.png").toURI().toString();

    public static final String[] firefighterImages = new String[]{ffBluePath,ffBlackPath,ffGreenPath,ffRedPath};
    public static final int[][] firefighterLocations = new int[][]{{1, 1}, {2, 2}, {3, 3}, {4, 4}};
    //Firefighter actions
    public static final int move = 0;
    public static final int drag = 1;
    public static final int hose = 2;
    public static final int chop = 3;

    //Map constants
    public static final int mapWidth = 10;
    public static final int mapHeight = 8;
    public static final int notAdjacent = 0;
    public static final int adjacent = 1;
    public static final int wallBetween = 2;


    //Square directions
    public static final int north = 0;
    public static final int south = 1;
    public static final int east = 2;
    public static final int west = 3;

    //Square Update
    public static final int addFire = 0;
    public static final int removeFire = 1;
    public static final int addPoi = 2;
    public static final int removePoi = 3;
    public static final int addVict = 4;
    public static final int removeVict = 5;
    public static final int addOutside = 6;

    public static void print(String content){
        System.out.print(content);
    }

}
