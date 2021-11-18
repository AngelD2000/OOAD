package com.example.proj6restartreal;

public class MapFactory {
    private static final MapFactory instance = new MapFactory();
    private MapFactory() {}
    public static MapFactory getInstance() {
        return instance;
    }

    //Just guessing parameters this will need
    public Map makeMap(Game game){
        //Make the map
        Map map = new Map();
        buildStaticMap(map);
        map.mapBasicPrint();

        return map;
    }

    public void setup(Map map, FireLogic fireLogic, Building building){
        for(int i = 0; i < 4; i++){
            Square square = map.getRandomSquare();
            Util.print("Explosion: " + square.getX() + square.getY() + "\n");
            fireLogic.explosion(square);
        }
        building.placePoi();
    }

    private void buildStaticMap(Map map) {
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

        //Add edges to squares
        //Row i=0
        //0,0
        map.getLoc(new int[] {0, 1}).setEdge(Util.south);
        map.getLoc(new int[] {0, 2}).setEdge(Util.south);
        map.getLoc(new int[] {0, 3}).setEdge(Util.south);
        map.getLoc(new int[] {0, 4}).setEdge(Util.south);
        map.getLoc(new int[] {0, 5}).setEdge(Util.south);
        //0,6
        map.getLoc(new int[] {0, 7}).setEdge(Util.south);
        map.getLoc(new int[] {0, 8}).setEdge(Util.south);
        //0,9

        // i=1
        map.getLoc(new int[] {1, 0}).setEdge(Util.east);
        map.getLoc(new int[] {1, 1}).setEdge(Util.north); map.getLoc(new int[] {1, 2}).setEdge(Util.west);
        map.getLoc(new int[] {1, 2}).setEdge(Util.north);
        map.getLoc(new int[] {1, 3}).setEdge(Util.north);
        map.getLoc(new int[] {1, 4}).setEdge(Util.north);
        map.getLoc(new int[] {1, 5}).setEdge(Util.north); map.getLoc(new int[] {1, 5}).setEdge(Util.east);
        map.getLoc(new int[] {1, 6}).setEdge(Util.west);
        map.getLoc(new int[] {1, 7}).setEdge(Util.north);
        map.getLoc(new int[] {1, 8}).setEdge(Util.north); map.getLoc(new int[] {1, 8}).setEdge(Util.east);
        map.getLoc(new int[] {1, 9}).setEdge(Util.west);

        // i=2
        map.getLoc(new int[] {2, 0}).setEdge(Util.east);
        map.getLoc(new int[] {2, 1}).setEdge(Util.west);
        //2,2
        map.getLoc(new int[] {2, 3}).setEdge(Util.south); map.getLoc(new int[] {2, 4}).setEdge(Util.east);
        map.getLoc(new int[] {2, 4}).setEdge(Util.south); map.getLoc(new int[] {2, 5}).setEdge(Util.west);
        map.getLoc(new int[] {2, 5}).setEdge(Util.south);
        //2,6
        map.getLoc(new int[] {2, 7}).setEdge(Util.south);
        map.getLoc(new int[] {2, 8}).setEdge(Util.east);
        map.getLoc(new int[] {2, 9}).setEdge(Util.west);

        // i=3
        //3,0
        //3,1
        //3,2
        map.getLoc(new int[] {3, 3}).setEdge(Util.north);
        map.getLoc(new int[] {3, 4}).setEdge(Util.north);
        map.getLoc(new int[] {3, 5}).setEdge(Util.north);
        map.getLoc(new int[] {3, 6}).setEdge(Util.east);
        map.getLoc(new int[] {3, 7}).setEdge(Util.north); map.getLoc(new int[] {3, 7}).setEdge(Util.west);
        map.getLoc(new int[] {3, 8}).setEdge(Util.east);
        map.getLoc(new int[] {3, 9}).setEdge(Util.west);

        // i=4
        map.getLoc(new int[] {4, 0}).setEdge(Util.east);
        map.getLoc(new int[] {4, 1}).setEdge(Util.south); map.getLoc(new int[] {4, 1}).setEdge(Util.west);
        map.getLoc(new int[] {4, 2}).setEdge(Util.south); map.getLoc(new int[] {4, 2}).setEdge(Util.east);
        map.getLoc(new int[] {4, 3}).setEdge(Util.north); map.getLoc(new int[] {4, 3}).setEdge(Util.west);
        //4,4
        map.getLoc(new int[] {4, 5}).setEdge(Util.south);
        map.getLoc(new int[] {4, 6}).setEdge(Util.south);
        map.getLoc(new int[] {4, 7}).setEdge(Util.south);
        map.getLoc(new int[] {4, 8}).setEdge(Util.south);
        //4,9

        // i=5
        map.getLoc(new int[] {5, 0}).setEdge(Util.east);
        map.getLoc(new int[] {5, 1}).setEdge(Util.west); map.getLoc(new int[] {5, 1}).setEdge(Util.north);
        map.getLoc(new int[] {5, 2}).setEdge(Util.north);
        map.getLoc(new int[] {5, 3}).setEdge(Util.north);
        //5,4
        map.getLoc(new int[] {5, 5}).setEdge(Util.north); map.getLoc(new int[] {5, 5}).setEdge(Util.east);
        map.getLoc(new int[] {5, 6}).setEdge(Util.north); map.getLoc(new int[] {5, 6}).setEdge(Util.west);
        map.getLoc(new int[] {5, 7}).setEdge(Util.north); map.getLoc(new int[] {5, 7}).setEdge(Util.east);
        map.getLoc(new int[] {5, 8}).setEdge(Util.north); map.getLoc(new int[] {5, 8}).setEdge(Util.west); map.getLoc(new int[] {5, 8}).setEdge(Util.east);
        map.getLoc(new int[] {5, 9}).setEdge(Util.west);

        // i=6
        map.getLoc(new int[] {6, 0}).setEdge(Util.east);
        map.getLoc(new int[] {6, 1}).setEdge(Util.west); map.getLoc(new int[] {6, 1}).setEdge(Util.south);
        map.getLoc(new int[] {6, 2}).setEdge(Util.south);
        //6,3
        map.getLoc(new int[] {6, 4}).setEdge(Util.south);
        map.getLoc(new int[] {6, 5}).setEdge(Util.south);
        map.getLoc(new int[] {6, 6}).setEdge(Util.south);
        map.getLoc(new int[] {6, 7}).setEdge(Util.south);
        map.getLoc(new int[] {6, 8}).setEdge(Util.south); map.getLoc(new int[] {6, 8}).setEdge(Util.east);
        map.getLoc(new int[] {6, 9}).setEdge(Util.south); map.getLoc(new int[] {6, 9}).setEdge(Util.west);

        // i=7
        //7,0
        map.getLoc(new int[] {7, 1}).setEdge(Util.north);
        map.getLoc(new int[] {7, 2}).setEdge(Util.north);
        //7,3
        map.getLoc(new int[] {7, 4}).setEdge(Util.north);
        map.getLoc(new int[] {7, 5}).setEdge(Util.north);
        map.getLoc(new int[] {7, 6}).setEdge(Util.north);
        map.getLoc(new int[] {7, 7}).setEdge(Util.north);
        map.getLoc(new int[] {7, 8}).setEdge(Util.north);
        //7,9

    }
}