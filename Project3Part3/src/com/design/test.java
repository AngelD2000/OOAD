package com.design;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.*;
public class test {
    @Test
    void incrementStoreTotal() {
        Register register = new Register();
        register.setStoreTotal(30);
        register.incrementStoreTotal(2500);
        Assertions.assertEquals(2530, register.getStoreTotal());
    }

    @Test
    void checkIfNeedFill() {
        Register register = new Register();
        register.setStoreTotal(30);
        register.checkIfNeedFill();
        Assertions.assertEquals(1030, register.getStoreTotal());
    }

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

    @Test
    void addGame() {
        GameList gamelist = new GameList();
        String gameName = "Gloomhaven";
        Game game = new BoardGame(gameName, Util.assign_dim(), 20);
        gamelist.addGame(gameName,game);
        Assertions.assertEquals(1,gamelist.size());
    }
    @Test
    void orderGame(){
        String gameName = "Gloomhaven";
        Game game = new BoardGame(gameName, Util.assign_dim(), 20);
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
        Assertions.assertEquals(20, inventory.get(gameName).getCost());
        Assertions.assertEquals(270, register.getStoreTotal());
    }

    @Test
    void orderCookies(){
        Cashier cashier = new Cashier("Bern", .3, Util.wide);
        Baker baker = new Baker("Bob");
        Store store = new Store();
        store.cookies = 0;
        cashier.orderCookies(baker,store);
        Assertions.assertEquals(2, baker.getDozenPerDay());

    }

}
