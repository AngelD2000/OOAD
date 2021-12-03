package com.example.proj6restartreal;

/**
 * Abstract Factory pattern to create a static Map and set up all the squares and Edges within it
 * Also a Singleton as there is no need for more than 1 factory object
 */
public class MapFactory {
    private static final MapFactory instance = new MapFactory();
    private MapFactory() {}
    public static MapFactory getInstance() {
        return instance;
    }

    /**
     * Create the game map
     * @return Created Map
     */
    public Map makeMap(){
        Map map = buildStaticMap();
        map.mapBasicPrint();

        return map;
    }

    /**
     * Handles initial fire and poi placement onto the map
     * @param map default empty map
     * @param fireLogic instance of logic defining fire behavior
     * @param building instance of logic defining the building behavior
     */
    public void setup(Map map, FireLogic fireLogic, Building building){
        for(int i = 0; i < 4; i++){
            Square square = map.getRandomSquare();
            square.addFire();
            fireLogic.explosion(square);
        }
        building.placePoi();
    }

    /**
     * Builds the static map for the game initializing all squares and edges in the exact configuration
     * @return Map created
     */
    private Map buildStaticMap() {
        Map map = new Map();
        //Add 32 Outside squares
        int[] edgesi = new int[] {0, Util.mapHeight-1};
        int[] edgesj = new int[] {0, Util.mapWidth-1};
        for(int i : edgesi) {
            for(int j=0; j < Util.mapWidth; j++) {
                map.updateSquare(map.getLoc(new int[] {i, j}), Util.addOutside);
            }
        }
        for(int j : edgesj) {
            for(int i=0; i < Util.mapHeight; i++) {
                map.updateSquare(map.getLoc(new int[] {i, j}), Util.addOutside);
            }
        }

        //All the edges between squares
        //i=0
        Edge edge01_11 = new Edge();
        Edge edge02_12 = new Edge();
        Edge edge03_13 = new Edge();
        Edge edge04_14 = new Edge();
        Edge edge05_15 = new Edge();
        Edge edge07_17 = new Edge();
        Edge edge08_18 = new Edge();

        //i=1
        Edge edge10_11 = new Edge();
        Edge edge15_16 = new Edge();
        Edge edge18_19 = new Edge();

        //i=2
        Edge edge20_21 = new Edge();
        Edge edge23_24 = new Edge();
        Edge edge28_29 = new Edge();
        Edge edge23_33 = new Edge();
        Edge edge24_34 = new Edge();
        Edge edge25_35 = new Edge();
        Edge edge27_37 = new Edge();

        //i=3
        Edge edge36_37 = new Edge();
        Edge edge38_39 = new Edge();

        //i=4
        Edge edge40_41 = new Edge();
        Edge edge41_51 = new Edge();
        Edge edge42_43 = new Edge();
        Edge edge42_52 = new Edge();
        Edge edge43_53 = new Edge();
        Edge edge45_55 = new Edge();
        Edge edge46_56 = new Edge();
        Edge edge47_57 = new Edge();
        Edge edge48_58 = new Edge();

        //i=5
        Edge edge50_51 = new Edge();
        Edge edge55_56 = new Edge();
        Edge edge57_58 = new Edge();
        Edge edge58_59 = new Edge();

        //i=6
        Edge edge60_61 = new Edge();
        Edge edge61_71 = new Edge();
        Edge edge62_72 = new Edge();
        Edge edge64_74 = new Edge();
        Edge edge65_75 = new Edge();
        Edge edge66_76 = new Edge();
        Edge edge67_77 = new Edge();
        Edge edge68_78 = new Edge();
        Edge edge68_69 = new Edge();


        //Add edges to squares
        //Row i=0
        //0,0
        map.getLoc(new int[] {0, 1}).setEdge(Util.south, edge01_11);
        map.getLoc(new int[] {0, 2}).setEdge(Util.south, edge02_12);
        map.getLoc(new int[] {0, 3}).setEdge(Util.south, edge03_13);
        map.getLoc(new int[] {0, 4}).setEdge(Util.south, edge04_14);
        map.getLoc(new int[] {0, 5}).setEdge(Util.south, edge05_15);
        //0,6
        map.getLoc(new int[] {0, 7}).setEdge(Util.south, edge07_17);
        map.getLoc(new int[] {0, 8}).setEdge(Util.south, edge08_18);
        //0,9

        // i=1
        map.getLoc(new int[] {1, 0}).setEdge(Util.east, edge10_11);
        map.getLoc(new int[] {1, 1}).setEdge(Util.north, edge01_11); map.getLoc(new int[] {1, 1}).setEdge(Util.west, edge10_11);
        map.getLoc(new int[] {1, 2}).setEdge(Util.north, edge02_12);
        map.getLoc(new int[] {1, 3}).setEdge(Util.north, edge03_13);
        map.getLoc(new int[] {1, 4}).setEdge(Util.north, edge04_14);
        map.getLoc(new int[] {1, 5}).setEdge(Util.north, edge05_15); map.getLoc(new int[] {1, 5}).setEdge(Util.east, edge15_16);
        map.getLoc(new int[] {1, 6}).setEdge(Util.west, edge15_16);
        map.getLoc(new int[] {1, 7}).setEdge(Util.north, edge07_17);
        map.getLoc(new int[] {1, 8}).setEdge(Util.north, edge08_18); map.getLoc(new int[] {1, 8}).setEdge(Util.east, edge18_19);
        map.getLoc(new int[] {1, 9}).setEdge(Util.west, edge18_19);

        // i=2
        map.getLoc(new int[] {2, 0}).setEdge(Util.east, edge20_21);
        map.getLoc(new int[] {2, 1}).setEdge(Util.west, edge20_21);
        //2,2
        map.getLoc(new int[] {2, 3}).setEdge(Util.south, edge23_33); map.getLoc(new int[] {2, 3}).setEdge(Util.east, edge23_24);
        map.getLoc(new int[] {2, 4}).setEdge(Util.south, edge24_34); map.getLoc(new int[] {2, 4}).setEdge(Util.west, edge23_24);
        map.getLoc(new int[] {2, 5}).setEdge(Util.south, edge25_35);
        //2,6
        map.getLoc(new int[] {2, 7}).setEdge(Util.south, edge27_37);
        map.getLoc(new int[] {2, 8}).setEdge(Util.east, edge28_29);
        map.getLoc(new int[] {2, 9}).setEdge(Util.west, edge28_29);

        // i=3
        //3,0
        //3,1
        //3,2
        map.getLoc(new int[] {3, 3}).setEdge(Util.north, edge23_33);
        map.getLoc(new int[] {3, 4}).setEdge(Util.north, edge24_34);
        map.getLoc(new int[] {3, 5}).setEdge(Util.north, edge25_35);
        map.getLoc(new int[] {3, 6}).setEdge(Util.east, edge36_37);
        map.getLoc(new int[] {3, 7}).setEdge(Util.north, edge27_37); map.getLoc(new int[] {3, 7}).setEdge(Util.west, edge36_37);
        map.getLoc(new int[] {3, 8}).setEdge(Util.east, edge38_39);
        map.getLoc(new int[] {3, 9}).setEdge(Util.west, edge38_39);

        // i=4
        map.getLoc(new int[] {4, 0}).setEdge(Util.east, edge40_41);
        map.getLoc(new int[] {4, 1}).setEdge(Util.south, edge41_51); map.getLoc(new int[] {4, 1}).setEdge(Util.west, edge40_41);
        map.getLoc(new int[] {4, 2}).setEdge(Util.south, edge42_52); map.getLoc(new int[] {4, 2}).setEdge(Util.east, edge42_43);
        map.getLoc(new int[] {4, 3}).setEdge(Util.south, edge43_53); map.getLoc(new int[] {4, 3}).setEdge(Util.west, edge42_43);
        //4,4
        map.getLoc(new int[] {4, 5}).setEdge(Util.south, edge45_55);
        map.getLoc(new int[] {4, 6}).setEdge(Util.south, edge46_56);
        map.getLoc(new int[] {4, 7}).setEdge(Util.south, edge47_57);
        map.getLoc(new int[] {4, 8}).setEdge(Util.south, edge48_58);
        //4,9

        // i=5
        map.getLoc(new int[] {5, 0}).setEdge(Util.east, edge50_51);
        map.getLoc(new int[] {5, 1}).setEdge(Util.west, edge50_51); map.getLoc(new int[] {5, 1}).setEdge(Util.north, edge41_51);
        map.getLoc(new int[] {5, 2}).setEdge(Util.north, edge42_52);
        map.getLoc(new int[] {5, 3}).setEdge(Util.north, edge43_53);
        //5,4
        map.getLoc(new int[] {5, 5}).setEdge(Util.north, edge45_55); map.getLoc(new int[] {5, 5}).setEdge(Util.east, edge55_56);
        map.getLoc(new int[] {5, 6}).setEdge(Util.north, edge46_56); map.getLoc(new int[] {5, 6}).setEdge(Util.west, edge55_56);
        map.getLoc(new int[] {5, 7}).setEdge(Util.north, edge47_57); map.getLoc(new int[] {5, 7}).setEdge(Util.east, edge57_58);
        map.getLoc(new int[] {5, 8}).setEdge(Util.north, edge48_58); map.getLoc(new int[] {5, 8}).setEdge(Util.west, edge57_58); map.getLoc(new int[] {5, 8}).setEdge(Util.east, edge58_59);
        map.getLoc(new int[] {5, 9}).setEdge(Util.west, edge58_59);

        // i=6
        map.getLoc(new int[] {6, 0}).setEdge(Util.east, edge60_61);
        map.getLoc(new int[] {6, 1}).setEdge(Util.west, edge60_61); map.getLoc(new int[] {6, 1}).setEdge(Util.south, edge61_71);
        map.getLoc(new int[] {6, 2}).setEdge(Util.south, edge62_72);
        //6,3
        map.getLoc(new int[] {6, 4}).setEdge(Util.south, edge64_74);
        map.getLoc(new int[] {6, 5}).setEdge(Util.south, edge65_75);
        map.getLoc(new int[] {6, 6}).setEdge(Util.south, edge66_76);
        map.getLoc(new int[] {6, 7}).setEdge(Util.south, edge67_77);
        map.getLoc(new int[] {6, 8}).setEdge(Util.south, edge68_78); map.getLoc(new int[] {6, 8}).setEdge(Util.east, edge68_69);
        map.getLoc(new int[] {6, 9}).setEdge(Util.west, edge68_69);

        // i=7
        //7,0
        map.getLoc(new int[] {7, 1}).setEdge(Util.north, edge61_71);
        map.getLoc(new int[] {7, 2}).setEdge(Util.north, edge62_72);
        //7,3
        map.getLoc(new int[] {7, 4}).setEdge(Util.north, edge64_74);
        map.getLoc(new int[] {7, 5}).setEdge(Util.north, edge65_75);
        map.getLoc(new int[] {7, 6}).setEdge(Util.north, edge66_76);
        map.getLoc(new int[] {7, 7}).setEdge(Util.north, edge67_77);
        map.getLoc(new int[] {7, 8}).setEdge(Util.north, edge68_78);
        //7,9

        return map;
    }
}