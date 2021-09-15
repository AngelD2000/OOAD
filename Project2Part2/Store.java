import java.util.*;

public class Store {
    float storeTotal = 0;
    int moneyFills = 0;
    int startingGames = 3;
    String[] employeeNames = {"Burt", "Ernie"};
    Double[] vacSkill = {.05, .1};
    String[] gameNames = {"Catan", "Gloomhaven", "Risk"};
    List<Cashier> cashiers = new ArrayList<>();
    HashMap<String, Game> inventory = new HashMap<String, Game>();
    HashMap<String, Game> brokenGames = new HashMap<String, Game>();
    HashMap<String, Game> orderedGames = new HashMap<String, Game>();
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
        removeGame(brokenGame, inventory);
        addGame(brokenGame, brokenGames);
        //TODO: Cashier stacks ordered games
        //TODO: Cashier opens store
        //TODO: Cashier orders more games
        //NOTE: The tasks above should be done in cashier, but they would like to access the game lists and add/removeGame functions here so GL
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
     * Function to handle removing a game of name from a game list of location
     * Used for breaking games and selling games
     */
    void removeGame(String game, HashMap<String, Game> location){
        if (game != null){
            Game gameObject = location.get(game);
            gameObject.incrementCount(-1);
            if (gameObject.getCount() == 0){
                location.remove(game);
            }
        }
    }
    /**
     * Function to handle adding a game of name from a game list of location
     */
    void addGame(String game, HashMap<String, Game> location){
        if (game != null){
            if (location.containsKey(game)){
                location.get(game).incrementCount(1);
            }
            else{
                int[] dimensions = {10, 10, 10};
                BoardGame x = new BoardGame();
                x.init(game, dimensions);
                x.incrementCount(1);
                location.put(game, x);
            }
        }
    }
    /**
     * Pretty prints games in list
     */
    void prettyPrintList(HashMap<String, Game> list){
        //https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/
        list.forEach((key, value) -> System.out.println("    -" + key + " : " + (value.getCount())));
    }
    /**
     * Pretty prints games in inventory
     */
    void printInventory(){
        print("Games in inventory: ");
        prettyPrintList(inventory);
    }
    /**
     * Pretty prints broken games
     */
    void printBroken(){
        print("Games that were broken: ");
        prettyPrintList(brokenGames);
    }

    /**
     * Prints all the stuff needed at end of sim
     */
    void finalSummary(){
        print("END OF SIMULATION");
        printInventory();
        printBroken();
        countRegister();
        print("The money was refilled " + String.valueOf(moneyFills) + "time(s)");
    }
}
