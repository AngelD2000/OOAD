package com.design;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.*;
public class test {
    //Register Test 1
    @Test
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
    void checkIfNeedFill() {
        Register register = new Register();
        register.setStoreTotal(30);
        register.checkIfNeedFill();
        Assertions.assertEquals(1030, register.getStoreTotal());
    }

    //GameList Test 1
    @Test
    void removeGame() {
        GameList gamelist = new GameList();
        String gameName = "Gloomhaven";
        Game game = new BoardGame(gameName, Util.assign_dim(), 20);
        gamelist.addGame(gameName, game);
        game.setCount(0);
        gamelist.removeGame(gameName,false);
        Assertions.assertFalse(gamelist.isEmpty());
    }

    //GameList Test 2
    @Test
    void addGame() {
        GameList gamelist = new GameList();
        String gameName = "Gloomhaven";
        Game game = new BoardGame(gameName, Util.assign_dim(), 20);
        gamelist.addGame(gameName,game);
        Assertions.assertEquals(1,gamelist.size());
    }

    //Cashier Test 1
    @Test
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
    void breakGame(){
        Store store = new Store();
        String gameName = "BoardGame";
        Game game = new BoardGame(gameName, Util.assign_dim(), 20);
        game.setCount(Util.maxInventory);
        store.inventory.put(gameName,game);
        store.breakGame();
        Assertions.assertEquals(1,store.brokenGames.size());
    }
    //Store Test 2
    @Test
    void rampage(){
        Store store = new Store();
        Cashier cashier = new Cashier("CookieBen", .2, Util.odd);
        store.cookies = 10;
        store.currentCashier = cashier;
        store.rampage();
        Assertions.assertEquals(10, store.eatenCookies);
    }

    //StackBehavior Test 1
    //StackBehavior Test 2

}
