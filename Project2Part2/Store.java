import java.util.*;

public class Store {
    float storeTotal = 0;
    int moneyFills = 0;
    String[] employeeNames = {"Burt", "Ernie"};
    Double[] vacSkill = {.05, .1};
    List<Cashier> cashiers = new ArrayList<>();
    //TODO: This probably should be thought out more to reflect position
    List<Game> games = new ArrayList<>();
    List<Game> brokenGames = new ArrayList<>();
    Store(){
        for (int i = 0; i < employeeNames.length; i++) {
            Cashier temp = new Cashier(employeeNames[i], vacSkill[i]);
            cashiers.add(temp);
        }
        int[] dimensions = {10, 10, 10};
        games.add(new BoardGame("Catan", dimensions));
    }
    /**
     * Runs the store through all the actions needed for a day
     */
    void runDay(){
        Cashier currentCashier = pickCashier();
        currentCashier.arrive();
        countRegister();
        //TODO: Move brokenGame from games to brokenGames
        Game brokenGame = currentCashier.vacuum(games);
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
}
