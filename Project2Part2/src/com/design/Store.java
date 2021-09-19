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
//    String[] familyGames = {"Monopoly", "Clue", "Life"};
//    String[] kidsGames = {"Mousetrap", "Candyland","Connect Four"};
//    String[] cardGames = {"Magic", "Pokemon", "Netrunner"};
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
//        FamilyGames familyGame = new FamilyGames();
//        KidsGames kidsGame = new KidsGames();
//        CardGames cardGame = new CardGames();
        for(int i = 0; i < boardGames.length; i++){
            int price = r.nextInt((100 - 5) + 1) + 5;
            Game game = new Game();
            ArrayList<Integer> dimensions = game.assign_dim();
            game.newGame(boardGames[i], dimensions, price, boardGame);
            game.setCount(3);
            inventory.put(boardGames[i], game);
//            game.newGame(familyGames[i], dimensions, price, familyGame);
//            game.newGame(kidsGames[i], dimensions, price, kidsGame);
//            game.newGame(cardGames[i], dimensions, price, cardGame);
            //TODO: Put the games in inventory
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
        Cashier currentCashier = pickCashier();
        currentCashier.arrive(day);
        float curr_storeTotal = register.getStoreTotal();
        int moneyFills = register.getMoneyFills();
        register.checkIfNeedFill(print);
        //Vacuum and break games
        String brokenGame = currentCashier.vacuum(inventory);
        boolean flag_vac = inventory.removeGame(brokenGame, orderedGames);
        if(flag_vac){
            orderedGames.put(brokenGame, inventory.get(brokenGame).getType());
        }
        Game game = inventory.get(brokenGame);
        brokenGames.addGame(brokenGame, game);
        //Cashier stacks ordered games
        currentCashier.stackShelf(inventory,orderedGames,print);
        //Cashier opens the store
        currentCashier.storeOpen(inventory, orderedGames, register, print);
        //Cashier orders the games
        orderedGames.orderGame(inventory, register);
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
