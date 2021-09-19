package com.design;
import java.lang.reflect.Array;
import java.util.*;


public class Store {

    private final String[] employeeNames = {"Burt", "Ernie"};
    //TODO: Set these values to be right once tested better
    private final Double[] vacSkill = {.5, .5};
    private final String[] stackPref = {"width", "height"};
    List<Cashier> cashiers = new ArrayList<>();

    String[] boardGames = {"Catan", "Gloomhaven", "Risk"};
    GameList inventory = new GameList();
    GameList brokenGames = new GameList();
    GameList orderedGames = new GameList();
    Register register = new Register();
    Print print = new Print();


    Store(){
        for (int i = 0; i < employeeNames.length; i++) {
            Cashier temp = new Cashier(employeeNames[i], vacSkill[i], stackPref[i]);
            cashiers.add(temp);
        }

        Random r = new Random();
        BoardGames boardGame = new BoardGames();
        for(int i = 0; i < boardGames.length; i++){
            int price = r.nextInt((100 - 5) + 1) + 5;
            Game board = new Game();
            ArrayList<Integer> dimensions = assign_dim();
            board.newGame(boardGames[i], dimensions, price, boardGame);
            board.setCount(3);
            inventory.put(boardGames[i], board);
        }
        Double[] shelfProbability = new Double[inventory.size()];
        for(int j = 0; j < inventory.size();j++){
            shelfProbability[j] = .2 - (2*j);
        }
    }
    /**
     * Runs the store through all the actions needed for a day
     */
    void runDay(int day){
        boolean flag_vac = false;
        Cashier currentCashier = pickCashier();
        currentCashier.arrive(day);
        float curr_storeTotal = register.getStoreTotal();
        int moneyFills = register.getMoneyFills();

        if (curr_storeTotal < 100){
            register.setStoreTotal(1000 + curr_storeTotal);
            register.setMoneyFills(moneyFills + 1);
            print.print("Register had to be filled");
            register.printAmount();
        }
        //Vacuum and break games
        String brokenGame = currentCashier.vacuum(inventory);
        flag_vac = inventory.removeGame(brokenGame, orderedGames);
        if(flag_vac){
            orderedGames.put(brokenGame, inventory.get(brokenGame).getType());
        }
        Game game = inventory.get(brokenGame);
        brokenGames.addGame(brokenGame, game);
        //TODO: Cashier stacks ordered games
        if(orderedGames.size() > 0){
            //Stacking game
            for (Map.Entry<String, Game> order:
                    orderedGames.entrySet()) {
                String ordered_game = order.getKey();
                Game restock = inventory.get(ordered_game);
                restock.setCount(3);
                print.print("ORDER ARRIVED " + restock.getGameName() + " is now in stock");
            }
            orderedGames.clear();
        }
        currentCashier.stackShelf(inventory);
        currentCashier.open();
        storeOpen(inventory);
        orderGames();
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
    public void storeOpen(GameList items){
        boolean flag_store = false;
        Random customer_rand = new Random();
        String game_buy = "";
        int num_customers = customer_rand.nextInt(5);
        print.print(num_customers + " customers entered the store.");
        for(int i = 0; i < num_customers; i++){
            Random game_rand = new Random();
            int num_games = game_rand.nextInt(3);
            Random rand = new Random();
            if(num_games == 0){
                print.print("Customer " + (i + 1)+ " didn't buy a game.");
            }
            for(int j = 0; j < num_games; j++){
                List<String> keysAsArray = new ArrayList<String>(items.keySet());
                game_buy = keysAsArray.get(rand.nextInt(keysAsArray.size()));
                int cost = inventory.get(game_buy).getCost();
                register.setStoreTotal(register.getStoreTotal() + cost);
                if(inventory.get(game_buy).getCount() == 0){
                    print.print(game_buy + " out of stock.");
                }
                else{
                    flag_store = inventory.removeGame(game_buy,orderedGames);
                    if(flag_store){
                        orderedGames.put(game_buy, inventory.get(game_buy).getType());
                        print.print(game_buy + " out of stock.");
                    }
                    else {
                        print.print("Customer " + (i + 1) + " bought " + game_buy);
                    }
                }
            }
        }
    }

    public void orderGames(){
        for (Map.Entry<String, Game> order: orderedGames.entrySet()) {
            String gameName = order.getKey();
            float totalUpdated = orderedGames.orderGame(gameName, inventory, register.getStoreTotal());
            register.setStoreTotal(totalUpdated);
        }
    }

    public ArrayList<Integer> assign_dim(){
        ArrayList<Integer> dimensions = new ArrayList<>();
        Random dim = new Random();
        int length = dim.nextInt(10 + 1);
        int width = dim.nextInt((20 - 10) + 1) + 10;
        int height = dim.nextInt(20 + 1);
        dimensions.add(length);
        dimensions.add(width);
        dimensions.add(height);
        return dimensions;
    }
    /**
     * Prints all the stuff needed at end of sim
     */
    public void finalSummary(){
        print.print("END OF SIMULATION");
        print.printInventory(inventory);
        print.printBroken(brokenGames);
        register.printAmount();
        print.print("The money was refilled " + register.getMoneyFills() + " time(s)");
    }
}
