import java.util.*;

public class Store {
    float storeTotal = 0;
    int moneyFills = 0;
    int startingGames = 3;
    String[] employeeNames = {"Burt", "Ernie"};
    //TODO: Set these values to be right once tested better
    Double[] vacSkill = {.5, .5};
    String[] gameNames = {"Catan", "Gloomhaven", "Risk"};
    List<Cashier> cashiers = new ArrayList<>();
    GameList inventory = new GameList();
    GameList brokenGames = new GameList();
    GameList orderedGames = new GameList();
    Store(){
        for (int i = 0; i < employeeNames.length; i++) {
            Cashier temp = new Cashier(employeeNames[i], vacSkill[i]);
            cashiers.add(temp);
        }
        int[] dimensions = {10, 10, 10};
        for(int i = 0; i < gameNames.length; i++){
            BoardGame x = new BoardGame();
            x.init(gameNames[i], dimensions);
            x.incrementCount(startingGames);
            inventory.put(gameNames[i], x);
        }
    }
    /**
     * Runs the store through all the actions needed for a day
     */
    void runDay(){
        Cashier currentCashier = pickCashier();
        currentCashier.arrive();
        countRegister();
        //Vacuum and break games
        String brokenGame = currentCashier.vacuum(inventory);
        inventory.removeGame(brokenGame);
        brokenGames.addGame(brokenGame);
        //TODO: Cashier stacks ordered games
        //TODO: Cashier opens store
        //TODO: Cashier orders more games
        currentCashier.close();
    }
    /**
     * Pick a random cashier to work today
     */
    Cashier pickCashier(){
        //Method: https://www.baeldung.com/java-random-list-element
        Random rand = new Random();
        return cashiers.get(rand.nextInt(cashiers.size()));
    }
    /**
     * Counts the money in register and fills it if needed
     * TODO: Should be in cashier? Pass by reference?
     */
    void countRegister(){
        print("Register has $" + String.valueOf(storeTotal));
        if (storeTotal == 0){
            storeTotal = 1000;
            moneyFills = moneyFills+1;
            print("Register filled so it has $1000");
        }
    }
    /**
     * Function to handle printing syntax
     */
    void print(String content){
        System.out.println(content);
    }
    /**
     * Pretty prints games in inventory
     */
    void printInventory(){
        print("Games in inventory: ");
        inventory.prettyPrintList();
    }
    /**
     * Pretty prints broken games
     */
    void printBroken(){
        print("Games that were broken: ");
        brokenGames.prettyPrintList();
    }

    /**
     * Prints all the stuff needed at end of sim
     */
    void finalSummary(){
        print("END OF SIMULATION");
        printInventory();
        printBroken();
        countRegister();
        print("The money was refilled " + String.valueOf(moneyFills) + " time(s)");
    }
}
