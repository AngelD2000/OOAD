abstract class Game {
    String name;
    int[] dimensions;
    void setName(String newName){
        name = newName;
    }
    void setDimensions(int[] newDimensions){
        dimensions = newDimensions;
    }
    void init(String newName, int[] newDimensions){
        setName(newName);
        setDimensions(newDimensions);
    }
}

abstract class FamilyGame extends Game {
}

class KidGame extends Game {
}

class CardGame extends Game {
}

class BoardGame extends Game {
}