abstract class Game {
    String name;
    int[] dimensions;
    void setName(String newName){
        name = newName;
    }
    void setDimensions(int[] newDimensions){
        dimensions = newDimensions;
    }
}

class FamilyGame extends Game {
    FamilyGame(String setName, int[] newDimensions){
        setName(setName);
        setDimensions(newDimensions);
    }
}

class KidGame extends Game {
    KidGame(String setName, int[] newDimensions){
        setName(setName);
        setDimensions(newDimensions);
    }
}

class CardGame extends Game {
    CardGame(String setName, int[] newDimensions){
        setName(setName);
        setDimensions(newDimensions);
    }
}

class BoardGame extends Game {
    BoardGame(String setName, int[] newDimensions){
        setName(setName);
        setDimensions(newDimensions);
    }
}