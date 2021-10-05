package com.design;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.*;
public class test {
    @Test
    /**
     * Check the incrementStoreTotal changed the money in register
     */
    void incrementStoreTotal() {
        Register register = new Register();
        register.setStoreTotal(30);
        register.incrementStoreTotal(2500);
        Assertions.assertEquals(2530, register.getStoreTotal());
    }

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
    @Test
    /**
     * Test that ordering games correctly changes register
     * Also check that ordering and unpacking games puts 3 in inventory
     */
    void orderGame(){
        String gameName = "Gloomhaven";
        Game game = new BoardGame(gameName, Util.assign_dim(), 20);
        Util.print(game.getGameName());
        Cashier cashier = new Cashier("Bernie", .3, Util.height);
        GameList inventory = new GameList();
        inventory.addGame(gameName,game);
        GameList orderGames = new GameList();
        orderGames.addGame(gameName,game);
        Register register = new Register();
        register.setStoreTotal(300);
        inventory.get(gameName).setCount(0);

        cashier.orderGame(orderGames,inventory,register);

        Assertions.assertEquals(0, inventory.get(gameName).getCount());
        Assertions.assertEquals(20, inventory.get(gameName).getPrice());
        Assertions.assertEquals(270, register.getStoreTotal());

        cashier.unpackOrders(inventory, orderGames);
        Assertions.assertEquals(3, inventory.get(gameName).getCount());
        Assertions.assertEquals(20, inventory.get(gameName).getPrice());
    }

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

}
