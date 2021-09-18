package com.design;
import java.lang.reflect.Array;
import java.util.*;


public class Store {

    private final String[] employeeNames = {"Burt", "Ernie"};
    private final Double[] vacSkill = {.5, .5};
    List<Cashier> cashiers = new ArrayList<>();

    //TODO: Set these values to be right once tested better
    String[] boardGames = {"Catan", "Gloomhaven", "Risk"};
    GameList inventory = new GameList();
    GameList brokenGames = new GameList();
    GameList orderedGames = new GameList();
    Register register = new Register();
    Print print = new Print();

    Store(){
        for (int i = 0; i < employeeNames.length; i++) {
            Cashier temp = new Cashier(employeeNames[i], vacSkill[i]);
            cashiers.add(temp);
        }

        Random r = new Random();
        BoardGames boardGame = new BoardGames();
        for(int i = 0; i < boardGames.length; i++){
            int price = r.nextInt((100 - 5) + 1) + 5;
            Game board = new Game();
            ArrayList<Integer> dimensions = assign_dim();
            board.newGame(boardGames[i], dimensions, price, boardGame);
            board.incrementCount(3);
            inventory.put(boardGames[i], board);
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
            System.out.println("Register filled to $1000");
        }
        //Vacuum and break games
        String brokenGame = currentCashier.vacuum(inventory);
        boolean flag = inventory.removeGame(brokenGame);
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
                System.out.println("ORDER ARRIVED " + restock.getGameName() + " is now in stock");
            }
            orderedGames.clear();
        }
        stackShelf(currentCashier);
        //TODO: Cashier opens store
        currentCashier.open();
        //storeOpen();
        if(flag){
            orderGame(brokenGame, register);
        }
        //Cashier have finished ordering
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

    public void orderGame(String gameName, Register register){
        float totalUpdated = orderedGames.orderGame(gameName, inventory, register.getStoreTotal());
        register.setStoreTotal(totalUpdated);
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
    public void stackShelf(Cashier at_work){
        String working_cashier = at_work.getName();
        String gameName = "";
        int shelfSize = inventory.size();
        if(working_cashier == "Burt"){
            //Loop through width to restack from widest to narrowest
            int curr_width = 0;
            ArrayList<String> gameAssigned = new ArrayList<>();
            for(int i = 0; i < shelfSize; i++){
                int max_width = Integer.MIN_VALUE;
                for (Map.Entry<String, Game> item:
                        inventory.entrySet()) {
                    if(!gameAssigned.contains(item.getKey())){
                        curr_width = item.getValue().getGameDimension().get(1);
                        if(curr_width > max_width){
                            max_width = curr_width;
                            gameName = item.getKey();
                        }
                    }
                }
                Game game = inventory.get(gameName);
                game.setPosOnShelf(i+1);
                gameAssigned.add(game.getGameName());
            }
        }
        else{
            //Loop shortest to tallest
            int curr_height = 0;
            ArrayList<String> gameAssigned = new ArrayList<>();
            for(int i = 0; i < shelfSize; i++){
                int min_height = Integer.MAX_VALUE;
                for (Map.Entry<String, Game> item:
                        inventory.entrySet()) {
                    if(!gameAssigned.contains(item.getKey())){
                        curr_height = item.getValue().getGameDimension().get(2);
                        if(curr_height < min_height){
                            min_height = curr_height;
                            gameName = item.getKey();
                        }
                    }
                }
                Game game = inventory.get(gameName);
                game.setPosOnShelf(i+1);
                gameAssigned.add(game.getGameName());
            }
        }
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


//    void storeOpen(){
//        if(invent.getOrder_games().size() > 0){
//            for(int j = 0; j < orderedGames.size(); j++){
//                orderedGames.get(j);
//            }
//        }
//        Random rand = new Random();
//        Double[] shelfPos = {.2,.38,.54,.68,.8,.9,.98,1.0};
//        int numCustomers = rand.nextInt(4);
//        for(int i = 0; i < numCustomers; i++){
//            double randomValue = Math.random();
//            for(int j = 0; j < shelfPos.length; j++){
//                if(randomValue <= shelfPos[j]){
//                    /** Choose game from the stack
//                     *
//                     * */
//                }
//            }
//        }
//    }