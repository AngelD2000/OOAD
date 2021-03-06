package com.design;
import java.util.*;
import java.util.stream.IntStream;

public class Store {
    List<Cashier> cashiers = new ArrayList<>();
    GameList inventory = new GameList();
    GameList brokenGames = new GameList();
    GameList orderedGames = new GameList();
    Register register = new Register();
    Announcer guy;
    Baker baker;
    Cashier currentCashier;
    int cookies = 0;
    int cookiesSold = 0;
    List<Integer> allCookiesSold = new ArrayList<Integer>();
    int eatenCookies = 0;
    List<Integer> customers = new ArrayList<>();
    Store() {
        //Create employees
        guy = new Announcer("Guy");

        for (int i = 0; i < Util.employeeNames.length; i++) {
            Cashier temp = new Cashier(Util.employeeNames[i], Util.vacSkill[i], Util.stackPref[i]);
            //Subscribe to Announcer
            temp.subscribe(guy);
            Util.print(guy.getName() + " Subscribed to " + temp.getName());
            cashiers.add(temp);
        }

        baker = new Baker("Gonger");
        //Subscribe to Announcer
        baker.subscribe(guy);
        Util.print(guy.getName() + " Subscribed to " + baker.getName());

        //Create inventory
        initGames("BoardGame", Util.boardGames);
        initGames("FamilyGame", Util.familyGames);
        initGames("KidsGame", Util.kidsGames);
        initGames("CardGame", Util.cardGames);

        inventory.printGameAmount();

        Double[] shelfProbability = new Double[inventory.size()];
        for (int j = 0; j < inventory.size(); j++) {
            shelfProbability[j] = .2 - (2 * j);
        }
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
            price = Util.rndFromRange(10, 50);
            switch (type) {
                case "BoardGame":
                    if(gameName == "Gloomhaven"){
                        game = new Gloomhaven(gameName, dimensions, price);
                        game = new AddOn(game, "custom miniatures", 4, .2, 10);
                    }
                    else if(gameName == "Risk"){
                        game = new Risk(gameName, dimensions, price);
                    }
                    else{
                        game = new Catan(gameName, dimensions, price);
                    }
                    break;
                case "FamilyGame":
                    if(gameName == "Clue"){
                        game = new Clue(gameName, dimensions, price);
                    }
                    else if(gameName == "Monopoly"){
                        game = new Monopoly(gameName, dimensions, price);
                        game = new AddOn(game, "Special Token pack", 1, .5, 5);
                    }
                    else {
                        game = new Life(gameName, dimensions, price);
                    }
                    break;
                case "KidsGame":
                    if(gameName == "Mousetrap"){
                        game = new Mousetrap(gameName, dimensions, price);
                        game = new AddOn(game, "spare parts", 2, .3, 1);
                    }
                    else if(gameName == "Candyland"){
                        game = new Candyland(gameName, dimensions, price);
                    }
                    else{
                        game = new ConnectFour(gameName, dimensions, price);
                    }
                    break;
                case "CardGame":
                    if(gameName == "Magic"){
                        game = new Magic(gameName, dimensions, price);
                    }
                    else if(gameName == "Pokemon"){
                        game = new Pokemon(gameName, dimensions, price);
                    }
                    else{
                        game = new Netrunner(gameName, dimensions, price);
                    }
                    game = new AddOn(game, "Special Cards", 6, .2, 1);
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
        Util.print("--- Simulation starting day " + String.valueOf(day));
        guy.arrive(day); //Announcer arrives first
        currentCashier = pickCashier();
        currentCashier.arrive(day);
        //Cashier stacks ordered games
        currentCashier.unpackOrders(inventory, orderedGames);
        currentCashier.report("sees that " + register.checkIfNeedFill());
        //Baker comes
        welcomeBaker();
        //Vacuum and break games
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
        currentCashier.orderCookies(baker, this);
        currentCashier.close();
        guy.leave(); //Announcer leaves last
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
        cookiesSold = 0;
        double cost = baker.sellCookies();
        register.incrementStoreTotal(cost);
        cookies += Util.dozen*baker.getDozenPerDay();
    }

    /**
     * Prints all the stuff needed at end of 30 days
     */
    public void finalSummary() {
        Util.print("==Store Summary Report==");
        Util.printInventory(inventory);
        Util.printBroken(brokenGames);
        Util.print(register.printAmount());
        Util.print("The money was refilled " + register.getMoneyFills() + " time(s)");
        //Number of cookies sold each day and total
        int sum = 0;
        for(int i = 0; i < allCookiesSold.size(); i++)
            sum += allCookiesSold.get(i);
        Util.print(sum + " cookies were sold in total");
        Util.print("For each of the days, the number of cookies sold were: ");
        Util.print(allCookiesSold + "");
        //Number of cookies eaten by monster
        Util.print(eatenCookies + " total cookies have been eaten by cookie monster");
        //Amount paid to Gonger
        Util.print("Gonger was paid " + Util.asDollar(baker.getPocketAmount()) + " in total");
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
     * Cookie monster goes on a rampage (sugar rush)
     */
    public void rampage() {
        currentCashier.report("saw the cookie monster come in.");
        if(cookies > 0){
            eatenCookies+=cookies;
            currentCashier.report("watched in horror as the cookie monster ate " + cookies + " cookies!");
            cookies = 0;
            int brokenGames = Util.rndFromRange(1, Util.maxMonsterBreaks);
            for(int i = 0; i < brokenGames; i++){
                currentCashier.report("watched in terror as the cookie monster " + breakGame());
            }
        }
        else{
            currentCashier.report("watched in joy as the cookie monster cried for lack of cookies.");
        }
    }
    /**
     * Selling cookies
     * - Increase number of cookies sold
     * - increase register
     */
    public void sellCookies(int numCookies) {
        cookiesSold+=numCookies;
        register.incrementStoreTotal(numCookies*Util.cookiePricePerDozen/12*2);
    }
    /**
     * Get the current number of cookies
     * */
    public int getCookieInventory(){
        return this.cookies;
    }
    public int getCookieSold(){
        return this.cookiesSold;
    }
    /**
     * Store adds the cookies sold today to the total cookies sold
     */
    public void countMissingCookies(){
        allCookiesSold.add(cookiesSold);
    }
}
