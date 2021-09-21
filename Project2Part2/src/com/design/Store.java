package com.design;
import java.util.*;

public class Store {
    List<Cashier> cashiers = new ArrayList<>();
    GameList inventory = new GameList();
    GameList brokenGames = new GameList();
    GameList orderedGames = new GameList();
    Register register = new Register();

    Store() {
        for (int i = 0; i < Util.employeeNames.length; i++) {
            Cashier temp = new Cashier(Util.employeeNames[i], Util.vacSkill[i], Util.stackPref[i]);
            cashiers.add(temp);
        }

        initGames("BoardGame", Util.boardGames);
        initGames("FamilyGame", Util.familyGames);
        initGames("KidsGame", Util.kidsGames);
        initGames("CardGame", Util.cardGames);


        Double[] shelfProbability = new Double[inventory.size()];
        for (int j = 0; j < inventory.size(); j++) {
            shelfProbability[j] = .2 - (2 * j);
        }
    }

    private void initGames(String type, String[] gameNames) {
        Random r = new Random();
        for (int i = 0; i < gameNames.length; i++) {
            int price = r.nextInt((100 - 5) + 1) + 5;
            ArrayList<Integer> dimensions = Util.assign_dim();

            Game game;
            switch(type) {
                case "BoardGame":
                    game = new BoardGame(gameNames[i], dimensions, price);
                    break;
                case "FamilyGame":
                    game = new FamilyGame(gameNames[i], dimensions, price);
                    break;
                case "KidsGame":
                    game = new KidsGame(gameNames[i], dimensions, price);
                    break;
                case "CardGame":
                    game = new CardGame(gameNames[i], dimensions, price);
                    break;
                default:
                    game = new BoardGame(gameNames[i], dimensions, price);
            }

//            game.newGame(gameNames[i], dimensions, price, game);
            game.setCount(3);
            inventory.put(gameNames[i], game);
        }
    }

    /**
     * Runs the store through all the actions needed for a day
     */
    void runDay(int day) {
        Cashier currentCashier = pickCashier();
        currentCashier.arrive(day);
        double curr_storeTotal = register.getStoreTotal();
        int moneyFills = register.getMoneyFills();
        register.checkIfNeedFill();
        //Vacuum and break games
        String brokenGame = currentCashier.vacuum(inventory);
        boolean flag_vac = inventory.removeGame(brokenGame, orderedGames);
        if (flag_vac) {
//            orderedGames.put(brokenGame, inventory.get(brokenGame).getType());
            orderedGames.put(brokenGame, inventory.get(brokenGame));
        }
        Game game = inventory.get(brokenGame);
        brokenGames.addGame(brokenGame, game);
        //Cashier stacks ordered games
        currentCashier.stackShelf(inventory, orderedGames);
        //Cashier opens the store
        currentCashier.storeOpen(inventory, orderedGames, register);
        //Cashier orders the games
        currentCashier.orderGame(inventory, register);
        currentCashier.close();
    }

    /**
     * Pick a random cashier to work today
     */
    Cashier pickCashier() {
        //Method: https://www.baeldung.com/java-random-list-element
        Random rand = new Random();
        return cashiers.get(rand.nextInt(cashiers.size()));
    }

    /**
     * Prints all the stuff needed at end of sim
     */
    public void finalSummary() {
        Util.print("END OF SIMULATION");
        Util.printInventory(inventory);
        Util.printBroken(brokenGames);
        register.printAmount();
        Util.print("The money was refilled " + register.getMoneyFills() + " time(s)");
    }
}
