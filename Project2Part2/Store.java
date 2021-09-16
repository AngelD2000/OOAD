import java.util.*;

public class Store {
    int startingGames = 3;
    private String[] employeeNames = {"Burt", "Ernie"};
    private List<Cashier> cashiers = new ArrayList<>();
    private Double[] vacSkill_arr = {.5, .5};

    //TODO: Set these values to be right once tested better
    String[] gameNames = {"Catan", "Gloomhaven", "Risk"};
    GameList inventory = new GameList();
    GameList brokenGames = new GameList();
    GameList orderedGames = new GameList();
    Register register = new Register();
    Print print = new Print();

    Store(){
        for (int i = 0; i < employeeNames.length; i++) {
            Cashier temp = new Cashier(employeeNames[i], vacSkill_arr[i]);
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
    void runDay(int day){
        Cashier currentCashier = pickCashier();
        currentCashier.arrive(day);
        float curr_storeTotal = register.getStoreTotal();
        int moneyFills = register.getMoneyFills();
        print.print("Register has $ " + curr_storeTotal);
        if (curr_storeTotal < 100){
            register.setStoreTotal(1000 + curr_storeTotal);
            register.setMoneyFills(moneyFills + 1);
            System.out.println("Register filled so it has $1000");
        }
        //Vacuum and break games
        String brokenGame = currentCashier.vacuum(inventory);
        inventory.removeGame(brokenGame);
        brokenGames.addGame(brokenGame);
        //TODO: Cashier stacks ordered games
        //TODO: Cashier opens store
        currentCashier.open();
        //TODO: Cashier orders more games
        currentCashier.close();
    }
    /**
     * Pick a random cashier to work today
     */

    public Cashier pickCashier(){
        //Method: https://www.baeldung.com/java-random-list-element
        Random rand = new Random();
        return cashiers.get(rand.nextInt(cashiers.size()));
    }

    /**
     * Prints all the stuff needed at end of sim
     */
    public void finalSummary(){
        print.print("END OF SIMULATION");
        print.printInventory(inventory);
        print.printBroken(brokenGames);
        System.out.println("Register has $ " + register.getStoreTotal());
        print.print("The money was refilled " + register.getMoneyFills() + " time(s)");
    }
}
