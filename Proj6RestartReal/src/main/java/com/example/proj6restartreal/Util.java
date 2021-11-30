package com.example.proj6restartreal;

import java.io.File;

public class Util {
    //Size of square
    public static final int length = 60;
    //SIze of map
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
     * Square type paths
     */
    public static final String firePath = "file:src/main/graphics/fire.png";
    public static final String smokePath = "file:src/main/graphics/smoke.png";
    public static final String poiPath = "file:src/main/graphics/poi.png";
    public static final String personPath = "file:src/main/graphics/person.png";

    /**
     * Firefighter paths
     */
    private static final String ffBlackPath = "file:src/main/graphics/black_ff.png";
    private static final String ffBluePath = "file:src/main/graphics/blue_ff.png";
    private static final String ffGreenPath = "file:src/main/graphics/green_ff.png";
    private static final String ffRedPath = "file:src/main/graphics/red_ff.png";
    public static final String[] firefighterImages = new String[]{ffGreenPath,ffBlackPath,ffRedPath,ffBluePath};
    public static final int[][] firefighterLocations = new int[][]{{3, 0}, {0, 6}, {4, 9}, {7, 3}};

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

    public static int getOppositeDirection(int dir) {
        switch(dir) {
            case Util.north:
                return Util.south;
            case Util.south:
                return Util.north;
            case Util.east:
                return Util.west;
            case Util.west:
                return Util.east;
            default:
                return -1;
        }
    }

}
