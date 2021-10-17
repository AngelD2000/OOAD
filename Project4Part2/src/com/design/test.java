package com.design;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.ArrayList;

@ExtendWith(gameTestWatcher.class)
public class test {
    //Register Test 1
    @Test
    /**
     * Check the incrementStoreTotal changed the money in register
     */
    void incrementStoreTotal() {
        Register register = new Register();
        register.setStoreTotal(30);
        register.incrementStoreTotal(2500);
        Assertions.assertEquals(2530, register.getStoreTotal());

        register.setStoreTotal(250);
        register.incrementStoreTotal(-50);
        Assertions.assertEquals(200, register.getStoreTotal());
    }

    //Register Test 2
    @Test
    /**
     * Check 1000 added to register when it is less than 100
     */
    void checkIfNeedFill() {
        int start = 99;
        Register register = new Register();
        register.setStoreTotal(start);
        register.checkIfNeedFill();
        Assertions.assertEquals(start+1000, register.getStoreTotal());
    }

    //GameList Test 1
    @Test
    /**
     * Check that addGame correctly removes a game to the gameList
     * Even if inventory is 0, should still exist in gamelist, just 1 less count
     */
    void removeGame() {
        GameList gamelist = new GameList();
        String gameName = "Gloomhaven";
        Game game = new BoardGame(gameName, Util.assign_dim(), 20);
        gamelist.addGame(gameName, game);
        gamelist.get(gameName).setCount(1);
        int hold = gamelist.get(gameName).getCount();
        gamelist.removeGame(gameName,false);
        Assertions.assertFalse(gamelist.isEmpty());
        Assertions.assertEquals(gamelist.get(gameName).getCount(), hold-1);
    }

    //GameList Test 2
    @Test
    /**
     * Check that addGame correctly adds a game to the gameList
     */
    void addGame() {
        GameList gamelist = new GameList();
        String gameName = "Gloomhaven";
        Game game = new BoardGame(gameName, Util.assign_dim(), 20);
        gamelist.addGame(gameName,game);
        Assertions.assertEquals(1,gamelist.size());
    }

    //Cashier Test 1
    @Test
    /**
     * Test that ordering games correctly changes register
     * Also check that ordering and unpacking games puts 3 in inventory
     */
    void orderGame(){
        String gameName = "Gloomhaven";
        Game game = new BoardGame(gameName, Util.assign_dim(), 20);
        game.setCount(Util.maxInventory);
        Cashier cashier = new Cashier("Bernie", .3, Util.height);
        GameList inventory = new GameList();
        inventory.put(gameName,game);
        GameList orderGames = new GameList();
        orderGames.addGame(gameName,game);
        Register register = new Register();
        register.setStoreTotal(300);

        //Customer bought all games
        inventory.get(gameName).setCount(0);
        cashier.orderGame(orderGames,inventory,register);

        Assertions.assertEquals(270, register.getStoreTotal());
    }

    //Cashier Test 2
    @Test
    /**
     * Make sure the cashier orders more cookies for the next day
     * if the store is currently out
     */
    void orderCookies(){
        Cashier cashier = new Cashier("Bern", .3, Util.wide);
        Baker baker = new Baker("Bob");
        Store store = new Store();
        store.cookies = 0;
        cashier.orderCookies(baker,store);
        Assertions.assertEquals(2, baker.getDozenPerDay());
    }

    //Store Test 1
    @Test
    /**
     * Make sure breaking a game will only break games
     * currently in stock
     */
    void breakGameTest(){
        //Test when no games in inventory
        Store store = new Store();
        for (String key : store.inventory.keySet()) {
            store.inventory.get(key).setCount(0);
        }
        String answer = store.breakGame();
        Assertions.assertEquals(0,store.brokenGames.size());
        Assertions.assertEquals(answer,"tried to break a game but there were no games to break.");
        //Test when one game in inventory
        store.inventory.get("Gloomhaven").setCount(1);
        answer = store.breakGame();
        Assertions.assertEquals(1,store.brokenGames.size());
        Assertions.assertEquals(answer,"broke the last Gloomhaven");
    }
    //Store test 2
    /**
     * Make sure the cookie monster eats all the cookies when he enters
     */
    @Test
    void rampage(){
        Store store = new Store();
        Cashier cashier = new Cashier("CookieBen", .2, Util.odd);
        store.cookies = 10;
        store.currentCashier = cashier;
        store.rampage();
        Assertions.assertEquals(10, store.eatenCookies);
        Assertions.assertEquals(0, store.cookies);
    }
    //Stack Test 1
    /**
     * Check the odd function behaviour stacks as expected
     * Stack games with non-zero inventory widest to narrowest, then
     * same for games with zero inventory
     */
    @Test
    void stackOddTest(){
        Store store = new Store();
        Cashier cashier = new Cashier("Ben", .2, Util.odd);
        ArrayList<Integer> dimensions = new ArrayList<>();
        dimensions.add(1);
        dimensions.add(1);
        dimensions.add(1);
        GameList inventory = new GameList();
        for (String gameName : Util.boardGames) {
            Game game = new BoardGame(gameName, new ArrayList(dimensions), 20);
            game.setCount(Util.maxInventory);
            inventory.addGame(gameName, game);
        }
        //Risk first since only game with > 1 inventory
        inventory.get("Gloomhaven").setCount(1);
        inventory.get("Catan").setCount(1);
        cashier.stackShelf(inventory, store.orderedGames);
        Assertions.assertEquals("Risk", inventory.getGameAtPos(1).getGameName());
        //Gloomhaven first since only game with > 1 inventory
        inventory.get("Gloomhaven").setCount(Util.maxInventory);
        inventory.get("Risk").setCount(1);
        cashier.stackShelf(inventory, store.orderedGames);
        Assertions.assertEquals("Gloomhaven", inventory.getGameAtPos(1).getGameName());
        //Monopoly first since widest with non-one inventory
        dimensions.set(1, 2);
        Game game = new BoardGame("Monopoly", new ArrayList(dimensions), 20);
        inventory.addGame("Monopoly", game);
        inventory.get("Monopoly").setCount(Util.maxInventory);
        cashier.stackShelf(inventory, store.orderedGames);
        Util.print("here?");
        Assertions.assertEquals("Monopoly", inventory.getGameAtPos(1).getGameName());
        Assertions.assertEquals("Gloomhaven", inventory.getGameAtPos(2).getGameName());
        //Monopoly second since widest with 1 inventory
        inventory.get("Monopoly").setCount(1);
        cashier.stackShelf(inventory, store.orderedGames);
        Assertions.assertEquals("Gloomhaven", inventory.getGameAtPos(1).getGameName());
        Assertions.assertEquals("Monopoly", inventory.getGameAtPos(2).getGameName());
    }
    //Stack Test 2
    /**
     * Check the tall-short
     */
    @Test
    void stackTallShort(){
        Store store = new Store();
        Cashier cashier = new Cashier("Ben", .2, Util.height);
        ArrayList<Integer> dimensions = new ArrayList<>();
        dimensions.add(1);
        dimensions.add(1);
        dimensions.add(1);
        int height = 5;
        GameList inventory = new GameList();
        for (String gameName : Util.boardGames) {
            height -= 1;
            ArrayList<Integer> temp = new ArrayList(dimensions);
            temp.set(2, height);
            Game game = new BoardGame(gameName, temp, 20);
            game.setCount(Util.maxInventory);
            inventory.addGame(gameName, game);
        }
        //Risk, Gloomhaven, then Catan
        cashier.stackShelf(inventory, store.orderedGames);
        Assertions.assertEquals("Risk", inventory.getGameAtPos(1).getGameName());
        Assertions.assertEquals("Gloomhaven", inventory.getGameAtPos(2).getGameName());
        Assertions.assertEquals("Catan", inventory.getGameAtPos(3).getGameName());
    }
    // Game Test 1
    /**
     * Checks if a family game is instance of a game
     * */
    @Test
    public void checkGameType(){
        FamilyGame Life_plus = new FamilyGame();
        Assertions.assertTrue(Life_plus instanceof Game);
    }
    //Game Test 2
    /**
     * Checks if a board game is instance of a game
     * */
    @Test
    public void checkGameType1(){
        BoardGame boardGame = new BoardGame();
        Assertions.assertTrue(boardGame instanceof Game);
    }
    //Announcer Test 1
    /**
     * Check if guy is an eager announcer
     * */
    @Test
    public void checkGuy(){
        Announcer guyTest;
        guyTest = EagerAnnouncer.getInstance();
        Assertions.assertTrue(guyTest instanceof EagerAnnouncer);
    }
    //Announcer Test 2
    /**
     * Check if guy is a lazy announcer
     * */
    @Test
    public void checkGuy2(){
        Announcer guyTest;
        guyTest = LazyAnnouncer.getInstance();
        Assertions.assertTrue(guyTest instanceof LazyAnnouncer);
    }
    //Announcer Test 3
    /**
     * Check if guy is a lazy announcer, it is not an eagerannouncer
     * */
    @Test
    public void checkGuy3(){
        Announcer guyTest;
        guyTest = EagerAnnouncer.getInstance();
        Assertions.assertTrue(!(guyTest instanceof LazyAnnouncer));
    }

}
