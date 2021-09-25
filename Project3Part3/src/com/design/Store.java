package com.design;
import java.util.*;

public class Store {
    List<Cashier> cashiers = new ArrayList<>();
    GameList inventory = new GameList();
    GameList brokenGames = new GameList();
    GameList orderedGames = new GameList();
    Register register = new Register();
    Announcer guy;
    Baker baker;
    int cookies = 0;
    int cookiesSold = 0;
    int cookiesSoldTotal = 0;
    int eatenCookies = 0;
    Store() {
        //Create employees
        for (int i = 0; i < Util.employeeNames.length; i++) {
            Cashier temp = new Cashier(Util.employeeNames[i], Util.vacSkill[i], Util.stackPref[i]);
            cashiers.add(temp);
        }
        guy = new Announcer("Guy");
        baker = new Baker("Gongor");
        //Create inventory
        initGames("BoardGame", Util.boardGames);
        initGames("FamilyGame", Util.familyGames);
        initGames("KidsGame", Util.kidsGames);
        initGames("CardGame", Util.cardGames);


        Double[] shelfProbability = new Double[inventory.size()];
        for (int j = 0; j < inventory.size(); j++) {
            shelfProbability[j] = .2 - (2 * j);
        }
    }

    /**
     * Run simulation for Util.simDays
     */
    public void runSim() {
        cashiers.get(0).subscribe(guy);
        guy.onNext("This");
                for(int i = 0; i < Util.simDays; i++){
            runDay(i + 1);
        }
        finalSummary();
        Util.print("END OF SIMULATION");
    }

    /**
     * Function to initialize the games in the store inventory and set their initial price, dimensions, and count
     */
    private void initGames(String type, String[] gameNames) {
        Random r = new Random();
        int price = 0;

        for (String gameName : gameNames) {
            ArrayList<Integer> dimensions = Util.assign_dim();
            Game game;
            price = r.nextInt((100 - 5) + 1) + 5;
            switch (type) {
                case "BoardGame":
                    game = new BoardGame(gameName, dimensions, price);
                    break;
                case "FamilyGame":
                    game = new FamilyGame(gameName, dimensions, price);
                    break;
                case "KidsGame":
                    game = new KidsGame(gameName, dimensions, price);
                    break;
                case "CardGame":
                    game = new CardGame(gameName, dimensions, price);
                    break;
                default:
                    game = new BoardGame(gameName, dimensions, price);
            }
            game.setCount(Util.maxInventory);
            inventory.put(gameName, game);
        }
    }

    /**
     * Runs the store through all the actions needed for a day
     */
    void runDay(int day) {
        Util.print("Start of day " + String.valueOf(day));
        Cashier currentCashier = pickCashier();
        currentCashier.arrive(day);
        //Cashier stacks ordered games
        currentCashier.unpackOrders(inventory, orderedGames);
        double curr_storeTotal = register.getStoreTotal();
        int moneyFills = register.getMoneyFills();
        register.checkIfNeedFill();
        //Baker comes
        welcomeBaker();
        //Vacuum and break games
        //Cashier vaccuums; check if game broken
        if(currentCashier.vacuum()){
            currentCashier.report(breakGame());
        }
        currentCashier.report("has finished vacuuming the store.");
        //Cashier stacks ordered games
        currentCashier.stackShelf(inventory, orderedGames);
        //Cashier opens the store
        currentCashier.storeOpen(inventory, register, this);
        //Cashier orders the games
        currentCashier.orderGame(orderedGames, inventory, register);
        currentCashier.close();
        Util.print("End of day " + String.valueOf(day));
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
     * Gets baker into the store
     * Buys cookies from baker, updates register
     */
    public void welcomeBaker() {
        double cost = baker.sellCookies();
        register.incrementStoreTotal(cost);
        cookies += Util.dozen*baker.getDozenPerDay();
    }

    /**
     * Prints all the stuff needed at end of each day
     */
    public void finalSummary() {
        Util.printInventory(inventory);
        Util.printBroken(brokenGames);
        register.printAmount();
        Util.print("The money was refilled " + register.getMoneyFills() + " time(s)");
        //Number of cookies sold each day and total
        Util.print("Today " + cookiesSold + " cookies were sold today");
        Util.print("Today " + cookiesSoldTotal + " cookies were sold in total");
        //Number of cookies eaten by monster
        Util.print(eatenCookies + " total cookies have been eaten by cookie monster");
        //Amount paid to Gonger
        Util.print("Gonger was paid " + baker.getPocketAmount() + " in total");
    }
    /**
     * Breaks a game in inventory
     */
    public String breakGame() {
        Random rand = new Random();
        String answer = "";
        //Random key of hashmap: https://stackoverflow.com/questions/12385284/how-to-select-a-random-key-from-a-hashmap-in-java
        List<String> keysAsArray = new ArrayList<String>(inventory.keySet());
        String broken = keysAsArray.get(rand.nextInt(keysAsArray.size()));
        if(inventory.getTotalInventory() > 0){
            while (inventory.get(broken).getCount() == 0){
                broken = keysAsArray.get(rand.nextInt(keysAsArray.size()));
            }
            if(inventory.get(broken).getCount() == 1){
                answer = "broke the last " + broken;
            }
            else{
                answer = "broke a " + broken;
            }
            inventory.removeGame(broken, false);
            brokenGames.addGame(broken, inventory.get(broken));
        }
        else{
            answer = "tried to break a game but there were no games to break.";
        }
        return answer;
    }

    /**
     * Cookie monster goes on a rampage
     */
    public void rampage() {
        Util.print("Oh not, the cookie monster came in.");
        if(cookies > 0){
            eatenCookies+=cookies;
            Util.print("The cookie monster ate " + cookies + " cookies!");
            cookies = 0;
            Random rand = new Random();
            int brokenGames = rand.nextInt(Util.maxCookiesDesired-1) + 1;
            for(int i = 0; i < brokenGames; i++){
                Util.print("The cookie monster " + breakGame());
            }
        }
        else{
            Util.print("The cookie monster was so sad it couldn't eat any cookies. It cried and left");
        }
    }
}
