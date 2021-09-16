abstract class Game {
    String name;
    int[] dimensions;
    int count;
    int cost;
    void setName(String newName){
        name = newName;
    }
    void setDimensions(int[] newDimensions){
        dimensions = newDimensions;
    }
    void init(String newName, int[] newDimensions){
        setName(newName);
        setDimensions(newDimensions);
        count = 0;
    }
    /**
     * Change count by value (can be negative)
     */
    void incrementCount(int value){
        count += value;
    }
    int getCount(){
        return count;
    }
    /**
    * Set cost of the game
    * */
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
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